package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryList {
    @JsonProperty("CategoryID")
    private Integer categoryID;

    @JsonProperty("ParentParentCategoryID")
    private Integer parentParentCategoryID;

    @JsonProperty("ParentCategoryID")
    private Integer parentCategoryID;

    @JsonProperty("CategoryName")
    private String categoryName;

    @JsonProperty("CategoryDescription")
    private String categoryDescription;

    @JsonProperty("TitleImage")
    private String titleImage;

    @JsonProperty("IsLandingPage")
    private Boolean isLandingPage;

    @JsonProperty("IsFeatured")
    private Boolean isFeatured;

    @JsonProperty("Lvl")
    private Integer lvl;

    @JsonProperty("ListOrder")
    private Integer listOrder;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("expended")
    private Integer expended;

    @JsonProperty("NodeCnt")
    private Long nodeCnt;

    @JsonProperty("CollectionCategoryID")
    private Integer collectionCategoryID;
}
