package net.fashiongo.webadmin.controller;

import java.util.List;
import java.util.Map;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.photostudio.PhotoCalendar;
import net.fashiongo.webadmin.model.photostudio.PhotoCancellationFee;
import net.fashiongo.webadmin.model.photostudio.PhotoCategory;
import net.fashiongo.webadmin.model.photostudio.PhotoDiscount;
import net.fashiongo.webadmin.model.photostudio.PhotoModel;
import net.fashiongo.webadmin.model.photostudio.PhotoPrice;
import net.fashiongo.webadmin.service.PhotoStudioService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andy
 *
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
	
	@RequestMapping(value = "/prices/save")
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
	
	@RequestMapping(value = "/cancellationfees/save")
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

	@RequestMapping(value = "/discount/save")
	public JsonResponse<?> saveDiscount(@RequestBody PhotoDiscount photoDiscount) {
		logger.debug("PhotoStudioController.saveDiscount() called!!!");
		JsonResponse<Integer> response = new JsonResponse<>(false, null, null);

		try {
			Integer result = photoStudioService.saveDiscount(photoDiscount);
			response.setSuccess(true);
			response.setData(result);
		} catch (Exception ex) {
			logger.error("Error: PhotoStudioController.saveDiscount():", ex);
		}

		return response;
	}

	@RequestMapping(value = "/discount/{discountID}/delete")
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

	@RequestMapping(value = "/model/save")
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
	
	@RequestMapping(value = "/calendar/save")
	public JsonResponse<?> saveCalendar(@RequestBody List<PhotoCalendar> photoCalendars) {
		logger.debug("PhotoStudioController.saveCalendar() called!!!");
		JsonResponse<String> response = new JsonResponse<>(false, null, null);

		try {
			String resultMsg = photoStudioService.saveCalendar(photoCalendars);
			response.setSuccess(StringUtils.isEmpty(resultMsg));
			response.setMessage(resultMsg);
		} catch (Exception ex) {
			logger.error("Error: PhotoStudioController.saveCalendar():", ex);
		}

		return response;
	}
}
