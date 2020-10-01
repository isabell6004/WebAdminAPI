package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.model.vendor.DelVendorContractParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorContractResponse;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorWholeSalerEntityRepository;
import net.fashiongo.webadmin.exception.vendor.NotFoundVendorException;
import net.fashiongo.webadmin.model.vendor.ClassType;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.VendorContractNewService;
import net.fashiongo.webadmin.service.vendor.VendorContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

@Service
@Slf4j
public class VendorContractServiceImpl implements VendorContractService {

    private VendorContractNewService vendorContractNewService;

    private VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;

    private CacheService cacheService;

    public VendorContractServiceImpl(
            VendorContractNewService vendorContractNewService,
            VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository,
            CacheService cacheService) {
        this.vendorContractNewService = vendorContractNewService;
        this.vendorWholeSalerEntityRepository = vendorWholeSalerEntityRepository;
        this.cacheService = cacheService;
    }

    @Transactional(value = "primaryTransactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void setVendorContract(SetVendorContractParameter request) {
        WholeSalerEntity vendorInfo = checkAndGetVendor(request.getWholeSalerID());
        updateVendorType(request.getContractTypeID(), request.getVendorContractFrom(), null, vendorInfo);

        vendorContractNewService.setVendorContract(request);

        cacheService.cacheEvictVendor(vendorInfo.getWholeSalerID());
     }

    private WholeSalerEntity checkAndGetVendor(Integer vendorId) {
        if(vendorId == null || vendorId == 0)
            throw new NotFoundVendorException("can not find a vendor info. The id is " + vendorId);

        WholeSalerEntity vendorInfo = vendorWholeSalerEntityRepository.findOneByID(vendorId);
        if(vendorInfo == null) {
            throw new NotFoundVendorException("can not find a vendor info. The id is " + vendorId);
        }
        return vendorInfo;
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_COMMITTED)
    public void delVendorContract(DelVendorContractParameter request) {

        vendorContractNewService.deleteContract(request.getWholeSalerID(), request.getVendorContractID().longValue());
        VendorContractResponse vendorContract = vendorContractNewService.inquiryVendorContract(request.getWholeSalerID());

        if (vendorContract != null) {
            WholeSalerEntity vendorInfo = checkAndGetVendor(request.getWholeSalerID());
            updateVendorType(vendorContract.getTypeCode(), vendorContract.getDateFrom(), vendorContract.getDateTo(), vendorInfo);
        }
    }

    private void updateVendorType(Integer contractTypeId, String strDateFrom, LocalDateTime dateTo, WholeSalerEntity wholeSaler) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("MM/d/yyyy")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
        LocalDateTime dateFrom = !ObjectUtils.isEmpty(strDateFrom) ? LocalDateTime.parse(strDateFrom, formatter) : null;

        updateVendorType(contractTypeId, dateFrom, dateTo, wholeSaler);
    }

    private void updateVendorType(Integer contractTypeId, LocalDateTime dateFrom, LocalDateTime dateTo, WholeSalerEntity wholeSaler) {
        if (ObjectUtils.isEmpty(dateFrom) || dateFrom.isAfter(LocalDateTime.now()) || (!ObjectUtils.isEmpty(dateTo) && dateTo.isBefore(LocalDateTime.now()))) {
            return;
        }

        // contractTypeId == 5 ? premium vendor
        ClassType vendorClassType = (contractTypeId != 5) ? ClassType.GENERAL : ClassType.PREMIUM;
        if (!wholeSaler.getVendorType().equals(vendorClassType.getValue())) {
            wholeSaler.setVendorType(vendorClassType.getValue());
            vendorWholeSalerEntityRepository.save(wholeSaler);
        }
    }
}
