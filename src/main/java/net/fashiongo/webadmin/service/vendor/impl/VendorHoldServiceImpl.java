package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.LogVendorHoldEntity;
import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.repository.primary.LogVendorHoldEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorWholeSalerEntityRepository;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.vendor.StatusType;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.VendorHoldService;
import net.fashiongo.webadmin.service.vendor.VendorInfoNewService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by jinwoo on 2020-01-24.
 */
@Service
@Slf4j
public class VendorHoldServiceImpl implements VendorHoldService {

    private LogVendorHoldEntityRepository logVendorHoldEntityRepository;

    private VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;

    private VendorInfoNewService vendorInfoNewService;

    private CacheService cacheService;

    public VendorHoldServiceImpl(LogVendorHoldEntityRepository logVendorHoldEntityRepository,
                                 VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository,
                                 VendorInfoNewService vendorInfoNewService,
                                 CacheService cacheService) {
        this.logVendorHoldEntityRepository = logVendorHoldEntityRepository;
        this.vendorWholeSalerEntityRepository = vendorWholeSalerEntityRepository;
        this.vendorInfoNewService = vendorInfoNewService;
        this.cacheService = cacheService;
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Boolean setHoldVendor(Integer wholeSalerID, Integer holdType, Boolean active, Timestamp holdFrom, Timestamp holdTo) {
        try {
            if (holdType == HoldType.HOLD.gatValue()) {
                LogVendorHoldEntity trm = new LogVendorHoldEntity();
                trm.setWholeSalerID(wholeSalerID);
                trm.setHoldFrom(holdFrom);
                trm.setHoldTo(holdTo);
                trm.setActive(active);
                trm.setCreatedBy(Utility.getUsername());
                trm.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));

                logVendorHoldEntityRepository.save(trm);
            } else if (holdType == HoldType.CLOSE.gatValue()) {
                WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
                if (active) {
                    if (holdFrom.toLocalDateTime().plusDays(1).minusSeconds(1).isAfter(LocalDateTime.now())) {
                        trm.setContractExpireDate(Timestamp.valueOf(holdFrom.toLocalDateTime().plusDays(1).minusSeconds(1)));
                        trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
                    } else {
                        trm.setContractExpireDate(Timestamp.valueOf(LocalDateTime.now()));
                        trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
                        trm.setOrderActive(false);
                        trm.setShopActive(false);
                        trm.setActive(false);
                    }
                } else {
                    trm.setContractExpireDate(null);
                    trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
                    trm.setShopActive(true);
                    trm.setActive(true);
                }

                vendorWholeSalerEntityRepository.save(trm);

                LocalDateTime closeDate = null;
                if(trm.getContractExpireDate() != null)
                    closeDate = trm.getContractExpireDate().toLocalDateTime();

                WebAdminLoginUser userInfo = Utility.getUserInfo();
                StatusType newStatusType = StatusType.getStatusType(trm.getActive(), trm.getShopActive(), trm.getOrderActive());
                vendorInfoNewService.updateStatusAndCloseDate(wholeSalerID, newStatusType.getValue(), closeDate, userInfo.getUserId(), userInfo.getUsername());

            } else if (holdType == HoldType.HOLD_NOW.gatValue()) {
                WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
                trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
                trm.setOrderActive(false);
                trm.setShopActive(true);
                trm.setActive(true);
                vendorWholeSalerEntityRepository.save(trm);
                updateNewStatus(wholeSalerID, trm);
            } else if (holdType == HoldType.HOLD_RELEASE.gatValue()) {
                WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
                trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
                trm.setOrderActive(true);
                vendorWholeSalerEntityRepository.save(trm);
                updateNewStatus(wholeSalerID, trm);
            }

            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        } finally {
            cacheService.cacheEvictVendor(wholeSalerID);
        }
    }

    private void updateNewStatus(Integer wholeSalerID, WholeSalerEntity trm) {
        WebAdminLoginUser userInfo = Utility.getUserInfo();
        StatusType newStatusType = StatusType.getStatusType(trm.getActive(), trm.getShopActive(), trm.getOrderActive());
        vendorInfoNewService.updateStatus(wholeSalerID, newStatusType.getValue(), userInfo.getUserId(), userInfo.getUsername());
    }

    public Integer setHoldVendorUpdate(Integer wholeSalerID, Integer logID, Boolean active, Timestamp holdFrom, Timestamp holdTo) {

        try {
            LogVendorHoldEntity lvh = logVendorHoldEntityRepository.findById(logID).get();
            if(active) {
                lvh.setHoldFrom(holdFrom);
                lvh.setHoldTo(holdTo);
            }
            lvh.setActive(active);
            logVendorHoldEntityRepository.save(lvh);

            if(!active) {
                WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
                trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
                trm.setLastUser(Utility.getUsername());
                trm.setOrderActive(true);
                vendorWholeSalerEntityRepository.save(trm);

                updateNewStatus(wholeSalerID, trm);
            } else {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
                if (holdFrom.compareTo(timestamp) <= 0) {
                    trm.setOrderActive(false);
                    trm.setShopActive(true);
                    trm.setActive(true);
                } else {
                    trm.setOrderActive(true);
                }
                trm.setLastUser(Utility.getUsername());
                trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));

                vendorWholeSalerEntityRepository.save(trm);
                updateNewStatus(wholeSalerID, trm);
            }
            return 1;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return -99;
        } finally {
            cacheService.cacheEvictVendor(null);
        }
    }

    private enum HoldType {

        HOLD(1),
        CLOSE(2),
        HOLD_NOW(3),
        HOLD_RELEASE(4),
        ;

        HoldType(int value) {
            this.value = value;
        }

        private int value;

        public int gatValue() {
            return this.value;
        }
    }
}
