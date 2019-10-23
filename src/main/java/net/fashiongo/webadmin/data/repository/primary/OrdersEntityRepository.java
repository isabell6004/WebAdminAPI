package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersEntityRepository extends JpaRepository<OrdersEntity,Integer>, OrdersEntityRepositoryCustom {
}
