package net.fashiongo.webadmin.data.model.helpcenter.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaqsResponse {
    @JsonProperty(value = "faqId")
    private Integer faqId;

    @JsonProperty(value = "topicId")
    private Integer topicId;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "categoryId")
    private Integer categoryId;

    @JsonProperty(value = "categoryName")
    private String categoryName;

    @JsonProperty(value = "sortNo")
    private Integer sortNo;
}
