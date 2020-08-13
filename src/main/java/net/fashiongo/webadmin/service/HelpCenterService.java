package net.fashiongo.webadmin.service;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.helpcenter.BoardBulkSaveParameter;
import net.fashiongo.webadmin.data.model.helpcenter.BoardCategoryParameter;
import net.fashiongo.webadmin.data.model.helpcenter.BoardFaqBulkSaveParameter;
import net.fashiongo.webadmin.data.model.helpcenter.BoardTopicParameter;
import net.fashiongo.webadmin.data.model.helpcenter.response.*;
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

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class HelpCenterService {

    private final HttpClientWrapper httpCaller;

    private final SwiftApiCallFactory swiftApiCallFactory;

    private final String boardStorageRootContainer;

    private final String boardStorageDirectory;

    public HelpCenterService(HttpClientWrapper httpCaller,
                             @Qualifier("boardIconSwiftApiCallFactory") SwiftApiCallFactory swiftApiCallFactory,
                             @Value("${board.icon.image.storage.root-container}") String rootContainer,
                             @Value("${board.icon.image.storage.directory}") String directory) {
        this.httpCaller = httpCaller;
        this.swiftApiCallFactory = swiftApiCallFactory;
        this.boardStorageRootContainer = rootContainer;
        this.boardStorageDirectory = directory;
    }

    public List<CategoryGroupsResponse> getCategoryGroups() {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/category-groups";

        FashionGoApiResponse<CollectionObject<CategoryGroupsResponse>> response = httpCaller.get(endpoint, getHeader(), new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<CategoryGroupsResponse>>>() {});

        return resolveResponse(response).getContents();
    }

    public List<CategoryAndTopicResponse> getCategoriesAndTopics(int categoryGroupId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/category-groups/" + categoryGroupId + "/list";

        FashionGoApiResponse<CollectionObject<CategoryAndTopicResponse>> response = httpCaller.get(endpoint, getHeader(), new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<CategoryAndTopicResponse>>>() {});

        return resolveResponse(response).getContents();
    }

    public CategoryResponse getCategory(int categoryId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/categories/" + categoryId;

        FashionGoApiResponse<SingleObject<CategoryResponse>> response = httpCaller.get(endpoint, getHeader(), new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<CategoryResponse>>>() {});

        return resolveResponse(response).getContent();
    }

    public List<FaqsResponse> getFaqs(int categoryGroupId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/category-groups/" + categoryGroupId + "/faqs";

        FashionGoApiResponse<CollectionObject<FaqsResponse>> response = httpCaller.get(endpoint, getHeader(), new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<FaqsResponse>>>() {});

        return resolveResponse(response).getContents();
    }

    public TopicResponse getTopic(int topicId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/topics/" + topicId;

        FashionGoApiResponse<SingleObject<TopicResponse>> response = httpCaller.get(endpoint, getHeader(), new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<TopicResponse>>>() {});

        return resolveResponse(response).getContent();
    }

    public Integer saveCategory(BoardCategoryParameter boardCategoryParameter,
                                MultipartFile colorIconImageFile,
                                MultipartFile greyIconImageFile) throws IOException {
        uploadImageFile(boardCategoryParameter.getColorIconImageFilename(), colorIconImageFile);
        uploadImageFile(boardCategoryParameter.getGreyIconImageFilename(), greyIconImageFile);

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/categories";

        FashionGoApiResponse<SingleObject<Integer>> response = httpCaller.post(endpoint, getHeader(), boardCategoryParameter, new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<Integer>>>() {});

        return resolveResponse(response).getContent();
    }

    public void setCategory(int categoryId,
                            BoardCategoryParameter boardCategoryParameter,
                            MultipartFile colorIconImageFile,
                            MultipartFile greyIconImageFile) throws IOException {
        uploadImageFile(boardCategoryParameter.getColorIconImageFilename(), colorIconImageFile);
        uploadImageFile(boardCategoryParameter.getGreyIconImageFilename(), greyIconImageFile);

        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/categories/" + categoryId;

        FashionGoApiResponse<Void> response = httpCaller.patch(endpoint, getHeader(), boardCategoryParameter, new ParameterizedTypeReference<FashionGoApiResponse<Void>>() {});

        resolveResponse(response);
    }

    public Integer saveTopic(BoardTopicParameter boardTopicParameter) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/topics";

        FashionGoApiResponse<SingleObject<Integer>> response = httpCaller.post(endpoint, getHeader(), boardTopicParameter, new ParameterizedTypeReference<FashionGoApiResponse<SingleObject<Integer>>>() {});

        return resolveResponse(response).getContent();
    }

    public void setTopic(int topicId, BoardTopicParameter boardTopicParameter) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/topics/" + topicId;

        FashionGoApiResponse<Void> response = httpCaller.patch(endpoint, getHeader(), boardTopicParameter, new ParameterizedTypeReference<FashionGoApiResponse<Void>>() {});

        resolveResponse(response);
    }

    public List<FeedbacksResponse> getFeedbacks(int topicId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/topics/" + topicId + "/feedback";

        FashionGoApiResponse<CollectionObject<FeedbacksResponse>> response = httpCaller.get(endpoint, getHeader(), new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<FeedbacksResponse>>>() {});

        return resolveResponse(response).getContents();
    }

    public List<OtherReasonsResponse> getOtherReasons(int topicId) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/topics/" + topicId + "/feedback/reason";

        FashionGoApiResponse<CollectionObject<OtherReasonsResponse>> response = httpCaller.get(endpoint, getHeader(), new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<OtherReasonsResponse>>>() {});

        return resolveResponse(response).getContents();
    }

    public void saveFaqOrder(List<BoardFaqBulkSaveParameter> boardFaqBulkSaveParameterList) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/faqs";

        FashionGoApiResponse<Void> response = httpCaller.patch(endpoint, getHeader(), boardFaqBulkSaveParameterList, new ParameterizedTypeReference<FashionGoApiResponse<Void>>() {});

        resolveResponse(response);
    }

    public void saveCategoriesAndTopicsOrder(BoardBulkSaveParameter boardBulkSaveParameter) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/board/bulk";

        FashionGoApiResponse<Void> response = httpCaller.patch(endpoint, getHeader(), boardBulkSaveParameter, new ParameterizedTypeReference<FashionGoApiResponse<Void>>() {});

        resolveResponse(response);
    }

    private Map<String, String> getHeader() {
        WebAdminLoginUser userInfo = Utility.getUserInfo();
        return FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername());
    }

    private <T> T resolveResponse(FashionGoApiResponse<T> response) {
        if (response == null) {
            throw new RuntimeException("unknown exception occurred while calling fashiongo api. (probably occurred during json serialization process.)");
        }

        if (response.getHeader().isSuccessful()) {
            return response.getData();
        } else {
            throw new RuntimeException("fail to call fashiongo api: " + response.getHeader().getResultMessage());
        }
    }

    public void uploadImageFile(String filename, MultipartFile file) throws IOException {
        if (filename != null || file != null) {
            if (filename == null) {
                throw new RuntimeException("filename cannot be null when file is not null");
            } else if (file == null) {
                throw new RuntimeException("file cannot be null when filename is not null");
            } else {
                if (!isValidFileName(filename)) {
                    throw new RuntimeException("invalid filename");
                }

                CloseableHttpResponse uploadResponse = swiftApiCallFactory.create()
                        .files()
                        .upload(boardStorageRootContainer, boardStorageDirectory + "/" + filename, file.getInputStream(), true)
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
