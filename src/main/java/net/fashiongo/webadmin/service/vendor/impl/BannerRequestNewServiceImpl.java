package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.BannerRequestNewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BannerRequestNewServiceImpl implements BannerRequestNewService {

    @Value("${api.endpoint.newVendorApi}")
    private String newVendorApi;

    private final HttpClientWrapper httpCaller;

    public BannerRequestNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void approveBanner(Integer vendorId, SetDenyBannerParameter request) {
        final String endpoint = newVendorApi + "/vendor/" + vendorId + "/banner/" + request.getImageRequestId() + "/approve";
        httpCaller.put(endpoint, null, VendorApiHeader.getHeader());
    }

    @Override
    public void rejectBanner(Integer vendorId, SetDenyBannerParameter request) {
        final String endpoint = newVendorApi + "/vendor/" + vendorId + "/banner/" + request.getImageRequestId() + "/reject";
        BannerRequestCommand newRequest = new BannerRequestCommand(request);
        httpCaller.put(endpoint, newRequest, VendorApiHeader.getHeader());
    }

    @Override
    public void deleteBanner(Integer vendorId, SetDenyBannerParameter request) {
        final String endpoint = newVendorApi + "/vendor/" + vendorId + "/banner/" + request.getImageRequestId();
        httpCaller.delete(endpoint, VendorApiHeader.getHeader());
    }

    @Getter
    private class BannerRequestCommand {
        private String rejectReason;

        private BannerRequestCommand(SetDenyBannerParameter request) {
            this.rejectReason = request.getDenialReason();
        }
    }
}
