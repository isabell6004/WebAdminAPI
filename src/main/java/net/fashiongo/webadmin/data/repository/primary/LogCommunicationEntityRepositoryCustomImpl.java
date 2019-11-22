package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.LogCommunicationEntity;
import net.fashiongo.webadmin.data.entity.primary.QListCommunicationReasonEntity;
import net.fashiongo.webadmin.data.entity.primary.QLogCommunicationEntity;
import net.fashiongo.webadmin.data.entity.primary.QRetailerEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorCommunicationList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class LogCommunicationEntityRepositoryCustomImpl implements LogCommunicationEntityRepositoryCustom{

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<LogCommunicationEntity>
	findAllByRetailerIdOrderByModifiedOnDesc(Integer retailerId) {
		QLogCommunicationEntity L = new QLogCommunicationEntity("L");
		QListCommunicationReasonEntity LCR = new QListCommunicationReasonEntity("LCR");
		QRetailerEntity R = new QRetailerEntity("R");
		JPAQuery<LogCommunicationEntity> jpaQuery = new JPAQuery<>(entityManager);

		jpaQuery.select(L)
				.from(L)
				.innerJoin(L.listCommunicationReason,LCR).fetchJoin()
				.innerJoin(L.retailer,R).fetchJoin()
				.where(
						L.retailerID.eq(retailerId)
				)
				.orderBy(L.modifiedOn.desc());

		return jpaQuery.fetch();
	}

	@Override
	public LogCommunicationEntity findOneByCommunicationID(Integer communicationID) {
		QLogCommunicationEntity L = new QLogCommunicationEntity("L");
		JPAQuery<LogCommunicationEntity> query = new JPAQuery<>(entityManager);

		query.select(L).from(L).where(L.communicationID.eq(communicationID));

		return query.fetchFirst();
	}

	@Override
	public List<VendorCommunicationList> findAllByRetailerIDAndIsForVendor(Integer wholeSalerID) {
		QLogCommunicationEntity L = new QLogCommunicationEntity("L");
		JPAQuery<VendorCommunicationList> query = new JPAQuery<>(entityManager);

		query.select(Projections.constructor(VendorCommunicationList.class,
				L.communicationID,
				L.notes,
				L.createdOn,
				L.createdBy,
				L.reasonID))
				.from(L)
				.where(L.retailerID.eq(wholeSalerID).and(L.isForVendor.eq(true)))
				.orderBy(L.communicationID.desc());

		return query.fetch();
	}
}
