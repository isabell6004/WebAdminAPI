package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.CustomerSocialMediaEntity;
import net.fashiongo.webadmin.data.model.vendor.SetVendorSNSListParameter;
import net.fashiongo.webadmin.data.repository.primary.CustomerSocialMediaEntityRepository;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.VendorSNSNewService;
import net.fashiongo.webadmin.service.vendor.VendorSNSService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jinwoo on 2020-01-08.
 */
@Slf4j
@Service
public class VendorSNSServiceImpl implements VendorSNSService {

    private CacheService cacheService;

    private VendorSNSNewService vendorSNSNewService;

    private CustomerSocialMediaEntityRepository customerSocialMediaEntityRepository;

    public VendorSNSServiceImpl(
            VendorSNSNewService vendorSNSNewService,
            CacheService cacheService,
            CustomerSocialMediaEntityRepository customerSocialMediaEntityRepository
    ) {
        this.vendorSNSNewService = vendorSNSNewService;
        this.cacheService = cacheService;
        this.customerSocialMediaEntityRepository = customerSocialMediaEntityRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public boolean modifyVendorSNSInfo(SetVendorSNSListParameter request) {
        Boolean result = setVendorSNSList(request);
        cacheService.cacheEvictVendor(request.getWholeSalerID());
        return result;
    }

    private Boolean setVendorSNSList(SetVendorSNSListParameter request) {

        Integer mapID = request.getMapID() == null ? 0 : request.getMapID();
        Integer wholeSalerID = request.getWholeSalerID() == null ? 0 : request.getWholeSalerID();
        Integer socialMediaID = request.getSocialMediaID() == null ? 0 : request.getSocialMediaID();
        String url = StringUtils.isEmpty(request.getSocialMediaUsername()) ? "" : request.getSocialMediaUsername();

        try {
            if (mapID == 0) {
                CustomerSocialMediaEntity customerSocialMedia = new CustomerSocialMediaEntity();
                customerSocialMedia.setReferenceID(wholeSalerID);
                customerSocialMedia.setUserTypeID(2);
                customerSocialMedia.setSocialMediaID(socialMediaID);
                customerSocialMedia.setSocialMediaUsername(url);
                customerSocialMediaEntityRepository.save(customerSocialMedia);
                vendorSNSNewService.create(request);
            } else {
                if(StringUtils.isEmpty(url)) {
                    CustomerSocialMediaEntity customerSocialMedia = customerSocialMediaEntityRepository.findOneByMapID(mapID);
                    customerSocialMediaEntityRepository.delete(customerSocialMedia);
                    vendorSNSNewService.delete(request);
                } else {
                    CustomerSocialMediaEntity customerSocialMedia = customerSocialMediaEntityRepository.findOneByMapID(mapID);
                    customerSocialMedia.setSocialMediaUsername(url);
                    customerSocialMediaEntityRepository.save(customerSocialMedia);
                    vendorSNSNewService.modify(request);
                }
            }
            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        }
    }

}
