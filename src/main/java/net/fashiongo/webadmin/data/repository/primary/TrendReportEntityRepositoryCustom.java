package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.sitemgmt.TrendReport;
import net.fashiongo.webadmin.data.model.sitemgmt.TrendReportDefault;
import net.fashiongo.webadmin.data.model.sitemgmt.TrendReportTotal;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface TrendReportEntityRepositoryCustom {
    TrendReportTotal getRecCnt();
    List<TrendReportDefault> getTrendReportDefault(String orderBy, String orderByGubn);

    Page<TrendReport> up_wa_GetAdminTrendReport(int pageNo
                                                ,int pageSize
                                                ,String searchText
                                                ,LocalDateTime fromDate
                                                ,LocalDateTime toDate
                                                ,String orderBy
                                                ,String orderByGubn
                                                ,Boolean active);

}
