package we.poc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Postback {

    private String payload;

    public String getPayload() {
        return payload;
    }

    public Postback setPayload(String payload) {
        this.payload = payload;
        return this;
    }

}
