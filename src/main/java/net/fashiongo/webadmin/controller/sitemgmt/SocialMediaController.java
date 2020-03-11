package net.fashiongo.webadmin.controller.sitemgmt;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.sitemgmt.DelSocialMediaParameter;
import net.fashiongo.webadmin.data.model.sitemgmt.SocialMediaParameter;
import net.fashiongo.webadmin.data.entity.primary.sitemgmt.SocialMedia;
import net.fashiongo.webadmin.service.sitemgmt.SocialMediaService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by jinwoo on 2020-01-14.
 */
@Api(tags = { "sitemgmt" })
@RestController
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class SocialMediaController {

    private SocialMediaService socialMediaService;

    public SocialMediaController(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    /**
     * Get social media list
     * @since Oct 23, 2018.
     * @author roy
     * @return JsonResponse<List<SocialMedia>>
     */
    @RequestMapping(value = "sitemgmt/getsocialmedialist", method = RequestMethod.GET, produces = "application/json")
    public JsonResponse<List<SocialMedia>> getSocialMediaList() {
        List<SocialMedia> socialMediaList = socialMediaService.getSocialMedias();
        return new JsonResponse<>(true, null, socialMediaList);
    }

    /**
     * Delete social media
     * @since Oct 25, 2018.
     * @author roy
     * @param DelSocialMediaParameter
     * @return JsonResponse<String>
     */
    @RequestMapping(value = "sitemgmt/delsocialmedia", method = RequestMethod.POST, produces = "application/json")
    public JsonResponse<String> deleteSocialMedias(@RequestBody DelSocialMediaParameter delSocialMediaParameter) {
        boolean result = socialMediaService.deleteSocialMedias(delSocialMediaParameter.getSocialMediaIds());
        return new JsonResponse<>(result, null, "");
    }

    /**
     * Save social media
     * @since Oct 26, 2018.
     * @author roy
     * @param SocialMedia
     * @return JsonResponse<String>
     */
    @RequestMapping(value = "sitemgmt/setsocialmedialist", method = RequestMethod.POST, produces = "application/json")
    public JsonResponse<String> saveSocialMedia(@RequestBody SocialMediaParameter socialMedia) {
        if(socialMediaService.saveSocialMedia(socialMedia)) {
            return new JsonResponse<>(true, "Saved successfully!", 1, "");
        } else {
            return new JsonResponse<>(false, "failure", -1, "");
        }
    }
}
