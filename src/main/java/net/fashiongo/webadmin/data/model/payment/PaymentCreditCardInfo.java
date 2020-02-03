package net.fashiongo.webadmin.data.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class PaymentCreditCardInfo {
    @JsonProperty(value = "CreditCardID")
    private Integer creditCardID;

    @JsonProperty(value = "RetailerID")
    private Integer retailerID;

    @JsonProperty(value = "ReferenceID")
    private String referenceID;

    @JsonProperty(value = "CardTypeID")
    private Integer cardTypeID;

    @JsonProperty(value = "Last4Digit")
    private String last4Digit;

    @JsonProperty(value = "CVV")
    private String cvv;

    @JsonProperty(value = "ExpirationMonth")
    private Integer expirationMonth;

    @JsonProperty(value = "ExpirationYear")
    private Integer expirationYear;

    @JsonProperty(value = "NameOnCard")
    private String nameOnCard;

    @JsonProperty(value = "Fingerprint")
    private String fingerprint;

    @JsonProperty(value = "Email")
    private String email;

    @JsonProperty(value = "Street")
    private String street;

    @JsonProperty(value = "City")
    private String city;

    @JsonProperty(value = "State")
    private String state;

    @JsonProperty(value = "Zipcode")
    private String zipcode;

    @JsonProperty(value = "Country")
    private String country;

    @JsonProperty(value = "CountryID")
    private Integer countryID;

    @JsonProperty(value = "IsDefaultCard")
    private Boolean isDefaultCard;

    @JsonProperty(value = "CardStatusID")
    private Integer cardStatusID;

    @JsonProperty(value = "IsDeleted")
    private Boolean isDeleted;

    @JsonProperty(value = "CreatedOn")
    private LocalDateTime createdOn;

    @JsonProperty(value = "CreatedBy")
    private String createdBy;

    @JsonProperty(value = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @JsonProperty(value = "ModifiedBy")
    private String modifiedBy;

    @JsonProperty(value = "ReferenceID2")
    private String referenceID2;

    @JsonProperty(value = "Source")
    private String source;

    @JsonProperty(value = "SourceDate")
    private LocalDateTime sourceDate;

    @JsonProperty(value = "Card_Funding")
    private String cardFunding;

    @JsonProperty(value = "CreditCardTypeName")
    private String creditCardTypeName;

    @JsonProperty(value = "CardStatusName")
    private String cardStatusName;

    public PaymentCreditCardInfo() {
    }

    public PaymentCreditCardInfo(Integer creditCardID, Integer retailerID, String referenceID, Integer cardTypeID, String last4Digit, String cvv, Integer expirationMonth, Integer expirationYear, String nameOnCard, String fingerprint, String email, String street, String city, String state, String zipcode, String country, Integer countryID, Boolean isDefaultCard, Integer cardStatusID, Boolean isDeleted, LocalDateTime createdOn, String createdBy, LocalDateTime modifiedOn, String modifiedBy, String referenceID2, String source, LocalDateTime sourceDate, String cardFunding, String creditCardTypeName, String cardStatusName) {
        this.creditCardID = creditCardID;
        this.retailerID = retailerID;
        this.referenceID = referenceID;
        this.cardTypeID = cardTypeID;
        this.last4Digit = last4Digit;
        this.cvv = cvv;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.nameOnCard = nameOnCard;
        this.fingerprint = fingerprint;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.countryID = countryID;
        this.isDefaultCard = isDefaultCard;
        this.cardStatusID = cardStatusID;
        this.isDeleted = isDeleted;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
        this.referenceID2 = referenceID2;
        this.source = source;
        this.sourceDate = sourceDate;
        this.cardFunding = cardFunding;
        this.creditCardTypeName = creditCardTypeName;
        this.cardStatusName = cardStatusName;
    }
}
