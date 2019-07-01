package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.AdPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdPageEntityRepository extends JpaRepository<AdPageEntity, Integer>, AdPageEntityRepositoryCustom {
}
