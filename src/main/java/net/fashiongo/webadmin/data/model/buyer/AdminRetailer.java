package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class AdminRetailer {

	@Column(name = "Active")
	@JsonProperty("Active")
	private String active;

	@Column(name = "AdditionalDocumentFileName")
	@JsonProperty("AdditionalDocumentFileName")
	private String additionalDocumentFileName;

	@Column(name = "BActedBy")
	@JsonProperty("BActedBy")
	private String BActedBy;

	@Column(name = "BActedOn")
	@JsonProperty("BActedOn")
	private LocalDateTime BActedOn;

	@Column(name = "BEntityID")
	@JsonProperty("BEntityID")
	private Integer BEntityID;

	@Column(name = "BEntityTypeID")
	@JsonProperty("BEntityTypeID")
	private Integer BEntityTypeID;

	@Column(name = "CompanyName")
	@JsonProperty("CompanyName")
	private String companyName;

	@Column(name = "ConfirmedBy")
	@JsonProperty("ConfirmedBy")
	private String confirmedBy;

	@Column(name = "ConfirmedOn")
	@JsonProperty("ConfirmedOn")
	private LocalDateTime confirmedOn;

	@Column(name = "CurrentStatus")
	@JsonProperty("CurrentStatus")
	private Integer currentStatus;

	@Column(name = "EmailType")
	@JsonProperty("EmailType")
	private String emailType;

	@Column(name = "FGActedBy")
	@JsonProperty("FGActedBy")
	private String fgActedBy;

	@Column(name = "FGActedOn")
	@JsonProperty("FGActedOn")
	private LocalDateTime fgActedOn;

	@Column(name = "FGEntityID")
	@JsonProperty("FGEntityID")
	private Integer fgEntityID;

	@Column(name = "FGEntityTypeID")
	@JsonProperty("FGEntityTypeID")
	private Integer fgEntityTypeID;

	@Column(name = "FirstName")
	@JsonProperty("FirstName")
	private String firstName;

	@Column(name = "HappenedAt")
	@JsonProperty("HappenedAt")
	private Integer happenedAt;

	@Column(name = "InvoiceFileName1")
	@JsonProperty("InvoiceFileName1")
	private String invoiceFileName1;

	@Column(name = "InvoiceFileName2")
	@JsonProperty("InvoiceFileName2")
	private String invoiceFileName2;

	@Column(name = "IsOperatorRead")
	@JsonProperty("IsOperatorRead")
	private boolean isOperatorRead;

	@Column(name = "LastModifiedDateTime")
	@JsonProperty("LastModifiedDateTime")
	private LocalDateTime lastModifiedDateTime;

	@Column(name = "LastName")
	@JsonProperty("LastName")
	private String lastName;

	@Column(name = "RetailerID")
	@JsonProperty("RetailerID")
	private Integer retailerID;

	@Column(name = "SellerPermitFileName")
	@JsonProperty("SellerPermitFileName")
	private String sellerPermitFileName;

	@Column(name = "SentBy")
	@JsonProperty("SentBy")
	private String s1entBy;

	@Column(name = "SentOn")
	@JsonProperty("SentOn")
	private LocalDateTime sentOn;

	@Column(name = "StartingDate")
	@JsonProperty("StartingDate")
	private LocalDateTime startingDate;

	@Column(name = "UserID")
	@JsonProperty("UserID")
	private String userID;

	@Column(name = "buyer_type")
	@JsonProperty("buyer_type")
	private Integer buyerType;

	@Column(name = "rowno")
	@JsonProperty("rowno")
	private Integer rowno;
}
