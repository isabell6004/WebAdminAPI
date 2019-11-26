package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.data.entity.primary.OrderStatusChangeLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusChangeLogRepository extends JpaRepository<OrderStatusChangeLogEntity, Integer> {
}
