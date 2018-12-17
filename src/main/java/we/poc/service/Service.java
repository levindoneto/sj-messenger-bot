package we.poc.service;

import org.springframework.beans.factory.annotation.Value;
import we.poc.enummeration.EventType;

@org.springframework.stereotype.Service
public class Service {

    @Value("${fbBotToken}")
    private String fbToken;

    public boolean isValidToken(String mode, String verifyToken) {
        return EventType.SUBSCRIBE.name().equalsIgnoreCase(mode) && this.fbToken.equals(verifyToken);
    }
}
