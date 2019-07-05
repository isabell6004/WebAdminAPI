package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.AdPageEntity;
import net.fashiongo.webadmin.data.entity.primary.AdPageSpotEntity;
import net.fashiongo.webadmin.data.model.ad.*;
import net.fashiongo.webadmin.data.model.ad.response.*;
import net.fashiongo.webadmin.data.repository.primary.AdPageEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.AdPageSpotEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.AdProcedureRepository;
import net.fashiongo.webadmin.data.repository.primary.MapAdVendorItemEntityRepository;
import net.fashiongo.webadmin.model.pojo.ad.parameter.GetCategoryAdCalendarParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.GetCategoryAdDetailParameter;
import net.fashiongo.webadmin.model.pojo.ad.parameter.GetCategoryAdListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryAdItemForBidVendorParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RenewalAdService {

	private final AdPageSpotEntityRepository adPageSpotEntityRepository;

	private final AdPageEntityRepository adPageEntityRepository;

	private final AdProcedureRepository adProcedureRepository;

	private final MapAdVendorItemEntityRepository mapAdVendorItemEntityRepository;

	private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-dd");

	@Autowired
	public RenewalAdService(AdPageSpotEntityRepository adPageSpotEntityRepository, AdPageEntityRepository adPageEntityRepository, AdProcedureRepository adProcedureRepository, MapAdVendorItemEntityRepository mapAdVendorItemEntityRepository) {
		this.adPageSpotEntityRepository = adPageSpotEntityRepository;
		this.adPageEntityRepository = adPageEntityRepository;
		this.adProcedureRepository = adProcedureRepository;
		this.mapAdVendorItemEntityRepository = mapAdVendorItemEntityRepository;
	}

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

	public GetCategoryAdCalendarResponse getCategoryAdCalendar(GetCategoryAdCalendarParameter parameters) {
		String categoryDateValue = parameters.getCategoryDate();

		LocalDate categoryDate = StringUtils.isEmpty(categoryDateValue) ? LocalDate.now() : LocalDate.parse(categoryDateValue, DATE_TIME_FORMATTER);

		ResultGetCategoryAdCalendar2 resultGetCategoryAdCalendar2 = adProcedureRepository.up_wa_GetCategoryAdCalendar2(categoryDate);

		return GetCategoryAdCalendarResponse.builder()
				.collectionCategoryWithCounts(resultGetCategoryAdCalendar2.getCollectionCategoryWithCounts())
				.biddingList(resultGetCategoryAdCalendar2.getBiddingList())
				.curatedList(resultGetCategoryAdCalendar2.getCuratedList())
				.build();
	}

	public GetCategoryAdDetailResponse getCategoryAdDetail(GetCategoryAdDetailParameter parameters) {

		String categoryDateValue = parameters.getCategorydate();

		LocalDate categoryDate = StringUtils.isEmpty(categoryDateValue) ? LocalDate.now() : LocalDate.parse(categoryDateValue, DATE_TIME_FORMATTER);

		ResultGetCategoryAdDetail resultGetCategoryAdDetail = adProcedureRepository.up_wa_GetCategoryAdDetail(categoryDate, parameters.getSpotID());

		return GetCategoryAdDetailResponse.builder()
				.biddingList(resultGetCategoryAdDetail.getBidding2List())
				.curatedBestList(resultGetCategoryAdDetail.getCuratedBestList())
				.build();
	}

	public GetCategoryAdItemForBidVendorResponse getCategoryAdItemForBidVendor(GetCategoryAdItemForBidVendorParameter parameter) {
		List<CategoryAdItem> results = mapAdVendorItemEntityRepository.findCategoryAdItemOrderByListOrder(parameter.getAdID());

		return GetCategoryAdItemForBidVendorResponse.builder()
				.categoryAdItem(results)
				.build();
	}

	public GetCategoryAdListResponse getTest(GetCategoryAdListParameter parameters) {
		String categoryDateValue = parameters.getCategoryDate();

		LocalDate categoryDate = StringUtils.isEmpty(categoryDateValue) ? LocalDate.now() : LocalDate.parse(categoryDateValue, DATE_TIME_FORMATTER);

		ResultGetCategoryAdList resultGetCategoryAdList = adProcedureRepository.up_wa_GetCategoryAdList(categoryDate);

		return GetCategoryAdListResponse.builder()
				.categoryList(resultGetCategoryAdList.getCollectionCategoryWithCountsList())
				.biddingList(resultGetCategoryAdList.getBidding2List())
				.curatedBestList(resultGetCategoryAdList.getCuratedBestList())
				.build();
	}

}
