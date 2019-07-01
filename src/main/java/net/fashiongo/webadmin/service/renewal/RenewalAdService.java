package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.AdPageEntity;
import net.fashiongo.webadmin.data.entity.primary.AdPageSpotEntity;
import net.fashiongo.webadmin.data.repository.primary.AdPageEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.AdPageSpotEntityRepository;
import net.fashiongo.webadmin.data.model.ad.AdPage;
import net.fashiongo.webadmin.data.model.ad.AdPageSpot;
import net.fashiongo.webadmin.data.model.ad.response.GetAdPageSettingResponse;
import net.fashiongo.webadmin.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RenewalAdService extends ApiService {

    @Autowired
    private AdPageSpotEntityRepository adPageSpotEntityRepository;
    @Autowired
    private AdPageEntityRepository adPageEntityRepository;


    public GetAdPageSettingResponse getAdSetting(boolean showAll) {
        GetAdPageSettingResponse result = new GetAdPageSettingResponse();

        result.setAdPageSpotList(convertAdPageSpotEntityToResponse(adPageSpotEntityRepository.findAllOrderByPageIDAscAndSpotIDAsc()));
        if (showAll) {
            result.setAdPageList(convertAdPageEntityToResponse(adPageEntityRepository.findAllOrderByPageIDAsc()));
        } else {
            result.setAdPageList(convertAdPageEntityToResponse(adPageEntityRepository.findAllDistinctOrderByPageIDAsc()));
        }

        return result;
    }

    private List<AdPage> convertAdPageEntityToResponse(List<AdPageEntity> entities) {
        return entities.stream().map(entity -> new AdPage(
                entity.getPageId(),
                entity.getPageName(),
                entity.getPageUrl())
        ).collect(Collectors.toList());
    }

    private List<AdPageSpot> convertAdPageSpotEntityToResponse(List<AdPageSpotEntity> entities) {
        return entities.stream().map(entity -> new AdPageSpot(
                entity.getSpotId(),
                entity.getPageId(),
                entity.getAdPageEntity().getPageName(),
                entity.getCategoryId(),
                entity.getBodySizeId(),
                entity.getSpotName(),
                entity.getSpotDescription(),
                entity.getPrice1(),
                entity.getPrice2(),
                entity.getPrice3(),
                entity.getPrice4(),
                entity.getPrice5(),
                entity.getPrice6(),
                entity.getPrice7(),
                entity.isActive(),
                entity.isIncludeVendorCategory(),
                entity.getSpotInstanceCount(),
                entity.getBannerImage(),
                entity.getCreatedOn(),
                entity.getCreatedBy(),
                entity.getModifiedOn(),
                entity.getModifiedBy(),
                entity.getBidEffectiveOn(),
                entity.getMaxPurchasable(),
                entity.getSpotItemCount(),
                entity.isBannerAd())
        ).collect(Collectors.toList());
    }

}
