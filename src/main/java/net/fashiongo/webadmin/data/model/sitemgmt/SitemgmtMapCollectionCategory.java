package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SitemgmtMapCollectionCategory {
    @JsonProperty("MapID")
    private Integer mapID;

    @JsonProperty("CollectionCategoryID")
    private Integer collectionCategoryID;

    @JsonProperty("CategoryID")
    private Integer categoryID;

    @JsonProperty("CategoryName")
    private String categoryName;
}
