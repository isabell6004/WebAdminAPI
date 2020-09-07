package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TrendReportMapEntity;

import java.util.List;

public interface TrendReportMapEntityRepositoryCustom {

    List<TrendReportMapEntity> findAllByTrendReportID(Integer trendReportID);
}
