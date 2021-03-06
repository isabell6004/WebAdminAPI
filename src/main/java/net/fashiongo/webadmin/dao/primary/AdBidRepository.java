package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdBid;

public interface AdBidRepository extends CrudRepository<AdBid, Integer> {
	
	List<AdBid> findByBidSettingIdAndStatusId(Integer bidSettingId, Integer statusId);
	List<AdBid> findByBidSettingIdAndWholeSalerId(Integer bidSettingId, Integer wholeSalerId);
	List<AdBid> findByBidIdInAndStatusId(List<Integer> bidIdList, Integer statusId);
	List<AdBid> findByBidSettingIdAndStatusIdOrderByBidAmountDescBiddedOnAscBidIdAsc(Integer bidSettingId, Integer statusId);
	AdBid findFirstByBidSettingIdAndStatusIdAndWholeSalerIdNotInOrderByBidAmountDescBiddedOnAscBidIdAsc(Integer bidSettingId, Integer statusId, List<Integer> wholeSalerId);

}
