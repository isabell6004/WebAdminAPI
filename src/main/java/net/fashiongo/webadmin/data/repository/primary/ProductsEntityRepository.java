package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsEntityRepository extends JpaRepository<ProductsEntity, Integer>, ProductsEntityRepositoryCustom {
}
