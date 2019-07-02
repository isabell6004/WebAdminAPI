package net.fashiongo.webadmin.controller.view;

import net.fashiongo.webadmin.model.photostudio.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by nhnent on 2019. 2. 4..
 */
@Component
public class PhotoStudioDailyReportWriter extends AbstractPhotoStudioReportWriter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String DAILY_REPORT_FILE_NAME = "daily_report_photostudio";
    private final static String DAILY_REPORT_FILE_NAME_EXTENSION = ".xlsx";

    private final static String PAGE_VIEW_SHEET_KEY = "PageView";
    private final static String ORDER_DETAIL_SHEET_KEY = "OrderDetail";
    private final static String CLICKS_SHEET_KEY = "Clicks";
    private final static String MONTHLY_SHEET_KEY = "Monthly Summary";

    @Override
    protected String getFileName(Map<String, Object> dataMap) {

        DailyReport dailyReport = (DailyReport) dataMap.get("excelReport");
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(DAILY_REPORT_FILE_NAME)
                .append("_").append(dailyReport.getRequestDate())
                .append(DAILY_REPORT_FILE_NAME_EXTENSION);
        return fileNameBuilder.toString();
    }

    @Override
    protected void createExcelData(Workbook workbook, Map<String, Object> dataMap, HttpServletResponse response) {

        DailyReport dailyReport = (DailyReport) dataMap.get("excelReport");

        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);

        Sheet pageViewsheet = workbook.createSheet(PAGE_VIEW_SHEET_KEY);
        mapToPageViewHeadList(pageViewsheet, style);
        mapToPageViewBodyList(pageViewsheet, dailyReport.getPageViewDailyReports());

        Sheet orderDetailsheet = workbook.createSheet(ORDER_DETAIL_SHEET_KEY);
        mapToOrderDetailHeadList(orderDetailsheet, style);
        mapToOrderDetailBodyList(orderDetailsheet, dailyReport.getOrderDetailDailyReports());

        Sheet clickSheet = workbook.createSheet(CLICKS_SHEET_KEY);
        mapToClicksHeadList(clickSheet, style);
        mapToClicksBodyList(clickSheet, dailyReport.getClickStatDailyReports());

        Sheet monthlySummarySheet = workbook.createSheet(MONTHLY_SHEET_KEY);
        mapToMonthlySummaryData(monthlySummarySheet, dailyReport.getReportMonthlySummaryResponse());
    }

    private void mapToMonthlySummaryData(Sheet monthlySummarySheet, ReportMonthlySummaryResponse response) {

        int rowNumber = 0;
        Row row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue(response.getStartDate().format(DateTimeFormatter.ofPattern("MM/yyyy")));

        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("Total Amount");
        row.createCell(1).setCellValue(response.getTotalOrderAmount().longValue());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("Avg. order");
        row.createCell(1).setCellValue(response.getAvgOrder().longValue());

        rowNumber++;
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of orders : Full Women");
        row.createCell(1).setCellValue(response.getWomenOrders());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of Cancelled order : Full Women");
        row.createCell(1).setCellValue(response.getWomenCancelled());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("Full Women total order amounts");
        row.createCell(1).setCellValue(response.getWomenAmounts().longValue());

        rowNumber++;
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of orders of Full Plus Women");
        row.createCell(1).setCellValue(response.getPlusWomenOrders());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of Cacelled Order : Pluse Women");
        row.createCell(1).setCellValue(response.getPlusWomenCancelled());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("Plus Women total order amounts");
        row.createCell(1).setCellValue(response.getPlusWomenAmounts().longValue());

        rowNumber++;
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of orders of Product Men");
        row.createCell(1).setCellValue(response.getMenOrders());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of Cacelled Order : Product Men");
        row.createCell(1).setCellValue(response.getMenCancelled());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("Product Men total order amounts");
        row.createCell(1).setCellValue(response.getMenAmounts().longValue());

        rowNumber++;
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of orders of Product Kids");
        row.createCell(1).setCellValue(response.getKidsOrders());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of Cacelled Order : Product Kids");
        row.createCell(1).setCellValue(response.getKidsCancelled());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("Product Kids total order amounts");
        row.createCell(1).setCellValue(response.getKidsAmounts().longValue());

        rowNumber++;
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of orders of Product Shoes");
        row.createCell(1).setCellValue(response.getShoesOrders());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of Cacelled Order : Product Shoes");
        row.createCell(1).setCellValue(response.getShoesCancelled());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("Product Shoes total order amounts");
        row.createCell(1).setCellValue(response.getShoesAmounts().longValue());

        rowNumber++;
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of orders of Product Handbags");
        row.createCell(1).setCellValue(response.getHandbagsOrders());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of Cacelled Order : Product Handbags");
        row.createCell(1).setCellValue(response.getHandbagsCancelled());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("Product Handbags total order amounts");
        row.createCell(1).setCellValue(response.getHandbagsAmounts().longValue());

        rowNumber++;
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of orders of Product Accessories");
        row.createCell(1).setCellValue(response.getAccessoriesOrders());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("# of Cacelled Order : Product Accessories");
        row.createCell(1).setCellValue(response.getAccessoriesCancelled());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("Product Accessories total order amounts");
        row.createCell(1).setCellValue(response.getAccessoriesAmounts().longValue());

        rowNumber++;
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("Returning Vendors");
        row.createCell(1).setCellValue(response.getReturningVendor());
        row = monthlySummarySheet.createRow(rowNumber++);
        row.createCell(0).setCellValue("First Time Vendors");
        row.createCell(1).setCellValue(response.getFirstTimeVendor());

    }

    private void mapToOrderDetailBodyList(Sheet sheet, List<OrderDetailDailyReport> orderDetailDailyReports) {

        int rowNumber = 1;
        for (OrderDetailDailyReport orderDetailDailyReport : orderDetailDailyReports) {
            int cellNumber = 0;
            Row row = sheet.createRow(rowNumber++);
            row.createCell(cellNumber++).setCellValue(orderDetailDailyReport.getCatetoryName());
            row.createCell(cellNumber++).setCellValue(orderDetailDailyReport.getOrderStatistic() == null ? 0 : orderDetailDailyReport.getOrderStatistic().getOrderCount());
            row.createCell(cellNumber++).setCellValue(orderDetailDailyReport.getOrderStatistic() == null ? 0 : orderDetailDailyReport.getOrderStatistic().getTotalAamounts());

            OrderDetailStatistic statistic = orderDetailDailyReport.getOrderDetailStatistic();
            if(statistic == null) {
                row.createCell(cellNumber++).setCellValue(0);
                row.createCell(cellNumber++).setCellValue(0);
                row.createCell(cellNumber++).setCellValue(0);
                row.createCell(cellNumber++).setCellValue(0);
                row.createCell(cellNumber++).setCellValue(0);
                row.createCell(cellNumber++).setCellValue(0);
                row.createCell(cellNumber++).setCellValue(0);
                row.createCell(cellNumber++).setCellValue(0);
            } else {
                row.createCell(cellNumber++).setCellValue(statistic.getStyleQuantity());
                row.createCell(cellNumber++).setCellValue(statistic.getAdditionalColorSetQuantity());
                row.createCell(cellNumber++).setCellValue(statistic.getAdditionalColorQuantity());
                row.createCell(cellNumber++).setCellValue(statistic.getMovieClipsQuantity());

                row.createCell(cellNumber++).setCellValue(statistic.getBaseColorSetQuantity());
                row.createCell(cellNumber++).setCellValue(statistic.getModelSwatchQuantity());
                row.createCell(cellNumber++).setCellValue(statistic.getNewMovieClipsQuantity());
                row.createCell(cellNumber++).setCellValue(statistic.getColorSwatchQuantity());
            }

            row.createCell(cellNumber++).setCellValue(orderDetailDailyReport.getCanceledOrderStatistic() == null ? 0 : orderDetailDailyReport.getCanceledOrderStatistic().getOrderCount());
        }
    }

    private void mapToPageViewHeadList(Sheet sheet, CellStyle style) {
        String[] heads = {
                "Order submit date",
                "Vendor name",
                "Category",
                "Package",
                "Input quantity- Full/Style",

                "Total styles",
                "Total add'l colors",
                "Total add'l color set",
                "Total movie clip",

                "Total color set",
                "Total model swatch",
                "Total movie clip",
                "Total color swatch",

                "Pick a date",
                "Choose model",
                "Promo",
                "Order Complete"};

        createRow(sheet, Arrays.asList(heads), 0, style, 5000);
    }

    private final static String DAILY_REPORT_DATE_PATTERN = "MM.dd.yyyy";

    private void mapToPageViewBodyList(Sheet sheet, List<PageViewDailyReport> pageViewDailyReports) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DAILY_REPORT_DATE_PATTERN);
        int rowNumber = 1;
        for (PageViewDailyReport pageViewDailyReport : pageViewDailyReports) {
            int cellNumber = 0;
            Row row = sheet.createRow(rowNumber++);
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getOrderSubmitDate().format(formatter));
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getVendorName());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getCategoryName());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getPackageName());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getInputQuantityType());
            // old product
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalStyleCount());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalAdditionalColorCount());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalAdditionalColorSetCount());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalMovieClipCount());
            // new product
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalBaseColorSetCount());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalModelSwatchCount());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalNewMovieClipCount());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalColorSwatchCount());

            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getPickDate() == null ? "" : pageViewDailyReport.getPickDate().format(formatter));
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getModelName());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getPromotionCode());
            row.createCell(cellNumber).setCellValue(pageViewDailyReport.getOrderComplete());
        }
    }

    private void mapToClicksBodyList(Sheet sheet, List<ClickStatDailyReport> clickStatDailyReports) {

        int rowNumber = 1;

        if(CollectionUtils.isEmpty(clickStatDailyReports)) {
            Row row = sheet.createRow(rowNumber);
            int cellNumber = 0;
            row.createCell(cellNumber++).setCellValue("Total");
            row.createCell(cellNumber++).setCellValue(0);
            row.createCell(cellNumber++).setCellValue(0);
            row.createCell(cellNumber++).setCellValue(0);
            row.createCell(cellNumber++).setCellValue(0);
            return;
        }

        for (ClickStatDailyReport clickStatDailyReport : clickStatDailyReports) {
            int cellNumber = 0;
            Row row = sheet.createRow(rowNumber++);
            row.createCell(cellNumber++).setCellValue(clickStatDailyReport.getWholeSalerCompanyName());
            row.createCell(cellNumber++).setCellValue(clickStatDailyReport.getClickCount(BannerType.BannerClick));
            row.createCell(cellNumber++).setCellValue(clickStatDailyReport.getClickCount(BannerType.OrderMenuClick));
            row.createCell(cellNumber++).setCellValue(clickStatDailyReport.getClickCount(BannerType.AdsDoNotShowAgain));
            row.createCell(cellNumber++).setCellValue(clickStatDailyReport.getClickCount(BannerType.AdsTryBottom));
        }

        Row row = sheet.createRow(rowNumber);
        int cellNumber = 0;
        row.createCell(cellNumber++).setCellValue("Total");
        row.createCell(cellNumber).setCellType(CellType.FORMULA);
        row.createCell(cellNumber++).setCellFormula("SUM(B2:B" + rowNumber + ")");

        row.createCell(cellNumber).setCellType(CellType.FORMULA);
        row.createCell(cellNumber++).setCellFormula("SUM(C2:C" + rowNumber + ")");

        row.createCell(cellNumber).setCellType(CellType.FORMULA);
        row.createCell(cellNumber++).setCellFormula("SUM(D2:D" + rowNumber + ")");

        row.createCell(cellNumber).setCellType(CellType.FORMULA);
        row.createCell(cellNumber++).setCellFormula("SUM(E2:E" + rowNumber + ")");
    }

    private void createBody(Sheet sheet, List<List<String>> bodyList) {
        int rowSize = bodyList.size();
        for (int i = 0; i < rowSize; i++) {
            createRow(sheet, bodyList.get(i), i + 1, null);
        }
    }

    private void mapToOrderDetailHeadList(Sheet sheet, CellStyle style) {

        String[] heads = {
                "Category",
                "Total order Number",
                "Total Amounts",
                "# of Style",
                "# of add'l set",
                "# of add'l image",
                "# of movie clips",
                "# of color set",
                "# of model swatch",
                "# of movie clips",
                "# of color swatch",
//                "New Customer",
                "# of Cancelled order"
        };

        createRow(sheet, Arrays.asList(heads), 0, style, 4000);
    }

    private void mapToClicksHeadList(Sheet sheet, CellStyle style) {

        String[] heads = {
                "Vendor name",
                "Banner Clicks",
                "Menu Clicks",
                "CloseForever",
                "Try out button"
        };

        createRow(sheet, Arrays.asList(heads), 0, style, 4000);
    }
}
