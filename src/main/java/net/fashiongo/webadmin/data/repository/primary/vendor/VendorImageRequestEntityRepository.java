package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import org.springframework.data.repository.CrudRepository;

public interface VendorImageRequestEntityRepository extends CrudRepository<VendorImageRequestEntity, Integer>, VendorImageRequestEntityRepositoryCustom {
}
