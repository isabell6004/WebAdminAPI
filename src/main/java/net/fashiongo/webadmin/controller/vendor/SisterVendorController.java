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
public class SisterVendorController {

    private final SisterVendorService sisterVendorService;

    public SisterVendorController(SisterVendorService sisterVendorService) {
        this.sisterVendorService = sisterVendorService;
    }

    @PostMapping(value = "vendor/getvendorsister", produces = "application/json")
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

    @PostMapping(value = "vendor/getvendorsisterchk", produces = "application/json")
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

    @PostMapping(value = "vendor/setvendorsister", produces = "application/json")
    public ResultCode setVendorSister(@RequestBody SetVendorSisterParamer param) {
        if (sisterVendorService.setSisterVendor(param.getWid(), param.getSisterid())) {
            return new ResultCode(true, 1, "success");
        } else {
            return new ResultCode(false, -1, "savefailure");
        }
    }

    @PostMapping(value = "vendor/delvendorsister", produces = "application/json")
    public ResultCode deleteSisterVendor(@RequestBody DelVendorSisterParameter param) {
        if (sisterVendorService.deleteSisterVendor(param.getMapID())) {
            return new ResultCode(true, 1, "success");
        } else {
            return new ResultCode(false, -1, "deletefailure");
        }
    }
}
