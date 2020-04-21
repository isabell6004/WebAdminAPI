package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorSettingParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;

public interface VendorInfoNewService {

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void update(VendorDetailInfo request, String originalUserId, Integer requestUserId, String requestUserName);

    void updateDetailInfo(SetVendorSettingParameter request, VendorDetailInfo vendorDetailInfo, Integer requestUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void updateStatusAndCloseDate(Integer wholeSalerID, Integer newStatusTypeValue, LocalDateTime contractExpireDate, Integer requestUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void updateStatus(Integer wholeSalerID, Integer newStatusTypeValue, Integer requestUserId, String requestUserName);

}
