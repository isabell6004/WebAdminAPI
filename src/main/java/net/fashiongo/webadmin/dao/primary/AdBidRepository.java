package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdBid;

public interface AdBidRepository extends CrudRepository<AdBid, Integer> {
	
	List<AdBid> findByBidSettingIdAndStatusId(Integer bidSettingId, Integer statusId);
	List<AdBid> findByBidIdInAndStatusId(List<Integer> bidIdList, Integer statusId);
	List<AdBid> findByBidSettingIdAndStatusIdOrderByBidAmountDescBiddedOnAsc(Integer bidSettingId, Integer statusId);
	AdBid findFirstByBidSettingIdAndStatusIdOrderByBidAmountDescBiddedOnAsc(Integer bidSettingId, Integer statusId);

}
