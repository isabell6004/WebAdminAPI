package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.SimpleWholeSalerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface WholeSalerEntityRepository extends JpaRepository<SimpleWholeSalerEntity, Integer>, WholeSalerEntityRepositoryCustom {

    List<SimpleWholeSalerEntity> findByWholeSalerIdIn(Collection<Integer> wholesalerIds);
}
