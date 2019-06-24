package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.dao.primary.custom.VendorBlockedEntityRepositoryCustom;
import net.fashiongo.webadmin.model.primary.VendorBlockedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorBlockedEntityRepository extends JpaRepository<VendorBlockedEntity, Integer>, VendorBlockedEntityRepositoryCustom {
}
