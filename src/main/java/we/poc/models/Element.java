package we.poc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Element {
    
    private String title;
    private String type;
    private String subtitle;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("item_url")
    private String itemUrl;
    @JsonProperty("default_action")
    private Button defaultAction;
    private Button[] buttons;

    public String getTitle() {
        return title;
    }

    public Element setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Element setType(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }

    public Element setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Element setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public Element setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
        return this;
    }

    public Button getDefaultAction() {
        return defaultAction;
    }

    public Element setDefaultAction(Button defaultAction) {
        this.defaultAction = defaultAction;
        return this;
    }

    public Button[] getButtons() {
        return buttons;
    }

    public Element setButtons(Button[] buttons) {
        this.buttons = buttons;
        return this;
    }
}
