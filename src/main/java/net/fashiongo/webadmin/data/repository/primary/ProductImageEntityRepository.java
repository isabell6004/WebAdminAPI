package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageEntityRepository extends JpaRepository<ProductImageEntity, Integer>, ProductImageEntityRepositoryCustom {
}
