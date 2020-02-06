package net.fashiongo.webadmin.model.pojo.buyer.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SetAdminRetailerDetailParameter {
	@JsonProperty("obj")
	private SetAdminRetailerDetailParameter.RetailerDetail retailerDetail;

	@JsonProperty("changeId")
	private boolean changeId;

	@JsonProperty("preUserId")
	private String preUserId;

	@JsonProperty("inactiveReason")
	private int inactiveReason;

	@JsonProperty("txtInactiveReason")
	private String txtInactiveReason;

	@JsonProperty("blockReason")
	private int blockReason;

	@JsonProperty("txtBlockReason")
	private String txtBlockReason;

	@JsonProperty("fraudNoticeList")
	private List<SetAdminRetailerDetailParameter.FraudNotice> fraudNoticeList;

	@JsonProperty("checkedFraudNotice")
	private boolean checkedFraudNotice;

	@Data
	public static class RetailerDetail {
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
		private Date lastLoginDate;

		@JsonProperty("LastModifiedDateTime")
		private Date lastModifiedDateTime;

		@JsonProperty("LastUser")
		private String lastUser;

		@JsonProperty("ConfirmedBy")
		private String confirmedBy;

		@JsonProperty("ConfirmedOn")
		private Date confirmedOn;

		@JsonProperty("BillCountry")
		private String billCountry;

		@JsonProperty("BillCountryID")
		private int billCountryId;

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
		private Date startingDate;

		@JsonProperty("WebSiteYN")
		private String webSiteYN;

		@JsonProperty("WebSite")
		private String webSite;

		@JsonProperty("MobilePhoneNo")
		private String mobilePhoneNo;

		@JsonProperty("BuyerClass")
		private int buyerClass;		

		@JsonProperty("AmUserID")
		private int amUserID;				
		
	}

	@Data
	public static class FraudNotice {
		@JsonProperty("FraudNoticeID")
		private int fraudNoticeId;

		@JsonProperty("WholeSalerCompanyName")
		private String wholesalerCompanyName;

		@JsonProperty("FraudDetail")
		private String fraudDetail;

		@JsonProperty("CommentByWholeSaler")
		private String commentByWholeSaler;

		@JsonProperty("CommentByFG")
		private String commentByFG;

		@JsonProperty("CreatedOn")
		private Date createdOn;

		@JsonProperty("Active")
		private boolean active;
	}
}
