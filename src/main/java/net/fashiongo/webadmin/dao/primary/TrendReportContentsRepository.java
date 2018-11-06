package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.TrendReportContents;

public interface TrendReportContentsRepository extends CrudRepository<TrendReportContents, Integer> {
	TrendReportContents findOneByTrendReportIDIsNull();

}
