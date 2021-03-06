package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.model.vendor.mapper.VendorBlockAdParameterMapper;
import net.fashiongo.webadmin.data.model.vendor.mapper.VendorBlockAdminLoginParameterMapper;
import net.fashiongo.webadmin.data.model.vendor.mapper.VendorBlockPayoutParameterMapper;
import net.fashiongo.webadmin.data.model.vendor.response.VendorBlockHistoryResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorBlockResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorBlockListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetVendorUnBlockParameter;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.vendor.VendorBlockNewService;
import net.fashiongo.webadmin.service.vendor.VendorBlockService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author roy
 */
@RestController
@Slf4j
public class VendorBlockController {

    private VendorService vendorService;

    private VendorBlockService vendorBlockService;

    private VendorBlockNewService vendorBlockNewService;

    public VendorBlockController(
            VendorService vendorService,
            VendorBlockService vendorBlockService,
            VendorBlockNewService vendorBlockNewService
    ) {
        this.vendorService = vendorService;
        this.vendorBlockService = vendorBlockService;
        this.vendorBlockNewService = vendorBlockNewService;
    }

    /**
     * Description Example
     *
     * @param parameters
     * @return
     * @throws ParseException
     * @author Reo
     * @since 2018. 11. 12.
     */
    @RequestMapping(value = "vendor/getvendorblockList", method = RequestMethod.POST, produces = "application/json")
    public JsonResponse<CollectionObject<VendorBlockResponse>> getVendorBlockList(@RequestBody GetVendorBlockListParameter parameters) throws ParseException {
        JsonResponse<CollectionObject<VendorBlockResponse>> results = new JsonResponse<CollectionObject<VendorBlockResponse>>(false, null, 0, null);
        //List<VwVendorBlocked> result = vendorService.getVendorBlockList(parameters);
        CollectionObject<VendorBlockResponse> result = vendorBlockNewService.getVendorBlockList(parameters);
        results.setData(result);
        results.setSuccess(true);
        return results;
    }

    /**
     * Description Example
     *
     * @param wholeSalerID
     * @return
     * @author Reo
     * @since 2018. 11. 12.
     */
    @RequestMapping(value = "vendor/getvendorblockhistoryList", method = RequestMethod.GET, produces = "application/json")
    public JsonResponse<CollectionObject<VendorBlockHistoryResponse>> getVendorBlockHistoryList(@RequestParam(value = "WholeSalerID") Long wholeSalerID) {
        JsonResponse<CollectionObject<VendorBlockHistoryResponse>> results = new JsonResponse<CollectionObject<VendorBlockHistoryResponse>>(false, null, 0, null);
        //List<EntityActionLog> result = vendorService.getVendorBlockHistoryList(wholeSalerID);
        CollectionObject<VendorBlockHistoryResponse> result = vendorBlockNewService.getVendorBlockHistoryList(wholeSalerID);

        results.setData(result);
        results.setSuccess(true);
        return results;
    }

    @PostMapping(value = "vendor/vendorunblock", produces = "application/json")
    public ResultCode setvendorunblock(@RequestBody SetVendorUnBlockParameter param) {

        if(param.getWholeSalerID() == null || param.getWholeSalerID() == 0)
            return new ResultCode(false, -1, "unblockfailure");

        if(param.getTypeCode() == null || param.getTypeCode() == 0)
            return new ResultCode(false, -1, "unblockfailure");

        Boolean result = Boolean.FALSE;
        switch (param.getTypeCode()) {
            case 1: result = vendorBlockService.updateVendorAdminLogin(VendorBlockAdminLoginParameterMapper.convertUnblock(param));
                break;
            case 2: result = vendorBlockService.updateVendorAd(VendorBlockAdParameterMapper.convertUnblock(param));
                break;
            case 3: result = vendorBlockService.updateVendorPayout(VendorBlockPayoutParameterMapper.convertUnblock(param));
                break;
            default:
                break;
        }

        if(result)
            return new ResultCode(true, 1, "unblocksucecss");
        else
            return new ResultCode(false, -1, "unblockfailure");
    }

}
	
