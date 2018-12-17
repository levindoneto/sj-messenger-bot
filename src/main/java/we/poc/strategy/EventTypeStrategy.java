package we.poc.strategy;

import we.poc.models.Event;

public interface EventTypeStrategy {

    boolean supports(Event event);

    String getMessage(Event event);
}