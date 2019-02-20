package net.fashiongo.webadmin.model.photostudio;

import java.util.List;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
public class DailyReport extends ExcelReport {

    @Override
    public String getWriterBeanName() {
        return "photoStudioDailyReportWriter";
    }

    private String requestDate;

    private List<PageViewDailyReport> pageViewDailyReports;

    private List<OrderDetailDailyReport> orderDetailDailyReports;

    private List<ClickStatDailyReport> clickStatDailyReports;

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public List<PageViewDailyReport> getPageViewDailyReports() {
        return pageViewDailyReports;
    }

    public void setPageViewDailyReports(List<PageViewDailyReport> pageViewDailyReports) {
        this.pageViewDailyReports = pageViewDailyReports;
    }

    public List<ClickStatDailyReport> getClickStatDailyReports() {
        return clickStatDailyReports;
    }

    public void setClickStatDailyReports(List<ClickStatDailyReport> clickStatDailyReports) {
        this.clickStatDailyReports = clickStatDailyReports;
    }

    public List<OrderDetailDailyReport> getOrderDetailDailyReports() {
        return orderDetailDailyReports;
    }

    public void setOrderDetailDailyReports(List<OrderDetailDailyReport> orderDetailDailyReports) {
        this.orderDetailDailyReports = orderDetailDailyReports;
    }
}
