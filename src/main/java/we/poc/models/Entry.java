package we.poc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entry {

    private String id;
    private Long time;
    private Event[] messaging;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Event[] getMessaging() {
        return messaging;
    }

    public void setMessaging(Event[] messaging) {
        this.messaging = messaging;
    }
}
