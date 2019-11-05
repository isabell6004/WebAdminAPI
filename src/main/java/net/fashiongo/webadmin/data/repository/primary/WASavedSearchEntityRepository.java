package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.WASavedSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WASavedSearchEntityRepository extends JpaRepository<WASavedSearchEntity,Integer>, WASavedSearchEntityRepositoryCustom {
}
