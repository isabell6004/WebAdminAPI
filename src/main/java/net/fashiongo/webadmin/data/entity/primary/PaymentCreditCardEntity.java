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
@Table(name = "PaymentCreditCard")
@Getter
@Setter
public class PaymentCreditCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "CreditCardID")
    private Integer creditCardID;

    @Column(name = "RetailerID")
    private Integer retailerID;

    @Column(name = "ReferenceID")
    private String referenceID;

    @Column(name = "CardTypeID")
    private Integer cardTypeID;

    @Column(name = "Last4Digit")
    private String last4Digit;

    @Column(name = "CVV")
    private String cvv;

    @Column(name = "ExpirationMonth")
    private Integer expirationMonth;

    @Column(name = "ExpirationYear")
    private Integer expirationYear;

    @Column(name = "NameOnCard")
    private String nameOnCard;

    @Column(name = "Fingerprint")
    private String fingerprint;

    @Column(name = "Email")
    private String email;

    @Column(name = "Street")
    private String street;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "Zipcode")
    private String zipcode;

    @Column(name = "Country")
    private String country;

    @Column(name = "CountryID")
    private Integer countryID;

    @Column(name = "IsDefaultCard")
    private Boolean isDefaultCard;

    @Column(name = "CardStatusID")
    private Integer cardStatusID;

    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Column(name = "ReferenceID2")
    private String referenceID2;

    @Column(name = "Source")
    private String source;

    @Column(name = "SourceDate")
    private LocalDateTime sourceDate;

    @Column(name = "Card_Funding")
    private String cardFunding;
}
