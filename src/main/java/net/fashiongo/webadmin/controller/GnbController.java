package net.fashiongo.webadmin.controller;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupInfo;
import net.fashiongo.webadmin.service.GnbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gnb")
public class GnbController {

	private final GnbService gnbService;

	@Autowired
	public GnbController(GnbService gnbService) {
		this.gnbService = gnbService;
	}

	@GetMapping("/vendor-group")
	public JsonResponse<List<GnbVendorGroupInfo>> getVendorGroupList(@RequestParam(name = "wholeSalerId", required = false) Integer wholeSalerId,
																	  @RequestParam(name = "title", required = false) String title) {
		JsonResponse<List<GnbVendorGroupInfo>> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);
		response.setData(gnbService.getGnbVendorGroupList(wholeSalerId, title));

		return response;
	}

	@DeleteMapping("/vendor-group")
	public JsonResponse<Void> deleteVendorGroupBatch(@RequestParam(name = "ids") List<Integer> gnbVendorGroupIdList) {
		JsonResponse<Void> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);

		gnbService.deleteGnbVendorGroupBatch(gnbVendorGroupIdList);

		return response;
	}

	@DeleteMapping("/vendor-group/{gnbVendorGroupId}")
	public JsonResponse<Void> deleteVendorGroup(@PathVariable("gnbVendorGroupId") int gnbVendorGroupId) {
		JsonResponse<Void> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);

		gnbService.deleteGnbVendorGroup(gnbVendorGroupId);

		return response;
	}

	@PostMapping("/vendor-group/{gnbVendorGroupId}/activate")
	public JsonResponse<Void> activeVendorGroup(@PathVariable("gnbVendorGroupId") int gnbVendorGroupId) {
		JsonResponse<Void> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);

		gnbService.activateGnbVendorGroup(gnbVendorGroupId);

		return response;
	}
}
