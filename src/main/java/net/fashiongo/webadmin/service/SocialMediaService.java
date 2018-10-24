/**
 * 
 */
package net.fashiongo.webadmin.service;

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
public class SocialMediaService {
	
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
	
}
