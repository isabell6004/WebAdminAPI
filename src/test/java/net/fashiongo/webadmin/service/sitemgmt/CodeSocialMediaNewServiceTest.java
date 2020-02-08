package net.fashiongo.webadmin.service.sitemgmt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.sitemgmt.SocialMedia;
import net.fashiongo.webadmin.data.model.sitemgmt.SocialMediaParameter;
import net.fashiongo.webadmin.data.model.sitemgmt.SocialMediaType;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CodeSocialMediaNewServiceTest {
	
	@Autowired
    SocialMediaNewService socialMediaNewService;

	@Test
	public void call_fashionGo_new_api_calling() {

	    // this test is integrated test using the real data.
        SocialMediaParameter parameter = new SocialMediaParameter();
        parameter.setSocialMediaId(15);
        parameter.setSocialMedia("Facebook");
        parameter.setIcon("fb1.png");

        try {
            Integer userId = 57;
            String userName = "developer";

            SocialMedia socialMedia = SocialMedia.create(parameter);
            log.debug("request : {}", socialMedia);
            socialMediaNewService.register(socialMedia, userId, userName);
        } catch (Exception e) {
            Assert.fail();
        }

	}

	@Autowired
    private HttpClientWrapper wrapper;

	@Test
	public void call_fashionGo_api() {

        String endpoint = "http://10.210.16.205:27301/v1.0/common/socialmedia";
        Map<String, String> headerMap = new HashMap<>();
        String payload = "{\"name\":\"Facebook\",\"iconFileName\":\"fb1.png\", \"active\":true}";
        wrapper.post(endpoint, payload, headerMap);
    }

    @Test
    public void type_test_null() {
	    String typeString = "MeToday";
	    try {
            SocialMediaType type = SocialMediaType.valueOf(typeString);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void check_response() {
        ObjectMapper mapper = new ObjectMapper();
	    String responseBody = "{\"header\":{\"resultCode\":0,\"resultMessage\":\"success\",\"isSuccessful\":true},\"data\":null}";
        try {
            FashionGoApiResponse response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse>() {});
            log.debug("{}", response.getHeader().isSuccessful());
        } catch (IOException e) {
            throw new RuntimeException("fail to operate a social media code.", e);
        }
    }
}
