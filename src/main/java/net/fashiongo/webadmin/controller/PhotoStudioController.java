package net.fashiongo.webadmin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.photostudio.PhotoCategory;
import net.fashiongo.webadmin.service.PhotoStudioService;

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

}
