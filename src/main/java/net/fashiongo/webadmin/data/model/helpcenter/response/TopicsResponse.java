package net.fashiongo.webadmin.data.model.helpcenter.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicsResponse {
    @JsonProperty(value = "topicId")
    private Integer topicId;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "sortNo")
    private Integer sortNo;

    @JsonProperty(value = "isActive")
    private Boolean isActive;

    @JsonProperty(value = "isSetFaq")
    private Boolean isSetFaq;
}
