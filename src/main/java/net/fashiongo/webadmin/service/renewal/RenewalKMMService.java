package net.fashiongo.webadmin.service.renewal;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.TrendReportContentsEntity;
import net.fashiongo.webadmin.data.entity.primary.TrendReportEntity;
import net.fashiongo.webadmin.data.entity.primary.TrendReportMapEntity;
import net.fashiongo.webadmin.data.model.kmm.KmmCandidateItems;
import net.fashiongo.webadmin.data.model.kmm.KmmDetail;
import net.fashiongo.webadmin.data.model.kmm.KmmSavePayload;
import net.fashiongo.webadmin.data.model.kmm.KmmTrendReport;
import net.fashiongo.webadmin.data.repository.primary.TrendReportContentsEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.TrendReportEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.TrendReportMapEntityRepository;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RenewalKMMService {

    private final TrendReportEntityRepository trendReportEntityRepository;
    private final TrendReportContentsEntityRepository trendReportContentsEntityRepository;
    private final TrendReportMapEntityRepository trendReportMapEntityRepository;

    @Autowired
    public RenewalKMMService(TrendReportEntityRepository trendReportEntityRepository, TrendReportContentsEntityRepository trendReportContentsEntityRepository, TrendReportMapEntityRepository trendReportMapEntityRepository) {
        this.trendReportEntityRepository = trendReportEntityRepository;
        this.trendReportContentsEntityRepository = trendReportContentsEntityRepository;
        this.trendReportMapEntityRepository = trendReportMapEntityRepository;
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

    public List<KmmCandidateItems> getKmmCandidateItems(Integer trendReportId) {
        List<KmmCandidateItems> trendReportItems = trendReportEntityRepository.up_wa_GetTrendReportProductType2(trendReportId);

        return trendReportItems;
    }

    public List<KmmCandidateItems> getKmmSelectedItems(Integer trendReportId) {
        List<KmmCandidateItems> trendReportItems = trendReportEntityRepository.up_wa_GetTrendReportProductType1(trendReportId);

        return trendReportItems;
    }

    @Transactional
    public Boolean saveKmm(KmmSavePayload payloadData) {
        int trendReportId = 0;
        Boolean success = true;

        KmmTrendReport trendReport = payloadData.getTrendReport();
        String content = payloadData.getContents();
        List<Integer> selectedItems = payloadData.getSelectedItems();

        int _trendReportId = trendReport.getTrendReportId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        try {
            TrendReportEntity trendReportEntity = trendReportEntityRepository.findById(_trendReportId).orElse(null);

            if (trendReportEntity != null) {
                trendReportEntity.setTitle(trendReport.getTitle());
                trendReportEntity.setTrDescription(trendReport.getTrDescription());
                trendReportEntity.setDateFrom(LocalDateTime.parse(trendReport.getDateFrom() + " 00:00:00", formatter));
                trendReportEntity.setDateTo(LocalDateTime.parse(trendReport.getDateTo(), formatter));
                trendReportEntity.setListOrder(trendReport.getListOrder());
                trendReportEntity.setActive(trendReport.getRActive());
                trendReportEntity.setImage(trendReport.getAttFile());
                trendReportEntity.setMiniImage(trendReport.getMiniImage());
                trendReportEntity.setSquareImage(trendReport.getSquareImage());
                trendReportEntity.setKmmImage1(trendReport.getKmmImage1());
                trendReportEntity.setKmmImage2(trendReport.getKmmImage2());
                trendReportEntity.setModifiedOn(LocalDateTime.now());
                trendReportEntity.setModifiedBy(Utility.getUsername());

                trendReportEntityRepository.save(trendReportEntity);
            }

            TrendReportContentsEntity trendReportContentsEntity = trendReportContentsEntityRepository.findOneByTrendReportID(_trendReportId);
            if(trendReportContentsEntity == null) {
                trendReportContentsEntity = new TrendReportContentsEntity();
                trendReportContentsEntity.setCreatedOn(LocalDateTime.now());
                trendReportContentsEntity.setCreatedBy(Utility.getUsername());
            }

            trendReportContentsEntity.setTrendReportID(_trendReportId);
            trendReportContentsEntity.setContents(content);
            trendReportContentsEntity.setModifiedOn(LocalDateTime.now());
            trendReportContentsEntity.setModifiedBy(Utility.getUsername());

            trendReportContentsEntityRepository.save(trendReportContentsEntity);

            List<TrendReportMapEntity> deleteTrendReportMap = trendReportMapEntityRepository.findAllByTrendReportID(_trendReportId);
            trendReportMapEntityRepository.deleteAll(deleteTrendReportMap);

            List<TrendReportMapEntity> deleteTrendReportMapChk = trendReportMapEntityRepository.findAllByTrendReportID(_trendReportId);

            List<TrendReportMapEntity> saveTrendReportMap = new ArrayList<>();
            for(int i = 0; i < selectedItems.size(); i++) {
                int productId = selectedItems.get(i);
                boolean dup = false;

                for (int j = 0; j < selectedItems.size(); j++) {
                    if (selectedItems.get(j) == productId && i != j) {
                        dup = true;
                        break;
                    }
                }
                if(dup) continue;

                TrendReportMapEntity _trendReportMap = new TrendReportMapEntity();
                _trendReportMap.setTrendReportID(_trendReportId);
                _trendReportMap.setProductID(productId);
                _trendReportMap.setSortNo(0);
                _trendReportMap.setCreatedOn(LocalDateTime.now());
                _trendReportMap.setCreatedBy(Utility.getUsername());

                saveTrendReportMap.add(_trendReportMap);
            }
            trendReportMapEntityRepository.saveAll(saveTrendReportMap);

            trendReportId = _trendReportId;
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            success = false;
        }

        return success;
    }
}
