package net.fashiongo.webadmin.service.externalutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jinwoo on 2019-12-13.
 */
@Slf4j
public class FashionGoApiHeader {

    private final static String ApplicationType = "WebAdmin";

    public static Map<String, String> getHeader(Integer userId, String userName) {

        Map<String, String> header = new HashMap<>();
        header.put("Connection", "close");
        header.put("Content-Type", "application/json; charset=utf-8");
        header.put("Referer-Application-Type", ApplicationType);
        header.put("Request-Id", UUID.randomUUID().toString());

        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("userId", userId);
            userInfo.put("username", userName);
            String jsonUserInfo = mapper.writeValueAsString(userInfo);

            header.put("User-Info", jsonUserInfo);
        } catch (JsonProcessingException e) {
            log.warn("fail to generate the user info. {}, {}", userId, userName);
        }
        return header;
    }
}
