package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.CodeFabricEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodeFabricEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.FabricInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CodeFabricEntityRepositoryCustomImpl implements CodeFabricEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Page<CodeFabricEntity> findAllByFabricNameAndActiveOrderByFabricName(String FabricName, Boolean active, int pageNumber, int pageSize) {
		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;
		JPAQuery<CodeFabricEntity> jpaQuery = new JPAQuery<>(entityManager);
		QCodeFabricEntity CL = QCodeFabricEntity.codeFabricEntity;

		Expression<Integer> constant = Expressions.constant(1);
		BooleanExpression expression = Expressions.asNumber(1).eq(constant);

		if(StringUtils.hasLength(FabricName)) {
			expression = expression.and(CL.fabricName.contains(FabricName));
		}

		if(active != null) {
			expression = expression.and(CL.active.eq(active));
		}

		jpaQuery.select(CL)
				.from(CL)
				.where(expression)
				.orderBy(CL.fabricName.asc())
				.offset(offset)
				.limit(limit);

		QueryResults<CodeFabricEntity> codeLengthEntityQueryResults = jpaQuery.fetchResults();
		List<CodeFabricEntity> results = codeLengthEntityQueryResults.getResults();
		long resultsTotal = codeLengthEntityQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}

	@Override
	public List<FabricInfo> findAllOrderByFabricName() {
		JPAQuery<FabricInfo> query = new JPAQuery<>(entityManager);
		QCodeFabricEntity fabricEntity = QCodeFabricEntity.codeFabricEntity;

		query.select(Projections.constructor(FabricInfo.class,
				fabricEntity.fabricId,
				fabricEntity.fabricName,
				fabricEntity.active))
				.from(fabricEntity)
				.orderBy(fabricEntity.fabricName.asc());

		return query.fetch();
	}
}
