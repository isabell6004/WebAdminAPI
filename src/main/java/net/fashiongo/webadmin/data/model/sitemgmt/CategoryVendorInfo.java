package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVendorInfo {
    @JsonProperty("CompanyName")
    private String companyName;

    @JsonProperty("WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty("CompanyTypeID")
    private Integer companyTypeID;
    
    private Integer vendorTypeId;

    @JsonProperty("SelectChk")
    private Integer selectChk;

    @JsonProperty("ViewChk")
    private Integer viewChk;

    @JsonProperty("BuyerRate")
    private BigDecimal buyerRate;

    @JsonProperty("VendorRate")
    private BigDecimal vendorRate;

    @JsonProperty("VendorTierGroup")
    private String vendorTierGroup;

    private Boolean isCM;
    private Boolean isPG;
    private Boolean isCS;
}
