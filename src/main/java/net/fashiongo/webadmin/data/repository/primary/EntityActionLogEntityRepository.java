package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.EntityActionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityActionLogEntityRepository extends JpaRepository<EntityActionLogEntity, Integer>, EntityActionLogEntityRepositoryCustom {
}
