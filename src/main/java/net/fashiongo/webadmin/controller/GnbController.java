package net.fashiongo.webadmin.controller;

import net.fashiongo.common.JsonResponse;
import net.fashiongo.webadmin.exception.NotFoundGnbVendorGroup;
import net.fashiongo.webadmin.exception.NotFoundSiteSetting;
import net.fashiongo.webadmin.model.pojo.request.GnbVendorGroupSaveRequest;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupInfoResponse;
import net.fashiongo.webadmin.model.pojo.request.GnbCollectionSaveRequest;
import net.fashiongo.webadmin.model.pojo.response.GnbCollectionInfoResponse;
import net.fashiongo.webadmin.service.GnbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gnb")
public class GnbController {

	private final GnbService gnbService;

	@Autowired
	public GnbController(GnbService gnbService) {
		this.gnbService = gnbService;
	}

	@GetMapping("/vendor-group")
	public JsonResponse<List<GnbVendorGroupInfoResponse>> getVendorGroupList(@RequestParam(name = "wholeSalerId", required = false) Integer wholeSalerId,
																			 @RequestParam(name = "title", required = false) String title) {
		JsonResponse<List<GnbVendorGroupInfoResponse>> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);
		response.setData(gnbService.getGnbVendorGroupList(wholeSalerId, title));

		return response;
	}

	@PostMapping("/vendor-group")
	public JsonResponse<GnbVendorGroupInfoResponse> createVendorGroup(@RequestBody @Valid GnbVendorGroupSaveRequest request) {
		JsonResponse<GnbVendorGroupInfoResponse> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);
		response.setData(gnbService.createGnbVendorGroup(request));

		return response;
	}

	@DeleteMapping("/vendor-group")
	public JsonResponse<Void> deleteVendorGroupBatch(@RequestParam(name = "idList") List<Integer> gnbVendorGroupIdList) {
		JsonResponse<Void> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);

		gnbService.deleteGnbVendorGroupBatch(gnbVendorGroupIdList);

		return response;
	}

	@GetMapping("/vendor-group/{gnbVendorGroupId}")
	public JsonResponse<GnbVendorGroupDetailResponse> getVendorGroup(@PathVariable("gnbVendorGroupId") int gnbVendorGroupId) {
		JsonResponse<GnbVendorGroupDetailResponse> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);
		response.setData(gnbService.getGnbVendorGroup(gnbVendorGroupId));

		return response;
	}

	@PostMapping("/vendor-group/{gnbVendorGroupId}")
	public JsonResponse<GnbVendorGroupInfoResponse> editVendorGroup(@PathVariable("gnbVendorGroupId") int gnbVendorGroupId,
																	@RequestBody @Valid GnbVendorGroupSaveRequest request) {
		JsonResponse<GnbVendorGroupInfoResponse> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);
		response.setData(gnbService.editGnbVendorGroup(gnbVendorGroupId, request));

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

	@ExceptionHandler({NotFoundGnbVendorGroup.class})
	public JsonResponse<String> handleNotFoundGnbVendorGroup() {
		JsonResponse<String> response = new JsonResponse<>();
		response.setSuccess(false);
		response.setMessage("Not Found GnbVendorList");

		return response;
	}

	@ExceptionHandler({NotFoundSiteSetting.class})
	public JsonResponse<String> handleNotFoundSiteSetting() {
		JsonResponse<String> response = new JsonResponse<>();
		response.setSuccess(false);
		response.setMessage("Not Found SiteSetting. Please contact developer.");

		return response;
	}

	@ExceptionHandler({MethodArgumentNotValidException.class})
	public JsonResponse<String> handleNotFoundGnbVendorGroup(MethodArgumentNotValidException e) {
		JsonResponse<String> response = new JsonResponse<>();
		response.setSuccess(false);
		response.setMessage(e.getBindingResult().getAllErrors().stream()
				.map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining(" ")));

		return response;
	}
	
	@GetMapping("/collection")
	public JsonResponse<List<GnbCollectionInfoResponse>> getCollectionList() {
		JsonResponse<List<GnbCollectionInfoResponse>> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);
		response.setData(gnbService.getGnbCollectionList());

		return response;
	}
	
	@PostMapping("/collection")
	public JsonResponse<GnbCollectionInfoResponse> creatCollecion(@RequestBody @Valid GnbCollectionSaveRequest request) {
		JsonResponse<GnbCollectionInfoResponse> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);
		response.setData(gnbService.createGnbCollection(request));

		return response;
	}	

	@GetMapping("/collection/{gnbMenuCollectionId}")
	public JsonResponse<GnbCollectionInfoResponse> getCollection(@PathVariable("gnbMenuCollectionId") int gnbMenuCollectionId) {
		JsonResponse<GnbCollectionInfoResponse> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);
		response.setData(gnbService.getGnbCollection(gnbMenuCollectionId));

		return response;
	}

	@PostMapping("/collection/{gnbMenuCollectionId}")
	public JsonResponse<GnbCollectionInfoResponse> editCollection(@PathVariable("gnbMenuCollectionId") int gnbMenuCollectionId,
																	@RequestBody @Valid GnbCollectionSaveRequest request) {
		JsonResponse<GnbCollectionInfoResponse> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);
		response.setData(gnbService.editGnbCollection(gnbMenuCollectionId, request));

		return response;
	}

	@DeleteMapping("/collection/{gnbMenuCollectionId}")
	public JsonResponse<Void> deleteCollection(@PathVariable("gnbMenuCollectionId") int gnbMenuCollectionId) {
		JsonResponse<Void> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);

		gnbService.deleteGnbCollection(gnbMenuCollectionId);

		return response;
	}
	
	@PostMapping("/collection/{gnbMenuCollectionId}/activate")
	public JsonResponse<Void> activeCollection(@PathVariable("gnbMenuCollectionId") int gnbMenuCollectionId) {
		JsonResponse<Void> response = new JsonResponse<>();
		response.setSuccess(true);
		response.setMessage(null);

		gnbService.activateGnbCollection(gnbMenuCollectionId);

		return response;
	}
}
