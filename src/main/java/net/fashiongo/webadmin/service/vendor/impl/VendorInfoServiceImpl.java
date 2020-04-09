package net.fashiongo.webadmin.service.vendor.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.dal.JdbcHelper;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBasicInfoParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorSettingParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import net.fashiongo.webadmin.data.repository.primary.*;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorCapEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorPayoutInfoEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorWholeSalerEntityRepository;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.VendorContractService;
import net.fashiongo.webadmin.service.vendor.VendorInfoNewService;
import net.fashiongo.webadmin.service.vendor.VendorInfoService;
import net.fashiongo.webadmin.service.vendor.VendorPaymentInfoNewService;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by jinwoo on 2020-01-03.
 */
@Service
@Slf4j
public class VendorInfoServiceImpl implements VendorInfoService {

    private ConfigurableEnvironment env;

    private JdbcHelper jdbcHelperFgBilling;

    private VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;

    private AspnetUsersEntityRepository aspnetUsersEntityRepository;

    private EntityActionLogEntityRepository entityActionLogEntityRepository;

    private AspnetMembershipEntityRepository aspnetMembershipEntityRepository;

    private TodayDealEntityRepository todayDealEntityRepository;

    private VendorPayoutInfoEntityRepository vendorPayoutInfoEntityRepository;

    private MapWholeSalerPaymentMethodEntityRepository mapWholeSalerPaymentMethodEntityRepository;

    private VendorAdminAccountEntityRepository vendorAdminAccountEntityRepository;

    private AdVendorItemEntityRepository adVendorItemEntityRepository;

    private VendorDirNameChangeLogEntityRepository vendorDirNameChangeLogEntityRepository;

    private VendorInfoNewService vendorInfoNewService;

    private VendorCapEntityRepository vendorCapEntityRepository;

    private VendorPaymentInfoNewService vendorPaymentInfoNewService;

    private VendorContractService vendorContractService;

    private CacheService cacheService;

    private final static Logger logger = LoggerFactory.getLogger("vendorContractCheckLogger");

    public VendorInfoServiceImpl(VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository,
                                 AspnetUsersEntityRepository aspnetUsersEntityRepository,
                                 AspnetMembershipEntityRepository aspnetMembershipEntityRepository,
                                 EntityActionLogEntityRepository entityActionLogEntityRepository,
                                 VendorPayoutInfoEntityRepository vendorPayoutInfoEntityRepository,
                                 TodayDealEntityRepository todayDealEntityRepository,
                                 MapWholeSalerPaymentMethodEntityRepository mapWholeSalerPaymentMethodEntityRepository,
                                 VendorAdminAccountEntityRepository vendorAdminAccountEntityRepository,
                                 AdVendorItemEntityRepository adVendorItemEntityRepository,
                                 VendorDirNameChangeLogEntityRepository vendorDirNameChangeLogEntityRepository,
                                 VendorInfoNewService vendorInfoNewService,
                                 VendorCapEntityRepository vendorCapEntityRepository,
                                 VendorPaymentInfoNewService vendorPaymentInfoNewService,
                                 VendorContractService vendorContractService,
                                 CacheService cacheService,
                                 JdbcHelper jdbcHelperFgBilling,
                                 ConfigurableEnvironment env) {
        this.vendorWholeSalerEntityRepository = vendorWholeSalerEntityRepository;
        this.aspnetUsersEntityRepository = aspnetUsersEntityRepository;
        this.aspnetMembershipEntityRepository = aspnetMembershipEntityRepository;
        this.entityActionLogEntityRepository = entityActionLogEntityRepository;
        this.vendorPayoutInfoEntityRepository = vendorPayoutInfoEntityRepository;
        this.todayDealEntityRepository = todayDealEntityRepository;
        this.mapWholeSalerPaymentMethodEntityRepository = mapWholeSalerPaymentMethodEntityRepository;
        this.vendorAdminAccountEntityRepository = vendorAdminAccountEntityRepository;
        this.adVendorItemEntityRepository = adVendorItemEntityRepository;
        this.vendorDirNameChangeLogEntityRepository = vendorDirNameChangeLogEntityRepository;
        this.vendorInfoNewService = vendorInfoNewService;
        this.vendorCapEntityRepository = vendorCapEntityRepository;
        this.vendorPaymentInfoNewService = vendorPaymentInfoNewService;
        this.vendorContractService = vendorContractService;
        this.cacheService = cacheService;
        this.jdbcHelperFgBilling = jdbcHelperFgBilling;
        this.env = env;
    }

    private final static ObjectMapper mapper = new ObjectMapper();

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Integer update(SetVendorBasicInfoParameter request) {
        try {
            VendorDetailInfo vendorDetailInfo = mapper.readValue(request.getVendorBasicInfo(), VendorDetailInfo.class);
            Integer result = updateVendorBasicInfo(vendorDetailInfo);
            if (result == 1) {
                cacheService.cacheEvictVendor(request.getWid());
                try {
                    updateAccountOfBillingSystem(vendorDetailInfo);
                } catch (Exception ex) {
                    log.warn("fail to update a account info of billing system. {}", ex.getMessage(), ex);
                }
            }
            return result;
        } catch (IOException e) {
            log.error("object mapper parse error", e);
            return null;
        }
    }

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Integer setVendorSettingInfo(SetVendorSettingParameter request) {
        try {
            VendorDetailInfo vendorDetailInfo = mapper.readValue(request.getVendorBasicInfo(), VendorDetailInfo.class);
            Integer result = updateVendorSettingInfo(request, vendorDetailInfo);
            if(result == 1) {
                cacheService.cacheEvictVendor(request.getWid());
            }
            return result;
        } catch (IOException e) {
            log.error("object mapper parse error", e);
            return null;
        }
    }

    private void setDirCompanyNameChangeHistory(VendorDetailInfo vendorDetailInfo, WholeSalerEntity wholeSalerEntity) {

        String oldCompanyName = Optional.ofNullable(wholeSalerEntity.getCompanyName()).orElse("");
        String newCompanyName = vendorDetailInfo.getCompanyName();
        if(StringUtils.isEmpty(oldCompanyName) || StringUtils.isEmpty(newCompanyName) || StringUtils.equals(oldCompanyName, newCompanyName)) {
            return;
        }

        String dirName = Optional.ofNullable(vendorDetailInfo.getDirName()).orElse("");
        try {
            setDirCompanyNameChangeHistory(dirName, dirName, oldCompanyName, newCompanyName);
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
        }
    }

    private void setDirCompanyNameChangeHistory(String sourceDirName, String targetDirName, String oldCompanyName, String newCompanyName) {
        VendorDirNameChangeLogEntity trm = VendorDirNameChangeLogEntity.create(sourceDirName, targetDirName, oldCompanyName, newCompanyName);
        try {
            vendorDirNameChangeLogEntityRepository.save(trm);
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
        }
    }

    private Integer updateVendorBasicInfo(VendorDetailInfo requestVendorDetailInfo) {

        WholeSalerEntity wholeSaler = vendorWholeSalerEntityRepository.findOneByID(requestVendorDetailInfo.getWholeSalerID());
        try {
            if (!wholeSaler.getUserId().equals(requestVendorDetailInfo.getUserId())) {
                if (checkDupAndCreateUserInfo(wholeSaler, requestVendorDetailInfo)) return 97;
            }
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return 98;
        }

        setDirCompanyNameChangeHistory(requestVendorDetailInfo, wholeSaler);

        wholeSaler.setFirstName(requestVendorDetailInfo.getFirstName());
        wholeSaler.setLastName(requestVendorDetailInfo.getLastName());
        wholeSaler.setCompanyName(requestVendorDetailInfo.getCompanyName());
        wholeSaler.setUserId(requestVendorDetailInfo.getUserId());
        wholeSaler.setConsolidationYN(requestVendorDetailInfo.getConsolidationYN());
        wholeSaler.setRegCompanyName(requestVendorDetailInfo.getRegCompanyName());
        wholeSaler.setEmail(requestVendorDetailInfo.getEmail());

        if (!requestVendorDetailInfo.getActive() && requestVendorDetailInfo.getCompanyTypeID() == null) {
            requestVendorDetailInfo.setCompanyTypeID(0); // if inactive, company type id is not required. (default 0)
        } else {
            wholeSaler.setCompanyTypeID(requestVendorDetailInfo.getCompanyTypeID());
        }

        wholeSaler.setBusinessCategory(requestVendorDetailInfo.getBusinessCategory());
        wholeSaler.setEstablishedYear(requestVendorDetailInfo.getEstablishedYear());
        wholeSaler.setWebSite(requestVendorDetailInfo.getWebSite());
        wholeSaler.setIndustryType(requestVendorDetailInfo.getIndustryType());
        wholeSaler.setDescription(requestVendorDetailInfo.getDescription());

        wholeSaler.setBillStreetNo(requestVendorDetailInfo.getBillStreetNo());
        wholeSaler.setBillStreetNo2(requestVendorDetailInfo.getBillStreetNo2());
        wholeSaler.setBillCity(requestVendorDetailInfo.getBillCity());
        wholeSaler.setBillState(requestVendorDetailInfo.getBillState());
        wholeSaler.setBillZipcode(requestVendorDetailInfo.getBillZipcode());
        wholeSaler.setBillCountry(requestVendorDetailInfo.getBillCountry());
        wholeSaler.setBillPhone(requestVendorDetailInfo.getBillPhone());
        wholeSaler.setBillFax(requestVendorDetailInfo.getBillFax());
        wholeSaler.setStreetNo(requestVendorDetailInfo.getStreetNo());
        wholeSaler.setStreetNo2(requestVendorDetailInfo.getStreetNo2());
        wholeSaler.setCity(requestVendorDetailInfo.getCity());
        wholeSaler.setState(requestVendorDetailInfo.getState());
        wholeSaler.setZipcode(requestVendorDetailInfo.getZipcode());
        wholeSaler.setCountry(requestVendorDetailInfo.getCountry());
        wholeSaler.setPhone(requestVendorDetailInfo.getPhone());
        wholeSaler.setFax(requestVendorDetailInfo.getFax());

        wholeSaler.setMemo(requestVendorDetailInfo.getMemo());
        wholeSaler.setInHouseMemo(requestVendorDetailInfo.getInHouseMemo());
        wholeSaler.setOrderNotice(requestVendorDetailInfo.getOrderNotice());
        wholeSaler.setNoticeToAll(requestVendorDetailInfo.getNoticeToAll());
        wholeSaler.setSourceType(requestVendorDetailInfo.getSourceType());
        wholeSaler.setLastModifiedDateTime(new Timestamp(System.currentTimeMillis()));
        wholeSaler.setLastUser(Utility.getUsername());

        vendorWholeSalerEntityRepository.save(wholeSaler);

        vendorInfoNewService.update(requestVendorDetailInfo, wholeSaler.getUserId(), Utility.getUserInfo().getUserId(), Utility.getUserInfo().getUsername());

        return 1;
    }

    private boolean checkDupAndCreateUserInfo(WholeSalerEntity wholeSaler, VendorDetailInfo requestVendorDetailInfo) {
        AspnetUsersEntity aspUserDuplicateCheck = aspnetUsersEntityRepository.findOneByUserNameAndWholeSalerGUID(requestVendorDetailInfo.getUserId(), wholeSaler.getWholeSalerGUID());
        if (aspUserDuplicateCheck != null) {
            return true;
        }

        AspnetUsersEntity aspUser = aspnetUsersEntityRepository.findOneByWholeSalerGUID(wholeSaler.getWholeSalerGUID());
        aspUser.setUserName(requestVendorDetailInfo.getUserId());
        aspUser.setLoweredUserName(requestVendorDetailInfo.getUserId().toLowerCase());
        aspnetUsersEntityRepository.save(aspUser);
        return false;
    }

    private void updateAccountOfBillingSystem(VendorDetailInfo requestVendorDetailInfo) {
        final String spname = "up_Setting_Account";
        List<Object> params = new ArrayList<>();
        params.add(requestVendorDetailInfo.getWholeSalerID());
        params.add(Utility.getUsername());
        jdbcHelperFgBilling.executeSP(spname, params);
    }

    private void checkAndRecordCommissionInfoHistory(WholeSalerEntity wholeSaler, VendorDetailInfo requestVendorDetailInfo) {
        if(!Objects.equals(wholeSaler.getTransactionFeeRate1(), requestVendorDetailInfo.getTransactionFeeRate1())
                || !Objects.equals(wholeSaler.getTransactionFeeRate2(), requestVendorDetailInfo.getTransactionFeeRate2())
                || !Objects.equals(wholeSaler.getTransactionFeeRate1Intl(), requestVendorDetailInfo.getTransactionFeeRate1Intl())
                || !Objects.equals(wholeSaler.getTransactionFeeRate2Intl(), requestVendorDetailInfo.getTransactionFeeRate2Intl())
                || !Objects.equals(wholeSaler.getTransactionFeeFixed(), requestVendorDetailInfo.getTransactionFeeFixed())
                || !Objects.equals(wholeSaler.getCommissionRate(), requestVendorDetailInfo.getCommissionRate())) {

            String logDetail = "TransactionFeeRate1 = " + requestVendorDetailInfo.getTransactionFeeRate1() + ",TransactionFeeRate2 = " + requestVendorDetailInfo.getTransactionFeeRate2() +
                    ",TransactionFeeRate1Intl = " + requestVendorDetailInfo.getTransactionFeeRate1Intl() + ",TransactionFeeRate2Intl = " + requestVendorDetailInfo.getTransactionFeeRate2Intl() +
                    ",TransactionFeeFixed = " + requestVendorDetailInfo.getTransactionFeeFixed() + ",CommissionRate = " + requestVendorDetailInfo.getCommissionRate();
            setEntityActionLogDetail(1, requestVendorDetailInfo.getWholeSalerID(), 3004, logDetail);
        }
    }

    private void setVendorCapInfo(SetVendorSettingParameter request) {
        Integer wid = request.getWid();

        Integer adminAccount = Optional.ofNullable(request.getAdminAccount()).orElse(0);
        Integer vendorCategory = Optional.ofNullable(request.getVendorCategory()).orElse(0);
        Integer fraudReport = Optional.ofNullable(request.getFraudReport()).orElse(0);
        Integer item = Optional.ofNullable(request.getItem()).orElse(0);

        Integer adminAccountID = Optional.ofNullable(request.getAdminAccountID()).orElse(0);
        Integer vendorCategoryID = Optional.ofNullable(request.getVendorCategoryID()).orElse(0);
        Integer fraudReportID = Optional.ofNullable(request.getFraudReportID()).orElse(0);
        Integer itemID = Optional.ofNullable(request.getItemID()).orElse(0);

        insertOrUpdateVendorCapInfo(wid, adminAccountID, 1, adminAccount);
        insertOrUpdateVendorCapInfo(wid, vendorCategoryID, 2, vendorCategory);
        insertOrUpdateVendorCapInfo(wid, fraudReportID, 3, fraudReport);
        insertOrUpdateVendorCapInfo(wid, itemID, 4, item);
    }

    private Integer updateVendorSettingInfo(SetVendorSettingParameter request, VendorDetailInfo requestVendorDetailInfo) {

        String sessionUserId = Utility.getUsername();
        try {
            setVendorCapInfo(request);

            WholeSalerEntity wholeSaler = vendorWholeSalerEntityRepository.findOneByID(requestVendorDetailInfo.getWholeSalerID());

            if (!checkAndRecordDirCompanyNameChangeHistory(wholeSaler, requestVendorDetailInfo)) return -1;

            checkAndRecordVendorStatusChangeHistory(wholeSaler, requestVendorDetailInfo);
            checkAndRecordCommissionInfoHistory(wholeSaler, requestVendorDetailInfo);

            if (requestVendorDetailInfo.getShopActive() && !wholeSaler.getShopActive())
                updateMembershipStatus(wholeSaler);

            if (wholeSaler.getActive() && !requestVendorDetailInfo.getActive())
                inactiveTodayDealInfo(requestVendorDetailInfo, sessionUserId);

            if (!requestVendorDetailInfo.getActive())
                updateAdminAccountStatus(requestVendorDetailInfo);

            wholeSaler.setActive(requestVendorDetailInfo.getActive());
            wholeSaler.setShopActive(requestVendorDetailInfo.getShopActive());
            wholeSaler.setOrderActive(requestVendorDetailInfo.getOrderActive());

            wholeSaler.setDirName(requestVendorDetailInfo.getDirName());
            wholeSaler.setCodeName(requestVendorDetailInfo.getCodeName());
            wholeSaler.setLastModifiedDateTime(new Timestamp(System.currentTimeMillis()));
            wholeSaler.setLastUser(sessionUserId);

            wholeSaler.setNewCustYN(requestVendorDetailInfo.getNewCustYN());
            wholeSaler.setIsADBlock(requestVendorDetailInfo.getIsADBlock());

            if (requestVendorDetailInfo.getOrderActive()) {
                setVendorNewVendorAdVendorItemAdd(requestVendorDetailInfo.getWholeSalerID(), sessionUserId);
                if (wholeSaler.getActualOpenDate() == null) {
                    wholeSaler.setActualOpenDate(Timestamp.valueOf(LocalDateTime.now()));
                    setEntityActionLog(1, requestVendorDetailInfo.getWholeSalerID(), 3001);
                    wholeSaler.setContractExpireDate(null);
                } else {
                    String actualOpenDateTest = wholeSaler.getActualOpenDate() != null ? wholeSaler.getActualOpenDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd")) : "0";
                    String dateTimeNowTest = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                    int actualOpenDateTestInt = Integer.parseInt(actualOpenDateTest);
                    int dateTimeNowTestInt = Integer.parseInt(dateTimeNowTest);

                    if (actualOpenDateTestInt > dateTimeNowTestInt) {
                        wholeSaler.setActualOpenDate(Timestamp.valueOf(LocalDateTime.now()));
                        setEntityActionLog(1, requestVendorDetailInfo.getWholeSalerID(), 3001);
                        wholeSaler.setContractExpireDate(null);
                    }
                }
            } else if (!requestVendorDetailInfo.getOrderActive() && requestVendorDetailInfo.getActualOpenDate() != null) {

                String requestActualOpenDate = requestVendorDetailInfo.getActualOpenDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String vendorActualOpenDate = wholeSaler.getActualOpenDate() != null ? wholeSaler.getActualOpenDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
                String dateTimeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if(!requestActualOpenDate.equals(vendorActualOpenDate)) {
                    if (requestActualOpenDate.equals(dateTimeNow)) {
                        setVendorNewVendorAdVendorItemAdd(requestVendorDetailInfo.getWholeSalerID(), sessionUserId);

                        wholeSaler.setActualOpenDate(Timestamp.valueOf(LocalDateTime.now()));
                        wholeSaler.setOrderActive(true);
                        wholeSaler.setShopActive(true);
                        wholeSaler.setActive(true);

                        setEntityActionLog(1, requestVendorDetailInfo.getWholeSalerID(), 3001);
                        wholeSaler.setContractExpireDate(null);
                    } else {
                        wholeSaler.setActualOpenDate(Timestamp.valueOf(requestVendorDetailInfo.getActualOpenDate()));
                    }
                }
            }

            wholeSaler.setTransactionFeeRate1(requestVendorDetailInfo.getTransactionFeeRate1());
            wholeSaler.setTransactionFeeRate2(requestVendorDetailInfo.getTransactionFeeRate2());
            wholeSaler.setTransactionFeeRate1Intl(requestVendorDetailInfo.getTransactionFeeRate1Intl());
            wholeSaler.setTransactionFeeRate2Intl(requestVendorDetailInfo.getTransactionFeeRate2Intl());
            wholeSaler.setTransactionFeeFixed(requestVendorDetailInfo.getTransactionFeeFixed());
            wholeSaler.setCommissionRate(requestVendorDetailInfo.getCommissionRate());

            try {
                int oldPaymentMethodId, newPaymentMethodId;
                if (requestVendorDetailInfo.getUseCreditCardPaymentService()) {
                    oldPaymentMethodId = 100;
                    newPaymentMethodId = 6;
                } else {
                    oldPaymentMethodId = 6;
                    newPaymentMethodId = 100;
                }
                switchPaymentMethodId(requestVendorDetailInfo.getWholeSalerID(), oldPaymentMethodId, newPaymentMethodId);

                wholeSaler.setUseCreditCardPaymentService(requestVendorDetailInfo.getUseCreditCardPaymentService());
                if (request.getPayoutCount() > 0) {
                    updateVendorPayoutInfo(requestVendorDetailInfo, request.getPayoutSchedule(), request.getPayoutScheduleWM(), request.getMaxPayoutPerDay(), sessionUserId);
                }
            } catch (Exception ex) {
                log.warn(ex.getMessage(), ex);
            }
            wholeSaler.setChargedByCreditCard(requestVendorDetailInfo.getChargedByCreditCard());

            vendorWholeSalerEntityRepository.save(wholeSaler);
            vendorInfoNewService.updateDetailInfo(request, requestVendorDetailInfo, Utility.getUserInfo().getUserId(), Utility.getUserInfo().getUsername());

            return 1;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return -99;
        }
    }

    private void updateVendorPayoutInfo(VendorDetailInfo requestVendorDetailInfo, Integer payoutSchedule, Integer payoutScheduleWM, Integer maxPayoutPerDay, String sessionUsrId) {
        VendorPayoutInfoEntity vp = vendorPayoutInfoEntityRepository.findOneByWholeSalerID(requestVendorDetailInfo.getWholeSalerID());
        vp.setPayoutSchedule(payoutSchedule);
        vp.setMaxPayoutPerDay(maxPayoutPerDay);
        vp.setModifiedBy(sessionUsrId);
        if (payoutSchedule == 1 || payoutSchedule == 2) {
            vp.setWeekday(null);
            vp.setDayOfMonth(null);
        } else if (payoutSchedule == 3) {
            vp.setWeekday(payoutScheduleWM);
            vp.setDayOfMonth(null);
        } else if (payoutSchedule == 4) {
            vp.setWeekday(null);
            vp.setDayOfMonth(payoutScheduleWM);
        }
        vendorPayoutInfoEntityRepository.save(vp);
    }

    private void switchPaymentMethodId(Integer vendorId, int oldPaymentMethodId, int newPaymentMethodId) {
        MapWholeSalerPaymentMethodEntity mapWholeSalerPaymentMethod;
        mapWholeSalerPaymentMethod = mapWholeSalerPaymentMethodEntityRepository.findOneByWholeSalerIDAndPaymentMethodID(vendorId, oldPaymentMethodId);
        if (mapWholeSalerPaymentMethod != null) {
            mapWholeSalerPaymentMethod.setPaymentMethodID(newPaymentMethodId);
            mapWholeSalerPaymentMethodEntityRepository.save(mapWholeSalerPaymentMethod);

            vendorPaymentInfoNewService.switchPaymentMethodId(vendorId, oldPaymentMethodId, newPaymentMethodId, Utility.getUserInfo().getUserId(), Utility.getUserInfo().getUsername());
        }
    }

    private void updateAdminAccountStatus(VendorDetailInfo requestVendorDetailInfo) {
        List<VendorAdminAccountEntity> vendorAdminAccountList = vendorAdminAccountEntityRepository.findAllByWholeSalerID(requestVendorDetailInfo.getWholeSalerID());
        for (VendorAdminAccountEntity va : vendorAdminAccountList) {
            AspnetMembershipEntity subAccount = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(va.getUserGUID());
            subAccount.setApproved(false);
            subAccount.setLockedOut(true);
            aspnetMembershipEntityRepository.save(subAccount);

            VendorAdminAccountEntity subAccount1 = vendorAdminAccountEntityRepository.findOneByVendorAdminAccountID(va.getVendorAdminAccountID());
            subAccount1.setActive(false);
            vendorAdminAccountEntityRepository.save(subAccount1);
        }
    }

    private void inactiveTodayDealInfo(VendorDetailInfo requestVendorDetailInfo, String sessionUsrId) {
        List<TodayDealEntity> todayDealList = todayDealEntityRepository.findAllByWholeSalerID(requestVendorDetailInfo.getWholeSalerID());
        List<TodayDealEntity> todayDealListUpdate = new ArrayList<>();
        for (TodayDealEntity todayDeal : todayDealList) {
            todayDeal.setActive(false);
            todayDeal.setModifiedOn(Timestamp.valueOf(LocalDateTime.now()));
            todayDeal.setModifiedBy(sessionUsrId);

            todayDealListUpdate.add(todayDeal);
        }
        todayDealEntityRepository.saveAll(todayDealListUpdate);
    }

    private void updateMembershipStatus(WholeSalerEntity wholeSaler) {
        AspnetMembershipEntity membership = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(wholeSaler.getWholeSalerGUID());
        membership.setApproved(true);
        aspnetMembershipEntityRepository.save(membership);
    }

    private void checkAndRecordVendorStatusChangeHistory(WholeSalerEntity wholeSaler, VendorDetailInfo requestVendorDetailInfo) {


        if (requestVendorDetailInfo.getOrderActive() != wholeSaler.getOrderActive()
                || requestVendorDetailInfo.getShopActive() != wholeSaler.getShopActive()
                || requestVendorDetailInfo.getActive() != wholeSaler.getActive()) {

            String logDetail = "Active = " + requestVendorDetailInfo.getActive() + ",ShopActive = " + requestVendorDetailInfo.getShopActive() + ",OrderActive = " + requestVendorDetailInfo.getOrderActive();
            setEntityActionLogDetail(1, requestVendorDetailInfo.getWholeSalerID(), 3003, logDetail);
        }

        try {
            recordVendorStatusChangeLogWithContract(wholeSaler, requestVendorDetailInfo);
        } catch (Throwable t) {
            /* ignore exception */
            log.warn("fail to record a log message.", t);
        }

    }

    // temporary logic (when this issue FGM-567 is checked, can be removed)
    private void recordVendorStatusChangeLogWithContract(WholeSalerEntity wholeSaler, VendorDetailInfo requestVendorDetailInfo) {
        String requestActualOpenDate = Optional.ofNullable(requestVendorDetailInfo.getActualOpenDate())
                .map(o -> o.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).orElse(null);
        String dateTimeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String openType = null;
        String openDate = null;
        if (!wholeSaler.getOrderActive() && requestVendorDetailInfo.getOrderActive()) {
            openType = "now";
            openDate = dateTimeNow;
        } else if (!wholeSaler.getOrderActive() && !requestVendorDetailInfo.getOrderActive()) {
            if (StringUtils.compare(requestActualOpenDate, dateTimeNow) > 0) {
                openType = "schedule";
                openDate = requestActualOpenDate;
            } else if (StringUtils.compare(requestActualOpenDate, dateTimeNow) == 0) {
                openType = "now";
                openDate = dateTimeNow;
            }
        } else {
            return;
        }

        VendorContractEntity vendorContract = vendorContractService.getVendorContractIncludedOpenDate(wholeSaler.getWholeSalerID(), requestVendorDetailInfo.getActualOpenDate());
        String message = String.format("[change-to-orderactive] vendor-id:%s,open-type:%s,open-date:%s,valid-contract-id:%s"
                , wholeSaler.getWholeSalerID(), openType, openDate, Optional.ofNullable(vendorContract).map(VendorContractEntity::getVendorContractID).orElse(null));
        logger.info(message);
    }

    private boolean checkAndRecordDirCompanyNameChangeHistory(WholeSalerEntity wholeSaler, VendorDetailInfo requestVendorDetailInfo) {

        String currentDirname = wholeSaler.getDirName();
        String newDirname = requestVendorDetailInfo.getDirName();
        if(StringUtils.isEmpty(currentDirname) || StringUtils.isEmpty(newDirname) || currentDirname.equals(newDirname)) {
            return true;
        }

        JsonResponse retVal = cacheService.GetRedisCacheEvict_ChangeDirName(currentDirname, newDirname);
        if (!retVal.isSuccess() && Arrays.asList(env.getActiveProfiles()).contains("toast")) {
            return false;
        }
        setDirCompanyNameChangeHistory(currentDirname, newDirname, requestVendorDetailInfo.getCompanyName(), requestVendorDetailInfo.getCompanyName());
        return true;
    }

    private int setEntityActionLogDetail(Integer entityTypeID, Integer wholeSalerID, Integer actionID, String detailLog) {
        try {
            EntityActionLogEntity actionLog = EntityActionLogEntity.create(entityTypeID, wholeSalerID, actionID, detailLog);
            entityActionLogEntityRepository.save(actionLog);
            return 1;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return -99;
        }
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public int setEntityActionLog(Integer entityTypeID, Integer wholeSalerID, Integer actionID) {
        try {
            EntityActionLogEntity actionLog = EntityActionLogEntity.create(entityTypeID, wholeSalerID, actionID);
            entityActionLogEntityRepository.save(actionLog);
            return 1;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return -99;
        }
    }

    private void insertOrUpdateVendorCapInfo(Integer wid, Integer capID, Integer vendorCapTypeID, Integer cap) {
        VendorCapEntity trm = new VendorCapEntity();
        try {
            if (capID == 0) {
                trm.setWholeSalerID(wid);
                trm.setVendorCapTypeID(vendorCapTypeID);
                trm.setCap(cap);
                trm.setCreatedBy(Utility.getUsername());
                trm.setCreatedOn(LocalDateTime.now());
                trm.setModifiedBy(Utility.getUsername());
                trm.setModifiedOn(LocalDateTime.now());

                vendorCapEntityRepository.save(trm);
            } else if (capID > 0) {
                trm = vendorCapEntityRepository.findOneByVendorCapID(capID);
                trm.setCap(cap);
                trm.setModifiedBy(Utility.getUsername());
                trm.setModifiedOn(LocalDateTime.now());

                vendorCapEntityRepository.save(trm);
            }
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
        }
    }

    private void setVendorNewVendorAdVendorItemAdd(Integer wholeSalerId, String sessionUsrID) {
        long count = adVendorItemEntityRepository.getCountByWholeSalerID(wholeSalerId);

        if (count == 0) {
            AdVendorItemEntity item = new AdVendorItemEntity();

            item.setCategoryID(-10);
            item.setWholeSalerID(wholeSalerId);
            item.setFromDate(LocalDateTime.now());
            item.setToDate(LocalDateTime.parse("9999-12-31 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            item.setCreatedOn(LocalDateTime.now());
            item.setCreatedBy(sessionUsrID);
            item.setHowToInput(1);
            item.setHowToSell(1);
            item.setItemCount(10);
            item.setActualPrice(BigDecimal.valueOf(0.00));

            adVendorItemEntityRepository.save(item);
        }
    }
}
