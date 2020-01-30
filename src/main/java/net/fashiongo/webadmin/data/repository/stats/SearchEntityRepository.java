package net.fashiongo.webadmin.data.repository.stats;

import net.fashiongo.webadmin.data.entity.stats.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchEntityRepository extends JpaRepository<SearchEntity, Integer>, SearchEntityRepositoryCustom {
}
