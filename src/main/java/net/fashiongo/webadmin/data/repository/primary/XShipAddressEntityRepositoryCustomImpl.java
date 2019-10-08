package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QXShipAddressEntity;
import net.fashiongo.webadmin.data.entity.primary.XShipAddressEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class XShipAddressEntityRepositoryCustomImpl implements XShipAddressEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public List<XShipAddressEntity> findAllByCustID2(Integer custId) {
		QXShipAddressEntity XSA = new QXShipAddressEntity("XSA");
		JPAQuery<XShipAddressEntity> jpaQuery = new JPAQuery<>(entityManager);

		jpaQuery.select(XSA)
				.from(XSA)
				.where(
						XSA.custID2.eq(custId)
				);

		return jpaQuery.fetch();
	}
}
