package net.fashiongo.webadmin.model.pojo.consolidation.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.model.primary.consolidation.ConsolidatedOrder;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@Builder
public class DropOffConsolidationOrderDto {
	private Integer orderId;
	private Integer orderStatusId;
	private Integer consolidationId;
	private String poNumber;
	private String vendorName;
	private String buyer;
	private String buyerName;
	private LocalDateTime dropOffTime;
	private String dropOffBy;
	private String receivedBy;
	private Integer itemQty;
	private Integer boxQty;
	private Integer bagQty;
	private String memo;
	
	public static DropOffConsolidationOrderDto build(ConsolidatedOrder order) {
		if(order == null) {
			return null;
		}
		return builder()
				.orderId(order.getOrderId())
				.orderStatusId(order.getOrderStatusId())
				.consolidationId(order.getConsolidationId())
				.poNumber(order.getPoNumber())
				.vendorName(order.getVendorName())
				.buyer(order.getBuyer())
				.buyerName(order.getFirstName() + " " + order.getLastName())
				.dropOffTime(order.getConsolidationOrder() != null ? order.getConsolidationOrder().getReceivedOn() : null)
				.dropOffBy(order.getConsolidationOrder() != null ? order.getConsolidationOrder().getDroppedBy() : null)
				.receivedBy(order.getConsolidationOrder() != null ? order.getConsolidationOrder().getReceivedBy() : null)
				.itemQty(order.getConsolidationOrder() != null ? order.getConsolidationOrder().getItemQty() : null)
				.boxQty(order.getConsolidationOrder() != null ? order.getConsolidationOrder().getBoxQty() : null)
				.bagQty(order.getConsolidationOrder() != null ? order.getConsolidationOrder().getBagQty() : null)
				.memo(order.getConsolidationOrder() != null ? order.getConsolidationOrder().getMemo() : null)
				.build();
	}
	
	public static List<DropOffConsolidationOrderDto> build(List<ConsolidatedOrder> orders) {
		if(CollectionUtils.isEmpty(orders)) {
			return Collections.emptyList();
		}
		List<DropOffConsolidationOrderDto> dropOffConsolidation = new ArrayList<>();
		for(ConsolidatedOrder order : orders) {
			dropOffConsolidation.add(DropOffConsolidationOrderDto.build(order));
		}
		return dropOffConsolidation;
	}
}
