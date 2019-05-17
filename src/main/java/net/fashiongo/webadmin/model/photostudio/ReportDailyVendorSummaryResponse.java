package net.fashiongo.webadmin.model.photostudio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jinwoo on 2019. 4. 30..
 */
@Getter
@Setter
@Builder
public class ReportDailyVendorSummaryResponse {

    private String date;

    private BigDecimal totalOrderAmount;

    private String wholeSalerCompanyName;

    public static ReportDailyVendorSummaryResponse makeSummary(String date, String wholeSalerCompanyName, List<PhotoOrder> validOrders) {

        ReportDailyVendorSummaryResponse response = ReportDailyVendorSummaryResponse.builder()
                .date(date)
                .totalOrderAmount(validOrders.stream().map(PhotoOrder::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add))
                .wholeSalerCompanyName(wholeSalerCompanyName)
                .build();
        return response;
    }
}
