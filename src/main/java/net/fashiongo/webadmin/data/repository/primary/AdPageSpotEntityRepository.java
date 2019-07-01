package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.AdPageSpotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdPageSpotEntityRepository extends JpaRepository<AdPageSpotEntity, Integer>, AdPageSpotEntityRepositoryCustom {
}
