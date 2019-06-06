package net.fashiongo.webadmin.controller.view;

import net.fashiongo.webadmin.model.photostudio.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

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

        SimpleDateFormat format = new SimpleDateFormat(DAILY_REPORT_DATE_PATTERN);

        int rowNumber = 1;
        for (PageViewDailyReport pageViewDailyReport : pageViewDailyReports) {
            int cellNumber = 0;
            Row row = sheet.createRow(rowNumber++);
            row.createCell(cellNumber++).setCellValue(format.format(pageViewDailyReport.getOrderSubmitDate()));
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

            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getPickDate() == null ? "" : format.format(pageViewDailyReport.getPickDate()));
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
