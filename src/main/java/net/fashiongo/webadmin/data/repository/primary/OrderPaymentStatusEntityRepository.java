package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.OrderPaymentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPaymentStatusEntityRepository extends JpaRepository<OrderPaymentStatusEntity,Integer>, OrderPaymentStatusEntityRepositoryCustom {
}
