package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class VendorListCSV {
    @JsonProperty(value = "ID")
    private Integer id;

    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "Comapny Name")
    private String companyName;

    @JsonProperty(value = "Old Company Name")
    private String oldCompanyName;

    @JsonProperty(value = "Vendor Category")
    private String vendorCategory;

    @JsonProperty(value = "UserID")
    private String userID;

    @JsonProperty(value = "FirstName")
    private String firstName;

    @JsonProperty(value = "LastName")
    private String lastName;

    @JsonProperty(value = "EMail")
    private String email;

    @JsonProperty(value = "Show room Phone")
    private String showRoomPhone;

    @JsonProperty(value = "Show room Street")
    private String showRoomStreet;

    @JsonProperty(value = "Show room City")
    private String showRoomCity;

    @JsonProperty(value = "ShowroomSTATE")
    private String showRoomState;

    @JsonProperty(value = "Show room Zipcode")
    private String showRoomZipcode;

    @JsonProperty(value = "ShowroomCountry")
    private String showRoomCountry;

    @JsonProperty(value = "Factory Phone")
    private String factoryPhone;

    @JsonProperty(value = "Factory Street")
    private String factoryStreet;

    @JsonProperty(value = "Factory City")
    private String factoryCity;

    @JsonProperty(value = "FactorySTATE")
    private String factoryState;

    @JsonProperty(value = "FactoryCountry")
    private String factoryCountry;

    @JsonProperty(value = "Factory Zipcode")
    private String factoryZipcode;

    @JsonProperty(value = "Active")
    private String active;

    @JsonProperty(value = "Current Status")
    private String currentStatus;

    @JsonProperty(value = "Order Active on")
    private LocalDateTime orderActiveOn;

    @JsonProperty(value = "BillCountryID")
    private Integer billCountryID;

    @JsonProperty(value = "ContractTypeID")
    private Integer contractTypeID;

    @JsonProperty(value = "CommissionRate")
    private Double commissionRate;

    @JsonProperty(value = "FashionGoExclusive")
    private Boolean fashionGoExclusive;

    @JsonProperty(value = "BlockedCheck")
    private Long blockedCheck;

    @JsonProperty(value = "HoldCheck")
    private Long holdCheck;

    public VendorListCSV(Integer id, String type, String companyName, String oldCompanyName, String vendorCategory, String userID, String firstName, String lastName, String email, String showRoomPhone, String showRoomStreet, String showRoomCity, String showRoomState, String showRoomZipcode, String showRoomCountry, String factoryPhone, String factoryStreet, String factoryCity, String factoryState, String factoryCountry, String factoryZipcode, String active, String currentStatus, Timestamp orderActiveOn, Integer billCountryID, Integer contractTypeID, BigDecimal commissionRate, Boolean fashionGoExclusive, Long blockedCheck, Long holdCheck) {
        this.id = id;
        this.type = type;
        this.companyName = companyName;
        this.oldCompanyName = oldCompanyName;
        this.vendorCategory = vendorCategory;
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.showRoomPhone = showRoomPhone;
        this.showRoomStreet = showRoomStreet;
        this.showRoomCity = showRoomCity;
        this.showRoomState = showRoomState;
        this.showRoomZipcode = showRoomZipcode;
        this.showRoomCountry = showRoomCountry;
        this.factoryPhone = factoryPhone;
        this.factoryStreet = factoryStreet;
        this.factoryCity = factoryCity;
        this.factoryState = factoryState;
        this.factoryCountry = factoryCountry;
        this.factoryZipcode = factoryZipcode;
        this.active = active;
        this.currentStatus = currentStatus;
        this.orderActiveOn = orderActiveOn == null ? null : orderActiveOn.toLocalDateTime();
        this.billCountryID = billCountryID;
        this.contractTypeID = contractTypeID;
        this.commissionRate = commissionRate == null ? null : commissionRate.doubleValue();
        this.fashionGoExclusive = fashionGoExclusive;
        this.blockedCheck = blockedCheck;
        this.holdCheck = holdCheck;
    }
}
