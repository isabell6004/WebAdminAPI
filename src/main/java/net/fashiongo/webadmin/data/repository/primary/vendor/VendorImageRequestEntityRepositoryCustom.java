package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorImage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VendorImageRequestEntityRepositoryCustom {
	Page<VendorImageRequestEntity> getVendorImageRequests(VendorImageRequestSelectParameter parameter);

	List<VendorImage> findByWholeSalerID(Integer wid);
}
