package net.fashiongo.webadmin.data.model.helpcenter.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TopicResponse {
    @JsonProperty(value = "topicId")
    private Integer topicId;

    @JsonProperty(value = "categoryId")
    private Integer categoryId;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "isActive")
    private Boolean isActive;

    @JsonProperty(value = "isSetFaq")
    private Boolean isSetFaq;

    @JsonProperty(value = "modifiedOn")
    private LocalDateTime modifiedOn;
}
