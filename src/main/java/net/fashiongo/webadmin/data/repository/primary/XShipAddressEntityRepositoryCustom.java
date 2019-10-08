package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.XShipAddressEntity;

import java.util.List;

public interface XShipAddressEntityRepositoryCustom {

	List<XShipAddressEntity> findAllByCustID2(Integer custId);
}
