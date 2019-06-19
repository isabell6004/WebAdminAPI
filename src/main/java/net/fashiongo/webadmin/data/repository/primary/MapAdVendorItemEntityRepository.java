package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.MapAdVendorItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapAdVendorItemEntityRepository extends JpaRepository<MapAdVendorItemEntity, Integer>, MapAdVendorItemEntityRepositoryCustom {
}
