package net.fashiongo.webadmin.data.repository.primary;

import java.util.List;

import org.springframework.data.domain.Page;

import net.fashiongo.webadmin.data.entity.primary.SEOEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.SEO;
import net.fashiongo.webadmin.data.model.sitemgmt.SEOTotal;

public interface SEOEntiryRepositoryCustom {

	Page<SEO> findAllBySeo(int pageNo, int pageSize);
	
	SEOEntity findOneByID(Integer siteSEOId);

	List<SEOEntity> findOneByIDAll(List<Integer> siteSEOIds);

}
