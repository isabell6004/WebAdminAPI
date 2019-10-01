package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import org.springframework.data.domain.Page;

public interface VendorImageRequestEntityRepositoryCustom {
	Page<VendorImageRequestEntity> getVendorImageRequests(VendorImageRequestSelectParameter parameter);
}
