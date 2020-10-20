package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.fashiongo.webadmin.data.entity.primary.VendorAddressEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorContractHistoryEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorEmailEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorIndustryEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorSettingEntity;
import net.fashiongo.webadmin.utility.LocalDateTimeDeserializer;
import net.fashiongo.webadmin.utility.LocalDateTimeSerializer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VendorDetailInfo {
	@JsonProperty(value = "WholeSalerID")
	private Integer wholeSalerID;

	@JsonProperty(value = "CompanyName")
	private String companyName;

	@JsonProperty(value = "RegCompanyName")
	private String regCompanyName;

	@JsonProperty(value = "DirName")
	private String dirName;

	@JsonProperty(value = "CodeName")
	private String codeName;

	@JsonProperty(value = "FirstName")
	private String firstName;

	@JsonProperty(value = "LastName")
	private String lastName;

	@JsonProperty(value = "Description")
	private String description;

	@JsonProperty(value = "BillStreetNo")
	private String billStreetNo;

	@JsonProperty(value = "BillCity")
	private String billCity;

	@JsonProperty(value = "BillSTATE")
	private String billState;

	@JsonProperty(value = "BillZipcode")
	private String billZipcode;

	@JsonProperty(value = "BillCountry")
	private String billCountry;

	@JsonProperty(value = "BillPhone")
	private String billPhone;

	@JsonProperty(value = "BillFax")
	private String billFax;

	@JsonProperty(value = "StreetNo")
	private String streetNo;

	@JsonProperty(value = "City")
	private String city;

	@JsonProperty(value = "STATE")
	private String state;

	@JsonProperty(value = "Zipcode")
	private String zipcode;

	@JsonProperty(value = "Country")
	private String country;

	@JsonProperty(value = "Phone")
	private String phone;

	@JsonProperty(value = "EMail")
	private String email;

	@JsonProperty(value = "Fax")
	private String fax;

	@JsonProperty(value = "Memo")
	private String memo;

	@JsonProperty(value = "WebSite")
	private String webSite;

	@JsonProperty(value = "UserID")
	private String userId;

	@JsonProperty(value = "NewCustYN")
	private String newCustYN;

	@JsonProperty(value = "OrderNotice")
	private String orderNotice;

	@JsonProperty(value = "NoticeToAll")
	private String noticeToAll;

	@JsonProperty(value = "ActualOpenDate")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime actualOpenDate;

	@JsonProperty(value = "ContractExpireDate")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime contractExpireDate;

	@JsonProperty(value = "BillStreetNo2")
	private String billStreetNo2;

	@JsonProperty(value = "StreetNo2")
	private String streetNo2;

	@JsonProperty(value = "InHouseMemo")
	private String inHouseMemo;

	@JsonProperty(value = "BusinessCategory")
	private String businessCategory;

	@JsonProperty(value = "ImageServerID")
	private Integer imageServerID;

	@JsonProperty(value = "CompanyTypeID")
	private Integer companyTypeID;

	@JsonProperty(value = "EstablishedYear")
	private Integer establishedYear;

	@JsonProperty(value = "Active")
	private Boolean active;

	@JsonProperty(value = "ShopActive")
	private Boolean shopActive;

	@JsonProperty(value = "OrderActive")
	private Boolean orderActive;

	@JsonProperty(value = "IndustryType")
	private String industryType;

	@JsonProperty(value = "ConsolidationYN")
	private Boolean consolidationYN;

	@JsonProperty(value = "AdminWebServerID")
	private Integer adminWebServerID;

	@JsonProperty(value = "WholeSalerGUID")
	private String wholeSalerGUID;

	@JsonProperty(value = "IsLockedOut")
	private Boolean isLockedOut;

	@JsonProperty(value = "LastLockoutDate")
	private Timestamp lastLockoutDate;

	@JsonProperty(value = "IsLockedOut2")
	private Long isLockedOut2;

	@JsonProperty(value = "UseCreditCardPaymentService")
	private Boolean useCreditCardPaymentService;

	@JsonProperty(value = "TransactionFeeRate1")
	private Double transactionFeeRate1;

	@JsonProperty(value = "TransactionFeeRate2")
	private Double transactionFeeRate2;

	@JsonProperty(value = "TransactionFeeRate1Intl")
	private Double transactionFeeRate1Intl;

	@JsonProperty(value = "TransactionFeeRate2Intl")
	private Double transactionFeeRate2Intl;

	@JsonProperty(value = "TransactionFeeFixed")
	private Double transactionFeeFixed;

	@JsonProperty(value = "CommissionRate")
	private Double commissionRate;

	@JsonProperty(value = "BillingEmail1")
	private String billingEmail1;

	@JsonProperty(value = "BillingEmail2")
	private String billingEmail2;

	@JsonProperty(value = "BillingEmail3")
	private String billingEmail3;

	@JsonProperty(value = "ShowRoomStreetNo")
	private String showRoomStreetNo;

	@JsonProperty(value = "ShowRoomCity")
	private String showRoomCity;

	@JsonProperty(value = "ShowRoomCountry")
	private String showRoomCountry;

	@JsonProperty(value = "ShowRoomSTATE")
	private String showRoomState;

	@JsonProperty(value = "ShowRoomZipcode")
	private String showRoomZipcode;

	@JsonProperty(value = "ShowRoomPhone")
	private String showRoomPhone;

	@JsonProperty(value = "ShowRoomFax")
	private String showRoomFax;

	@JsonProperty(value = "elambsuser")
	private Long elambsuser;

	@JsonProperty(value = "Source_Type")
	private Integer sourceType;

	@JsonProperty(value = "Vendor_seo_Id")
	private Integer vendorseoId;

	@JsonProperty(value = "Meta_Keyword")
	private String metaKeyword;

	@JsonProperty(value = "Meta_Description")
	private String metaDescription;

	public VendorDetailInfo(VendorBasicInfo vendorBasicInfo) {
		/* Vendor */
		VendorEntity vendor = vendorBasicInfo.getVendor();

		this.wholeSalerID = vendor.getVendor_id().intValue();
		this.companyName = vendor.getName();
		this.regCompanyName = vendor.getDbaName();
		this.codeName = vendor.getCodename();
		this.dirName = vendor.getDirname();
		this.firstName = vendor.getFirstName();
		this.lastName = vendor.getLastName();
		this.description = vendor.getDescription();
		this.webSite = vendor.getWebsite();
		this.userId = vendor.getCreatedBy();
		this.businessCategory = vendor.getBusinessCategoryInfo();
		this.companyTypeID = vendor.getTypeCode();
		this.establishedYear = vendor.getEstablishedYear();
		this.wholeSalerGUID = vendor.getGuid();
		this.sourceType = vendor.getSourceCode();

		/* Vendor Address */
		List<VendorAddressEntity> vendorAddress = new ArrayList<>(vendor.getVendorAddresses());
		VendorAddressEntity showroom = vendorAddress.stream().filter(a -> a.getTypeCode() == 2).findAny().orElse(null);
		VendorAddressEntity billing = vendorAddress.stream().filter(a -> a.getTypeCode() == 1).findAny().orElse(null);
		VendorAddressEntity warehouse = vendorAddress.stream().filter(a -> a.getTypeCode() == 3).findAny().orElse(null);

		if (billing != null) {
			this.billStreetNo = billing.getStreetNo1();
			this.billCity = billing.getCity();
			this.billState = billing.getState();
			this.billZipcode = billing.getZipCode();
			this.billCountry = billing.getCountryName();
			this.billPhone = billing.getPhone();
			this.billFax = billing.getFax();
			this.billStreetNo2 = billing.getStreetNo2();
		} else {
			this.billStreetNo = null;
			this.billCity = null;
			this.billState = null;
			this.billZipcode = null;
			this.billCountry = null;
			this.billPhone = null;
			this.billFax = null;
			this.billStreetNo2 = null;
		}

		if (warehouse != null) {
			this.streetNo = warehouse.getStreetNo1();
			this.city = warehouse.getCity();
			this.state = warehouse.getState();
			this.zipcode = warehouse.getZipCode();
			this.country = warehouse.getCountryName();
			this.phone = warehouse.getPhone();
			this.email = vendor.getEmail();
			this.fax = warehouse.getFax();
			this.streetNo2 = warehouse.getStreetNo2();
		} else {
			this.streetNo = null;
			this.city = null;
			this.state = null;
			this.zipcode = null;
			this.country = null;
			this.phone = null;
			this.email = null;
			this.fax = null;
			this.streetNo2 = null;
		}

		if (showroom != null) {
			this.showRoomStreetNo = showroom.getStreetNo1();
			this.showRoomCity = showroom.getCity();
			this.showRoomCountry = showroom.getCountryName();
			this.showRoomState = showroom.getState();
			this.showRoomZipcode = showroom.getZipCode();
			this.showRoomPhone = showroom.getPhone();
			this.showRoomFax = showroom.getFax();
		} else {
			this.showRoomStreetNo = null;
			this.showRoomCity = null;
			this.showRoomCountry = null;
			this.showRoomState = null;
			this.showRoomZipcode = null;
			this.showRoomPhone = null;
			this.showRoomFax = null;
		}

		/* Vendor Emails */
		if (vendor.getVendorEmail() != null) {
			List<VendorEmailEntity> vendorEmails = new ArrayList<>(vendor.getVendorEmail());

			this.billingEmail1 = null;
			vendorEmails.stream().filter(email -> email.getTypeCode() == 3).findAny().ifPresent(email1 -> this.billingEmail1 = email1.getEmail());
			this.billingEmail2 = null;
			vendorEmails.stream().filter(email -> email.getTypeCode() == 4).findAny().ifPresent(email2 -> this.billingEmail2 = email2.getEmail());
			this.billingEmail3 = null;
			vendorEmails.stream().filter(email -> email.getTypeCode() == 5).findAny().ifPresent(email3 -> this.billingEmail3 = email3.getEmail());
		} else {
			this.billingEmail1 = null;
			this.billingEmail2 = null;
			this.billingEmail3 = null;
		}

		/* Vendor Setting */
		VendorSettingEntity setting = vendor.getVendorSetting().stream().findAny().orElse(null);

		if (setting != null) {
			this.memo = setting.getMemo();
			this.newCustYN = setting.getIsNew() ? "Y" : "N";
			this.orderNotice = setting.getOrderNotice();
			this.noticeToAll = setting.getBuyerNotice();
			this.actualOpenDate = setting.getOpenDate();
			this.contractExpireDate = setting.getClosedDate();
			this.inHouseMemo = setting.getInHouseMemo();
			this.active = setting.getStatusCode() == 1 || setting.getStatusCode() == 2 || setting.getStatusCode() == 3;
			this.shopActive = setting.getStatusCode() == 2 || setting.getStatusCode() == 3;
			this.orderActive = setting.getStatusCode() == 3;
			this.consolidationYN = setting.getIsConsolidation();
			this.useCreditCardPaymentService = setting.getIsUsePgService();
			this.transactionFeeRate1 = setting.getTransactionFeeRate1().doubleValue();
			this.transactionFeeRate2 = setting.getTransactionFeeRate2().doubleValue();
			this.transactionFeeRate1Intl = setting.getTransactionFeeRate1Intl() == null ? null : setting.getTransactionFeeRate1Intl().doubleValue();
			this.transactionFeeRate2Intl = setting.getTransactionFeeRate2Intl() == null ? null : setting.getTransactionFeeRate2Intl().doubleValue();
			this.transactionFeeFixed = setting.getTransactionFeeFixed() == null ? null : setting.getTransactionFeeFixed().doubleValue();
		} else {
			this.memo = null;
			this.newCustYN = null;
			this.orderNotice = null;
			this.noticeToAll = null;
			this.actualOpenDate = null;
			this.contractExpireDate = null;
			this.inHouseMemo = null;
			this.active = null;
			this.shopActive = null;
			this.orderActive = null;
			this.consolidationYN = null;
			this.useCreditCardPaymentService = null;
			this.transactionFeeRate1 = null;
			this.transactionFeeRate2 = null;
			this.transactionFeeRate1Intl = null;
			this.transactionFeeRate2Intl = null;
			this.transactionFeeFixed = null;
		}


		/* Vendor Industry */
		if (vendor.getVendorIndustry() != null) {
			List<VendorIndustryEntity> industries = new ArrayList<>(vendor.getVendorIndustry());
			StringBuilder industryTypeString = new StringBuilder();
			for (VendorIndustryEntity industry : industries) {
				industryTypeString.append(getIndustryType(industry.getTypeCode())).append(',');
			}
			String industryString = industryTypeString.toString();
			if (industryString.length() > 0 && industryString.substring(industryString.length() - 1).equals(',')) {
				industryString = industryString.substring(0, industryString.length() - 1);
			}
			this.industryType = industryString;
		} else {
			this.industryType = null;
		}

		/* Vendor Contract */
		if (vendor.getVendorContractHistory() != null) {
			this.commissionRate = null;
			vendor.getVendorContractHistory().stream().findAny().ifPresent(contractHistory -> this.commissionRate = contractHistory.getCommissionRate().doubleValue());
		} else {
			this.commissionRate = null;
		}

		/* Vendor Basic Info */
		this.imageServerID = 7;
		this.adminWebServerID = 0;

		this.isLockedOut = vendorBasicInfo.getIsLockedOut();
		this.lastLockoutDate = vendorBasicInfo.getLastLockoutDate();
		this.isLockedOut2 = vendorBasicInfo.getIsLockedOut2();
		this.elambsuser = vendorBasicInfo.getElambsuser();
	}

	private String getIndustryType(Integer typeCode) {
		switch (typeCode) {
			case 1:
				return "Women's Clothing";
			case 2:
				return "Man's Clothing";
			case 3:
				return "Children's Clothing";
			case 4:
				return "Accessories";
			case 5:
				return "Shoes";
			case 6:
				return "Others";
			case 7:
				return "Handbag";
			case 8:
				return "Beauty";
		}
		return "";
	}
}
