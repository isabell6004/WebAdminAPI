package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.FraudNoticeEntity;
import net.fashiongo.webadmin.data.entity.primary.QFraudNoticeEntity;
import net.fashiongo.webadmin.data.entity.primary.QRetailerEntity;
import net.fashiongo.webadmin.data.entity.primary.QSimpleWholeSalerEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class FraudNoticeEntityRepositoryCustomImpl implements FraudNoticeEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public List<FraudNoticeEntity> findAllByRetailerIdOrderByCreatedOnDesc(Integer retailerId) {
		QFraudNoticeEntity FN = new QFraudNoticeEntity("FN");
		QRetailerEntity R = new QRetailerEntity("R");
		QSimpleWholeSalerEntity W = new QSimpleWholeSalerEntity("W");
		JPAQuery<FraudNoticeEntity> jpaQuery = new JPAQuery<>(entityManager);

		jpaQuery.select(FN)
				.from(FN)
				.leftJoin(FN.wholeSaler,W).fetchJoin()
				.leftJoin(FN.retailer,R).fetchJoin()
				.where(
						FN.retailerID.eq(retailerId)
				)
				.orderBy(FN.createdOn.desc());

		return jpaQuery.fetch();
	}
}
