package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.TodayDeal;

public interface TodayDealRepository extends CrudRepository<TodayDeal, Integer> {
	TodayDeal findOneByTodayDealId(Integer todayDealId);
}