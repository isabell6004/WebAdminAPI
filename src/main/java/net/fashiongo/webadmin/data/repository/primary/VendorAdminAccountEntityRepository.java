package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorAdminAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorAdminAccountEntityRepository extends JpaRepository<VendorAdminAccountEntity, Integer>, VendorAdminAccountEntityRepositoryCustom {
}
