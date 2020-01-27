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
    public void insert(Integer imageOriginalId, SetVendorImageParameter request, Integer requestedUserId, String requestUserName) {
        // bannerId == imageOriginId,
        Integer bannerId = insert(imageOriginalId, request.getWid(), request.getType(), request.getFilename(), requestedUserId, requestUserName);
        approve(request.getWid(), imageOriginalId, requestedUserId, requestUserName);
        activate(request.getWid(), imageOriginalId, requestedUserId, requestUserName);
    }

    @Override
    public void delete(Integer vendorId, Integer bannerId, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + bannerId;
        httpCaller.delete(endpoint, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    private Integer insert(Integer imageOriginalId, Integer vendorId, Integer bannerTypeId, String fileName, Integer requestedUserId, String requestUserName) {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner";
        VendorBannerImageCommand vendorBannerImageCommand = new VendorBannerImageCommand(imageOriginalId, bannerTypeId, fileName);
        ResponseEntity<JsonResponse> response = httpCaller.post(endpoint, vendorBannerImageCommand, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));

        if(Optional.ofNullable(response.getBody()).orElseThrow(RuntimeException::new).isSuccess()) {
            Map<String, Integer> result = (Map<String, Integer>) response.getBody().getData();
            return Optional.ofNullable(result.get("content")).orElse(0);
        } else {
            throw new RuntimeException("fail to inert a new image in new fashiongo api");
        }
    }

    private ResponseEntity<JsonResponse> approve(Integer vendorId, Integer bannerId, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + bannerId + "/approve";
        return httpCaller.put(endpoint, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    private ResponseEntity<JsonResponse> activate(Integer vendorId, Integer bannerId, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + bannerId + "/activate";
        return httpCaller.put(endpoint, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Getter
    private class VendorBannerImageCommand {

        private Integer id;

        private Integer bannerTypeId;

        private String fileName;

        private VendorBannerImageCommand(Integer id, Integer bannerTypeId, String fileName) {
            this.id = id;
            this.bannerTypeId = bannerTypeId;
            this.fileName = fileName;
        }
    }
}
