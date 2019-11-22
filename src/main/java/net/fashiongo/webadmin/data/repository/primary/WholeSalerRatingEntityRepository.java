package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.WholeSalerRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WholeSalerRatingEntityRepository extends JpaRepository<WholeSalerRatingEntity,Integer>, WholeSalerRatingEntityRepositoryCustom {
}
