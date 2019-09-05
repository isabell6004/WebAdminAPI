package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeaturedItemList {
    @JsonProperty("FeaturedItemID")
    private Integer featuredItemID;

    @JsonProperty("FeaturedItemDate")
    private LocalDateTime featuredItemDate;

    @JsonProperty("BestItemUse")
    private Integer bestItemUse;

    @JsonProperty("WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty("WholeSalerName")
    private String wholeSalerName;

    @JsonProperty("ProductID")
    private Integer productID;

    @JsonProperty("ProductName")
    private String productName;

    @JsonProperty("CreatedOn")
    private LocalDateTime createdOn;

    @JsonProperty("CreatedBy")
    private String createdBy;

    @JsonProperty("ProductID1")
    private Integer productID1;

    @JsonProperty("WholeSalerID1")
    private Integer wholeSalerID1;

    @JsonProperty("ProductName1")
    private String productName1;

    @JsonProperty("UnitPrice")
    private BigDecimal unitPrice;

    @JsonProperty("PictureGeneral")
    private String pictureGeneral;

    @JsonProperty("UrlPath")
    private String urlPath;

    @JsonProperty("DirName")
    private String dirName;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("ActivatedOn")
    private Date activatedOn;

    @JsonProperty("CreatedOn1")
    private Date createdOn1;

    @JsonProperty("ModifiedOn")
    private Date modifiedOn;

    @JsonProperty("RowIndex")
    private Integer rowIndex;
}
