package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.ConsolidationShipMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsolidationShipMethodEntityRepository extends JpaRepository<ConsolidationShipMethodEntity,Integer>,ConsolidationShipMethodEntityRepositoryCustom {
}
