package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QVendorImageRequestEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.QWholesalerCompanyEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VendorImageRequestEntityRepositoryCustomImpl implements VendorImageRequestEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Page<VendorImageRequestEntity> getVendorImageRequests(VendorImageRequestSelectParameter parameter) {
		QVendorImageRequestEntity qRequest = QVendorImageRequestEntity.vendorImageRequestEntity;
		QWholesalerCompanyEntity qWholesaler = QWholesalerCompanyEntity.wholesalerCompanyEntity;

		QueryResults<VendorImageRequestEntity> results = new JPAQuery<>(entityManager)
				.select(qRequest)
				.from(qRequest)
				.leftJoin(qRequest.wholesaler, qWholesaler).fetchJoin()
				.where(getFilter(parameter))
				.orderBy(getOrder(parameter.getOrderingType()))
				.offset(parameter.getOffset())
				.limit(parameter.getPageSize())
				.fetchResults();
		long totalCount = results.getTotal();
		PageRequest pageRequest = PageRequest.of(parameter.getPageNumber() - 1, parameter.getPageSize());
		return PageableExecutionUtils.getPage(results.getResults(), pageRequest, () -> totalCount);
	}

	@Override
	public List<VendorImage> findByWholeSalerID(Integer wid) {
		QVendorImageRequestEntity qRequest = QVendorImageRequestEntity.vendorImageRequestEntity;
		QVendorImageRequestEntity qRequest_SUB = QVendorImageRequestEntity.vendorImageRequestEntity;

		JPAQuery<VendorImage> query = new JPAQuery<>(entityManager);
		JPAQuery<Integer> query2 = new JPAQuery<>(entityManager);

		List<Integer> imageRequestIDs= query2.select(qRequest_SUB.imageRequestId.max()).from(qRequest_SUB).where(qRequest_SUB.wholesalerId.eq(wid).and(qRequest_SUB.deletedOn.isNull())).groupBy(qRequest_SUB.vendorImageTypeId).fetch();

		query.select(Projections.constructor(VendorImage.class,
				qRequest.vendorImageTypeId,
				qRequest.originalFileName))
				.from(qRequest)
				.where(qRequest.imageRequestId.in(imageRequestIDs))
				.orderBy(qRequest.vendorImageTypeId.asc());

		return query.fetch();
	}

	@Override
	public VendorImageRequestEntity findOneByWholeSalerIDAndVendorImageTypeID(Integer wid, Integer type) {
		QVendorImageRequestEntity qRequest = QVendorImageRequestEntity.vendorImageRequestEntity;

		JPAQuery<VendorImageRequestEntity> query = new JPAQuery<>(entityManager);

		query.select(qRequest)
				.from(qRequest)
				.where(qRequest.wholesalerId.eq(wid).and(qRequest.vendorImageTypeId.eq(type)));

		return query.fetchFirst();
	}

	@Override
	public VendorImageRequestEntity findOneByWholeSalerIDAndVendorImageTypeIDAndActiveTrue(Integer wid, Integer type) {
		QVendorImageRequestEntity qRequest = QVendorImageRequestEntity.vendorImageRequestEntity;

		JPAQuery<VendorImageRequestEntity> query = new JPAQuery<>(entityManager);

		query.select(qRequest)
				.from(qRequest)
				.where(qRequest.wholesalerId.eq(wid).and(qRequest.vendorImageTypeId.eq(type)).and(qRequest.active.eq(true)));

		return query.fetchFirst();
	}

	private Integer findByCompanyName(String name) {
		QWholesalerCompanyEntity qWholesaler = QWholesalerCompanyEntity.wholesalerCompanyEntity;
		return new JPAQuery<Long>(entityManager)
				.select(qWholesaler.wholesalerId)
				.from(qWholesaler)
				.where(qWholesaler.companyName.eq(name))
				.fetchFirst();
	}

	private Predicate getFilter(VendorImageRequestSelectParameter parameter) {
		QVendorImageRequestEntity qRequest = QVendorImageRequestEntity.vendorImageRequestEntity;
		QWholesalerCompanyEntity qWholesaler = QWholesalerCompanyEntity.wholesalerCompanyEntity;
		BooleanExpression expression = null;

		if (parameter.getWholesalerName() != null) {
			Integer wholesalerId = findByCompanyName(parameter.getWholesalerName());
			if (wholesalerId != null) {
				expression = addAndExpression(null, qRequest.wholesalerId.eq(wholesalerId));
			} else {
				expression = addAndExpression(null, qWholesaler.companyName.like("%" + parameter.getWholesalerName() + "%"));
			}
		}

		if (parameter.getVendorImageTypeId() != null) {
			expression = addAndExpression(expression, qRequest.vendorImageTypeId.eq(parameter.getVendorImageTypeId()));
		}

		if (parameter.getSearchFrom() != null) {
			expression = addAndExpression(expression, qRequest.requestedOn.goe(parameter.getSearchFrom()));
		}

		if (parameter.getSearchTo() != null) {
			expression = addAndExpression(expression, qRequest.requestedOn.loe(parameter.getSearchTo()));
		}

		if (parameter.getShowDeleted() == null || !parameter.getShowDeleted()) {
			expression = addAndExpression(expression, qRequest.deletedOn.isNull());
		}

		if (parameter.getActive() != null) {
			expression = addAndExpression(expression, qRequest.active.eq(parameter.getActive()));
		}

		if (parameter.getApprovalType() != null) {
			switch (parameter.getApprovalType()) {
				case APPROVED:
					expression = addAndExpression(expression, qRequest.isApproved.isTrue());
					break;
				case DENIED:
					expression = addAndExpression(expression, qRequest.isApproved.isFalse());
					break;
				case PENDING:
					expression = addAndExpression(expression, qRequest.isApproved.isNull());
					break;
			}
		}

		return expression;
	}

	private BooleanExpression addAndExpression(BooleanExpression expression, BooleanExpression and) {
		return expression == null ? and : expression.and(and);
	}

	private OrderSpecifier[] getOrder(VendorImageRequestOrderingType orderingType) {
		QVendorImageRequestEntity qRequest = QVendorImageRequestEntity.vendorImageRequestEntity;
		QWholesalerCompanyEntity qWholesaler = QWholesalerCompanyEntity.wholesalerCompanyEntity;

		if (orderingType == null) {
			return new OrderSpecifier[]{
					qRequest.active.desc(),
					qRequest.isApproved.asc(),
					qRequest.vendorImageTypeId.asc()
			};
		}

		switch (orderingType) {
			case APPROVED_ON:
				return new OrderSpecifier[]{
						qRequest.decidedOn.desc(),
						qRequest.isApproved.asc(),
						qWholesaler.companyName.asc(),
						qRequest.vendorImageTypeId.asc()
				};
			case REQUESTED_ON:
				return new OrderSpecifier[]{
						qRequest.requestedOn.desc(),
						qRequest.isApproved.asc(),
						qWholesaler.companyName.asc(),
						qRequest.vendorImageTypeId.asc()
				};
			case COMPANY_NAME:
				return new OrderSpecifier[]{
						qWholesaler.companyName.asc(),
						qRequest.requestedOn.desc(),
						qRequest.isApproved.asc(),
						qRequest.vendorImageTypeId.asc()
				};
			case COMPANY_NAME_ASC:
				return new OrderSpecifier[]{qWholesaler.companyName.asc(), qRequest.imageRequestId.asc()};
			case COMPANY_NAME_DESC:
				return new OrderSpecifier[]{qWholesaler.companyName.desc(), qRequest.imageRequestId.asc()};
			case VENDOR_IMAGE_TYPE_ID_ASC:
				return new OrderSpecifier[]{qRequest.vendorImageTypeId.asc(), qRequest.imageRequestId.asc()};
			case VENDOR_IMAGE_TYPE_ID_DESC:
				return new OrderSpecifier[]{qRequest.vendorImageTypeId.desc(), qRequest.imageRequestId.asc()};
			case REQUESTED_ON_ASC:
				return new OrderSpecifier[]{qRequest.requestedOn.asc()};
			case REQUESTED_ON_DESC:
				return new OrderSpecifier[]{qRequest.requestedOn.desc()};
			case DECIDED_ON_ASC:
				return new OrderSpecifier[]{qRequest.decidedOn.asc()};
			case DECIDED_ON_DESC:
				return new OrderSpecifier[]{qRequest.decidedOn.desc()};
			case REJECT_REASON_ASC:
				return new OrderSpecifier[]{qRequest.rejectReason.asc(), qRequest.imageRequestId.asc()};
			case REJECT_REASON_DESC:
				return new OrderSpecifier[]{qRequest.rejectReason.desc(), qRequest.imageRequestId.asc()};
		}
		return new OrderSpecifier[]{
				qRequest.active.desc(),
				qRequest.isApproved.asc(),
				qRequest.vendorImageTypeId.asc()
		};
	}
}
