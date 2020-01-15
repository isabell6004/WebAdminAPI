package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetVendorSNSListParameter;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.FashionGoApiHeader;
import net.fashiongo.webadmin.service.vendor.VendorSNSNewService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by jinwoo on 2020-01-08.
 */
@Slf4j
@Service
public class VendorSNSNewServiceImpl implements VendorSNSNewService {

    @Value("${fashionGoApi.fashionGo-api.endpoint}")
    private String fashionGoApi;

    private HttpClientWrapper httpCaller;

    public VendorSNSNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void create(SetVendorSNSListParameter request) {
        final String endpoint = fashionGoApi + "/v1.0/vendor/" + request.getWholeSalerID() + "/sns";
        VendorSocialMediaCommand command = VendorSocialMediaCommand.create(request.getSocialMediaID(), request.getSocialMediaUsername());
        httpCaller.post(endpoint, command, FashionGoApiHeader.getHeader());
    }

    @Override
    public void delete(SetVendorSNSListParameter request) {
        final String endpoint = fashionGoApi + "/v1.0/vendor/" + request.getWholeSalerID() + "/sns/" + request.getSocialMediaID();
        httpCaller.delete(endpoint, FashionGoApiHeader.getHeader());
    }

    @Override
    public void modify(SetVendorSNSListParameter request) {
        final String endpoint = fashionGoApi + "/v1.0/vendor/" + request.getWholeSalerID() + "/sns/" + request.getSocialMediaID();
        VendorSocialMediaCommand command = VendorSocialMediaCommand.create(request.getSocialMediaUsername());
        httpCaller.put(endpoint, command, FashionGoApiHeader.getHeader());
    }

    @Getter
    static class VendorSocialMediaCommand {
        private Integer socialMediaId;
        private String uri;

        static VendorSocialMediaCommand create(Integer socialMediaId, String uri) {
            VendorSocialMediaCommand command = new VendorSocialMediaCommand();
            command.socialMediaId = socialMediaId;
            command.uri = uri;
            return command;
        }

        static VendorSocialMediaCommand create(String uri) {
            VendorSocialMediaCommand command = new VendorSocialMediaCommand();
            command.uri = uri;
            return command;
        }
    }
}
