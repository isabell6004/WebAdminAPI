	package net.fashiongo.webadmin.data.repository.primary;
	
	import java.math.BigDecimal;
	import java.sql.Timestamp;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.List;
	
	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;
	
	import org.apache.commons.lang3.StringUtils;
	import org.springframework.data.domain.Page;
	import org.springframework.data.domain.PageRequest;
	import org.springframework.data.repository.support.PageableExecutionUtils;
	import org.springframework.transaction.annotation.Transactional;
	
	import com.querydsl.core.QueryResults;
	import com.querydsl.core.types.Expression;
	import com.querydsl.core.types.OrderSpecifier;
	import com.querydsl.core.types.Projections;
	import com.querydsl.core.types.dsl.BooleanExpression;
	import com.querydsl.core.types.dsl.Expressions;
	import com.querydsl.jpa.sql.JPASQLQuery;
	import com.querydsl.sql.SQLExpressions;
	
	import net.fashiongo.webadmin.data.entity.primary.QPaymentRecoveryEntity;
	import net.fashiongo.webadmin.data.model.payment.GetPaymentRecoveryListParameter;
	import net.fashiongo.webadmin.data.model.payment.PaymentRecoveryList;
	import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
	
	public class PaymentRecoveryRepositoryCustomImpl implements PaymentRecoveryRepositoryCustom {
	
	    @PersistenceContext(unitName = "primaryEntityManager")
	    private EntityManager entityManager;	
	    
	    @Override
	    public Page<PaymentRecoveryList> getPaymentRecoveryListWithCount(GetPaymentRecoveryListParameter param){
	
	        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
	
	        Integer pageNum = param.getPageNum() == null ? 1 : param.getPageNum();
			Integer pageSize = param.getPageSize() == null ? 30 : param.getPageSize();
			Integer referenceID = param.getReferenceID() == null ? null : param.getReferenceID();
			Integer referencetype = param.getReferencetype() == null ? null : param.getReferencetype();
			Boolean needtoBill = param.getNeedtoBill();
			Timestamp paymentDateFrom = param.getPaymentDateFrom();
			Timestamp paymentDateTo = param.getPaymentDateTo();
			Timestamp appliedDateFrom = param.getAppliedDateFrom();
			Timestamp appliedDateTo = param.getAppliedDateTo();
			BigDecimal netAmount = param.getNetAmount();
			//String orderBy = StringUtils.isEmpty(param.getOrderBy()) ? null : param.getOrderBy();
	 
	        long offset = (pageNum - 1) * pageSize;
	        long limit = pageSize;		
			OrderSpecifier orderSpecifier = null;
	        
	        JPASQLQuery<PaymentRecoveryList> jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
	    	QPaymentRecoveryEntity paymentrecovery = QPaymentRecoveryEntity.paymentRecoveryEntity;
	    	orderSpecifier = paymentrecovery.transactionFailureID.asc();
	    	
	    	Expression<Integer> constant = Expressions.constant(1);
	        BooleanExpression expression = Expressions.asNumber(1).eq(constant);
	
	        if (referenceID != null) {
	            expression = expression.and(paymentrecovery.referenceID.eq(referenceID));
	        }  
	        if (referencetype != null) {
	            expression = expression.and(paymentrecovery.referenceTypeID.eq(referencetype));
	        }  
	        if (paymentDateFrom!= null) {
	        	expression = expression.and(paymentrecovery.appliedDate.goe(paymentDateFrom));
	        }
	        if (paymentDateTo != null) {
	        	expression = expression.and(paymentrecovery.appliedDate.loe(paymentDateTo));
	        }
	  	        
	        if (appliedDateFrom!= null) {
	        	expression = expression.and(paymentrecovery.appliedDate.goe(appliedDateFrom));
	        }
	        if (appliedDateTo != null) {
	        	expression = expression.and(paymentrecovery.appliedDate.loe(appliedDateTo));
	        }
	        if (netAmount != null) {
	            expression = expression.and(paymentrecovery.netAmount.eq(netAmount));
	        }         
	        if (needtoBill != null) {
	            expression = expression.and(paymentrecovery.needtoBill.eq(needtoBill));
	        }    
	        
	    	jpasqlQuery.select(
	                Projections.constructor(PaymentRecoveryList.class,
	                		paymentrecovery.transactionFailureID
	                              ,paymentrecovery.transactionType
	                              ,paymentrecovery.referenceID
	                              ,paymentrecovery.referenceTypeID
	                              ,paymentrecovery.pGReferenceID
	                              ,paymentrecovery.paymentPGReferenceID
	                              ,paymentrecovery.creditCardID
	                              ,paymentrecovery.creditCardReferenceID
	                              ,paymentrecovery.netAmount
	                              ,paymentrecovery.transferAmount
	                              ,paymentrecovery.paymentDate
	                              ,paymentrecovery.appliedDate
	                              ,paymentrecovery.billingDate
	                              ,paymentrecovery.needtoBill
	                              ,paymentrecovery.createdOn
	                              ,paymentrecovery.createdBy
	                              ,paymentrecovery.modifiedOn
	                              ,paymentrecovery.modifiedBy
	                              ,SQLExpressions.rowNumber().over().orderBy(orderSpecifier).as("rowno"))
	            )
	                .from(paymentrecovery)
	                .where(expression)
	                .orderBy(orderSpecifier)
	                .offset(offset)
	                .limit(limit);
	
	        QueryResults<PaymentRecoveryList> QueryResult = jpasqlQuery.fetchResults();
	        long total = QueryResult.getTotal();
	        List<PaymentRecoveryList> results = QueryResult.getResults();
	        
	        PageRequest pageRequest = PageRequest.of(pageNum-1, pageSize);
	        return PageableExecutionUtils.getPage(results,pageRequest,()-> total);   	
	    }
	
	}
