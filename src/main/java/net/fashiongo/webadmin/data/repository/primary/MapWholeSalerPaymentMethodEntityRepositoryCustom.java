package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerPaymentMethodEntity;

import java.util.List;

public interface MapWholeSalerPaymentMethodEntityRepositoryCustom {

	List<MapWholeSalerPaymentMethodEntity> findAllByWholeSalerIdWithCodePayment(int wholesalerId);
}
