package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.fashiongo.webadmin.utility.LocalDateTimeDeserializer;
import net.fashiongo.webadmin.utility.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VendorDetailInfo {
    @JsonProperty(value = "WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty(value = "SortNo")
    private Integer sortNo;

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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime contractExpireDate;

    @JsonProperty(value = "BillReviewHoLee")
    private String billReviewHoLee;

    @JsonProperty(value = "CompsolutionNo")
    private Integer compsolutionNo;

    @JsonProperty(value = "RetailerYes")
    private Boolean retailerYes;

    @JsonProperty(value = "RetailerBlockList")
    private String retailerBlockList;

    @JsonProperty(value = "RetailerOpenList")
    private String retailerOpenList;

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

    @JsonProperty(value = "Source_Type")
    private Integer sourceType;

    @JsonProperty(value = "Vendor_seo_Id")
    private Integer vendorseoId;

    @JsonProperty(value = "Meta_Keyword")
    private String metaKeyword;

    @JsonProperty(value = "Meta_Description")
    private String metaDescription;

    public VendorDetailInfo(Integer wholeSalerID, Integer sortNo, String companyName, String regCompanyName, String dirName, String codeName, String firstName, String lastName, String description,
                            String billStreetNo, String billCity, String billState, String billZipcode, String billCountry, String billPhone, String billFax,
                            String streetNo, String city, String state, String zipcode, String country, String phone, String email, String fax,
                            String memo, String webSite, String userId, String pwd, String webSiteActive, String slActive, String reportActive, String catalogActive, String lambsActive,
                            Integer group1, Integer group2, String dm, String posUse, String mainPage, String titlePageMemo, String wsaPolicy, String wholeSalerTitlePage, String onSale,
                            String newCustYN, String goodUpYN, String minTQYN, Integer minTQ, String minDollarYN, Float minDollar, String minECQYN, Integer minECQ, String qtySeqYN, String minPolicyUseYN,
                            String orderNotice, String compCharge, Float ratio, String autoRActive, String minCharge, Float minAmount, String isMonthly, String isYearly, BigDecimal yearlyAmount,
                            String noticeToAll, String asKnownAs, String honote1, String honote2, String honote3, String honote4, Timestamp actualOpenDate, String billingNote2, String specialNote1,
                            String specialNote2, String adv1, String adv2, String advertiseYN, String actualOpen, String ownerCountry, Timestamp contractExpireDate, String billReviewHoLee,
                            Integer compsolutionNo, Boolean retailerYes, String retailerBlockList, String retailerOpenList, String prePackYN, Boolean salesItem, Boolean hotItems, Boolean promotionalItems,
                            String billStreetNo2, String streetNo2, String itemLocation, String lastUser, String minTQYNStyle, Integer minTQStyle, String billingNote1, Boolean billingYN, String inHouseMemo,
                            Integer sizeID, Integer packID, Integer districtID, Integer fgPlan, Integer webSiteLinkCount, Integer howKnownType, String howKnownOther, Boolean discountYN, Integer insertedWhichApp,
                            Boolean allowImage2Anony, Integer maxPictureQty, Boolean allowImmediateShopping, String businessCategory, Integer imageServerID, String contactPerson, Integer companyTypeID,
                            Integer establishedYear, String pictureMain, String pictureLogo, String sizeChart, String madeIn, Boolean productSortByLastUpdate, Boolean active, Boolean shopActive, Boolean orderActive,
                            Integer billCountryID, Integer countryID, String billPhone2, String phone2, String creditCardAccessPassword, Integer adminAccountCap, Integer defaultSizeID, Integer defaultPackID,
                            Integer defaultFabricDescriptionID, Boolean manageInventory, Boolean allowAccess2Y3, String sm_Facebook, String sm_Twitter, String sm_Youtube, String blog, BigDecimal minOrderByAmount,
                            Integer minOrderByQty, BigDecimal extraCharge, BigDecimal extraChargeAmountFrom, BigDecimal extraChargeAmountTo, String industryType, Boolean orderActiveLock, String activeOld, String shopActiveOld,
                            Float minOrderAmount, String minOrderAmountYN, String minTQAllYN, Integer minTQAll, Integer itemUploadCap, Integer defaultMadeInCountryID, Integer defaultLabelID,
                            Integer defaultInventoryStatusID, String pictureMain2, Boolean showFeedback, Boolean consolidationYN, Integer defaultVendorID, Integer adminWebServerID, String wholeSalerGUID,
                            Boolean fashionGoExclusive, String sizeChartImage, Boolean blockPolicy, String sm_Instagram, Boolean chargedByCreditCard, String category, Boolean isLockedOut, Timestamp lastLockoutDate,
                            Long isLockedOut2, Boolean useCreditCardPaymentService, Double transactionFeeRate1, Double transactionFeeRate2, Double transactionFeeRate1Intl, Double transactionFeeRate2Intl,
                            Double transactionFeeFixed, Double commissionRate, String billingEmail1, String billingEmail2, String billingEmail3,
                            String showRoomStreetNo, String showRoomCity, String showRoomCountry, String showRoomState, String showRoomZipcode, String showRoomPhone, String showRoomFax, Long elambsuser, Boolean isADBlock, Integer sourceType) {
        this.wholeSalerID = wholeSalerID;
        this.sortNo = sortNo;
        this.companyName = companyName;
        this.regCompanyName = regCompanyName;
        this.dirName = dirName;
        this.codeName = codeName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.billStreetNo = billStreetNo;
        this.billCity = billCity;
        this.billState = billState;
        this.billZipcode = billZipcode;
        this.billCountry = billCountry;
        this.billPhone = billPhone;
        this.billFax = billFax;
        this.streetNo = streetNo;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.fax = fax;
        this.memo = memo;
        this.webSite = webSite;
        this.userId = userId;
        this.pwd = pwd;
        this.webSiteActive = webSiteActive;
        this.slActive = slActive;
        this.reportActive = reportActive;
        this.catalogActive = catalogActive;
        this.lambsActive = lambsActive;
        this.group1 = group1;
        this.group2 = group2;
        this.dm = dm;
        this.posUse = posUse;
        this.mainPage = mainPage;
        this.titlePageMemo = titlePageMemo;
        this.wsaPolicy = wsaPolicy;
        this.wholeSalerTitlePage = wholeSalerTitlePage;
        this.onSale = onSale;
        this.newCustYN = newCustYN;
        this.goodUpYN = goodUpYN;
        this.minTQYN = minTQYN;
        this.minTQ = minTQ;
        this.minDollarYN = minDollarYN;
        this.minDollar = minDollar;
        this.minECQYN = minECQYN;
        this.minECQ = minECQ;
        this.qtySeqYN = qtySeqYN;
        this.minPolicyUseYN = minPolicyUseYN;
        this.orderNotice = orderNotice;
        this.compCharge = compCharge;
        this.ratio = ratio;
        this.autoRActive = autoRActive;
        this.minCharge = minCharge;
        this.minAmount = minAmount;
        this.isMonthly = isMonthly;
        this.isYearly = isYearly;
        this.yearlyAmount = yearlyAmount;
        this.noticeToAll = noticeToAll;
        this.asKnownAs = asKnownAs;
        this.honote1 = honote1;
        this.honote2 = honote2;
        this.honote3 = honote3;
        this.honote4 = honote4;
        this.actualOpenDate = actualOpenDate == null ? null : actualOpenDate.toLocalDateTime();
        this.billingNote2 = billingNote2;
        this.specialNote1 = specialNote1;
        this.specialNote2 = specialNote2;
        this.adv1 = adv1;
        this.adv2 = adv2;
        this.advertiseYN = advertiseYN;
        this.actualOpen = actualOpen;
        this.ownerCountry = ownerCountry;
        this.contractExpireDate = contractExpireDate == null ? null : contractExpireDate.toLocalDateTime();
        this.billReviewHoLee = billReviewHoLee;
        this.compsolutionNo = compsolutionNo;
        this.retailerYes = retailerYes;
        this.retailerBlockList = retailerBlockList;
        this.retailerOpenList = retailerOpenList;
        this.prePackYN = prePackYN;
        this.salesItem = salesItem;
        this.hotItems = hotItems;
        this.promotionalItems = promotionalItems;
        this.billStreetNo2 = billStreetNo2;
        this.streetNo2 = streetNo2;
        this.itemLocation = itemLocation;
        this.lastUser = lastUser;
        this.minTQYNStyle = minTQYNStyle;
        this.minTQStyle = minTQStyle;
        this.billingNote1 = billingNote1;
        this.billingYN = billingYN;
        this.inHouseMemo = inHouseMemo;
        this.sizeID = sizeID;
        this.packID = packID;
        this.districtID = districtID;
        this.fgPlan = fgPlan;
        this.webSiteLinkCount = webSiteLinkCount;
        this.howKnownType = howKnownType;
        this.howKnownOther = howKnownOther;
        this.discountYN = discountYN;
        this.insertedWhichApp = insertedWhichApp;
        this.allowImage2Anony = allowImage2Anony;
        this.maxPictureQty = maxPictureQty;
        this.allowImmediateShopping = allowImmediateShopping;
        this.businessCategory = businessCategory;
        this.imageServerID = imageServerID;
        this.contactPerson = contactPerson;
        this.companyTypeID = companyTypeID;
        this.establishedYear = establishedYear;
        this.pictureMain = pictureMain;
        this.pictureLogo = pictureLogo;
        this.sizeChart = sizeChart;
        this.madeIn = madeIn;
        this.productSortByLastUpdate = productSortByLastUpdate;
        this.active = active;
        this.shopActive = shopActive;
        this.orderActive = orderActive;
        this.billCountryID = billCountryID;
        this.countryID = countryID;
        this.billPhone2 = billPhone2;
        this.phone2 = phone2;
        this.creditCardAccessPassword = creditCardAccessPassword;
        this.adminAccountCap = adminAccountCap;
        this.defaultSizeID = defaultSizeID;
        this.defaultPackID = defaultPackID;
        this.defaultFabricDescriptionID = defaultFabricDescriptionID;
        this.manageInventory = manageInventory;
        this.allowAccess2Y3 = allowAccess2Y3;
        this.sm_Facebook = sm_Facebook;
        this.sm_Twitter = sm_Twitter;
        this.sm_Youtube = sm_Youtube;
        this.blog = blog;
        this.minOrderByAmount = minOrderByAmount;
        this.minOrderByQty = minOrderByQty;
        this.extraCharge = extraCharge;
        this.extraChargeAmountFrom = extraChargeAmountFrom;
        this.extraChargeAmountTo = extraChargeAmountTo;
        this.industryType = industryType;
        this.orderActiveLock = orderActiveLock;
        this.activeOld = activeOld;
        this.shopActiveOld = shopActiveOld;
        this.minOrderAmount = minOrderAmount;
        this.minOrderAmountYN = minOrderAmountYN;
        this.minTQAllYN = minTQAllYN;
        this.minTQAll = minTQAll;
        this.itemUploadCap = itemUploadCap;
        this.defaultMadeInCountryID = defaultMadeInCountryID;
        this.defaultLabelID = defaultLabelID;
        this.defaultInventoryStatusID = defaultInventoryStatusID;
        this.pictureMain2 = pictureMain2;
        this.showFeedback = showFeedback;
        this.consolidationYN = consolidationYN;
        this.defaultVendorID = defaultVendorID;
        this.adminWebServerID = adminWebServerID;
        this.wholeSalerGUID = wholeSalerGUID;
        this.fashionGoExclusive = fashionGoExclusive;
        this.sizeChartImage = sizeChartImage;
        this.blockPolicy = blockPolicy;
        this.sm_Instagram = sm_Instagram;
        this.chargedByCreditCard = chargedByCreditCard;
        this.category = category;
        this.isLockedOut = isLockedOut;
        this.lastLockoutDate = lastLockoutDate;
        this.isLockedOut2 = isLockedOut2;
        this.useCreditCardPaymentService = useCreditCardPaymentService;
        this.transactionFeeRate1 = transactionFeeRate1;
        this.transactionFeeRate2 = transactionFeeRate2;
        this.transactionFeeRate1Intl = transactionFeeRate1Intl;
        this.transactionFeeRate2Intl = transactionFeeRate2Intl;
        this.transactionFeeFixed = transactionFeeFixed;
        this.commissionRate = commissionRate;
        this.billingEmail1 = billingEmail1;
        this.billingEmail2 = billingEmail2;
        this.billingEmail3 = billingEmail3;
        this.showRoomStreetNo = showRoomStreetNo;
        this.showRoomCity = showRoomCity;
        this.showRoomCountry = showRoomCountry;
        this.showRoomState = showRoomState;
        this.showRoomZipcode = showRoomZipcode;
        this.showRoomPhone = showRoomPhone;
        this.showRoomFax = showRoomFax;
        this.elambsuser = elambsuser;
        this.isADBlock = isADBlock;
        this.sourceType = sourceType;
    }

    public VendorDetailInfo(Integer wholeSalerID, Integer sortNo, Timestamp startingDate, String companyName, String regCompanyName, String dirName, String codeName, String firstName, String lastName, String description, String billStreetNo, String billCity, String billState, String billZipcode, String billCountry, String billPhone, String billFax, String streetNo, String city, String state, String zipcode, String country, String phone, String email, String fax, String memo, String webSite, String userId, String pwd, String webSiteActive, String slActive, String reportActive, String catalogActive, String lambsActive, Integer group1, Integer group2, String dm, String posUse, String mainPage, String titlePageMemo, String wsaPolicy, String wholeSalerTitlePage, String onSale, String newCustYN, String goodUpYN, String minTQYN, Integer minTQ, String minDollarYN, Float minDollar, String minECQYN, Integer minECQ, String qtySeqYN, String minPolicyUseYN, String orderNotice, String compCharge, Float ratio, String autoRActive, String minCharge, Float minAmount, String isMonthly, String isYearly, BigDecimal yearlyAmount, String noticeToAll, String asKnownAs, String honote1, String honote2, String honote3, String honote4, Timestamp actualOpenDate, String billingNote2, String specialNote1, String specialNote2, String adv1, String adv2, String advertiseYN, String actualOpen, String ownerCountry, Timestamp contractExpireDate, String billReviewHoLee, LocalDateTime openDate, Integer compsolutionNo, Boolean retailerYes, String retailerBlockList, String retailerOpenList, LocalDateTime dateTimeModified, String prePackYN, Boolean salesItem, Boolean hotItems, Boolean promotionalItems, String billStreetNo2, String streetNo2, String itemLocation, String lastUser, Timestamp lastModifiedDateTime, String minTQYNStyle, Integer minTQStyle, String billingNote1, Boolean billingYN, String inHouseMemo, Integer sizeID, Integer packID, Integer districtID, Integer fgPlan, Integer webSiteLinkCount, Integer howKnownType, String howKnownOther, Boolean discountYN, Integer insertedWhichApp, Boolean allowImage2Anony, Integer maxPictureQty, Boolean allowImmediateShopping, String businessCategory, Integer imageServerID, String contactPerson, Integer companyTypeID, Integer establishedYear, String pictureMain, String pictureLogo, String sizeChart, String madeIn, Boolean productSortByLastUpdate, Boolean active, Boolean shopActive, Boolean orderActive, Integer billCountryID, Integer countryID, String billPhone2, String phone2, String creditCardAccessPassword, Integer adminAccountCap, Integer defaultSizeID, Integer defaultPackID, Integer defaultFabricDescriptionID, Boolean manageInventory, Boolean allowAccess2Y3, String sm_Facebook, String sm_Twitter, String sm_Youtube, String blog, BigDecimal minOrderByAmount, Integer minOrderByQty, BigDecimal extraCharge, BigDecimal extraChargeAmountFrom, BigDecimal extraChargeAmountTo, String industryType, Boolean orderActiveLock, String activeOld, String shopActiveOld, Float minOrderAmount, String minOrderAmountYN, String minTQAllYN, Integer minTQAll, Integer itemUploadCap, Integer defaultMadeInCountryID, Integer defaultLabelID, Integer defaultInventoryStatusID, String pictureMain2, Boolean showFeedback, Boolean consolidationYN, Integer defaultVendorID, Integer adminWebServerID, String wholeSalerGUID, Boolean fashionGoExclusive, String sizeChartImage, Boolean blockPolicy, String sm_Instagram, Boolean chargedByCreditCard, String category, Boolean isLockedOut, Timestamp lastLockoutDate, Long isLockedOut2, Boolean useCreditCardPaymentService, Double transactionFeeRate1, Double transactionFeeRate2, Double transactionFeeRate1Intl, Double transactionFeeRate2Intl, Double transactionFeeFixed, Double commissionRate, String billingEmail1, String billingEmail2, String billingEmail3, String showRoomStreetNo, String showRoomCity, String showRoomCountry, String showRoomState, String showRoomZipcode, String showRoomPhone, String showRoomFax, Long elambsuser, Boolean isADBlock, Integer sourceType ,Integer vendorseoId,String metaKeyword,String metaDescription) {
        this.wholeSalerID = wholeSalerID;
        this.sortNo = sortNo;
        this.companyName = companyName;
        this.regCompanyName = regCompanyName;
        this.dirName = dirName;
        this.codeName = codeName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.billStreetNo = billStreetNo;
        this.billCity = billCity;
        this.billState = billState;
        this.billZipcode = billZipcode;
        this.billCountry = billCountry;
        this.billPhone = billPhone;
        this.billFax = billFax;
        this.streetNo = streetNo;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.fax = fax;
        this.memo = memo;
        this.webSite = webSite;
        this.userId = userId;
        this.pwd = pwd;
        this.webSiteActive = webSiteActive;
        this.slActive = slActive;
        this.reportActive = reportActive;
        this.catalogActive = catalogActive;
        this.lambsActive = lambsActive;
        this.group1 = group1;
        this.group2 = group2;
        this.dm = dm;
        this.posUse = posUse;
        this.mainPage = mainPage;
        this.titlePageMemo = titlePageMemo;
        this.wsaPolicy = wsaPolicy;
        this.wholeSalerTitlePage = wholeSalerTitlePage;
        this.onSale = onSale;
        this.newCustYN = newCustYN;
        this.goodUpYN = goodUpYN;
        this.minTQYN = minTQYN;
        this.minTQ = minTQ;
        this.minDollarYN = minDollarYN;
        this.minDollar = minDollar;
        this.minECQYN = minECQYN;
        this.minECQ = minECQ;
        this.qtySeqYN = qtySeqYN;
        this.minPolicyUseYN = minPolicyUseYN;
        this.orderNotice = orderNotice;
        this.compCharge = compCharge;
        this.ratio = ratio;
        this.autoRActive = autoRActive;
        this.minCharge = minCharge;
        this.minAmount = minAmount;
        this.isMonthly = isMonthly;
        this.isYearly = isYearly;
        this.yearlyAmount = yearlyAmount;
        this.noticeToAll = noticeToAll;
        this.asKnownAs = asKnownAs;
        this.honote1 = honote1;
        this.honote2 = honote2;
        this.honote3 = honote3;
        this.honote4 = honote4;
        this.actualOpenDate = actualOpenDate == null ? null : actualOpenDate.toLocalDateTime();
        this.billingNote2 = billingNote2;
        this.specialNote1 = specialNote1;
        this.specialNote2 = specialNote2;
        this.adv1 = adv1;
        this.adv2 = adv2;
        this.advertiseYN = advertiseYN;
        this.actualOpen = actualOpen;
        this.ownerCountry = ownerCountry;
        this.contractExpireDate = contractExpireDate == null ? null : contractExpireDate.toLocalDateTime();
        this.billReviewHoLee = billReviewHoLee;
        this.compsolutionNo = compsolutionNo;
        this.retailerYes = retailerYes;
        this.retailerBlockList = retailerBlockList;
        this.retailerOpenList = retailerOpenList;
        this.prePackYN = prePackYN;
        this.salesItem = salesItem;
        this.hotItems = hotItems;
        this.promotionalItems = promotionalItems;
        this.billStreetNo2 = billStreetNo2;
        this.streetNo2 = streetNo2;
        this.itemLocation = itemLocation;
        this.lastUser = lastUser;
        this.minTQYNStyle = minTQYNStyle;
        this.minTQStyle = minTQStyle;
        this.billingNote1 = billingNote1;
        this.billingYN = billingYN;
        this.inHouseMemo = inHouseMemo;
        this.sizeID = sizeID;
        this.packID = packID;
        this.districtID = districtID;
        this.fgPlan = fgPlan;
        this.webSiteLinkCount = webSiteLinkCount;
        this.howKnownType = howKnownType;
        this.howKnownOther = howKnownOther;
        this.discountYN = discountYN;
        this.insertedWhichApp = insertedWhichApp;
        this.allowImage2Anony = allowImage2Anony;
        this.maxPictureQty = maxPictureQty;
        this.allowImmediateShopping = allowImmediateShopping;
        this.businessCategory = businessCategory;
        this.imageServerID = imageServerID;
        this.contactPerson = contactPerson;
        this.companyTypeID = companyTypeID;
        this.establishedYear = establishedYear;
        this.pictureMain = pictureMain;
        this.pictureLogo = pictureLogo;
        this.sizeChart = sizeChart;
        this.madeIn = madeIn;
        this.productSortByLastUpdate = productSortByLastUpdate;
        this.active = active;
        this.shopActive = shopActive;
        this.orderActive = orderActive;
        this.billCountryID = billCountryID;
        this.countryID = countryID;
        this.billPhone2 = billPhone2;
        this.phone2 = phone2;
        this.creditCardAccessPassword = creditCardAccessPassword;
        this.adminAccountCap = adminAccountCap;
        this.defaultSizeID = defaultSizeID;
        this.defaultPackID = defaultPackID;
        this.defaultFabricDescriptionID = defaultFabricDescriptionID;
        this.manageInventory = manageInventory;
        this.allowAccess2Y3 = allowAccess2Y3;
        this.sm_Facebook = sm_Facebook;
        this.sm_Twitter = sm_Twitter;
        this.sm_Youtube = sm_Youtube;
        this.blog = blog;
        this.minOrderByAmount = minOrderByAmount;
        this.minOrderByQty = minOrderByQty;
        this.extraCharge = extraCharge;
        this.extraChargeAmountFrom = extraChargeAmountFrom;
        this.extraChargeAmountTo = extraChargeAmountTo;
        this.industryType = industryType;
        this.orderActiveLock = orderActiveLock;
        this.activeOld = activeOld;
        this.shopActiveOld = shopActiveOld;
        this.minOrderAmount = minOrderAmount;
        this.minOrderAmountYN = minOrderAmountYN;
        this.minTQAllYN = minTQAllYN;
        this.minTQAll = minTQAll;
        this.itemUploadCap = itemUploadCap;
        this.defaultMadeInCountryID = defaultMadeInCountryID;
        this.defaultLabelID = defaultLabelID;
        this.defaultInventoryStatusID = defaultInventoryStatusID;
        this.pictureMain2 = pictureMain2;
        this.showFeedback = showFeedback;
        this.consolidationYN = consolidationYN;
        this.defaultVendorID = defaultVendorID;
        this.adminWebServerID = adminWebServerID;
        this.wholeSalerGUID = wholeSalerGUID;
        this.fashionGoExclusive = fashionGoExclusive;
        this.sizeChartImage = sizeChartImage;
        this.blockPolicy = blockPolicy;
        this.sm_Instagram = sm_Instagram;
        this.chargedByCreditCard = chargedByCreditCard;
        this.category = category;
        this.isLockedOut = isLockedOut;
        this.lastLockoutDate = lastLockoutDate;
        this.isLockedOut2 = isLockedOut2;
        this.useCreditCardPaymentService = useCreditCardPaymentService;
        this.transactionFeeRate1 = transactionFeeRate1;
        this.transactionFeeRate2 = transactionFeeRate2;
        this.transactionFeeRate1Intl = transactionFeeRate1Intl;
        this.transactionFeeRate2Intl = transactionFeeRate2Intl;
        this.transactionFeeFixed = transactionFeeFixed;
        this.commissionRate = commissionRate;
        this.billingEmail1 = billingEmail1;
        this.billingEmail2 = billingEmail2;
        this.billingEmail3 = billingEmail3;
        this.showRoomStreetNo = showRoomStreetNo;
        this.showRoomCity = showRoomCity;
        this.showRoomCountry = showRoomCountry;
        this.showRoomState = showRoomState;
        this.showRoomZipcode = showRoomZipcode;
        this.showRoomPhone = showRoomPhone;
        this.showRoomFax = showRoomFax;
        this.elambsuser = elambsuser;
        this.isADBlock = isADBlock;
        this.sourceType = sourceType;
        this.vendorseoId = vendorseoId;
        this.metaKeyword = metaKeyword;
        this.metaDescription = metaDescription;
    }

}
