package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QAspnetMembershipEntity;
import net.fashiongo.webadmin.data.entity.primary.QAspnetRolesEntity;
import net.fashiongo.webadmin.data.entity.primary.QAspnetUsersEntity;
import net.fashiongo.webadmin.data.entity.primary.QAspnetUsersInRolesEntity;
import net.fashiongo.webadmin.data.entity.primary.QSimpleWholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorAdminAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.QWholeSalerEntity;
import net.fashiongo.webadmin.data.model.vendor.Vendor;
import net.fashiongo.webadmin.data.model.vendor.VendorAdminAccount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class WholeSalerEntityRepositoryCustomImpl implements WholeSalerEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<Vendor> findAllByActiveTrueAndShopActiveTrueOrderByCompanyName() {
		JPAQuery<Vendor> jpaQuery = new JPAQuery<>(entityManager);
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;

		jpaQuery.select(
					Projections.constructor(
							Vendor.class
							, W.wholeSalerId
							, W.companyName
					)
				).from(W)
				.where(W.active.eq(true).and(W.shopActive.eq(true)))
				.orderBy(W.companyName.asc());

		return jpaQuery.fetch();
	}

	@Override
	public List<Vendor> findAllByOrderActiveOrderByCompanyNameAsc(boolean isOrderActive) {
		JPAQuery<Vendor> jpaQuery = new JPAQuery<>(entityManager);
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;

		jpaQuery.select(
				Projections.constructor(
						Vendor.class
						, W.wholeSalerId
						, W.companyName
				)
		).from(W)
				.where(W.orderActive.eq(isOrderActive))
				.orderBy(W.companyName.asc());

		return jpaQuery.fetch();
	}

	@Override
	public long countByDirNameAndNotWholeSalerID(Integer wholeSalerID, String dirName) {
		JPAQuery<Long> query = new JPAQuery<>(entityManager);
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;

		query.select(W.wholeSalerId.count()).from(W).where(W.dirName.eq(dirName).and(W.wholeSalerId.ne(wholeSalerID)));

		return query.fetchFirst();
	}

	@Override
	public List<VendorAdminAccount> findVendorAdminAccountList(Integer wholeSalerID) {
		QWholeSalerEntity W1 = new QWholeSalerEntity("W1");
		QAspnetUsersEntity AU = new QAspnetUsersEntity("AU");
		QAspnetMembershipEntity AM1 = new QAspnetMembershipEntity("AM1");

		QVendorAdminAccountEntity V = QVendorAdminAccountEntity.vendorAdminAccountEntity;
		QWholeSalerEntity W2 = new QWholeSalerEntity("W2");
		QAspnetUsersEntity U = new QAspnetUsersEntity("U");
		QAspnetUsersInRolesEntity UR = new QAspnetUsersInRolesEntity("UR");
		QAspnetRolesEntity R = new QAspnetRolesEntity("R");
		QAspnetMembershipEntity AM2 = new QAspnetMembershipEntity("AM2");

		StringExpression userType = Expressions.asString("WholeSaler");
		JPAQuery<VendorAdminAccount> query1 = new JPAQuery<>(entityManager);
		JPAQuery<VendorAdminAccount> query2 = new JPAQuery<>(entityManager);

		List<VendorAdminAccount> queryResult1 = query1.select(Projections.constructor(VendorAdminAccount.class,
				W1.startingDate.as("CreatedOn"),
				W1.userId.as("UserID"),
				W1.firstName.concat(" ").concat(W1.lastName).as("UserName"),
				userType.as("UserType"),
				AU.lastActivityDate.as("LastActivityDate")))
			.from(W1)
			.innerJoin(AU).on(AU.userName.eq(W1.userId))
			.innerJoin(AM1).on(AU.userId.eq(AM1.userId))
			.where(W1.wholeSalerID.eq(wholeSalerID)).fetch();

		List<VendorAdminAccount> queryResult2 = query2.select(Projections.constructor(VendorAdminAccount.class,
				V.createdOn.as("CreatedOn"),
				V.userName.as("UserID"),
				V.firstName.concat(" ").concat(V.lastName).as("UserName"),
				R.roleName.as("UserType"),
				U.lastActivityDate.as("LastActivityDate")))
			.from(V)
			.innerJoin(W2).on(V.wholeSalerID.eq(W2.wholeSalerID))
			.innerJoin(U).on(V.userName.eq(U.userName))
			.innerJoin(UR).on(U.userId.eq(UR.userId))
			.innerJoin(R).on(UR.roleId.eq(R.roleId))
			.innerJoin(AM2).on(U.userId.eq(AM2.userId))
			.where(W2.active.eq(true).and(W2.wholeSalerID.eq(wholeSalerID)))
		.fetch();

		queryResult1.addAll(queryResult2);


		return queryResult1;
	}
}
