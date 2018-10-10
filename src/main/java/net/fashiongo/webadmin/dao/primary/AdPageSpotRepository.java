package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdPageSpot;

/**
 * @author Nayeon Kim
 */
public interface AdPageSpotRepository extends CrudRepository<AdPageSpot,Integer> {
	AdPageSpot findOneBySpotID(Integer spotID);
}