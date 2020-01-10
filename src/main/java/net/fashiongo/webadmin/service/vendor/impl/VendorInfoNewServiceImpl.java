package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetVendorSettingParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import net.fashiongo.webadmin.model.vendor.AddressType;
import net.fashiongo.webadmin.model.vendor.EmailType;
import net.fashiongo.webadmin.model.vendor.IndustryType;
import net.fashiongo.webadmin.model.vendor.StatusType;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.VendorInfoNewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by jinwoo on 2020-01-06.
 */
@Service
@Slf4j
public class VendorInfoNewServiceImpl implements VendorInfoNewService {

    @Value("${api.endpoint.newVendorApi}")
    private String newVendorApi;

    private HttpClientWrapper httpCaller;

    public VendorInfoNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    private void updateAccount(Integer vendorId, String userId, String firstName, String lastName) {
        final String endpoint = newVendorApi + "/v1.0/vendor/" + vendorId + "/account/" + userId;
        VendorAccountCommand vendorAccountCommand = VendorAccountCommand.create(firstName, lastName, userId);
        httpCaller.put(endpoint, vendorAccountCommand, VendorApiHeader.getHeader());
    }

    private void updateVendorBasicInfo(VendorDetailInfo request) {
        final String endpoint = newVendorApi + "/v1.0/vendor/" + request.getWholeSalerID();
        VendorInfoCommand vendorInfoCommand = VendorInfoCommand.createForUpdatingBasicInfo(request);
        httpCaller.put(endpoint, vendorInfoCommand, VendorApiHeader.getHeader());
    }

    @Override
    public void update(VendorDetailInfo request) {
        // account
        updateAccount(request.getWholeSalerID(), request.getUserId(), request.getFirstName(), request.getLastName());
        // vendor basic info
        updateVendorBasicInfo(request);
    }

    @Override
    public void updateDetailInfo(SetVendorSettingParameter request, VendorDetailInfo vendorDetailInfo) {
        final String endpoint = newVendorApi + "/v1.0/vendor/" + vendorDetailInfo.getWholeSalerID();
        VendorInfoCommand vendorInfoCommand = VendorInfoCommand.createForUpdatingDetailInfo(request, vendorDetailInfo);
        httpCaller.put(endpoint, vendorInfoCommand, VendorApiHeader.getHeader());
    }

    @Getter
    private static class VendorAccountCommand {
        private String firstName;
        private String lastName;
        private String userId;
        private Integer typeCode;

        static VendorAccountCommand create(String firstName, String lastName, String userId) {
            VendorAccountCommand command = new VendorAccountCommand();
            command.firstName = firstName;
            command.lastName = lastName;
            command.userId = userId;
            return command;
        }
    }

    @Getter
    private static class VendorAddressCommand {
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

        static VendorAddressCommand create(
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

            VendorAddressCommand command = new VendorAddressCommand();
            command.typeCode = typeCode;
            command.name = name;
            command.streetNo1 = streetNo1;
            command.streetNo2 = streetNo2;
            command.city = city;
            command.state = state;
            command.zipCode = zipCode;
            command.countryName = countryName;
            command.phone = phone;
            command.fax = fax;
            command.isDefault = isDefault;
            return command;
        }

    }

    @Getter
    private static class VendorEmailCommand {
        private Integer typeCode;
        private String email;

        static VendorEmailCommand create(Integer typeCode, String email) {
            VendorEmailCommand command = new VendorEmailCommand();
            command.typeCode = typeCode;
            command.email = email;
            return command;
        }
    }

    @Getter
    private static class VendorDetailSettingInfoCommand {

        private String codename;
        private String dirname;

        private Integer statusCode;
        private Integer scheduledStatusCode;
        private String scheduledStatusDate;

        private Boolean isAdBlock;

        private BigDecimal transactionFeeRate1;
        private BigDecimal transactionFeeRate2;
        private BigDecimal transactionFeeRate1Intl;
        private BigDecimal transactionFeeRate2Intl;
        private BigDecimal transactionFeeFixed;

        private Integer capAccount;
        private Integer capCategory;
        private Integer capFraudReport;
        private Integer capItem;

        static VendorDetailSettingInfoCommand create(SetVendorSettingParameter request, VendorDetailInfo vendorDetailInfo) {

            VendorDetailSettingInfoCommand command = new VendorDetailSettingInfoCommand();

            command.dirname = vendorDetailInfo.getDirName();
            command.codename = vendorDetailInfo.getCodeName();

            StatusType vendorStatusType = StatusType.INACTIVE;
            if(vendorDetailInfo.getOrderActive()) {
                vendorStatusType = StatusType.ORDER_ACTIVE;
            } else if (vendorDetailInfo.getShopActive()) {
                vendorStatusType = StatusType.SHOP_ACTIVE;
            } else if (vendorDetailInfo.getActive()) {
                vendorStatusType = StatusType.ACTIVE;
            }

            if(vendorDetailInfo.getActualOpenDate() != null) {
                command.scheduledStatusCode = StatusType.ORDER_ACTIVE.getValue();
                command.scheduledStatusDate = vendorDetailInfo.getActualOpenDate().toString();
            }

            command.statusCode = vendorStatusType.getValue();
            command.isAdBlock = vendorDetailInfo.getIsADBlock();

            command.transactionFeeFixed = BigDecimal.valueOf(vendorDetailInfo.getTransactionFeeFixed());
            command.transactionFeeRate1 = BigDecimal.valueOf(vendorDetailInfo.getTransactionFeeRate1());
            command.transactionFeeRate1Intl = BigDecimal.valueOf(vendorDetailInfo.getTransactionFeeRate1Intl());
            command.transactionFeeRate2 = BigDecimal.valueOf(vendorDetailInfo.getTransactionFeeRate2());
            command.transactionFeeRate2Intl = BigDecimal.valueOf(vendorDetailInfo.getTransactionFeeRate2Intl());

            command.capAccount = request.getAdminAccount();
            command.capFraudReport = request.getFraudReport();
            command.capCategory = request.getVendorCategory();
            command.capItem = request.getItem();

            return command;
        }

    }

    @Getter
    private static class VendorBasicSettingInfoCommand {

        private String codename;
        private String dirname;
        private String name;
        private String description;
        private String orderNotice;
        private Boolean isConsolidation;
        private Integer capAccount;
        private String inHouseMemo;
        private String buyerNotice;
        private String memo;

        static VendorBasicSettingInfoCommand create(VendorDetailInfo request) {
            VendorBasicSettingInfoCommand command = new VendorBasicSettingInfoCommand();

            command.codename = request.getCodeName();
            command.dirname = request.getDirName();
            command.name = request.getCompanyName();
            command.description = request.getDescription();
            command.orderNotice = request.getOrderNotice();
            command.inHouseMemo = request.getInHouseMemo();
            command.buyerNotice = request.getNoticeToAll();
            command.memo = request.getMemo();
            command.isConsolidation = request.getConsolidationYN();
            command.capAccount = request.getAdminAccountCap();
            return command;
        }
    }

    @Getter
    private static class VendorInfoCommand<T> {

        private String name;
        private String dbaName;
        private String description;
        private Integer typeCode;
        private String businessCategoryInfo;
        private String website;
        private Integer establishedYear;

        private VendorAccountCommand account;
        private List<VendorEmailCommand> emails;
        private List<VendorAddressCommand> addresses;
        private T setting;
        private List<Integer> industries;

        static VendorInfoCommand createForUpdatingBasicInfo(VendorDetailInfo request) {
            VendorInfoCommand command = new VendorInfoCommand();

            command.name = request.getCompanyName();
            command.dbaName = request.getRegCompanyName();
            command.description = request.getDescription();
            command.typeCode = request.getCompanyTypeID();
            command.businessCategoryInfo = request.getBusinessCategory();
            command.website = request.getWebSite();
            command.establishedYear = request.getEstablishedYear();
            command.account = VendorAccountCommand.create(request.getFirstName(), request.getLastName(), request.getUserId());
            command.setting = VendorBasicSettingInfoCommand.create(request);
            command.emails = Arrays.asList(
                    VendorEmailCommand.create(EmailType.ORDER.getValue(), request.getEmail())
                    , VendorEmailCommand.create(EmailType.BILLING1.getValue(), request.getBillingEmail1())
                    , VendorEmailCommand.create(EmailType.BILLING2.getValue(), request.getBillingEmail2())
                    , VendorEmailCommand.create(EmailType.BILLING3.getValue(), request.getBillingEmail3())
            );
            command.addresses = Arrays.asList(
                VendorAddressCommand.create(
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
                , VendorAddressCommand.create(
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
                , VendorAddressCommand.create(
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

            List<Integer> industries = new ArrayList<>();
            if(StringUtils.isNotEmpty(request.getIndustryType())) {
                List<String> industryNames = Arrays.asList(request.getIndustryType().split(","));
                for(String value : industryNames) {
                    IndustryType type = IndustryType.get(value);
                    industries.add(type.getValue());
                }
            }
            command.industries = industries;

            return command;
        }

        static VendorInfoCommand createForUpdatingDetailInfo(SetVendorSettingParameter request, VendorDetailInfo vendorDetailInfo) {
            VendorInfoCommand command = new VendorInfoCommand();
            command.setting = VendorDetailSettingInfoCommand.create(request, vendorDetailInfo);
            return command;
        }
    }
}
