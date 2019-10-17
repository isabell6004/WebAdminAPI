package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.CreditCardEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodeCreditCardTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.QCreditCardEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

public class CreditCardEntityRepositoryCustomImpl implements CreditCardEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<CreditCardEntity> findAllByRetailerIdWithCodeCreditCardType(int retailerId) {
		JPAQuery<CreditCardEntity> jpaQuery = new JPAQuery<>(entityManager);
		QCreditCardEntity CARD = QCreditCardEntity.creditCardEntity;
		QCodeCreditCardTypeEntity CC = QCodeCreditCardTypeEntity.codeCreditCardTypeEntity;
		LocalDateTime NOW = LocalDateTime.now();

		jpaQuery.select(CARD)
				.from(CARD)
				.innerJoin(CARD.codeCreditCardType,CC).fetchJoin()
				.where(
						CARD.retailerID.eq(retailerId)
						.and(CARD.active.eq(true))
						.and(CARD.expirationYear.goe(NOW.getYear())
								.or(ExpressionUtils.and(CARD.expirationYear.eq(NOW.getYear()), CARD.expirationMonth.goe(NOW.getMonth().getValue()))))
				);
		return jpaQuery.fetch();
	}
}
