package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.AspnetMembershipEntity;
import net.fashiongo.webadmin.data.entity.primary.EntityActionLogEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorAdminAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorBlockedEntity;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockUpdate;
import net.fashiongo.webadmin.data.repository.primary.AspnetMembershipEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.EntityActionLogEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.VendorAdminAccountEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.VendorBlockedEntityRepository;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.VendorBlockNewService;
import net.fashiongo.webadmin.service.vendor.VendorBlockService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinwoo on 2020-01-02.
 */
@Service
@Slf4j
public class VendorBlockServiceImpl implements VendorBlockService {

    private VendorBlockedEntityRepository vendorBlockedEntityRepository;

    private EntityActionLogEntityRepository entityActionLogEntityRepository;

    private VendorAdminAccountEntityRepository vendorAdminAccountEntityRepository;

    private AspnetMembershipEntityRepository aspnetMembershipEntityRepository;

    private VendorBlockNewService vendorBlockNewService;

    private CacheService cacheService;

    public VendorBlockServiceImpl(VendorBlockedEntityRepository vendorBlockedEntityRepository,
                                  EntityActionLogEntityRepository entityActionLogEntityRepository,
                                  VendorAdminAccountEntityRepository vendorAdminAccountEntityRepository,
                                  AspnetMembershipEntityRepository aspnetMembershipEntityRepository,
                                  VendorBlockNewService vendorBlockNewService,
                                  CacheService cacheService) {
        this.vendorBlockedEntityRepository = vendorBlockedEntityRepository;
        this.entityActionLogEntityRepository = entityActionLogEntityRepository;
        this.vendorAdminAccountEntityRepository = vendorAdminAccountEntityRepository;
        this.aspnetMembershipEntityRepository = aspnetMembershipEntityRepository;
        this.vendorBlockNewService = vendorBlockNewService;
        this.cacheService = cacheService;
    }


    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Boolean block(SetVendorBlockParameter param) {
        Boolean result = registVendorBlock(param);
        if (result) {
            cacheService.cacheEvictVendor(param.getWholeSalerID());
        }
        return result;
    }

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Boolean unblock(DelVendorBlockParameter param) {
        Boolean result = deleteVendorBlock(param);
        if (result) {
            cacheService.cacheEvictVendor(param.getWholeSalerID());
        }
        return result;
    }

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Integer modifyBlockReason(SetVendorBlockUpdate request) {
        try {
            VendorBlockedEntity retailer = vendorBlockedEntityRepository.findOneByWholeSalerID(request.getWholeSalerID());
            retailer.setBlockReasonId(request.getBlockReasonID());
            vendorBlockedEntityRepository.save(retailer);

            WebAdminLoginUser userInfo = Utility.getUserInfo();
            vendorBlockNewService.modifyBlockReason(request, userInfo.getUserId(), userInfo.getUsername());

            return 1;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return -99;
        }
    }

    private Boolean deleteVendorBlock(DelVendorBlockParameter request) {

        try {
            vendorBlockedEntityRepository.deleteById(request.getBlockID());

            EntityActionLogEntity entityActionLogEntity = EntityActionLogEntity.create(9, request.getWholeSalerID(), 9002);
            entityActionLogEntityRepository.save(entityActionLogEntity);

            List<VendorAdminAccountEntity> vendorAdminAccountList = vendorAdminAccountEntityRepository.findAllByWholeSalerID(request.getWholeSalerID());

            List<AspnetMembershipEntity> aspnetMembershipEntityList = new ArrayList<>();
            for (VendorAdminAccountEntity va : vendorAdminAccountList) {
                AspnetMembershipEntity subAccount = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(va.getUserGUID());
                subAccount.setApproved(true);
                subAccount.setLockedOut(false);
                aspnetMembershipEntityList.add(subAccount);
            }
            aspnetMembershipEntityRepository.saveAll(aspnetMembershipEntityList);

            // new DB 스키마를 위한 API call
            WebAdminLoginUser userInfo = Utility.getUserInfo();
            vendorBlockNewService.unblockVendor(request, userInfo.getUserId(), userInfo.getUsername());

            return Boolean.TRUE;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return Boolean.FALSE;
        }

    }

    private Boolean registVendorBlock(SetVendorBlockParameter request) {
        try {
            VendorBlockedEntity vendorBlockedEntity = VendorBlockedEntity.create(request);
            vendorBlockedEntityRepository.save(vendorBlockedEntity);

            EntityActionLogEntity entityActionLogEntity = EntityActionLogEntity.create(0, request.getWholeSalerID(), 9001, request.getReason());
            entityActionLogEntityRepository.save(entityActionLogEntity);

            List<VendorAdminAccountEntity> vendorAdminAccountList = vendorAdminAccountEntityRepository.findAllByWholeSalerID(request.getWholeSalerID());

            List<AspnetMembershipEntity> aspnetMembershipEntityList = new ArrayList<>();
            for (VendorAdminAccountEntity va : vendorAdminAccountList) {
                AspnetMembershipEntity subAccount = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(va.getUserGUID());
                subAccount.setApproved(false);
                subAccount.setLockedOut(true);
                aspnetMembershipEntityList.add(subAccount);
            }
            aspnetMembershipEntityRepository.saveAll(aspnetMembershipEntityList);

            WebAdminLoginUser userInfo = Utility.getUserInfo();
            vendorBlockNewService.blockVendor(request, userInfo.getUserId(), userInfo.getUsername());

            return Boolean.TRUE;

        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return Boolean.FALSE;
        }
    }
}
