package we.poc.strategy.actions;

import com.weenvoyer.intention.core.training.model.Callback;
import com.weenvoyer.intention.core.training.model.TagButtonCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import we.poc.client.Client;
import we.poc.models.Button;
import we.poc.models.Event;

import java.util.Optional;
import java.util.function.BiFunction;

@Component
public class ShowTagButtonsStrategy implements ActionStrategy{

    @Autowired
    private Client client;

    @Override
    public boolean supports(Callback callback) {
        return callback instanceof TagButtonCallback;
    }

    @Override
    public void action(Callback callback, Event event, Optional<String> firstName, BiFunction<Optional<String>, String, String> formatName) {
        TagButtonCallback buttonCallback = (TagButtonCallback) callback;
        Button[] buttons = buttonCallback.getButtons()
                                        .stream()
                                        .map(b ->
                                                new Button().setContentType("text").setTitle(b.getText()).setPayload(b.getPayload())
                                        ).toArray(Button[]::new);
        String s = formatName.apply(firstName, buttonCallback.getTextBelow());
        this.client.showTagButtons(event, s, buttons);
    }

}
