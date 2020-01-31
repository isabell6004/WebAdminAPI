package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.DelVendorImageParameter;
import net.fashiongo.webadmin.data.model.vendor.GetVendorImageParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorImageParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorImage;
import net.fashiongo.webadmin.service.vendor.VendorImageService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author roy
 */
@RestController
@Slf4j
public class VendorImageController {

    private VendorImageService vendorImageService;
    public VendorImageController(@Qualifier("vendorBannerImageService") VendorImageService vendorImageService) {
        this.vendorImageService = vendorImageService;
    }

    @PostMapping(value = "vendor/getvendorimage", produces = "application/json")
    public JsonResponse<List<VendorImage>> getvendorimage(@RequestBody GetVendorImageParameter param) {
        JsonResponse<List<VendorImage>> response = new JsonResponse<>(false, null, null);
        Integer wid = param.getWid();

        try {
            response.setData(vendorImageService.getVendorImage(wid));
            response.setSuccess(true);
        } catch (Exception ex) {
            log.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "vendor/setvendorimage")
    public Integer setvendorimage(@RequestBody SetVendorImageParameter param) {

        if (param.getWid() == null|| param.getType() == null || StringUtils.isEmpty(param.getFilename())) {
            return -1;
        }

        return vendorImageService.create(param);
    }

    @PostMapping(value = "vendor/delvendorimage")
    public Integer delvendorimage(@RequestBody DelVendorImageParameter param) {
        if(param.getWid() == null | param.getType() == null) {
            return -1;
        }

        return vendorImageService.delete(param);
    }

}
	
