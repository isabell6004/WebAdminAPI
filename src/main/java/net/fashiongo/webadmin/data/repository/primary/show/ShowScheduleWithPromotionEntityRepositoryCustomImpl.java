package net.fashiongo.webadmin.data.repository.primary.show;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.show.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ShowScheduleWithPromotionEntityRepositoryCustomImpl implements ShowScheduleWithPromotionEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Page<ShowScheduleWithPromotionEntity> getShowSchedules(ScheduleSelectParameter param) {
		QShowScheduleWithPromotionEntity qSchedule = QShowScheduleWithPromotionEntity.showScheduleWithPromotionEntity;
		QListShowEntity qListShow = QListShowEntity.listShowEntity;
		QMapShowScheduleWholesalerEntity qMapScheduleWholesaler = QMapShowScheduleWholesalerEntity.mapShowScheduleWholesalerEntity;
		QShowSchedulePromotionPlanWithVendorEntity qSchedulePromotion = QShowSchedulePromotionPlanWithVendorEntity.showSchedulePromotionPlanWithVendorEntity;
		QMapShowSchedulePromotionPlanVendorEntity qMapPromotionVendor = QMapShowSchedulePromotionPlanVendorEntity.mapShowSchedulePromotionPlanVendorEntity;

		long totalCount = new JPAQuery<>(entityManager)
				.select(qSchedule)
				.from(qSchedule)
				.join(qSchedule.listShow).fetchJoin()
				.where(getFilter(param))
				.fetchCount();

		List<ShowScheduleWithPromotionEntity> results = new JPAQuery<>(entityManager)
				.select(qSchedule)
				.from(qSchedule)
				.join(qSchedule.listShow, qListShow).fetchJoin()
				.leftJoin(qSchedule.mapShowScheduleWholesalers, qMapScheduleWholesaler).fetchJoin()
				.leftJoin(qSchedule.showSchedulePromotionPlans, qSchedulePromotion).fetchJoin()
				.leftJoin(qSchedulePromotion.mapShowSchedulePromotionPlanVendors, qMapPromotionVendor).fetchJoin()
				.where(
						getFilter(param)
								.and(
										qSchedulePromotion.isNull()
												.or(qMapPromotionVendor.isNull())
												.or(qMapScheduleWholesaler.wholesalerId.eq(qMapPromotionVendor.wholesalerId))
								)
				)
				.orderBy(getScheduleOrder(param))
				.offset(param.getOffset())
				.limit(param.getPageSize())
				.fetch();

		PageRequest pageRequest = PageRequest.of(param.getPageNumber() - 1, param.getPageSize());
		return new PageImpl<>(results, pageRequest, totalCount);
	}

	private OrderSpecifier<?> getScheduleOrder(ScheduleSelectParameter param) {
		QShowScheduleWithPromotionEntity qSchedule = QShowScheduleWithPromotionEntity.showScheduleWithPromotionEntity;
		QListShowEntity qListShow = QListShowEntity.listShowEntity;

		if (StringUtils.isEmpty(param.getOrderBy())) {
			return qSchedule.showScheduleId.desc();
		}

		switch (param.getOrderBy()) {
			case "ShowScheduleID desc":
				return qSchedule.showScheduleId.desc();
			case "ShowScheduleID":
				return qSchedule.showScheduleId.asc();
			case "ShowID desc":
				return qSchedule.showId.desc();
			case "ShowID":
				return qSchedule.showId.asc();
			case "ShowName desc":
				return qListShow.showName.desc();
			case "ShowName asc":
				return qListShow.showName.asc();
			case "Location desc":
				return qListShow.location.desc();
			case "Location asc":
				return qListShow.location.asc();
			case "Url desc":
				return qListShow.url.desc();
			case "Url asc":
				return qListShow.url.asc();
			case "Active desc":
				return qSchedule.active.desc();
			case "Active asc":
				return qSchedule.active.asc();
			default:
				return qSchedule.showScheduleId.desc();
		}
	}

	private BooleanExpression getFilter(ScheduleSelectParameter param) {
		BooleanExpression showFilter = getShowFilter(param);
		return getScheduleFilter(showFilter, param);
	}

	private BooleanExpression getShowFilter(ScheduleSelectParameter param) {
		QListShowEntity qListShow = QListShowEntity.listShowEntity;
		BooleanExpression expression = Expressions.asNumber(1).eq(1);

		if (StringUtils.isNotEmpty(param.getShowName())) {
			expression = expression.and(qListShow.showName.like("%" + param.getShowName() + "%"));
		}
		if (StringUtils.isNotEmpty(param.getLocation())) {
			expression = expression.and(qListShow.location.like("%" + param.getLocation() + "%"));
		}

		return expression;
	}

	private BooleanExpression getScheduleFilter(BooleanExpression expression, ScheduleSelectParameter param) {
		QShowScheduleWithPromotionEntity qSchedule = QShowScheduleWithPromotionEntity.showScheduleWithPromotionEntity;

		if (param.getFromDate() != null) {
			expression = expression.and(qSchedule.dateFrom.goe(param.getFromDate()));
		}
		if (param.getToDate() != null) {
			expression = expression.and(qSchedule.dateFrom.loe(param.getToDate()));
		}
		if (param.getShowID() != null) {
			expression = expression.and(qSchedule.showId.eq(param.getShowID()));
		}
		if (param.getActive() != null) {
			expression = expression.and(qSchedule.active.eq(param.getActive()));
		}

		return expression;
	}
}
