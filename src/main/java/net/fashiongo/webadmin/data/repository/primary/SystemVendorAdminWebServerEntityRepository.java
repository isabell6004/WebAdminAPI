package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SystemVendorAdminWebServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemVendorAdminWebServerEntityRepository extends JpaRepository<SystemVendorAdminWebServerEntity, Integer>, SystemVendorAdminWebServerEntityRepositoryCustom {
}
