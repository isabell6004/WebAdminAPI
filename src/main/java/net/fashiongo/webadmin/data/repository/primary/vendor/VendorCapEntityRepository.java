package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorCapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorCapEntityRepository extends JpaRepository<VendorCapEntity, Integer>, VendorCapEntityRepositoryCustom {
}
