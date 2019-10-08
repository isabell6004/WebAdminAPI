package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.FraudNoticeEntity;

import java.util.List;

public interface FraudNoticeEntityRepositoryCustom {

	List<FraudNoticeEntity> findAllByRetailerIdOrderByCreatedOnDesc(Integer retailerId);
}
