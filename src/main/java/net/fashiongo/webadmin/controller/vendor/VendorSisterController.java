package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.service.vendor.VendorSNSService;
import net.fashiongo.webadmin.service.vendor.VendorSisterAccountService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author roy
 */
@RestController
@Slf4j
public class VendorSisterController {

    private VendorSisterAccountService vendorSisterAccountService;

    public VendorSisterController(
            VendorSisterAccountService vendorSisterAccountService
    ) {
        this.vendorSisterAccountService = vendorSisterAccountService;
    }

    @PostMapping(value = "vendor/getvendorsister")
    public JsonResponse<List<VendorSister>> getvendorsister(@RequestBody GetVendorSisterParameter param) {
        JsonResponse<List<VendorSister>> response = new JsonResponse<>(false, null, null);

        try {
            List<VendorSister> result = vendorSisterAccountService.getVendorSister(param.getWid());

            response.setSuccess(true);
            response.setData(result);
            response.setMessage("success");
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            response.setMessage("fail");
        }
        return response;
    }

    @PostMapping(value = "vendor/getvendorsisterchk")
    public JsonResponse<List<Integer>> getvendorsisterchk(@RequestBody GetVendorSisterChkParameter param) {
        JsonResponse<List<Integer>> response = new JsonResponse<>(false, null, null);

        try {
            List<Integer> result = vendorSisterAccountService.getVendorSisterChk(param.getWid(), param.getSisterid());

            response.setSuccess(true);
            response.setData(result);
            response.setMessage("success");
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            response.setMessage("fail");
        }
        return response;
    }

    @PostMapping(value = "vendor/setvendorsister")
    public ResultCode setvendorsister(@RequestBody SetVendorSisterParamer param) {
        if (vendorSisterAccountService.setVendorSister(param.getWid(), param.getSisterid())) {
            return new ResultCode(true, 1, "success");
        } else {
            return new ResultCode(false, -1, "savefailure");
        }
    }

    @PostMapping(value = "vendor/delvendorsister")
    public ResultCode delvendorsister(@RequestBody DelVendorSisterParameter param) {

        if (vendorSisterAccountService.delVendorSister(param.getMapID())) {
            return new ResultCode(true, 1, "success");
        } else {
            return new ResultCode(false, -1, "deletefailure");
        }
    }

}
	
