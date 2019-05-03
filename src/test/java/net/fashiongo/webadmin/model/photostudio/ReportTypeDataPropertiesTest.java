package net.fashiongo.webadmin.model.photostudio;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jinwoo on 2019. 4. 25..
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ReportTypeDataPropertiesTest {

    @Autowired
    private PhotoStudioProperties photoStudioProperties;

    @Test
    public void load_test() {
        Assert.assertNotNull(photoStudioProperties);

        List<Integer> categoryIds = photoStudioProperties.getCategoryIds(ReportType.FullModelShotWomenPlusSize);
        Assert.assertNotNull(categoryIds);
    }
}
