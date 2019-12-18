package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.buyer.OrderHistory;
import net.fashiongo.webadmin.data.model.order.ConsolidationOrderSummary;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface OrdersEntityRepositoryCustom {

	Page<OrderHistory> up_wa_GetOrderHistoryMaster(int pageNum
			, int pageSize
			, Integer retailerID
			, String wholeCompanyName
			, Integer paymentStatusID
			, Integer orderStatusID
			, LocalDateTime dateFrom
			, LocalDateTime dateTo
			, String poNumber
			, String productName
			, String orderBy);

	ConsolidationOrderSummary summaryByConsolidationId(int consolidationId);

	boolean hasOrderByConsolidationIdAndIsConsolidate(int consolidationId,boolean isConsolidate);

	long countInvalidConsolidationOrder(Integer consolidationId);
}
