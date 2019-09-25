package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QVendorContractEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorContractEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class VendorContractEntityRepositoryCustomImpl implements VendorContractEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public List<VendorContractEntity> findAllByWholeSalerId(Integer wholeSalerID) {
		JPAQuery<VendorContractEntity> jpaQuery = new JPAQuery<>(entityManager);
		QVendorContractEntity VC = new QVendorContractEntity("VC");

		jpaQuery.select(VC)
				.from(VC)
				.where(
						VC.wholeSalerID.eq(wholeSalerID)
				);

		return jpaQuery.fetch();
	}
}
