package net.fashiongo.webadmin.service.vendor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.service.vendor.response.CreateBannerResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by jinwoo on 2020-01-21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BannerRequestNewServiceTest {


    @Test
    public void get_response_and_check_response_model() {

        String responseBody = "{\n" +
                "\"header\" : {\n" +
                "\"resultCode\" : 0,\n" +
                "\"resultMessage\" : \"success\",\n" +
                "\"successful\" : true\n" +
                "},\n" +
                "\"data\" : {\n" +
                "\"content\" : 42398\n" +
                "}\n" +
                "}";
        try {
            ObjectMapper mapper = new ObjectMapper();
            FashionGoApiResponse<CreateBannerResponse> response = mapper.readValue(responseBody, new TypeReference<FashionGoApiResponse<CreateBannerResponse>>(){});
            if(response.getHeader().isSuccessful()) {
                CreateBannerResponse bannerResponse = response.getData();
                int bannerId = Optional.ofNullable(bannerResponse.getContent()).orElse(0);
                log.debug("bannerId : {}", bannerId);
                Assert.assertEquals(bannerId, 42398);
            } else {
                Assert.fail();
            }
        } catch (IOException e) {
            Assert.fail();
        }
    }


}
