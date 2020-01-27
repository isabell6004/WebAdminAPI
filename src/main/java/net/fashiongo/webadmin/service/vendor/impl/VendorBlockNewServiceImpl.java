package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockParameter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorBlockUpdate;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.service.FashionGoApiConfig;
import net.fashiongo.webadmin.service.FashionGoApiHeader;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.VendorBlockNewService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class VendorBlockNewServiceImpl implements VendorBlockNewService {

    private final static String Vendor_Request_Command_Key_Name = "setting";

    private HttpClientWrapper httpCaller;

    public VendorBlockNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    private void modifyBlockStatus(Integer wholeSalerId, Boolean isBlock, Long blockReasonId, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + wholeSalerId;

        VendorBlockStatusCommand newRequest = new VendorBlockStatusCommand(isBlock, blockReasonId);
        Map<String, Object> wrappedRequest = new HashMap<>();
        wrappedRequest.put(Vendor_Request_Command_Key_Name, newRequest);

        httpCaller.put(endpoint, wrappedRequest, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Override
    public void blockVendor(SetVendorBlockParameter request, Integer requestedUserId, String requestUserName) {
        modifyBlockStatus(request.getWholeSalerID(), true, Long.valueOf(request.getBlockReasonID()), requestedUserId, requestUserName);
    }

    @Override
    public void unblockVendor(DelVendorBlockParameter request, Integer requestedUserId, String requestUserName) {
        modifyBlockStatus(request.getWholeSalerID(), false, null, requestedUserId, requestUserName);
    }

    @Override
    public void modifyBlockReason(SetVendorBlockUpdate request, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + request.getWholeSalerID();
        VendorInfoCommand command = new VendorInfoCommand(new VendorBlockStatusCommand(request.getIsBlock(), Long.valueOf(request.getBlockReasonID())));
        httpCaller.post(endpoint, command, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Getter
    private class VendorInfoCommand<T> {
        private VendorBlockStatusCommand setting;
        private VendorInfoCommand(VendorBlockStatusCommand setting) {
            this.setting = setting;
        }
    }

    @Getter
    private class VendorBlockStatusCommand {
        private Boolean isBlock;
        private Long blockReasonId;

        private VendorBlockStatusCommand(Boolean isBlock, Long blockReasonId) {
            this.isBlock = isBlock;
            this.blockReasonId = blockReasonId;
        }
    }
}
