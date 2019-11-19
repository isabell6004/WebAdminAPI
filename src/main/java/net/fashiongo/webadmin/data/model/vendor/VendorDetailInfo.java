package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class VendorDetailInfo {
    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "SortNo")
    private Integer sortNo;

    @JsonProperty(value = "StartingDate")
    private LocalDateTime startingDate;

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

    @JsonProperty(value = "Pwd")
    private String pwd;

    @JsonProperty(value = "WebSiteActive")
    private String webSiteActive;

    @JsonProperty(value = "SLActive")
    private String slActive;

    @JsonProperty(value = "ReportActive")
    private String reportActive;

    @JsonProperty(value = "CatalogActive")
    private String catalogActive;

    @JsonProperty(value = "LambsActive")
    private String lambsActive;

    @JsonProperty(value = "Group1")
    private Integer group1;

    @JsonProperty(value = "Group2")
    private Integer group2;

    @JsonProperty(value = "DM")
    private String dm;

    @JsonProperty(value = "POSUse")
    private String posUse;

    @JsonProperty(value = "MainPage")
    private String mainPage;

    @JsonProperty(value = "TitlePageMemo")
    private String titlePageMemo;

    @JsonProperty(value = "WSAPolicy")
    private String wsaPolicy;

    @JsonProperty(value = "WholeSalerTitlePage")
    private String wholeSalerTitlePage;

    @JsonProperty(value = "OnSale")
    private String onSale;

    @JsonProperty(value = "NewCustYN")
    private String newCustYN;

    @JsonProperty(value = "GoodUpYN")
    private String goodUpYN;

    @JsonProperty(value = "MinTQYN")
    private String minTQYN;

    @JsonProperty(value = "MinTQ")
    private Integer minTQ;

    @JsonProperty(value = "MinDollarYN")
    private String minDollarYN;

    @JsonProperty(value = "MinDollar")
    private Float minDollar;

    @JsonProperty(value = "MinECQYN")
    private String minECQYN;

    @JsonProperty(value = "MinECQ")
    private Integer minECQ;

    @JsonProperty(value = "QTYSeqYN")
    private String qtySeqYN;

    @JsonProperty(value = "MinPolicyUseYN")
    private String minPolicyUseYN;

    @JsonProperty(value = "OrderNotice")
    private String orderNotice;

    @JsonProperty(value = "CompCharge")
    private String compCharge;

    @JsonProperty(value = "Ratio")
    private Float ratio;

    @JsonProperty(value = "AutoRActive")
    private String autoRActive;

    @JsonProperty(value = "MinCharge")
    private String minCharge;

    @JsonProperty(value = "MinAmount")
    private Float minAmount;

    @JsonProperty(value = "IsMontyly")
    private String isMonthly;

    @JsonProperty(value = "IsYearly")
    private String isYearly;

    @JsonProperty(value = "YearlyAmount")
    private BigDecimal yearlyAmount;

    @JsonProperty(value = "NoticeToAll")
    private String noticeToAll;

    @JsonProperty(value = "AsKnownAs")
    private String asKnownAs;

    @JsonProperty(value = "HOnote1")
    private String honote1;

    @JsonProperty(value = "HOnote2")
    private String honote2;

    @JsonProperty(value = "HOnote3")
    private String honote3;

    @JsonProperty(value = "HOnote4")
    private String honote4;

    @JsonProperty(value = "ActualOpenDate")
    private LocalDateTime actualOpenDate;

    @JsonProperty(value = "BillingNote2")
    private String billingNote2;

    @JsonProperty(value = "SpecialNote1")
    private String specialNote1;

    @JsonProperty(value = "SpecialNote2")
    private String specialNote2;

    @JsonProperty(value = "Adv1")
    private String adv1;

    @JsonProperty(value = "Adv2")
    private String adv2;

    @JsonProperty(value = "AdvertiseYN")
    private String advertiseYN;

    @JsonProperty(value = "ActualOpen")
    private String actualOpen;

    @JsonProperty(value = "OwnerCountry")
    private String ownerCountry;

    @JsonProperty(value = "ContractExpireDate")
    private LocalDateTime contractExpireDate;

    @JsonProperty(value = "BillReviewHoLee")
    private String billReviewHoLee;

    @JsonProperty(value = "OpenDate")
    private LocalDateTime openDate;

    @JsonProperty(value = "CompsolutionNo")
    private Integer compsolutionNo;

    @JsonProperty(value = "RetailerYes")
    private Boolean retailerYes;

    @JsonProperty(value = "RetailerBlockList")
    private String retailerBlockList;

    @JsonProperty(value = "RetailerOpenList")
    private String retailerOpenList;

    @JsonProperty(value = "DateTimeModified")
    private LocalDateTime dateTimeModified;

    @JsonProperty(value = "PrePackYN")
    private String prePackYN;

    @JsonProperty(value = "SalesItem")
    private Boolean salesItem;

    @JsonProperty(value = "HotItems")
    private Boolean hotItems;

    @JsonProperty(value = "PromotionalItems")
    private Boolean promotionalItems;

    @JsonProperty(value = "BillStreetNo2")
    private String billStreetNo2;

    @JsonProperty(value = "StreetNo2")
    private String streetNo2;

    @JsonProperty(value = "ItemLocation")
    private String itemLocation;

    @JsonProperty(value = "LastUser")
    private String lastUser;

    @JsonProperty(value = "LastModifiedDateTime")
    private LocalDateTime lastModifiedDateTime;

    @JsonProperty(value = "MinTQYNStyle")
    private String minTQYNStyle;

    @JsonProperty(value = "MinTQStyle")
    private Integer minTQStyle;

    @JsonProperty(value = "BillingNote1")
    private String billingNote1;

    @JsonProperty(value = "BillingYN")
    private Boolean billingYN;

    @JsonProperty(value = "InHouseMemo")
    private String inHouseMemo;

    @JsonProperty(value = "SizeID")
    private Integer sizeID;

    @JsonProperty(value = "PackID")
    private Integer packID;

    @JsonProperty(value = "DistrictID")
    private Integer districtID;

    @JsonProperty(value = "FGPlan")
    private Integer fgPlan;

    @JsonProperty(value = "WebSiteLinkCount")
    private Integer webSiteLinkCount;

    @JsonProperty(value = "HowKnownType")
    private Integer howKnownType;

    @JsonProperty(value = "HowKnownOther")
    private String howKnownOther;

    @JsonProperty(value = "DisCountYN")
    private Boolean discountYN;

    @JsonProperty(value = "InsertedWhichApp")
    private Integer insertedWhichApp;

    @JsonProperty(value = "AllowImage2Anony")
    private Boolean allowImage2Anony;

    @JsonProperty(value = "MaxPictureQty")
    private Integer maxPictureQty;

    @JsonProperty(value = "AllowImmediateShopping")
    private Boolean allowImmediateShopping;

    @JsonProperty(value = "BusinessCategory")
    private String businessCategory;

    @JsonProperty(value = "ImageServerID")
    private Integer imageServerID;

    @JsonProperty(value = "ContactPerson")
    private String contactPerson;

    @JsonProperty(value = "CompanyTypeID")
    private Integer companyTypeID;

    @JsonProperty(value = "EstablishedYear")
    private Integer establishedYear;

    @JsonProperty(value = "PictureMain")
    private String pictureMain;

    @JsonProperty(value = "PictureLogo")
    private String pictureLogo;

    @JsonProperty(value = "SizeChart")
    private String sizeChart;

    @JsonProperty(value = "MadeIn")
    private String madeIn;

    @JsonProperty(value = "ProductSortByLastUpdate")
    private Boolean productSortByLastUpdate;

    @JsonProperty(value = "Active")
    private Boolean active;

    @JsonProperty(value = "ShopActive")
    private Boolean shopActive;

    @JsonProperty(value = "OrderActive")
    private Boolean orderActive;

    @JsonProperty(value = "BillCountryID")
    private Integer billCountryID;

    @JsonProperty(value = "CountryID")
    private Integer countryID;

    @JsonProperty(value = "BillPhone2")
    private String billPhone2;

    @JsonProperty(value = "Phone2")
    private String phone2;

    @JsonProperty(value = "CreditCardAccessPassword")
    private String creditCardAccessPassword;

    @JsonProperty(value = "AdminAccountCap")
    private Integer adminAccountCap;

    @JsonProperty(value = "DefaultSizeID")
    private Integer defaultSizeID;

    @JsonProperty(value = "DefaultPackID")
    private Integer defaultPackID;

    @JsonProperty(value = "DefaultFabricDescriptionID")
    private Integer defaultFabricDescriptionID;

    @JsonProperty(value = "ManageInventory")
    private Boolean manageInventory;

    @JsonProperty(value = "AllowAccess2Y3")
    private Boolean allowAccess2Y3;

    @JsonProperty(value = "SM_Facebook")
    private String sm_Facebook;

    @JsonProperty(value = "SM_Twitter")
    private String sm_Twitter;

    @JsonProperty(value = "SM_Youtube")
    private String sm_Youtube;

    @JsonProperty(value = "Blog")
    private String blog;

    @JsonProperty(value = "MinOrderByAmount")
    private BigDecimal minOrderByAmount;

    @JsonProperty(value = "MinOrderByQty")
    private Integer minOrderByQty;

    @JsonProperty(value = "ExtraCharge")
    private BigDecimal extraCharge;

    @JsonProperty(value = "ExtraChargeAmountFrom")
    private BigDecimal extraChargeAmountFrom;

    @JsonProperty(value = "ExtraChargeAmountTo")
    private BigDecimal extraChargeAmountTo;

    @JsonProperty(value = "IndustryType")
    private String industryType;

    @JsonProperty(value = "OrderActiveLock")
    private Boolean orderActiveLock;

    @JsonProperty(value = "ActiveOld")
    private String activeOld;

    @JsonProperty(value = "ShopActiveOld")
    private String shopActiveOld;

    @JsonProperty(value = "MinOrderAmount")
    private Float minOrderAmount;

    @JsonProperty(value = "MinOrderAmountYN")
    private String minOrderAmountYN;

    @JsonProperty(value = "MinTQAllYN")
    private String minTQAllYN;

    @JsonProperty(value = "MinTQAll")
    private Integer minTQAll;

    @JsonProperty(value = "ItemUploadCap")
    private Integer itemUploadCap;

    @JsonProperty(value = "DefaultMadeInCountryID")
    private Integer defaultMadeInCountryID;

    @JsonProperty(value = "DefaultLabelID")
    private Integer defaultLabelID;

    @JsonProperty(value = "DefaultInventoryStatusID")
    private Integer defaultInventoryStatusID;

    @JsonProperty(value = "PictureMain2")
    private String pictureMain2;

    @JsonProperty(value = "ShowFeedback")
    private Boolean showFeedback;

    @JsonProperty(value = "ConsolidationYN")
    private Boolean consolidationYN;

    @JsonProperty(value = "DefaultVendorID")
    private Integer defaultVendorID;

    @JsonProperty(value = "AdminWebServerID")
    private Integer adminWebServerID;

    @JsonProperty(value = "WholeSalerGUID")
    private String wholeSalerGUID;

    @JsonProperty(value = "FashionGoExclusive")
    private Boolean fashionGoExclusive;

    @JsonProperty(value = "SizeChartImage")
    private String sizeChartImage;

    @JsonProperty(value = "BlockPolicy")
    private Boolean blockPolicy;

    @JsonProperty(value = "SM_Instagram")
    private String sm_Instagram;

    @JsonProperty(value = "ChargedByCreditCard")
    private Boolean chargedByCreditCard;

    @JsonProperty(value = "Category", defaultValue = "")
    private String category;

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

    @JsonProperty(value = "IsADBlock")
    private Boolean isADBlock;

}
