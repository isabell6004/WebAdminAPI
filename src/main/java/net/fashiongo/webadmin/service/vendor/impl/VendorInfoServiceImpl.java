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
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.VendorInfoNewService;
import net.fashiongo.webadmin.service.vendor.VendorInfoService;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    private CacheService cacheService;

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
        this.cacheService = cacheService;
        this.jdbcHelperFgBilling = jdbcHelperFgBilling;
        this.env = env;
    }

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Integer update(SetVendorBasicInfoParameter request) {

        VendorDetailInfo vendorDetailInfo;
        try {
            ObjectMapper mapper = new ObjectMapper();
            vendorDetailInfo = mapper.readValue(request.getVendorBasicInfo(), VendorDetailInfo.class);
        } catch (IOException e) {
            log.error("object mapper parse error", e);
            return null;
        }

        WholeSalerEntity wholeSaler = vendorWholeSalerEntityRepository.findOneByID(vendorDetailInfo.getWholeSalerID());
        String originalUserId = wholeSaler.getUserId();
        Integer result = setVendorBasicInfo(wholeSaler, vendorDetailInfo, 1, null, null, null, 0);
        if (result == 1) {
            WebAdminLoginUser userInfo = Utility.getUserInfo();
            vendorInfoNewService.update(vendorDetailInfo, originalUserId, userInfo.getUserId(), userInfo.getUsername());

            setDirCompanyNameChangeHistory(vendorDetailInfo, request.getCompanyNameTemp());
            cacheService.cacheEvictVendor(request.getWid());
        }

        return result;
    }

    private void setDirCompanyNameChangeHistory(VendorDetailInfo vendorDetailInfo, String oldCompanyName) {

        String oldCompanyNameTemp = Optional.ofNullable(oldCompanyName).orElse("");
        String newCompanyName = vendorDetailInfo.getCompanyName();
        if(StringUtils.isEmpty(oldCompanyNameTemp) || StringUtils.isEmpty(newCompanyName) || StringUtils.equals(oldCompanyNameTemp, newCompanyName)) {
            return;
        }

        String dirName = Optional.ofNullable(vendorDetailInfo.getDirName()).orElse("");
        try {
            setDirCompanyNameChangeHistory(dirName, dirName, oldCompanyNameTemp, newCompanyName);
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

    private Integer setVendorBasicInfo(WholeSalerEntity wholeSaler, VendorDetailInfo requestVendorDetailInfo, Integer saveType, Integer payoutSchedule, Integer payoutScheduleWM, Integer maxPayoutPerDay, Integer payoutCount) {
        Integer result;
        String sessionUsrId = Utility.getUsername();

        try {
            if (saveType == 1) {
                try {
                    if (!wholeSaler.getUserId().equals(requestVendorDetailInfo.getUserId())) {
                        AspnetUsersEntity aspUserDuplicateCheck = aspnetUsersEntityRepository.findOneByUserNameAndWholeSalerGUID(requestVendorDetailInfo.getUserId(), wholeSaler.getWholeSalerGUID());
                        if (aspUserDuplicateCheck == null) {
                            AspnetUsersEntity aspUser = aspnetUsersEntityRepository.findOneByWholeSalerGUID(wholeSaler.getWholeSalerGUID());
                            aspUser.setUserName(requestVendorDetailInfo.getUserId());
                            aspUser.setLoweredUserName(requestVendorDetailInfo.getUserId().toLowerCase());
                            aspnetUsersEntityRepository.save(aspUser);
                        } else {
                            return 97;
                        }
                    }
                } catch (Exception ex) {
                    return 98;
                }
            }

            if (StringUtils.isNotEmpty(wholeSaler.getDirName()) && StringUtils.isNotEmpty(requestVendorDetailInfo.getDirName())) {
                if (!wholeSaler.getDirName().equals(requestVendorDetailInfo.getDirName())) {
                    JsonResponse retVal = cacheService.GetRedisCacheEvict_ChangeDirName(wholeSaler.getDirName(), requestVendorDetailInfo.getDirName());

                    if (!retVal.isSuccess() && Arrays.asList(env.getActiveProfiles()).contains("toast")) {
                        return -1;
                    }
                    setDirCompanyNameChangeHistory(wholeSaler.getDirName(), requestVendorDetailInfo.getDirName(), requestVendorDetailInfo.getCompanyName(), requestVendorDetailInfo.getCompanyName());
                }
            }

            wholeSaler.setFirstName(requestVendorDetailInfo.getFirstName());
            wholeSaler.setLastName(requestVendorDetailInfo.getLastName());
            wholeSaler.setCompanyName(requestVendorDetailInfo.getCompanyName());

            wholeSaler.setUserId(requestVendorDetailInfo.getUserId());
            wholeSaler.setRegCompanyName(requestVendorDetailInfo.getRegCompanyName());
            wholeSaler.setEmail(requestVendorDetailInfo.getEmail());
            wholeSaler.setCompanyTypeID(requestVendorDetailInfo.getCompanyTypeID());
            wholeSaler.setBusinessCategory(requestVendorDetailInfo.getBusinessCategory());
            wholeSaler.setEstablishedYear(requestVendorDetailInfo.getEstablishedYear());
            wholeSaler.setWebSite(requestVendorDetailInfo.getWebSite());
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

            if (saveType == 2) {
                if (requestVendorDetailInfo.getOrderActive() != wholeSaler.getOrderActive() || requestVendorDetailInfo.getShopActive() != wholeSaler.getShopActive() || requestVendorDetailInfo.getActive() != wholeSaler.getActive()) {
                    String logDetail = "Active = " + requestVendorDetailInfo.getActive() + ",ShopActive = " + requestVendorDetailInfo.getShopActive() + ",OrderActive = " + requestVendorDetailInfo.getOrderActive();
                    setEntityActionLogDetail(1, requestVendorDetailInfo.getWholeSalerID(), 3003, logDetail);
                }
                if (wholeSaler.getTransactionFeeRate1() != requestVendorDetailInfo.getTransactionFeeRate1() || wholeSaler.getTransactionFeeRate2() != requestVendorDetailInfo.getTransactionFeeRate2()
                        || wholeSaler.getTransactionFeeRate1Intl() != requestVendorDetailInfo.getTransactionFeeRate1Intl() || wholeSaler.getTransactionFeeRate2Intl() != requestVendorDetailInfo.getTransactionFeeRate2Intl()
                        || wholeSaler.getTransactionFeeFixed() != requestVendorDetailInfo.getTransactionFeeFixed() || wholeSaler.getCommissionRate() != requestVendorDetailInfo.getCommissionRate()) {
                    String logDetail = "TransactionFeeRate1 = " + requestVendorDetailInfo.getTransactionFeeRate1() + ",TransactionFeeRate2 = " + requestVendorDetailInfo.getTransactionFeeRate2() +
                            ",TransactionFeeRate1Intl = " + requestVendorDetailInfo.getTransactionFeeRate1Intl() + ",TransactionFeeRate2Intl = " + requestVendorDetailInfo.getTransactionFeeRate2Intl() +
                            ",TransactionFeeFixed = " + requestVendorDetailInfo.getTransactionFeeFixed() + ",CommissionRate = " + requestVendorDetailInfo.getCommissionRate();
                    setEntityActionLogDetail(1, requestVendorDetailInfo.getWholeSalerID(), 3004, logDetail);
                }
                if (wholeSaler.getShopActive() != requestVendorDetailInfo.getShopActive() && requestVendorDetailInfo.getShopActive()) {
                    AspnetMembershipEntity membership = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(wholeSaler.getWholeSalerGUID());
                    membership.setApproved(true);
                    aspnetMembershipEntityRepository.save(membership);
                }
            }

            if (saveType == 2 && wholeSaler.getActive() && !requestVendorDetailInfo.getActive()) {
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

            wholeSaler.setActive(requestVendorDetailInfo.getActive());
            wholeSaler.setShopActive(requestVendorDetailInfo.getShopActive());
            wholeSaler.setOrderActive(requestVendorDetailInfo.getOrderActive());
            wholeSaler.setImageServerID(requestVendorDetailInfo.getImageServerID());
            wholeSaler.setDirName(requestVendorDetailInfo.getDirName());
            wholeSaler.setAdminWebServerID(requestVendorDetailInfo.getAdminWebServerID());
            wholeSaler.setCodeName(requestVendorDetailInfo.getCodeName());
            wholeSaler.setLastModifiedDateTime(new Timestamp(System.currentTimeMillis()));
            wholeSaler.setLastUser(sessionUsrId);
            wholeSaler.setMemo(requestVendorDetailInfo.getMemo());
            wholeSaler.setInHouseMemo(requestVendorDetailInfo.getInHouseMemo());
            wholeSaler.setOrderNotice(requestVendorDetailInfo.getOrderNotice());
            wholeSaler.setNoticeToAll(requestVendorDetailInfo.getNoticeToAll());
            wholeSaler.setSourceType(requestVendorDetailInfo.getSourceType());

            if (saveType == 2) {
                wholeSaler.setNewCustYN(requestVendorDetailInfo.getNewCustYN());
                wholeSaler.setIsADBlock(requestVendorDetailInfo.getIsADBlock());

                if (requestVendorDetailInfo.getOrderActive()) {
                    setVendorNewVendorAdVendorItemAdd(requestVendorDetailInfo.getWholeSalerID(), sessionUsrId);
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
                } else if (!requestVendorDetailInfo.getOrderActive() && wholeSaler.getActualOpenDate() == null) {
                    if (requestVendorDetailInfo.getActualOpenDate() != null) {
                        String actualOpenDateTest = requestVendorDetailInfo.getActualOpenDate() != null ? requestVendorDetailInfo.getActualOpenDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
                        String dateTimeNowTest = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        if (actualOpenDateTest.equals(dateTimeNowTest)) {
                            setVendorNewVendorAdVendorItemAdd(requestVendorDetailInfo.getWholeSalerID(), sessionUsrId);

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
                } else if (!requestVendorDetailInfo.getOrderActive() && wholeSaler.getActualOpenDate() != null) {
                    String actualOpenDateTest2 = requestVendorDetailInfo.getActualOpenDate() != null ? requestVendorDetailInfo.getActualOpenDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
                    String actualOpenDateTest3 = wholeSaler.getActualOpenDate() != null ? wholeSaler.getActualOpenDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";

                    if (requestVendorDetailInfo.getActualOpenDate() != null && !actualOpenDateTest2.equals(actualOpenDateTest3)) {
                        String actualOpenDateTest = requestVendorDetailInfo.getActualOpenDate() != null ? requestVendorDetailInfo.getActualOpenDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
                        String dateTimeNowTest = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                        if (actualOpenDateTest.equals(dateTimeNowTest)) {
                            setVendorNewVendorAdVendorItemAdd(requestVendorDetailInfo.getWholeSalerID(), sessionUsrId);

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

                try {
                    wholeSaler.setTransactionFeeRate1(requestVendorDetailInfo.getTransactionFeeRate1());
                    wholeSaler.setTransactionFeeRate2(requestVendorDetailInfo.getTransactionFeeRate2());
                    wholeSaler.setTransactionFeeRate1Intl(requestVendorDetailInfo.getTransactionFeeRate1Intl());
                    wholeSaler.setTransactionFeeRate2Intl(requestVendorDetailInfo.getTransactionFeeRate2Intl());
                    wholeSaler.setTransactionFeeFixed(requestVendorDetailInfo.getTransactionFeeFixed());
                    wholeSaler.setCommissionRate(requestVendorDetailInfo.getCommissionRate());

                    MapWholeSalerPaymentMethodEntity mapWholeSalerPaymentMethod = null;
                    if (requestVendorDetailInfo.getUseCreditCardPaymentService()) {
                        mapWholeSalerPaymentMethod = mapWholeSalerPaymentMethodEntityRepository.findOneByWholeSalerIDAndPaymentMethodID(requestVendorDetailInfo.getWholeSalerID(), 100);
                        if (mapWholeSalerPaymentMethod != null) {
                            mapWholeSalerPaymentMethod.setPaymentMethodID(6);
                            mapWholeSalerPaymentMethodEntityRepository.save(mapWholeSalerPaymentMethod);
                        }
                    } else {
                        mapWholeSalerPaymentMethod = mapWholeSalerPaymentMethodEntityRepository.findOneByWholeSalerIDAndPaymentMethodID(requestVendorDetailInfo.getWholeSalerID(), 6);
                        if (mapWholeSalerPaymentMethod != null) {
                            mapWholeSalerPaymentMethod.setPaymentMethodID(100);
                            mapWholeSalerPaymentMethodEntityRepository.save(mapWholeSalerPaymentMethod);
                        }
                    }

                    wholeSaler.setUseCreditCardPaymentService(requestVendorDetailInfo.getUseCreditCardPaymentService());
                    if (payoutCount > 0) {
                        if (payoutSchedule == 1 || payoutSchedule == 2) {
                            VendorPayoutInfoEntity vp = vendorPayoutInfoEntityRepository.findOneByWholeSalerID(requestVendorDetailInfo.getWholeSalerID());
                            vp.setPayoutSchedule(payoutSchedule);
                            vp.setMaxPayoutPerDay(maxPayoutPerDay);
                            vp.setModifiedBy(sessionUsrId);
                            vp.setWeekday(null);
                            vp.setDayOfMonth(null);
                            vendorPayoutInfoEntityRepository.save(vp);

                        } else if (payoutSchedule == 3) {
                            VendorPayoutInfoEntity vp = vendorPayoutInfoEntityRepository.findOneByWholeSalerID(requestVendorDetailInfo.getWholeSalerID());
                            vp.setPayoutSchedule(payoutSchedule);
                            vp.setMaxPayoutPerDay(maxPayoutPerDay);
                            vp.setModifiedBy(sessionUsrId);
                            vp.setWeekday(payoutScheduleWM);
                            vp.setDayOfMonth(null);
                            vendorPayoutInfoEntityRepository.save(vp);
                        } else if (payoutSchedule == 4) {
                            VendorPayoutInfoEntity vp = vendorPayoutInfoEntityRepository.findOneByWholeSalerID(requestVendorDetailInfo.getWholeSalerID());
                            vp.setPayoutSchedule(payoutSchedule);
                            vp.setMaxPayoutPerDay(maxPayoutPerDay);
                            vp.setModifiedBy(sessionUsrId);
                            vp.setWeekday(null);
                            vp.setDayOfMonth(payoutScheduleWM);
                            vendorPayoutInfoEntityRepository.save(vp);
                        }
                    }
                } catch (Exception ex) {
                }
            }

            wholeSaler.setIndustryType(requestVendorDetailInfo.getIndustryType());
            wholeSaler.setConsolidationYN(requestVendorDetailInfo.getConsolidationYN());
            wholeSaler.setChargedByCreditCard(requestVendorDetailInfo.getChargedByCreditCard());
            vendorWholeSalerEntityRepository.save(wholeSaler);

            if (!requestVendorDetailInfo.getActive()) {
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

            result = 1;


        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return -99;
        }

        try {
            if (result == 1 && saveType == 1) {
                String spname = "up_Setting_Account";
                List<Object> params = new ArrayList<>();
                params.add(requestVendorDetailInfo.getWholeSalerID());
                params.add(sessionUsrId);
                jdbcHelperFgBilling.executeSP(spname, params);
            }
        } catch (Exception ex) {
        }

        return result;
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

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Integer setVendorSettingInfo(SetVendorSettingParameter request) {

        Integer wid = request.getWid();
        Integer adminAccount = request.getAdminAccount() == null ? 0 : request.getAdminAccount();
        Integer vendorCategory = request.getVendorCategory() == null ? 0 : request.getVendorCategory();
        Integer fraudReport = request.getFraudReport() == null ? 0 : request.getFraudReport();
        Integer item = request.getItem() == null ? 0 : request.getItem();
        Integer adminAccountID = request.getAdminAccountID() == null ? 0 : request.getAdminAccountID();
        Integer vendorCategoryID = request.getVendorCategoryID() == null ? 0 : request.getVendorCategoryID();
        Integer fraudReportID = request.getFraudReportID() == null ? 0 : request.getFraudReportID();
        Integer itemID = request.getItemID() == null ? 0 : request.getItemID();

        setVendorSetting(wid, adminAccountID, 1, adminAccount);
        setVendorSetting(wid, vendorCategoryID, 2, vendorCategory);
        setVendorSetting(wid, fraudReportID, 3, fraudReport);
        setVendorSetting(wid, itemID, 4, item);

        Integer payoutSchedule = request.getPayoutSchedule();
        Integer payoutScheduleWM = request.getPayoutScheduleWM();
        Integer maxPayoutPerDay = request.getMaxPayoutPerDay();
        Integer payoutCount = request.getPayoutCount();

        ObjectMapper mapper = new ObjectMapper();
        VendorDetailInfo vendorDetailInfo;
        try {
            vendorDetailInfo = mapper.readValue(request.getVendorBasicInfo(), VendorDetailInfo.class);
        } catch (IOException e) {
            log.debug("object mapper parse error");
            return null;
        }

        WholeSalerEntity wholeSaler = vendorWholeSalerEntityRepository.findOneByID(vendorDetailInfo.getWholeSalerID());
        Integer result = setVendorBasicInfo(wholeSaler, vendorDetailInfo, 2, payoutSchedule, payoutScheduleWM, maxPayoutPerDay, payoutCount);
        if (result == -1) {
            return result;
        }

        cacheService.cacheEvictVendor(wid);

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        vendorInfoNewService.updateDetailInfo(request, vendorDetailInfo, userInfo.getUserId(), userInfo.getUsername());

        return result;
    }

    private void setVendorSetting(Integer wid, Integer capID, Integer vendorCapTypeID, Integer cap) {
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
