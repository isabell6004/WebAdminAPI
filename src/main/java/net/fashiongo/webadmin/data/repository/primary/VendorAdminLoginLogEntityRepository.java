package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorAdminLoginLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorAdminLoginLogEntityRepository extends JpaRepository<VendorAdminLoginLogEntity, Integer>, VendorAdminLoginLogEntityRepositoryCustom {
}
