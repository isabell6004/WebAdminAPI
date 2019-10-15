package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.buyer.InaccessibleVendor;
import org.springframework.data.domain.Page;

public interface WholeRetailerBlockEntityRepositoryCustom {

	Page<InaccessibleVendor> findAllByRetailerIdOrderByStartingDateDesc(int pageNumber, int pageSize, int retailerId);
}
