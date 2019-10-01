package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.CodeCountryEntity;
import net.fashiongo.webadmin.data.entity.primary.CodeWholeSalerCompanyTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodeCountryEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CodeCountryEntityRepositoryCustomImpl implements CodeCountryEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Transactional(transactionManager = "primaryTransactionManager")
	public List<CodeCountryEntity> findAllByActive(boolean isActive) {
		JPAQuery<CodeWholeSalerCompanyTypeEntity> jpaQuery = new JPAQuery<>(entityManager);
		QCodeCountryEntity CC = new QCodeCountryEntity("CC");

		return jpaQuery.select(CC)
				.from(CC)
				.where(
						CC.active.eq(isActive)
				).fetch();
	}
}
