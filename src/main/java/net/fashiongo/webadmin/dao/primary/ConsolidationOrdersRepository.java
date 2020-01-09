package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.jpa.repository.JpaRepository;

import net.fashiongo.webadmin.model.primary.consolidation.ConsolidationOrders;

public interface ConsolidationOrdersRepository extends JpaRepository<ConsolidationOrders, Integer>{
	ConsolidationOrders findOneByOrderId(Integer orderId);
}
