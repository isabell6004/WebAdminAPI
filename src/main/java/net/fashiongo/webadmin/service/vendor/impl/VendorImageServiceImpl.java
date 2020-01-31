package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import net.fashiongo.webadmin.data.model.vendor.DelVendorImageParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorImageParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorImage;
import net.fashiongo.webadmin.data.repository.primary.vendor.VendorImageRequestEntityRepository;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.BannerRequestNewService;
import net.fashiongo.webadmin.service.vendor.VendorImageService;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jinwoo on 2020-01-08.
 */
@Slf4j
@Service("vendorBannerImageService")
public class VendorImageServiceImpl implements VendorImageService {

    private CacheService cacheService;

    private VendorImageRequestEntityRepository vendorImageRequestEntityRepository;

    private BannerRequestNewService bannerRequestNewService;

    public VendorImageServiceImpl(
            VendorImageRequestEntityRepository vendorImageRequestEntityRepository,
            BannerRequestNewService bannerRequestNewService,
            CacheService cacheService
    ) {
        this.vendorImageRequestEntityRepository = vendorImageRequestEntityRepository;
        this.bannerRequestNewService = bannerRequestNewService;
        this.cacheService = cacheService;
    }

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Integer create(SetVendorImageParameter request) {
        VendorImageRequestEntity result = setVendorImage(request);
        cacheService.GetRedisCacheEvict("VendorPictureLogo", String.valueOf(request.getWid()));
        cacheService.cacheEvictVendor(request.getWid());

        if(result != null) {
            WebAdminLoginUser userInfo = Utility.getUserInfo();
            bannerRequestNewService.insert(result.getImageRequestId(), request, userInfo.getUserId(), userInfo.getUsername());
        } else {
            return -99;
        }
        return 1;
    }

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Integer delete(DelVendorImageParameter request) {
        Integer wid = request.getWid();
        Integer type = request.getType();

        Integer result = delVendorImage(wid, type);
        cacheService.GetRedisCacheEvict("VendorPictureLogo", String.valueOf(wid));
        return result;
    }

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    public List<VendorImage> getVendorImage(Integer wid) {
        return vendorImageRequestEntityRepository.findByWholeSalerID(wid);
    }

    private VendorImageRequestEntity setVendorImage(SetVendorImageParameter request) {
        Integer wid = request.getWid();
        Integer type = request.getType();
        String fileName = request.getFilename();
        String userID = StringUtils.isEmpty(request.getUserid()) ? "admin" : request.getUserid();

        VendorImageRequestEntity vendorImage = null;
        try {
            if (type == 5) {
                vendorImage = vendorImageRequestEntityRepository.findOneByWholeSalerIDAndVendorImageTypeID(wid, type);
            } else {
                vendorImage = vendorImageRequestEntityRepository.findOneByWholeSalerIDAndVendorImageTypeIDAndActiveTrue(wid, type);
            }

            if (vendorImage == null) {
                vendorImage = new VendorImageRequestEntity();
                vendorImage.setActive(true);
                vendorImage.setOriginalFileName(fileName);
                vendorImage.setVendorImageTypeId(type);
                vendorImage.setDecidedOn(LocalDateTime.now());
                vendorImage.setRequestedOn(LocalDateTime.now());
                vendorImage.setWholesalerId(wid);
                vendorImage.setIsApproved(true);
                vendorImage.setDecidedBy(userID);
                vendorImageRequestEntityRepository.save(vendorImage);
            } else {
                vendorImage.setOriginalFileName(fileName);
                vendorImage.setVendorImageTypeId(type);
                vendorImage.setDecidedOn(LocalDateTime.now());
                vendorImage.setRequestedOn(LocalDateTime.now());
                vendorImage.setWholesalerId(wid);
                vendorImage.setDecidedBy(userID);
                vendorImageRequestEntityRepository.save(vendorImage);
            }
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
        }
        return vendorImage;
    }

    private Integer delVendorImage(Integer wid, Integer type) {
        try {
            VendorImageRequestEntity vendorImage = vendorImageRequestEntityRepository.findOneByWholeSalerIDAndVendorImageTypeID(wid, type);
            vendorImageRequestEntityRepository.delete(vendorImage);

            WebAdminLoginUser userInfo = Utility.getUserInfo();
            bannerRequestNewService.delete(wid, vendorImage.getImageRequestId(), userInfo.getUserId(), userInfo.getUsername());

            return 1;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return -99;
        }
    }
}
