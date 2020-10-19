package net.fashiongo.webadmin.service.vendor.impl;

import net.fashiongo.webadmin.data.model.vendor.response.ActiveVendorResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorsResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorAutocompleteResponse;
import net.fashiongo.webadmin.data.model.vendor.response.VendorResponse;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.vendor.VendorNewService;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendorNewServiceImpl implements VendorNewService {

    private final HttpClientWrapper httpCaller;

    public VendorNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    @Override
    public List<ActiveVendorResponse> getActiveVendors() {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/active";
        WebAdminLoginUser userInfo = Utility.getUserInfo();

        FashionGoApiResponse<GetVendorsResponse> response = httpCaller.get(endpoint, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()),
                new ParameterizedTypeReference<FashionGoApiResponse<GetVendorsResponse>>() {});

        if (response == null) {
            throw new RuntimeException("unknown exception occurred while calling fashiongo api.");
        }

        if (response.getHeader().isSuccessful()) {
            return ActiveVendorResponse.createResponse(response.getData().getVendor());
        } else {
            throw new RuntimeException("fail to call fashiongo api: " + response.getHeader().getResultMessage());
        }
    }

    @Override
    public List<VendorAutocompleteResponse> getShopActiveVendors(String prefix) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/shop-active/" + prefix;
        WebAdminLoginUser userInfo = Utility.getUserInfo();

        FashionGoApiResponse<GetVendorsResponse> response = httpCaller.get(endpoint, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()),
                new ParameterizedTypeReference<FashionGoApiResponse<GetVendorsResponse>>() {});

        if (response == null) {
            throw new RuntimeException("unknown exception occurred while calling fashiongo api.");
        }

        if (response.getHeader().isSuccessful()) {
            return VendorAutocompleteResponse.create(response.getData().getVendor());

        } else {
            throw new RuntimeException("fail to call fashiongo api: " + response.getHeader().getResultMessage());
        }
    }

    @Override
    public VendorResponse getVendorById(Long vendorId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/vendors/" + vendorId;
        WebAdminLoginUser userInfo = Utility.getUserInfo();

        FashionGoApiResponse<GetVendorResponse> response = httpCaller.get(endpoint, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()),
                new ParameterizedTypeReference<FashionGoApiResponse<GetVendorResponse>>() {});

        if (response == null) {
            throw new RuntimeException("unknown exception occurred while calling fashiongo api.");
        }

        if (response.getHeader().isSuccessful()) {
            return response.getData().getVendor();

        } else {
            throw new RuntimeException("fail to call fashiongo api: " + response.getHeader().getResultMessage());
        }
    }
}
