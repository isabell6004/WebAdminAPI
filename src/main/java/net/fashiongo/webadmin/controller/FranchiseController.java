package net.fashiongo.webadmin.controller;

import net.fashiongo.webadmin.data.model.franchise.response.FranchiseMasterResponse;
import net.fashiongo.webadmin.data.model.franchise.response.FranchiseSubResponse;
import net.fashiongo.webadmin.service.renewal.RenewalFranchiseService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
