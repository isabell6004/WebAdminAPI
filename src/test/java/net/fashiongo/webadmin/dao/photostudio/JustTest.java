package net.fashiongo.webadmin.dao.photostudio;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.utility.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Period;

/**
 * Created by jinwoo on 2019. 4. 22..
 */
@Slf4j
public class JustTest {

    @Test(expected = NullPointerException.class)
    public void check_null_object_and_primitive_result_nullpointerexception() {

        Integer src1 = null;
        int src2 = 0;

        if(src1 == src2) {
            Assert.fail();
        }
    }

    @Test
    public void cal_dates_of_localdatetime() {

        LocalDateTime start = DateUtils.getFirstDayOfMonthAsLocalDateTime(2019, 12);
        LocalDateTime end = DateUtils.getLastDayOfMonthAsLocalDateTime(2020, 1);

        Period p0 = Period.between(start.toLocalDate(), end.toLocalDate());
        log.debug("period : {}", p0.getDays());

        Period p = Period.of(2019, 4, 1);
        int period = p.getDays();

        log.debug("period : {}", period);

    }

    @Test(expected = NullPointerException.class)
    public void null_test_Long_valueof() {
        Long test = null;
        Long.valueOf(test);
    }

    @Test
    public void test_string_compare() {

        String str1 = "2020-04-01";
        String str2 = "2020-04-02";
        log.debug("result : " + StringUtils.compare(str1, str2));
        Assert.assertTrue(StringUtils.compare(str1, str2) < 0);

        String str3 = "2020-04-01";
        String str4 = "2020-04-01";
        log.debug("result : " + StringUtils.compare(str3, str4));
        Assert.assertTrue(StringUtils.compare(str3, str4) == 0);

        String str5 = "2020-04-01";
        String str6 = "2020-03-02";
        log.debug("result : " + StringUtils.compare(str5, str6));
        Assert.assertTrue(StringUtils.compare(str5, str6) > 0);
    }

}
