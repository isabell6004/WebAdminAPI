package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SitemgmtCollectionCategory {
    @JsonProperty("CategoryID")
    private Integer categoryID;

    @JsonProperty("ParentCategoryID")
    private Integer parentCategoryID;

    @JsonProperty("CategoryName")
    private String categoryName;

    @JsonProperty("Lvl")
    private Integer lvl;

    @JsonProperty("ListOrder")
    private Integer listOrder;

    @JsonProperty("Active")
    private boolean active;

    @JsonProperty("expended")
    private int expended;

    @JsonProperty("NodeCnt")
    private Long nodeCnt;

    @JsonProperty("SpotID")
    private Integer spotId;

    @JsonProperty("ServiceInUse")
    private String serviceInUse;

    @JsonProperty("VendorType")
    private String vendorType;

    @JsonProperty("VendorTierGroup")
    private String vendorTierGroup;

    @JsonProperty("OrderBy")
    private String orderBy;

    @JsonProperty("ModifiedBy")
    private String modifiedBy;

    @JsonProperty("ModifiedOn")
    private LocalDateTime modifiedOn;
}
