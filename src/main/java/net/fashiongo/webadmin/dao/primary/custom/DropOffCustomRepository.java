package net.fashiongo.webadmin.dao.primary.custom;

import java.util.List;

import net.fashiongo.webadmin.model.primary.consolidation.ConsolidatedOrder;

public interface DropOffCustomRepository {
	List<ConsolidatedOrder> getDropOffConsolidationOrder(String poNumber);
	List<ConsolidatedOrder> getDropOffConsolidationReceipt(Integer orderId);
}
