package net.fashiongo.webadmin.model.photostudio;

import lombok.*;

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
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ReportDailySummaryResponse {

    private String date;
    private BigDecimal totalOrderAmount = BigDecimal.ZERO;
    private Integer totalPONumbers = 0;
    private BigDecimal totalUnits = BigDecimal.ZERO;
    private Integer totalOrders = 0;

    // old product
    private Integer totalStyles = 0;
    private Integer totalColorSet = 0;
    private Integer totalColors = 0;
    private Integer totalMovie = 0;

    // new product
    private Integer totalModelSwatch = 0;
    private Integer totalColorSwatch = 0;
    private Integer totalBaseColorSet = 0;
    private Integer totalMovieClip = 0;

    private Integer firstTimeVendor = 0;

    public void addStatistic(PhotoOrderDetail detail) {
        this.totalStyles += Optional.ofNullable(detail.getStyleQty()).orElse(0);
        this.totalColors += Optional.ofNullable(detail.getColorQty()).orElse(0);
        this.totalColorSet += Optional.ofNullable(detail.getColorSetQty()).orElse(0);
        this.totalMovie += Optional.ofNullable(detail.getMovieQty()).orElse(0);

        this.totalModelSwatch += Optional.ofNullable(detail.getModelSwatchQty()).orElse(0);
        this.totalColorSwatch += Optional.ofNullable(detail.getColorSwatchQty()).orElse(0);
        this.totalBaseColorSet += Optional.ofNullable(detail.getBaseColorSetQty()).orElse(0);
        this.totalMovieClip += Optional.ofNullable(detail.getMovieClipQty()).orElse(0);
    }

    public static ReportDailySummaryResponse makeSummary(String date, List<PhotoOrder> orders) {

        ReportDailySummaryResponse response = new ReportDailySummaryResponse().toBuilder().build();

        response.setDate(date);
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
