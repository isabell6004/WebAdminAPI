package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdBidSetting;

public interface AdBidSettingRepository extends CrudRepository<AdBidSetting, Integer>, AdBidSettingCustom {

}
