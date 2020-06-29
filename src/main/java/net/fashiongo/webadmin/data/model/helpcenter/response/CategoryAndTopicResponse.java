package net.fashiongo.webadmin.data.model.helpcenter.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryAndTopicResponse {
    @JsonProperty(value = "categoryId")
    private Integer categoryId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "isActive")
    private Boolean isActive;

    @JsonProperty(value = "sortNo")
    private Integer sortNo;

    @JsonProperty(value = "topicList")
    private List<TopicsResponse> topicList;
}
