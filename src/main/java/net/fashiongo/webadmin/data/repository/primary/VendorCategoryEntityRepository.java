package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.VendorCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorCategoryEntityRepository extends JpaRepository<VendorCategoryEntity, Integer>, VendorCategoryEntityRepositoryCustom {

    List<VendorCategoryEntity> findByWholeSalerIDAndActive(Integer wholesalerId, Boolean active);
}
