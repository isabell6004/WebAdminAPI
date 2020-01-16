package net.fashiongo.webadmin.service.sitemgmt;

import net.fashiongo.common.dal.JdbcHelper;
import net.fashiongo.webadmin.data.entity.primary.sitemgmt.SocialMedia;
import net.fashiongo.webadmin.data.model.sitemgmt.SocialMediaParameter;
import net.fashiongo.webadmin.data.repository.primary.sitemgmt.SocialMediaRepository;
import net.fashiongo.webadmin.exception.NotFoundSitemgmtException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<SocialMedia> getSocialMedias() {
		return socialMediaRepository.findAllByOrderBySocialMediaIdAsc();
	}
	
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
	
	public Boolean saveSocialMedia(SocialMediaParameter request) {
		try {
		    if(request.getSocialMediaId() == null || request.getSocialMediaId() == 0) {
		        SocialMedia socialMedia = SocialMedia.create(request);
                socialMediaRepository.save(socialMedia);

                socialMediaNewService.regist(socialMedia);
            } else {
                SocialMedia socialMedia = socialMediaRepository.findById(request.getSocialMediaId()).orElseThrow(NotFoundSitemgmtException::new);
                socialMedia.update(request);
                socialMediaRepository.save(socialMedia);

                socialMediaNewService.update(socialMedia);
            }
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
