package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetVendorSNSListParameter;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.service.vendor.VendorSNSService;
import org.springframework.web.bind.annotation.*;

/**
 * @author roy
 */
@RestController
@Slf4j
public class VendorSNSController {

    private VendorSNSService vendorSNSService;

    public VendorSNSController(
            VendorSNSService vendorSNSService
    ) {
        this.vendorSNSService = vendorSNSService;
    }
    @PostMapping(value = "vendor/setvendorsnslist")
    public ResultCode setvendorsnslist(@RequestBody SetVendorSNSListParameter param) {
        if(vendorSNSService.modifyVendorSNSInfo(param)) {
            return new ResultCode(true, 1, "success");
        } else {
            return new ResultCode(false, -1, "failure");
        }
    }

}
	
