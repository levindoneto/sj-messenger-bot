package we.poc.strategy;

import org.springframework.stereotype.Component;
import we.poc.models.Event;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class EventMessageStrategy implements EventTypeStrategy{

    @Override
    public boolean supports(Event event) {
        return nonNull(event.getMessage()) &&
               nonNull(event.getMessage().getText()) &&
               isNull(event.getMessage().getQuickReply()) &&
               isNull(event.getMessage().getAttachment()) &&
               isNull(event.getMessage().getAttachments()) &&
               !Boolean.TRUE.equals(event.getMessage().isEcho());
    }

    @Override
    public String getMessage(Event event) {
        return event.getMessage().getText();
    }
}
