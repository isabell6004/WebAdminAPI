package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.CodeLengthEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodeLengthEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CodeLengthEntityRepositoryCustomImpl implements CodeLengthEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public Page<CodeLengthEntity> findAllByLengthNameAndActiveOrderByLengthName(String lengthName,Boolean active,int pageNumber, int pageSize) {
		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;
		JPAQuery<CodeLengthEntity> jpaQuery = new JPAQuery<>(entityManager);
		QCodeLengthEntity CL = QCodeLengthEntity.codeLengthEntity;

		Expression<Integer> constant = Expressions.constant(1);
		BooleanExpression expression = Expressions.asNumber(1).eq(constant);

		if(StringUtils.hasLength(lengthName)) {
			expression = expression.and(CL.lengthName.contains(lengthName));
		}

		if(active != null) {
			expression = expression.and(CL.active.eq(active));
		}

		jpaQuery.select(CL)
				.from(CL)
				.where(expression)
				.orderBy(CL.lengthName.asc())
				.offset(offset)
				.limit(limit);

		QueryResults<CodeLengthEntity> codeLengthEntityQueryResults = jpaQuery.fetchResults();
		List<CodeLengthEntity> results = codeLengthEntityQueryResults.getResults();
		long resultsTotal = codeLengthEntityQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}
}
