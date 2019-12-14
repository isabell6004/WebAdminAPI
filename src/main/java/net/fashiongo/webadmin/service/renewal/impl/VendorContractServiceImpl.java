package net.fashiongo.webadmin.service.renewal.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.VendorContractDocumentEntity;
import net.fashiongo.webadmin.data.model.vendor.DelVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorContractDocumentParameter;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorContractDocumentEntityRepository;
import net.fashiongo.webadmin.service.renewal.VendorContractNewService;
import net.fashiongo.webadmin.service.renewal.VendorContractService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public VendorContractServiceImpl(
            VendorContractDocumentEntityRepository vendorContractDocumentEntityRepository,
            VendorContractNewService vendorContractNewService) {
        this.vendorContractDocumentEntityRepository = vendorContractDocumentEntityRepository;
        this.vendorContractNewService = vendorContractNewService;
    }

    @Transactional
    public Boolean setVendorContractDocument(SetVendorContractDocumentParameter request) {

        try {
            if (request.getVendorContractDocumentID() == 0 || request.getVendorContractDocumentID() == null) {
                VendorContractDocumentEntity documentEntity = VendorContractDocumentEntity.create(request, Utility.getUsername());
                vendorContractDocumentEntityRepository.save(documentEntity);
            } else {
                VendorContractDocumentEntity documentEntity = vendorContractDocumentEntityRepository.findOneByVendorContractDocumentID(request.getVendorContractDocumentID());
                documentEntity.modifyEntity(request);
                vendorContractDocumentEntityRepository.save(documentEntity);
            }

            // new DB 스키마를 위한 API call
            vendorContractNewService.createAndModifyVendorContractDocument(request);
            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        }
    }

    @Transactional
    public Boolean delVendorContractDocument(DelVendorContractDocumentParameter request) {

        Integer[] documentIds = Arrays.stream(request.getDocumentHistoryIDs().split(",")).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
        try {
            List<VendorContractDocumentEntity> contractDocumentList = vendorContractDocumentEntityRepository.findAllById(Arrays.asList(documentIds));
            vendorContractDocumentEntityRepository.deleteAll(contractDocumentList);

            // new DB 스키마를 위한 API call
            vendorContractNewService.deleteVendorContractDocument(Arrays.stream(documentIds).mapToLong(i -> i).boxed().collect(Collectors.toList()));

            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        }
    }
}
