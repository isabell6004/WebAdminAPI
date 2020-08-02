package net.fashiongo.webadmin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.collection.CollectionBulkPatchParameter;
import net.fashiongo.webadmin.data.model.collection.CollectionSaveParameter;
import net.fashiongo.webadmin.data.model.collection.response.*;
import net.fashiongo.webadmin.model.ads.ApiResponse;
import net.fashiongo.webadmin.model.ads.request.FetchTotalAmountRequest;
import net.fashiongo.webadmin.model.ads.response.FetchTotalAmountResponse;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
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
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CollectionService {

    private final HttpClientWrapper httpCaller;

    private final SwiftApiCallFactory swiftApiCallFactory;

    private final RestTemplate restTemplate;

    private final String collectionStorageRootContainer;

    private final String collectionStorageDirectory;

    private final String adApiEndpoint;

    private static final String CHANNEL_VENDOR_API = "webAdmin";

    private static final String HEADER_NAME_CHANNEL = "FG-Channel";

    private static final String HEADER_NAME_USER_ID = "FG-User-Id";

    private static final String HEADER_NAME_USER_NAME = "FG-User-Name";

    private final ObjectMapper objectMapper;

    public CollectionService(HttpClientWrapper httpCaller,
                             @Qualifier("adApiRestTemplate") RestTemplate restTemplate,
                             @Qualifier("collectionBannerSwiftApiCallFactory") SwiftApiCallFactory swiftApiCallFactory,
                             @Value("${collection.banner.image.storage.root-container}") String rootContainer,
                             @Value("${collection.banner.image.storage.directory}") String directory,
                             @Value("${webAdminapi.adapi.endpoint}") String adApiEndpoint) {
        this.httpCaller = httpCaller;
        this.restTemplate = restTemplate;
        this.swiftApiCallFactory = swiftApiCallFactory;
        this.collectionStorageRootContainer = rootContainer;
        this.collectionStorageDirectory = directory;
        this.adApiEndpoint = adApiEndpoint;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    private Map<String, String> getFashionGoApiHeader() {
        WebAdminLoginUser userInfo = Utility.getUserInfo();
        return FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername());
    }

    private HttpHeaders getAdApiHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_NAME_CHANNEL, CHANNEL_VENDOR_API);
        httpHeaders.add(HEADER_NAME_USER_ID, Utility.getUserInfo().getUserId().toString());
        httpHeaders.add(HEADER_NAME_USER_NAME, Utility.getUsername());
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        return httpHeaders;
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

        FashionGoApiResponse<SingleObject<Integer>> response = httpCaller.post(endpoint, getFashionGoApiHeader(), collectionSaveParameter, new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<Integer>>>() {});

        return resolveResponse(response).getContent();
    }

    public CollectionResponse getCollection(int collectionId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/" + collectionId;

        FashionGoApiResponse<SingleObject<CollectionResponse>> response = httpCaller.get(endpoint, getFashionGoApiHeader(), new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<CollectionResponse>>>() {});

        return resolveResponse(response).getContent();
    }

    public CollectionPatchResponse setCollection(int collectionId, CollectionSaveParameter collectionSaveParameter,
                              MultipartFile desktopImageBannerFile, MultipartFile mobileImageBannerFile) throws IOException {
        if (collectionSaveParameter.getDesktopImageBannerFilename() != null || collectionSaveParameter.getMobileImageBannerFilename() != null) {
            uploadBannerImageFile(collectionSaveParameter.getDesktopImageBannerFilename(), desktopImageBannerFile);
            uploadBannerImageFile(collectionSaveParameter.getMobileImageBannerFilename(), mobileImageBannerFile);
        }

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/" + collectionId;

        FashionGoApiResponse<SingleObject<CollectionPatchResponse>> response = httpCaller.patch(endpoint, getFashionGoApiHeader(), collectionSaveParameter, new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<CollectionPatchResponse>>>() {});

        return resolveResponse(response).getContent();
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

        FashionGoApiResponse<CollectionObject<CollectionListResponse>> response = httpCaller.get(uri.toUriString(), getFashionGoApiHeader(),
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

        FashionGoApiResponse<CollectionObject<CollectionVendorResponse>> response = httpCaller.get(uri.toUriString(), getFashionGoApiHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<CollectionVendorResponse>>>() {});

        CollectionObject<CollectionVendorResponse> vendorLists = resolveResponse(response);
        List<Integer> vendorIds = vendorLists.getContents().stream()
                .map(CollectionVendorResponse::getVendorId)
                .distinct()
                .collect(Collectors.toList());

        int maxRequestIdSize = 30;
        int requestDay = 30;
        LocalDate now = LocalDate.now();
        Map<Integer, BigDecimal> adAmountMap = new HashMap<>(vendorIds.size());
        for (int cursor = 0; cursor < vendorIds.size(); cursor += maxRequestIdSize) {
            List<Integer> requestVendorIds;
            if (cursor + maxRequestIdSize <= vendorIds.size()) {
                requestVendorIds = vendorIds.subList(cursor, cursor + maxRequestIdSize);
            } else {
                requestVendorIds = vendorIds.subList(cursor, vendorIds.size());
            }

            adAmountMap.putAll(getAdAmountFromAdApi(requestVendorIds, now.minusDays(requestDay), now));
        }
        vendorLists.getContents()
                .forEach(vendor -> Optional.ofNullable(adAmountMap.get(vendor.getVendorId()))
                        .ifPresent(vendor::setAdAmt));

        return vendorLists;
    }

    private Map<Integer, BigDecimal> getAdAmountFromAdApi(List<Integer> vendorIds, LocalDate beginIssuedOn, LocalDate endIssuedOn) {
        HttpHeaders header = getAdApiHeader();
        FetchTotalAmountRequest request = FetchTotalAmountRequest.of(vendorIds, beginIssuedOn, endIssuedOn);

        try {
            HttpEntity<String> httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(request), header);
            ResponseEntity<ApiResponse<ApiResponse.Contents<FetchTotalAmountResponse>>> response = restTemplate.exchange(adApiEndpoint + "/v2/receipts/fetch-total-amount", HttpMethod.POST, httpEntity, new ParameterizedTypeReference<ApiResponse<ApiResponse.Contents<FetchTotalAmountResponse>>>() {});

            if (!HttpStatus.OK.equals(response.getStatusCode())) {
                log.error("ad api response with a status code which is not 200.");
                return Collections.emptyMap();
            }

            if (!response.getBody().getHeader().isSuccess()) {
                log.error("ad api response with a header whose isSuccessful is false.");
                return Collections.emptyMap();
            }

            return response.getBody()
                    .getData()
                    .getContents().stream()
                    .collect(Collectors.toMap(FetchTotalAmountResponse::getVendorId, FetchTotalAmountResponse::getPurchaseAmount));
        } catch (Exception e) {
            throw new RuntimeException("exception occurred while calling ad api. ", e);
        }
    }


    public CollectionObject<CollectionVendorLatestPromotionResponse> getVendorLatestPromotion(List<Integer> vendorIds) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/vendors/latest-promotion";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("ids", vendorIds.stream().map(Object::toString).collect(Collectors.joining(",")))
                .build(false);

        FashionGoApiResponse<CollectionObject<CollectionVendorLatestPromotionResponse>> response = httpCaller.get(builder.toUriString(), getFashionGoApiHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<CollectionVendorLatestPromotionResponse>>>() {});

        return resolveResponse(response);
    }

    public void setStatus(CollectionBulkPatchParameter collectionBulkPatchParameter) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/bulk";

        FashionGoApiResponse<Void> response = httpCaller.patch(endpoint, getFashionGoApiHeader(), collectionBulkPatchParameter, new ParameterizedTypeReference<FashionGoApiResponse<Void>>() {});

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

        FashionGoApiResponse<CollectionObject<CollectionProductResponse>> response = httpCaller.get(uri.toUriString(), getFashionGoApiHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<CollectionProductResponse>>>() {});

        return resolveResponse(response);
    }

    public CollectionObject<CollectionProductResponse> getProducts(int collectionId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/" + collectionId + "/products";

        FashionGoApiResponse<CollectionObject<CollectionProductResponse>> response = httpCaller.get(endpoint, getFashionGoApiHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<CollectionProductResponse>>>() {});

        return resolveResponse(response);
    }

    public CollectionProductQuickShopResponse getProductQuickShopInfo(int productId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/collections/products/" + productId;

        FashionGoApiResponse<SingleObject<CollectionProductQuickShopResponse>> response = httpCaller.get(endpoint, getFashionGoApiHeader(),
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
}
