package net.fashiongo.webadmin.service.ads;

import net.fashiongo.webadmin.model.ads.response.VerifyVendorImageResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin Kwon
 * 2020-01-31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdVendorImageServiceTest {

    private final List<Integer> vendorIds = Arrays.asList(5515, 2034);
    private final List<Integer> vendorImageTypes = Arrays.asList(6, 7);

    @Autowired
    private AdVendorImageService adVendorImageService;

    @Test(expected = IllegalArgumentException.class)
    public void throws_error_when_vendors_are_null() {
        adVendorImageService.getVerifyVendorImages(null, vendorImageTypes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throws_error_when_vendorImageTypes_are_null() {
        adVendorImageService.getVerifyVendorImages(vendorIds, null);
    }

    @Test
    public void success_case() {
        // when
        List<VerifyVendorImageResponse> responses = adVendorImageService.getVerifyVendorImages(vendorIds, vendorImageTypes);

        // then
        Assert.assertNotNull(responses);
        Assert.assertEquals(vendorIds.size() * vendorImageTypes.size(), responses.size());
    }
}
