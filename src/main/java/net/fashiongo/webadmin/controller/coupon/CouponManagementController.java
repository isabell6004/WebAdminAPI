package net.fashiongo.webadmin.controller.coupon;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.primary.coupon.command.*;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponDto;
import net.fashiongo.webadmin.model.primary.coupon.dto.CouponNotificationDto;
import net.fashiongo.webadmin.service.coupon.impl.CouponManagementServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/coupon", consumes = "application/json", produces = "application/json")
public class CouponManagementController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CouponManagementServiceImpl couponManagementService;

    @GetMapping(value = "/list")
    public JsonResponse<PagedResult<CouponDto>> getCoupons(@ModelAttribute CouponQueryParam q) {

        JsonResponse<PagedResult<CouponDto>> response = new JsonResponse<>(false, null, null);

        try {
            PagedResult<CouponDto> result = couponManagementService.getCoupons(q.getPn(), q.getPs());

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "/create")
    public JsonResponse<CouponDto> createCoupon(@RequestBody @Valid CouponCreateInput createRequest) {

        JsonResponse<CouponDto> response = new JsonResponse<>(false, null,  null);

        try {
            CouponDto result = couponManagementService.createCoupon(createRequest);

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "/{couponId}/update")
    public JsonResponse<CouponDto> createCoupon(@PathVariable("couponId") Long couponId,
                                                @RequestBody @Valid CouponUpdateInput updateRequest) {

        JsonResponse<CouponDto> response = new JsonResponse<>(false, null,  null);

        try {
            CouponDto result = couponManagementService.updateCoupon(couponId, updateRequest);

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping(value = "/{couponId}/activate")
    public JsonResponse<Boolean> activateCoupon(@PathVariable("couponId") Long couponId) {

        JsonResponse<Boolean> response = new JsonResponse<>(false, null, null);

        try {
            boolean result = couponManagementService.activateCoupon(couponId);

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping(value = "/{couponId}/deactivate")
    public JsonResponse<Boolean> deactivateCoupon(@PathVariable("couponId") Long couponId) {

        JsonResponse<Boolean> response = new JsonResponse<>(false, null, null);

        try {
            boolean result = couponManagementService.deactivateCoupon(couponId);

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @DeleteMapping(value = "/{couponId}/delete")
    public JsonResponse<Boolean> deleteCoupon(@PathVariable("couponId") Long couponId) {

        JsonResponse<Boolean> response = new JsonResponse<>(false, null, null);

        try {
            boolean result = couponManagementService.deleteCoupon(couponId);

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping(value = "/{couponId}/notification")
    public JsonResponse<CouponNotificationDto> getCouponNotification(@PathVariable("couponId") Long couponId) {

        JsonResponse<CouponNotificationDto> response = new JsonResponse<>(false, null, null);

        try {
            CouponNotificationDto result = couponManagementService.getCouponNotifications(couponId);

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "/{couponId}/notification/create",  headers="content-type=multipart/*",  consumes = {"multipart/form-data"})
    public JsonResponse<CouponNotificationDto> createCouponNotification(@PathVariable("couponId") Long couponId,
                                                                        @RequestPart(name = "notification") @Valid CouponNotificationInput input,
                                                                        @RequestPart(name = "targetFile", required = false) MultipartFile targetFile,
                                                                        @RequestPart(name = "imageFile", required = false) MultipartFile imageFile) {

        JsonResponse<CouponNotificationDto> response = new JsonResponse<>(false, null, null);

        try {
            CouponNotificationDto result = couponManagementService.createCouponNotification(couponId, input, targetFile, imageFile);

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "/{couponId}/notification/{couponNotificationId}/update", headers="content-type=multipart/*", consumes = {"multipart/form-data"})
    public JsonResponse<CouponNotificationDto> updateCouponNotification(@PathVariable("couponId") Long couponId,
                                                                        @PathVariable("couponNotificationId") Long couponNotificationId,
                                                                        @RequestPart(name = "notification") @Valid CouponNotificationInput input,
                                                                        @RequestPart(name = "targetFile", required = false) MultipartFile targetFile,
                                                                        @RequestPart(name = "imageFile", required = false) MultipartFile imageFile) {

        JsonResponse<CouponNotificationDto> response = new JsonResponse<>(false, null, null);

        try {
            CouponNotificationDto result = couponManagementService.updateCouponNotification(couponId, couponNotificationId, input, targetFile, imageFile);

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }


    @DeleteMapping(value = "/{couponId}/notification/{couponNotificationId}/delete")
    public JsonResponse<Boolean> deleteCouponNotification(@PathVariable("couponId") Long couponId,
                                                          @PathVariable("couponNotificationId") Long couponNotificationId) {

        JsonResponse<Boolean> response = new JsonResponse<>(false, null, null);

        try {
            boolean result = couponManagementService.deleteCouponNotification(couponId, couponNotificationId);

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping(value = "/code/{couponCodeId}/{couponCode}/unique")
    public JsonResponse<Boolean> checkCouponCodeUniqueness(@PathVariable("couponCodeId") Long couponCodeId,
                                                           @PathVariable("couponCode") String couponCode) {

        JsonResponse<Boolean> response = new JsonResponse<>(false, null, null);

        try {
            boolean result = couponManagementService.checkCouponCodeUnique(couponCode, couponCodeId);

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping(value = "/options")
    public JsonResponse<CouponOptionOutput> getCouponOptions() {

        JsonResponse<CouponOptionOutput> response = new JsonResponse<>(false, null, null);

        try {
            CouponOptionOutput result = couponManagementService.getCouponOptions();

            response.setSuccess(true);
            response.setMessage("success");
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }
}