package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.ProductsEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductsEntityRepository extends JpaRepository<ProductsEntity, Integer> {

    List<ProductsEntity> findByVendorCategoryEntityInAndActive(Collection<VendorCategoryEntity> vendorCategoryEntities, Boolean active);
}
