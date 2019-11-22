package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerPaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapWholeSalerPaymentMethodEntityRepository extends JpaRepository<MapWholeSalerPaymentMethodEntity,Integer>, MapWholeSalerPaymentMethodEntityRepositoryCustom {
}
