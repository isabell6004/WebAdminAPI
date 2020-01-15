package net.fashiongo.webadmin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.utility.Utility;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jinwoo on 2019-12-13.
 */
@Slf4j
public class FashionGoApiHeader {

    private final static String ServiceType = "FG";
    private final static String ApplicationType = "WebAdmin";

    public static Map<String, String> getHeader() {

        Map<String, String> header = new HashMap<>();
        header.put("Referer-Service-Type", ServiceType);
        header.put("Referer-Application-Type", ApplicationType);
        header.put("Request-Id", UUID.randomUUID().toString());

        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("userId", String.valueOf(Utility.getUserInfo().getUserId()));
            userInfo.put("username", Utility.getUserInfo().getUsername());
            String jsonUserInfo = mapper.writeValueAsString(userInfo);

            header.put("User-Info", jsonUserInfo);
        } catch (JsonProcessingException e) {
            log.warn("fail to generate the user info. {}, {}", Utility.getUserInfo().getUserId(), Utility.getUserInfo().getUsername());
        }
        return header;
    }
}
