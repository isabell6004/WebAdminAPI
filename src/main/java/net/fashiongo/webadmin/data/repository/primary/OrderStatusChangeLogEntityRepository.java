package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.OrderStatusChangeLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusChangeLogEntityRepository extends JpaRepository<OrderStatusChangeLogEntity,Integer> {
}
