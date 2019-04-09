package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdBid;

public interface AdBidRepository extends CrudRepository<AdBid, Integer> {
	
	public List<AdBid> findByBidSettingIdAndStatusId(Integer bidSettingId, Integer statusId);

}
