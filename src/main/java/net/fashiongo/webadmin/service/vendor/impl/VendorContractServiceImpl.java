package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.VendorContractDocumentEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorContractEntity;
import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import net.fashiongo.webadmin.data.model.vendor.DelVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractParameter;
import net.fashiongo.webadmin.data.repository.primary.VendorContractEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorContractDocumentEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorWholeSalerEntityRepository;
import net.fashiongo.webadmin.exception.vendor.NotFoundVendorContractException;
import net.fashiongo.webadmin.exception.vendor.NotFoundVendorException;
import net.fashiongo.webadmin.model.vendor.ClassType;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.VendorContractNewService;
import net.fashiongo.webadmin.service.vendor.VendorContractService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VendorContractServiceImpl implements VendorContractService {

    private VendorContractDocumentEntityRepository vendorContractDocumentEntityRepository;

    private VendorContractNewService vendorContractNewService;

    private VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository;

    private VendorContractEntityRepository vendorContractEntityRepository;

    private CacheService cacheService;

    public VendorContractServiceImpl(
            VendorContractDocumentEntityRepository vendorContractDocumentEntityRepository,
            VendorContractNewService vendorContractNewService,
            VendorWholeSalerEntityRepository vendorWholeSalerEntityRepository,
            VendorContractEntityRepository vendorContractEntityRepository,
            CacheService cacheService) {
        this.vendorContractDocumentEntityRepository = vendorContractDocumentEntityRepository;
        this.vendorContractNewService = vendorContractNewService;
        this.vendorWholeSalerEntityRepository = vendorWholeSalerEntityRepository;
        this.vendorContractEntityRepository = vendorContractEntityRepository;
        this.cacheService = cacheService;
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_COMMITTED)
    public void setVendorContractDocument(SetVendorContractDocumentParameter request) {

        VendorContractEntity vendorContractEntity = vendorContractEntityRepository.findById(request.getVendorContractID())
                .orElseThrow(() -> new NotFoundVendorContractException("not found vendor contract. " + request.getVendorContractID()));

        if (request.getVendorContractDocumentID() == null || request.getVendorContractDocumentID() == 0) {
            VendorContractDocumentEntity documentEntity = VendorContractDocumentEntity.create(request, Utility.getUsername());
            vendorContractDocumentEntityRepository.save(documentEntity);
            request.setVendorContractDocumentID(documentEntity.getVendorContractDocumentID());
            vendorContractNewService.createVendorContractDocument(vendorContractEntity.getWholeSalerID(), request);
        } else {
            VendorContractDocumentEntity documentEntity = vendorContractDocumentEntityRepository.findOneByVendorContractDocumentID(request.getVendorContractDocumentID());
            documentEntity.modifyEntity(request);
            vendorContractDocumentEntityRepository.save(documentEntity);

            vendorContractNewService.modifyVendorContractDocument(vendorContractEntity.getWholeSalerID(), request);
        }
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_COMMITTED)
    public void delVendorContractDocument(DelVendorContractDocumentParameter request) {

        Integer[] documentIds = Arrays.stream(request.getDocumentHistoryIDs().split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        if (documentIds.length == 0){
            return;
        }

        VendorContractDocumentEntity vendorContractDocumentEntity = vendorContractDocumentEntityRepository.findById(documentIds[0])
                .orElseThrow(() -> new NotFoundVendorContractException("not found vendor document. " + documentIds[0]));
        VendorContractEntity vendorContractEntity = vendorContractEntityRepository.findById(vendorContractDocumentEntity.getVendorContractID())
                .orElseThrow(() -> new NotFoundVendorContractException("not found vendor contract. " + documentIds[0]));
        List<VendorContractDocumentEntity> contractDocumentList = vendorContractDocumentEntityRepository.findAllById(Arrays.asList(documentIds));
        if(!CollectionUtils.isEmpty(contractDocumentList))
            vendorContractDocumentEntityRepository.deleteAll(contractDocumentList);

        vendorContractNewService.deleteVendorContractDocument(vendorContractEntity.getWholeSalerID(), vendorContractEntity.getVendorContractID().longValue(), Arrays.stream(documentIds).mapToLong(i -> i).boxed().collect(Collectors.toList()));
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
            VendorContractEntity originContractInfo = vendorContractEntityRepository.findOneByWholeSalerID(request.getWholeSalerID());
            if (originContractInfo == null) {
                // temp log
                log.warn("original contract request: {}, {}, {}", request.getWholeSalerID(), request.getVendorContractID(), request.getVendorContractPlanID());
                createContract(vendorInfo, request);
            }  else {
                modifyContract(vendorInfo, originContractInfo, request);
            }
        } else {
            VendorContractEntity originContractInfo = vendorContractEntityRepository.findOneByVendorContractID(request.getVendorContractID());
            if (request.getVendorContractRowAdd() == null || !request.getVendorContractRowAdd()) {
                modifyContract(vendorInfo, originContractInfo, request);
            } else {
                reviseContract(vendorInfo, originContractInfo, request);
            }
        }
        cacheService.cacheEvictVendor(vendorInfo.getWholeSalerID());
     }

    @Override
    public VendorContractEntity getVendorContractIncludedOpenDate(Integer vendorId, LocalDateTime actualOpenDate) {
        if(actualOpenDate == null) return null;
        return vendorContractEntityRepository.findVendorContractByVendorIdAndOpenDate(vendorId, actualOpenDate);
    }

    private void reviseContract(WholeSalerEntity wholeSalerEntity, VendorContractEntity originContractInfo, SetVendorContractParameter request) {
        originContractInfo.terminate(request.getVendorContractFrom());
        vendorContractEntityRepository.save(originContractInfo);
        Long originalVendorContractHistoryId = Long.valueOf(originContractInfo.getVendorContractID());

        VendorContractEntity newContractInfo = VendorContractEntity.create(request);
        vendorContractEntityRepository.save(newContractInfo);
        Long revisedVendorContractHistoryId = Long.valueOf(newContractInfo.getVendorContractID());

        updateVendorType(request, wholeSalerEntity);

        request.setCommissionRate(BigDecimal.valueOf(request.getCommissionRate().doubleValue() / 100.0));
        vendorContractNewService.reviseContract(originalVendorContractHistoryId, revisedVendorContractHistoryId, request);
    }

    private void createContract(WholeSalerEntity wholeSalerEntity, SetVendorContractParameter request) {
        VendorContractEntity originContractInfo = VendorContractEntity.create(request);
        vendorContractEntityRepository.save(originContractInfo);
        Long originalVendorContractHistoryId = Long.valueOf(originContractInfo.getVendorContractID());
        updateVendorType(request, wholeSalerEntity);

        request.setCommissionRate(BigDecimal.valueOf(request.getCommissionRate().doubleValue() / 100.0));
        vendorContractNewService.createContract(originalVendorContractHistoryId, request);
    }

    private void modifyContract(WholeSalerEntity wholeSalerEntity, VendorContractEntity originContractInfo, SetVendorContractParameter request) {
        originContractInfo.updateEntity(request);
        vendorContractEntityRepository.save(originContractInfo);
        Long originalVendorContractHistoryId = Long.valueOf(originContractInfo.getVendorContractID());
        updateVendorType(request, wholeSalerEntity);

        request.setCommissionRate(BigDecimal.valueOf(request.getCommissionRate().doubleValue() / 100.0));
        vendorContractNewService.modifyContract(originalVendorContractHistoryId, request);
    }

    private void updateVendorType(SetVendorContractParameter request, WholeSalerEntity wholeSaler) {
        // contractTypeId == 5 ? premium vendor
        ClassType vendorClassType = (request.getContractTypeID() != 5) ? ClassType.GENERAL : ClassType.PREMIUM;
        wholeSaler.setVendorType(vendorClassType.getValue());
        vendorWholeSalerEntityRepository.save(wholeSaler);
    }
}
