package we.poc.strategy;

import org.springframework.stereotype.Component;
import we.poc.models.Event;

import static java.util.Objects.nonNull;

@Component
public class EventQuickReplyStrategy implements EventTypeStrategy {

    @Override
    public boolean supports(Event event) {
        return nonNull(event.getMessage()) &&
               nonNull(event.getMessage().getQuickReply()) &&
               nonNull(event.getMessage().getQuickReply().getPayload());
    }

    @Override
    public String getMessage(Event event){
        return event.getMessage().getQuickReply().getPayload();
    }
}
