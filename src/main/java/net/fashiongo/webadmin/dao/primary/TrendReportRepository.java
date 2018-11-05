package net.fashiongo.webadmin.dao.primary;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.primary.TrendReport;

/**
 * @author Nayeon Kim
 */
public interface TrendReportRepository extends CrudRepository<TrendReport, Integer> {
	TrendReport findTopByCuratedTypeOrderByTrendReportIDDesc(Integer CuratedType);
	List<TrendReport> findByActiveTrue();
	List<TrendReport> findByTrendReportIDIn(List<Integer> trendReportIDs);
	TrendReport findOneByTrendReportID(Integer trendReportID);
	@Transactional("primaryTransactionManager")
	void deleteByTrendReportIDIn(List<Integer> trendReportIDs);
}
