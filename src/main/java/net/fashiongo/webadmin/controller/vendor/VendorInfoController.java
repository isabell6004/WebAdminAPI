package net.fashiongo.webadmin.controller.vendor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorBasicInfoResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorListCSVResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorSettingResponse;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.renewal.RenewalVendorService;
import net.fashiongo.webadmin.service.vendor.VendorInfoService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author roy
 */
@RestController
@Slf4j
public class VendorInfoController {

    private RenewalVendorService renewalVendorService;

    private VendorInfoService vendorInfoService;

    public VendorInfoController(RenewalVendorService renewalVendorService,
                                VendorInfoService vendorInfoService) {
        this.renewalVendorService = renewalVendorService;
        this.vendorInfoService = vendorInfoService;
    }

    @PostMapping(value = "vendor/getvendorsetting", produces = "application/json")
    public JsonResponse<GetVendorSettingResponse> getvendorsetting(@RequestBody GetVendorSettingParameter param) {
        JsonResponse<GetVendorSettingResponse> response = new JsonResponse<>(false, null, null);

        try {
            GetVendorSettingResponse result = renewalVendorService.getVendorSetting(param.getWid());

            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            log.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "vendor/setvendorsetting")
    public Integer setvendorsetting(@RequestBody SetVendorSettingParameter request) {

        if (request.getWid() == null || request.getWid() == 0)
            return null;

        return vendorInfoService.setVendorSettingInfo(request);
    }

    @PostMapping(value = "vendor/setvendorbasicinfo", produces = "application/json")
    public Integer setvendorbasicinfo(@RequestBody SetVendorBasicInfoParameter request) {

        if (request.getWid() == null || request.getWid() == 0)
            return null;

        Integer result = vendorInfoService.update(request);
        return result;
    }

    @PostMapping(value = "vendor/getvendorlist", produces = "application/json")
    public JsonResponse getvendorlist(@RequestBody GetVendorListParameter param) {
        JsonResponse response = new JsonResponse(false, null, null);

        try {
            GetVendorListResponse data = renewalVendorService.getVendorList(param);

            response.setSuccess(true);
            response.setData(data);
            response.setMessage("success");
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            response.setMessage("fail");
        }
        return response;
    }

    @PostMapping(value = "vendor/getvendorlistcsv", produces = "application/json")
    public JsonResponse getvendorlistcsv(@RequestBody GetVendorListParameter param) {
        JsonResponse response = new JsonResponse(false, null, null);

        try {
            GetVendorListCSVResponse data = renewalVendorService.getvendorlistcsv(param);

            response.setSuccess(true);
            response.setData(data);
            response.setMessage("success");
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            response.setMessage("fail");
        }
        return response;
    }

    @PostMapping(value = "vendor/getvendorbasicinfo", produces = "application/json")
    public JsonResponse<GetVendorBasicInfoResponse> getvendorbasicinfo(@RequestBody GetVendorBasicInfoParameter param) {
        JsonResponse<GetVendorBasicInfoResponse> response = new JsonResponse<>(false, null, null);
        Integer wid = param.getWid();

        try {
            response.setData(renewalVendorService.getVendorDetailInfo(wid));
            response.setSuccess(true);
        } catch (Exception ex) {
            log.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

}
	
