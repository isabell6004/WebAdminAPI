package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapWholeSalerGroupEntityRepository extends JpaRepository<MapWholeSalerGroupEntity, Integer>, MapWholeSalerGroupEntityRepositoryCustom {
}
