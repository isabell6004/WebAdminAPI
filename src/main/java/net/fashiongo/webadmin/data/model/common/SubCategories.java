package net.fashiongo.webadmin.data.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubCategories {
    @JsonProperty(value = "CategoryID")
    private Integer categoryID;

    @JsonProperty(value = "CategoryName")
    private String categoryName;
}
