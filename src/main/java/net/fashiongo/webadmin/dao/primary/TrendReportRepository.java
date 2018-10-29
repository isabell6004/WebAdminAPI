package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.TrendReport;

/**
 * @author Nayeon Kim
 */
public interface TrendReportRepository extends CrudRepository<TrendReport, Integer> {
	List<TrendReport> findAllByCuratedTypeOrderByTrendReportIDDesc(Integer CuratedType);
}
