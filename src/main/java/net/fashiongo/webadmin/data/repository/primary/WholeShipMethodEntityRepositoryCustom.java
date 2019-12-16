package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.WholeShipMethodEntity;

import java.util.List;

public interface WholeShipMethodEntityRepositoryCustom {

	List<WholeShipMethodEntity> findAllByWholeSalerIdWithShipMethod(int wholeSalerId);

	WholeShipMethodEntity findFirstWithShipMethod(boolean isDefault,int wholeSalerId,boolean isShipMethodActive);
}
