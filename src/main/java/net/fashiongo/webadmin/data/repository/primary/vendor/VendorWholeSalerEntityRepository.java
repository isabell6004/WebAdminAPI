package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.WholeSalerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorWholeSalerEntityRepository extends JpaRepository<WholeSalerEntity, Integer>, VendorWholeSalerEntityRepositoryCustom {
}
