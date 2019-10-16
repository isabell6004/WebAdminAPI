package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbBannerTypeResponse;
import net.fashiongo.webadmin.service.GnbBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
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
}
