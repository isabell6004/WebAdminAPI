package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerSisterEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorSister;
import net.fashiongo.webadmin.data.repository.primary.MapWholeSalerSisterEntityRepository;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.SisterVendorNewService;
import net.fashiongo.webadmin.service.vendor.SisterVendorService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SisterVendorServiceImpl implements SisterVendorService {

    private final SisterVendorNewService sisterVendorNewService;
    private final MapWholeSalerSisterEntityRepository mapWholeSalerSisterEntityRepository;
    private CacheService cacheService;

    public SisterVendorServiceImpl(SisterVendorNewService sisterVendorNewService,
                                   MapWholeSalerSisterEntityRepository mapWholeSalerSisterEntityRepository,
                                   CacheService cacheService) {
        this.sisterVendorNewService = sisterVendorNewService;
        this.mapWholeSalerSisterEntityRepository = mapWholeSalerSisterEntityRepository;
        this.cacheService = cacheService;
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    public List<VendorSister> getSisterVendors(Integer wid) {
        List<VendorSister> result = mapWholeSalerSisterEntityRepository.findVendorSister(wid);
        return result;
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    public List<Integer> getSisterVendorChecks(Integer wid, Integer sisterId) {
        return mapWholeSalerSisterEntityRepository.findMapIDByWholeSalerIDAndSisterWholeSalerID(wid, sisterId);
    }

    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Boolean setSisterVendor(Integer wid, Integer sisterVendorId) {
        try {
            MapWholeSalerSisterEntity trm = MapWholeSalerSisterEntity.create(wid, sisterVendorId, Utility.getUsername());
            mapWholeSalerSisterEntityRepository.save(trm);
            cacheService.cacheEvictVendor(wid);

            WebAdminLoginUser userInfo = Utility.getUserInfo();
            sisterVendorNewService.createSisterVendor(wid, trm.getMapID(), sisterVendorId, userInfo.getUserId(), userInfo.getUsername());

            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public Boolean deleteSisterVendor(Integer mapId) {
        try {
            MapWholeSalerSisterEntity trm = mapWholeSalerSisterEntityRepository.findById(mapId).orElseThrow(() -> new RuntimeException("can not find the data."));
            mapWholeSalerSisterEntityRepository.delete(trm);

            WebAdminLoginUser userInfo = Utility.getUserInfo();
            sisterVendorNewService.deleteSisterVendor(trm.getWholeSalerID(), mapId, userInfo.getUserId(), userInfo.getUsername());
            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        }
    }
}
