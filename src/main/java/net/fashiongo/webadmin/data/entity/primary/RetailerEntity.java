package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.utility.Utility;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Setter
@Getter
@Entity
@Table(name = "tblRetailer")
@DynamicUpdate
public class RetailerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RetailerID")
	@Setter(AccessLevel.NONE)
	private Integer retailerID;

	@Column(name = "RetailerGUID")
	private String retailerGUID;

	@Column(name = "StartingDate")
	private Timestamp startingDate;

	@Column(name = "CompanyName")
	private String companyName;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "Description")
	private String description;

	@Column(name = "BillStreetNo")
	private String billStreetNo;

	@Column(name = "BillCity")
	private String billCity;

	@Column(name = "BillSTATE")
	private String billSTATE;

	@Column(name = "BillZipcode")
	private String billZipcode;

	@Column(name = "BillCountry")
	private String billCountry;

	@Column(name = "BillPhone")
	private String billPhone;

	@Column(name = "BillFax")
	private String billFax;

	@Column(name = "StreetNo")
	private String streetNo;

	@Column(name = "City")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "Zipcode")
	private String zipcode;

	@Column(name = "Country")
	private String country;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "EMail")
	private String email;

	@Column(name = "Fax")
	private String fax;

	@Column(name = "AddressStatus")
	private Boolean addressStatus;

	@Column(name = "Memo")
	private String memo;

	@Column(name = "WebSite")
	private String webSite;

	@Column(name = "USERID")
	private String userID;

	@Column(name = "Pwd")
	private String pwd;

	@Column(name = "Active")
	private String active;

	@Column(name = "DM")
	private String dm;

	@Column(name = "POSUse")
	private String poSUse;

	@Column(name = "RetailerPermitNo")
	private String retailerPermitNo;

	@Column(name = "Vendor1Name")
	private String vendor1Name;

	@Column(name = "Vendor2Name")
	private String vendor2Name;

	@Column(name = "Vendor1Phone")
	private String vendor1Phone;

	@Column(name = "Vendor2Phone")
	private String vendor2Phone;

	@Column(name = "LastLoginDate")
	private Timestamp lastLoginDate;

	@Column(name = "LoginCount")
	private Integer loginCount;

	@Column(name = "CurrentStatus")
	private Integer currentStatus;

	@Column(name = "CurrentComment")
	private String currentComment;

	@Column(name = "NewYN")
	private String newYN;

	@Column(name = "EditorScore")
	private Integer editorScore;

	@Column(name = "FashiongoScore")
	private Integer fashiongoScore;

	@Column(name = "FavoriteVendor")
	private String favoriteVendor;

	@Column(name = "BillStreetNo2")
	private String billStreetNo2;

	@Column(name = "StreetNo2")
	private String streetNo2;

	@Column(name = "AsKnownAs")
	private String asKnownAs;

	@Column(name = "LastUser")
	private String lastUser;

	@Column(name = "LastModifiedDateTime")
	private Timestamp lastModifiedDateTime;

	@Column(name = "InHouseMemo")
	private String inHouseMemo;

	@Column(name = "TerminatedBy")
	private String terminatedBy;

	@Column(name = "TerminatedNote")
	private String terminatedNote;

	@Column(name = "TerminatedDate")
	private Timestamp terminatedDate;

	@Column(name = "EmailSentYN")
	private Boolean emailSentYN;

	@Column(name = "EmailSentDate")
	private Timestamp emailSentDate;

	@Column(name = "EmailState")
	private Integer emailState;

	@Column(name = "HowKnownType")
	private Integer howKnownType;

	@Column(name = "HowKnownOther")
	private String howKnownOther;

	@Column(name = "MarketCategoryID")
	private Integer marketCategoryID;

	@Column(name = "PrmYN")
	private boolean prmYN;

	@Column(name = "ShowFGNews")
	private boolean showFGNews = true;

	@Column(name = "ShowFavVendor")
	private boolean showFavVendor = true;

	@Column(name = "ReceiveFGNewsletter")
	private boolean receiveFGNewsletter = true;

	@Column(name = "BillPhoneAlternate")
	private String billPhoneAlternate;

	@Column(name = "BillPhoneAlternate2")
	private String billPhoneAlternate2;

	@Column(name = "PhoneAlternate")
	private String phoneAlternate;

	@Column(name = "PhoneAlternate2")
	private String phoneAlternate2;

	@Column(name = "IsCommercialAddress")
	private Boolean isCommercialAddress;

	@Column(name = "StoreNo")
	private String storeNo;

	@Column(name = "ShipName")
	private String shipName;

	@Column(name = "ShipAttention")
	private String shipAttention;

	@Column(name = "SNS")
	private String sns;

	@Column(name = "UseMobile")
	private String useMobile;

	@Column(name = "SellerPermitFileName")
	private String sellerPermitFileName;

	@Column(name = "InvoiceFileName1")
	private String invoiceFileName1;

	@Column(name = "InvoiceFileName2")
	private String invoiceFileName2;

	@Column(name = "AdditionalDocumentFileName")
	private String additionalDocumentFileName;

	@Column(name = "ApprovedOn")
	private Timestamp approvedOn;

	@Column(name = "BillCountryID")
	private Integer billCountryID;

	@Column(name = "CountryID")
	private Integer countryID;

	@Column(name = "CreditScore")
	private int creditScore;

	@Column(name = "CreatedByVendorID")
	private Integer createdByVendorID;

	@Column(name = "ConfirmedOn")
	private Timestamp confirmedOn;

	@Column(name = "ConfirmedBy")
	private String confirmedBy;

	@Column(name = "EmailVerifiedOn")
	private Timestamp emailVerifiedOn;

	@Column(name = "IsOperatorRead")
	private Boolean isOperatorRead;

	@Column(name = "AcceptSmsMessage")
	private Boolean acceptSmsMessage;

	@Column(name = "AcceptPushMessage")
	private Boolean acceptPushMessage;

	@Column(name = "MobilePhoneNo")
	private String mobilePhoneNo;

	@Column(name = "buyer_company_id")
	private Long buyerCompanyId;

	@Column(name = "buyer_type")
	private Integer buyerType = 1;

	@Column(name = "buyer_class")
	private Integer buyerClass;
	
	@Column(name = "am_user_id")
	private Integer amUserId;		
	
	public LocalDateTime getStartingDate() {
		return Optional.ofNullable(startingDate).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getLastLoginDate() {
		return Optional.ofNullable(lastLoginDate).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getLastModifiedDateTime() {
		return Optional.ofNullable(lastModifiedDateTime).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getTerminatedDate() {
		return Optional.ofNullable(terminatedDate).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getEmailSentDate() {
		return Optional.ofNullable(emailSentDate).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getEmailVerifiedOn() {
		return Optional.ofNullable(emailVerifiedOn).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getConfirmedOn() {
		return Optional.ofNullable(confirmedOn).map(Timestamp::toLocalDateTime).orElse(null);
	}
	
    public void update(String username) { 

    	this.lastModifiedDateTime = Timestamp.valueOf(LocalDateTime.now()); 
    	this.lastUser = username; 
	 
    }
}
