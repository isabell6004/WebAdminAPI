package net.fashiongo.webadmin.controller.view;

import net.fashiongo.webadmin.model.photostudio.ExcelReport;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by nhnent on 2019. 2. 4..
 */
@Component
public class PhotoStudioReportViewWriterResolver extends AbstractXlsxStreamingView {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Map<String, AbstractPhotoStudioReportWriter> photoStudioReportWriterMap;

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExcelReport excelReport = (ExcelReport) model.get("excelReport");
        logger.debug("== selected writer : {}", excelReport.getWriterBeanName());

        if(StringUtils.isEmpty(excelReport.getWriterBeanName())){
            throw new IllegalArgumentException();
        }

        photoStudioReportWriterMap.get(excelReport.getWriterBeanName()).create(workbook, model, response);
    }
}
