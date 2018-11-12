/**
 * 
 */
package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.SocialMediaRepository;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
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
	
	/**
	 * Save social media
	 * @since Oct 25, 2018.
	 * @author roy
	 * @param SocialMedia
	 * @return ResultCode
	 */
	public ResultCode saveSocialMedia(SocialMedia socialMedia) {
		ResultCode result = new ResultCode(true, 1, MSG_SAVE_SUCCESS);
		
		try {
			socialMediaRepository.save(socialMedia);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setResultCode(-1);
			result.setResultMsg("failure");
		}
		
		return result;
	}
	
}
