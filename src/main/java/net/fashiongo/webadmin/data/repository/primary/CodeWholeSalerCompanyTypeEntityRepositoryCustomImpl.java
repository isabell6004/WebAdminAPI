package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.CodeWholeSalerCompanyTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodeWholeSalerCompanyTypeEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CodeWholeSalerCompanyTypeEntityRepositoryCustomImpl implements CodeWholeSalerCompanyTypeEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public List<CodeWholeSalerCompanyTypeEntity> findAllByActive(boolean isActive) {
		JPAQuery<CodeWholeSalerCompanyTypeEntity> jpaQuery = new JPAQuery<>(entityManager);
		QCodeWholeSalerCompanyTypeEntity CWC = new QCodeWholeSalerCompanyTypeEntity("CWC");

		return jpaQuery.select(CWC)
				.from(CWC)
				.where(
						CWC.active.eq(isActive)
				).fetch();
	}
}
