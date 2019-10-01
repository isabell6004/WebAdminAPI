package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.sitemgmt.TrendReportDefault;
import net.fashiongo.webadmin.data.model.sitemgmt.TrendReportTotal;

import java.util.List;

public interface TrendReportEntityRepositoryCustom {
    TrendReportTotal getRecCnt();
    List<TrendReportDefault> getTrendReportDefault(String orderBy, String orderByGubn);
}
