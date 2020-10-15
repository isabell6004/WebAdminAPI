package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.model.vendor.response.*;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.service.renewal.RenewalVendorService;
import net.fashiongo.webadmin.service.vendor.VendorInfoNewService;
import net.fashiongo.webadmin.service.vendor.VendorInfoService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author roy
 */
@RestController
@Slf4j
public class VendorInfoController {

    private RenewalVendorService renewalVendorService;

    private VendorInfoService vendorInfoService;
    private VendorInfoNewService vendorInfoNewService;

    public VendorInfoController(RenewalVendorService renewalVendorService,
                                VendorInfoService vendorInfoService,
                                VendorInfoNewService vendorInfoNewService) {
        this.renewalVendorService = renewalVendorService;
        this.vendorInfoService = vendorInfoService;
        this.vendorInfoNewService = vendorInfoNewService;
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

    @PostMapping(value = "vendor/getvendorsettingdetail", produces = "application/json")
    public JsonResponse<VendorSettingDetailResponse> getvendorsettingdetail(@RequestBody GetVendorSettingParameter param) {
        JsonResponse<VendorSettingDetailResponse> response = new JsonResponse<>(false, null, null);

        try {
            VendorSettingDetailResponse result = vendorInfoNewService.getVendorSettingDetail((long)param.getWid());

            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            log.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "vendor/setvendorsetting")
    public ResultCode setvendorsetting(@RequestBody SetVendorSettingParameter request) {

        if (request.getWid() == null || request.getWid() == 0)
            return null;

        return vendorInfoService.setVendorSettingInfo(request);
    }

    @PostMapping(value = "vendor/setvendorbasicinfo", produces = "application/json")
    public Integer setvendorbasicinfo(@RequestBody SetVendorBasicInfoParameter request) {

        if (request.getWid() == null || request.getWid() == 0) {
            return null;
        }

        return vendorInfoService.update(request);
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
	
