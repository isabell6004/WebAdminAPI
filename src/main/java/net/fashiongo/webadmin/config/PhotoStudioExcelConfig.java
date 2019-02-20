package net.fashiongo.webadmin.config;

import net.fashiongo.webadmin.controller.view.AbstractPhotoStudioReportWriter;
import net.fashiongo.webadmin.controller.view.PhotoStudioDailyReportWriter;
import net.fashiongo.webadmin.controller.view.PhotoStudioReportViewWriterResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nhnent on 2019. 2. 4..
 */
@Configuration
public class PhotoStudioExcelConfig implements WebMvcConfigurer {

    @Autowired
    private PhotoStudioReportViewWriterResolver photoStudioReportView;

    @Autowired
    private PhotoStudioDailyReportWriter photoStudioDailyReportWriter;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(photoStudioReportView);
    }

    @Bean
    public Map<String, AbstractPhotoStudioReportWriter> getPhotoStudioReportWriterMap(){

        Map<String, AbstractPhotoStudioReportWriter> photoStudioReportWriterMap = new HashMap<>();
        photoStudioReportWriterMap.put("photoStudioDailyReportWriter", photoStudioDailyReportWriter);

        return photoStudioReportWriterMap;
    }
}
