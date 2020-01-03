package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.SisterVendorService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value="/vendor", produces = "application/json")
public class SisterVendorController {

    private final SisterVendorService sisterVendorService;
    private final CacheService cacheService;

    public SisterVendorController(SisterVendorService sisterVendorService,
                                  CacheService cacheService) {
        this.sisterVendorService = sisterVendorService;
        this.cacheService = cacheService;
    }

    @PostMapping("getvendorsister")
    public JsonResponse<List<VendorSister>> getSisterVendors(@RequestBody GetVendorSisterParameter param) {
        JsonResponse<List<VendorSister>> response = new JsonResponse<>(false, null, null);

        try {
            List<VendorSister> result = sisterVendorService.getSisterVendors(param.getWid());

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            response.setMessage("fail");
        }
        return response;
    }

    @PostMapping("getvendorsisterchk")
    public JsonResponse<List<Integer>> getSisterVendorChecks(@RequestBody GetVendorSisterChkParameter param) {
        JsonResponse<List<Integer>> response = new JsonResponse<>(false, null, null);

        try {
            List<Integer> result = sisterVendorService.getSisterVendorChecks(param.getWid(), param.getSisterid());

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            response.setMessage("fail");
        }
        return response;
    }

    @PostMapping("setvendorsister")
    public ResultCode setSisterVendor(@RequestBody SetVendorSisterParamer param) {
        ResultCode result = sisterVendorService.setSisterVendor(param.getWid(), param.getSisterid());

        cacheService.cacheEvictVendor(param.getWid());

        return result;
    }

    @PostMapping(value = "delvendorsister")
    public ResultCode deleteSisterVendor(@RequestBody DelVendorSisterParameter param) {
        return sisterVendorService.deleteSisterVendor(param.getMapID());
    }
}
