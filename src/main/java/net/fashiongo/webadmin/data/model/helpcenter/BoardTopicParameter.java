package net.fashiongo.webadmin.data.model.helpcenter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BoardTopicParameter {
    @JsonProperty(value = "categoryId")
    private Integer categoryId;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "isActive")
    private Boolean isActive;

    @JsonProperty(value = "isSetFaq")
    private Boolean isSetFaq;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "sortNo")
    private Integer sortNo;
}
