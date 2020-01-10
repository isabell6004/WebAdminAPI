package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerSisterEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorSister;
import net.fashiongo.webadmin.data.repository.primary.MapWholeSalerSisterEntityRepository;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.VendorSisterAccountNewService;
import net.fashiongo.webadmin.service.vendor.VendorSisterAccountService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jinwoo on 2020-01-09.
 */
@Slf4j
@Service
public class VendorSisterAccountServiceImpl implements VendorSisterAccountService {

    private MapWholeSalerSisterEntityRepository mapWholeSalerSisterEntityRepository;

    private CacheService cacheService;

    private VendorSisterAccountNewService vendorSisterAccountNerService;

    public VendorSisterAccountServiceImpl(
            MapWholeSalerSisterEntityRepository mapWholeSalerSisterEntityRepository,
            VendorSisterAccountNewService vendorSisterAccountNerService,
            CacheService cacheService
    ) {
        this.mapWholeSalerSisterEntityRepository = mapWholeSalerSisterEntityRepository;
        this.vendorSisterAccountNerService = vendorSisterAccountNerService;
        this.cacheService = cacheService;
    }

    public List<VendorSister> getVendorSister(Integer wid) {
        List<VendorSister> result = mapWholeSalerSisterEntityRepository.findVendorSister(wid);

        return result;
    }

    public List<Integer> getVendorSisterChk(Integer wid, Integer sisterId) {
        List<Integer> result = mapWholeSalerSisterEntityRepository.findMapIDByWholeSalerIDAndSisterWholeSalerID(wid, sisterId);

        return result;
    }

    @Transactional
    public Boolean setVendorSister(Integer wid, Integer sisterVendorId) {
        MapWholeSalerSisterEntity trm = new MapWholeSalerSisterEntity();
        try {
            trm.setWholeSalerID(wid);
            trm.setSisterWholeSalerID(sisterVendorId);
            trm.setCreatedBy(Utility.getUsername());
            trm.setCreatedOn(LocalDateTime.now());

            mapWholeSalerSisterEntityRepository.save(trm);
            cacheService.cacheEvictVendor(wid);

            vendorSisterAccountNerService.regist(wid, sisterVendorId);

            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        }
    }

    @Transactional
    public Boolean delVendorSister(Integer mapID) {
        try {
            MapWholeSalerSisterEntity trm = mapWholeSalerSisterEntityRepository.findById(mapID).orElseThrow(() -> new RuntimeException("can not find the data."));
            mapWholeSalerSisterEntityRepository.delete(trm);

            vendorSisterAccountNerService.delete(trm.getWholeSalerID(), trm.getSisterWholeSalerID());

            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            return false;
        }
    }

}
