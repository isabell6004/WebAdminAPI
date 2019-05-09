package net.fashiongo.webadmin.model.photostudio;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Transient;

import lombok.*;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.fashiongo.webadmin.service.photostudio.PackageIdChecker;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DailySummaryResponse {

	private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
	
	private String photoshootDate;

	private Integer totalOrders;

	private BigDecimal totalUnits = BigDecimal.ZERO;

	private Integer totalStyles = 0;

	private Integer totalColors = 0;

	private Integer totalColorSet = 0;

	private Integer totalMovie = 0;

	private Integer totalBaseColorSet = 0;

	private Integer totalModelSwatch = 0;

	private Integer totalNewMovie = 0;

	private Integer totalColorSwatch = 0;

	private Integer totalpackage1 = 0;
	
	private Integer totalpackage2 = 0;

	private Integer totalpackage3 = 0;

	private Integer lightpink = 0;

	private Integer lightblue = 0;

	private Integer pastelyellow = 0;

    public static DailySummaryResponse make(LocalDateTime photoshootdate, List<PhotoOrder> photoOrders) {

        DailySummaryResponse response = new DailySummaryResponse().toBuilder()
                .photoshootDate(
                        Optional.ofNullable(photoshootdate).map(x -> x.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD))).orElse(null)
                )
                .totalOrders(Optional.ofNullable(photoOrders).map(x -> x.size()).orElse(0))
                .build();

        for (PhotoOrder order : photoOrders) {
            response.setTotalUnits(response.getTotalUnits().add(order.getTotalUnit()));

            // package
            if(PackageIdChecker.checkFullModelPackage1(order.getPackageID())) {
                response.setTotalpackage1(response.getTotalpackage1() + 1);
            }

            if(PackageIdChecker.checkFullModelPackage2(order.getPackageID())) {
                response.setTotalpackage2(response.getTotalpackage2() + 1);
            }

            if(PackageIdChecker.checkFullModelPackage3(order.getPackageID())) {
                response.setTotalpackage3(response.getTotalpackage3() + 1);
                if(order.getColorID() == 1) { // Pastel Yellow
                    response.setPastelyellow(response.getPastelyellow() + 1);
                } else if (order.getColorID() == 2) { // Light Blue
                    response.setLightblue(response.getLightblue() + 1);
                }
            }

            List<PhotoOrderDetail> details = order.getOrderDetails();
            details.stream().forEach(x -> {
                response.addStatistic(x);
            });
        }

        return response;
    }

    public void addStatistic(PhotoOrderDetail detail) {
        this.totalStyles += Optional.ofNullable(detail.getStyleQty()).orElse(0);
        this.totalColors += Optional.ofNullable(detail.getColorQty()).orElse(0);
        this.totalColorSet += Optional.ofNullable(detail.getColorSetQty()).orElse(0);
        this.totalMovie += Optional.ofNullable(detail.getMovieQty()).orElse(0);

        this.totalModelSwatch += Optional.ofNullable(detail.getModelSwatchQty()).orElse(0);
        this.totalColorSwatch += Optional.ofNullable(detail.getColorSwatchQty()).orElse(0);
        this.totalBaseColorSet += Optional.ofNullable(detail.getBaseColorSetQty()).orElse(0);
        this.totalNewMovie += Optional.ofNullable(detail.getMovieClipQty()).orElse(0);
    }
}
