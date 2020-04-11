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
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
	import com.querydsl.core.types.Projections;
	import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpression;
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
	
	        Integer pageNum = param.getPageNum() == null ? 1 : param.getPageNum();
			Integer pageSize = param.getPageSize() == null ? 30 : param.getPageSize();
			Integer referenceID = param.getReferenceID() == null ? null : param.getReferenceID();
			Integer referencetype = param.getReferencetype() == null ? null : param.getReferencetype();
			Integer needtoBill = param.getNeedtoBill();
			LocalDateTime paymentDateFrom = param.getPaymentDateFrom();
			LocalDateTime paymentDateTo = param.getPaymentDateTo();
			LocalDateTime appliedDateFrom = param.getAppliedDateFrom();
			LocalDateTime appliedDateTo = param.getAppliedDateTo();
			BigDecimal netAmount = param.getNetAmount();
			String orderBy = StringUtils.isEmpty(param.getOrderBy()) ? "transactionFailureID desc" : param.getOrderBy();
	 
	        long offset = (pageNum - 1) * pageSize;
	        long limit = pageSize;		
	        
	        JPASQLQuery<PaymentRecoveryList> jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
	    	QPaymentRecoveryEntity p = QPaymentRecoveryEntity.paymentRecoveryEntity;
	    	
	    	OrderSpecifier orderSpecifier = null;
	    	
	    	if (orderBy != null) {
	    		String[] orderByStrings = orderBy.split(" ");
				ComparableExpression<String> orderByColumn = Expressions.comparablePath(String.class,p,orderByStrings[0]);
				orderSpecifier = orderByStrings[1].equalsIgnoreCase("asc") ? orderByColumn.asc() : orderByColumn.desc();
	        }	    	
	    	else {
	    		orderSpecifier = p.transactionFailureID.desc();
	    	}
	    	
	    	Expression<Integer> constant = Expressions.constant(1);
	        BooleanExpression expression = Expressions.asNumber(1).eq(constant);
	
	        if (referenceID != null) {
	            expression = expression.and(p.referenceID.eq(referenceID));
	        }  
	        if (referencetype != null) {
	            expression = expression.and(p.referenceTypeID.eq(referencetype));
	        }  
	        
	        if (paymentDateFrom!= null) {
	        	//expression = expression.and(Expressions.stringTemplate("convert(nvarchar(50),{0},101", p.paymentDate).goe(Expressions.stringTemplate("convert(nvarchar(50),{0},101", paymentDateFrom)));
	        	expression = expression.and(p.paymentDate.goe(Timestamp.valueOf(paymentDateFrom)));
	        }
	        if (paymentDateTo != null) {
	        	//expression = expression.and(Expressions.stringTemplate("convert(nvarchar(50),{0},101", p.paymentDate).loe(Expressions.stringTemplate("convert(nvarchar(50),{0},101", paymentDateTo)));
	        	expression = expression.and(p.paymentDate.loe(Timestamp.valueOf(paymentDateTo)));
	        }
	        if (appliedDateFrom!= null) {
	        	expression = expression.and(p.appliedDate.goe(Timestamp.valueOf(appliedDateFrom)));
	        	//expression = expression.and(Expressions.stringTemplate("convert(nvarchar(50),{0},101", p.appliedDate).goe(Expressions.stringTemplate("convert(nvarchar(50),{0},101", appliedDateFrom)));
	        }
	        if (appliedDateTo != null) {
	        	expression = expression.and(p.appliedDate.loe(Timestamp.valueOf(appliedDateTo)));
	        	//expression = expression.and(Expressions.stringTemplate("convert(nvarchar(50),{0},101", p.appliedDate).loe(Expressions.stringTemplate("convert(nvarchar(50),{0},101", appliedDateTo)));
	        }
	        if (netAmount != null) {
	            expression = expression.and(p.netAmount.eq(netAmount));
	        }         
	        if (needtoBill != null) {
	        	boolean needtoBillBoolean;
	            if (needtoBill == 1) {
	            	needtoBillBoolean = true;
	            } else {
	            	needtoBillBoolean = false;
	            }
            
	            expression = expression.and(p.needtoBill.eq(needtoBillBoolean));
	        }    
	        
	    	jpasqlQuery.select(
	                Projections.constructor(PaymentRecoveryList.class,
	                		p.transactionFailureID
	                              ,p.transactionType
	                              ,p.referenceID
	                              ,p.referenceTypeID
	                              ,p.pGReferenceID
	                              ,p.paymentPGReferenceID
	                              ,p.creditCardID
	                              ,p.creditCardReferenceID
	                              ,p.netAmount
	                              ,p.transferAmount
	                              ,p.paymentDate
	                              ,p.appliedDate
	                              ,p.billingDate
	                              ,p.needtoBill
	                              ,p.createdOn
	                              ,p.createdBy
	                              ,p.modifiedOn
	                              ,p.modifiedBy
	                              ,SQLExpressions.rowNumber().over().orderBy(orderSpecifier).as("rowno"))
	            )
	                .from(p)
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
