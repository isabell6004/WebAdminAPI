package net.fashiongo.webadmin.data.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class CreditCardInfo {
    @JsonProperty("CreditCardID")
    private Integer creditCardID;

    @JsonProperty("RetailerID")
    private Integer retailerID;

    @JsonProperty("ReferenceID")
    private String referenceID;

    @JsonProperty("Last4Digit")
    private String last4Digit;

    @JsonProperty("CVV")
    private String cvv;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("NameOnCard")
    private String nameOnCard;

    @JsonProperty("Street")
    private String street;

    @JsonProperty("City")
    private String city;

    @JsonProperty("State")
    private String state;

    @JsonProperty("Zipcode")
    private String zipcode;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("CountryID")
    private Integer countryID;

    @JsonProperty("IsDefaultCard")
    private Boolean defaultCard;

    @JsonProperty("CardStatusID")
    private Integer cardStatusID;

    @JsonProperty("IsDeleted")
    private Boolean deleted;

    @JsonProperty("CreatedOn")
    private LocalDateTime createdOn;

    @JsonProperty("CreatedBy")
    private String createdBy;

    @JsonProperty("ModifiedOn")
    private LocalDateTime modifiedOn;

    @JsonProperty("ModifiedBy")
    private String modifiedBy;

    @JsonProperty("CardTypeID")
    private Integer cardTypeID;

    @JsonProperty("CreditCardType")
    private String creditCardType;

    @JsonProperty("Buyer")
    private String buyer;

    @JsonProperty("CreditCardInfo")
    private String creditCardInfo;

    @JsonProperty("CardStatusName")
    private String cardStatusName;

    @JsonProperty("BillingAddress")
    private String billingAddress;

    @JsonProperty("ExpirationMonth")
    private Integer expirationMonth;

    @JsonProperty("ExpirationYear")
    private Integer expirationYear;

    @JsonProperty("ExpDate")
    private String expDate;

    @JsonProperty("PendingCount")
    private Integer pendingCount;

    @JsonProperty("Reason")
    private String reason;

    public CreditCardInfo() {
    }

    public CreditCardInfo(Integer creditCardID, Integer retailerID, String referenceID, String last4Digit, String cvv, String email, String nameOnCard, String street, String city, String state, String zipcode, String country, Integer countryID, Boolean defaultCard, Integer cardStatusID, Boolean deleted, Timestamp createdOn, String createdBy, Timestamp modifiedOn, String modifiedBy, Integer cardTypeID, String creditCardType, String buyer, String creditCardInfo, String cardStatusName, String billingAddress, Integer expirationMonth, Integer expirationYear, String expDate, Long pendingCount, String reason) {
        this.creditCardID = creditCardID;
        this.retailerID = retailerID;
        this.referenceID = referenceID;
        this.last4Digit = last4Digit;
        this.cvv = cvv;
        this.email = email;
        this.nameOnCard = nameOnCard;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.countryID = countryID;
        this.defaultCard = defaultCard;
        this.cardStatusID = cardStatusID;
        this.deleted = deleted;
        this.createdOn = createdOn == null ? null : createdOn.toLocalDateTime();
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn == null ? null : modifiedOn.toLocalDateTime();
        this.modifiedBy = modifiedBy;
        this.cardTypeID = cardTypeID;
        this.creditCardType = creditCardType;
        this.buyer = buyer;
        this.creditCardInfo = creditCardInfo;
        this.cardStatusName = cardStatusName;
        this.billingAddress = billingAddress;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.expDate = expDate;
        this.pendingCount = pendingCount == null ? null : pendingCount.intValue();
        this.reason = reason;
    }
}
