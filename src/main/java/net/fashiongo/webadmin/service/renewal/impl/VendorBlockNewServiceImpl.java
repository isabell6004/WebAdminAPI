package net.fashiongo.webadmin.service.renewal.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockUpdate;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.renewal.VendorBlockNewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinwoo on 2020-01-02.
 */
@Service
@Slf4j
public class VendorBlockNewServiceImpl implements VendorBlockNewService {

    private final static String Vendor_Request_Command_Key_Name = "setting";

    @Value("${api.endpoint.newVendorApi}")
    private String newVendorApi;

    private HttpClientWrapper httpCaller;

    public VendorBlockNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    private void modifyBlockStatus(Integer wholeSalerId, Boolean isBlock, Long blockReasonId) {
        final String endpoint = newVendorApi + "/v1.0/vendor/" + wholeSalerId;

        VendorBlockNewServiceImpl.VendorBlockStatusCommand newRequest = VendorBlockNewServiceImpl.VendorBlockStatusCommand.create(isBlock, blockReasonId);
        Map<String, Object> wrappedRequest = new HashMap<>();
        wrappedRequest.put(Vendor_Request_Command_Key_Name, newRequest);

        httpCaller.put(endpoint, wrappedRequest, VendorApiHeader.getHeader());
    }

    @Override
    public void blockVendor(SetVendorBlockParameter request) {
        modifyBlockStatus(request.getWholeSalerID(), true, Long.valueOf(request.getBlockReasonID()));
    }

    @Override
    public void unblockVendor(DelVendorBlockParameter request) {
        modifyBlockStatus(request.getWholeSalerID(), false, null);
    }

    @Override
    public void modifyBlockReason(SetVendorBlockUpdate request) {
        final String endpoint = newVendorApi + "/v1.0/vendor/" + request.getWholeSalerID();

        VendorBlockNewServiceImpl.VendorBlockStatusCommand newRequest = VendorBlockNewServiceImpl.VendorBlockStatusCommand.create(request.getIsBlock(), Long.valueOf(request.getBlockReasonID()));
        Map<String, Object> wrappedRequest = new HashMap<>();
        wrappedRequest.put(Vendor_Request_Command_Key_Name, newRequest);

        httpCaller.post(endpoint, wrappedRequest, VendorApiHeader.getHeader());
    }

    @Getter
    private static class VendorBlockStatusCommand {
        private Boolean isBlock;
        private Long blockReasonId;

        static VendorBlockStatusCommand create(Boolean isBlock, Long blockReasonId) {
            VendorBlockStatusCommand command = new VendorBlockStatusCommand();
            command.isBlock = isBlock;
            command.blockReasonId = blockReasonId;
            return command;
        }
    }
}
