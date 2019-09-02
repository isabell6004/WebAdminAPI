package net.fashiongo.webadmin.data.model.sitemgmt.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import net.fashiongo.webadmin.data.entity.primary.show.ShowPromotionWholesalerJoinRow;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class ShowPromotionVendorResponse {
	@JsonProperty("MapID")
	private Integer mapId;

	@JsonProperty("PlanID")
	private int planId;

	@JsonProperty("WholeSalerID")
	private int wholesalerId;

	@JsonProperty("CommissionRate")
	private double commissionRate;

	@JsonProperty("RackCount")
	private int rackCount;

	@JsonProperty("Fee")
	private BigDecimal fee;

	@JsonProperty("ItemCountMax")
	private int itemCountMax;

	@JsonProperty("row")
	private long row;

	@JsonProperty("ShowScheduleID")
	private int showScheduleId;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("PlanName")
	private String planName;

	@JsonProperty("DeleteFlag")
	private int deleteFlag;

	@JsonProperty("CanceledBy")
	private String canceledBy;

	@JsonProperty("CancelDate")
	private LocalDateTime cancelDate;

	@JsonProperty("Active")
	private Boolean active;

	public static ShowPromotionVendorResponse convert(ShowPromotionWholesalerJoinRow row) {
		return ShowPromotionVendorResponse.builder()
				.mapId(row.getMapId())
				.planId(row.getPlanId())
				.wholesalerId(row.getWholesalerId())
				.commissionRate(row.getCommissionRate().doubleValue())
				.rackCount(row.getRackCount())
				.fee(row.getFee())
				.showScheduleId(row.getShowScheduleId())
				.companyName(row.getCompanyName())
				.planName(row.getPlanName())
				.deleteFlag(LocalDateTime.now().isBefore(row.getCommissionEffectiveFrom()) ? 1 : 0)
				.canceledBy(null)
				.cancelDate(null)
				.active(null)
				.row(0)
				.build();
	}
}
