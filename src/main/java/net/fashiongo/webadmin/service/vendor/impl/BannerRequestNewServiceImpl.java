package net.fashiongo.webadmin.service.vendor.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.vendor.SetVendorImageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.vendor.BannerRequestNewService;
import net.fashiongo.webadmin.service.vendor.response.CreateBannerResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class BannerRequestNewServiceImpl implements BannerRequestNewService {

    private final HttpClientWrapper httpCaller;

    public BannerRequestNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void reject(Integer vendorId, SetDenyBannerParameter request, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/banners/" + request.getImageRequestId() + "/reject";
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
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/banners/" + bannerId;
        httpCaller.delete(endpoint, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    private Integer insert(Integer imageOriginalId, Integer vendorId, Integer bannerTypeId, String fileName, Integer requestedUserId, String requestUserName) {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/banners";
        BannerImageCommand vendorBannerImageCommand = new BannerImageCommand(imageOriginalId, bannerTypeId, fileName);
        String responseBody = httpCaller.post(endpoint, vendorBannerImageCommand, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
        try {
            FashionGoApiResponse<CreateBannerResponse> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<CreateBannerResponse>>(){});
            if(response.getHeader().isSuccessful()) {
                CreateBannerResponse bannerResponse = response.getData();
                return Optional.ofNullable(bannerResponse.getContent()).orElse(0);
            } else {
                throw new RuntimeException("fail to inert a new image in new fashiongo api");
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to inert a new image in new fashiongo api");
        }
    }

    @Override
    public void approve(Integer vendorId, Integer bannerId, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/banners/" + bannerId + "/approve";
        httpCaller.put(endpoint, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
    }

    private void activate(Integer vendorId, Integer bannerId, Integer requestedUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/banners/" + bannerId + "/activate";
        httpCaller.put(endpoint, FashionGoApiHeader.getHeader(requestedUserId, requestUserName));
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
