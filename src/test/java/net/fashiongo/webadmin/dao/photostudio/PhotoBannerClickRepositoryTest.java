package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.exception.NotFoundPhotostudioCartInfo;
import net.fashiongo.webadmin.model.photostudio.PhotoBannerClick;
import net.fashiongo.webadmin.model.photostudio.PhotoBannerClickStatistic;
import net.fashiongo.webadmin.model.photostudio.PhotoCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jinwoo on 2019. 2. 4..
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PhotoBannerClickRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PhotoBannerClickRepository photoBannerClickRepository;

    @Test
    public void getPhotoBannerClickInfo() throws NotFoundPhotostudioCartInfo {

        int id = 1;
        PhotoBannerClick click = photoBannerClickRepository.findById(id).orElseThrow(() -> new NotFoundPhotostudioCartInfo());

        logger.debug("click info -> id : {}", click.getId());
        assertNotNull(click);
    }

    @Test
    public void getClickStatInfo() throws NotFoundPhotostudioCartInfo, ParseException {

        String start = "20181204";
        String end = "20181205";

        List<PhotoBannerClickStatistic> statistics = photoBannerClickRepository.getClickStatistic(
                new SimpleDateFormat("yyyyMMdd").parse(start),
                new SimpleDateFormat("yyyyMMdd").parse(end)
        );
        assertNotNull(statistics);

    }

}
