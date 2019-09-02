package net.fashiongo.webadmin.data.entity.primary.show;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ShowPromotionWholesalerJoinRow {
	private Integer wholesalerId;
	private String companyName;
	private Integer mapId;
	private Integer rackCount;
	private BigDecimal fee;
	private BigDecimal commissionRate;
	private Integer planId;
	private String planName;
	private Integer showScheduleId;
	private LocalDateTime commissionEffectiveFrom;
}
