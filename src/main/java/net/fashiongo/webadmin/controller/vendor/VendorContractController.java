package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.ListVendorDocumentTypeEntity;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.service.vendor.VendorContractNewService;
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
	
	private VendorContractNewService vendorContractNewService;
	private RenewalVendorService renewalVendorService;
	private VendorContractService vendorContractService;

	public VendorContractController(
			VendorContractNewService vendorContractNewService,
            RenewalVendorService renewalVendorService,
            VendorContractService vendorContractService
    ) {
	    this.vendorContractNewService = vendorContractNewService;
	    this.renewalVendorService = renewalVendorService;
	    this.vendorContractService = vendorContractService;
    }

	@RequestMapping(value="vendor/getvendorcontract", method=RequestMethod.GET, produces = "application/json")
	public JsonResponse<VendorContractResponse> getVendorContract(@RequestParam(value="wid") Integer wholeSalerID) {
		JsonResponse<VendorContractResponse> results = new JsonResponse<VendorContractResponse>(false, null, 0, null);
		VendorContractResponse result = vendorContractNewService.inquiryVendorContract(wholeSalerID);
		results.setData(result);
		results.setSuccess(true);
		return results;
	}
	
    @GetMapping(value = "vendor/contractplans", produces = "application/json")
    public JsonResponse<List<ContractPlansResponse>> getContractPlans() {
    	JsonResponse<List<ContractPlansResponse>> response = new JsonResponse<>(false, null, null);
    	try {
    		response.setData(vendorContractNewService.inquiryContractPlans());
    		response.setSuccess(true);
    	} catch (Exception ex) {
    		log.error("Exception Error: {}", ex);
    		response.setMessage(ex.getMessage());
    	}
    	return response;
    }

    @RequestMapping(value="vendor/getvendorcontractdocumenthistory", method=RequestMethod.GET, produces = "application/json")
    public JsonResponse<GetVendorContractDocumentHistoryResponse> getVendorContractDocumentHistory(@RequestParam(value="VendorContractID") Long vendorContractID) {
        JsonResponse<GetVendorContractDocumentHistoryResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorContractDocumentHistoryResponse>(true, null, null);

		GetVendorContractDocumentHistoryResponse vendorContractDocumentHistory = GetVendorContractDocumentHistoryResponse.builder()
				.vendorContractDocumentHistoryList(vendorContractNewService.getContractDocumentHistory(vendorContractID).getContractDocumentHistories())
				.build();
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
	public JsonResponse<List<VendorContractHistoryList>> getvendorcontracthistorylist(@RequestParam(value="wid") Long wholeSalerID) {
    	JsonResponse<List<VendorContractHistoryList>> response = new JsonResponse<>(false, null, null);

    	try {
            List<VendorContractHistoryList> result = vendorContractNewService.getContractHistoryList(wholeSalerID).getContractHistories();

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
	        vendorContractService.setVendorContract(param);
            return new ResultCode(true, 1, "success");
        } catch (Exception e) {
	        log.error("fail to update the vendor contract info.", e);
            return new ResultCode(false, -1, "failure");
        }
	}

	@PostMapping(value = "vendor/setvendorcontractdocument", produces = "application/json")
	public ResultCode setVendorContractDocument(@RequestBody SetVendorContractDocumentParameter request) {
	    try {
            vendorContractService.setVendorContractDocument(request);
            return new ResultCode(true, 1, "success");
        } catch (Exception e) {
	        log.error("fail to set vendor contract document", e);
            return new ResultCode(false, -1, "failure");
        }
	}

	@PostMapping(value = "vendor/delvendorcontractdocument", produces = "application/json")
	public ResultCode deleteVendorContractDocument(@RequestBody DelVendorContractDocumentParameter request) {

    	if(StringUtils.isEmpty(request.getDocumentHistoryIDs()))
            return new ResultCode(false, -1, "failure");

    	try {
            vendorContractService.delVendorContractDocument(request);
            return new ResultCode(true, 1, "success");
        } catch (Exception e) {
            log.error("fail to delete vendor contract document", e);
            return new ResultCode(false, -1, "failure");
        }
	}
}
	
