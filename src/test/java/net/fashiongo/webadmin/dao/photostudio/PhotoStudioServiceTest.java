package net.fashiongo.webadmin.dao.photostudio;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.exception.NotFoundPhotostudioCartInfo;
import net.fashiongo.webadmin.model.photostudio.*;
import net.fashiongo.webadmin.service.PhotoStudioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jinwoo on 2019. 3. 4..
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class PhotoStudioServiceTest {

    @Autowired
    private PhotoStudioService photoStudioService;

    @Test
    public void getPhotoOrder() {

        String poNumber = "FGP20190226356";
        Map<String, Object> orderInfo = photoStudioService.getPhotoOrder(poNumber);

        DetailPhotoOrder photoOrder = (DetailPhotoOrder) orderInfo.get("photoOrder");
        List<LogPhotoAction> logPhotoActions = (List<LogPhotoAction>) orderInfo.get("actionLogs");
        List<PhotoOrderDetail> photoOrderDetails = (List<PhotoOrderDetail>) orderInfo.get("items");
        List<PhotoActionUser> photoActionUsers = (List<PhotoActionUser>) orderInfo.get("photoStudioUsers");

        log.debug("photo orders : {}", photoOrder.getCategoryName());
        log.debug("log photo actions : {}", logPhotoActions.size());
        log.debug("photo order details : {}", photoOrderDetails.size());
        log.debug("photo action users : {}", photoActionUsers.size());

    }

    @Test
    public void getPhotoOrder_NotUsingSP() {

        String poNumber = "FGP20190226356";
        Map<String, Object> orderInfo = photoStudioService.getPhotoOrderNotUsingSP(poNumber);

        DetailPhotoOrder photoOrder = (DetailPhotoOrder) orderInfo.get("photoOrder");
        List<LogPhotoAction> logPhotoActions = (List<LogPhotoAction>) orderInfo.get("actionLogs");
        List<PhotoOrderDetail> photoOrderDetails = (List<PhotoOrderDetail>) orderInfo.get("items");
        List<PhotoActionUser> photoActionUsers = (List<PhotoActionUser>) orderInfo.get("photoStudioUsers");

        log.debug("photo orders : {}", photoOrder.getCategoryName());
        log.debug("log photo actions : {}", logPhotoActions.size());
        log.debug("photo order details : {}", photoOrderDetails.size());
        log.debug("photo action users : {}", photoActionUsers.size());

    }
}
