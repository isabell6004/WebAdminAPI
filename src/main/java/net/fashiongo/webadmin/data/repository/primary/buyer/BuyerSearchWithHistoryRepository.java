package net.fashiongo.webadmin.data.repository.primary.buyer;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.fashiongo.webadmin.data.model.buyer.BuyerSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.sql.JPASQLQuery;

import net.fashiongo.webadmin.data.entity.primary.BuyerEmailHistoryEntity;
import net.fashiongo.webadmin.data.repository.primary.BuyerEmailHistoryEntityRepository;

import net.fashiongo.webadmin.data.entity.primary.QRetailerEntity;
import net.fashiongo.webadmin.data.entity.primary.QBuyerEmailHistoryEntity;
import net.fashiongo.webadmin.data.model.buyer.AdminRetailer;
import net.fashiongo.webadmin.data.model.buyer.OrderHistory;
import net.fashiongo.webadmin.data.model.buyer.BuyerSearch;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;

@Repository
public class BuyerSearchWithHistoryRepository {
	
	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;
	
	@Autowired
	private QueryDSLSQLFunctions queryDSLSQLFunctions;
	
	@Autowired
	private BuyerEmailHistoryEntityRepository buyerEmailHistoryEntityRepository;
	
	@Transactional(transactionManager = "primaryTransactionManager")
	public Page<BuyerSearch> buyerSearchWithHistory(int pageNum, int pageSize,
													String userID,
													Boolean isUserIDPartialMatch,
													Integer retailerID,
													String companyName,
													Boolean isCompanyNamePartialMatch,
													Boolean isCompanyNameStartsWith,
													String firstName,
													Boolean isFirstNamePartialMatch,
													String lastName,
													Boolean isLastNamePartialMatch,
													String oldEmail,
													Boolean isOldEmailPartialMatch,
													String orderBy
	) {
		long offset = (pageNum - 1) * pageSize;
		long limit = pageSize;
		
		MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
		JPASQLQuery<BuyerSearch> jpasqlQuery = new JPASQLQuery<BuyerSearch>(entityManager,mssqlServer2012Templates);
		QRetailerEntity R = QRetailerEntity.retailerEntity;

		SimplePath<Object> pathHistory = Expressions.path(Object.class, "History");
		NumberPath<Integer> pathHistory_buyerId = Expressions.numberPath(Integer.class, pathHistory, "buyer_id");
		BooleanExpression filter = Expressions.ONE.eq(Expressions.ONE);

		OrderSpecifier orderSpecifier = R.startingDate.desc();
		if(StringUtils.hasLength(orderBy)) {
			String[] orderByStrings = orderBy.split(" ");
			ComparableExpression<String> orderByColumn = Expressions.comparablePath(String.class,R,orderByStrings[0]);
			orderSpecifier = orderByStrings[1].equalsIgnoreCase("asc") ? orderByColumn.asc() : orderByColumn.desc();
		}

		jpasqlQuery
				.select(
						Projections.constructor(
								BuyerSearch.class,
								R.retailerID,
								R.companyName,
								R.firstName, 
								R.lastName, 
								R.userID, 							
								R.active,
								R.currentStatus, 
								R.startingDate
						)
				)
				.from(R);

		if(oldEmail != null) {
			JPASQLQuery emailHistory = emailHistory(oldEmail,isOldEmailPartialMatch, mssqlServer2012Templates);
			jpasqlQuery.innerJoin(emailHistory,pathHistory).on(R.retailerID.eq(pathHistory_buyerId)); 
		}	
			
		
		filter = filter.and(R.currentStatus.notIn(98));
		
		if(userID != null) {
			if(Objects.equals(true,isUserIDPartialMatch)) {
				filter = filter.and(R.userID.contains(userID));
			} else {
				filter = filter.and(R.userID.eq(userID));
			}
		}

		if(retailerID != null) {
			if(Objects.equals(true,isCompanyNamePartialMatch)) {
				filter = filter.and(R.retailerID.like("%" + String.valueOf(retailerID) + "%"));
			} else {
				filter = filter.and(R.retailerID.eq(retailerID));
			}
		}	

		if(companyName != null) {
			if(Objects.equals(true,isCompanyNamePartialMatch)) {
				if(isCompanyNameStartsWith == null || Objects.equals(false,isCompanyNameStartsWith)) {
					filter = filter.and(R.companyName.contains(companyName));
				} else {
					filter = filter.and(R.companyName.startsWith(companyName));
				}
			} else {
				filter = filter.and(R.companyName.eq(companyName));
			}
		}

		if(firstName != null) {
			if(Objects.equals(true,isFirstNamePartialMatch)) {
				filter = filter.and(R.firstName.contains(firstName));
			} else {
				filter = filter.and(R.firstName.eq(firstName));
			}
		}

		if(lastName != null) {
			if(Objects.equals(true,isLastNamePartialMatch)) {
				filter = filter.and(R.lastName.contains(lastName));
			} else {
				filter = filter.and(R.lastName.eq(lastName));
			}
		}
		
		jpasqlQuery
		.where(filter)
		.offset(offset)
		.limit(limit)
		.orderBy(orderSpecifier);
		
		QueryResults<BuyerSearch> buyerSearchQueryResults = jpasqlQuery.fetchResults();
		List<BuyerSearch> results = buyerSearchQueryResults.getResults();
		long resultsTotal = buyerSearchQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNum-1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}
	
	
	private JPASQLQuery emailHistory(String  oldEmail,Boolean isOldEmailPartialMatch, MSSQLServer2012Templates mssqlServer2012Templates) {
	 
		QBuyerEmailHistoryEntity e = QBuyerEmailHistoryEntity.buyerEmailHistoryEntity;
		
		BooleanExpression filter = Expressions.ONE.eq(Expressions.ONE);
				
		if(Objects.equals(true,isOldEmailPartialMatch)) {
			filter = filter.and(e.email.contains(oldEmail));
		} else {
			filter = filter.and(e.email.eq(oldEmail));
		}

		JPASQLQuery jpasqlQuery = new JPASQLQuery(entityManager,mssqlServer2012Templates);
		jpasqlQuery.select(e.buyerId)
				.from(e)
				.where(filter)
				.groupBy(e.buyerId);
		
		return jpasqlQuery;
	}

}
