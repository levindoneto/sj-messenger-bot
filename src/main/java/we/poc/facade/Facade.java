package we.poc.facade;

import com.weenvoyer.intention.core.intentdetection.IntentDetectionService;
import com.weenvoyer.intention.core.intentdetection.model.IntentionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import we.poc.client.Client;
import we.poc.data.UserContext;
import we.poc.models.*;
import we.poc.service.Service;
import we.poc.service.UserContextService;
import we.poc.strategy.EventTypeStrategy;
import we.poc.strategy.actions.ActionStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Facade {

    @Autowired
    private Service service;

    @Autowired
    private Client client;

    @Autowired
    private List<EventTypeStrategy> eventStrategies;

    @Autowired
    private List<ActionStrategy> actionStrategies;

    @Autowired
    private UserContextService contextService;

    @Autowired
    private IntentDetectionService intentDetectionService;

    public void subscribe() {
        this.client.subscribe();
    }

    public ResponseEntity isValidToken(String mode, String verifyToken, String challenge) {
        if (this.service.isValidToken(mode, verifyToken)) {
            return ResponseEntity.ok(challenge);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    public ResponseEntity conversation(Callback callback) {
        if (!isConversationFromPage(callback)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.processEvent(callback);
        return ResponseEntity.ok("EVENT_RECEIVED");
    }

    private void processEvent(Callback callback) {
        Optional.ofNullable(callback)
                .map(Callback::getEntry)
                .map(Arrays::asList)
                .map(this::filterNonNullAndMapMessage)
                .ifPresent(this::processEvent);
    }

    private void processEvent(List<Event> list) {
        list.forEach(e ->
                this.filterStrategy(e)
                        .ifPresent(s -> this.processEvent(e, s))
        );
    }

    private void processEvent(Event event, EventTypeStrategy strategy) {
        Optional<UserContext> userContextOp = Optional.ofNullable(event)
                                                            .map(Event::getSender)
                                                            .map(User::getId)
                                                            .map(this::setUserContext);
        Optional<IntentionModel> intentionOp = userContextOp.map(userContext -> this.detectIntention(event, strategy, userContext));

        Optional<String> firstName = userContextOp.map(UserContext::getFirstName);
        intentionOp.ifPresent(i -> this.overrideUserContext(i, event));
        Optional<String> outputOp = intentionOp.map(IntentionModel::getOutput)
                                               .filter(o -> !StringUtils.isEmpty(o))
                                               .map(o -> this.formatName(firstName, o));
        outputOp.ifPresentOrElse(
           (o) ->  this.client.reply(event, o),
           ( ) -> intentionOp.ifPresent(i -> this.invokeActionStrategies(event, i, firstName))
        );
    }

    private String formatName(Optional<String> firstName, String o) {
        return firstName.map(name -> this.formatUserFirstName(name, o))
                 .orElse(o);
    }

    private void invokeActionStrategies(Event event, IntentionModel i, Optional<String> firstName) {
         this.actionStrategies
                        .stream()
                        .filter(it -> it.supports(i.getCallback()))
                        .findFirst()
                        .ifPresent(it -> it.action(i.getCallback(), event, firstName, this::formatName));
    }

    private String formatUserFirstName(String firstName, String o) {
        return o.replace("@name", firstName);
    }

    private void overrideUserContext(IntentionModel intentionModel, Event event) {
        this.contextService.override(event.getSender().getId(), intentionModel.getContext());
    }

    private IntentionModel detectIntention(Event event, EventTypeStrategy strategy, UserContext userContext) {
        return this.intentDetectionService.detectIntention(strategy.getMessage(event),
                                                           userContext.getChatContext());
    }

    public UserContext setUserContext(String id) {
        UserContext userContext = this.contextService.find(id);
        if (userContext.getFirstName() == null) {
            SenderUser senderUser = this.client.getSenderUser(id);
            userContext.setFirstName(senderUser.getFirstName());
            this.contextService.update(userContext);
        }
        return userContext;
    }

    private Optional<EventTypeStrategy> filterStrategy(Event e) {
        return this.eventStrategies
                .stream()
                .filter(it -> it.supports(e))
                .findFirst();
    }

    private List<Event> filterNonNullAndMapMessage(List<Entry> list) {
        return list.stream()
                .filter(entry -> Objects.nonNull(entry.getMessaging()))
                .map(Entry::getMessaging)
                .map(Arrays::asList)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private boolean isConversationFromPage(Callback callback) {
        return callback.getObject().equals("page");
    }
}
