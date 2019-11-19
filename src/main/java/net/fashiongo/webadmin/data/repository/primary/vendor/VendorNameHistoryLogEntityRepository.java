package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorNameHistoryLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorNameHistoryLogEntityRepository extends JpaRepository<VendorNameHistoryLogEntity, Integer>, VendorNameHistoryLogEntityRepositoryCustom {
}
