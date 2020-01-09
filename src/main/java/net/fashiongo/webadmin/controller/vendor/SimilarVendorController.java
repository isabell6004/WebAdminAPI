package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetVendorGroupingParameter;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorGroupingResponse;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.SimilarVendorService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value="/vendor", produces = "application/json")
public class SimilarVendorController {

    private final SimilarVendorService similarVendorService;
    private final CacheService cacheService;

    public SimilarVendorController(SimilarVendorService similarVendorService, CacheService cacheService) {
        this.similarVendorService = similarVendorService;
        this.cacheService = cacheService;
    }

    @GetMapping(value = "getvendorgrouping")
    public JsonResponse<GetVendorGroupingResponse> getVendorGroupings(
            @RequestParam(value = "wholesalerid") Integer vendorId,
            @RequestParam(value = "CompanyType") String companyType,
            @RequestParam(value = "vendortype") String vendorType,
            @RequestParam(value = "searchkeyword") String keyword,
            @RequestParam(value = "Categorys") String categorys,
            @RequestParam(value = "alphabet") String alphabet) {
        JsonResponse<GetVendorGroupingResponse> response = new JsonResponse<>(false, null, null);

        try {
            response.setSuccess(true);
            response.setMessage("success");
            response.setData(similarVendorService.getVendorGroupings(vendorId, companyType, vendorType, keyword, categorys, alphabet));
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            response.setMessage("fail");
        }

        return response;
    }

    @PostMapping(value = "setvendorgrouping")
    public JsonResponse<Integer> setVendorGrouping(@RequestBody SetVendorGroupingParameter param) {
        JsonResponse<Integer> response = new JsonResponse<>(false, null, null);

        Integer wid = param.getWid() == null ? 0 : param.getWid();
        String saveIds = StringUtils.isEmpty(param.getSaveIds()) ? "" : param.getSaveIds();
        String deleteIds = StringUtils.isEmpty(param.getDeleteIds()) ? "" : param.getDeleteIds();

        try {
            response.setSuccess(true);
            response.setMessage("success");
            response.setData(similarVendorService.setVendorGrouping(wid, saveIds, deleteIds));
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            response.setMessage("fail");
        }

        cacheService.cacheEvictVendor(wid);

        return response;
    }
}
