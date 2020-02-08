package net.fashiongo.webadmin.service.sitemgmt.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.sitemgmt.SocialMedia;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.sitemgmt.SocialMediaNewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by jinwoo on 2020-01-14.
 */
@Service
@Slf4j
public class SocialMediaNewServiceImpl implements SocialMediaNewService {

    private final HttpClientWrapper httpCaller;

    public SocialMediaNewServiceImpl(HttpClientWrapper httpCaller) {
        this.httpCaller = httpCaller;
    }

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Boolean delete(List<Integer> delIds, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/misc/social-media/" + delIds;
        String responseBody = httpCaller.delete(endpoint, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
        return checkAndReturnOfResponseBody(responseBody);
    }

    private Boolean checkAndReturnOfResponseBody(String responseBody) {
        if (StringUtils.isEmpty(responseBody))
            return Boolean.FALSE;

        try {
            FashionGoApiResponse response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse>() {});
            return response.getHeader().isSuccessful();
        } catch (IOException e) {
            throw new RuntimeException("fail to operate a social media code.", e);
        }
    }

    @Override
    public Boolean register(SocialMedia socialMedia, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/misc/social-media";
        RegisterSocialMediaCommand command = RegisterSocialMediaCommand.create(socialMedia);
        String responseBody = httpCaller.post(endpoint, command, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
        return checkAndReturnOfResponseBody(responseBody);
    }

    @Override
    public Boolean update(SocialMedia socialMedia, Integer requestUserId, String requestUserName) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/misc/social-media/" + socialMedia.getSocialMediaId();
        ModifySocialMediaCommand command = ModifySocialMediaCommand.create(socialMedia);
        String responseBody = httpCaller.put(endpoint, command, FashionGoApiHeader.getHeader(requestUserId, requestUserName));
        return checkAndReturnOfResponseBody(responseBody);
    }

    @Getter
    private static class RegisterSocialMediaCommand {

        private Integer id;

        private String name;

        private String iconFileName;

        private Boolean active;

        private static RegisterSocialMediaCommand create(SocialMedia request) {

            RegisterSocialMediaCommand command = new RegisterSocialMediaCommand();
            command.id = request.getSocialMediaId();
            command.name = request.getSocialMedia();
            command.iconFileName = request.getIcon();
            command.active = true;

            return command;
        }
    }

    @Getter
    private static class ModifySocialMediaCommand {

        private Integer id;

        private String name;

        private String iconFileName;

        private Boolean active;

        private static ModifySocialMediaCommand create(SocialMedia request) {

            ModifySocialMediaCommand command = new ModifySocialMediaCommand();
            command.id = request.getSocialMediaId();
            command.name = request.getSocialMedia();
            command.iconFileName = request.getIcon();
            command.active = true;

            return command;
        }
    }
}
