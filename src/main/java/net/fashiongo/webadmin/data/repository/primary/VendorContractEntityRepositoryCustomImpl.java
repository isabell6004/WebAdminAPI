package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QVendorContractEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorContractEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorContractHistoryList;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

	@Override
	public List<VendorContractHistoryList> findContractHistoryListByWholeSalerID(Integer wholeSalerID) {
		JPAQuery<VendorContractHistoryList> query = new JPAQuery<>(entityManager);
		QVendorContractEntity VC = new QVendorContractEntity("VC");

		query.select(Projections.constructor(VendorContractHistoryList.class,
				VC.contractTypeID,
				VC.vendorContractID,
				VC.setupFee,
				VC.isSetupFeeWaived,
				VC.lastMonthServiceFee,
				VC.isLastMonthServiceFeeWaived,
				VC.monthlyFee,
				VC.photoPlanID,
				VC.useModelStyle,
				VC.commissionRate,
				VC.perorder,
				VC.fromContractDate,
				VC.toContractDate,
				VC.createdBy))
				.from(VC)
				.where(VC.wholeSalerID.eq(wholeSalerID))
				.orderBy(VC.vendorContractID.desc());

		return query.fetch();
	}

	@Override
	public VendorContractEntity findOneByWholeSalerID(Integer wholeSalerID) {
		QVendorContractEntity VC = new QVendorContractEntity("VC");
		JPAQuery<VendorContractEntity> query = new JPAQuery<>(entityManager);

		query.select(VC)
				.from(VC)
				.where(VC.wholeSalerID.eq(wholeSalerID));

		return query.fetchFirst();
	}

    @Override
    public VendorContractEntity findVendorContractByVendorIdAndOpenDate(Integer veondorId, LocalDateTime openDate) {

	    QVendorContractEntity vendorContractEntity = new QVendorContractEntity("vendorContractEntity");
	    JPAQuery<VendorContractEntity> query = new JPAQuery<>(entityManager);

        Timestamp openDateTime = Timestamp.valueOf(openDate);
	    BooleanExpression contractDateToIsNull = vendorContractEntity.toContractDate.isNull();
	    BooleanExpression between = vendorContractEntity.fromContractDate.loe(openDateTime).and(vendorContractEntity.toContractDate.goe(openDateTime));
	    BooleanExpression contractDateFromCondition = vendorContractEntity.fromContractDate.goe(openDateTime);

        query.select(vendorContractEntity)
                .from(vendorContractEntity)
                .where(vendorContractEntity.wholeSalerID.eq(veondorId).and(contractDateToIsNull.or(between).or(contractDateFromCondition)))
        ;

        return query.fetchFirst();
    }

    @Override
	public VendorContractEntity findOneByVendorContractID(Integer vendorContractID) {
		QVendorContractEntity VC = new QVendorContractEntity("VC");
		JPAQuery<VendorContractEntity> query = new JPAQuery<>(entityManager);

		query.select(VC)
				.from(VC)
				.where(VC.vendorContractID.eq(vendorContractID));

		return query.fetchFirst();
	}
}
