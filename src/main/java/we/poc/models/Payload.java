package we.poc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payload {

    @JsonProperty("template_type")
    private String templateType;
    private String locale;
    private String text;
    private Button[] buttons;
    private Element[] elements;

    public String getTemplateType() {
        return templateType;
    }

    public Payload setTemplateType(String templateType) {
        this.templateType = templateType;
        return this;
    }

    public String getLocale() {
        return locale;
    }

    public Payload setLocale(String locale) {
        this.locale = locale;
        return this;
    }

    public String getText() {
        return text;
    }

    public Payload setText(String text) {
        this.text = text;
        return this;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public Payload setButtons(Button[] buttons) {
        this.buttons = buttons;
        return this;
    }

    public Element[] getElements() {
        return elements;
    }

    public Payload setElements(Element[] elements) {
        this.elements = elements;
        return this;
    }
}
