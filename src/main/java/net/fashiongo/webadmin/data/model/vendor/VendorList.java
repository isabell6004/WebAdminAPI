package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class VendorList {
    @JsonProperty(value = "rowno")
    private Long rowno;

    @JsonProperty(value = "ContractExpireDate")
    private LocalDateTime contractExpireDate;

    @JsonProperty(value = "WholeSalerID")
    private Long wholeSalerID;

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
    private Long grouped;

    @JsonProperty(value = "BusinessCategory")
    private String businessCategory;

    @JsonProperty(value = "CheckBox")
    private Integer checkBox;

    @JsonProperty(value = "LinkCheck")
    private String linkCheck;

    @JsonProperty(value = "BlockedCheck")
    private Long blockedCheck;

    @JsonProperty(value = "HoldCheck")
    private Long holdCheck;

    @JsonProperty(value = "ContractTypeID")
    private Integer contractTypeID;

    @JsonProperty(value = "CommissionRate")
    private Double commissionRate;

    @JsonProperty(value = "FashionGoExclusive")
    private Boolean fashionGoExclusive;

    @JsonProperty(value = "Source_Type")
    private Integer sourceType;

    public VendorList(Long rowno, String contractExpireDate, Long wholeSalerID, String companyName,
                      Integer companyTypeID, String firstName, String lastName, String email, String userID,
                      Boolean active, Boolean shopActive, Boolean orderActive, String startingDate, String lastModifiedDateTime,
                      String nameHistory, Long grouped, String businessCategory, Integer checkBox, String linkCheck, Long blockedCheck, Long holdCheck,
                      Integer contractTypeID, BigDecimal commissionRate, Boolean fashionGoExclusive, Integer sourceType) {
        this.rowno = rowno;
        this.contractExpireDate = contractExpireDate == null ? null : LocalDateTime.parse(contractExpireDate);
        this.wholeSalerID = wholeSalerID;
        this.companyName = companyName;
        this.companyTypeID = companyTypeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userID = userID;
        this.active = active;
        this.shopActive = shopActive;
        this.orderActive = orderActive;
        this.startingDate = startingDate == null ? null : LocalDateTime.parse(startingDate);
        this.lastModifiedDateTime = lastModifiedDateTime == null ? null : LocalDateTime.parse(lastModifiedDateTime);
        this.nameHistory = nameHistory;
        this.grouped = grouped;
        this.businessCategory = businessCategory;
        this.checkBox = checkBox;
        this.linkCheck = linkCheck;
        this.blockedCheck = blockedCheck;
        this.holdCheck = holdCheck;
        this.contractTypeID = contractTypeID;
        this.commissionRate = commissionRate == null ? null : commissionRate.doubleValue();
        this.fashionGoExclusive = fashionGoExclusive;
        this.sourceType = sourceType;
    }
}
