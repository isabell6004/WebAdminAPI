package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RetailerDetail {

	@JsonProperty("RetailerID")
	private int retailerId;

	@JsonProperty("FirstName")
	private String firstName;

	@JsonProperty("LastName")
	private String lastName;

	@JsonProperty("EMail")
	private String email;

	@JsonProperty("CurrentStatus")
	private int currentStatus;

	@JsonProperty("Active")
	private String active;

	@JsonProperty("USERID")
	private String userId;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("CurrentComment")
	private String currentComment;

	@JsonProperty("TerminatedNote")
	private String terminatedNote;

	@JsonProperty("Memo")
	private String memo;

	@JsonProperty("InHouseMemo")
	private String inHouseMemo;

	@JsonProperty("RetailerPermitNo")
	private String retailerPermitNo;

	@JsonProperty("LoginCount")
	private int loginCount;

	@JsonProperty("LastLoginDate")
	private LocalDateTime lastLoginDate;

	@JsonProperty("LastModifiedDateTime")
	private LocalDateTime lastModifiedDateTime;

	@JsonProperty("LastUser")
	private String lastUser;

	@JsonProperty("ConfirmedBy")
	private String confirmedBy;

	@JsonProperty("ConfirmedOn")
	private LocalDateTime confirmedOn;

	@JsonProperty("BillCountry")
	private String billCountry;

	@JsonProperty("BillCountryID")
	private Integer billCountryId;

	@JsonProperty("BillSTATE")
	private String billState;

	@JsonProperty("BillStreetNo")
	private String billStreetNo;

	@JsonProperty("BillStreetNo2")
	private String billStreetNo2;

	@JsonProperty("BillCity")
	private String billCity;

	@JsonProperty("BillZipcode")
	private String billZipCode;

	@JsonProperty("BillPhone")
	private String billPhone;

	@JsonProperty("BillFax")
	private String billFax;

	@JsonProperty("SellerPermitFileName")
	private String sellerPermitFileName;

	@JsonProperty("InvoiceFileName1")
	private String invoiceFileName1;

	@JsonProperty("InvoiceFileName2")
	private String invoiceFileName2;

	@JsonProperty("AdditionalDocumentFileName")
	private String additionalDocumentFileName;

	@JsonProperty("StartingDate")
	private LocalDateTime startingDate;

	@JsonProperty("WebSiteYN")
	private String webSiteYN;

	@JsonProperty("WebSite")
	private String webSite;

	@JsonProperty("MobilePhoneNo")
	private String mobilePhoneNo;
}
