package net.fashiongo.webadmin.data.model.helpcenter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BoardCategoryBulkSaveParameter {
    @JsonProperty(value = "categoryId")
    private Integer categoryId;

    @JsonProperty(value = "sortNo")
    private Integer sortNo;
}
