package net.fashiongo.webadmin.data.repository.primary;

import java.util.Optional;

public interface MergeOrdersEntityRepositoryCustom {

	Optional<String> getMergeOrderWholesalerGuid(int orderId);
}
