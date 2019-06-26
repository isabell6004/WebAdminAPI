package net.fashiongo.webadmin.data.jpa.repository.primary;

import net.fashiongo.webadmin.data.jpa.entity.primary.VendorBlockedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorBlockedEntityRepository extends JpaRepository<VendorBlockedEntity, Integer>, VendorBlockedEntityRepositoryCustom {
}
