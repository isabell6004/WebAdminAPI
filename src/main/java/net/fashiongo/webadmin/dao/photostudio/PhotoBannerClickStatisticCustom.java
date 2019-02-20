package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoBannerClickStatistic;

import java.util.Date;
import java.util.List;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
public interface PhotoBannerClickStatisticCustom {

    List<PhotoBannerClickStatistic> getClickStatistic(Date start, Date end);

}
