package net.fashiongo.webadmin.data.model.helpcenter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardBulkSaveParameter {
    @JsonProperty(value = "categoryList")
    private List<BoardCategoryBulkSaveParameter> categoryList;

    @JsonProperty(value = "topicList")
    private List<BoardTopicBulkSaveParameter> topicList;
}
