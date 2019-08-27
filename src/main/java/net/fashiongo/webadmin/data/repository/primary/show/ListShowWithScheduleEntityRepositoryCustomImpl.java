package net.fashiongo.webadmin.data.repository.primary.show;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.show.ListShowWithScheduleEntity;
import net.fashiongo.webadmin.data.entity.primary.show.QListShowWithScheduleEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ListShowWithScheduleEntityRepositoryCustomImpl implements ListShowWithScheduleEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Page<ListShowWithScheduleEntity> getShowList(ListShowSelectParameter param) {
		QListShowWithScheduleEntity qListShow = QListShowWithScheduleEntity.listShowWithScheduleEntity;

		long totalCount = new JPAQuery<>(entityManager).select(qListShow)
				.from(qListShow)
				.where(getFilter(param))
				.fetchCount();

		List<ListShowWithScheduleEntity> results = new JPAQuery<>(entityManager)
				.select(qListShow)
				.from(qListShow)
				.leftJoin(qListShow.showSchedule).fetchJoin()
				.where(getFilter(param))
				.orderBy(getOrder(param))
				.offset(param.getOffset())
				.limit(param.getPageSize())
				.fetch();

		PageRequest pageRequest = PageRequest.of(param.getPageNum() - 1, param.getPageSize());
		return PageableExecutionUtils.getPage(results, pageRequest, () -> totalCount);
	}

	private BooleanExpression getFilter(ListShowSelectParameter param) {
		QListShowWithScheduleEntity qListShow = QListShowWithScheduleEntity.listShowWithScheduleEntity;
		BooleanExpression expression = Expressions.asNumber(1).eq(1);
		if (StringUtils.isNotEmpty(param.getShowName())) {
			expression = expression.and(qListShow.showName.like("%" + param.getShowName() + "%"));
		}
		if (StringUtils.isNotEmpty(param.getLocation())) {
			expression = expression.and(qListShow.location.like("%" + param.getLocation() + "%"));
		}
		if (param.getActive() != null) {
			expression = expression.and(qListShow.active.eq(param.getActive()));
		}
		return expression;
	}

	private OrderSpecifier getOrder(ListShowSelectParameter param) {
		QListShowWithScheduleEntity qListShow = QListShowWithScheduleEntity.listShowWithScheduleEntity;
		if (StringUtils.isEmpty(param.getOrderBy())) {
			return qListShow.showId.desc();
		}

		switch (param.getOrderBy()) {
			case "ShowID desc":
				return qListShow.showId.desc();
			case "ShowID":
				return qListShow.showId.asc();
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
				return qListShow.active.desc();
			case "Active asc":
				return qListShow.active.asc();
			default:
				return qListShow.showId.desc();
		}
	}
}
