package net.fashiongo.webadmin.data.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetSubCategoriesParameter {
    @JsonProperty(value = "lvl")
    private Integer lvl;

    @JsonProperty(value = "parentCateId")
    private Integer parentCateId;
}
