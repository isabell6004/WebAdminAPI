package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SendEmailParameter {
    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "sender")
    private String sender;

    @JsonProperty(value = "sendername")
    private String sendername;

    @JsonProperty(value = "recipient")
    private String recipient;

    @JsonProperty(value = "recipientname")
    private String recipientname;

    @JsonProperty(value = "message")
    private String message;
}
