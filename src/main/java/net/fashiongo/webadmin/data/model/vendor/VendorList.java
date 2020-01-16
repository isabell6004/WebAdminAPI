package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class VendorList {
    @JsonProperty(value = "rowno")
    private Long rowno;

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
    private Double commissionRate;

    @JsonProperty(value = "FashionGoExclusive")
    private Boolean fashionGoExclusive;

    @JsonProperty(value = "Source_Type")
    private Integer sourceType;

    public VendorList(Long rowno, Timestamp contractExpireDate, Integer wholeSalerID, String companyName, Integer companyTypeID, String firstName, String lastName, String email, String userID, Boolean active, Boolean shopActive, Boolean orderActive, Timestamp startingDate, Timestamp lastModifiedDateTime, String nameHistory, Long grouped, String businessCategory, Integer checkBox, String linkCheck, Long blockedCheck, Long holdCheck, Integer billCountryID, String billState, Integer contractTypeID, Integer photoPlanID, Boolean useModel, BigDecimal commissionRate, Boolean fashionGoExclusive, Integer sourceType) {
        this.rowno = rowno;
        this.contractExpireDate = contractExpireDate == null ? null : contractExpireDate.toLocalDateTime();
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
        this.startingDate = startingDate == null ? null : startingDate.toLocalDateTime();
        this.lastModifiedDateTime = lastModifiedDateTime == null ? null : lastModifiedDateTime.toLocalDateTime();
        this.nameHistory = nameHistory;
        this.grouped = grouped;
        this.businessCategory = businessCategory;
        this.checkBox = checkBox;
        this.linkCheck = linkCheck;
        this.blockedCheck = blockedCheck;
        this.holdCheck = holdCheck;
        this.billCountryID = billCountryID;
        this.billState = billState;
        this.contractTypeID = contractTypeID;
        this.photoPlanID = photoPlanID;
        this.useModel = useModel;
        this.commissionRate = commissionRate == null ? null : commissionRate.doubleValue();
        this.fashionGoExclusive = fashionGoExclusive;
        this.sourceType = sourceType;
    }
}
