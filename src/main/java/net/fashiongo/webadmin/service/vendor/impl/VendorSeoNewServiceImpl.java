package net.fashiongo.webadmin.service.vendor.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.VendorSeoInfoResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorSeoInfoResponse;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.vendor.VendorSeoNewService;
import net.fashiongo.webadmin.utility.Utility;

@Service
@Slf4j

public class VendorSeoNewServiceImpl implements VendorSeoNewService {

    private HttpClientWrapper httpCaller;

    private final ObjectMapper mapper = new ObjectMapper();

    public VendorSeoNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }
	
    @Override
    public VendorSeoInfoResponse inquiryVendorSeo(Long vendorId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/vendorseo";

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        String responseBody = httpCaller.get(endpoint, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));

        try {
            mapper.registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            FashionGoApiResponse<GetVendorSeoInfoResponse> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<GetVendorSeoInfoResponse>>() {});
            if (fashionGoApiResponse.getHeader().isSuccessful()) {
                if (fashionGoApiResponse.getData().getVendorSeoInfo().getVendorSeoId() != null) {
                    return fashionGoApiResponse.getData().getVendorSeoInfo();
                } else {
                    return null;
                }
            } else {
                throw new RuntimeException("fail to get vendor seo in new fashiongo api");
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to get vendor seo in new fashiongo api");
        }
    }    
    
    @Override
	public void createVendorSeo(Long vendorId, VendorSeoInfoResponse request) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/createvendorseo";
        VendorSeoInfoResponse newRequest = new VendorSeoInfoResponse();

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        try {
            String responseBody = httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));
            FashionGoApiResponse<Object> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Object>>() {});
            if (!fashionGoApiResponse.getHeader().isSuccessful()) {
                log.error("fail to create vendor seo. vendorId: {}, code : {}, message : {}", vendorId, fashionGoApiResponse.getHeader().getResultCode(), fashionGoApiResponse.getHeader().getResultMessage());
                //throw new VendorContractOperationException("fail to create vendor seo. vendorId: " + vendorId);
            }
        } catch (IOException e) {
            log.error("fail to create vendor contract document.", e);
            //throw new VendorContractOperationException("fail to create vendor seo. " + e.getMessage());
        }  	
    }

    @Override
	public void modifyVendorSeo(Long vendorId, VendorSeoInfoResponse request) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/updatevendorseo";
        VendorSeoInfoResponse newRequest = new VendorSeoInfoResponse();

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        try {
            String responseBody = httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));
            FashionGoApiResponse<Object> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Object>>() {});
            if (!fashionGoApiResponse.getHeader().isSuccessful()) {
                log.error("fail to create vendor seo. vendorId: {}, code : {}, message : {}", vendorId, fashionGoApiResponse.getHeader().getResultCode(), fashionGoApiResponse.getHeader().getResultMessage());
                //throw new VendorContractOperationException("fail to create vendor seo. vendorId: " + vendorId);
            }
        } catch (IOException e) {
            log.error("fail to create vendor contract document.", e);
            //throw new VendorContractOperationException("fail to create vendor seo. " + e.getMessage());
        }   	
    }
	
}
