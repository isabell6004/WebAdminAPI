/**
 * 
 */
package net.fashiongo.webadmin.service.sitemgmt;

import net.fashiongo.common.dal.JdbcHelper;
import net.fashiongo.webadmin.dao.primary.SocialMediaRepository;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.primary.SocialMedia;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author roy
 *
 */
@Service
public class SocialMediaService {

    private JdbcHelper jdbcHelper;

    private SocialMediaRepository socialMediaRepository;

    private SocialMediaNewService socialMediaNewService;

    public SocialMediaService(JdbcHelper jdbcHelper,
                              SocialMediaNewService socialMediaNewService,
                              SocialMediaRepository socialMediaRepository
    ) {
        this.socialMediaNewService = socialMediaNewService;
        this.socialMediaRepository = socialMediaRepository;
        this.jdbcHelper = jdbcHelper;
    }

	/**
	 * Get social media list
	 * @since Oct 23, 2018.
	 * @author roy
	 * @return List<SocialMedia>
	 */
	public List<SocialMedia> getSocialMedias() {
		return socialMediaRepository.findAllByOrderBySocialMediaIdAsc();
	}
	
	/**
	 * Delete social medias
	 * @since Oct 23, 2018.
	 * @author roy
	 * @param String socialMediaIds
	 * @return boolean
	 */
	public boolean deleteSocialMedias(String socialMediaIds) {

	    if(StringUtils.isEmpty(socialMediaIds)) {
	        return false;
        }

		String spName = "up_wa_DeleteSocialMedia";
		
		List<Object> params = new ArrayList<Object>();
        params.add(socialMediaIds);
        jdbcHelper.executeSP(spName, params);

        List<String> ids = Arrays.asList(socialMediaIds.split(","));
        socialMediaNewService.delete(ids.stream().map(Long::parseLong).collect(Collectors.toList()));
		
		return true;
	}
	
	/**
	 * Save social media
	 * @since Oct 25, 2018.
	 * @author roy
	 * @param SocialMedia
	 * @return ResultCode
	 */
	public Boolean saveSocialMedia(SocialMedia socialMedia) {
		try {
			socialMediaRepository.save(socialMedia);

			socialMediaNewService.registOrUpdate(socialMedia);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
