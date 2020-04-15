package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SimpleWholeSalerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WholeSalerEntityRepository extends JpaRepository<SimpleWholeSalerEntity, Integer>, WholeSalerEntityRepositoryCustom {

}
