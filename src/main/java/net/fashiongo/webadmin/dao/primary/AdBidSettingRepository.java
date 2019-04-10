package net.fashiongo.webadmin.dao.primary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdBidSetting;

public interface AdBidSettingRepository extends CrudRepository<AdBidSetting, Integer>, AdBidSettingCustom {
	
	public List<AdBidSetting> findBySpotIdAndFromDate(Integer spotId, LocalDateTime fromDate);

}
