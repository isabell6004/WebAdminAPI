package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.dao.primary.custom.OrderRepositoryCustom;
import net.fashiongo.webadmin.data.entity.primary.Order;
import net.fashiongo.webadmin.model.primary.OrderPaymentStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>, OrderRepositoryCustom {
	int countByConsolidationId(Integer consolidationId);
	List<Order> findByConsolidationIdAndIsConsolidatedAndOrderStatusIdNotIn(Integer consolidationId, Boolean isConsolidated, List<Integer> orderStatusIds);
}
