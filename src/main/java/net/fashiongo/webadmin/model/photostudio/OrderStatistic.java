package net.fashiongo.webadmin.model.photostudio;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 2. 12..
 */
public class OrderStatistic {

    private Integer categoryId;

    private Integer orderCount = 0;

    private Double totalAamounts = 0d;

    public OrderStatistic(Integer categoryId, Integer totalOrderNumber, Double totalAmounts) {
        this.categoryId = categoryId;
        this.orderCount = totalOrderNumber;
        this.totalAamounts = totalAmounts;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Double getTotalAamounts() {
        return totalAamounts;
    }

    public void setTotalAamounts(Double totalAamounts) {
        this.totalAamounts = totalAamounts;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public static Map<Integer, OrderStatistic> build(List<Tuple> tuples) {

        List<OrderStatistic> results = tuples.stream().map((tuple) -> {
            return new OrderStatistic(tuple.get("categoryId", Integer.class),
                    tuple.get("totalOrderNumber", Long.class).intValue(), tuple.get("totalAmounts", BigDecimal.class).doubleValue());
        }).collect(Collectors.toList());

        return results.stream().collect(Collectors.toMap(order -> order.getCategoryId(), order -> order));
    }



}
