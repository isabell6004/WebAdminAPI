package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.RetailerRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetailerRatingEntityRepository extends JpaRepository<RetailerRatingEntity,Integer> , RetailerRatingEntityRepositoryCustom {
}
