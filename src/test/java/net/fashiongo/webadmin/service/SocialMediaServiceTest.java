/**
 * 
 */
package net.fashiongo.webadmin.service;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.fashiongo.webadmin.model.pojo.ResultCode;
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
	public void testGetSocialMedias() {
		List<SocialMedia> socialMedias = socialMediaService.getSocialMedias();
		
		assertTrue("Social media count should be largger than 0.", socialMedias.size() > 0);
		
		SocialMedia socialMedia = socialMedias.get(0);
		
		assertNotNull("First socialmedia should be not null.", socialMedia);
		assertTrue("SocialMediaID should be not largger than 0.", socialMedia.getSocialMediaId() > 0);
		assertFalse("SocialMedia should be not empty.", StringUtils.isEmpty(socialMedia.getSocialMedia()));
	}
	
	@Ignore
	@Test
	public void testSaveAndDeleteSocialMedia() {
		SocialMedia socialMedia = new SocialMedia();
		socialMedia.setSocialMedia("TestSNS");
		
		ResultCode resultCode = socialMediaService.saveSocialMedia(socialMedia);
		
		assertTrue("success should be true.", resultCode.getSuccess());
		assertTrue("resultCode should be 1.", resultCode.getResultCode().equals(1));
		
		socialMedia.setSocialMedia(null);
		
		resultCode = socialMediaService.saveSocialMedia(socialMedia);
		
		assertFalse("success should be false.", resultCode.getSuccess());
		assertTrue("resultCode should be -1.", resultCode.getResultCode().equals(-1));
		
		Integer socialMediaCount = socialMediaService.getSocialMedias().size();
		
		socialMediaService.deleteSocialMedias(socialMedia.getSocialMediaId().toString());
		Integer socialMediaCountAfterDelete = socialMediaService.getSocialMedias().size();
		
		assertEquals("Number of social medias after deletion 1 row should be 1 less than before deletion.", socialMediaCount - socialMediaCountAfterDelete, 1);
	}

}
