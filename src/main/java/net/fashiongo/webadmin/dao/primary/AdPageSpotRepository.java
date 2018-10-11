package net.fashiongo.webadmin.dao.primary;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdPageSpot;

/**
 * @author Nayeon Kim
 */
public interface AdPageSpotRepository extends CrudRepository<AdPageSpot, Integer> {
	AdPageSpot findOneBySpotID(Integer spotID);
	List<AdPageSpot> findByPageID(int pageID);
	List<AdPageSpot> findByCategoryID(int categoryID);

	void deleteByPageID(int pageID);
	void deleteByCategoryID(int categoryID);

	List<AdPageSpot> findByActiveTrueAndBidEffectiveOnLessThanEqualAndPageIDNotAndPageIDOrderBySpotName(
			Date bidEffectiveOn, Integer pageIdNot, Integer pageId);
}