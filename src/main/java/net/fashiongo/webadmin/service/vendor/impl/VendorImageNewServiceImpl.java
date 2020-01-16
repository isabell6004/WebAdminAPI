package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetVendorImageParameter;
import net.fashiongo.webadmin.service.FashionGoApiConfig;
import net.fashiongo.webadmin.service.FashionGoApiHeader;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.VendorImageNewService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by jinwoo on 2020-01-08.
 */
@Slf4j
@Service
public class VendorImageNewServiceImpl implements VendorImageNewService {

    private HttpClientWrapper httpCaller;

    public VendorImageNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void insert(SetVendorImageParameter request) {
        try {
            Integer bannerId = insert(request.getWid(), request.getType(), request.getFilename());
            approve(request.getWid(), bannerId);
            activate(request.getWid(), bannerId);
        } catch (Exception e) {
        }
    }

    @Override
    public void delete(Integer vendorId, Integer bannerId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + bannerId;
        httpCaller.delete(endpoint);
    }

    private Integer insert(Integer vendorId, Integer bannerTypeId, String fileName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner";
        VendorBannerImageCommand vendorBannerImageCommand = VendorBannerImageCommand.create(bannerTypeId, fileName);
        ResponseEntity<JsonResponse> response = httpCaller.post(endpoint, vendorBannerImageCommand, FashionGoApiHeader.getHeader());

        if(response.getBody().isSuccess()) {
            Map<String, Integer> result = (Map<String, Integer>) response.getBody().getData();
            return Integer.valueOf(result.get("content"));
        } else {
            throw new RuntimeException();
        }
    }

    private void approve(Integer vendorId, Integer bannerId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + bannerId + "/approve";
        ResponseEntity<JsonResponse> response = httpCaller.put(endpoint, FashionGoApiHeader.getHeader());
    }

    private void activate(Integer vendorId, Integer bannerId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + bannerId + "/activate";
        ResponseEntity<JsonResponse> response = httpCaller.put(endpoint, FashionGoApiHeader.getHeader());
    }

    @Getter
    static class VendorBannerImageCommand {

        private Integer bannerTypeId;

        private String fileName;

        static VendorBannerImageCommand create(Integer bannerTypeId, String fileName) {
            VendorBannerImageCommand command = new VendorBannerImageCommand();
            command.bannerTypeId = bannerTypeId;
            command.fileName = fileName;
            return command;
        }
    }
}
