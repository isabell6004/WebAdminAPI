package net.fashiongo.webadmin.service.renewal;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.TrendReportContentsEntity;
import net.fashiongo.webadmin.data.entity.primary.TrendReportEntity;
import net.fashiongo.webadmin.data.model.kmm.KmmDetail;
import net.fashiongo.webadmin.data.repository.primary.TrendReportContentsEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.TrendReportEntityRepository;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Slf4j
@Service
public class RenewalKMMService {

    private final TrendReportEntityRepository trendReportEntityRepository;
    private final TrendReportContentsEntityRepository trendReportContentsEntityRepository;

    public RenewalKMMService(TrendReportEntityRepository trendReportEntityRepository, TrendReportContentsEntityRepository trendReportContentsEntityRepository) {
        this.trendReportEntityRepository = trendReportEntityRepository;
        this.trendReportContentsEntityRepository = trendReportContentsEntityRepository;
    }

    public KmmDetail getKmmDetail(Integer trendReportId) {
        KmmDetail kmmDetail = new KmmDetail();

        TrendReportEntity trendReport = trendReportEntityRepository.findOneByTrendReportIDAndCuratedType(trendReportId);

        if (trendReport != null && trendReport.getTrendReportID().equals(trendReportId)) {
            Boolean isSticky = trendReport.getSticky() != null ? trendReport.getSticky() : false;
            String content = "";
            Integer trendReportContentID = null;

            TrendReportContentsEntity trendReportContents = trendReportContentsEntityRepository.findOneByTrendReportID(trendReportId);

            if (trendReportContents != null && trendReportContents.getTrendReportID().equals(trendReportId) && trendReportContents.getContents() != null && trendReportContents.getContents().trim().length() != 0) {
                trendReportContentID = trendReportContents.getContentID();
                content = trendReportContents.getContents();
            } else {
                try {
                    String path = "~/htmlTemplates/KmmTemplate.html";
                    FileInputStream fileStream = new FileInputStream(path);

                    byte[] readBuffer = new byte[fileStream.available()];
                    while (fileStream.read(readBuffer) != -1) {}
                    content = new String(readBuffer);

                    fileStream.close();
                } catch (Exception e) { }
            }


            kmmDetail.setTrendReportId(trendReport.getTrendReportID());
            kmmDetail.setTitle(trendReport.getTitle());
            kmmDetail.setImage(trendReport.getImage());
            kmmDetail.setSquareImage(trendReport.getSquareImage());
            kmmDetail.setMiniImage(trendReport.getMiniImage());
            kmmDetail.setCuratedType(trendReport.getCuratedType());
            kmmDetail.setTrDescription(trendReport.getTrDescription());
            kmmDetail.setDateFrom(trendReport.getDateFrom());
            kmmDetail.setDateTo(trendReport.getDateTo());
            kmmDetail.setListOrder(trendReport.getListOrder());
            kmmDetail.setActive(trendReport.getActive());
            kmmDetail.setCreatedBy(trendReport.getCreatedBy());
            kmmDetail.setKmmImage1(trendReport.getKmmImage1());
            kmmDetail.setKmmImage2(trendReport.getKmmImage2());
            kmmDetail.setSticky(trendReport.getSticky());
            kmmDetail.setTrendReportContentId(trendReportContentID);
            kmmDetail.setContent(content);
        }

        return kmmDetail;
    }
}
