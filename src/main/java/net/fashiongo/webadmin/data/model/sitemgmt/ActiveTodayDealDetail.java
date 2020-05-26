package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActiveTodayDealDetail {
    @JsonProperty("TodayDealID")
    private Integer todayDealID;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("FromDate")
    private Timestamp fromDate;

    @JsonProperty("ToDate")
    private Timestamp toDate;

    @JsonProperty("TodayDealPrice")
    private BigDecimal todayDealPrice;

    @JsonProperty("AppliedOn")
    private Timestamp appliedOn;

    @JsonProperty("ApprovedOn")
    private Timestamp approvedOn;

    @JsonProperty("Active")
    private Boolean active;

    @JsonProperty("ModifiedBy")
    private String modifiedBy;

    @JsonProperty("ModifiedOn")
    private Timestamp modifiedOn;

    @JsonProperty("CreatedByVendor")
    private Boolean createdByVendor;

    @JsonProperty("ProductID")
    private Integer productID;

    @JsonProperty("ProductName")
    private String productName;

    @JsonProperty("ImageUrlRoot")
    private String imageUrlRoot;

    @JsonProperty("DirName")
    private String dirName;

    @JsonProperty("PictureGeneral")
    private String imageName;

    @JsonProperty("CompanyName")
    private String companyName;

    @JsonProperty("WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty("UnitPrice")
    private BigDecimal price;

    private Integer sYear;

    private Integer sMonth;
    
    @JsonProperty("OrderActive")
    private Boolean orderActive; 

}
