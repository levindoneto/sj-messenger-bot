package we.poc.strategy.actions;

import com.weenvoyer.intention.core.training.model.*;
import com.weenvoyer.intention.core.training.model.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import we.poc.client.Client;
import we.poc.models.*;
import we.poc.models.Button;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Component
public class CarouselButtonsStrategy implements ActionStrategy{

    @Autowired
    private Client client;

    @Override
    public boolean supports(Callback callback) {
        return callback instanceof CarouselButtonCallback;
    }

    @Override
    public void action(Callback callback, Event event, Optional<String> firstName, BiFunction<Optional<String>, String, String> formatName) {
        CarouselButtonCallback callback1 = (CarouselButtonCallback) callback;
        this.formatUserName(firstName, formatName, callback1);
        Message message = this.buildMessage(callback1);
        this.client.showCarousel(event, message);
    }

    private void formatUserName(Optional<String> firstName, BiFunction<Optional<String>, String, String> formatName, CarouselButtonCallback callback) {
        callback.getCarouselItems()
                .forEach(it -> {
                    it.setTitle(formatName.apply(firstName, it.getTitle()));
                    it.setSubtitle(formatName.apply(firstName, it.getSubtitle()));
                });
    }

    private Message buildMessage(CarouselButtonCallback callback) {
        return new Message()
                .setAttachment(this.buildAttachment(callback.getCarouselItems()));
    }

    private Attachment buildAttachment(List<Carousel> carouselItems) {
        return new Attachment()
                .setType("template")
                .setPayload(new Payload()
                             .setTemplateType("generic")
                             .setElements(this.buildElements(carouselItems)));
    }

    private Element[] buildElements(List<Carousel> list) {
       return list.stream()
                  .map(callback ->
                         new Element()
                          .setButtons(this.buildButtons(callback.getButtons()))
                          .setTitle(callback.getTitle())
                          .setSubtitle(callback.getSubtitle())
                          .setImageUrl(callback.getImageUrl()))
                  .toArray(Element[]::new);
    }

    private Button[] buildButtons(List<CarouselButton> carouselButtonList) {
        return carouselButtonList
                .stream()
                .map(this::buildButton)
                .toArray(Button[]::new);
    }

    private Button buildButton(CarouselButton button) {
        return new Button()
                .setType(button.getType())
                .setUrl(button.getUrl())
                .setPayload(button.getPayload())
                .setTitle(button.getTitle());
    }
}
