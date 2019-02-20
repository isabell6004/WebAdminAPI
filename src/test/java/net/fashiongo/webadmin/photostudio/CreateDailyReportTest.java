package net.fashiongo.webadmin.photostudio;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;

/**
 * Created by nhnent on 2019. 1. 31..
 */
public class CreateDailyReportTest {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private final static int FIRST_SHEET_INDEX = 0;
    private final static int ROW_ACCESS_WINDOW_SIZE = 10;

    @Test
    public void createDailyReport() {

        logger.debug("create a daily report of photo studio to excel file.");

        //CheckOutDate	WholeSalerID，WholeSalerCompanyName	CategoryID	PackageID	IsByStyle					PhotoshootDate	BookingID	DiscountID
        InputStream templateFile = this.getClass().getResourceAsStream("/template/daily_report_photostudio_template.xlsx");
        assertNotNull(templateFile);


        try (
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(templateFile);
                Workbook sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook, ROW_ACCESS_WINDOW_SIZE);
                OutputStream outputStream = new FileOutputStream("/Users/nhnent/Desktop/tt.xlsx");
        ) {
            Sheet originSheet = xssfWorkbook.getSheetAt(FIRST_SHEET_INDEX);
            int rowNo = originSheet.getLastRowNum();

            logger.debug("row number : {}", rowNo);


            Sheet sheet = sxssfWorkbook.getSheetAt(FIRST_SHEET_INDEX);

            int i = 0;
            for(; i < 10 ; i++) {
                Row row = sheet.createRow(++rowNo);

                Cell cell = row.createCell(0);
                cell.setCellValue(1);
                cell = row.createCell(1);
                cell.setCellValue(2);
                cell = row.createCell(2);
                cell.setCellValue(3);
            }

            try {
                ((SXSSFSheet) sheet).flushRows(i);
            } catch (IOException e) {
                fail();
            }
//            response.setContentType("application/msexcel");
//            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", URLEncoder.encode(downloadFileName,"UTF-8")));
            sxssfWorkbook.write(outputStream);
//            sxssfWorkbook.dispose();

        } catch (IOException e) {
            fail();
        } finally {

        }

    }


//    public void writeWorkbook(String fileName){
//        long start = System.currentTimeMillis();
//        try {
//            s = wb.createSheet("sample Sheet");
//
//            int i=0;
//            int n=-1;
//
//            while ( n++ < 10 ) {
//
//                for(i=n*10000;i<(n+1)*10000;i++){
//                    setCellValue(i,0,"Test_col"+i);
//                    setCellValue(i,1,"Test_col_"+i);
//                    setCellValue(i,2,"Test_col_"+i);
//                    setCellValue(i,3,"Test_col_"+i);
//                    setCellValue(i,4,"Test_col_"+i);
//                }
//
//
//
//                // 메모리 flush
//
//                ((SXSSFSheet)s).flushRows(10000); // retain 100 last rows and flush all others
//
//            }
//
//            // 파일생성
//
//            wb.write(new FileOutputStream(fileName));
//
//
//        }catch(Exception e){
//            e.printStackTrace();
//            System.err.println(e.getMessage());
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("writeWorkbook : "+(end-start));
//    }
//
//
//    public Row getRow(int i){
//        Row r = s.getRow(i);
//        if(r==null)
//            r = s.createRow(i);
//        return r;
//    }
//
//    public Cell getCell(int row,int cell){
//        Row r = getRow(row);
//        Cell c = r.getCell(cell);
//        if(c==null)
//            c = r.createCell(cell);
//        return c;
//    }
//
//    public void setCellValue(int row, int cell, String cellvalue){
//        Cell c = getCell(row,cell);
//        c.setCellValue(cellvalue);
//    }
//
//    }
}
