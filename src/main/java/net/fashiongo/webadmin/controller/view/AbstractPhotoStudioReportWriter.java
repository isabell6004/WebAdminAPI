package net.fashiongo.webadmin.controller.view;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by jinwoo on 2019. 2. 5..
 */
public abstract class AbstractPhotoStudioReportWriter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    abstract protected String getFileName(Map<String, Object> dataMap);

    public void create(Workbook workbook, Map<String, Object> dataMap, HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileName(dataMap) + "\"");
//        logger.info("data : {} ", dataMap.get("dailyReport"));
        createEcxelData(workbook, dataMap, response);
    }

    protected void createRow(Sheet sheet, List<String> cellList, int rowNum, CellStyle style) {
        createRow(sheet, cellList, rowNum, style, 0);
    }

    protected void createRow(Sheet sheet, List<String> cellList, int rowNum, CellStyle style, int fixedColumnWidth) {
        int size = cellList.size();
        Row row = sheet.createRow(rowNum);

        for (int i = 0; i < size; i++) {
            Cell cell = row.createCell(i);
            if (style != null) {
                cell.setCellStyle(style);
            }
            cell.setCellValue(cellList.get(i));
            if(fixedColumnWidth != 0) {
                sheet.setColumnWidth(i, fixedColumnWidth);
            }

        }
    }

    abstract protected void createEcxelData(Workbook workbook, Map<String, Object> model, HttpServletResponse response);

}
