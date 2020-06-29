package net.fashiongo.webadmin.data.model.helpcenter.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    @JsonProperty(value = "categoryId")
    private Integer categoryId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "colorIconImageUrl")
    private String colorIconImageUrl;

    @JsonProperty(value = "greyIconImageUrl")
    private String greyIconImageUrl;

    @JsonProperty(value = "isActive")
    private Boolean isActive;

    @JsonProperty(value = "sortNo")
    private Integer sortNo;
}
