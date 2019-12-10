package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VendorListCSV {
    @Column(name = "ID")
    @JsonProperty(value = "ID")
    private Integer id;

    @Column(name = "Type")
    @JsonProperty(value = "Type")
    private String type;

    @Column(name = "Comapny Name")
    @JsonProperty(value = "Comapny Name")
    private String companyName;

    @Column(name = "Old Company Name")
    @JsonProperty(value = "Old Company Name")
    private String oldCompanyName;

    @Column(name = "Vendor Category")
    @JsonProperty(value = "Vendor Category")
    private String vendorCategory;

    @Column(name = "UserID")
    @JsonProperty(value = "UserID")
    private String userID;

    @Column(name = "FirstName")
    @JsonProperty(value = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    @JsonProperty(value = "LastName")
    private String lastName;

    @Column(name = "EMail")
    @JsonProperty(value = "EMail")
    private String email;

    @Column(name = "Show room Phone")
    @JsonProperty(value = "Show room Phone")
    private String showRoomPhone;

    @Column(name = "Show room Street")
    @JsonProperty(value = "Show room Street")
    private String showRoomStreet;

    @Column(name = "Show room City")
    @JsonProperty(value = "Show room City")
    private String showRoomCity;

    @Column(name = "ShowroomSTATE")
    @JsonProperty(value = "ShowroomSTATE")
    private String showRoomState;

    @Column(name = "Show room Zipcode")
    @JsonProperty(value = "Show room Zipcode")
    private String showRoomZipcode;

    @Column(name = "ShowroomCountry")
    @JsonProperty(value = "ShowroomCountry")
    private String showRoomCountry;

    @Column(name = "Factory Phone")
    @JsonProperty(value = "Factory Phone")
    private String factoryPhone;

    @Column(name = "Factory Street")
    @JsonProperty(value = "Factory Street")
    private String factoryStreet;

    @Column(name = "Factory City")
    @JsonProperty(value = "Factory City")
    private String factoryCity;

    @Column(name = "FactorySTATE")
    @JsonProperty(value = "FactorySTATE")
    private String factoryState;

    @Column(name = "FactoryCountry")
    @JsonProperty(value = "FactoryCountry")
    private String factoryCountry;

    @Column(name = "Factory Zipcode")
    @JsonProperty(value = "Factory Zipcode")
    private String factoryZipcode;

    @Column(name = "Active")
    @JsonProperty(value = "Active")
    private String active;

    @Column(name = "Current Status")
    @JsonProperty(value = "Current Status")
    private String currentStatus;

    @Column(name = "Order Active on")
    @JsonProperty(value = "Order Active on")
    private LocalDateTime orderActiveOn;

    @Column(name = "BillCountryID")
    @JsonProperty(value = "BillCountryID")
    private Integer billCountryID;

    @Column(name = "ContractTypeID")
    @JsonProperty(value = "ContractTypeID")
    private Integer contractTypeID;

    @Column(name = "PhotoPlanID")
    @JsonProperty(value = "PhotoPlanID")
    private Integer photoPlanID;

    @Column(name = "UseModel")
    @JsonProperty(value = "UseModel")
    private String useModel;

    @Column(name = "CommissionRate")
    @JsonProperty(value = "CommissionRate")
    private BigDecimal commissionRate;

    @Column(name = "FashionGoExclusive")
    @JsonProperty(value = "FashionGoExclusive")
    private Boolean fashionGoExclusive;

    @Column(name = "BlockedCheck")
    @JsonProperty(value = "BlockedCheck")
    private long blockedCheck;

    @Column(name = "HoldCheck")
    @JsonProperty(value = "HoldCheck")
    private long holdCheck;
}
