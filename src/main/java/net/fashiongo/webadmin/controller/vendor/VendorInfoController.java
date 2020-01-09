package net.fashiongo.webadmin.controller.vendor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.*;
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

    private CacheService cacheService;

    private VendorInfoService vendorInfoService;

    public VendorInfoController(RenewalVendorService renewalVendorService,
                                CacheService cacheService,
                                VendorInfoService vendorInfoService) {
        this.renewalVendorService = renewalVendorService;
        this.cacheService = cacheService;
        this.vendorInfoService = vendorInfoService;
    }

    @PostMapping(value = "vendor/getvendorsetting")
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
    public Integer setvendorsetting(@RequestBody SetVendorSettingParameter param) {
        Integer wid = param.getWid();
        Integer adminAccount = param.getAdminAccount() == null ? 0 : param.getAdminAccount();
        Integer vendorCategory = param.getVendorCategory() == null ? 0 : param.getVendorCategory();
        Integer fraudReport = param.getFraudReport() == null ? 0 : param.getFraudReport();
        Integer item = param.getItem() == null ? 0 : param.getItem();
        Integer adminAccountID = param.getAdminAccountID() == null ? 0 : param.getAdminAccountID();
        Integer vendorCategoryID = param.getVendorCategoryID() == null ? 0 : param.getVendorCategoryID();
        Integer fraudReportID = param.getFraudReportID() == null ? 0 : param.getFraudReportID();
        Integer itemID = param.getItemID() == null ? 0 : param.getItemID();
        LocalDateTime actualOpenDateTemp = StringUtils.isEmpty(param.getActualOpenDateTemp()) ? null : LocalDateTime.parse(param.getActualOpenDateTemp());

        renewalVendorService.setVendorSetting(wid, adminAccountID, 1, adminAccount);
        renewalVendorService.setVendorSetting(wid, vendorCategoryID, 2, vendorCategory);
        renewalVendorService.setVendorSetting(wid, fraudReportID, 3, fraudReport);
        renewalVendorService.setVendorSetting(wid, itemID, 4, item);

        Integer payoutSchedule = param.getPayoutSchedule();
        Integer payoutScheduleWM = param.getPayoutScheduleWM();
        Integer maxPayoutPerDay = param.getMaxPayoutPerDay();
        Integer payoutCount = param.getPayoutCount();

        ObjectMapper mapper = new ObjectMapper();
        VendorDetailInfo r;
        try {
            r = mapper.readValue(param.getVendorBasicInfo(), VendorDetailInfo.class);
        } catch (IOException e) {
            log.debug("object mapper parse error");
            return null;
        }

        // TODO
        Integer result = vendorInfoService.setVendorBasicInfo(r, 2, payoutSchedule, payoutScheduleWM, maxPayoutPerDay, payoutCount);

        if (result == -1) {
            return result;
        }

        String dirNameTemp = StringUtils.isEmpty(param.getDirNameTemp()) ? "" : param.getDirNameTemp();

        cacheService.cacheEvictVendor(wid);

        return result;
    }

    @PostMapping(value = "vendor/setvendorbasicinfo", produces = "application/json")
    public Integer setvendorbasicinfo(@RequestBody SetVendorBasicInfoParameter request) {

        if (request.getWid() == null || request.getWid() == 0)
            return null;

        Integer result = vendorInfoService.update(request);
        return result;
    }

    @PostMapping(value = "vendor/getvendorlist")
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

    @PostMapping(value = "vendor/getvendorlistcsv")
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

}
	
