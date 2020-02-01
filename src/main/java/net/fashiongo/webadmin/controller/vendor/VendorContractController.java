package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.ListVendorDocumentTypeEntity;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.primary.*;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.service.vendor.VendorContractService;
import net.fashiongo.webadmin.service.renewal.RenewalVendorService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author roy
 */
@RestController
@Slf4j
public class VendorContractController {
	
	private VendorService vendorService;
	private RenewalVendorService renewalVendorService;
	private VendorContractService vendorContractService;

	public VendorContractController(
            VendorService vendorService,
            RenewalVendorService renewalVendorService,
            VendorContractService vendorContractService
    ) {
	    this.vendorService = vendorService;
	    this.renewalVendorService = renewalVendorService;
	    this.vendorContractService = vendorContractService;
    }

	@RequestMapping(value="vendor/getvendorcontract", method=RequestMethod.GET, produces = "application/json")
	public JsonResponse<List<VendorContract>> getVendorContract(@RequestParam(value="wid") Integer wholeSalerID) {
		JsonResponse<List<VendorContract>> results = new JsonResponse<List<VendorContract>>(false, null, 0, null);
		List<VendorContract> result = vendorService.getVendorContract(wholeSalerID);
		
		results.setData(result);
		results.setSuccess(true);
		return results;
	}
	
    @GetMapping(value = "vendor/contractplans", produces = "application/json")
    public JsonResponse<List<ContractPlan>> getContractPlans() {
    	JsonResponse<List<ContractPlan>> response = new JsonResponse<>(false, null, null);
    	try {
    		response.setData(vendorService.getContractPlans());
    		response.setSuccess(true);
    	} catch (Exception ex) {
    		log.error("Exception Error: {}", ex);
    		response.setMessage(ex.getMessage());
    	}
    	return response;
    }

    @RequestMapping(value="vendor/getvendorcontractdocumenthistory", method=RequestMethod.GET, produces = "application/json")
    public JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse> getVendorContractDocumentHistory(@RequestParam(value="VendorContractID") Integer vendorContractID) {
        JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse>(true, null, null);

        net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse vendorContractDocumentHistory = renewalVendorService.getVendorContractDocumentHistory(vendorContractID);
        results.setData(vendorContractDocumentHistory);

        return results;
    }

    @PostMapping(value = "vendor/getvendordocumenttypelist", produces = "application/json")
	public JsonResponse<List<ListVendorDocumentTypeEntity>> getvendordocumenttypelist() {
    	JsonResponse<List<ListVendorDocumentTypeEntity>> response = new JsonResponse<>(false, null, null);

    	try {
    		response.setData(renewalVendorService.getListVendorDocumentType());
    		response.setSuccess(true);
		} catch (Exception ex) {
    		log.error("Exception Error: {}", ex);
    		response.setMessage(ex.getMessage());
		}

    	return response;
	}

	@GetMapping(value = "vendor/getvendorcontracthistorylist", produces = "application/json")
	public JsonResponse<List<VendorContractHistory>> getvendorcontracthistorylist(@RequestParam(value="wid") Integer wholeSalerID) {
    	JsonResponse<List<VendorContractHistory>> response = new JsonResponse<>(false, null, null);

    	try {
    		List<VendorContractHistory> result = renewalVendorService.getVendorContractHistoryList(wholeSalerID);

    		response.setSuccess(true);
    		response.setData(result);
    		response.setMessage("success");
		} catch (Exception ex) {
    		log.warn(ex.getMessage(), ex);
    		response.setMessage("fail");
		}

    	return response;
	}

	@PostMapping(value = "vendor/setvendorcontract", produces = "application/json")
	public ResultCode setvendorcontract(@RequestBody SetVendorContractParameter param) {
	    try {
	        if(vendorContractService.setVendorContract(param))
                return new ResultCode(true, 1, "success");
            else
                return new ResultCode(false, -1, "failure");
        } catch (Exception e) {
	        log.error("fail to update the vendor contract info.", e);
            return new ResultCode(false, -1, "failure");
        }
	}

	@PostMapping(value = "vendor/setvendorcontractdocument", produces = "application/json")
	public ResultCode setVendorContractDocument(@RequestBody SetVendorContractDocumentParameter request) {
	    if(vendorContractService.setVendorContractDocument(request))
            return new ResultCode(true, 1, "success");
         else
            return new ResultCode(false, -1, "failure");
	}

	@PostMapping(value = "vendor/delvendorcontractdocument", produces = "application/json")
	public ResultCode deleteVendorContractDocument(@RequestBody DelVendorContractDocumentParameter request) {

    	if(StringUtils.isEmpty(request.getDocumentHistoryIDs()))
            return new ResultCode(false, -1, "failure");

        if(vendorContractService.delVendorContractDocument(request))
            return new ResultCode(true, 1, "success");
        else
            return new ResultCode(false, -1, "failure");
	}
}
	
