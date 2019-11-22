package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.LogEmailSentEntity;
import net.fashiongo.webadmin.data.entity.primary.QListEmailTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.QLogEmailSentEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class LogEmailSentEntityRepositoryCustomImpl implements LogEmailSentEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public List<LogEmailSentEntity> findAllWithListEmailTypeByRetailerId(Integer retailerId) {
		QLogEmailSentEntity L = new QLogEmailSentEntity("L");
		QListEmailTypeEntity ET = new QListEmailTypeEntity("ET");
		JPAQuery<LogEmailSentEntity> jpaQuery = new JPAQuery<>(entityManager);

		jpaQuery.select(L)
				.from(L)
				.innerJoin(L.listEmailTypeEntity,ET).fetchJoin()
				.where(L.retailerID.eq(retailerId))
				.orderBy(L.sentOn.desc());

		return jpaQuery.fetch();
	}
}
