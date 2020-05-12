package net.fashiongo.webadmin.service.vendor.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.SetVendorSeoParameter;
import net.fashiongo.webadmin.data.model.vendor.VendorSeoInfoResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorSeoInfoResponse;
import net.fashiongo.webadmin.exception.vendor.VendorSeoOperationException;
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
                if (fashionGoApiResponse.getData().getVendorSeoInfo() != null) {
                    return fashionGoApiResponse.getData().getVendorSeoInfo();
                } else {
                    return null;
                }
            } else {
                throw new RuntimeException("fail to get vendor seo in new fashiongo api");
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to get vendor seo in new fashiongo api 1");
        }
    }    
    
    @Override
	public void createVendorSeo(Long vendorId, SetVendorSeoParameter request) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/createvendorseo";
        
        VendorSeo newRequest = new VendorSeo(request);

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        try {
            String responseBody = httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));
            FashionGoApiResponse<Object> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Object>>() {});
            if (!fashionGoApiResponse.getHeader().isSuccessful()) {
                log.error("fail to create vendor seo. vendorId: {}, code : {}, message : {}", vendorId, fashionGoApiResponse.getHeader().getResultCode(), fashionGoApiResponse.getHeader().getResultMessage());
                throw new VendorSeoOperationException("fail to create vendor seo. vendorId: " + vendorId);
            }
        } catch (IOException e) {
            log.error("fail to create vendor seo.", e);
            throw new VendorSeoOperationException("fail to create vendor seo. " + e.getMessage());
        }  	
    }

    @Override
	public void modifyVendorSeo(Long vendorId, SetVendorSeoParameter request) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId + "/updatevendorseo";

        VendorSeo newRequest = new VendorSeo(request);
        		
        WebAdminLoginUser userInfo = Utility.getUserInfo();
        try {
            String responseBody = httpCaller.post(endpoint, newRequest, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));
            FashionGoApiResponse<Object> fashionGoApiResponse = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Object>>() {});
            if (!fashionGoApiResponse.getHeader().isSuccessful()) {
                log.error("fail to update vendor seo. vendorId: {}, code : {}, message : {}", vendorId, fashionGoApiResponse.getHeader().getResultCode(), fashionGoApiResponse.getHeader().getResultMessage());
                //throw new VendorContractOperationException("fail to create vendor seo. vendorId: " + vendorId);
            }
        } catch (IOException e) {
            log.error("fail to update vendor seo.", e);
            //throw new VendorContractOperationException("fail to create vendor seo. " + e.getMessage());
        }   	
    }
	
    @Getter
    private class VendorSeo {

        //private Long vendorseoId;
        private String metaKeyword;
        private String metaDescription;

        private VendorSeo(SetVendorSeoParameter request) {
            //this.vendorseoId = Long.valueOf(request.getVendorseoId());
            this.metaKeyword = request.getMetaKeyword();
            this.metaDescription = request.getMetaDescription();
        }

    }    
    
}
