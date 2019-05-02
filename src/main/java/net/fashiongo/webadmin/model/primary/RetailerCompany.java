package net.fashiongo.webadmin.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * 
 * @author Reo
 *
 */
@Data
@Entity
@Table(name = "tblRetailer")
public class RetailerCompany {
	@Id
	@JsonProperty("RetailerID")
	@Column(name = "RetailerID")
	private Integer retailerID;
	
	@JsonProperty("CompanyName")
	@Column(name = "CompanyName")
	private String companyName;
	
	@JsonProperty("FirstName")
	@Column(name = "FirstName")
	private String firstName;
	
	@JsonProperty("LastName")
	@Column(name = "LastName")
	private String lastName;
	
	@JsonProperty("EMail")
	@Column(name = "EMail")
	private String eMail;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private String active;

	@JsonProperty("CurrentStatus")
	@Column(name = "CurrentStatus")
	private int currentStatus;

	@JsonIgnore
	@Column(name = "RetailerGUID")
	private String retailerGuid;

	@JsonIgnore
	@Column(name = "USERID")
	private String userID;

	@JsonIgnore
	@Column(name = "RetailerPermitNo")
	private String retailerPermitNo;

	@JsonIgnore
	@Column(name = "TerminatedNote")
	private String terminatedNote;

	@JsonIgnore
	@Column(name = "InHouseMemo")
	private String inHouseMemo;

	@JsonIgnore
	@Column(name = "WebSite")
	private String webSite;

	@JsonIgnore
	@Column(name = "LastUser")
	private String lastUser;

	@JsonIgnore
	@Column(name = "LastModifiedDateTime")
	private Date lastModifiedDateTime;

	@JsonIgnore
	@Column(name = "SellerPermitFileName")
	private String sellerPermitFileName;

	@JsonIgnore
	@Column(name = "InvoiceFileName1")
	private String invoiceFileName1;

	@JsonIgnore
	@Column(name = "InvoiceFileName2")
	private String invoiceFileName2;

	@JsonIgnore
	@Column(name = "AdditionalDocumentFileName")
	private String additionalDocumentFileName;

	@JsonIgnore
	@Column(name = "IsOperatorRead")
	private Boolean operatorRead;

}
