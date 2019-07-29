package net.fashiongo.webadmin.mapper;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CommonMapper {

    private final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    public static LocalDateTime asLocalDateTime(String string) throws ParseException {
        return StringUtils.isEmpty(string) ? null : LocalDateTime.parse(string, DATETIME_FORMATTER);
    }

    public static String asString(LocalDateTime localDateTime) {
        return localDateTime == null ? null : DATETIME_FORMATTER.format(localDateTime);
    }
}
