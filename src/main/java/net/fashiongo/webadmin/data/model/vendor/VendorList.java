package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VendorList {
    @JsonProperty(value = "rowno")
    private Integer rowno;

    @JsonProperty(value = "ContractExpireDate")
    private LocalDateTime contractExpireDate;

    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "CompanyName")
    private String companyName;

    @JsonProperty(value = "CompanyTypeID")
    private Integer companyTypeID;

    @JsonProperty(value = "FirstName")
    private String firstName;

    @JsonProperty(value = "LastName")
    private String lastName;

    @JsonProperty(value = "Email")
    private String email;

    @JsonProperty(value = "UserID")
    private String userID;

    @JsonProperty(value = "Active")
    private Boolean active;

    @JsonProperty(value = "ShopActive")
    private Boolean shopActive;

    @JsonProperty(value = "OrderActive")
    private Boolean orderActive;

    @JsonProperty(value = "StartingDate")
    private LocalDateTime startingDate;

    @JsonProperty(value = "LastModifiedDateTime")
    private LocalDateTime lastModifiedDateTime;

    @JsonProperty(value = "NameHistory")
    private String nameHistory;

    @JsonProperty(value = "Grouped")
    private Integer grouped;

    @JsonProperty(value = "BusinessCategory")
    private String businessCategory;

    @JsonProperty(value = "CheckBox")
    private Integer checkBox;

    @JsonProperty(value = "LinkCheck")
    private String linkCheck;

    @JsonProperty(value = "BlockedCheck")
    private long blockedCheck;

    @JsonProperty(value = "HoldCheck")
    private long holdCheck;

    @JsonProperty(value = "BillCountryID")
    private Integer billCountryID;

    @JsonProperty(value = "BillState")
    private String billState;

    @JsonProperty(value = "ContractTypeID")
    private Integer contractTypeID;

    @JsonProperty(value = "PhotoPlanID")
    private Integer photoPlanID;

    @JsonProperty(value = "UseModel")
    private Boolean useModel;

    @JsonProperty(value = "CommissionRate")
    private BigDecimal commissionRate;

    @JsonProperty(value = "FashionGoExclusive")
    private Boolean fashionGoExclusive;
    
    @JsonProperty(value = "Source_Type")
    private Boolean sourceType;
}
