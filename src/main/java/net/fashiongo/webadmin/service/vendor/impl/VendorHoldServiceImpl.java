package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.LogVendorHoldEntity;
import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.repository.primary.LogVendorHoldEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorWholeSalerEntityRepository;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.vendor.StatusType;
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

    public VendorHoldServiceImpl(LogVendorHoldEntityRepository logVendorHoldEntityRepository,
                                 VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository,
                                 VendorInfoNewService vendorInfoNewService) {
        this.logVendorHoldEntityRepository = logVendorHoldEntityRepository;
        this.vendorWholeSalerEntityRepository = vendorWholeSalerEntityRepository;
        this.vendorInfoNewService = vendorInfoNewService;
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Boolean setHoldVendor(Integer wholeSalerID, Integer holdType, Boolean active, Timestamp holdFrom, Timestamp holdTo) {
        try {
            if (holdType == HoldType.HOLD.gatValue()) {
                LogVendorHoldEntity trm = new LogVendorHoldEntity();
                trm.setWholeSalerID(wholeSalerID);
                trm.setHoldFrom(holdFrom);
                trm.setHoldTo(holdTo);
                trm.setCreatedBy(Utility.getUsername());
                trm.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));

                logVendorHoldEntityRepository.save(trm);
            } else if (holdType == HoldType.CLOSE.gatValue()) {
                WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);

                StatusType newStatusType = null;
                if (active) {
                    if (holdFrom.toLocalDateTime().plusDays(1).minusSeconds(1).isAfter(LocalDateTime.now())) {
                        trm.setContractExpireDate(Timestamp.valueOf(holdFrom.toLocalDateTime().plusDays(1).minusSeconds(1)));
                        trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
                        newStatusType = StatusType.getStatusType(trm.getActive(), trm.getShopActive(), trm.getOrderActive());
                    } else {
                        trm.setContractExpireDate(Timestamp.valueOf(LocalDateTime.now()));
                        trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
                        trm.setOrderActive(false);
                        trm.setShopActive(false);
                        trm.setActive(false);
                        newStatusType = StatusType.INACTIVE;
                    }
                } else {
                    trm.setContractExpireDate(null);
                    trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
                    trm.setShopActive(true);
                    trm.setActive(true);
                    newStatusType = StatusType.SHOP_ACTIVE;
                }

                vendorWholeSalerEntityRepository.save(trm);

                LocalDateTime closeDate = null;
                if(trm.getContractExpireDate() != null)
                    closeDate = trm.getContractExpireDate().toLocalDateTime();

                WebAdminLoginUser userInfo = Utility.getUserInfo();
                vendorInfoNewService.updateStatusAndCloseDate(wholeSalerID, newStatusType.getValue(), closeDate, userInfo.getUserId(), userInfo.getUsername());

            } else if (holdType == 3) {
                WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
                trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
                trm.setOrderActive(false);
                trm.setShopActive(true);
                trm.setActive(true);

                vendorWholeSalerEntityRepository.save(trm);
            } else if (holdType == 4) {
                WholeSalerEntity trm = vendorWholeSalerEntityRepository.findOneByID(wholeSalerID);
                trm.setLastModifiedDateTime(Timestamp.valueOf(LocalDateTime.now()));
                trm.setOrderActive(true);

                vendorWholeSalerEntityRepository.save(trm);
            }
            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        }
    }

    public Integer setHoldVendorUpdate(Integer logID, Boolean active, Timestamp holdFrom, Timestamp holdTo) {

        Integer result = 0;
        try {
            LogVendorHoldEntity lvh = logVendorHoldEntityRepository.findById(logID).get();
            lvh.setHoldFrom(holdFrom);
            lvh.setHoldTo(holdTo);
            lvh.setActive(active);
            logVendorHoldEntityRepository.save(lvh);

            result = 1;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            result = -99;
        }

        return result;
    }

    private enum HoldType {

        HOLD(1),
        CLOSE(2),
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
