package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.CodePatternEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodePatternEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CodePatternEntityRepositoryCustomImpl implements CodePatternEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Page<CodePatternEntity> findAllByPattenNameAndActiveOrderByPattenName(String pattenName, Boolean active, int pageNumber, int pageSize) {
		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;
		JPAQuery<CodePatternEntity> jpaQuery = new JPAQuery<>(entityManager);
		QCodePatternEntity CS = QCodePatternEntity.codePatternEntity;

		Expression<Integer> constant = Expressions.constant(1);
		BooleanExpression expression = Expressions.asNumber(1).eq(constant);

		if(StringUtils.hasLength(pattenName)) {
			expression = expression.and(CS.patternName.contains(pattenName));
		}

		if(active != null) {
			expression = expression.and(CS.active.eq(active));
		}

		jpaQuery.select(CS)
				.from(CS)
				.where(expression)
				.orderBy(CS.patternName.asc())
				.offset(offset)
				.limit(limit)
				.fetchResults();

		QueryResults<CodePatternEntity> codeLengthEntityQueryResults = jpaQuery.fetchResults();
		List<CodePatternEntity> results = codeLengthEntityQueryResults.getResults();
		long resultsTotal = codeLengthEntityQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}
}
