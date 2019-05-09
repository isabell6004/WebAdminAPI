package net.fashiongo.webadmin.utility;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by jinwoo on 2019. 4. 19..
 */
@Slf4j
public class DateUtilsTest {

    @Test
    public void getDatePlusOneDay() {

        Date now = new Date();
        Date tommorrow = DateUtils.getDatePlusOneDay(now);
        log.debug(tommorrow.toString());

        Assert.assertTrue(now.compareTo(tommorrow) < 0);
    }

    @Test
    public void getFirstDayOfMonth() {

        int year = 2019;
        int month = 4;

        Date date = DateUtils.getFirstDayOfMonth(year, month);
        log.debug(date.toString());
    }


    @Test
    public void getLastDayOfMonth() {
        int year = 2019;
        int month = 4;

        Date date = DateUtils.getLastDayOfMonth(year, month);
        log.debug(date.toString());
    }

    @Test
    public void getDateCount() {

        int year = 2019;
        int month = 4;

        LocalDateTime start = DateUtils.getFirstDayOfMonthAsLocalDateTime(year, month);
        int dateCount = DateUtils.getDateCount(start);
        log.debug("{}", dateCount);
    }

    @Test
    public void getLocalDateTime() {

        String str = "20190501";
        LocalDateTime startDate = DateUtils.getLocalDateTimeFromyyyyMMdd(str);

        log.debug("{}", startDate);
    }

    @Test
    public void getLastDayOfMonthAsLocalDateTime() {

        String str = "20190501";
        LocalDateTime startDate = DateUtils.getLocalDateTimeFromyyyyMMdd(str);
        LocalDateTime endDate = DateUtils.getLastDayOfMonthAsLocalDateTime(startDate);

        log.debug("{}, {}", startDate, endDate);
    }
}
