package net.fashiongo.webadmin.service.vendor.impl;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerSisterEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorSister;
import net.fashiongo.webadmin.data.repository.primary.MapWholeSalerSisterEntityRepository;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.service.vendor.SisterVendorNewService;
import net.fashiongo.webadmin.service.vendor.SisterVendorService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SisterVendorServiceImpl implements SisterVendorService {

    private final SisterVendorNewService sisterVendorNewService;
    private final MapWholeSalerSisterEntityRepository mapWholeSalerSisterEntityRepository;

    public SisterVendorServiceImpl(SisterVendorNewService sisterVendorNewService,
                                   MapWholeSalerSisterEntityRepository mapWholeSalerSisterEntityRepository) {
        this.sisterVendorNewService = sisterVendorNewService;
        this.mapWholeSalerSisterEntityRepository = mapWholeSalerSisterEntityRepository;
    }

    @Override
    public List<VendorSister> getSisterVendors(Integer vendorId) {
        return mapWholeSalerSisterEntityRepository.findVendorSister(vendorId);
    }

    @Override
    public List<Integer> getSisterVendorChecks(Integer vendorId, Integer sisterVendorId) {
        return mapWholeSalerSisterEntityRepository.findMapIDByWholeSalerIDAndSisterWholeSalerID(vendorId, sisterVendorId);
    }

    @Override
    @Transactional
    public ResultCode setSisterVendor(Integer vendorId, Integer sisterVendorId) {
        ResultCode result = new ResultCode(false, null, null);

        try {
            MapWholeSalerSisterEntity mapWholeSalerSisterEntity = MapWholeSalerSisterEntity.create(vendorId, sisterVendorId, Utility.getUsername());
            mapWholeSalerSisterEntityRepository.save(mapWholeSalerSisterEntity);

            // TODO: call vendor API
            sisterVendorNewService.createSisterVendor(vendorId, sisterVendorId);

            result.setSuccess(true);
            result.setResultCode(1);
            result.setResultMsg("success");
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            result.setResultCode(-1);
            result.setResultMsg("savefailure");
        }

        return result;
    }

    @Override
    @Transactional
    public ResultCode deleteSisterVendor(Integer mapId) {
        ResultCode result = new ResultCode(false, null, null);

        try {
            MapWholeSalerSisterEntity mapWholeSalerSisterEntity = mapWholeSalerSisterEntityRepository.findById(mapId).get();
            mapWholeSalerSisterEntityRepository.delete(mapWholeSalerSisterEntity);

            // TODO: call vendor API
            sisterVendorNewService.deleteSisterVendor(mapWholeSalerSisterEntity.getWholeSalerID(), mapId);

            result.setSuccess(true);
            result.setResultCode(1);
            result.setResultMsg("deletesuccess");
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            result.setResultCode(-1);
            result.setResultMsg("deletefailure");
        }

        return result;
    }
}
