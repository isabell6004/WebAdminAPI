package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.BannerRequestResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetBannerRequestParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.model.primary.ListVendorImageType;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.vendor.BannerRequestService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BannerRequestController {

    private final BannerRequestService bannerRequestService;
    private final CacheService cacheService;

    public BannerRequestController(BannerRequestService bannerRequestService,
                                   CacheService cacheService) {
        this.bannerRequestService = bannerRequestService;
        this.cacheService = cacheService;
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 13.
     * @author Reo
     * @return
     */
    @PostMapping(value = "vendor/getvendorimagetype", produces = "application/json")
    public JsonResponse<List<ListVendorImageType>> getVendorImageType() {
        JsonResponse<List<ListVendorImageType>> response = new JsonResponse<>(false, null, 0, null);

        try {
            response.setSuccess(true);
            response.setData(bannerRequestService.getVendorImageType());
        } catch (Exception e) {
            log.error("fail to get vendor image type", e);
            response.setMessage("failure");
        }

        return response;
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 13.
     * @author Reo
     * @param parameters
     * @return
     */
    @PostMapping(value = "vendor/getbannerrequest", produces = "application/json")
    public JsonResponse<BannerRequestResponse> getBannerRequest(@RequestBody GetBannerRequestParameter parameters) {
        JsonResponse<BannerRequestResponse> response = new JsonResponse<>(false, null, null);

        try {
            response.setSuccess(true);
            response.setData(bannerRequestService.getBannerRequest(parameters));
        } catch (Exception e) {
            log.error("fail to get banner requests", e);
            response.setMessage("failure");
        }

        return response;
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 13.
     * @author Reo
     * @param parameters
     * @return
     */
    @PostMapping(value = "vendor/setdenybanner", produces = "application/json")
    public JsonResponse<ResultCode> denyBannerRequest(@RequestBody SetDenyBannerParameter parameters) {
        JsonResponse<ResultCode> response = new JsonResponse<>(false, null, 0, null);

        try {
            bannerRequestService.setDenyBanner(parameters);

            response.setSuccess(true);
            response.setData(new ResultCode(true, 1, null));

            cacheService.GetRedisCacheEvict("vendorActivated", null);
            cacheService.GetRedisCacheEvict("vendorDeactivated", null);
        } catch (Exception e) {
            log.error("fail to deny banner request", e);
            response.setMessage("failure");
        }

        return response;
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 13.
     * @author Reo
     * @param parameters
     * @return
     */
    @PostMapping(value = "vendor/setapprovebanner", produces = "application/json")
    public JsonResponse<ResultCode> approveBannerRequest(@RequestBody SetDenyBannerParameter parameters) {
        JsonResponse<ResultCode> response = new JsonResponse<>(false, null, 0, null);

        try {
            bannerRequestService.setApproveBanner(parameters);

            response.setSuccess(true);
            response.setData(new ResultCode(true, 1, null));

            cacheService.GetRedisCacheEvict("vendorActivated", null);
            cacheService.GetRedisCacheEvict("vendorDeactivated", null);
        } catch (Exception e) {
            log.error("fail to approve banner request", e);
            response.setMessage("failure");
        }

        return response;
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 13.
     * @author Reo
     * @param parameters
     * @return
     */
    @Deprecated
    @PostMapping(value = "vendor/setrestorebanner", produces = "application/json")
    public JsonResponse<ResultCode> restoreBannerRequest(@RequestBody SetDenyBannerParameter parameters) {
        JsonResponse<ResultCode> response = new JsonResponse<>(false, null, 0, null);

        try {
            bannerRequestService.setRestoreBanner(parameters);

            response.setSuccess(true);
            response.setData(new ResultCode(true, 1, null));

            cacheService.GetRedisCacheEvict("vendorActivated", null);
            cacheService.GetRedisCacheEvict("vendorDeactivated", null);
        } catch (Exception e) {
            log.error("fail to restore banner request", e);
            response.setMessage("failure");
        }

        return response;
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 13.
     * @author Reo
     * @param parameters
     * @return
     */
    @PostMapping(value = "delbannerbanner", produces = "application/json")
    public JsonResponse<ResultCode> deleteBannerRequest(@RequestBody SetDenyBannerParameter parameters) {
        JsonResponse<ResultCode> response = new JsonResponse<>(false, null, 0, null);

        try {
            bannerRequestService.delBannerRequest(parameters);

            response.setSuccess(true);
            response.setData(new ResultCode(true, 1, "Deleted successfully!"));
        } catch (Exception e) {
            log.error("fail to delete banner request", e);
            response.setMessage("failure");
        }

        return response;
    }
}
