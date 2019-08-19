package net.fashiongo.webadmin.data.repository.primary;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import net.fashiongo.webadmin.data.entity.primary.PolicyAgreementEntity;
import net.fashiongo.webadmin.data.entity.primary.QPolicyAgreementEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.PolicyAgreement;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PolicyAgreementEntityRepositoryCustomImpl implements PolicyAgreementEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(value = "primaryTransactionManager")
	public Page<PolicyAgreementEntity> findDetail(int policyId, String searchColumn, String searchText, int pageNumber, int pageSize) {
		int pn = pageNumber < 1 ? 0 : pageNumber-1;
		long offset = pn * pageSize;
		long limit = pageSize;

		JPAQuery<PolicyAgreementEntity> jpaQuery = new JPAQuery<>(entityManager);
		QPolicyAgreementEntity PA = new QPolicyAgreementEntity("PA");
		BooleanExpression where = Expressions.asNumber(1).eq(1);

		if(StringUtils.isEmpty(searchColumn) ==false && StringUtils.isEmpty(searchText) == false) {
			String propertyNameByColumnName = getPropertyNameByColumnName(PolicyAgreementEntity.class, searchColumn);
			StringPath stringPath = Expressions.stringPath(PA, propertyNameByColumnName);
			BooleanExpression contains = stringPath.contains(searchText);
			where = where.and(contains);
		}

		if(policyId > 0) {
			where = where.and(PA.policyID.eq(policyId));
		}

		jpaQuery.select(PA)
				.from(PA)
				.where(
						where
				)
				.orderBy(PA.policyAgreementID.desc())
				.offset(offset)
				.limit(limit);

		try {
			QueryResults<PolicyAgreementEntity> policyAgreementEntityQueryResults = jpaQuery.fetchResults();
			PageRequest pageRequest = PageRequest.of(pn, pageSize);
			return PageableExecutionUtils.getPage(policyAgreementEntityQueryResults.getResults(),pageRequest, policyAgreementEntityQueryResults::getTotal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getPropertyNameByColumnName(Class<?> clazz,String name) {

		Column[] annotationsByType = clazz.getAnnotationsByType(Column.class);
		Field[] declaredFields = clazz.getDeclaredFields();

		for (Field declaredField : declaredFields) {
			Column annotation = declaredField.getAnnotation(Column.class);
			if(annotation != null) {
				if(annotation.name().equals(name)) {
					return declaredField.getName();
				}
			}
		}

		return null;
	}

	@Override
	@Transactional(value = "primaryTransactionManager")
	public Page<PolicyAgreement> findDetailPolicyAgreement(int policyId, String searchColumn, String searchText, int pageNumber, int pageSize) {
		int pn = pageNumber < 1 ? 0 : pageNumber-1;
		long offset = pn * pageSize;
		long limit = pageSize;

		JPASQLQuery<PolicyAgreement> jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		BooleanExpression where = Expressions.asNumber(1).eq(1);

		PathMetadata pathMetadata = PathMetadataFactory.forVariable("PA");
		SimplePath<Object> PA = Expressions.path(PolicyAgreementEntity.class, pathMetadata);

		NumberPath<Integer> PolicyAgreementID = Expressions.numberPath(Integer.class,PA,"PolicyAgreementID");
		NumberPath<Integer> PolicyID = Expressions.numberPath(Integer.class,PA,"PolicyID");
		NumberPath<Integer> WholeSalerID = Expressions.numberPath(Integer.class,PA,"WholeSalerID");
		NumberPath<Integer> RetailerID = Expressions.numberPath(Integer.class,PA,"RetailerID");
		StringPath CompanyName = Expressions.stringPath(PA,"CompanyName");
		StringPath AgreedByName = Expressions.stringPath(PA,"AgreedByName");
		StringPath AgreedByID = Expressions.stringPath(PA,"AgreedByID");
		StringPath IPAddress = Expressions.stringPath(PA,"IPAddress");
		BooleanPath Agreed = Expressions.booleanPath(PA, "Agreed");
		DateTimePath<Timestamp> AgreedOn = Expressions.dateTimePath(Timestamp.class, PA, "AgreedOn");

		JPAQuery<PolicyAgreement> jpaQuery = new JPAQuery(entityManager);

		if(StringUtils.isEmpty(searchColumn) ==false && StringUtils.isEmpty(searchText) == false) {
			StringPath stringPath = Expressions.stringPath(PA, searchColumn);
			BooleanExpression contains = stringPath.contains(searchText);
			where = where.and(contains);
		}

		if(policyId > 0) {
			where = where.and(PolicyID.eq(policyId));
		}

		jpasqlQuery
				.select(
						Projections.constructor(
								PolicyAgreement.class
								,PolicyAgreementID
								,PolicyID
								,WholeSalerID
								,CompanyName
								,RetailerID
								,AgreedOn
								,AgreedByName
								,AgreedByID
								,IPAddress
								,Agreed
						)
				)
				.from(PA)
				.where(where)
				.orderBy(PolicyAgreementID.desc())
				.offset(offset)
				.limit(limit);

		QueryResults<PolicyAgreement> policyAgreementQueryResults = jpasqlQuery.fetchResults();
		List<PolicyAgreement> results = policyAgreementQueryResults.getResults();
		long resultsTotal = policyAgreementQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pn, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}
}
