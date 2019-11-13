package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupMapEntity;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupMapId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GnbVendorGroupMapRepository extends JpaRepository<GnbVendorGroupMapEntity, GnbVendorGroupMapId> {
}
