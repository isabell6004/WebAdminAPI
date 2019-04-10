package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdBidLog;

import java.util.List;

public interface AdBidLogRepository extends CrudRepository<AdBidLog, Integer> {
    List<AdBidLog> findByBidSettingIdAndStatusIdAndOriginBidIdInAndWholeSalerIdNotIn(Integer bidSettingId, Integer statusId, List<Integer> originBidIdList, List<Integer> wholeSalerIdList);
}
