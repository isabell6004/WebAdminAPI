package net.fashiongo.webadmin.data.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import net.fashiongo.webadmin.data.entity.primary.SEOEntity;

public interface SEOEntiryRepository extends JpaRepository<SEOEntity, Integer>, SEOEntiryRepositoryCustom {

}
