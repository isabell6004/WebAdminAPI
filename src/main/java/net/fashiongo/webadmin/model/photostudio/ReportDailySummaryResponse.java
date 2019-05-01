package net.fashiongo.webadmin.model.photostudio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 4. 25..
 */

@Getter
@Setter
@Builder
@ToString
public class ReportDailySummaryResponse {

    private String date;
    private Integer totalColorSet;
    private Integer totalColors;
    private Integer totalMovie;
    private BigDecimal totalOrderAmount;
    private Integer totalPONumbers;
    private Integer totalStyles;
    private Integer totalModelSwatch;
    private Integer totalColorSwatch;
    private Integer firstTimeVendor;
    private BigDecimal totalUnits;
    private Integer totalOrders;

    public void addStatistic(PhotoOrderDetail detail) {
        this.totalStyles += Optional.ofNullable(detail.getStyleQty()).orElse(0);
        this.totalColors += Optional.ofNullable(detail.getColorQty()).orElse(0);
        this.totalColorSet += Optional.ofNullable(detail.getColorSetQty()).orElse(0);
        this.totalMovie += Optional.ofNullable(detail.getMovieQty()).orElse(0);
        this.totalModelSwatch += Optional.ofNullable(detail.getModelSwatchQty()).orElse(0);
        this.totalColorSwatch += Optional.ofNullable(detail.getColorSwatchQty()).orElse(0);
    }

    public static ReportDailySummaryResponse makeSummary(String date, List<PhotoOrder> orders) {

        ReportDailySummaryResponse response = ReportDailySummaryResponse.builder()
                .date(date)
                .totalPONumbers(0)
                .totalOrderAmount(BigDecimal.ZERO)
                .totalStyles(0)
                .totalColors(0)
                .totalColorSet(0)
                .totalMovie(0)
                .totalModelSwatch(0)
                .totalColorSwatch(0)
                .totalUnits(BigDecimal.ZERO)
                .totalOrders(0)
                .firstTimeVendor(0)
                .build();

        response.setTotalPONumbers(orders.size());
        response.setTotalOrders(orders.size());
        for (PhotoOrder order : orders) {
            response.setTotalOrderAmount(response.getTotalOrderAmount().add(order.getTotalAmount()));
            response.setTotalUnits(response.getTotalUnits().add(order.getTotalUnit()));

            List<PhotoOrderDetail> details = order.getOrderDetails();
            details.stream().forEach(x -> {
                response.addStatistic(x);
            });
        }

        return response;
    }

    public void makeOldOrdersSummary(Map<Integer, List<PhotoOrder>> oldOrders) {
        oldOrders.keySet().stream().filter(wholeSalerId -> oldOrders.get(wholeSalerId).size() == 1).forEach(wholeSalerId -> {
            this.firstTimeVendor++;
        });
    }
}
