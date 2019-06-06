package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.OrderDetailStatistic;
import net.fashiongo.webadmin.model.photostudio.OrderStatistic;
import net.fashiongo.webadmin.model.photostudio.PhotoOrder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jinwoo on 2019. 2. 12..
 */
public interface PhotoOrderRepositoryCustom {

    PhotoOrder getPhotoOrderInfoWithBookAndModelAndCategory(int orderId);

    Map<Integer, OrderStatistic> getValidOrderStatistic(Date start, Date end);

    Map<Integer, OrderStatistic> getCancelOrderStatistic(Date start, Date end);

    Map<Integer, OrderDetailStatistic> getValidOrderDetailStatistic(Date start, Date end);

    PhotoOrder getPhotoOrderInfoWithAdditionalInfo(String poNumber);

    List<PhotoOrder> getValidOrderWithDetail(LocalDateTime start, LocalDateTime end);

    List<PhotoOrder> getCancelOrderWithDetail(LocalDateTime start, LocalDateTime end);

    List<PhotoOrder> getOrderWithDetail(LocalDateTime start, LocalDateTime end);

    Map<Integer, List<PhotoOrder>> getOrderOfWholeSaler(List<Integer> wholeSalerIds);

    Map<Integer, PhotoOrder> getOrderOfCart(List<Integer> cartIds);

    List<PhotoOrder> getOrderWithDetailByPhotoshootDate(LocalDateTime start, LocalDateTime end);

    List<PhotoOrder> getValidOrderWithDetailByPhotoshootDate(LocalDateTime start, LocalDateTime end);

    List<PhotoOrder> getOrdersByPhotoshootDate(LocalDateTime start, LocalDateTime end);
}
