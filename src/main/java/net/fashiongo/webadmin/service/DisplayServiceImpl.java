package net.fashiongo.webadmin.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.display.response.DisplayLocationResponse;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DisplayServiceImpl implements DisplayService {

    private HttpClientWrapper httpCaller;
    private final ObjectMapper mapper = new ObjectMapper();

    public DisplayServiceImpl(HttpClientWrapper httpCaller) {
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

    @Override
    public CollectionObject<DisplayLocationResponse> getDisplayLocation() {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/display/location";

        FashionGoApiResponse<CollectionObject<DisplayLocationResponse>> response = httpCaller.get(endpoint, getHeader(),
                new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<DisplayLocationResponse>>>() {});

        return resolveResponse(response);
    }
	
}
