package net.fashiongo.webadmin.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.display.DisplaySettingRequest;
import net.fashiongo.webadmin.data.model.display.response.*;
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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class DisplayServiceImpl implements DisplayService {

    private final HttpClientWrapper httpCaller;

    private final SwiftApiCallFactory swiftApiCallFactory;

    private final String displayStorageRootContainer;

    private final String displayStorageDirectory;

    private final ObjectMapper mapper = new ObjectMapper();

    public DisplayServiceImpl(HttpClientWrapper httpCaller,
                              @Qualifier("displayBannerSwiftApiCallFactory") SwiftApiCallFactory swiftApiCallFactory,
                              @Value("${display.banner.image.storage.root-container}") String rootContainer,
                              @Value("${display.banner.image.storage.directory}") String directory ) {
        this.httpCaller = httpCaller;
        this.swiftApiCallFactory = swiftApiCallFactory;
        this.displayStorageRootContainer = rootContainer;
        this.displayStorageDirectory = directory;
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

    @Override
    public CollectionObject<DisplayLocationResponse> getDisplayLocations() {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/display/location";

        FashionGoApiResponse<CollectionObject<DisplayLocationResponse>> response = httpCaller.get(endpoint, getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<DisplayLocationResponse>>>() {});

        return resolveResponse(response);
    }

    @Override
    public CollectionObject<DisplayCollectionResponse> getDisplayCollections() {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/display/setting/collection";

        FashionGoApiResponse<CollectionObject<DisplayCollectionResponse>> response = httpCaller.get(endpoint, getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<DisplayCollectionResponse>>>() {});

        return resolveResponse(response);
    }

    @Override
    public CollectionObject<DisplayCalendarResponse> getDisplayCalendar(LocalDateTime startDate, LocalDateTime endDate) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/display/calendar";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .build(false);

        FashionGoApiResponse<CollectionObject<DisplayCalendarResponse>> response = httpCaller.get(builder.toUriString(), getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<DisplayCalendarResponse>>>() {});

        return resolveResponse(response);
    }

    @Override
    public SingleObject<DisplaySettingResponse> getDisplaySetting(int displaySettingId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/display/setting/" + displaySettingId;

        FashionGoApiResponse<SingleObject<DisplaySettingResponse>> response = httpCaller.get(endpoint, getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<DisplaySettingResponse>>>() {});

        return resolveResponse(response);
    }

    @Override
    public SingleObject<Integer> createDisplaySetting(DisplaySettingRequest displaySettingRequest, MultipartFile imageLinkFile) throws IOException {

        if (displaySettingRequest.linkFileName() != null && displaySettingRequest.linkType() == 3) {
            uploadBannerImageFile(displaySettingRequest.linkFileName(), imageLinkFile);
        }

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/display/setting";
        FashionGoApiResponse<SingleObject<Integer>> response = httpCaller.post(endpoint, getHeader(), displaySettingRequest,new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<Integer>>>() {});
        return resolveResponse(response);
    }

    @Override
    public void updateDisplaySetting(int displaySettingId, DisplaySettingRequest displaySettingRequest, MultipartFile imageLinkFile) throws IOException {

        if (displaySettingRequest.linkFileName() != null && displaySettingRequest.linkType() == 3) {
            uploadBannerImageFile(displaySettingRequest.linkFileName(), imageLinkFile);
        }

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/display/setting/" + displaySettingId;
        String responseBody =  httpCaller.put(endpoint, displaySettingRequest, getHeader());
        try {
            FashionGoApiResponse<Void> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Void>>() {});
            resolveResponse(response);

        } catch (IOException e) {
            throw new RuntimeException("fail to update display setting. " + e.getMessage());
        }

    }

    @Override
    public void deleteDisplaySetting(int displaySettingId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/display/setting/" + displaySettingId;
        String responseBody = httpCaller.delete(endpoint, getHeader());
        try {
            FashionGoApiResponse<Void> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<Void>>() {});
            resolveResponse(response);

        } catch (IOException e) {
            throw new RuntimeException("fail to delete display setting. " + e.getMessage());
        }
    }

    public CollectionObject<DisplaySettingListResponse> getDisplaySettingList(int pageNum,
                                                                              int pageSize,
                                                                              Integer deviceType,
                                                                              Integer pageId,
                                                                              Integer displayLocationId,
                                                                              Integer linkType,
                                                                              Integer dateType,
                                                                              LocalDateTime fromDate,
                                                                              LocalDateTime toDate,
                                                                              Integer displaySettingStatus,
                                                                              String title,
                                                                              Integer linkCollectionId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/display/setting";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("pn", pageNum)
                .queryParam("ps", pageSize);

        Optional.ofNullable(deviceType)
                .ifPresent(t -> builder.queryParam("deviceType", t));
        Optional.ofNullable(pageId)
                .ifPresent(t -> builder.queryParam("pageId", t));
        Optional.ofNullable(displayLocationId)
                .ifPresent(t -> builder.queryParam("displayLocationId", t));
        Optional.ofNullable(linkType)
                .ifPresent(t -> builder.queryParam("linkType", t));
        Optional.ofNullable(dateType)
                .ifPresent(t -> builder.queryParam("dateType", t));
        Optional.ofNullable(fromDate)
                .ifPresent(t -> builder.queryParam("fromDate", t));
        Optional.ofNullable(toDate)
                .ifPresent(t -> builder.queryParam("toDate", t));
        Optional.ofNullable(displaySettingStatus)
                .ifPresent(t -> builder.queryParam("displaySettingStatus", t));
        Optional.ofNullable(title)
                .ifPresent(t -> builder.queryParam("title", t));
        Optional.ofNullable(linkCollectionId)
                .ifPresent(t -> builder.queryParam("linkCollectionId", t));

        UriComponents uri = builder.build(false);

        FashionGoApiResponse<CollectionObject<DisplaySettingListResponse>> response = httpCaller.get(uri.toUriString(), getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<DisplaySettingListResponse>>>() {});

        return resolveResponse(response);
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
                        .upload(displayStorageRootContainer, displayStorageDirectory + "/" + filename, file.getInputStream(), true)
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
