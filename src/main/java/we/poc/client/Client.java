package we.poc.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import we.poc.models.*;

@Component
public class Client {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${fbSendUrl}")
    private String fbSendUrl;

    @Value("${fbMessengerProfileUrl}")
    private String fbMessengerProfileUrl;

    @Value("${fbPageAccessToken}")
    private String pageAccessToken;

    @Value("${fbSubscribeUrl}")
    private String subscribeUrl;

    @Autowired
    private ObjectMapper mapper;

    public void subscribe() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("access_token", this.pageAccessToken);
        restTemplate.postForEntity(this.subscribeUrl, params, String.class);
    }

    private void sendTypingOnIndicator(User recipient) {
        this.restTemplate.postForEntity(this.fbSendUrl,
                new Event()
                        .setRecipient(recipient)
                        .setSenderAction("typing_on"), we.poc.models.Response.class);
    }

    private void sendTypingOffIndicator(User recipient) {
        this.restTemplate.postForEntity(this.fbSendUrl,
                new Event()
                        .setRecipient(recipient)
                        .setSenderAction("typing_off"), we.poc.models.Response.class);
    }

    private final ResponseEntity<String> reply(Event event) {
        sendTypingOnIndicator(event.getRecipient());
        sendTypingOffIndicator(event.getRecipient());
        try {
            return this.restTemplate.postForEntity(this.fbSendUrl, event, String.class);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }

    public ResponseEntity<String> reply(Event event, String text) {
        Event response = new Event()
                .setMessagingType("RESPONSE")
                .setRecipient(event.getSender())
                .setMessage(new Message().setText(text));
        return reply(response);
    }

    protected ResponseEntity<String> reply(Event event, Message message) {
        Event response = new Event()
                .setMessagingType("RESPONSE")
                .setRecipient(event.getSender())
                .setMessage(message);
        return reply(response);
    }

    public void showButtons(Event event, String text, Button[] buttons) {
        reply(event,
              new Message()
                  .setAttachment(new Attachment().setType("template")
                  .setPayload(new Payload()
                                  .setTemplateType("button")
                                  .setText(text)
                                  .setButtons(buttons))));
    }

    public void showTagButtons(Event event, String text, Button[] buttons) {
        reply(event, new Message().setText(text).setQuickReplies(buttons));
    }

    public SenderUser getSenderUser(String id) {
        return this.restTemplate
                .getForObject("https://graph.facebook.com/v2.7/{userId}?access_token={pageAccessToken}",
                        SenderUser.class, id, this.pageAccessToken);
    }

    public void showCarousel(Event event, Message message) {
        sendTypingOnIndicator(event.getSender());
        sendTypingOffIndicator(event.getSender());
        this.reply(event, message);
    }

    // List of text buttons
    public void showListButtons(Event event, Message message) {
        // new Button().setType("web_url").setUrl("http://weenvoyer.com").setTitle("We1"),
        sendTypingOnIndicator(event.getSender());
        sendTypingOffIndicator(event.getSender());
        this.reply(event, message);
    }

}
