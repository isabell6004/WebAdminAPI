package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.pojo.request.GnbBannerTargetUrlRequest;
import net.fashiongo.webadmin.model.pojo.response.GnbBannerResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbBannerTypeResponse;
import net.fashiongo.webadmin.service.GnbBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@Consumes(MediaType.APPLICATION_JSON)
@RequestMapping(value = "/gnb-banner", produces = "application/json")
public class GnbBannerController {

	private final GnbBannerService gnbBannerService;

	@Autowired
	public GnbBannerController(GnbBannerService gnbBannerService) {
		this.gnbBannerService = gnbBannerService;
	}

	@GetMapping("/banner-types")
	public JsonResponse<List<GnbBannerTypeResponse>> getTypes() {
		JsonResponse<List<GnbBannerTypeResponse>> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);
		response.setData(gnbBannerService.getAllTypes());
		return response;
	}

	@GetMapping("/banner-types/{typeId}")
	public JsonResponse<GnbBannerTypeResponse> getTypes(@PathVariable("typeId") int typeId) {
		return gnbBannerService.getType(typeId)
				.map(t -> new JsonResponse<>(true, null, t))
				.orElseGet(() -> new JsonResponse<>(false, "Invalid banner type ID.", null));
	}

	@PostMapping("/banner-types/{typeId}")
	public JsonResponse<GnbBannerResponse> addBanner(@PathVariable("typeId") int typeId,
													 @RequestPart("imageFileName") String imageFileName,
													 @RequestPart("imageFile") MultipartFile imageFile,
													 @RequestPart("targetUrl") String targetUrl) {
		try {
			GnbBannerResponse response = gnbBannerService.addBanner(typeId, imageFileName, imageFile.getInputStream(), targetUrl);
			return new JsonResponse<>(true, null, response);
		} catch (IllegalArgumentException e) {
			return new JsonResponse<>(false, e.getMessage(), null);
		} catch (Exception e1) {
			return new JsonResponse<>(false, "", null);
		}
	}

	@PostMapping("/banner-types/{typeId}/banners/{bannerId}")
	public JsonResponse<GnbBannerResponse> modifyBannerImage(@PathVariable("typeId") int typeId,
															 @PathVariable("bannerId") int bannerId,
															 @RequestPart("imageFileName") String imageFileName,
															 @RequestPart("imageFile") MultipartFile imageFile,
															 @RequestPart("targetUrl") String targetUrl) {
		try {
			GnbBannerResponse response = gnbBannerService.modifyBanner(typeId, bannerId, imageFileName, imageFile.getInputStream(), targetUrl);
			return new JsonResponse<>(true, null, response);
		} catch (IllegalArgumentException e) {
			return new JsonResponse<>(false, e.getMessage(), null);
		} catch (Exception e1) {
			return new JsonResponse<>(false, "", null);
		}
	}

	@PutMapping("/banner-types/{typeId}/banners/{bannerId}/target-url")
	public ResponseEntity<Void> modifyTargetUrl(@PathVariable("typeId") int typeId,
														   @PathVariable("bannerId") int bannerId,
														   @RequestBody GnbBannerTargetUrlRequest request) {
		try {
			gnbBannerService.modifyTargetUrl(typeId, bannerId, request.getTargetUrl());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
