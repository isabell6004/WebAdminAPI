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
public class ReportTypeTest {

    @Autowired
    private PhotoStudioProperties photoStudioProperties;

    @Test
    public void get_categories_from_configuration_flat_product_shot_accessories() {

        ReportType reportType = ReportType.FlatProductShotAccessories;
        List<Integer> categories = photoStudioProperties.getCategoryIds(reportType);
        List<Integer> packages = photoStudioProperties.getPackageIds(reportType);

        Assert.assertNull(categories);
        Assert.assertEquals(1, packages.size());

        Assert.assertEquals(16, packages.get(0).intValue());
    }

    @Test
    public void get_categories_from_configuration_flat_product_shot_man() {

        ReportType reportType = ReportType.FlatProductShotMen;
        List<Integer> categories = photoStudioProperties.getCategoryIds(reportType);
        List<Integer> packages = photoStudioProperties.getPackageIds(reportType);

        Assert.assertEquals(1, categories.size());
        Assert.assertEquals(1, packages.size());

        Assert.assertEquals(3, categories.get(0).intValue());
        Assert.assertEquals(12
                , packages.get(0).intValue());
    }

    @Test
    public void get_categories_from_configuration_full_model_shot_woman() {

        ReportType reportType = ReportType.FullModelShotWomen;
        List<Integer> categories = photoStudioProperties.getCategoryIds(reportType);
        List<Integer> packages = photoStudioProperties.getPackageIds(reportType);

        Assert.assertEquals(2, categories.size());
        Assert.assertNull(packages);

        Assert.assertEquals(1, categories.get(0).intValue());
        Assert.assertEquals(5, categories.get(1).intValue());
    }
}
