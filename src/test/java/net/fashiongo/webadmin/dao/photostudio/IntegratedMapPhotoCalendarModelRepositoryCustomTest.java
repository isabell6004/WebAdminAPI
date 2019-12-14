package net.fashiongo.webadmin.dao.photostudio;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.photostudio.*;
import net.fashiongo.webadmin.utility.DateUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 4. 25..
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class IntegratedMapPhotoCalendarModelRepositoryCustomTest {

    @Autowired
    private MapPhotoCalendarModelRepositoryCustom mapPhotoCalendarModelRepositoryCustom;

    @Ignore
    @Test
    public void findAvailableMapByTheDate() {

        LocalDate theDate = null;
        List<MapPhotoCalendarModel> mapPhotoCalendarModels = mapPhotoCalendarModelRepositoryCustom.findAvailableMapByTheDate(theDate);

        Assert.assertNotNull(mapPhotoCalendarModels);

    }



}
