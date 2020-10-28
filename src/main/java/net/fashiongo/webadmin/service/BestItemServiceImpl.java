package net.fashiongo.webadmin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.bestofbest.response.BestItemCategoryResponse;
import net.fashiongo.webadmin.data.model.bestofbest.response.BestItemProductListResponse;
import net.fashiongo.webadmin.data.model.bestofbest.response.VendorListResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.BestItemService;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class BestItemServiceImpl implements BestItemService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpClientWrapper httpCaller;
    private final ObjectMapper mapper = new ObjectMapper();


    public BestItemServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    private Map<String, String> getHeader() {
        WebAdminLoginUser userInfo = Utility.getUserInfo();
        return FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername());
    }

    private <T> T resolveResponse(FashionGoApiResponse<T> response) {
        if (response == null) {
            throw new RuntimeException("unknown exception occurred while calling fashiongo api.");
        }

        if (response.getHeader().isSuccessful()) {
            return response.getData();
        } else {
            throw new RuntimeException("error: " + response.getHeader().getResultMessage());
        }
    }

    private ResultCode resolveByIOResponse(FashionGoApiResponse<Void> response) {

        if (response == null) {
            throw new RuntimeException("unknown exception occurred while calling fashiongo api.");
        }

        ResultCode result = new ResultCode(response.getHeader().isSuccessful(), response.getHeader().getResultCode(), response.getHeader().getResultMessage());

        return result;
    }

    @Override
    public CollectionObject<VendorListResponse> getVendorList(int pageNum,
                                                              int pageSize,
                                                              Long vendorId,
                                                              String vendorName,
                                                              Integer status,
                                                              Boolean listStatus) {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collection/best-item-block/vendors";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("pn", pageNum)
                .queryParam("ps", pageSize);

        Optional.ofNullable(vendorId)
                .ifPresent(t -> builder.queryParam("vendorId", t));
        Optional.ofNullable(vendorName)
                .ifPresent(t -> builder.queryParam("vendorName", t));
        Optional.ofNullable(status)
                .ifPresent(t -> builder.queryParam("status", t));

        if (listStatus != null) {
            builder.queryParam("listStatus",false);
        }
        else {
            builder.queryParam("listStatus",listStatus);
        }

        UriComponents uri = builder.build(false);

        FashionGoApiResponse<CollectionObject<VendorListResponse>> response = httpCaller.get(uri.toUriString(), getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<VendorListResponse>>>() {});

        return resolveResponse(response);
    }

    @Override
    public ResultCode addUnlistedVendor(Long vendorId) {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collection/best-item-block/vendor/" + vendorId;
        String responseBody =  httpCaller.post(endpoint, null, getHeader());
        try {
            FashionGoApiResponse<Void> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Void>>() {});
            return resolveByIOResponse(response);

        } catch (IOException e) {
            throw new RuntimeException("fail to add unlisted vendor. " + e.getMessage());
        }

    }

    @Override
    public ResultCode removeUnlistedVendor(Long vendorId) {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collection/best-item-block/vendor/" + vendorId;
        String responseBody = httpCaller.delete(endpoint, getHeader());
        try {
            FashionGoApiResponse<Void> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Void>>() {});
            return resolveByIOResponse(response);

        } catch (IOException e) {
            throw new RuntimeException("fail to remove unlisted vendor. " + e.getMessage());
        }

    }

    @Override
    public SingleObject<Integer> getBestItemLimitPerVendor() {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/misc/site-setting/best-item-limit";

        FashionGoApiResponse<SingleObject<Integer>> response = httpCaller.get(endpoint, getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<Integer>>>() {});

        return resolveResponse(response);
    }

    @Override
    public ResultCode updateBestItemLimitPerVendor(int limitPerVendor) {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/misc/site-setting/best-item-limit/" + limitPerVendor;
        String responseBody =  httpCaller.put(endpoint, limitPerVendor, getHeader());
        try {
            FashionGoApiResponse<Void> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Void>>() {});
            return resolveByIOResponse(response);
        } catch (IOException e) {
            throw new RuntimeException("fail to update Best Item Limit per Vendor setting. " + e.getMessage());
        }

    }

    @Override
    public CollectionObject<BestItemProductListResponse> getListProductsList(Integer categoryId,
                                                                             Integer bodySizeId,
                                                                             Integer showItemOption,
                                                                             String vendorStatusList,
                                                                             Long vendorId,
                                                                             Integer productId,
                                                                             String productName,
                                                                             String itemName,
                                                                             String productDescription,
                                                                             String orderBy){

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collection/best-item-block/best-products";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint);

        Optional.ofNullable(categoryId)
                .ifPresent(t -> builder.queryParam("categoryId", t));
        Optional.ofNullable(bodySizeId)
                .ifPresent(t -> builder.queryParam("bodySizeId", t));
        Optional.ofNullable(showItemOption)
                .ifPresent(t -> builder.queryParam("showItemOption", t));
        Optional.ofNullable(vendorStatusList)
                .ifPresent(t -> builder.queryParam("vendorStatusList", t));
        Optional.ofNullable(vendorId)
                .ifPresent(t -> builder.queryParam("vendorId", t));
        Optional.ofNullable(productId)
                .ifPresent(t -> builder.queryParam("productId", t));
        Optional.ofNullable(productName)
                .ifPresent(t -> builder.queryParam("productName", t));
        Optional.ofNullable(itemName)
                .ifPresent(t -> builder.queryParam("itemName", t));
        Optional.ofNullable(productDescription)
                .ifPresent(t -> builder.queryParam("productDescription", t));
        Optional.ofNullable(orderBy)
                .ifPresent(t -> builder.queryParam("orderBy", t));

        UriComponents uri = builder.build(false);

        FashionGoApiResponse<CollectionObject<BestItemProductListResponse>> response = httpCaller.get(uri.toUriString(), getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<BestItemProductListResponse>>>() {});

        return resolveResponse(response);

    }

    @Override
    public ResultCode addUnlistedItem(String productIdList) {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collection/best-item-block/product/" + productIdList;
        String responseBody =  httpCaller.post(endpoint, null, getHeader());
        try {
            FashionGoApiResponse<Void> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Void>>() {});
            return resolveByIOResponse(response);

        } catch (IOException e) {
            throw new RuntimeException("fail to add unlisted items. " + e.getMessage());
        }

    }

    @Override
    public CollectionObject<BestItemProductListResponse> getUnlistProductsList(Integer categoryId,
                                                                               Integer bodySizeId,
                                                                               Integer showItemOption,
                                                                               String vendorStatusList,
                                                                               Long vendorId,
                                                                               Integer productId,
                                                                               String productName,
                                                                               String itemName,
                                                                               String productDescription,
                                                                               String orderBy) {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collection/best-item-block/products";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint);

        Optional.ofNullable(categoryId)
                .ifPresent(t -> builder.queryParam("categoryId", t));
        Optional.ofNullable(bodySizeId)
                .ifPresent(t -> builder.queryParam("bodySizeId", t));
        Optional.ofNullable(showItemOption)
                .ifPresent(t -> builder.queryParam("showItemOption", t));
        Optional.ofNullable(vendorStatusList)
                .ifPresent(t -> builder.queryParam("vendorStatusList", t));
        Optional.ofNullable(vendorId)
                .ifPresent(t -> builder.queryParam("vendorId", t));
        Optional.ofNullable(productId)
                .ifPresent(t -> builder.queryParam("productId", t));
        Optional.ofNullable(productName)
                .ifPresent(t -> builder.queryParam("productName", t));
        Optional.ofNullable(itemName)
                .ifPresent(t -> builder.queryParam("itemName", t));
        Optional.ofNullable(productDescription)
                .ifPresent(t -> builder.queryParam("productDescription", t));
        Optional.ofNullable(orderBy)
                .ifPresent(t -> builder.queryParam("orderBy", t));

        UriComponents uri = builder.build(false);

        FashionGoApiResponse<CollectionObject<BestItemProductListResponse>> response = httpCaller.get(uri.toUriString(), getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<BestItemProductListResponse>>>() {});

        return resolveResponse(response);
    }

    @Override
    public ResultCode removeUnlistedItem(String productIdList) {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collection/best-item-block/product/" + productIdList;
        String responseBody = httpCaller.delete(endpoint, getHeader());
        try {
            FashionGoApiResponse<Void> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Void>>() {});
            return resolveByIOResponse(response);

        } catch (IOException e) {
            throw new RuntimeException("fail to remove unlisted items. " + e.getMessage());
        }

    }

    @Override
    public CollectionObject<BestItemCategoryResponse> getBestItemCategories() {

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collection/best-item/categories";

        FashionGoApiResponse<CollectionObject<BestItemCategoryResponse>> response = httpCaller.get(endpoint, getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<BestItemCategoryResponse>>>() {});

        return resolveResponse(response);
    }
}
