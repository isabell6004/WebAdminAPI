package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QWASavedSearchEntity;
import net.fashiongo.webadmin.data.entity.primary.WASavedSearchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WASavedSearchEntityRepositoryCustomImpl implements  WASavedSearchEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Page<WASavedSearchEntity> up_wa_GetSavedSearch(Integer pageNum, Integer pageSize, String savedType, String type, String keyword, LocalDateTime startDate, LocalDateTime endDate, String orderBy) {
		long offset = (pageNum - 1) * pageSize;
		long limit = pageSize;
		JPAQuery<WASavedSearchEntity> jpaQuery = new JPAQuery<>(entityManager);
		QWASavedSearchEntity SS = QWASavedSearchEntity.wASavedSearchEntity;

		BooleanExpression expression = SS.active.eq(true);
		List<OrderSpecifier> orderSpecifierList = new ArrayList<>();

		if(StringUtils.isEmpty(orderBy)) {
			orderSpecifierList.add(SS.modifiedOn.desc());
			orderSpecifierList.add(SS.createdOn.desc());
		}

		if(StringUtils.hasLength(savedType) ) {
			expression = expression.and(SS.savedType.eq(savedType));
		}

		if(StringUtils.hasLength(keyword)) {
			switch (type.toLowerCase()) {
				case "groupid":
					StringPath path = Expressions.stringPath(SS, "SavedId");
					expression = expression.and(path.contains(keyword));
					break;
				case "groupname":
					expression = expression.and(SS.savedName.contains(keyword));
					break;
				case "createdby":
					expression = expression.and(SS.createdBy.contains(keyword));
					break;
				case "memo":
					expression = expression.and(SS.remark.contains(keyword));
					break;
			}
		}

		if(StringUtils.hasLength(type) && startDate != null && endDate != null) {
			expression = expression.and(SS.createdOn.between(Timestamp.valueOf(startDate),Timestamp.valueOf(endDate)));
		}

		jpaQuery.select(SS)
				.from(SS)
				.where(expression)
				.orderBy(orderSpecifierList.toArray(new OrderSpecifier[orderSpecifierList.size()]))
				.offset(offset)
				.limit(limit);

		QueryResults<WASavedSearchEntity> waSavedSearchEntityQueryResults = jpaQuery.fetchResults();
		long total = waSavedSearchEntityQueryResults.getTotal();
		List<WASavedSearchEntity> results = waSavedSearchEntityQueryResults.getResults();

		PageRequest request = PageRequest.of(pageNum - 1, pageSize);

		return PageableExecutionUtils.getPage(results,request,() -> total);
	}
}
