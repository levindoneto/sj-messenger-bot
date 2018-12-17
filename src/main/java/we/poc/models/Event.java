package we.poc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import we.poc.enummeration.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {

    private static final Logger logger = LoggerFactory.getLogger(Event.class);

    private User sender;
    private User recipient;
    private Message message;
    @JsonProperty("messaging_type")
    private String messagingType;
    private Postback postback;
    @JsonProperty("account_linking")
    private AccountLinking accountLinking;
    private Read read;
    private Delivery delivery;
    @JsonProperty("sender_action")
    private String senderAction;
    @JsonProperty("hub.challenge")
    private String challenge;
    @JsonProperty("get_started")
    private Postback getStarted;
    private Payload[] greeting;
    private EventType type;

    public User getSender() {
        return sender;
    }

    public Event setSender(User sender) {
        this.sender = sender;
        return this;
    }

    public User getRecipient() {
        return recipient;
    }

    public Event setRecipient(User recipient) {
        this.recipient = recipient;
        return this;
    }

    public Message getMessage() {
        return message;
    }

    public Event setMessage(Message message) {
        this.message = message;
        return this;
    }

    public String getMessagingType() {
        return messagingType;
    }

    public Event setMessagingType(String messagingType) {
        this.messagingType = messagingType;
        return this;
    }

    public Postback getPostback() {
        return postback;
    }

    public Event setPostback(Postback postback) {
        this.postback = postback;
        return this;
    }

    public AccountLinking getAccountLinking() {
        return accountLinking;
    }


    public Event setAccountLinking(AccountLinking accountLinking) {
        this.accountLinking = accountLinking;
        return this;
    }

    public Read getRead() {
        return read;
    }

    public Event setRead(Read read) {
        this.read = read;
        return this;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Event setDelivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    public String getSenderAction() {
        return senderAction;
    }

    public Event setSenderAction(String senderAction) {
        this.senderAction = senderAction;
        return this;
    }

    public String getChallenge() {
        return challenge;
    }

    public Event setChallenge(String challenge) {
        this.challenge = challenge;
        return this;
    }

    public Postback getGetStarted() {
        return getStarted;
    }

    public Event setGetStarted(Postback getStarted) {
        this.getStarted = getStarted;
        return this;
    }

    public Payload[] getGreeting() {
        return greeting;
    }

    public Event setGreeting(Payload[] greetings) {
        this.greeting = greetings;
        return this;
    }

    public EventType getType() {
        return type;
    }

    public Event setType(EventType type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing object: ", e);
            return null;
        }
    }
}