package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.ConsolidationShipMethodEntity;

import java.util.Optional;

public interface ConsolidationShipMethodEntityRepositoryCustom {

	Optional<ConsolidationShipMethodEntity> findOneByIdAndActive(int consolidationShipMethodId, boolean isActive);
}
