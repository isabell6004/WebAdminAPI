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
import java.util.Optional;

@Slf4j
@Service
public class VendorImageNewServiceImpl implements VendorImageNewService {

    private HttpClientWrapper httpCaller;

    public VendorImageNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void insert(SetVendorImageParameter request) {
        Integer bannerId = insert(request.getWid(), request.getType(), request.getFilename());
        approve(request.getWid(), bannerId);
        activate(request.getWid(), bannerId);
    }

    @Override
    public void delete(Integer vendorId, Integer bannerId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + bannerId;
        httpCaller.delete(endpoint);
    }

    private Integer insert(Integer vendorId, Integer bannerTypeId, String fileName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner";
        VendorBannerImageCommand vendorBannerImageCommand = new VendorBannerImageCommand(bannerTypeId, fileName);
        ResponseEntity<JsonResponse> response = httpCaller.post(endpoint, vendorBannerImageCommand, FashionGoApiHeader.getHeader());

        if(Optional.ofNullable(response.getBody()).orElseThrow(RuntimeException::new).isSuccess()) {
            Map<String, Integer> result = (Map<String, Integer>) response.getBody().getData();
            return Optional.ofNullable(result.get("content")).orElse(0);
        } else {
            throw new RuntimeException();
        }
    }

    private ResponseEntity<JsonResponse> approve(Integer vendorId, Integer bannerId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + bannerId + "/approve";
        return httpCaller.put(endpoint, FashionGoApiHeader.getHeader());
    }

    private ResponseEntity<JsonResponse> activate(Integer vendorId, Integer bannerId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + bannerId + "/activate";
        return httpCaller.put(endpoint, FashionGoApiHeader.getHeader());
    }

    @Getter
    private class VendorBannerImageCommand {

        private Integer bannerTypeId;

        private String fileName;

        private VendorBannerImageCommand(Integer bannerTypeId, String fileName) {
            this.bannerTypeId = bannerTypeId;
            this.fileName = fileName;
        }
    }
}
