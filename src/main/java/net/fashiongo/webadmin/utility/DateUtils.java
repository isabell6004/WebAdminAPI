package net.fashiongo.webadmin.utility;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jinwoo on 2019. 2. 5..
 */
@Slf4j
public class DateUtils {

    public static Date getDatePlusOneDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }

    public static Date getFirstDayOfMonth(int year, int month) {
        return Date.from(LocalDateTime.of(year, month, 1, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getLastDayOfMonth(int year, int month) {
        LocalDateTime now = LocalDateTime.of(year, month + 1, 1, 0, 0, 0);
        now = now.minusDays(1);
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime getFirstDayOfMonthAsLocalDateTime(int year, int month) {
        return LocalDateTime.of(year, month, 1, 0, 0, 0);
    }

    public static LocalDateTime getLastDayOfMonthAsLocalDateTime(int year, int month) {
        LocalDateTime now = LocalDateTime.of(year, month + 1, 1, 0, 0, 0);
        now = now.minusDays(1);
        return now;
    }

    public static int getDateCount(LocalDateTime startDate) {
        LocalDateTime endDate = startDate.plusMonths(1);
        int dateCount = (int) ChronoUnit.DAYS.between(startDate, endDate);
        return dateCount;

    }
}
