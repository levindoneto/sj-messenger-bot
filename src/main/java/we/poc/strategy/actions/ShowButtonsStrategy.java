package we.poc.strategy.actions;

import com.weenvoyer.intention.core.training.model.ButtonCallback;
import com.weenvoyer.intention.core.training.model.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import we.poc.client.Client;
import we.poc.models.Button;
import we.poc.models.Event;
import we.poc.models.User;

import java.util.Optional;
import java.util.function.BiFunction;

@Component
public class ShowButtonsStrategy implements ActionStrategy{

    @Autowired
    private Client client;

    @Override
    public boolean supports(Callback callback) {
        return callback instanceof ButtonCallback;
    }

    @Override
    public void action(Callback callback, Event event, Optional<String> firstName, BiFunction<Optional<String>, String, String> formatName) {
        ButtonCallback buttonCallback = (ButtonCallback) callback;
        Button[] buttons = buttonCallback.getButtons()
                .stream()
                .map(button ->
                        new Button()
                                .setType(button.getType())
                                .setUrl(getUserIdFromButton(event, button.getUrl()))
                                .setPayload(button.getPayload())
                                .setTitle(button.getText())
                ).toArray(Button[]::new);
        String text = formatName.apply(firstName, buttonCallback.getName());
        this.client.showButtons(event, text, buttons);
    }

    private String getUserIdFromButton(Event event, String url) {
        return Optional.ofNullable(url)
                       .map(u -> u.replace("{facebookId}", event.getSender().getId()))
                       .orElse(null);
    }
}
