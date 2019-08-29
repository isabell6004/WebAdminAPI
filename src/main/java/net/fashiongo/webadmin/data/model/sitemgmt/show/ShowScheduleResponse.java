package net.fashiongo.webadmin.data.model.sitemgmt.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import net.fashiongo.webadmin.data.entity.primary.show.ShowScheduleWithPromotionEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDateTime;

@Value
@Builder
public class ShowScheduleResponse {
	@JsonProperty("ShowScheduleID")
	Integer showScheduleId;

	@JsonProperty("ShowID")
	int showId;

	@JsonProperty("BannerImage")
	String bannerImage;

	@JsonProperty("TitleImage")
	String titleImage;

	@JsonProperty("DateFrom")
	LocalDateTime dateFrom;

	@JsonProperty("DateTo")
	LocalDateTime dateTo;

	@JsonProperty("Active")
	boolean active;

	@JsonProperty("ListOrder")
	int listOrder;

	@JsonProperty("MobileImage")
	String mobileImage;

	@JsonProperty("rowno")
	long rowNumber;

	@JsonProperty("ShowName")
	String showName;

	@JsonProperty("Location")
	String location;

	@JsonProperty("Url")
	String url;

	@JsonProperty("VendorCount")
	Integer vendorCount;

	@JsonProperty("DeleteFlag")
	int deleteFlag;

	public static ShowScheduleResponse convert(ShowScheduleWithPromotionEntity entity, long rowNumber) {
		return ShowScheduleResponse.builder()
				.showScheduleId(entity.getShowScheduleId())
				.showId(entity.getShowId())
				.bannerImage(entity.getBannerImage())
				.titleImage(entity.getTitleImage())
				.dateFrom(entity.getDateFrom())
				.dateTo(entity.getDateTo())
				.active(entity.isActive())
				.listOrder(entity.getListOrder())
				.mobileImage(entity.getMobileImage())
				.showName(entity.getListShow().getShowName())
				.location(entity.getListShow().getLocation())
				.url(entity.getListShow().getUrl())
				.vendorCount(calVendorCount(entity))
				.deleteFlag(CollectionUtils.isEmpty(entity.getMapShowScheduleWholesalers()) ? 1 : 0)
				.rowNumber(rowNumber)
				.build();
	}

	private static int calVendorCount(ShowScheduleWithPromotionEntity entity) {
		if (CollectionUtils.isEmpty(entity.getShowSchedulePromotionPlans())) {
			return 0;
		}
		return entity.getShowSchedulePromotionPlans()
				.stream()
				.filter(p -> CollectionUtils.isNotEmpty(p.getMapShowSchedulePromotionPlanVendors()))
				.map(p -> p.getMapShowSchedulePromotionPlanVendors().size())
				.reduce((a, b) -> a + b)
				.orElse(0);
	}
}
