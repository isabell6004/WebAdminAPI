package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.TrendReport;

/**
 * @author Nayeon Kim
 */
public interface TrendReportRepository extends CrudRepository<TrendReport, Integer> {
	TrendReport findTopByCuratedTypeOrderByTrendReportIDDesc(Integer CuratedType);
}
