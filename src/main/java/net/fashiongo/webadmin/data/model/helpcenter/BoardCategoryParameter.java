package net.fashiongo.webadmin.data.model.helpcenter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BoardCategoryParameter {
    @JsonProperty(value = "categoryGroupId")
    private Integer categoryGroupId;

    @JsonProperty(value = "isActive")
    private Boolean isActive;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "colorIconImageFilename")
    private String colorIconImageFilename;

    @JsonProperty(value = "greyIconImageFilename")
    private String greyIconImageFilename;

    @JsonProperty(value = "sortNo")
    private Integer sortNo;
}
