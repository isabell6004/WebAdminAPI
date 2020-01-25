package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.service.FashionGoApiConfig;
import net.fashiongo.webadmin.service.FashionGoApiHeader;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.BannerRequestNewService;
import org.springframework.stereotype.Service;

@Service
public class BannerRequestNewServiceImpl implements BannerRequestNewService {

    private final HttpClientWrapper httpCaller;

    public BannerRequestNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void approveBanner(Integer vendorId, SetDenyBannerParameter request, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/vendor/" + vendorId + "/banner/" + request.getImageRequestId() + "/approve";
        httpCaller.put(endpoint, null, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    @Override
    public void rejectBanner(Integer vendorId, SetDenyBannerParameter request, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/vendor/" + vendorId + "/banner/" + request.getImageRequestId() + "/reject";
        BannerRequestCommand newRequest = new BannerRequestCommand(request);
        httpCaller.put(endpoint, newRequest, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    @Override
    public void deleteBanner(Integer vendorId, SetDenyBannerParameter request, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/vendor/" + vendorId + "/banner/" + request.getImageRequestId();
        httpCaller.delete(endpoint, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    @Getter
    private class BannerRequestCommand {
        private String rejectReason;

        private BannerRequestCommand(SetDenyBannerParameter request) {
            this.rejectReason = request.getDenialReason();
        }
    }
}
