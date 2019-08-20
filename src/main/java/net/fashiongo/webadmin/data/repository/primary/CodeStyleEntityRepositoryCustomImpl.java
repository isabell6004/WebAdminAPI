package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.CodeStyleEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodeStyleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CodeStyleEntityRepositoryCustomImpl implements CodeStyleEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Page<CodeStyleEntity> findAllByStyleNameAndActiveOrderByStyleName(String styleName, Boolean active, int pageNumber, int pageSize) {
		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;
		JPAQuery<CodeStyleEntity> jpaQuery = new JPAQuery<>(entityManager);
		QCodeStyleEntity CS = QCodeStyleEntity.codeStyleEntity;

		Expression<Integer> constant = Expressions.constant(1);
		BooleanExpression expression = Expressions.asNumber(1).eq(constant);

		if(StringUtils.hasLength(styleName)) {
			expression = expression.and(CS.styleName.contains(styleName));
		}

		if(active != null) {
			expression = expression.and(CS.active.eq(active));
		}

		jpaQuery.select(CS)
				.from(CS)
				.where(expression)
				.orderBy(CS.styleName.asc())
				.offset(offset)
				.limit(limit);

		QueryResults<CodeStyleEntity> codeLengthEntityQueryResults = jpaQuery.fetchResults();
		List<CodeStyleEntity> results = codeLengthEntityQueryResults.getResults();
		long resultsTotal = codeLengthEntityQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}
}
