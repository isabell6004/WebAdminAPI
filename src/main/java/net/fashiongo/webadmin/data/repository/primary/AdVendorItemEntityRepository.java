package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.AdVendorItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdVendorItemEntityRepository extends JpaRepository<AdVendorItemEntity, Integer>, AdVendorItemEntityRepositoryCustom {
}
