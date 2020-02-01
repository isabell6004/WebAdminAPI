package net.fashiongo.webadmin.controller.vendor;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetHoldVendorParameter;
import net.fashiongo.webadmin.data.model.vendor.SetHoldVendorUpdateParameter;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.UserService;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.service.renewal.RenewalVendorService;
import net.fashiongo.webadmin.service.vendor.VendorHoldService;
import net.fashiongo.webadmin.service.vendor.VendorInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author roy
 */
@RestController
@Slf4j
public class VendorHoldController {

	private VendorHoldService vendorHoldService;
    private final CacheService cacheService;

    public VendorHoldController(VendorHoldService vendorHoldService,
                                CacheService cacheService) {
    	this.vendorHoldService = vendorHoldService;
    	this.cacheService = cacheService;
	}

    @PostMapping(value = "vendor/setholdvendor", produces = "application/json")
    public ResultCode setholdvendor(@RequestBody SetHoldVendorParameter param) {
    	Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
    	Integer holdType = param.getHoldType() == null ? 0 : param.getHoldType();
    	Boolean active = param.getActive() == null ? false : param.getActive();

		Date holdFromDate;
		Date holdToDate;
		Timestamp holdFrom = Timestamp.valueOf(LocalDateTime.now());
		Timestamp holdTo = Timestamp.valueOf(LocalDateTime.now());
		try {
			holdFromDate = StringUtils.isEmpty(param.getHoldFrom()) ? new Date() : new SimpleDateFormat("MM/dd/yyyy").parse(param.getHoldFrom());
			holdToDate = StringUtils.isEmpty(param.getHoldTo()) ? new Date() : new SimpleDateFormat("MM/dd/yyyy").parse(param.getHoldTo());

			holdFrom = StringUtils.isEmpty(param.getHoldFrom()) ? Timestamp.valueOf(LocalDateTime.now()) : new Timestamp(holdFromDate.getTime());
			holdTo = StringUtils.isEmpty(param.getHoldTo()) ? Timestamp.valueOf(LocalDateTime.now()) : new Timestamp(holdToDate.getTime());
		} catch (ParseException e) {
			log.warn(e.getMessage(), e);
		}

    	Boolean result = vendorHoldService.setHoldVendor(wholeSalerID, holdType, active, holdFrom, holdTo);

        if(result) {
            return new ResultCode(true, 1, "success");
        } else {
            return new ResultCode(false, -1, "savefailure");
        }
	}

	@PostMapping(value = "vendor/setholdvendorupdate", produces = "application/json")
	public Integer setholdvendorupdate(@RequestBody SetHoldVendorUpdateParameter param) {

        Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
    	Integer logID = param.getLogID() == null ? 0 : param.getLogID();
    	Boolean active = param.getActive() == null ? false : param.getActive();
		Date holdFromDate;
		Date holdToDate;
		Timestamp holdFrom = Timestamp.valueOf(LocalDateTime.now());
		Timestamp holdTo = Timestamp.valueOf(LocalDateTime.now());
		try {
			holdFromDate = StringUtils.isEmpty(param.getHoldFrom()) ? new Date() : new SimpleDateFormat("MM/dd/yyyy").parse(param.getHoldFrom());
			holdToDate = StringUtils.isEmpty(param.getHoldTo()) ? new Date() : new SimpleDateFormat("MM/dd/yyyy").parse(param.getHoldTo());

			holdFrom = StringUtils.isEmpty(param.getHoldFrom()) ? Timestamp.valueOf(LocalDateTime.now()) : new Timestamp(holdFromDate.getTime());
			holdTo = StringUtils.isEmpty(param.getHoldTo()) ? Timestamp.valueOf(LocalDateTime.now()) : new Timestamp(holdToDate.getTime());
		} catch (ParseException e) {
			log.warn(e.getMessage(), e);
		}

		Integer result = vendorHoldService.setHoldVendorUpdate(wholeSalerID, logID, active,holdFrom, holdTo);

		cacheService.cacheEvictVendor(null);

    	return result;
	}

}
	
