package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TrendReportEntity;
import net.fashiongo.webadmin.data.model.kmm.KmmCandidateItems;
import net.fashiongo.webadmin.data.model.kmm.KmmListDetail;
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

    void up_wa_AddDelTrendReportItem(String setType
            , int mapId
            , int trendreportId
            , int productId
            , String modifiedBy);

    TrendReportEntity findOneByTrendReportIDAndCuratedType(Integer trendReportID);

    List<KmmCandidateItems> up_wa_GetTrendReportProductType1(Integer trendReportId);
    List<KmmCandidateItems> up_wa_GetTrendReportProductType2(Integer trendReportId);

    Page<KmmListDetail> up_wa_GetAdminTrendReport2(int pageNo
                                                , int pageSize
                                                , String searchText
                                                , LocalDateTime fromDate
                                                , LocalDateTime toDate
                                                , String orderBy
                                                , String orderByGubn
                                                , Boolean active
                                                , Integer curatedType);
}
