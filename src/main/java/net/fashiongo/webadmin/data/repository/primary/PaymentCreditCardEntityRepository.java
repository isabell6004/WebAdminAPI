package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.PaymentCreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCreditCardEntityRepository extends JpaRepository<PaymentCreditCardEntity, Integer>, PaymentCreditCardEntityRepositoryCustom {
}
