package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.buyer.StoreCardDetail;
import net.fashiongo.webadmin.data.model.buyer.StoreCardSummary;

import java.time.LocalDateTime;
import java.util.List;

public interface StoreCreditEntityRepositoryCustom {

	List<StoreCardSummary> findAllStoreCardSummary(int retailerId, String vendorName, String poNumber, LocalDateTime from, LocalDateTime to);

	List<StoreCardDetail> findAllStoreCardDetail(int retailerId, String vendorName, String poNumber, LocalDateTime from, LocalDateTime to);
}
