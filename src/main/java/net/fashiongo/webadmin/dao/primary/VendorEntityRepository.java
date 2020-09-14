package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.dao.primary.custom.VendorEntityRepositoryCustom;
import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorEntityRepository extends JpaRepository<VendorEntity, Long>, VendorEntityRepositoryCustom {
}
