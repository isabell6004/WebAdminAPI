package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.AspnetMembershipEntity;
import net.fashiongo.webadmin.data.entity.primary.QAspnetMembershipEntity;
import net.fashiongo.webadmin.data.entity.primary.QAspnetUsersEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class AspnetMembershipEntityRepositoryCustomImpl implements AspnetMembershipEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public Optional<AspnetMembershipEntity> findByUserName(String userName) {

		JPAQuery<AspnetMembershipEntity> jpaQuery = new JPAQuery<>(entityManager);
		QAspnetMembershipEntity M = new QAspnetMembershipEntity("M");
		QAspnetUsersEntity AU = new QAspnetUsersEntity("AU");

		jpaQuery.select(M)
				.from(M)
				.innerJoin(M.aspnetUsers,AU).fetchJoin()
				.where(
					AU.userName.eq(userName)
				);

		return Optional.ofNullable(jpaQuery.fetchOne());
	}

	@Override
	public AspnetMembershipEntity findOneByWholeSalerGUID(String wholeaSalerGUID) {
		JPAQuery<AspnetMembershipEntity> query = new JPAQuery<>(entityManager);
		QAspnetMembershipEntity M = QAspnetMembershipEntity.aspnetMembershipEntity;

		query.select(M).from(M).where(M.userId.eq(wholeaSalerGUID));

		return query.fetchFirst();
	}

	@Override
	public AspnetMembershipEntity findOneByWholeSalerGUIDAndIsLockedOutTrue(String wholeaSalerGUID) {
		JPAQuery<AspnetMembershipEntity> query = new JPAQuery<>(entityManager);
		QAspnetMembershipEntity M = QAspnetMembershipEntity.aspnetMembershipEntity;

		query.select(M).from(M).where(M.userId.eq(wholeaSalerGUID).and(M.isLockedOut.eq(true)));

		return query.fetchFirst();
	}
}
