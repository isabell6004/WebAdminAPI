package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.VendorContractHistoryDocumentEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorContractHistoryEntity;
import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.repository.primary.VendorContractHistoryDocumentEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.VendorContractHistoryEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorWholeSalerEntityRepository;
import net.fashiongo.webadmin.exception.vendor.NotFoundVendorContractException;
import net.fashiongo.webadmin.exception.vendor.NotFoundVendorException;
import net.fashiongo.webadmin.model.vendor.ClassType;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.VendorContractNewService;
import net.fashiongo.webadmin.service.vendor.VendorContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VendorContractServiceImpl implements VendorContractService {

    private VendorContractNewService vendorContractNewService;

    private VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;

    private VendorContractHistoryEntityRepository vendorContractHistoryEntityRepository;

    private VendorContractHistoryDocumentEntityRepository vendorContractHistoryDocumentEntityRepository;

    private CacheService cacheService;

    public VendorContractServiceImpl(
            VendorContractNewService vendorContractNewService,
            VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository,
            VendorContractHistoryEntityRepository vendorContractHistoryEntityRepository,
            VendorContractHistoryDocumentEntityRepository vendorContractHistoryDocumentEntityRepository,
            CacheService cacheService) {
        this.vendorContractNewService = vendorContractNewService;
        this.vendorWholeSalerEntityRepository = vendorWholeSalerEntityRepository;
        this.vendorContractHistoryEntityRepository = vendorContractHistoryEntityRepository;
        this.vendorContractHistoryDocumentEntityRepository = vendorContractHistoryDocumentEntityRepository;
        this.cacheService = cacheService;
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_COMMITTED)
    public void setVendorContractDocument(SetVendorContractDocumentParameter request) {
        VendorContractHistoryEntity vendorContractHistoryEntity = vendorContractHistoryEntityRepository.findById(Long.valueOf(request.getVendorContractID()))
                .orElseThrow(() -> new NotFoundVendorContractException("not found vendor contract. " + request.getVendorContractID()));

        if (request.getVendorContractDocumentID() == null || request.getVendorContractDocumentID() == 0) {
            vendorContractNewService.createVendorContractDocument(vendorContractHistoryEntity.getVendorId(), request);
        } else {
            vendorContractNewService.modifyVendorContractDocument(vendorContractHistoryEntity.getVendorId(), request);
        }
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_COMMITTED)
    public void delVendorContractDocument(DelVendorContractDocumentParameter request) {

        Integer[] documentIds = Arrays.stream(request.getDocumentHistoryIDs().split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        if (documentIds.length == 0){
            return;
        }

        VendorContractHistoryDocumentEntity vendorContractHistoryDocumentEntity = vendorContractHistoryDocumentEntityRepository.findById(Long.valueOf(documentIds[0]))
                .orElseThrow(() -> new NotFoundVendorContractException("not found vendor document. " + documentIds[0]));
        VendorContractHistoryEntity vendorContractHistoryEntity = vendorContractHistoryEntityRepository.findById(vendorContractHistoryDocumentEntity.getVendorContractHistoryId())
                .orElseThrow(() -> new NotFoundVendorContractException("not found vendor contract. " + documentIds[0]));

        vendorContractNewService.deleteVendorContractDocument(vendorContractHistoryEntity.getVendorId(), vendorContractHistoryEntity.getId(), Arrays.stream(documentIds).mapToLong(i -> i).boxed().collect(Collectors.toList()));
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

    @Transactional(value = "primaryTransactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void setVendorContract(SetVendorContractParameter request) {

        WholeSalerEntity vendorInfo = checkAndGetVendor(request.getWholeSalerID());

        if (request.isNewVendorContract()) {
            createContract(vendorInfo, request);
        } else {
            if (request.getVendorContractRowAdd() == null || !request.getVendorContractRowAdd()) {
                modifyContract(vendorInfo, request);
            } else {
                reviseContract(vendorInfo, request);
            }
        }
        cacheService.cacheEvictVendor(vendorInfo.getWholeSalerID());
     }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_COMMITTED)
    public void delVendorContract(DelVendorContractParameter request) {

        vendorContractNewService.deleteContract(request.getWholeSalerID(), request.getVendorContractID().longValue());
    }

    private void reviseContract(WholeSalerEntity wholeSalerEntity, SetVendorContractParameter request) {

        updateVendorType(request, wholeSalerEntity);

        request.setCommissionRate(BigDecimal.valueOf(request.getCommissionRate().doubleValue() / 100.0));
        vendorContractNewService.reviseContract(request);
    }

    private void createContract(WholeSalerEntity wholeSalerEntity, SetVendorContractParameter request) {
        updateVendorType(request, wholeSalerEntity);

        request.setCommissionRate(BigDecimal.valueOf(request.getCommissionRate().doubleValue() / 100.0));
        vendorContractNewService.createContract(request);
    }

    private void modifyContract(WholeSalerEntity wholeSalerEntity,  SetVendorContractParameter request) {
        updateVendorType(request, wholeSalerEntity);

        request.setCommissionRate(BigDecimal.valueOf(request.getCommissionRate().doubleValue() / 100.0));
        vendorContractNewService.modifyContract(request);
    }

    private void updateVendorType(SetVendorContractParameter request, WholeSalerEntity wholeSaler) {
        // contractTypeId == 5 ? premium vendor
        ClassType vendorClassType = (request.getContractTypeID() != 5) ? ClassType.GENERAL : ClassType.PREMIUM;
        wholeSaler.setVendorType(vendorClassType.getValue());
        vendorWholeSalerEntityRepository.save(wholeSaler);
    }
}
