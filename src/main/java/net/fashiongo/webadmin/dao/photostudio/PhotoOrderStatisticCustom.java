package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.OrderDetailStatistic;
import net.fashiongo.webadmin.model.photostudio.OrderStatistic;
import net.fashiongo.webadmin.model.photostudio.PhotoOrder;

import java.util.Date;
import java.util.Map;

/**
 * Created by jinwoo on 2019. 2. 12..
 */
public interface PhotoOrderStatisticCustom {

    Map<Integer, OrderStatistic> getValidOrderStatistic(Date start, Date end);

    Map<Integer, OrderStatistic> getCancelOrderStatistic(Date start, Date end);

    Map<Integer, OrderDetailStatistic> getValidOrderDetailStatistic(Date start, Date end);

    PhotoOrder getPhotoOrderInfoWithAdditionalInfo(String poNumber);
}
