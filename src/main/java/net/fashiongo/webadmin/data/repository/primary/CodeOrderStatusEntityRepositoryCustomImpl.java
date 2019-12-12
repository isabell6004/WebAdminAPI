package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QCodeOrderStatusEntity;
import net.fashiongo.webadmin.data.model.common.CodeOrderStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CodeOrderStatusEntityRepositoryCustomImpl implements CodeOrderStatusEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<CodeOrderStatus> finAllConsolidationOrders() {

		JPAQuery<CodeOrderStatus> jpaQuery = new JPAQuery<>(entityManager);
		QCodeOrderStatusEntity CODE = QCodeOrderStatusEntity.codeOrderStatusEntity;

		return jpaQuery.select(
				Projections.constructor(
						CodeOrderStatus.class
						, CODE.orderStatusID
						, CODE.orderStatusName
						, CODE.name2Vendor
						, CODE.name2Retailer
				)
		).from(CODE)
				.where(
						CODE.orderStatusID.ne(3).and(
								CODE.orderStatusID.ne(6)
						).and(
								CODE.orderStatusID.ne(7)
						)
				).fetch();
	}
}
