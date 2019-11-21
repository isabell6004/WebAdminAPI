package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorDirNameChangeLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorDirNameChangeLogEntityRepository extends JpaRepository<VendorDirNameChangeLogEntity, Integer>, VendorDirNameChangeLogEntityRepositoryCustom {
}
