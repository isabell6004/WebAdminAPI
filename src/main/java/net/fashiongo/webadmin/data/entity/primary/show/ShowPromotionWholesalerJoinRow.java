package net.fashiongo.webadmin.data.entity.primary.show;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ShowPromotionWholesalerJoinRow {
	private Long wholesalerId;
	private String companyName;
	private Integer mapId;
	private Integer rackCount;
	private BigDecimal fee;
	private BigDecimal commissionRate;
	private Integer planId;
	private String planName;
	private Integer showScheduleId;
	private LocalDateTime commissionEffectiveFrom;

	public ShowPromotionWholesalerJoinRow() {
	}

	@Builder
	public ShowPromotionWholesalerJoinRow(Long wholesalerId, String companyName, Integer mapId, Integer rackCount, BigDecimal fee, BigDecimal commissionRate, Integer planId, String planName, Integer showScheduleId, LocalDateTime commissionEffectiveFrom) {
		this.wholesalerId = wholesalerId;
		this.companyName = companyName;
		this.mapId = mapId;
		this.rackCount = rackCount;
		this.fee = fee;
		this.commissionRate = commissionRate;
		this.planId = planId;
		this.planName = planName;
		this.showScheduleId = showScheduleId;
		this.commissionEffectiveFrom = commissionEffectiveFrom;
	}
}
