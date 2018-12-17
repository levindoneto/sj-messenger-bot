package we.poc.strategy;

import org.springframework.stereotype.Component;
import we.poc.models.Event;

import static java.util.Objects.nonNull;

@Component
public class EventPostbackStrategy implements EventTypeStrategy{

    @Override
    public boolean supports(Event event) {
        return nonNull(event.getPostback()) &&
               nonNull(event.getPostback().getPayload()) ;
    }

    @Override
    public String getMessage(Event event) {
        return event.getPostback().getPayload();
    }
}
