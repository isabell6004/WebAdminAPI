package net.fashiongo.webadmin.controller;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.photostudio.*;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.payment.parameter.QueryParam;
import net.fashiongo.webadmin.service.PhotoStudioService;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Andy
 */
@RestController
@RequestMapping(value = "/photo-studio")
public class PhotoStudioController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhotoStudioService photoStudioService;

    @GetMapping("/categories")
    public JsonResponse<?> getCategories() {
        logger.debug("PhotoStudioController.getCategories() called!!!");
        JsonResponse<List<PhotoCategory>> response = new JsonResponse<>(false, null, null);

        try {
            List<PhotoCategory> categories = photoStudioService.getCategories();
            response.setSuccess(true);
            response.setData(categories);
        } catch (Exception ex) {
            logger.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/prices")
    public JsonResponse<PhotoPriceListResponse> getPhotoPrices() {
        logger.debug("PhotoStudioController.getPhotoPrices() called!!!");
        JsonResponse<PhotoPriceListResponse> response = new JsonResponse<>(false, null, null);

        try {
            PhotoPriceListResponse result = photoStudioService.getPhotoPrices();
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getPhotoPrices()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "/prices/save")
    public JsonResponse<?> savePrices(@RequestBody Map<String, List<PhotoPrice>> parmMap) {
        logger.debug("PhotoStudioController.savePrices() called!!!");
        JsonResponse<String> response = new JsonResponse<>(false, null, null);

        try {
            String resultMsg = photoStudioService.savePrices(parmMap);
            response.setSuccess(StringUtils.isEmpty(resultMsg));
            response.setMessage(resultMsg);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.savePrices():", ex);
        }

        return response;
    }

    @GetMapping("/cancellationfees")
    public JsonResponse<?> getCancellationfees() {
        logger.debug("PhotoStudioController.getCancellationfees() called!!!");
        JsonResponse<Map<String, Object>> response = new JsonResponse<>(false, null, null);

        try {
            Map<String, Object> result = photoStudioService.getCancellationfees();
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getCancellationfees()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "/cancellationfees/save")
    public JsonResponse<?> saveCancellationfees(@RequestBody Map<String, List<PhotoCancellationFee>> parmMap) {
        logger.debug("PhotoStudioController.saveCancellationfees() called!!!");
        JsonResponse<String> response = new JsonResponse<>(false, null, null);

        try {
            String resultMsg = photoStudioService.saveCancellationFees(parmMap);
            response.setSuccess(StringUtils.isEmpty(resultMsg));
            response.setMessage(resultMsg);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.saveCancellationfees():", ex);
        }

        return response;
    }

    @GetMapping("/units")
    public JsonResponse<PhotoUnitListResponse> getPhotoUnits() {
        logger.debug("PhotoStudioController.getPhotoUnits() called!!!");
        JsonResponse<PhotoUnitListResponse> response = new JsonResponse<>(false, null, null);

        try {
            PhotoUnitListResponse result = photoStudioService.getPhotoUnits();
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getPhotoUnits()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "/unit/save")
    public JsonResponse<?> savePhotoUnits(@RequestBody Map<String, List<PhotoUnit>> parmMap) {
        logger.debug("PhotoStudioController.savePhotoUnit() called!!!");
        JsonResponse<String> response = new JsonResponse<>(false, null, null);

        try {
            String resultMsg = photoStudioService.savePhotoUnits(parmMap);
            response.setSuccess(StringUtils.isEmpty(resultMsg));
            response.setMessage(resultMsg);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.savePhotoUnit():", ex);
        }

        return response;
    }

    @GetMapping("/discounts")
    public JsonResponse<?> getDiscounts() {
        logger.debug("PhotoStudioController.getDiscounts() called!!!");
        JsonResponse<List<PhotoDiscount>> response = new JsonResponse<>(false, null, null);

        try {
            List<PhotoDiscount> discounts = photoStudioService.getDiscounts();
            response.setSuccess(true);
            response.setData(discounts);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getDiscounts()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/discount/{discountID}")
    public JsonResponse<?> getDiscount(@PathVariable("discountID") Integer discountID) {
        logger.debug("PhotoStudioController.getDiscount() called!!!");
        JsonResponse<PhotoDiscount> response = new JsonResponse<>(false, null, null);

        try {
            PhotoDiscount discount = photoStudioService.getDiscount(discountID);
            response.setSuccess(true);
            response.setData(discount);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getDiscount()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/discount/check")
    public JsonResponse<PhotoDiscount> checkDiscountCode(@RequestParam("code") String code) {
        logger.debug("PhotoStudioController.checkDiscountCode() called!!!");
        JsonResponse<PhotoDiscount> response = new JsonResponse<>(false, null, null);

        try {
            PhotoDiscount discount = photoStudioService.checkDiscountCode(code);
            response.setSuccess(true);
            response.setData(discount);
        } catch (Exception e) {
            logger.error("Exception Error: PhotoStudioController.getDiscount()：", e);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @PostMapping(value = "/discount/save")
    public JsonResponse<?> saveDiscount(@RequestBody PhotoDiscount photoDiscount) {
        logger.debug("PhotoStudioController.saveDiscount() called!!!");
        JsonResponse<?> response = new JsonResponse<>(false, null, null);

        try {
            String message = photoStudioService.saveDiscount(photoDiscount);
            response.setSuccess(StringUtils.isEmpty(message));
            response.setMessage(message);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.saveDiscount():", ex);
        }

        return response;
    }

    @GetMapping(value = "/discount/{discountID}/delete")
    public JsonResponse<?> deleteDiscount(@PathVariable("discountID") Integer discountID) {
        logger.debug("PhotoStudioController.deleteDiscount() called!!!");
        JsonResponse<String> response = new JsonResponse<>(false, null, null);

        try {
            boolean bSuccess = photoStudioService.deleteDiscount(discountID);
            response.setSuccess(bSuccess);
            response.setData("");
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.deleteDiscount():", ex);
        }

        return response;
    }

    @PostMapping(value = "/model/save")
    public JsonResponse<?> saveModel(@RequestBody PhotoModel photoModel) {
        logger.debug("PhotoStudioController.addModel() called!!!");
        JsonResponse<Integer> response = new JsonResponse<>(false, null, null);

        try {
            Integer result = photoStudioService.saveModel(photoModel);
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.addModel():", ex);
        }

        return response;
    }

    @GetMapping(value = "/models")
    public JsonResponse getModels(@ModelAttribute QueryParam queryParam) {
        logger.debug("PhotoStudioController.getModels() called!!!");
        if (queryParam.getPn() == null) {
            queryParam.setPn(1);
        }
        if (queryParam.getPs() == null) {
            queryParam.setPs(20);
        }
        if (queryParam.getOrderBy() == null) {


            queryParam.setOrderBy("PhotoshootDateDesc");
        }

        try {
            if (Utility.checkValidPageSize("getModels", queryParam.getPs())) {
                logger.debug("getModels() params: {}", queryParam.toString());
                PagedResult<PhotoModel> photoModels = photoStudioService.getModels(queryParam);
                return new JsonResponse(true, "", photoModels);
            } else {
                return new JsonResponse(false, "getModels Page Size invalid.", null);
            }
        } catch (Exception e) {
            logger.error("Error: PhotoStudioController.getModels():", e);
            logger.error("QueryParam: {}", queryParam.toString());
            return new JsonResponse(false, "", null);
        }
    }

    @GetMapping(value = "/model/{modelID}")
    public JsonResponse getModel(@PathVariable("modelID") Integer modelID) {
        logger.debug("PhotoStudioController.getModel() called!!!");
        JsonResponse<PhotoModel> response = new JsonResponse<>(false, null, null);
        try {
            PhotoModel photoModel = photoStudioService.getModel(modelID);
            response.setSuccess(true);
            response.setData(photoModel);
        } catch (Exception e) {
            logger.error("Error: PhotoStudioController.getModel():", e);
        }

        return response;
    }

    @GetMapping(value = "/model/{modelID}/delete")
    public JsonResponse deleteModel(@PathVariable("modelID") Integer modelID) {
        logger.debug("PhotoStudioController.deleteModel() called!!!");
        JsonResponse<String> response = new JsonResponse<>(false, null, null);
        try {
            String errMsg = photoStudioService.deleteModel(modelID);
            response.setSuccess(StringUtils.isEmpty(errMsg));
            response.setMessage(errMsg);
        } catch (Exception e) {
            logger.error("Error: PhotoStudioController.deleteModel():", e);
        }

        return response;
    }

    @GetMapping("/calendar")
    public JsonResponse<List<PhotoCalendarResponse>> getPhotoCalendar(@RequestParam Map<String, String> parmMap) {
        logger.debug("PhotoStudioController.getPhotoCalendar() called!!!");
        JsonResponse<List<PhotoCalendarResponse>> response = new JsonResponse<>(false, null, null);

        try {
            List<PhotoCalendarResponse> result = photoStudioService.getPhotoCalendar(parmMap);
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getPhotoCalendar()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/calendar/models-orders")
    public JsonResponse<ModelsOrdersResponse> getPhotoCalendarModelsOrders(@RequestParam(name = "calendarID") int calendarId,
                                                                           @RequestParam(name = "modelID", required = false) Integer modelId) {
        logger.debug("PhotoStudioController.getPhotoCalendarModelsOrders() called!!!");
        JsonResponse<ModelsOrdersResponse> response = new JsonResponse<>(false, null, null);

        try {
            ModelsOrdersResponse result = photoStudioService.getPhotoCalendarModelsOrders(calendarId, modelId);
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getPhotoCalendarModelsOrders()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "/calendar/save")
    public JsonResponse<?> saveCalendar(@RequestBody PhotoCalendar photoCalendar) {
        logger.debug("PhotoStudioController.saveCalendar() called!!!");
        JsonResponse<String> response = new JsonResponse<>(false, null, null);

        try {
            String resultMsg = photoStudioService.saveCalendar(photoCalendar);
            response.setSuccess(StringUtils.isEmpty(resultMsg));
            response.setMessage(resultMsg);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.saveCalendar():", ex);
        }

        return response;
    }

    @PostMapping(value = "/calendar/available")
    public JsonResponse<?> calendarAvailable(@RequestBody PhotoCalendar photoCalendar) {
        logger.debug("PhotoStudioController.calendarAvailable() called!!!");
        JsonResponse<String> response = new JsonResponse<>(false, null, null);

        try {
            String resultMsg = photoStudioService.calendarAvailable(photoCalendar);
            response.setSuccess(StringUtils.isEmpty(resultMsg));
            response.setMessage(resultMsg);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.calendarAvailable():", ex);
        }

        return response;
    }

    @GetMapping(value = "/orders")
    public JsonResponse<PagedResult<PhotoOrderResponse>> getPhotoOrders(@ModelAttribute OrderQueryParam queryParam) {
        logger.debug("PhotoStudioController.getPhotoOrders() called!!!");
        if (queryParam.getPn() == null) {
            queryParam.setPn(1);
        }
        if (queryParam.getPs() == null) {
            queryParam.setPs(20);
        }
        if (queryParam.getOrderBy() == null) {
            queryParam.setOrderBy("PhotoshootDate Desc");
        }

        try {
            if (Utility.checkValidPageSize("getPhotoOrders", queryParam.getPs())) {
                logger.debug("getPhotoOrders() params: {}", queryParam.toString());
                PagedResult<PhotoOrderResponse> photoOrders = photoStudioService.getPhotoOrders(queryParam);
                return new JsonResponse<>(true, "", photoOrders);
            } else {
                return new JsonResponse<>(false, "PhotoOrder Page Size invalid.", null);
            }
        } catch (Exception e) {
            logger.error("Error: PhotoStudioController.getPhotoOrders():", e);
            logger.error("QueryParam: {}", queryParam.toString());
            return new JsonResponse<>(false, "", null);
        }
    }

    @GetMapping("/order/{poNumber}")
    public JsonResponse<?> getPhotoOrder(@PathVariable("poNumber") String poNumber) {
        logger.debug("PhotoStudioController.getPhotoOrder() called!!!");
        JsonResponse<Map<String, Object>> response = new JsonResponse<>(false, null, null);

        try {
            Map<String, Object> result = photoStudioService.getPhotoOrder(poNumber);
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getPhotoOrder()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/order/{poNumber}/siblings")
    public JsonResponse<PhotoOrderSiblingResponse> getPhotoOrderSiblings(@PathVariable("poNumber") String poNumber,
                                                                         @ModelAttribute OrderQueryParam queryParam) {
        logger.debug("PhotoStudioController.getPhotoOrderSiblings() called!!!");
        if (queryParam.getPn() == null) {
            queryParam.setPn(1);
        }
        if (queryParam.getPs() == null) {
            queryParam.setPs(20);
        }
        if (queryParam.getOrderBy() == null) {
            queryParam.setOrderBy("PhotoshootDate Desc");
        }

        try {
            if (Utility.checkValidPageSize("getPhotoOrders", queryParam.getPs())) {
                logger.debug("getPhotoOrderSiblings() params: {}", queryParam.toString());
                PhotoOrderSiblingResponse siblings = photoStudioService.getPhotoOrderSiblings(poNumber, queryParam);
                return new JsonResponse<>(true, "", siblings);
            } else {
                return new JsonResponse<>(false, "PhotoOrder Page Size invalid.", null);
            }
        } catch (Exception e) {
            logger.error("Error: PhotoStudioController.getPhotoOrderSiblings():", e);
            logger.error("QueryParam: {}", queryParam.toString());
            return new JsonResponse<>(false, "", null);
        }
    }

    @GetMapping("/calendar/availablemodels/{orderID}/{theDate}")
    public JsonResponse<?> getAvailableModels(@PathVariable("orderID") Integer orderID, @PathVariable("theDate") String theDate) {
        logger.debug("PhotoStudioController.getAvailableModels() called!!!");
        JsonResponse<List<AvailableModelsResponse>> response = new JsonResponse<>(false, null, null);

        try {
            List<AvailableModelsResponse> result = photoStudioService.getAvailableModels(orderID, theDate);
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getAvailableModels()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "/order/update")
    public JsonResponse<?> updatePhotoOrder(@RequestBody OrderUpdateRequest orderUpdateRequest) {
        logger.debug("PhotoStudioController.updatePhotoOrder() called!!!");
        JsonResponse<String> response = new JsonResponse<>(false, null, null);

        try {
            String resultMsg = photoStudioService.updatePhotoOrder(orderUpdateRequest);
            response.setSuccess(StringUtils.isEmpty(resultMsg));
            response.setMessage(resultMsg);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.updatePhotoOrder():", ex);
        }

        return response;
    }

    @PostMapping(value = "order/cancel")
    public JsonResponse<?> cancelPhotoOrder(@RequestBody PhotoOrder photoOrder) {
        logger.debug("PhotoStudioController.cancelPhotoOrder() called!!!");
        JsonResponse<String> response = new JsonResponse<>(false, null, null);

        try {

            String resultMsg = photoStudioService.cancelPhotoOrder(photoOrder);
            response.setSuccess(StringUtils.isEmpty(resultMsg));
            response.setMessage(resultMsg);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.cancelPhotoOrder():", ex);
        }

        return response;
    }

    @GetMapping("/order/dailysummary/{photoshootDate}")
    public JsonResponse<?> getDailySummary(@PathVariable("photoshootDate") String photoshootDate) {
        logger.debug("PhotoStudioController.getDailySummary() called!!!");
        JsonResponse<DailySummaryResponse> response = new JsonResponse<>(false, null, null);

        try {
            DailySummaryResponse result = photoStudioService.getDailySummary(photoshootDate);
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getDailySummary()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "/order/action/save")
    public JsonResponse<?> saveActionLog(@RequestBody LogPhotoAction logPhotoAction) {
        logger.debug("PhotoStudioController.saveActionLog() called!!!");
        JsonResponse<Integer> response = new JsonResponse<>(false, null, null);

        try {
            Integer result = photoStudioService.saveActionLog(logPhotoAction);
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.saveActionLog():", ex);
        }

        return response;
    }

    @GetMapping(value = "/dropper/{wholeSalerId}")
    public JsonResponse<List<PhotoDropperResponse>> getDroppers(@PathVariable int wholeSalerId) {
        logger.debug("PhotoStudioController.getDroppers() called!!!");
        JsonResponse<List<PhotoDropperResponse>> response = new JsonResponse<>(false, null, null);

        try {
            List<PhotoDropperResponse> droppers = photoStudioService.getDroppers(wholeSalerId);
            response.setSuccess(true);
            response.setData(droppers);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.getDroppers():", ex);
        }

        return response;
    }

    @PostMapping(value = "/dropper")
    public JsonResponse<List<PhotoDropperResponse>> saveDroppers(@RequestBody PhotoDropperSaveRequest request) {
        logger.debug("PhotoStudioController.saveDroppers() called!!!");
        JsonResponse<List<PhotoDropperResponse>> response = new JsonResponse<>(false, null, null);

        try {
            List<PhotoDropperResponse> droppers = photoStudioService.saveDropper(request);
            response.setSuccess(true);
            response.setData(droppers);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.saveDroppers():", ex);
        }

        return response;
    }

    @GetMapping("/order/{orderId}/log/{actionType}")
    public JsonResponse<?> getActionLog(@PathVariable("orderId") Integer orderId, @PathVariable("actionType") Integer actionType) {
        logger.debug("PhotoStudioController.getActionLog() called!!!");
        JsonResponse<List<LogPhotoAction>> response = new JsonResponse<>(false, null, null);

        try {
            List<LogPhotoAction> result = photoStudioService.getActionLog(orderId, actionType);
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getActionLog()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/reports")
    public String getReports(@RequestParam Map<String, Object> parmMap) {
        logger.debug("PhotoStudioController.getReports() called!!!");
        JsonResponse<Map<String, Object>> response = new JsonResponse<>(false, null, null);

        try {
            int year = Integer.parseInt(String.valueOf(parmMap.get("year")));
            int month = Integer.parseInt(String.valueOf(parmMap.get("month")));
            int reportTypeId = Integer.parseInt(String.valueOf(parmMap.get("reportTypeId")));

            Map<String, Object> result = photoStudioService.getReports(year, month, ReportType.typeOf(reportTypeId));
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getReports()：", ex);
            response.setMessage(ex.getMessage());
        }

        return Utility.xssEscape(response);
    }

    @GetMapping("/reports/daily/report")
    public ModelAndView getDailyReoortsToExcel(@RequestParam(required = true, name = "yyyymmdd") String date) {
        DailyReport dailyReport = photoStudioService.getDailyReportToExcel(date);
        dailyReport.setRequestDate(date);
        return new ModelAndView("photoStudioReportView", "excelReport", dailyReport);
    }

    @GetMapping("/reports/monthly/csv")
    public String getReportsMonthlyCsv(@RequestParam Map<String, Object> parmMap) {
        logger.debug("PhotoStudioController.getReportsMonthlyCsv() called!!!");
        JsonResponse<List<ReportCsvMonthly>> response = new JsonResponse<>(false, null, null);

        try {
            String yyyymmddString = String.valueOf(parmMap.get("yyyymmdd"));
            int reportTypeId = Integer.parseInt(String.valueOf(parmMap.get("reportTypeId")));

            List<ReportCsvMonthly> result = photoStudioService.getReportsMonthlyCsv(yyyymmddString, ReportType.typeOf(reportTypeId));
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getReportsMonthlyCsv()：", ex);
            response.setMessage(ex.getMessage());
        }

        return Utility.xssEscape(response);
    }

    @PostMapping(value = "/credit/save")
    public JsonResponse<?> saveCredit(@RequestBody PhotoCreditRequest photoCreditRequest) {
        logger.debug("PhotoStudioController.saveCredit() called!!!");
        JsonResponse<Integer> response = new JsonResponse<>(false, null, null);

        if(photoCreditRequest.getPhotoCreditAmount().compareTo(BigDecimal.ZERO) == -1) {
            return response;
        }

        try {
            Integer result = photoStudioService.saveCredit(photoCreditRequest);
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.saveCredit():", ex);
        }

        return response;
    }

    @GetMapping("/credit/balance/{wholeSalerID}")
    public JsonResponse<?> getCreditBalance(@PathVariable("wholeSalerID") Integer wholeSalerID) {
        logger.debug("PhotoStudioController.getCreditBalance() called!!!");
        JsonResponse<BigDecimal> response = new JsonResponse<>(false, null, null);

        try {
            BigDecimal photoCreditBalance = photoStudioService.getCreditBalance(wholeSalerID);
            response.setSuccess(true);
            response.setData(photoCreditBalance);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getCreditBalance()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/credits")
    public JsonResponse<?> getCredits(@ModelAttribute QueryParam queryParam) {

        logger.debug("PhotoStudioController.getCredits() called!!!");
        if (queryParam.getPn() == null) {
            queryParam.setPn(1);
        }
        if (queryParam.getPs() == null) {
            queryParam.setPs(20);
        }

        try {
            if (Utility.checkValidPageSize("getCredits", queryParam.getPs())) {
                logger.debug("getCredits() params: {}", queryParam.toString());
                PagedResult<PhotoCredit> photoCredits = photoStudioService.getCredits(queryParam);
                return new JsonResponse(true, "", photoCredits);
            } else {
                return new JsonResponse(false, "Credits Page Size invalid.", null);
            }
        } catch (Exception e) {
            logger.error("Error: PhotoStudioController.getCredits():", e);
            logger.error("QueryParam: {}", queryParam.toString());
            return new JsonResponse(false, "", null);
        }
    }

    @GetMapping("/credit/history/{wholeSalerID}")
    public JsonResponse<?> getCreditHistory(@PathVariable("wholeSalerID") Integer wholeSalerID) {
        logger.debug("PhotoStudioController.getCreditHistory() called!!!");
        JsonResponse<List<PhotoCredit>> response = new JsonResponse<>(false, null, null);

        try {
            List<PhotoCredit> result = photoStudioService.getCreditHistory(wholeSalerID);
            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            logger.error("Exception Error: PhotoStudioController.getCreditHistory()：", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping(value = "/credit/{photoCreditID}/delete")
    public JsonResponse<?> deleteCredit(@PathVariable("photoCreditID") Integer photoCreditID) {
        logger.debug("PhotoStudioController.deleteCredit() called!!!");
        JsonResponse<String> response = new JsonResponse<>(false, null, null);

        try {
            boolean bSuccess = photoStudioService.deleteCredit(photoCreditID);
            response.setSuccess(bSuccess);
            response.setData("");
        } catch (Exception ex) {
            logger.error("Error: PhotoStudioController.deleteCredit():", ex);
        }

        return response;
    }
}
