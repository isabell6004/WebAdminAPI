/**
 * 
 */
package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.SocialMediaRepository;
import net.fashiongo.webadmin.model.primary.SocialMedia;

/**
 * @author roy
 *
 */
@Service
public class SocialMediaService extends ApiService {
	
	@Autowired
	private SocialMediaRepository socialMediaRepository;
	
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
		String spName = "up_wa_DeleteSocialMedia";
		
		List<Object> params = new ArrayList<Object>();
		
        params.add(socialMediaIds);
        
        jdbcHelper.executeSP(spName, params);
		
		return true;
	}
	
}
