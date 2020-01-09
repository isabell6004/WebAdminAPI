package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorBlockListParameter;
import net.fashiongo.webadmin.model.primary.EntityActionLog;
import net.fashiongo.webadmin.model.primary.VwVendorBlocked;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.service.vendor.VendorBlockService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @author roy
 */
@RestController
@Slf4j
public class VendorBlockController {

    private VendorService vendorService;

    private VendorBlockService vendorBlockService;

    public VendorBlockController(
            VendorService vendorService,
            VendorBlockService vendorBlockService
    ) {
        this.vendorService = vendorService;
        this.vendorBlockService = vendorBlockService;
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
    public JsonResponse<List<VwVendorBlocked>> getVendorBlockList(@RequestBody GetVendorBlockListParameter parameters) throws ParseException {
        JsonResponse<List<VwVendorBlocked>> results = new JsonResponse<List<VwVendorBlocked>>(false, null, 0, null);
        List<VwVendorBlocked> result = vendorService.getVendorBlockList(parameters);

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
    public JsonResponse<List<EntityActionLog>> getVendorBlockHistoryList(@RequestParam(value = "WholeSalerID") Integer wholeSalerID) {
        JsonResponse<List<EntityActionLog>> results = new JsonResponse<List<EntityActionLog>>(false, null, 0, null);
        List<EntityActionLog> result = vendorService.getVendorBlockHistoryList(wholeSalerID);

        results.setData(result);
        results.setSuccess(true);
        return results;
    }

    @PostMapping(value = "vendor/setvendorblock", produces = "application/json")
    public ResultCode setvendorblock(@RequestBody SetVendorBlockParameter param) {

        if(param.getWholeSalerID() == null || param.getWholeSalerID() == 0)
            return new ResultCode(false, -1, "invalid a parameter[WholeSalerID]");

        if(param.getBlockReasonID() == null || param.getBlockReasonID() == 0)
            return new ResultCode(false, -1, "invalid a parameter[BlockReasonID]");

        if(vendorBlockService.block(param))
            return new ResultCode(true, 1, "success");
        else
            return new ResultCode(false, -1, "failure");
    }

    @PostMapping(value = "vendor/setvendorblockupdate")
    public Integer setvendorblockupdate(@RequestBody SetVendorBlockUpdate param) {

        if(param.getWholeSalerID() == null || param.getWholeSalerID() == 0)
            return -99;

        if(param.getBlockReasonID() == null || param.getBlockReasonID() == 0)
            return -99;

        return vendorBlockService.modifyBlockReason(param);
    }

    @PostMapping(value = "vendor/delvendorblock", produces = "application/json")
    public ResultCode delvendorblock(@RequestBody DelVendorBlockParameter param) {
        if(param.getWholeSalerID() == null || param.getWholeSalerID() == 0)
            return new ResultCode(false, -1, "deletefailure");

        if(param.getBlockID() == null || param.getBlockID() == 0)
            return new ResultCode(false, -1, "deletefailure");

        if(vendorBlockService.unblock(param))
            return new ResultCode(true, 1, "deletesucecss");
        else
            return new ResultCode(false, -1, "deletefailure");
    }

}
	
