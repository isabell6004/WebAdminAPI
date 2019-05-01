package net.fashiongo.webadmin.model.photostudio;

import lombok.*;
import net.fashiongo.webadmin.service.photostudio.PackageIdChecker;
import net.fashiongo.webadmin.service.photostudio.ReportTypeChecker;
import net.fashiongo.webadmin.utility.DateUtils;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jinwoo on 2019. 4. 26..
 */
@Getter
@Setter
@Builder
@ToString
public class ReportMonthlySummaryResponse {

    private Integer dateCount;

    private BigDecimal totalOrderAmount = BigDecimal.ZERO;

    private BigDecimal avgOrder = BigDecimal.ZERO;

    private Integer firstTimeVendor = 0;

    private Integer returningVendor = 0;

    private Integer womenOrders = 0;

    private Integer womenCancelled = 0;

    private BigDecimal womenAmounts = BigDecimal.ZERO;

    private Integer plusWomenOrders = 0;

    private Integer plusWomenCancelled = 0;

    private BigDecimal plusWomenAmounts = BigDecimal.ZERO;

    private Integer menOrders = 0;

    private Integer menCancelled = 0;

    private BigDecimal menAmounts = BigDecimal.ZERO;

    private Integer kidsOrders = 0;

    private Integer kidsCancelled = 0;

    private BigDecimal kidsAmounts = BigDecimal.ZERO;

    private Integer shoesOrders = 0;

    private Integer shoesCancelled = 0;

    private BigDecimal shoesAmounts = BigDecimal.ZERO;

    private Integer handbagsOrders = 0;

    private Integer handbagsCancelled = 0;

    private BigDecimal handbagsAmounts = BigDecimal.ZERO;

    private Integer accessoriesOrders = 0;

    private Integer accessoriesCancelled = 0;

    private BigDecimal accessoriesAmounts = BigDecimal.ZERO;

    private BigDecimal avgOrdersDaily;
    private BigDecimal avgStylesDaily;
    private BigDecimal avgUnitsDaily;

    private Integer lightblue;
    private Integer pastelyellow;
    private Integer totalCancelledOrders;
    private Integer totalColorSet;
    private Integer totalColors;
    private Integer totalMovie;
    private Integer totalModelSwatch;
    private Integer totalColorSwatch;
    private Integer totalOrders;
    private Integer totalStyles;
    private BigDecimal totalUnits;
    private Integer totalpackage1;
    private Integer totalpackage2;
    private Integer totalpackage3;


    public BigDecimal getAvgOrdersDaily() {
        this.avgOrdersDaily = BigDecimal.valueOf(this.totalOrders / this.dateCount);
        return this.avgOrdersDaily;
    }

    public BigDecimal getAvgStylesDaily() {
        this.avgStylesDaily = BigDecimal.valueOf(this.totalStyles / this.dateCount);
        return this.avgStylesDaily;
    }

    public BigDecimal getAvgUnitsDaily() {
        this.avgUnitsDaily = BigDecimal.valueOf(this.totalUnits.doubleValue() / this.dateCount);
        return this.avgUnitsDaily;
    }

    public BigDecimal getAvgOrder() {

        try {
            this.avgOrder = BigDecimal.valueOf(this.totalOrderAmount.doubleValue() /
                    (this.womenOrders + this.plusWomenOrders + this.menOrders + this.kidsOrders + this.shoesOrders + this.handbagsOrders + this.accessoriesOrders));
        } catch (Throwable t) {
            this.avgOrder = BigDecimal.ZERO;
        }
        return this.avgOrder;
    }

    private void addOrderDetailStatistic(List<PhotoOrderDetail> orderDetails) {

        for(PhotoOrderDetail detail : orderDetails) {
            this.totalStyles += Optional.ofNullable(detail.getStyleQty()).orElse(0);
            this.totalColorSet += Optional.ofNullable(detail.getColorSetQty()).orElse(0);
            this.totalColors += Optional.ofNullable(detail.getColorQty()).orElse(0);
            this.totalMovie += Optional.ofNullable(detail.getMovieQty()).orElse(0);
            this.totalColorSwatch += Optional.ofNullable(detail.getModelSwatchQty()).orElse(0);
            this.totalColorSwatch += Optional.ofNullable(detail.getColorSwatchQty()).orElse(0);
        }

    }

    public void addStatistic(PhotoOrder order) {

        Integer categoryId = order.getCategoryID();
        Integer packageId = order.getPackageID();

        if (order.getIsCancelledBy() == null) {
            this.totalUnits = this.totalUnits.add(Optional.ofNullable(order.getTotalUnit()).orElse(BigDecimal.ZERO));
            this.totalOrders++;
            this.totalOrderAmount = this.totalOrderAmount.add(Optional.ofNullable(order.getTotalAmount()).orElse(BigDecimal.ZERO));

            // color
            switch (Optional.ofNullable(order.getColorID()).orElse(0)) {
                case 2 :
                    this.lightblue++;
                    break;
                case 3 :
                    this.pastelyellow++;
                    break;
            }

            // package
            if(PackageIdChecker.checkFullModelPackage1(order.getPackageID())) {
                this.totalpackage1++;
            }

            if(PackageIdChecker.checkFullModelPackage2(order.getPackageID())) {
                this.totalpackage2++;
            }

            if(PackageIdChecker.checkFullModelPackage3(order.getPackageID())) {
                this.totalpackage3++;
            }

            addOrderDetailStatistic(order.getOrderDetails());
        } else {
            this.totalCancelledOrders++;
        }


        if (ReportTypeChecker.isFullModelShotWoman(categoryId, packageId)) {
            addWomenStatistic(order);
            return;
        }

        if (ReportTypeChecker.isFullModelShotWomanPlusSize(categoryId, packageId)) {
            addWomenPlusStatistic(order);
            return;
        }

        if (ReportTypeChecker.isFlatProductShotMan(categoryId, packageId)) {
            addMenStatistic(order);
            return;
        }

        if (ReportTypeChecker.isFlatProductShotKids(categoryId, packageId)) {
            addKidsStatistic(order);
            return;
        }

        if (ReportTypeChecker.isFlatProductShotShoes(categoryId, packageId)) {
            addShoesStatistic(order);
            return;
        }

        if (ReportTypeChecker.isFlatProductShotHandbags(categoryId, packageId)) {
            addHandbagsStatistic(order);
            return;
        }

        if (ReportTypeChecker.isFlatProductShotAccessories(categoryId, packageId)) {
            addAccessoriesStatistic(order);
            return;
        }
    }

    private void addAccessoriesStatistic(PhotoOrder order) {
        if (order.getIsCancelledBy() == null) {
            this.accessoriesOrders++;
            this.accessoriesAmounts = this.accessoriesAmounts.add(order.getTotalAmount());
        } else {
            this.accessoriesCancelled++;
        }
        return;
    }

    private void addHandbagsStatistic(PhotoOrder order) {
        if (order.getIsCancelledBy() == null) {
            this.handbagsOrders++;
            this.handbagsAmounts = this.handbagsAmounts.add(order.getTotalAmount());
        } else {
            this.handbagsCancelled++;
        }
        return;
    }

    private void addShoesStatistic(PhotoOrder order) {
        if (order.getIsCancelledBy() == null) {
            this.shoesOrders++;
            this.shoesAmounts = this.shoesAmounts.add(order.getTotalAmount());
        } else {
            this.shoesCancelled++;
        }
        return;
    }

    private void addKidsStatistic(PhotoOrder order) {
        if (order.getIsCancelledBy() == null) {
            this.kidsOrders++;
            this.kidsAmounts = this.kidsAmounts.add(order.getTotalAmount());
        } else {
            this.kidsCancelled++;
        }
        return;
    }

    private void addMenStatistic(PhotoOrder order) {
        if (order.getIsCancelledBy() == null) {
            this.menOrders++;
            this.menAmounts = this.menAmounts.add(order.getTotalAmount());
        } else {
            this.menCancelled++;
        }
        return;
    }

    private void addWomenPlusStatistic(PhotoOrder order) {
        if (order.getIsCancelledBy() == null) {
            this.plusWomenOrders++;
            this.plusWomenAmounts = this.plusWomenAmounts.add(order.getTotalAmount());
        } else {
            this.plusWomenCancelled++;
        }
        return;
    }

    private void addWomenStatistic(PhotoOrder order) {
        if (order.getIsCancelledBy() == null) {
            this.womenOrders++;
            this.womenAmounts = this.womenAmounts.add(order.getTotalAmount());
        } else {
            this.womenCancelled++;
        }
        return;
    }

    public void makeOldOrdersSummary(Map<Integer, List<PhotoOrder>> oldOrders) {
        for (Integer wholeSalerId : oldOrders.keySet()) {
            if (oldOrders.get(wholeSalerId).size() == 1) {
                this.firstTimeVendor++;
            } else {
                this.returningVendor++;
            }
        }
    }

    public static ReportMonthlySummaryResponse makeSummary(LocalDateTime startDate, List<PhotoOrder> photoOrders) {

        Integer dateCount = DateUtils.getDateCount(startDate);

        ReportMonthlySummaryResponse response = ReportMonthlySummaryResponse.builder()
                .dateCount(dateCount)
                .totalOrderAmount(BigDecimal.ZERO)
                .avgOrder(BigDecimal.ZERO)
                .firstTimeVendor(0)
                .returningVendor(0)
                .womenOrders(0)
                .womenCancelled(0)
                .womenAmounts(BigDecimal.ZERO)
                .plusWomenOrders(0)
                .plusWomenCancelled(0)
                .plusWomenAmounts(BigDecimal.ZERO)
                .menOrders(0)
                .menCancelled(0)
                .menAmounts(BigDecimal.ZERO)
                .kidsOrders(0)
                .kidsCancelled(0)
                .kidsAmounts(BigDecimal.ZERO)
                .shoesOrders(0)
                .shoesCancelled(0)
                .shoesAmounts(BigDecimal.ZERO)
                .handbagsOrders(0)
                .handbagsCancelled(0)
                .handbagsAmounts(BigDecimal.ZERO)
                .accessoriesOrders(0)
                .accessoriesCancelled(0)
                .accessoriesAmounts(BigDecimal.ZERO)
                .totalCancelledOrders(0)
                .totalOrders(0)
                .totalStyles(0)
                .totalUnits(BigDecimal.ZERO)
                .totalColors(0)
                .totalColorSet(0)
                .totalMovie(0)
                .totalModelSwatch(0)
                .totalColorSwatch(0)
                .lightblue(0)
                .pastelyellow(0)
                .avgOrdersDaily(BigDecimal.ZERO)
                .avgStylesDaily(BigDecimal.ZERO)
                .avgUnitsDaily(BigDecimal.ZERO)
                .totalpackage1(0)
                .totalpackage2(0)
                .totalpackage3(0)
                .build();

        photoOrders.stream().forEach(x -> response.addStatistic(x));

        return response;
    }
}