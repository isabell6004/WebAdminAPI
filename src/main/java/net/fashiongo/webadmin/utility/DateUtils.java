package net.fashiongo.webadmin.utility;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jinwoo on 2019. 2. 5..
 */
public class DateUtils {

    public static Date getDatePlusOneDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }
}
