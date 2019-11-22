package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.JoinFlag;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.ProjectableSQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.message.RetailerRating;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class RetailerRatingEntityRepositoryCustomImpl implements RetailerRatingEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Autowired
	private QueryDSLSQLFunctions queryDSLSQLFunctions;

	@Override
	public Page<RetailerRating> up_wa_GetRetailerRating(Integer retailerID, Integer WholeSalerID, int pageNum, int pageSize, Boolean active, String additional, LocalDateTime from, LocalDateTime to, String orderBy) {
		long offset = (pageNum - 1) * pageSize;
		long limit = pageSize;
		MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
		JPASQLQuery<RetailerRating> jpasqlQuery = new JPASQLQuery<RetailerRating>(entityManager,mssqlServer2012Templates);
		JPASQLQuery subQuery = new JPASQLQuery(entityManager,mssqlServer2012Templates);
		QRetailerRatingEntity RR = QRetailerRatingEntity.retailerRatingEntity;
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;
		QOrdersEntity ORDER = QOrdersEntity.ordersEntity;
		QRetailerEntity R = QRetailerEntity.retailerEntity;
		QRatingCommentEntity RC = QRatingCommentEntity.ratingCommentEntity;

		SimplePath<Object> vwRetailerRatingDetail = Expressions.path(Object.class, "vwRetailerRatingDetail");
		NumberPath<Integer> path_retailerRatingID = Expressions.numberPath(Integer.class, vwRetailerRatingDetail, "RetailerRatingID");
		NumberPath<Integer> path_orderID = Expressions.numberPath(Integer.class, vwRetailerRatingDetail, "OrderID");
		NumberPath<Integer> path_wholeSalerID = Expressions.numberPath(Integer.class, vwRetailerRatingDetail, "WholeSalerID");
		NumberPath<Integer> path_retailerid = Expressions.numberPath(Integer.class, vwRetailerRatingDetail, "retailerid");
		NumberPath<Integer> path_score = Expressions.numberPath(Integer.class, vwRetailerRatingDetail, "Score");
		NumberPath<Integer> path_ratingCommentID = Expressions.numberPath(Integer.class, vwRetailerRatingDetail, "RatingCommentID");
		StringPath path_comment = Expressions.stringPath(vwRetailerRatingDetail, "Comment");
		StringPath path_wholeSalerCompanyName = Expressions.stringPath(vwRetailerRatingDetail, "WholeSalerCompanyName");
		StringPath path_retailerCompanyName = Expressions.stringPath(vwRetailerRatingDetail, "RetailerCompanyName");
		StringPath path_retailerFullName = Expressions.stringPath(vwRetailerRatingDetail, "RetailerFullName");
		StringPath path_poNumber = Expressions.stringPath(vwRetailerRatingDetail, "PONumber");
		StringPath path_response = Expressions.stringPath(vwRetailerRatingDetail, "Response");
		BooleanPath path_active = Expressions.booleanPath(vwRetailerRatingDetail, "Active");
		DateTimePath<Timestamp> path_createdOn = Expressions.dateTimePath(Timestamp.class, vwRetailerRatingDetail, "CreatedOn");
		DateTimePath<Timestamp> path_checkOutDate = Expressions.dateTimePath(Timestamp.class, vwRetailerRatingDetail, "CheckOutDate");

		BooleanExpression expression = path_retailerCompanyName.isNotNull().and(path_retailerCompanyName.ne("''"));

		if(retailerID != null) {
			expression = expression.and(path_retailerid.eq(retailerID));
		}

		if(WholeSalerID != null) {
			expression = expression.and(path_wholeSalerID.eq(WholeSalerID));
		}

		if(active != null) {
			expression = expression.and(path_active.eq(active));
		}

		if(additional != null) {
			BooleanTemplate booleanTemplate = Expressions.booleanTemplate(additional);
			expression = expression.and(booleanTemplate);
		}

		if(from != null) {
			expression = expression.and(path_createdOn.goe(Timestamp.valueOf(from)));
		}

		if(to != null) {
			expression = expression.and(path_createdOn.loe(Timestamp.valueOf(to)));
		}

		OrderSpecifier orderSpecifier = path_createdOn.desc();
		if(orderBy != null) {
			String[] s = orderBy.split(" ");

			BooleanPath booleanPath = Expressions.booleanPath(s[0]);
			if(s[1].equalsIgnoreCase("asc")) {
				orderSpecifier = booleanPath.asc();
			}else {
				orderSpecifier = booleanPath.desc();
			}
		}

		ProjectableSQLQuery sqlQuery = subQuery.select(
						RR.retailerRatingID.as("RetailerRatingID")
						, RR.orderID.as("OrderID")
						, RR.wholeSalerID.as("WholeSalerID")
						, RR.retailerID.as("retailerid")
						, RR.comment.as("Comment")
						, queryDSLSQLFunctions.isnull(Integer.class, RR.score, 4).as("Score")
						, RR.active.as("Active")
						, RR.createdOn.as("CreatedOn")
						, W.companyName.as("WholeSalerCompanyName")
						, R.companyName.as("RetailerCompanyName")
						, R.firstName.concat(" ").concat(R.lastName).as("RetailerFullName")
						, ORDER.poNumber.as("PONumber")
						, ORDER.checkOutDate.as("CheckOutDate")
						, RC.comment.as("Response")
						, RC.ratingCommentID.as("RatingCommentID")
				)
				.from(RR)
				.leftJoin(W).on(RR.wholeSalerID.eq(W.wholeSalerId))
				.leftJoin(ORDER).addJoinFlag(" WITH(NOLOCK) ", JoinFlag.Position.BEFORE_CONDITION).on(RR.orderID.eq(ORDER.orderID))
				.leftJoin(R).on(RR.retailerID.eq(R.retailerID))
				.leftJoin(RC).on(RR.retailerRatingID.eq(RC.referenceID).and(RC.isVendorRating.eq(false)));

		jpasqlQuery
				.select(
						Projections.constructor(
						RetailerRating.class
						, SQLExpressions.rowNumber().over().orderBy(orderSpecifier).as("rowno")
						,path_retailerRatingID
						,path_orderID
						,path_wholeSalerID
						,path_retailerid
						,path_comment
						,path_score
						,path_active
						,path_createdOn
						,path_wholeSalerCompanyName
						,path_retailerCompanyName
						,path_retailerFullName
						,path_poNumber
						,path_checkOutDate
						,path_response
						,path_ratingCommentID)
				)
				.from(sqlQuery,vwRetailerRatingDetail)
		.where(
				expression
		)
		.offset(offset)
		.limit(limit)
		.orderBy(orderSpecifier)
		;

		QueryResults<RetailerRating> retailerRatingQueryResults = jpasqlQuery.fetchResults();
		List<RetailerRating> results = retailerRatingQueryResults.getResults();
		long resultsTotal = retailerRatingQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNum-1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}
}
