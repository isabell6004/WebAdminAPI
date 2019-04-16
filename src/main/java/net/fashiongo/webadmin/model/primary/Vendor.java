package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.dal.IPersistent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-04-15
 */
@Entity
@Table(name = "tblWholeSaler")
@Getter
@Setter
public class Vendor implements IPersistent, Serializable {
	private static final long serialVersionUID = -5598542682840377145L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WholeSalerID")
	private Integer wholeSalerId;
	
	@Column(name="SortNo")
	private Integer sortNo;
	
	@Column(name="StartingDate")
	private LocalDateTime startingDate;
	
	@Column(name="CompanyName")
	private String companyName;
	
	@Column(name="RegCompanyName")
	private String regCompanyName;
	
	@Column(name="DirName")
	private String dirName;
	
	@Column(name="CodeName")
	private String codeName;
	
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="BillStreetNo")
	private String billStreetNo;
	
	@Column(name="BillCity")
	private String billCity;
	
	@Column(name="BillSTATE")
	private String billState;
	
	@Column(name="BillZipcode")
	private String billZipcode;
	
	@Column(name="BillCountry")
	private String billCountry;
	
	@Column(name="BillPhone")
	private String billPhone;
	
	@Column(name="BillFax")
	private String billFax;
	
	@Column(name="StreetNo")
	private String streetNo;
	
	@Column(name="City")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="Zipcode")
	private String zipcode;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="Phone")
	private String phone;
	
	@Column(name="EMail")
	private String email;
	
	@Column(name="Fax")
	private String fax;
	
	@Column(name="Memo")
	private String memo;
	
	@Column(name="WebSite")
	private String webSite;
	
	@Column(name="UserID")
	private String userId;
	
	@Column(name="Pwd")
	private String pwd;
	
	@Column(name="WebSiteActive")
	private String webSiteActive;
	
	@Column(name="SLActive")
	private String SLActive;
	
	@Column(name="ReportActive")
	private String reportActive;
	
	@Column(name="CatalogActive")
	private String catalogActive;
	
	@Column(name="LambsActive")
	private String lambsActive;
	
	@Column(name="Group1")
	private Integer group1;
	
	@Column(name="Group2")
	private Integer group2;
	
	@Column(name="DM")
	private String dm;
	
	@Column(name="POSUse")
	private String POSUse;
	
	@Column(name="MainPage")
	private String mainPage;
	
	@Column(name="TitlePageMemo")
	private String titlePageMemo;
	
	@Column(name="WSAPolicy")
	private String WSAPolicy;
	
	@Column(name="WholeSalerTitlePage")
	private String wholeSalerTitlePage;
	
	@Column(name="OnSale")
	private String onSale;
	
	@Column(name="NewCustYN")
	private String newCustYN;
	
	@Column(name="GoodUpYN")
	private String goodUpYN;
	
	@Column(name="MinTQYN")
	private String minTQYN;
	
	@Column(name="MinTQ")
	private String minTQ;
	
	@Column(name="MinDollarYN")
	private String minDollarYN;
	
	@Column(name="MinDollar")
	private BigDecimal minDollar;
	
	@Column(name="MinECQYN")
	private String minECQYN;
	
	@Column(name="MinECQ")
	private Integer minECQ;
	
	@Column(name="QTYSeqYN")
	private String qtySeqYN;
	
	@Column(name="MinPolicyUseYN")
	private String minPolicyUseYN;
	
	@Column(name="OrderNotice")
	private String orderNotice;
	
	@Column(name="CompCharge")
	private String compCharge;
	
	@Column(name="Ratio")
	private BigDecimal ratio;
	
	@Column(name="AutoRActive")
	private String autoRActive;
	
	@Column(name="MinCharge")
	private String minCharge;
	
	@Column(name="MinAmount")
	private BigDecimal minAmount;
	
	@Column(name="IsMontyly")
	private String isMontyly;
	
	@Column(name="IsYearly")
	private String isYearly;
	
	@Column(name="YearlyAmount")
	private BigDecimal yearlyAmount;
	
	@Column(name="NoticeToAll")
	private String noticeToAll;
	
	@Column(name="AsKnownAs")
	private String asKnownAs;
	
	@Column(name="HOnote1")
	private String HOnote1;
	
	@Column(name="HOnote2")
	private String HOnote2;
	
	@Column(name="HOnote3")
	private String HOnote3;
	
	@Column(name="HOnote4")
	private String HOnote4;
	
	@Column(name="ActualOpenDate")
	private LocalDateTime actualOpenDate;
	
	@Column(name="BillingNote2")
	private String billingNote2;
	
	@Column(name="SpecialNote1")
	private String specialNote1;
	
	@Column(name="SpecialNote2")
	private String specialNote2;
	
	@Column(name="Adv1")
	private String adv1;
	
	@Column(name="Adv2")
	private String adv2;
	
	@Column(name="AdvertiseYN")
	private String advertiseYN;
	
	@Column(name="ActualOpen")
	private String actualOpen;
	
	@Column(name="OwnerCountry")
	private String ownerCountry;
	
	@Column(name="ContractExpireDate")
	private LocalDateTime contractExpireDate;
	
	@Column(name="BillReviewHoLee")
	private String billReviewHoLee;
	
	@Column(name="OpenDate")
	private LocalDateTime openDate;
	
	@Column(name="CompsolutionNo")
	private Integer compsolutionNo;
	
	@Column(name="RetailerYes")
	private Boolean retailerYes;
	
	@Column(name="RetailerBlockList")
	private String retailerBlockList;
	
	@Column(name="RetailerOpenList")
	private String retailerOpenList;
	
	@Column(name="DateTimeModified")
	private LocalDateTime dateTimeModified;
	
	@Column(name="PrePackYN")
	private String prePackYN;
	
	@Column(name="SalesItem")
	private Boolean salesItem;
	
	@Column(name="HotItems")
	private Boolean hotItems;
	
	@Column(name="PromotionalItems")
	private Boolean promotionalItems;
	
	@Column(name="BillStreetNo2")
	private String billStreetNo2;
	
	@Column(name="StreetNo2")
	private String streetNo2;
	
	@Column(name="ItemLocation")
	private String itemLocation;
	
	@Column(name="LastUser")
	private String lastUser;
	
	@Column(name="LastModifiedDateTime")
	private LocalDateTime lastModifiedDateTime;
	
	@Column(name="MinTQYNStyle")
	private String minTQYNStyle;
	
	@Column(name="MinTQStyle")
	private Integer minTQStyle;
	
	@Column(name="BillingNote1")
	private String billingNote1;
	
	@Column(name="BillingYN")
	private Boolean billingYN;
	
	@Column(name="InHouseMemo")
	private String inHouseMemo;
	
	@Column(name="SizeID")
	private Integer sizeId;
	
	@Column(name="PackID")
	private Integer packId;
	
	@Column(name="DistrictID")
	private Integer districtId;
	
	@Column(name="FGPlan")
	private Integer FGPlan;
	
	@Column(name="WebSiteLinkCount")
	private Integer webSiteLinkCount;
	
	@Column(name="HowKnownType")
	private Integer howKnownType;
	
	@Column(name="HowKnownOther")
	private String howKnownOther;
	
	@Column(name="DiscountYN")
	private Boolean discountYN;
	
	@Column(name="InsertedWhichApp")
	private Integer insertedWhichApp;
	
	@Column(name="AllowImage2Anony")
	private Boolean allowImage2Anony;
	
	@Column(name="MaxPictureQty")
	private Integer maxPictureQty;
	
	@Column(name="AllowImmediateShopping")
	private Boolean allowImmediateShopping;
	
	@Column(name="BusinessCategory")
	private String businessCategory;
	
	@Column(name="ImageServerID")
	private Integer imageServerId;
	
	@Column(name="ContactPerson")
	private String contactPerson;
	
	@Column(name="CompanyTypeID")
	private Integer companyTypeId;
	
	@Column(name="EstablishedYear")
	private Integer establishedYear;
	
	@Column(name="PictureMain")
	private String pictureMain;
	
	@Column(name="PictureLogo")
	private String pictureLogo;
	
	@Column(name="SizeChart")
	private String sizeChart;
	
	@Column(name="MadeIn")
	private String madeIn;
	
	@Column(name="ProductSortByLastUpdate")
	private Boolean productSortByLastUpdate;
	
	@Column(name="Active")
	private Boolean active;
	
	@Column(name="ShopActive")
	private Boolean shopActive;
	
	@Column(name="OrderActive")
	private Boolean orderActive;
	
	@Column(name="BillCountryID")
	private Integer billCountryId;
	
	@Column(name="CountryID")
	private Integer countryId;
	
	@Column(name="BillPhone2")
	private String billPhone2;
	
	@Column(name="Phone2")
	private String phone2;
	
	@Column(name="CreditCardAccessPassword")
	private String creditCardAccessPassword;
	
	@Column(name="AdminAccountCap")
	private Integer adminAccountCap;
	
	@Column(name="DefaultSizeID")
	private Integer defaultSizeId;
	
	@Column(name="DefaultPackID")
	private Integer defaultPackId;
	
	@Column(name="DefaultFabricDescriptionID")
	private Integer defaultFabricDescriptionID;
	
	@Column(name="ManageInventory")
	private Boolean manageInventory;
	
	@Column(name="AllowAccess2Y3")
	private Boolean allowAccess2Y3;
	
	@Column(name="SM_Facebook")
	private String smFacebook;
	
	@Column(name="SM_Twitter")
	private String smTwitter;
	
	@Column(name="SM_Youtube")
	private String smYoutube;
	
	@Column(name="Blog")
	private String blog;
	
	@Column(name="MinOrderByAmount")
	private String minOrderByAmount;
	
	@Column(name="MinOrderByQty")
	private String minOrderByQty;
	
	@Column(name="ExtraCharge")
	private BigDecimal extraCharge;
	
	@Column(name="ExtraChargeAmountFrom")
	private BigDecimal extraChargeAmountFrom;
	
	@Column(name="ExtraChargeAmountTo")
	private BigDecimal extraChargeAmountTo;
	
	@Column(name="IndustryType")
	private String industryType;
	
	@Column(name="OrderActiveLock")
	private Boolean orderActiveLock;
	
	@Column(name="ActiveOld")
	private String activeOld;
	
	@Column(name="ShopActiveOld")
	private String shopActiveOld;
	
	@Column(name="MinOrderAmount")
	private BigDecimal minOrderAmount;
	
	@Column(name="MinOrderAmountYN")
	private String minOrderAmountYN;
	
	@Column(name="MinTQAllYN")
	private String minTQAllYN;
	
	@Column(name="MinTQAll")
	private Integer minTQAll;
	
	@Column(name="ItemUploadCap")
	private Integer itemUploadCap;
	
	@Column(name="DefaultMadeInCountryID")
	private Integer defaultMadeInCountryId;
	
	@Column(name="DefaultLabelID")
	private Integer defaultLabelId;
	
	@Column(name="DefaultInventoryStatusID")
	private Integer defaultInventoryStatusId;
	
	@Column(name="PictureMain2")
	private String pictureMain2;
	
	@Column(name="ShowFeedback")
	private Boolean showFeedback;
	
	@Column(name="ConsolidationYN")
	private Boolean consolidationYN;
	
	@Column(name="DefaultVendorID")
	private Integer defaultVendorId;
	
	@Column(name="AdminWebServerID")
	private Integer adminWebServerId;
	
	@Column(name="WholeSalerGUID")
	private String wholeSalerGUID;
	
	@Column(name="FashionGoExclusive")
	private Boolean fashionGoExclusive;
	
	@Column(name="SizeChartImage")
	private String sizeChartImage;
	
	@Column(name="BlockPolicy")
	private Boolean blockPolicy;
	
	@Column(name="SM_Instagram")
	private String smInstagram;
	
	@Column(name="ChargedByCreditCard")
	private Boolean chargedByCreditCard;
	
	@Column(name="ViewEstimateShippingCharge")
	private Boolean viewEstimateShippingCharge;
	
	@Column(name="UseCreditCardPaymentService")
	private Boolean useCreditCardPaymentService;
	
	@Column(name="TransactionFeeRate1")
	private BigDecimal transactionFeeRate1;
	
	@Column(name="TransactionFeeRate2")
	private BigDecimal transactionFeeRate2;
	
	@Column(name="TransactionFeeRate1Intl")
	private BigDecimal transactionFeeRate1Intl;
	
	@Column(name="TransactionFeeRate2Intl")
	private BigDecimal transactionFeeRate2Intl;
	
	@Column(name="TransactionFeeFixed")
	private BigDecimal transactionFeeFixed;
	
	@Column(name="CommissionRate")
	private BigDecimal commissionRate;
	
	@Column(name="UseMergeOrder")
	private Boolean useMergeOrder;
	
	@Column(name="BillingEmail1")
	private String billingEmail1;
	
	@Column(name="BillingEmail2")
	private String billingEmail2;
	
	@Column(name="BillingEmail3")
	private String billingEmail3;
	
	@Column(name="IsADBlock")
	private Boolean isADBlock;
	
	@Column(name="ShowRoomStreetNo")
	private String showRoomStreetNo;
	
	@Column(name="ShowRoomCity")
	private String showRoomCity;
	
	@Column(name="ShowRoomSTATE")
	private String showRoomState;
	
	@Column(name="ShowRoomZipcode")
	private String showRoomZipcode;
	
	@Column(name="ShowRoomCountry")
	private String showRoomCountry;
	
	@Column(name="ShowRoomCountryID")
	private Integer showRoomCountryId;
	
	@Column(name="ShowRoomPhone")
	private String showRoomPhone;
	
	@Column(name="ShowRoomFax")
	private String showRoomFax;
	
	@Column(name="IsPaperLess")
	private Boolean isPaperLess;
	
	@Column(name="Vendor_Type")
	private Integer vendorType;
	
//	@Override
//	public String toString() {
//		try {
//			return new ObjectMapper().writeValueAsString(this);
//		} catch (JsonProcessingException e) {
//			return ReflectionToStringBuilder.toString(this);
//		}
//	}
}
