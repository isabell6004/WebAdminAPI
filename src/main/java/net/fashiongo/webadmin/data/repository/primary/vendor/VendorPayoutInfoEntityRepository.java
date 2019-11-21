package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorPayoutInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorPayoutInfoEntityRepository extends JpaRepository<VendorPayoutInfoEntity, Integer>, VendorPayoutInfoEntityRepositoryCustom {
}
