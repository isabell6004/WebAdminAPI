package net.fashiongo.webadmin.model.photostudio;

import net.fashiongo.webadmin.model.pojo.statics.OrderStatic;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 2. 11..
 */
public class OrderDetailDailyReport {

    private Integer categoryId;

    private String catetoryName;

    private OrderStatistic orderStatistic;

    private OrderStatistic canceledOrderStatistic;

    private OrderDetailStatistic orderDetailStatistic;

    private Integer newCustomerCount = 0;

    public String getCatetoryName() {
        return catetoryName;
    }

    public void setCatetoryName(String catetoryName) {
        this.catetoryName = catetoryName;
    }

    public Integer getNewCustomerCount() {
        return newCustomerCount;
    }

    public void setNewCustomerCount(Integer newCustomerCount) {
        this.newCustomerCount = newCustomerCount;
    }

    public OrderStatistic getCanceledOrderStatistic() {
        return canceledOrderStatistic;
    }

    public void setCanceledOrderStatistic(OrderStatistic canceledOrderStatistic) {
        this.canceledOrderStatistic = canceledOrderStatistic;
    }

    public OrderDetailStatistic getOrderDetailStatistic() {
        return orderDetailStatistic;
    }

    public void setOrderDetailStatistic(OrderDetailStatistic orderDetailStatistic) {
        this.orderDetailStatistic = orderDetailStatistic;
    }

    public OrderStatistic getOrderStatistic() {
        return orderStatistic;
    }

    public void setOrderStatistic(OrderStatistic orderStatistic) {
        this.orderStatistic = orderStatistic;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public static List<OrderDetailDailyReport> build(Map<Integer, PhotoCategory> photoCategoryMap,
                                                     Map<Integer, OrderStatistic> orderStatistic,
                                                     Map<Integer, OrderStatistic> canceledOrderStatistic,
                                                     Map<Integer, OrderDetailStatistic> orderDetailStatistic) {

        // TODO i dont like below code.
        Map<Integer, OrderDetailDailyReport> reportMap = new HashMap<>();
        for (Integer categoryId : orderStatistic.keySet()) {
            OrderDetailDailyReport report = null;
            if (reportMap.containsKey(categoryId)) {
                report = reportMap.get(categoryId);
            } else {
                report = new OrderDetailDailyReport();
            }
            report.setCategoryId(categoryId);

            if(photoCategoryMap.get(categoryId) == null) {
                report.setCatetoryName("" + categoryId);
            } else {
                report.setCatetoryName(photoCategoryMap.get(categoryId).getCategoryName());
            }
            report.setOrderStatistic(orderStatistic.get(categoryId));
            reportMap.put(categoryId, report);
        }

        for (Integer categoryId : canceledOrderStatistic.keySet()) {
            OrderDetailDailyReport report = null;
            if (reportMap.containsKey(categoryId)) {
                report = reportMap.get(categoryId);
            } else {
                report = new OrderDetailDailyReport();
            }
            report.setCategoryId(categoryId);
            report.setCanceledOrderStatistic(canceledOrderStatistic.get(categoryId));
            reportMap.put(categoryId, report);
        }

        for (Integer categoryId : orderDetailStatistic.keySet()) {
            OrderDetailDailyReport report = null;
            if (reportMap.containsKey(categoryId)) {
                report = reportMap.get(categoryId);
            } else {
                report = new OrderDetailDailyReport();
            }
            report.setCategoryId(categoryId);
            report.setOrderDetailStatistic(orderDetailStatistic.get(categoryId));
            reportMap.put(categoryId, report);
        }

        return reportMap.values().stream().collect(Collectors.toList());
    }

}
