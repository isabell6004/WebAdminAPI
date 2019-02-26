package net.fashiongo.webadmin.controller.view;

import net.fashiongo.webadmin.model.photostudio.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    protected void createEcxelData(Workbook workbook, Map<String, Object> dataMap, HttpServletResponse response) {

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
            row.createCell(cellNumber++).setCellValue(orderDetailDailyReport.getOrderDetailStatistic() == null ? 0 : orderDetailDailyReport.getOrderDetailStatistic().getStyleQuentity());
            row.createCell(cellNumber++).setCellValue(orderDetailDailyReport.getOrderDetailStatistic() == null ? 0 : orderDetailDailyReport.getOrderDetailStatistic().getAdditionalColorSetQuentity());
            row.createCell(cellNumber++).setCellValue(orderDetailDailyReport.getOrderDetailStatistic() == null ? 0 : orderDetailDailyReport.getOrderDetailStatistic().getAdditionalColorQuentity());
            row.createCell(cellNumber++).setCellValue(orderDetailDailyReport.getOrderDetailStatistic() == null ? 0 : orderDetailDailyReport.getOrderDetailStatistic().getMovieClipsQuentity());
            row.createCell(cellNumber++).setCellValue(orderDetailDailyReport.getCanceledOrderStatistic() == null ? 0 : orderDetailDailyReport.getCanceledOrderStatistic().getOrderCount());
        }
    }

    private void mapToPageViewHeadList(Sheet sheet, CellStyle style) {
        String[] heads = {
                "order submit date",
                "Vendor name",
                "Category",
                "Package",
                "input quantity- Full/Style",
                "Total styles",
                "total add'l colors",
                "total Add'l Color set",
                "total movie Clip",
                "pick a date",
                "choose model",
                "Promo"};

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
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalStyleCount());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalAdditionalColorCount());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalAdditionalColorSetCount());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getTotalMovieClipCount());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getPickDate() == null ? "" : format.format(pageViewDailyReport.getPickDate()));
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getModelName());
            row.createCell(cellNumber++).setCellValue(pageViewDailyReport.getPromotionCode());
        }
    }

    private void mapToClicksBodyList(Sheet sheet, List<ClickStatDailyReport> clickStatDailyReports) {

        int rowNumber = 1;
        for (ClickStatDailyReport clickStatDailyReport : clickStatDailyReports) {
            int cellNumber = 0;
            Row row = sheet.createRow(rowNumber++);
            row.createCell(cellNumber++).setCellValue(clickStatDailyReport.getWholeSalerCompanyName());
            row.createCell(cellNumber++).setCellValue(clickStatDailyReport.getBannerClickCount());
//            row.createCell(cellNumber++).setCellValue(clickStatDailyReport.getMenuClickCount());
            row.createCell(cellNumber++).setCellValue(clickStatDailyReport.getCloseOnTopCount());
            row.createCell(cellNumber++).setCellValue(clickStatDailyReport.getCloseForeverCount());
            row.createCell(cellNumber++).setCellValue(clickStatDailyReport.getTryOutButtonCount());
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
//                "New Customer",
                "# of Cancelled order"
        };

        createRow(sheet, Arrays.asList(heads), 0, style, 4000);
    }

    private void mapToClicksHeadList(Sheet sheet, CellStyle style) {

        String[] heads = {
                "Vendor name",
                "bannerClicks",
                "MenuClicks	Close on Top",
                "CloseForever",
                "Try out button"
        };

        createRow(sheet, Arrays.asList(heads), 0, style, 4000);
    }
}
