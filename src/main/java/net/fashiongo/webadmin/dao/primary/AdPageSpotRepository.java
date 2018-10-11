package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdPageSpot;

/**
 * @author Nayeon Kim
 */
public interface AdPageSpotRepository extends CrudRepository<AdPageSpot, Integer> {
	AdPageSpot findOneBySpotID(Integer spotID);

	List<AdPageSpot> findByPageID(int pageID);

	void deleteByPageID(int pageID);

}