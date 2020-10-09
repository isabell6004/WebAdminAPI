package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.AspnetMembershipEntity;
import net.fashiongo.webadmin.data.entity.primary.EntityActionLogEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorAdminAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorBlockedEntity;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.repository.primary.AspnetMembershipEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.EntityActionLogEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.VendorAdminAccountEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.VendorBlockedEntityRepository;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.PaymentService;
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
    private PaymentService paymentService;

    public VendorBlockServiceImpl(VendorBlockedEntityRepository vendorBlockedEntityRepository,
                                  EntityActionLogEntityRepository entityActionLogEntityRepository,
                                  VendorAdminAccountEntityRepository vendorAdminAccountEntityRepository,
                                  AspnetMembershipEntityRepository aspnetMembershipEntityRepository,
                                  VendorBlockNewService vendorBlockNewService,
                                  CacheService cacheService,
                                  PaymentService paymentService) {
        this.vendorBlockedEntityRepository = vendorBlockedEntityRepository;
        this.entityActionLogEntityRepository = entityActionLogEntityRepository;
        this.vendorAdminAccountEntityRepository = vendorAdminAccountEntityRepository;
        this.aspnetMembershipEntityRepository = aspnetMembershipEntityRepository;
        this.vendorBlockNewService = vendorBlockNewService;
        this.cacheService = cacheService;
        this.paymentService = paymentService;
    }

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Boolean updateVendorAdminLogin(VendorBlockAdminLoginParameter request) {
        Boolean result = false;
        Boolean resultBlock = true;
        if (request.getBlockChanged()) {
            resultBlock = this.updateVendorAdminLoginMembership(request.getVendorId(), request.getIsBlock(), request.getBlockReasonId());
        }
        if (resultBlock) {
            result = vendorBlockNewService.updateBlock(request);
        }
        return result;
    }

    private Boolean updateVendorAdminLoginMembership(Long vendorId, Boolean isBlock, Long blockReasonId) {
        try {
            EntityActionLogEntity entityActionLogEntity;
            if (isBlock){
                entityActionLogEntity = EntityActionLogEntity.create(9, vendorId.intValue(), 9001, blockReasonId.toString());
            } else {
                entityActionLogEntity = EntityActionLogEntity.create(9, vendorId.intValue(), 9002);
            }
            entityActionLogEntityRepository.save(entityActionLogEntity);
            List<VendorAdminAccountEntity> vendorAdminAccountList = vendorAdminAccountEntityRepository.findAllByWholeSalerID(vendorId.intValue());

            List<AspnetMembershipEntity> aspnetMembershipEntityList = new ArrayList<>();
            for (VendorAdminAccountEntity va : vendorAdminAccountList) {
                AspnetMembershipEntity subAccount = aspnetMembershipEntityRepository.findOneByWholeSalerGUID(va.getUserGUID());
                subAccount.setApproved(!isBlock);
                subAccount.setLockedOut(isBlock);
                aspnetMembershipEntityList.add(subAccount);
            }
            aspnetMembershipEntityRepository.saveAll(aspnetMembershipEntityList);
            return Boolean.TRUE;

        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return Boolean.FALSE;
        }
    }

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Boolean updateVendorPayout(VendorBlockPayoutParameter request){

        Boolean result = false;
        if (request.getPayoutBlockChanged()) {
            if (request.getIsPayoutBlock()) {
                result = this.updateVendorPayoutBlock(request);
            } else {
                result = this.updateVendorPayoutUnblock(request);
            }
        } else {
            //change payout block reason only
            result = vendorBlockNewService.updatePayoutBlock(request);
        }
        return result;
    }

    private Boolean updateVendorPayoutBlock(VendorBlockPayoutParameter request) {
        Boolean result = false;
        Boolean resultSetting = vendorBlockNewService.updatePayoutBlock(request);

        if (resultSetting) {
            Boolean resultPayment = paymentService.updatePaymentPayoutBlock(request.getVendorId().intValue());
            result = resultPayment;
            if (!resultPayment) {
                // rollback : unblock
                VendorBlockPayoutParameter unblockParameter = new VendorBlockPayoutParameter(request.getVendorId(),!request.getIsPayoutBlock(),null,Boolean.TRUE);
                resultSetting = vendorBlockNewService.updatePayoutBlock(unblockParameter);
            }
        }
        return result;
    }

    private Boolean updateVendorPayoutUnblock(VendorBlockPayoutParameter request) {
        Boolean resultSetting = false;
        Boolean resultPayment = paymentService.updatePaymentPayoutUnblock(request.getVendorId().intValue());
        if (resultPayment) {
            resultSetting = vendorBlockNewService.updatePayoutBlock(request);
        }
        return resultSetting;
    }

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Boolean updateVendorAd(VendorBlockAdParameter request) {
        Boolean result = vendorBlockNewService.updateAdBlock(request);
        return result;
    }





}
