package net.fashiongo.webadmin.controller;

import java.util.List;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.photostudio.PhotoCategory;
import net.fashiongo.webadmin.model.photostudio.PhotoDiscount;
import net.fashiongo.webadmin.service.PhotoStudioService;

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

}
