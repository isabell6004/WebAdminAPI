package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.dao.primary.custom.GnbVendorGroupRepositoryCustom;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GnbVendorGroupRepository extends JpaRepository<GnbVendorGroupEntity, Integer>, GnbVendorGroupRepositoryCustom {
}
