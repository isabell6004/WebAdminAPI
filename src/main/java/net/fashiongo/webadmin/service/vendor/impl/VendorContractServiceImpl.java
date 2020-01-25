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
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.VendorContractNewService;
import net.fashiongo.webadmin.service.vendor.VendorContractService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019-12-12.
 */
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

    @Transactional
    public Boolean setVendorContractDocument(SetVendorContractDocumentParameter request) {

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        VendorContractEntity vendorContractEntity = vendorContractEntityRepository.findById(request.getVendorContractID())
                .orElseThrow(() -> new NotFoundVendorContractException("not found vendor contract. " + request.getVendorContractID()));

        try {
            if (request.getVendorContractDocumentID() == null || request.getVendorContractDocumentID() == 0) {
                VendorContractDocumentEntity documentEntity = VendorContractDocumentEntity.create(request, Utility.getUsername());
                vendorContractDocumentEntityRepository.save(documentEntity);
                request.setVendorContractDocumentID(documentEntity.getVendorContractDocumentID());
                vendorContractNewService.createVendorContractDocument(vendorContractEntity.getWholeSalerID(), request, userInfo.getUserId(), userInfo.getUsername());
            } else {
                VendorContractDocumentEntity documentEntity = vendorContractDocumentEntityRepository.findOneByVendorContractDocumentID(request.getVendorContractDocumentID());
                documentEntity.modifyEntity(request);
                vendorContractDocumentEntityRepository.save(documentEntity);
                vendorContractNewService.modifyVendorContractDocument(vendorContractEntity.getWholeSalerID(), request, userInfo.getUserId(), userInfo.getUsername());
            }
            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        }
    }

    @Transactional
    public Boolean delVendorContractDocument(DelVendorContractDocumentParameter request) {

        try {
            Integer[] documentIds = Arrays.stream(request.getDocumentHistoryIDs().split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
            if (documentIds.length == 0){
                return false;
            }

            VendorContractDocumentEntity vendorContractDocumentEntity = vendorContractDocumentEntityRepository.findById(documentIds[0])
                    .orElseThrow(() -> new NotFoundVendorContractException("not found vendor document. " + documentIds[0]));

            VendorContractEntity vendorContractEntity = vendorContractEntityRepository.findById(vendorContractDocumentEntity.getVendorContractID())
                    .orElseThrow(() -> new NotFoundVendorContractException("not found vendor contract. " + documentIds[0]));

            List<VendorContractDocumentEntity> contractDocumentList = vendorContractDocumentEntityRepository.findAllById(Arrays.asList(documentIds));
            if(!CollectionUtils.isEmpty(contractDocumentList))
                vendorContractDocumentEntityRepository.deleteAll(contractDocumentList);

            // new DB 스키마를 위한 API call
            WebAdminLoginUser userInfo = Utility.getUserInfo();
            vendorContractNewService.deleteVendorContractDocument(vendorContractEntity.getWholeSalerID(), Arrays.stream(documentIds).mapToLong(i -> i).boxed().collect(Collectors.toList()), userInfo.getUserId(), userInfo.getUsername());

            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        }
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

    @Transactional
    public Boolean setVendorContract(SetVendorContractParameter request) {

        WholeSalerEntity vendorInfo = checkAndGetVendor(request.getWholeSalerID());

        Long originalVendorContractHistoryId = 0L, revisedVendorContractHistoryId = 0L;
        try {
            if (request.isNewVendorContract()) {
                VendorContractEntity originContractInfo = vendorContractEntityRepository.findOneByWholeSalerID(request.getWholeSalerID());
                if (originContractInfo == null) {
                    createContract(request);
                }  else {
                    modifyContract(originContractInfo, request);
                }
            } else {
                VendorContractEntity originContractInfo = vendorContractEntityRepository.findOneByVendorContractID(request.getVendorContractID());
                if (request.getVendorContractRowAdd() == null || !request.getVendorContractRowAdd()) {
                    modifyContract(originContractInfo, request);
                } else {
                    reviseContract(originContractInfo, request);
                }
                updateVendorType(request, vendorInfo);
            }

            cacheService.cacheEvictVendor(vendorInfo.getWholeSalerID());
            return true;

        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        }
    }

    private void reviseContract(VendorContractEntity originContractInfo, SetVendorContractParameter request) {
        originContractInfo.terminate(request.getVendorContractFrom());
        vendorContractEntityRepository.save(originContractInfo);
        Long originalVendorContractHistoryId = Long.valueOf(originContractInfo.getVendorContractID());

        VendorContractEntity newContractInfo = VendorContractEntity.create(request);
        vendorContractEntityRepository.save(newContractInfo);
        Long revisedVendorContractHistoryId = Long.valueOf(newContractInfo.getVendorContractID());

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        vendorContractNewService.reviseContract(originalVendorContractHistoryId, revisedVendorContractHistoryId, request, userInfo.getUserId(), userInfo.getUsername());
    }

    private void createContract(SetVendorContractParameter request) {
        VendorContractEntity originContractInfo = VendorContractEntity.create(request);
        vendorContractEntityRepository.save(originContractInfo);
        Long originalVendorContractHistoryId = Long.valueOf(originContractInfo.getVendorContractID());

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        vendorContractNewService.createContract(originalVendorContractHistoryId, request, userInfo.getUserId(), userInfo.getUsername());
    }

    private void modifyContract(VendorContractEntity originContractInfo, SetVendorContractParameter request) {
        originContractInfo.updateEntity(request);
        vendorContractEntityRepository.save(originContractInfo);
        Long originalVendorContractHistoryId = Long.valueOf(originContractInfo.getVendorContractID());

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        vendorContractNewService.modifyContract(originalVendorContractHistoryId, request, userInfo.getUserId(), userInfo.getUsername());
    }

    private void updateVendorType(SetVendorContractParameter request, WholeSalerEntity wholeSaler) {
        wholeSaler.setVendorType(request.getContractTypeID() != 5 ? 1 : 2); // contractTypeId == 5 ? premium vendor
        vendorWholeSalerEntityRepository.save(wholeSaler);
    }
}
