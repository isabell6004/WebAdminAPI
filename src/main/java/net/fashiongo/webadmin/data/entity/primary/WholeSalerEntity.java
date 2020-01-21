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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblWholeSaler")
@Getter
@Setter
public class WholeSalerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "WholeSalerID")
    private Integer wholeSalerID;

    @Column(name = "SortNo")
    private Integer sortNo;

    @Column(name = "StartingDate")
    private Timestamp startingDate;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "RegCompanyName")
    private String regCompanyName;

    @Column(name = "DirName")
    private String dirName;

    @Column(name = "CodeName")
    private String codeName;

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
    private String billState;

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

    @Column(name = "Memo")
    private String memo;

    @Column(name = "WebSite")
    private String webSite;

    @Column(name = "UserID")
    private String userId;

    @Column(name = "Pwd")
    private String pwd;

    @Column(name = "WebSiteActive")
    private String webSiteActive;

    @Column(name = "SLActive")
    private String slActive;

    @Column(name = "ReportActive")
    private String reportActive;

    @Column(name = "CatalogActive")
    private String catalogActive;

    @Column(name = "LambsActive")
    private String lambsActive;

    @Column(name = "Group1")
    private Integer group1;

    @Column(name = "Group2")
    private Integer group2;

    @Column(name = "DM")
    private String dm;

    @Column(name = "POSUse")
    private String posUse;

    @Column(name = "MainPage")
    private String mainPage;

    @Column(name = "TitlePageMemo")
    private String titlePageMemo;

    @Column(name = "WSAPolicy")
    private String wsaPolicy;

    @Column(name = "WholeSalerTitlePage")
    private String wholeSalerTitlePage;

    @Column(name = "OnSale")
    private String onSale;

    @Column(name = "NewCustYN")
    private String newCustYN;

    @Column(name = "GoodUpYN")
    private String goodUpYN;

    @Column(name = "MinTQYN")
    private String minTQYN;

    @Column(name = "MinTQ")
    private Integer minTQ;

    @Column(name = "MinDollarYN")
    private String minDollarYN;

    @Column(name = "MinDollar")
    private Float minDollar;

    @Column(name = "MinECQYN")
    private String minECQYN;

    @Column(name = "MinECQ")
    private Integer minECQ;

    @Column(name = "QTYSeqYN")
    private String qtySeqYN;

    @Column(name = "MinPolicyUseYN")
    private String minPolicyUseYN;

    @Column(name = "OrderNotice")
    private String orderNotice;

    @Column(name = "CompCharge")
    private String compCharge;

    @Column(name = "Ratio")
    private Float ratio;

    @Column(name = "AutoRActive")
    private String autoRActive;

    @Column(name = "MinCharge")
    private String minCharge;

    @Column(name = "MinAmount")
    private Float minAmount;

    @Column(name = "IsMontyly")
    private String isMonthly;

    @Column(name = "IsYearly")
    private String isYearly;

    @Column(name = "YearlyAmount")
    private BigDecimal yearlyAmount;

    @Column(name = "NoticeToAll")
    private String noticeToAll;

    @Column(name = "AsKnownAs")
    private String asKnownAs;

    @Column(name = "HOnote1")
    private String honote1;

    @Column(name = "HOnote2")
    private String honote2;

    @Column(name = "HOnote3")
    private String honote3;

    @Column(name = "HOnote4")
    private String honote4;

    @Column(name = "ActualOpenDate")
    private Timestamp actualOpenDate;

    @Column(name = "BillingNote2")
    private String billingNote2;

    @Column(name = "SpecialNote1")
    private String specialNote1;

    @Column(name = "SpecialNote2")
    private String specialNote2;

    @Column(name = "Adv1")
    private String adv1;

    @Column(name = "Adv2")
    private String adv2;

    @Column(name = "AdvertiseYN")
    private String advertiseYN;

    @Column(name = "ActualOpen")
    private String actualOpen;

    @Column(name = "OwnerCountry")
    private String ownerCountry;

    @Column(name = "ContractExpireDate")
    private Timestamp contractExpireDate;

    @Column(name = "BillReviewHoLee")
    private String billReviewHoLee;

    @Column(name = "OpenDate")
    private LocalDateTime openDate;

    @Column(name = "CompsolutionNo")
    private Integer compsolutionNo;

    @Column(name = "RetailerYes")
    private Boolean retailerYes;

    @Column(name = "RetailerBlockList")
    private String retailerBlockList;

    @Column(name = "RetailerOpenList")
    private String retailerOpenList;

    @Column(name = "DateTimeModified")
    private LocalDateTime dateTimeModified;

    @Column(name = "PrePackYN")
    private String prePackYN;

    @Column(name = "SalesItem")
    private Boolean salesItem;

    @Column(name = "HotItems")
    private Boolean hotItems;

    @Column(name = "PromotionalItems")
    private Boolean promotionalItems;

    @Column(name = "BillStreetNo2")
    private String billStreetNo2;

    @Column(name = "StreetNo2")
    private String streetNo2;

    @Column(name = "ItemLocation")
    private String itemLocation;

    @Column(name = "LastUser")
    private String lastUser;

    @Column(name = "LastModifiedDateTime")
    private Timestamp lastModifiedDateTime;

    @Column(name = "MinTQYNStyle")
    private String minTQYNStyle;

    @Column(name = "MinTQStyle")
    private Integer minTQStyle;

    @Column(name = "BillingNote1")
    private String billingNote1;

    @Column(name = "BillingYN")
    private Boolean billingYN;

    @Column(name = "InHouseMemo")
    private String inHouseMemo;

    @Column(name = "SizeID")
    private Integer sizeID;

    @Column(name = "PackID")
    private Integer packID;

    @Column(name = "DistrictID")
    private Integer districtID;

    @Column(name = "FGPlan")
    private int fgPlan;

    @Column(name = "WebSiteLinkCount")
    private Integer webSiteLinkCount;

    @Column(name = "HowKnownType")
    private Integer howKnownType;

    @Column(name = "HowKnownOther")
    private String howKnownOther;

    @Column(name = "DisCountYN")
    private Boolean discountYN = false;

    @Column(name = "InsertedWhichApp")
    private Integer insertedWhichApp = 1;

    @Column(name = "AllowImage2Anony")
    private Boolean allowImage2Anony = true;

    @Column(name = "MaxPictureQty")
    private Integer maxPictureQty = 0;

    @Column(name = "AllowImmediateShopping")
    private Boolean allowImmediateShopping = true;

    @Column(name = "BusinessCategory")
    private String businessCategory;

    @Column(name = "ImageServerID")
    private Integer imageServerID = 1;

    @Column(name = "ContactPerson")
    private String contactPerson;

    @Column(name = "CompanyTypeID")
    private Integer companyTypeID = 1;

    @Column(name = "EstablishedYear")
    private Integer establishedYear;

    @Column(name = "PictureMain")
    private String pictureMain;

    @Column(name = "PictureLogo")
    private String pictureLogo;

    @Column(name = "SizeChart")
    private String sizeChart;

    @Column(name = "MadeIn")
    private String madeIn;

    @Column(name = "ProductSortByLastUpdate")
    private Boolean productSortByLastUpdate = false;

    @Column(name = "Active")
    private Boolean active = false;

    @Column(name = "ShopActive")
    private Boolean shopActive;

    @Column(name = "OrderActive")
    private Boolean orderActive;

    @Column(name = "BillCountryID")
    private Integer billCountryID;

    @Column(name = "CountryID")
    private Integer countryID;

    @Column(name = "BillPhone2")
    private String billPhone2;

    @Column(name = "Phone2")
    private String phone2;

    @Column(name = "CreditCardAccessPassword")
    private String creditCardAccessPassword;

    @Column(name = "AdminAccountCap")
    private Integer adminAccountCap;

    @Column(name = "DefaultSizeID")
    private Integer defaultSizeID;

    @Column(name = "DefaultPackID")
    private Integer defaultPackID;

    @Column(name = "DefaultFabricDescriptionID")
    private Integer defaultFabricDescriptionID;

    @Column(name = "ManageInventory")
    private Boolean manageInventory = false;

    @Column(name = "AllowAccess2Y3")
    private Boolean allowAccess2Y3 = true;

    @Column(name = "SM_Facebook")
    private String sm_Facebook;

    @Column(name = "SM_Twitter")
    private String sm_Twitter;

    @Column(name = "SM_Youtube")
    private String sm_Youtube;

    @Column(name = "Blog")
    private String blog;

    @Column(name = "MinOrderByAmount")
    private BigDecimal minOrderByAmount;

    @Column(name = "MinOrderByQty")
    private Integer minOrderByQty;

    @Column(name = "ExtraCharge")
    private BigDecimal extraCharge;

    @Column(name = "ExtraChargeAmountFrom")
    private BigDecimal extraChargeAmountFrom;

    @Column(name = "ExtraChargeAmountTo")
    private BigDecimal extraChargeAmountTo;

    @Column(name = "IndustryType")
    private String industryType;

    @Column(name = "OrderActiveLock")
    private Boolean orderActiveLock = false;

    @Column(name = "ActiveOld")
    private String activeOld;

    @Column(name = "ShopActiveOld")
    private String shopActiveOld;

    @Column(name = "MinOrderAmount")
    private Float minOrderAmount;

    @Column(name = "MinOrderAmountYN")
    private String minOrderAmountYN;

    @Column(name = "MinTQAllYN")
    private String minTQAllYN;

    @Column(name = "MinTQAll")
    private Integer minTQAll;

    @Column(name = "ItemUploadCap")
    private Integer itemUploadCap;

    @Column(name = "DefaultMadeInCountryID")
    private Integer defaultMadeInCountryID;

    @Column(name = "DefaultLabelID")
    private Integer defaultLabelID;

    @Column(name = "DefaultInventoryStatusID")
    private Integer defaultInventoryStatusID;

    @Column(name = "PictureMain2")
    private String pictureMain2;

    @Column(name = "ShowFeedback")
    private Boolean showFeedback = true;

    @Column(name = "ConsolidationYN")
    private Boolean consolidationYN = true;

    @Column(name = "DefaultVendorID")
    private Integer defaultVendorID = 1;

    @Column(name = "AdminWebServerID")
    private Integer adminWebServerID = 0;

    @Column(name = "WholeSalerGUID")
    private String wholeSalerGUID;

    @Column(name = "FashionGoExclusive")
    private Boolean fashionGoExclusive;

    @Column(name = "SizeChartImage")
    private String sizeChartImage;

    @Column(name = "BlockPolicy")
    private Boolean blockPolicy = false;

    @Column(name = "SM_Instagram")
    private String sm_Instagram;

    @Column(name = "ChargedByCreditCard")
    private Boolean chargedByCreditCard = false;

    @Column(name = "ViewEstimateShippingCharge")
    private Boolean viewEstimateShippingCharge = true;

    @Column(name = "UseCreditCardPaymentService")
    private Boolean useCreditCardPaymentService = false;

    @Column(name = "TransactionFeeRate1")
    private Double  transactionFeeRate1 = 0.029;

    @Column(name = "TransactionFeeRate2")
    private Double transactionFeeRate2 = 0.029;

    @Column(name = "TransactionFeeRate1Intl")
    private Double transactionFeeRate1Intl = 0.0415;

    @Column(name = "TransactionFeeRate2Intl")
    private Double transactionFeeRate2Intl = 0.048;

    @Column(name = "TransactionFeeFixed")
    private Double transactionFeeFixed = 0.30;

    @Column(name = "CommissionRate")
    private Double commissionRate;

    @Column(name = "UseMergeOrder")
    private Boolean useMergeOrder = true;

    @Column(name = "BillingEmail1")
    private String billingEmail1;

    @Column(name = "BillingEmail2")
    private String billingEmail2;

    @Column(name = "BillingEmail3")
    private String billingEmail3;

    @Column(name = "IsADBlock")
    private Boolean isADBlock;

    @Column(name = "ShowRoomStreetNo")
    private String showRoomStreetNo;

    @Column(name = "ShowRoomCity")
    private String showRoomCity;

    @Column(name = "ShowRoomSTATE")
    private String showRoomState;

    @Column(name = "ShowRoomZipcode")
    private String showRoomZipcode;

    @Column(name = "ShowRoomCountry")
    private String showRoomCountry;

    @Column(name = "ShowRoomCountryID")
    private Integer showRoomCountryID;

    @Column(name = "ShowRoomPhone")
    private String showRoomPhone;

    @Column(name = "ShowRoomFax")
    private String showRoomFax;

    @Column(name = "IsPaperLess")
    private Boolean isPaperLess;

    @Column(name = "Vendor_Type")
    private Integer vendorType = 1;

    @Column(name = "PremiumEmail")
    private String premiumEmail;

    @Column(name = "PrivacyPolicy_Url")
    private String privacyPolicyUrl;
    
    @Column(name = "Source_Type")
    private Integer sourceType = 1;
}
