package net.fashiongo.webadmin.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

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

    public static LocalDateTime convertToLocalDateTime(String dateString,String type) {

//        if (string.IsNullOrEmpty(dateFormat))
//        {
//            dateFormat = "NaN";
//        }
//
//        var r = DateTime.Now;
//        if (dateFormat.Contains("NaN"))
//        {
//            switch (typ)
//            {
//                case "F":
//                    r = new DateTime(1970, 1, 1);
//                    break;
//                case "T":
//                    r = new DateTime(2099, 12, 31);
//                    //r = DateTime.Today.AddDays(1);
//                    break;
//                default:
//                    break;
//            }
//        }
//        else
//        {
//            r = Convert.ToDateTime(dateFormat);
//            if (typ == "T")
//            {
//                if (r.Hour == 0)
//                {
//                    r = r.AddDays(1).AddSeconds(-1);
//                }
//            }
//        }
//
//        return r;
        return Optional.ofNullable(dateString)
                .filter(s -> StringUtils.hasLength(s))
                .map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .map(dateTime -> {
                    if ("T".equalsIgnoreCase(type)) {
                        if (dateTime.getHour() == 0) {
                            return dateTime.plusDays(1).minusSeconds(1);
                        }
                    }
                    return dateTime;
                }).orElseGet(() -> {
                    LocalDateTime now = LocalDateTime.now();

                    switch (type) {
                        case "F":
                            return LocalDateTime.of(1970, 1, 1, 0, 0, 0, 0);
                        case "T":
                            return LocalDateTime.of(2099, 12, 31, 0, 0, 0, 0);
                    }

                    return now;
                });
    }

}
