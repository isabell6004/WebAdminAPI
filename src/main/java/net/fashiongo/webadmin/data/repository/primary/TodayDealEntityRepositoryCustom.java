package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TodayDealEntity;

import java.util.List;

public interface TodayDealEntityRepositoryCustom {
    List<TodayDealEntity> findAllByWholeSalerID(Integer wholeSalerID);
}
