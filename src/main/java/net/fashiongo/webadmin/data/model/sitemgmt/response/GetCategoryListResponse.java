package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.data.model.sitemgmt.CategoryList;

import java.util.List;

@JsonSerialize
@Getter
@Builder
public class GetCategoryListResponse {
    @JsonProperty("Table")
    private List<CategoryList> categoryList;

}
