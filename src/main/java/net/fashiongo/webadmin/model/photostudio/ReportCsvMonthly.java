package net.fashiongo.webadmin.model.photostudio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Column;

@Getter
@Setter
@Builder
public class ReportCsvMonthly {

	private String photoshootDate;

	private String wholeSalerCompanyName;

	private BigDecimal orderAmount;

	private BigDecimal dailyOrderAmount;

	private Integer dailyOrders;

	private BigDecimal avgDailyOrderAmounts;

	private BigDecimal units;

	private BigDecimal dailyTotalUnits;

	private Integer styleQty;

	private Integer dailyTotalStyles;

    public static List<ReportCsvMonthly> makeSummary(List<PhotoOrder> orders) {

        if(CollectionUtils.isEmpty(orders)) {
            return new ArrayList<>();
        }

        List<ReportCsvMonthly> reports = new ArrayList<>();
        Map<String, List<PhotoOrder>> photoOrderMap = orders.stream().collect(Collectors.groupingBy(PhotoOrder::getPhotoshootDate));
        for(String date : photoOrderMap.keySet()) {
            List<PhotoOrder> dailyOrders = photoOrderMap.get(date);

            BigDecimal dailyOrderAmount = BigDecimal.ZERO;
            Integer dailyOrderCount = 0;
            BigDecimal avgDailyOrderAmounts = BigDecimal.ZERO;
            BigDecimal dailyTotalUnits = BigDecimal.ZERO;
            Integer dailyTotalStyles = 0;

            for(PhotoOrder o : dailyOrders) {
                dailyOrderAmount = dailyOrderAmount.add(o.getTotalAmount());
                dailyOrderCount++;
                dailyTotalUnits = dailyTotalUnits.add(o.getTotalUnit());
                List<PhotoOrderDetail> details = o.getOrderDetails();
                dailyTotalStyles += details.stream().mapToInt(x -> x.getStyleQty()).sum();
            }
            try {
                avgDailyOrderAmounts = BigDecimal.valueOf(dailyOrderAmount.longValue() / dailyOrderCount);
            } catch (Throwable t) {
            }

            Map<String, List<PhotoOrder>> wholeSalerOrderMap = dailyOrders.stream().collect(Collectors.groupingBy(PhotoOrder::getWholeSalerCompanyName));
            for(String wholeSalerCompanyName : wholeSalerOrderMap.keySet()) {
                List<PhotoOrder> wholeSalerOrders = wholeSalerOrderMap.get(wholeSalerCompanyName);

                BigDecimal orderAmount = BigDecimal.ZERO;
                BigDecimal units = BigDecimal.ZERO;
                Integer styleQty = 0;
                for(PhotoOrder w : wholeSalerOrders) {
                    orderAmount = orderAmount.add(w.getTotalAmount());
                    units = units.add(w.getTotalUnit());
                    List<PhotoOrderDetail> details = w.getOrderDetails();
                    styleQty += details.stream().mapToInt(x -> x.getStyleQty()).sum();
                }

                ReportCsvMonthly report = ReportCsvMonthly.builder()
                        .photoshootDate(date)
                        .wholeSalerCompanyName(wholeSalerCompanyName)
                        .dailyOrderAmount(dailyOrderAmount)
                        .dailyOrders(dailyOrderCount)
                        .avgDailyOrderAmounts(avgDailyOrderAmounts)
                        .dailyTotalUnits(dailyTotalUnits)
                        .dailyTotalStyles(dailyTotalStyles)
                        .orderAmount(orderAmount)
                        .units(units)
                        .styleQty(styleQty)
                        .build();

                reports.add(report);
            }

        }

        if(CollectionUtils.isNotEmpty(reports)) reports.sort(Comparator.comparing(o -> o.getPhotoshootDate()));
        return reports;
    }
}
