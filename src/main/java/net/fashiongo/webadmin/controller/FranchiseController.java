package net.fashiongo.webadmin.controller;

import net.fashiongo.webadmin.data.model.franchise.AutoCompleteParameter;
import net.fashiongo.webadmin.data.model.franchise.FranchiseBuyer;
import net.fashiongo.webadmin.data.model.franchise.FranchiseSubAddParameter;
import net.fashiongo.webadmin.data.model.franchise.response.FranchiseMasterResponse;
import net.fashiongo.webadmin.data.model.franchise.response.FranchiseSubResponse;
import net.fashiongo.webadmin.service.renewal.RenewalFranchiseService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/franchise", produces = "application/json")
public class FranchiseController {

	@Autowired
	private RenewalFranchiseService renewalFranchiseService;

	@RequestMapping(value = "master/{retailerId}", method = RequestMethod.POST)
	public JsonResponse<FranchiseMasterResponse> getFranchiseMaster(@PathVariable(value = "retailerId") Integer retailerId) {
		JsonResponse<FranchiseMasterResponse> response = new JsonResponse();

		FranchiseMasterResponse franchiseMaster = renewalFranchiseService.getFranchiseMaster(retailerId);

		Boolean success = franchiseMaster != null;
		String message = success ? "" : "Failed to retrieve master account information.";

		response.setSuccess(success);
		response.setData(franchiseMaster);
		response.setMessage(message);

		return response;
	}

	@RequestMapping(value = "sub/{retailerId}", method = RequestMethod.POST)
	public JsonResponse<FranchiseSubResponse> getFranchiseSub(@PathVariable(value = "retailerId") Integer retailerId) {
		JsonResponse<FranchiseSubResponse> response = new JsonResponse();

		FranchiseSubResponse franchiseSub = renewalFranchiseService.getFranchiseSub(retailerId);

		Boolean success = franchiseSub != null;
		String message = success ? "" : "Failed to retrieve sub account information.";

		response.setSuccess(success);
		response.setData(franchiseSub);
		response.setMessage(message);

		return response;
	}

	@RequestMapping(value = "autoComplete", method = RequestMethod.POST)
	public JsonResponse<List<FranchiseBuyer>> autoComplete(@RequestBody AutoCompleteParameter parameter) {
		JsonResponse<List<FranchiseBuyer>> response = new JsonResponse();

		List<FranchiseBuyer> franchiseBuyers = renewalFranchiseService.autoComplete(parameter);

		Boolean success = franchiseBuyers != null;
		String message = success ? "" : "Failed to retrieve retailers.";

		response.setSuccess(success);
		response.setData(franchiseBuyers);
		response.setMessage(message);

		return response;
	}

	@RequestMapping(value = "sub/add", method = RequestMethod.POST)
	public JsonResponse<String> addSub(@RequestBody FranchiseSubAddParameter parameter) {
		JsonResponse<String> response = new JsonResponse();

		String message = "Invalid input parameters";
		boolean result = false;
		Integer retailerId = Optional.ofNullable(parameter.getRetailerId()).orElse(0);
		Integer masterAccountId = Optional.ofNullable(parameter.getMasterAccountId()).orElse(0);

		if (retailerId != 0 && masterAccountId != 0) {
			result = renewalFranchiseService.addSub(retailerId,masterAccountId);
			message = result ? "" : "Failed to add sub account";
		}

		response.setSuccess(result);
		response.setMessage(message);

		return response;
	}

}
