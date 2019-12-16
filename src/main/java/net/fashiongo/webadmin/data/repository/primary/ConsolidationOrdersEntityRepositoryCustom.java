package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.ConsolidationOrdersEntity;

import java.util.List;

public interface ConsolidationOrdersEntityRepositoryCustom {

	List<ConsolidationOrdersEntity> findByConsolidationId(int consolidationId);
}
