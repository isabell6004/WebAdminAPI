package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.exception.NotFoundPhotostudioCartInfo;
import net.fashiongo.webadmin.model.photostudio.PhotoCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jinwoo on 2019. 2. 4..
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PhotoCartRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PhotoCartRepository photoCartRepository;

    @Test
    public void getPhotoCartInfo() throws NotFoundPhotostudioCartInfo {

        int cartId = 4;

        PhotoCart cart = photoCartRepository.findById(cartId).orElseThrow(() -> new NotFoundPhotostudioCartInfo());

        logger.debug("cart info -> id : {}", cart.getId());
        logger.debug("cart info -> package info : {}", cart.getPackageInfo().getName());
        logger.debug("cart info -> category info : {}", cart.getCategory().getCategoryName());

        assertNotNull(cart);
        assertNotNull(cart.getCartDetails().get(0));

        logger.debug("cart detail info -> id : {}", cart.getCartDetails().get(0).getCartId());
    }

    @Test
    public void getPhotoCartInfosByDate() throws NotFoundPhotostudioCartInfo, ParseException {

        String start = "20190504";
        String end = "20190531";

        List<PhotoCart> carts = photoCartRepository.findAllByCreatedOnBetween(
                LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay(),
                LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay()
        );

        logger.debug("cart info -> id : {}", carts.size());

        assertNotNull(carts);
        logger.debug("cart detail info -> id : {}", carts.get(0).getCartDetails().get(0).getCartId());
    }

}
