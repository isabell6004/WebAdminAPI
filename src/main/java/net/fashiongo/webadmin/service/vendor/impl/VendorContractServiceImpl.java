package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.dao.primary.VendorEntityRepository;
import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import net.fashiongo.webadmin.data.model.vendor.DelVendorContractParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorContractResponse;
import net.fashiongo.webadmin.exception.vendor.NotFoundVendorException;
import net.fashiongo.webadmin.model.vendor.ClassType;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.VendorContractNewService;
import net.fashiongo.webadmin.service.vendor.VendorContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class VendorContractServiceImpl implements VendorContractService {

    private VendorContractNewService vendorContractNewService;

//    private VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;

    private VendorEntityRepository vendorEntityRepository;

    private CacheService cacheService;

    public VendorContractServiceImpl(
            VendorContractNewService vendorContractNewService,
//            VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository,
            VendorEntityRepository vendorEntityRepository, CacheService cacheService) {
        this.vendorContractNewService = vendorContractNewService;
        this.vendorEntityRepository = vendorEntityRepository;
//        this.vendorWholeSalerEntityRepository = vendorWholeSalerEntityRepository;
        this.cacheService = cacheService;
    }

    @Transactional(value = "primaryTransactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void setVendorContract(SetVendorContractParameter request) {
        VendorEntity vendorInfo = checkAndGetVendor(request.getWholeSalerID());
        updateVendorType(request, vendorInfo);

        vendorContractNewService.setVendorContract(request);

        cacheService.cacheEvictVendor(vendorInfo.getVendor_id().intValue());
     }

    private VendorEntity checkAndGetVendor(Integer vendorId) {
        if(vendorId == null || vendorId == 0)
            throw new NotFoundVendorException("can not find a vendor info. The id is " + vendorId);

        VendorEntity vendor = vendorEntityRepository.findByVendorId(vendorId.longValue());
        if(vendor == null) {
            throw new NotFoundVendorException("can not find a vendor info. The id is " + vendorId);
        }
        return vendor;
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_COMMITTED)
    public void delVendorContract(DelVendorContractParameter request) {

        vendorContractNewService.deleteContract(request.getWholeSalerID(), request.getVendorContractID().longValue());
        VendorContractResponse vendorContract = vendorContractNewService.inquiryVendorContract(request.getWholeSalerID());

        if (vendorContract != null)
        {
            VendorEntity vendorInfo = checkAndGetVendor(request.getWholeSalerID());
            ClassType vendorClassType = (vendorContract.getTypeCode() != 5) ? ClassType.GENERAL : ClassType.PREMIUM;
            vendorInfo.setClassCode(vendorClassType.getValue());
            vendorEntityRepository.save(vendorInfo);
        }
    }

    private void updateVendorType(SetVendorContractParameter request, VendorEntity wholeSaler) {
        // contractTypeId == 5 ? premium vendor
        ClassType vendorClassType = (request.getContractTypeID() != 5) ? ClassType.GENERAL : ClassType.PREMIUM;
        wholeSaler.setClassCode(vendorClassType.getValue());
        vendorEntityRepository.save(wholeSaler);
    }
}
