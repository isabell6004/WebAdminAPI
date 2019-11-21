package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.model.primary.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Integer> {
}
