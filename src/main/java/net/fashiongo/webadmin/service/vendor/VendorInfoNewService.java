package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorSettingParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import net.fashiongo.webadmin.model.vendor.StatusType;
import org.springframework.scheduling.annotation.Async;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by jinwoo on 2020-01-06.
 */
public interface VendorInfoNewService {

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void update(VendorDetailInfo request, String originalUserId, Integer requestUserId, String requestUserName);

    void updateDetailInfo(SetVendorSettingParameter request, VendorDetailInfo vendorDetailInfo, Integer requestUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void updateStatusAndCloseDate(Integer wholeSalerID, Integer newStatusTypeValue, LocalDateTime contractExpireDate, Integer requestUserId, String requestUserName);
}
