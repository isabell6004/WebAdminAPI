package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.OrderPaymentStatusEntity;
import net.fashiongo.webadmin.data.entity.primary.QOrderPaymentStatusEntity;
import net.fashiongo.webadmin.model.primary.QOrderPaymentStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class OrderPaymentStatusEntityRepositoryCustomImpl implements OrderPaymentStatusEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Optional<OrderPaymentStatusEntity> findFirst(int referenceId, int isOrder, int paymentStatusId) {
		JPAQuery<OrderPaymentStatusEntity> jpaQuery = new JPAQuery<>(entityManager);
		QOrderPaymentStatusEntity OPS = QOrderPaymentStatusEntity.orderPaymentStatusEntity;

		return Optional.ofNullable(
				jpaQuery.select(OPS)
				.from(OPS)
				.where(
						OPS.referenceID.eq(referenceId)
								.and(OPS.isOrder.eq(isOrder))
								.and(OPS.paymentStatusID.eq(paymentStatusId))
				).limit(1)
				.fetchOne()
		);

	}
}
