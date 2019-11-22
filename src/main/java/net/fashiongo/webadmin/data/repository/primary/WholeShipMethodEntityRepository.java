package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.WholeShipMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WholeShipMethodEntityRepository extends JpaRepository<WholeShipMethodEntity,Integer> , WholeShipMethodEntityRepositoryCustom{
}
