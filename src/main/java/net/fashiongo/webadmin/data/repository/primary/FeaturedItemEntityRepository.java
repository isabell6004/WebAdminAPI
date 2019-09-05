package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.FeaturedItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeaturedItemEntityRepository extends JpaRepository<FeaturedItemEntity, Integer>, FeaturedItemEntityRepositoryCustom {
}
