package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdPage;

/**
 * @author Nayeon Kim
 */
public interface AdPageRepository extends CrudRepository<AdPage, Integer> {
	AdPage findOneByPageID(Integer PageID);

	List<AdPage> findAllByOrderByPageIDDesc();
	
	// select top 1 order by pageid desc
	AdPage findTopByOrderByPageIDDesc(); // select top 1 * from ad_page order by pageid desc
}
