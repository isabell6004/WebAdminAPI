package net.fashiongo.webadmin.service;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.photostudio.PhotoBannerClickStatistic;
import net.fashiongo.webadmin.model.photostudio.PhotoCart;
import net.fashiongo.webadmin.model.photostudio.ReportType;
import net.fashiongo.webadmin.utility.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jinwoo on 2019. 3. 4..
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class IntegratedPhotoStudioServiceTest {

    @Autowired
    private PhotoStudioService photoStudioService;

    @Test
    public void getReports() {

//        int categoryID = ReportType.DailySalesReport.getValue();
//        int year = 2019;
//        int month = 4;
//        Map<String, Object> dummyRequesParams = new HashMap<>();
//        dummyRequesParams.put("categoryID", categoryID);
//        dummyRequesParams.put("year", year);
//        dummyRequesParams.put("month", month);
//
//        Map<String, Object> reports = photoStudioService.getReports(dummyRequesParams);
//        log.debug("reports size : {}", reports.size());

    }
//
//    @Test
//    public void get_pohto_carts() {
//
//        int year = 2019;
//        int month = 4;
//
//        Date start = DateUtils.getFirstDayOfMonth(year, month);
//        Date end = DateUtils.getLastDayOfMonth(year, month);
//
//        List<PhotoCart> photoCarts = photoStudioService.getPhotoCarts(start, end);
//        log.info("photo cart size : {}", photoCarts.size());
//
//        Assert.assertNotNull(photoCarts);
//
//        for (PhotoCart p : photoCarts) {
//            log.info("cart : {}, {}", p.getCreatedOn(), p.getCartDetails().size());
//        }
//
//    }
//
//    @Test
//    public void get_banner_click_statistic() {
//
//        int year = 2019;
//        int month = 4;
//
//        Date start = DateUtils.getFirstDayOfMonth(year, month);
//        Date end = DateUtils.getLastDayOfMonth(year, month);
//
//        List<PhotoBannerClickStatistic> photoBannerClickStatistics = photoStudioService.getPhotoBannerClicks(start, end);
//        log.info("photo banner click statistic size : {}", photoBannerClickStatistics.size());
//
//        Assert.assertNotNull(photoBannerClickStatistics);
//    }

}
