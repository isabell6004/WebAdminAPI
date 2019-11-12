package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.dao.primary.custom.OrderRepositoryCustom;
import net.fashiongo.webadmin.data.entity.primary.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>, OrderRepositoryCustom {
}
