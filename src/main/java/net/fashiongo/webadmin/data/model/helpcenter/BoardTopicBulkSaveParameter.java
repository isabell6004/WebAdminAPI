package net.fashiongo.webadmin.data.model.helpcenter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BoardTopicBulkSaveParameter {
    @JsonProperty(value = "topicId")
    private Integer topicId;

    @JsonProperty(value = "categoryId")
    private Integer categoryId;

    @JsonProperty(value = "sortNo")
    private Integer sortNo;
}
