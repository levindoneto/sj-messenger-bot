package we.poc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
    
    @JsonProperty("is_echo")
    private Boolean isEcho;
    private String text;
    private Attachment attachment;
    private Attachment[] attachments;
    @JsonProperty("quick_reply")
    private Button quickReply;
    @JsonProperty("quick_replies")
    private Button[] quickReplies;
    

    public Boolean isEcho() {
        return isEcho;
    }

    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public Message setAttachment(Attachment attachment) {
        this.attachment = attachment;
        return this;
    }

    public Attachment[] getAttachments() {
        return attachments;
    }

    public Message setAttachments(Attachment[] attachments) {
        this.attachments = attachments;
        return this;
    }

    public Button getQuickReply() {
        return quickReply;
    }

    public Message setQuickReply(Button quickReply) {
        this.quickReply = quickReply;
        return this;
    }

    public Button[] getQuickReplies() {
        return quickReplies;
    }

    public Message setQuickReplies(Button[] quickReplies) {
        this.quickReplies = quickReplies;
        return this;
    }
}
