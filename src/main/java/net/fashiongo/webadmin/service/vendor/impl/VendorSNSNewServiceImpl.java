package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetVendorSNSListParameter;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.VendorSNSNewService;
import org.springframework.stereotype.Service;

/**
 * Created by jinwoo on 2020-01-08.
 */
@Slf4j
@Service
public class VendorSNSNewServiceImpl implements VendorSNSNewService {

    private HttpClientWrapper httpCaller;

    public VendorSNSNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void create(SetVendorSNSListParameter request, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + request.getWholeSalerID() + "/social-media";
        VendorSocialMediaCommand command = new VendorSocialMediaCommand(request.getSocialMediaID(), request.getSocialMediaUsername());
        httpCaller.post(endpoint, command, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    @Override
    public void delete(SetVendorSNSListParameter request, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + request.getWholeSalerID() + "/social-media/" + request.getSocialMediaID();
        httpCaller.delete(endpoint, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    @Override
    public void modify(SetVendorSNSListParameter request, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + request.getWholeSalerID() + "/social-media/" + request.getSocialMediaID();
        VendorSocialMediaCommand command = new VendorSocialMediaCommand(request.getSocialMediaUsername());
        httpCaller.put(endpoint, command, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
    }

    @Getter
    private class VendorSocialMediaCommand {
        private Integer socialMediaId;
        private String uri;

        private VendorSocialMediaCommand(Integer socialMediaId, String uri) {
            this.socialMediaId = socialMediaId;
            this.uri = uri;
        }

        private VendorSocialMediaCommand(String uri) {
            this.uri = uri;
        }
    }
}
