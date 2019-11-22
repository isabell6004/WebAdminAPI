package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerSisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapWholeSalerSisterEntityRepository extends JpaRepository<MapWholeSalerSisterEntity, Integer>, MapWholeSalerSisterEntityRepositoryCustom {
}
