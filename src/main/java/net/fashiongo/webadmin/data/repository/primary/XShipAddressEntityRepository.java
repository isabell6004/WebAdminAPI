package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.XShipAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XShipAddressEntityRepository extends JpaRepository<XShipAddressEntity,Integer>, XShipAddressEntityRepositoryCustom{
}
