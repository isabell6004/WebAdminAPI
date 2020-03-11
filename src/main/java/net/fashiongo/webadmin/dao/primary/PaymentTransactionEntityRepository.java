package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.data.entity.primary.PaymentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentTransactionEntityRepository extends JpaRepository<PaymentTransactionEntity, Integer>, PaymentTransactionEntityRepositoryCustom {
	List<PaymentTransactionEntity> findByReferenceIDAndReferenceTypeIDOrderByTransactionIDDesc(Integer referenceId, Integer referenceTypeId);
}
