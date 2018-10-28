/**
 * 
 */
package net.fashiongo.webadmin.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.primary.SocialMedia;

/**
 * @author roy
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SocialMediaServiceTest {
	
	@Autowired
	SocialMediaService socialMediaService;

	@Test
	@Ignore
	public void testGetSocialMedias() {
		List<SocialMedia> socialMedias = socialMediaService.getSocialMedias();
		
		assertTrue(socialMedias.size() > 0);
		
		SocialMedia socialMedia = socialMedias.get(0);
		
		assertNotNull(socialMedia);
		assertTrue(socialMedia.getSocialMediaId() > 0);
		assertTrue(socialMedia.getSocialMedia().length() > 0);
		assertTrue(socialMedia.getIcon().length() > 0);
	}

}
