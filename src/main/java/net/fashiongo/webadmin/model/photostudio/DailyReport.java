package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
@Getter
@Setter
public class DailyReport extends ExcelReport {

    @Override
    public String getWriterBeanName() {
        return "photoStudioDailyReportWriter";
    }

    private String requestDate;

    private List<PageViewDailyReport> pageViewDailyReports;

    private List<OrderDetailDailyReport> orderDetailDailyReports;

    private List<ClickStatDailyReport> clickStatDailyReports;

    private ReportMonthlySummaryResponse reportMonthlySummaryResponse;

}
