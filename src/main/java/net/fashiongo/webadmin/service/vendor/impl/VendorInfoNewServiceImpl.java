package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.AccountType;
import net.fashiongo.webadmin.data.model.vendor.SetVendorSettingParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import net.fashiongo.webadmin.model.vendor.AddressType;
import net.fashiongo.webadmin.model.vendor.EmailType;
import net.fashiongo.webadmin.model.vendor.IndustryType;
import net.fashiongo.webadmin.model.vendor.StatusType;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.VendorInfoNewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VendorInfoNewServiceImpl implements VendorInfoNewService {

    private HttpClientWrapper httpCaller;

    public VendorInfoNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    private void updateAccount(Integer vendorId, String originalUserId, String userId, String firstName, String lastName, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/accounts/" + originalUserId;
        VendorAccountCommand vendorAccountCommand = new VendorAccountCommand(firstName, lastName, userId);
        httpCaller.put(endpoint, vendorAccountCommand, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    private void updateVendorBasicInfo(VendorDetailInfo request, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + request.getWholeSalerID();
        VendorInfoCommand<VendorBasicSettingInfoCommand> vendorInfoCommand = new VendorInfoCommand<>(request, new VendorBasicSettingInfoCommand(request));
        httpCaller.put(endpoint, vendorInfoCommand, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    @Override
    public void update(VendorDetailInfo request, String originalUserId, Integer requestUserId, String requestUserName) {
        updateAccount(request.getWholeSalerID(), originalUserId, request.getUserId(), request.getFirstName(), request.getLastName(), requestUserId, requestUserName);
        updateVendorBasicInfo(request, requestUserId, requestUserName);
    }

    @Override
    public void updateDetailInfo(SetVendorSettingParameter request, VendorDetailInfo vendorDetailInfo, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorDetailInfo.getWholeSalerID();
        VendorInfoDetailCommand<VendorDetailSettingInfoCommand> vendorInfoCommand = new VendorInfoDetailCommand<>(vendorDetailInfo.getCodeName(), vendorDetailInfo.getDirName(), new VendorDetailSettingInfoCommand(request, vendorDetailInfo));
        httpCaller.put(endpoint, vendorInfoCommand, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    @Override
    public void updateStatusAndCloseDate(Integer wholeSalerID, Integer newStatusTypeValue, LocalDateTime contractExpireDate, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + wholeSalerID;
        VendorInfoDetailCommand<VendorCloseStatusInfoCommand> vendorInfoCommand = new VendorInfoDetailCommand<>(new VendorCloseStatusInfoCommand(newStatusTypeValue, contractExpireDate));
        httpCaller.put(endpoint, vendorInfoCommand, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    @Override
    public void updateStatus(Integer wholeSalerID, Integer newStatusTypeValue, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + wholeSalerID;
        VendorInfoDetailCommand<VendorStatusInfoCommand> vendorInfoCommand = new VendorInfoDetailCommand<>(new VendorStatusInfoCommand(newStatusTypeValue));
        httpCaller.put(endpoint, vendorInfoCommand, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    @Getter
    private class VendorAccountCommand {
        private String firstName;
        private String lastName;
        private String userId;
        private Integer typeCode;

        VendorAccountCommand(String firstName, String lastName, String userId) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.userId = userId;
            this.typeCode = AccountType.MASTER.getValue();
        }
    }

    @Getter
    private class VendorAddressCommand {
        private Integer typeCode;
        private String name;
        private String streetNo1;
        private String streetNo2;
        private String city;
        private String state;
        private String zipCode;
        private String countryName;
        private String phone;
        private String fax;
        private Boolean isDefault;

        private VendorAddressCommand(
                Integer typeCode,
                String name,
                String streetNo1,
                String streetNo2,
                String city,
                String state,
                String zipCode,
                String countryName,
                String phone,
                String fax,
                Boolean isDefault) {

            this.typeCode = typeCode;
            this.name = name;
            this.streetNo1 = streetNo1;
            this.streetNo2 = streetNo2;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            this.countryName = countryName;
            this.phone = phone;
            this.fax = fax;
            this.isDefault = isDefault;
        }

    }

    @Getter
    private class VendorEmailCommand {
        private Integer typeCode;
        private String email;

        private VendorEmailCommand(Integer typeCode, String email) {
            this.typeCode = typeCode;
            this.email = email;
        }
    }

    @Getter
    private class VendorCloseStatusInfoCommand {

        private Integer statusCode;
        private String closedDate;

        private VendorCloseStatusInfoCommand(Integer newStatusTypeValue, LocalDateTime closedDate) {
            this.statusCode = newStatusTypeValue;
            this.closedDate = (closedDate != null) ? closedDate.toString() : null;
        }
    }

    @Getter
    private class VendorStatusInfoCommand {

        private Integer statusCode;

        private VendorStatusInfoCommand(Integer newStatusTypeValue) {
            this.statusCode = newStatusTypeValue;
        }
    }

    @Getter
    private class VendorClassCodeCommand {

        private Integer classCode;

        private VendorClassCodeCommand(Integer classCode) {
            this.classCode = classCode;
        }
    }


    @Getter
    private class VendorDetailSettingInfoCommand {

        private Boolean isNew;

        private Integer statusCode;
        private String openDate;
        private String closedDate;

        private Boolean isAdBlock;

        private Boolean isUsePgService;
        private BigDecimal transactionFeeRate1;
        private BigDecimal transactionFeeRate2;
        private BigDecimal transactionFeeRate1Intl;
        private BigDecimal transactionFeeRate2Intl;
        private BigDecimal transactionFeeFixed;

        private Integer capAccount;
        private Integer capCategory;
        private Integer capFraudReport;
        private Integer capItem;

        private VendorDetailSettingInfoCommand(SetVendorSettingParameter request, VendorDetailInfo vendorDetailInfo) {

            StatusType vendorStatusType = StatusType.getStatusType(vendorDetailInfo.getActive(), vendorDetailInfo.getShopActive(), vendorDetailInfo.getOrderActive());
            this.statusCode = vendorStatusType.getValue();

            this.isNew = StringUtils.equals("Y", Optional.ofNullable(vendorDetailInfo.getNewCustYN()).orElse("N").toUpperCase());

            this.openDate = (vendorDetailInfo.getActualOpenDate() != null) ? vendorDetailInfo.getActualOpenDate().toString() : null;
            this.closedDate = (vendorDetailInfo.getContractExpireDate() != null) ? vendorDetailInfo.getContractExpireDate().toString() : null;

            this.isAdBlock = vendorDetailInfo.getIsADBlock();

            this.isUsePgService = vendorDetailInfo.getUseCreditCardPaymentService();
            this.transactionFeeFixed = (vendorDetailInfo.getTransactionFeeFixed() != null) ? BigDecimal.valueOf(vendorDetailInfo.getTransactionFeeFixed()) : null;
            this.transactionFeeRate1 = (vendorDetailInfo.getTransactionFeeRate1() != null) ? BigDecimal.valueOf(vendorDetailInfo.getTransactionFeeRate1()) : null;
            this.transactionFeeRate1Intl = (vendorDetailInfo.getTransactionFeeRate1Intl() != null) ? BigDecimal.valueOf(vendorDetailInfo.getTransactionFeeRate1Intl()) : null;
            this.transactionFeeRate2 = (vendorDetailInfo.getTransactionFeeRate2() != null) ? BigDecimal.valueOf(vendorDetailInfo.getTransactionFeeRate2()) : null;
            this.transactionFeeRate2Intl = (vendorDetailInfo.getTransactionFeeRate2Intl() != null) ? BigDecimal.valueOf(vendorDetailInfo.getTransactionFeeRate2Intl()) : null;

            this.capAccount = request.getAdminAccount();
            this.capFraudReport = request.getFraudReport();
            this.capCategory = request.getVendorCategory();
            this.capItem = request.getItem();
        }
    }

    @Getter
    private class VendorBasicSettingInfoCommand {

        private String orderNotice;
        private Boolean isConsolidation;
        private String inHouseMemo;
        private String buyerNotice;
        private String memo;

        private VendorBasicSettingInfoCommand(VendorDetailInfo request) {
            this.orderNotice = request.getOrderNotice();
            this.inHouseMemo = request.getInHouseMemo();
            this.buyerNotice = request.getNoticeToAll();
            this.memo = request.getMemo();
            this.isConsolidation = request.getConsolidationYN();
        }
    }

    @Getter
    private class VendorInfoCommand<T> {

        private String name;
        private String dbaName;
        private String codename;
        private String dirname;
        private String description;
        private String website;
        private String businessCategoryInfo;

        private Integer typeCode;
        private Integer establishedYear;
        private Integer sourceCode;

        private VendorAccountCommand account;
        private List<VendorEmailCommand> emails;
        private List<VendorAddressCommand> addresses;
        private T setting;
        private List<Integer> industries;

        private VendorInfoCommand(VendorDetailInfo request, T setting) {
            this.name = request.getCompanyName();
            this.dbaName = request.getRegCompanyName();
            this.codename = request.getCodeName();
            this.dirname = request.getDirName();
            this.description = request.getDescription();
            this.website = request.getWebSite();
            this.businessCategoryInfo = request.getBusinessCategory();
            this.typeCode = request.getCompanyTypeID();
            this.establishedYear = request.getEstablishedYear();
            this.sourceCode = request.getSourceType();

            this.account = new VendorAccountCommand(request.getFirstName(), request.getLastName(), request.getUserId());

            this.emails = Arrays.asList(
                    new VendorEmailCommand(EmailType.ORDER.getValue(), request.getEmail())
                    , new VendorEmailCommand(EmailType.BILLING1.getValue(), request.getBillingEmail1())
                    , new VendorEmailCommand(EmailType.BILLING2.getValue(), request.getBillingEmail2())
                    , new VendorEmailCommand(EmailType.BILLING3.getValue(), request.getBillingEmail3())
            );
            this.addresses = Arrays.asList(
                    new VendorAddressCommand(
                            AddressType.SHOWROOM.getValue(),
                            AddressType.SHOWROOM.name(),
                            request.getBillStreetNo(),
                            request.getBillStreetNo2(),
                            request.getBillCity(),
                            request.getBillState(),
                            request.getBillZipcode(),
                            request.getBillCountry(),
                            request.getBillPhone(),
                            request.getBillFax(),
                            true)
                    , new VendorAddressCommand(
                            AddressType.BILLING.getValue(),
                            AddressType.BILLING.name(),
                            request.getShowRoomStreetNo(),
                            null,
                            request.getShowRoomCity(),
                            request.getShowRoomState(),
                            request.getShowRoomZipcode(),
                            request.getShowRoomCountry(),
                            request.getShowRoomPhone(),
                            request.getShowRoomFax(),
                            false)
                    , new VendorAddressCommand(
                            AddressType.WAREHOUSE.getValue(),
                            AddressType.WAREHOUSE.name(),
                            request.getStreetNo(),
                            request.getStreetNo2(),
                            request.getCity(),
                            request.getState(),
                            request.getZipcode(),
                            request.getCountry(),
                            request.getPhone(),
                            request.getFax(),
                            false)
            );

            this.setting = setting;

            List<Integer> industries = null;
            if (StringUtils.isNotEmpty(request.getIndustryType())) {
                industries = new ArrayList<>();
                String[] industryNames = request.getIndustryType().split(",");
                for (String value : industryNames) {
                    IndustryType type = IndustryType.get(value);
                    industries.add(type.getValue());
                }
            }
            this.industries = industries;
        }
    }

    @Getter
    private class VendorInfoDetailCommand<T> {

        private String codename;
        private String dirname;

        private T setting;

        private VendorInfoDetailCommand(String codeName, String dirName, T setting) {
            this.codename = codeName;
            this.dirname = dirName;
            this.setting = setting;
        }

        private VendorInfoDetailCommand(T setting) {
            this.setting = setting;
        }
    }


}
