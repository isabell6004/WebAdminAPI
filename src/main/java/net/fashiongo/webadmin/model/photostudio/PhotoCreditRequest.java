package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.Local;

import javax.persistence.Convert;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class PhotoCreditRequest {
    private static final String FROM_TIME = " 00:00:00";
    private static final String TO_TIME = " 23:59:59";
    private static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    private String photoCreditReason;
    private Integer wholeSalerID;
    private BigDecimal photoCreditAmount;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime fromDate;

    public void setFromDate(String fromDate) {
        String date = StringUtils.isNotEmpty(fromDate) ? fromDate + FROM_TIME : null;
        this.fromDate = StringUtils.isNotEmpty(date) ? LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null;
    }

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime toDate;

    public void setToDate(String toDate) {
        String date = StringUtils.isNotEmpty(toDate) ? toDate + TO_TIME : null;
        this.toDate = StringUtils.isNotEmpty(date) ? LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDDHHMMSS)) : null;
    }
}
