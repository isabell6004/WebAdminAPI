package net.fashiongo.webadmin.service.vendor;

import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorBlockAdParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorBlockAdminLoginParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorBlockPayoutParameter;
import net.fashiongo.webadmin.data.model.vendor.response.CodeVendorBlockReasonResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorBlockHistoryResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorBlockPayoutScheduleInfoResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorBlockResponse;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorBlockListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetVendorUnBlockParameter;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * Created by jinwoo on 2020-01-02.
 */
public interface VendorBlockNewService {

//    @Async("fashionGoApiThreadPoolTaskExecutor")
//    void blockVendor(SetVendorBlockParameter request, Integer requestedUserId, String requestUserName);
//
//    @Async("fashionGoApiThreadPoolTaskExecutor")
//    void unblockVendor(DelVendorBlockParameter request, Integer requestedUserId, String requestUserName);

    CollectionObject<VendorBlockResponse> getVendorBlockList(GetVendorBlockListParameter parameter);

    CollectionObject<VendorBlockHistoryResponse> getVendorBlockHistoryList(Long vendorId);

    List<CodeVendorBlockReasonResponse> getCodeVendorBlockReason(Long vendorId);
    public VendorBlockPayoutScheduleInfoResponse getVendorPreviousPayoutScheduleInfo(Long vendorId);
    Boolean updateBlock(VendorBlockAdminLoginParameter param);
    Boolean updateAdBlock(VendorBlockAdParameter param);
    Boolean updatePayoutBlock(VendorBlockPayoutParameter param);

}
