package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import javax.persistence.Convert;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class PhotoCreditRequest {
    private String photoCreditReason;
    private Integer wholeSalerID;
    private BigDecimal photoCreditAmount;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime fromDate;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime toDate;
}
