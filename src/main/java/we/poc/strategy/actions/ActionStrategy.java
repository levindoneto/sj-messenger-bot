package we.poc.strategy.actions;

import com.weenvoyer.intention.core.training.model.Callback;
import we.poc.models.Event;

import java.util.Optional;
import java.util.function.BiFunction;

public interface ActionStrategy {

    boolean supports(Callback callback);
    void action(Callback callback, Event event, Optional<String> firstName, BiFunction<Optional<String>, String, String> formatName);
}
