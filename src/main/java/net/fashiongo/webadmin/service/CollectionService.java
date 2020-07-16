package net.fashiongo.webadmin.service;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.collection.CollectionBulkPatchParameter;
import net.fashiongo.webadmin.data.model.collection.CollectionSaveParameter;
import net.fashiongo.webadmin.data.model.collection.response.*;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.product.command.category.ProductCategoryResponse;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;
import net.fashiongo.webadmin.support.storage.SwiftApiCallFactory;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CollectionService {

    private final HttpClientWrapper httpCaller;

    private final SwiftApiCallFactory swiftApiCallFactory;

    private final String collectionStorageRootContainer;

    private final String collectionStorageDirectory;

    public CollectionService(HttpClientWrapper httpCaller,
                             @Qualifier("collectionBannerSwiftApiCallFactory") SwiftApiCallFactory swiftApiCallFactory,
                             @Value("${collection.banner.image.storage.root-container}") String rootContainer,
                             @Value("${collection.banner.image.storage.directory}") String directory) {
        this.httpCaller = httpCaller;
        this.swiftApiCallFactory = swiftApiCallFactory;
        this.collectionStorageRootContainer = rootContainer;
        this.collectionStorageDirectory = directory;
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

    public Integer saveCollection(CollectionSaveParameter collectionSaveParameter,
                                  MultipartFile desktopImageBannerFile,
                                  MultipartFile mobileImageBannerFile) throws IOException {
        uploadBannerImageFile(collectionSaveParameter.getDesktopImageBannerFilename(), desktopImageBannerFile);
        uploadBannerImageFile(collectionSaveParameter.getMobileImageBannerFilename(), mobileImageBannerFile);

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections";

        FashionGoApiResponse<SingleObject<Integer>> response = httpCaller.post(endpoint, getHeader(), collectionSaveParameter, new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<Integer>>>() {});

        return resolveResponse(response).getContent();
    }

    public CollectionResponse getCollection(int collectionId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/" + collectionId;

        FashionGoApiResponse<SingleObject<CollectionResponse>> response = httpCaller.get(endpoint, getHeader(), new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<CollectionResponse>>>() {});

        return resolveResponse(response).getContent();
    }

    public void setCollection(int collectionId, CollectionSaveParameter collectionSaveParameter,
                              MultipartFile desktopImageBannerFile, MultipartFile mobileImageBannerFile) throws IOException {
        if (collectionSaveParameter.getDesktopImageBannerFilename() != null || collectionSaveParameter.getMobileImageBannerFilename() != null) {
            CollectionResponse collection = getCollection(collectionId);

            changeBannerImage(collection.getDesktopImageBannerFilename(), collectionSaveParameter.getDesktopImageBannerFilename(), desktopImageBannerFile);
            changeBannerImage(collection.getMobileImageBannerFilename(), collectionSaveParameter.getMobileImageBannerFilename(), mobileImageBannerFile);
        }

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/" + collectionId;

        FashionGoApiResponse<Void> response = httpCaller.patch(endpoint, getHeader(), collectionSaveParameter, new ParameterizedTypeReference<FashionGoApiResponse<Void>>() {});

        resolveResponse(response);
    }

    public CollectionObject<CollectionListResponse> getCollections(int pageNum,
                                                                   int pageSize,
                                                                   String orderBy,
                                                                   String title,
                                                                   Integer type,
                                                                   Integer status,
                                                                   LocalDateTime fromDate,
                                                                   LocalDateTime toDate) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("pn", pageNum)
                .queryParam("ps", pageSize)
                .queryParam("orderby", orderBy);

        Optional.ofNullable(title)
                .ifPresent(t -> builder.queryParam("title", t));

        Optional.ofNullable(type)
                .ifPresent(t -> builder.queryParam("type", t));

        Optional.ofNullable(status)
                .ifPresent(s -> builder.queryParam("status", s));

        Optional.ofNullable(fromDate)
                .ifPresent(fd -> builder.queryParam("fromDate", fd));

        Optional.ofNullable(toDate)
                .ifPresent(td -> builder.queryParam("toDate", td));

        UriComponents uri = builder.build(false);

        FashionGoApiResponse<CollectionObject<CollectionListResponse>> response = httpCaller.get(uri.toUriString(), getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<CollectionListResponse>>>() {});

        return resolveResponse(response);
    }

    public CollectionObject<CollectionVendorResponse> searchVendors(List<String> query, String order) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/vendors";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("q", String.join(",", query));

        Optional.ofNullable(order)
                .ifPresent(o -> builder.queryParam("order", o));

        UriComponents uri = builder.build(false);

        FashionGoApiResponse<CollectionObject<CollectionVendorResponse>> response = httpCaller.get(uri.toUriString(), getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<CollectionVendorResponse>>>() {});

        return resolveResponse(response);
    }

    public void setStatus(CollectionBulkPatchParameter collectionBulkPatchParameter) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/bulk";

        FashionGoApiResponse<Void> response = httpCaller.patch(endpoint, getHeader(), collectionBulkPatchParameter, new ParameterizedTypeReference<FashionGoApiResponse<Void>>() {});

        resolveResponse(response);
    }

    public CollectionObject<CollectionProductResponse> searchProducts(String query, int start, int size) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/products";

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("query", query)
                .queryParam("start", start)
                .queryParam("size", size)
                .build()
                .encode();

        FashionGoApiResponse<CollectionObject<CollectionProductResponse>> response = httpCaller.get(uri.toUriString(), getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<CollectionProductResponse>>>() {});

        return resolveResponse(response);
    }

    public CollectionObject<CollectionProductResponse> getProducts(int collectionId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/"+ collectionId + "/products" ;

        FashionGoApiResponse<CollectionObject<CollectionProductResponse>> response = httpCaller.get(endpoint, getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<CollectionProductResponse>>>() {});

        return resolveResponse(response);
    }

    public CollectionProductQuickShopResponse getProductQuickShopInfo(int productId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/products/" + productId;

        FashionGoApiResponse<SingleObject<CollectionProductQuickShopResponse>> response = httpCaller.get(endpoint, getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<CollectionProductQuickShopResponse>>>() {});

        return resolveResponse(response).getContent();
    }

    private void uploadBannerImageFile(String filename, MultipartFile file) throws IOException {
        if (filename != null || file != null) {
            if (file == null) {
                throw new RuntimeException("file cannot be null when filename is not null");
            } else if (filename == null) {
                throw new RuntimeException("filename cannot be null when file is not null");
            } else {
                if (!isValidFileName(filename)) {
                    throw new RuntimeException("invalid filename");
                }

                CloseableHttpResponse uploadResponse = swiftApiCallFactory.create()
                        .files()
                        .upload(collectionStorageRootContainer, collectionStorageDirectory + "/" + filename, file.getInputStream(), true)
                        .executeWithoutHandler();
                HttpClientUtils.closeQuietly(uploadResponse);
            }
        }
    }

    private boolean isValidFileName(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return false;
        }
        Pattern validFileNamePattern = Pattern.compile("[a-zA-Z0-9\\-_.]+");
        Matcher matcher = validFileNamePattern.matcher(fileName);
        return matcher.matches();
    }

    private void changeBannerImage(String oldBannerImageFilename, String newBannerImageFilename, MultipartFile bannerImageFile) throws IOException {
        if (newBannerImageFilename == null) return;

        if (oldBannerImageFilename == null && bannerImageFile != null) { //  first file upload
            uploadBannerImageFile(newBannerImageFilename, bannerImageFile);
        } else if (oldBannerImageFilename != null && !oldBannerImageFilename.equals(newBannerImageFilename)) {    // old file exists
            deleteBannerImageFile(oldBannerImageFilename);  // old file delete
            if (bannerImageFile != null) {   // old file delete and upload new file
                uploadBannerImageFile(newBannerImageFilename, bannerImageFile);
            }
        }
    }

    private void deleteBannerImageFile(String filename) {
        if (filename == null) {
            throw new RuntimeException("filename cannot be null when file is not null");
        }

        CloseableHttpResponse deleteResponse = swiftApiCallFactory.create()
                .files()
                .delete(collectionStorageRootContainer, collectionStorageDirectory + "/" + filename)
                .executeWithoutHandler();
        HttpClientUtils.closeQuietly(deleteResponse);
    }
}
