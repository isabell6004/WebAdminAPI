package net.fashiongo.webadmin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.push.PushSendParameter;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class PushServiceImpl implements PushService {

    private final HttpClientWrapper httpCaller;
    private final ObjectMapper mapper;

    public PushServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
        mapper = new ObjectMapper();
    }

    @Override
    public void sendPushNotification(PushSendParameter parameter) {
        WebAdminLoginUser userInfo = Utility.getUserInfo();
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/push/messages";

        String responseBody = httpCaller.post(endpoint, parameter, FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername()));

        try {
            FashionGoApiResponse response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse>(){});
            if(response.getHeader().isSuccessful()) {
                return;
            } else {
                throw new RuntimeException("fail to inert a new image in new fashiongo api");
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to inert a new image in new fashiongo api");
        }
    }
}
