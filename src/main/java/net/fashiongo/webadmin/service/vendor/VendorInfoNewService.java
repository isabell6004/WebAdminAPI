package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorSettingParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import net.fashiongo.webadmin.data.model.vendor.response.CodeVendorBlockReasonResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorBlockInfoResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorBlockPayoutScheduleInfoResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorSettingDetailResponse;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.util.List;

public interface VendorInfoNewService {

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void update(VendorDetailInfo request, String originalUserId, Integer requestUserId, String requestUserName);

    void updateDetailInfo(SetVendorSettingParameter request, VendorDetailInfo vendorDetailInfo, Integer requestUserId, String requestUserName);


    @Async("fashionGoApiThreadPoolTaskExecutor")
    void updateStatusAndCloseDate(Integer wholeSalerID, Integer newStatusTypeValue, LocalDateTime contractExpireDate, Integer requestUserId, String requestUserName);

    @Async("fashionGoApiThreadPoolTaskExecutor")
    void updateStatus(Integer wholeSalerID, Integer newStatusTypeValue, Integer requestUserId, String requestUserName);

    VendorBlockInfoResponse getVendorBlockInfo(Long vendorId);
    VendorSettingDetailResponse getVendorSettingDetail(Long vendorId);
    void updatePayoutScheduleLock(Long vendorId, Boolean isPayoutScheduleLock);
}
