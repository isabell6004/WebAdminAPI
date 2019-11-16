package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.dao.primary.custom.OrderRepositoryCustom;
import net.fashiongo.webadmin.data.entity.primary.Order;
import net.fashiongo.webadmin.model.primary.OrderPaymentStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>, OrderRepositoryCustom {
	int countByConsolidationId(Integer consolidationId);
}
