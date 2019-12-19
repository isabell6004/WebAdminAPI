package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TrendReportContentsEntity;

public interface TrendReportContentsEntityRepositoryCustom {
    TrendReportContentsEntity findOneByTrendReportID(Integer trendReportId);
}
