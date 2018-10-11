package net.fashiongo.webadmin.dao.primary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdPageSpot;

/**
 * @author Nayeon Kim
 */
public interface AdPageSpotRepository extends CrudRepository<AdPageSpot,Integer> {
	AdPageSpot findOneBySpotID(Integer spotID);
	
	/*
	 * var d = _Fg_v3Db.Ad_PageSpots
                .Where(x => x.Active.Equals(true) && x.BidEffectiveOn <= DateTime.Today && x.PageID != 0 && x.PageID == pageId)
                .OrderBy(x => x.SpotName).Select(x => new { x.SpotID, x.SpotName, x.SpotInstanceCount }).ToList();
	 */
	List<AdPageSpot> findByActiveTrueAndBidEffectiveOnLessThanEqualAndPageIDNotAndPageIDOrderBySpotName(LocalDateTime bidEffectiveOn, Integer pageIdNot, Integer pageId);
}