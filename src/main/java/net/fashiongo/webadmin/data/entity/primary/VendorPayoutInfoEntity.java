package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Vendor_PayoutInfo")
@Setter
@Getter
public class VendorPayoutInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "VendorPayoutInfoID")
    private Integer vendorPayoutInfoID;

    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "ReferenceID")
    private String referenceID;

    @Column(name = "BusinessName")
    private String businessName;

    @Column(name = "BusinessIdentificationNo")
    private String businessIdentificationNo;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "DateOfBirth")
    private LocalDateTime dateOfBirth;

    @Column(name = "PersonIdentificationNo")
    private String personIdentificationNo;

    @Column(name = "Street")
    private String street;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "Country")
    private String country;

    @Column(name = "Zipcode")
    private String zipcode;

    @Column(name = "BankReferenceID")
    private String bankReferenceID;

    @Column(name = "BankName")
    private String bankName;

    @Column(name = "RoutingNo")
    private String routingNo;

    @Column(name = "AccountNo")
    private String accountNo;

    @Column(name = "AccountHolderName")
    private String accountHolderName;

    @Column(name = "IsVerified")
    private Boolean isVerified;

    @Column(name = "Status")
    private String status;

    @Column(name = "StatusDetail")
    private String statusDetail;

    @Column(name = "AgreedOn")
    private LocalDateTime agreedOn;

    @Column(name = "AgreeIPAddress")
    private String agreeIPAddress;

    @Column(name = "AgreedBy")
    private String agreedBy;

    @Column(name = "VerifiedOn")
    private LocalDateTime verifiedOn;

    @Column(name = "PayoutSchedule")
    private Integer payoutSchedule;

    @Column(name = "MaxPayoutPerDay")
    private Integer maxPayoutPerDay;

    @Column(name = "Weekday")
    private Integer weekday;

    @Column(name = "DayofMonth")
    private Integer dayOfMonth;

    @Column(name = "BillingStatementDescription")
    private String billingStatementDescription;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;
    
    @Column(name = "IsLocked")
    private Boolean isLocked;
}
