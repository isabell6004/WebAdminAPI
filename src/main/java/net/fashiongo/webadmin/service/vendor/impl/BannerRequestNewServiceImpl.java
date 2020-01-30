package net.fashiongo.webadmin.service.vendor.impl;

import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorImageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.service.FashionGoApiConfig;
import net.fashiongo.webadmin.service.FashionGoApiHeader;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.vendor.BannerRequestNewService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class BannerRequestNewServiceImpl implements BannerRequestNewService {

    private final HttpClientWrapper httpCaller;

    public BannerRequestNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public void reject(Integer vendorId, SetDenyBannerParameter request, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + request.getImageRequestId() + "/reject";
        BannerRequestCommand newRequest = new BannerRequestCommand(request);
        httpCaller.put(endpoint, newRequest, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
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
        BannerImageCommand vendorBannerImageCommand = new BannerImageCommand(imageOriginalId, bannerTypeId, fileName);
        ResponseEntity<JsonResponse> response = httpCaller.post(endpoint, vendorBannerImageCommand, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));

        if(Optional.ofNullable(response.getBody()).orElseThrow(RuntimeException::new).isSuccess()) {
            Map<String, Integer> result = (Map<String, Integer>) response.getBody().getData();
            return Optional.ofNullable(result.get("content")).orElse(0);
        } else {
            throw new RuntimeException("fail to inert a new image in new fashiongo api");
        }
    }

    @Override
    public ResponseEntity<JsonResponse> approve(Integer vendorId, Integer bannerId, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + bannerId + "/approve";
        return httpCaller.put(endpoint, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    private ResponseEntity<JsonResponse> activate(Integer vendorId, Integer bannerId, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendor/" + vendorId + "/banner/" + bannerId + "/activate";
        return httpCaller.put(endpoint, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    @Getter
    private class BannerImageCommand {

        private Integer id;

        private Integer bannerTypeId;

        private String fileName;

        private BannerImageCommand(Integer id, Integer bannerTypeId, String fileName) {
            this.id = id;
            this.bannerTypeId = bannerTypeId;
            this.fileName = fileName;
        }
    }

    @Getter
    private class BannerRequestCommand {
        private String rejectReason;

        private BannerRequestCommand(SetDenyBannerParameter request) {
            this.rejectReason = request.getDenialReason();
        }
    }

}
