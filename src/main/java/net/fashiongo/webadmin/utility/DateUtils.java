package net.fashiongo.webadmin.utility;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
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

    public static LocalDateTime getDatePlusOneDay(LocalDateTime date) {
        LocalDateTime returnDate = date.plusDays(1);
        return returnDate;
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
        LocalDateTime now = LocalDateTime.of(year, month, 1, 0, 0, 0);
        now = now.plusMonths(1);
        now = now.minusDays(1);
        return now;
    }

    public static LocalDateTime getFirstDayOfNextMonth(int year, int month) {
        LocalDateTime now = LocalDateTime.of(year, month, 1, 0, 0, 0);
        now = now.plusMonths(1);
        return now;
    }


    public static LocalDateTime getLastDayOfMonthAsLocalDateTime(LocalDateTime startDate) {
        LocalDateTime endDate = startDate.plusMonths(1);
        endDate = endDate.minusDays(1);
        return endDate;
    }

    public static int getDateCount(LocalDateTime startDate) {
        LocalDateTime endDate = startDate.plusMonths(1);
        int dateCount = (int) ChronoUnit.DAYS.between(startDate, endDate);
        return dateCount;

    }


    public static LocalDateTime getLocalDateTimeFromyyyyMMdd(String yyyymmddString) {
        final String formatString = "yyyyMMdd";
        return parse(yyyymmddString, formatString);
    }

    public static LocalDateTime getLocalDateTimeFromyyyyDashMMDashdd(String yyyydashmmdashddString) {
        final String formatString = "yyyy-MM-dd";
        return parse(yyyydashmmdashddString, formatString);
    }

    private static LocalDateTime parse(String dateString, String formatString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatString);
        DateTimeFormatter localDateTimeFormatter = new DateTimeFormatterBuilder().append(dateFormatter)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();

        LocalDateTime ldt = LocalDateTime.parse(dateString, localDateTimeFormatter);
        return ldt;
    }

    public static LocalDateTime convertToLocalDateTime(Date data) {
        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
