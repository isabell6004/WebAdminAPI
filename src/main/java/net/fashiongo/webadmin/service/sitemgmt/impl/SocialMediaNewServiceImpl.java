package net.fashiongo.webadmin.service.sitemgmt.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.sitemgmt.SocialMedia;
import net.fashiongo.webadmin.service.FashionGoApiConfig;
import net.fashiongo.webadmin.service.FashionGoApiHeader;
import net.fashiongo.webadmin.service.HttpClientWrapper;
import net.fashiongo.webadmin.service.sitemgmt.SocialMediaNewService;
import org.springframework.stereotype.Service;

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

    @Override
    public void delete(List<Long> delIds) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/common/socialmedia/delete";
        DelSocialMediaCommand command = DelSocialMediaCommand.create(delIds);
        httpCaller.post(endpoint, command, FashionGoApiHeader.getHeader());
    }

    @Override
    public void regist(SocialMedia socialMedia) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/common/socialmedia";
        RegistSocialMediaCommand command = RegistSocialMediaCommand.create(socialMedia);
        httpCaller.post(endpoint, command, FashionGoApiHeader.getHeader());
    }

    @Override
    public void update(SocialMedia socialMedia) {
        final String endpoint = FashionGoApiConfig.fashionGoApi + "/v1.0/common/socialmedia";
        ModifySocialMediaCommand command = ModifySocialMediaCommand.create(socialMedia);
        httpCaller.put(endpoint, command, FashionGoApiHeader.getHeader());
    }

    @Getter
    private static class DelSocialMediaCommand {
        private List<Long> delIds;

        private static DelSocialMediaCommand create(List<Long> delIds) {
            DelSocialMediaCommand command = new DelSocialMediaCommand();
            command.delIds = delIds;
            return command;
        }
    }

    @Getter
    private static class RegistSocialMediaCommand {

        private Integer id;

        private String name;

        private String iconFileName;

        private Boolean active;

        private static RegistSocialMediaCommand create(SocialMedia request) {

            RegistSocialMediaCommand command = new RegistSocialMediaCommand();
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