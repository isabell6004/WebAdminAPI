package net.fashiongo.webadmin.data.repository.primary.procedure;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLTemplates;
import net.fashiongo.webadmin.data.entity.primary.QLogLoginEntity;
import net.fashiongo.webadmin.data.entity.primary.QRetailerEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorAdminLoginLogEntity;
import net.fashiongo.webadmin.data.entity.primary.QWholeSalerEntity;
import net.fashiongo.webadmin.data.model.admin.ColumnCount;
import net.fashiongo.webadmin.data.model.admin.UserLogin;
import net.fashiongo.webadmin.data.model.admin.response.GetUserLoginTrackingResponse;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository
public class GetUserLoginTrackingProcedure {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Transactional(value = "primaryTransactionManager")
	public GetUserLoginTrackingResponse up_wa_GetUserLoginTracking(int pageNum, int pageSize, String sortField, String sortDir, String userType, String userName, String companyName, String ip, LocalDateTime sDate, LocalDateTime eDate) {
		long offset = (pageNum - 1) * pageSize;
		long limmit = pageSize;

		MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
		JPASQLQuery<Long> countQuery = new JPASQLQuery(entityManager,mssqlServer2012Templates);
		JPASQLQuery<UserLogin> query = new JPASQLQuery(entityManager,mssqlServer2012Templates);
		SimplePath<Object> path_X = Expressions.path(Object.class, "X");
		NumberPath<Long> path_X_Cnt = Expressions.numberPath(Long.class, path_X, "cnt");

		countQuery.select(SQLExpressions.sum(path_X_Cnt))
				.from(
						SQLExpressions.unionAll(
								logLoginCountQuery(mssqlServer2012Templates,companyName,ip,userName,userType,sDate,eDate)
								,vendorAdminLogLoginCountQuery(mssqlServer2012Templates,companyName,ip,userName,userType,sDate,eDate)
						),path_X
				);

		Long recCnt = countQuery.fetchOne();
		ColumnCount columnCount = ColumnCount.builder().column1(recCnt).build();

		NumberPath<Integer> path_X_LoginID = Expressions.numberPath(Integer.class, path_X, "LoginID");
		NumberPath<Integer> path_X_UserID = Expressions.numberPath(Integer.class, path_X, "UserID");
		StringPath path_X_UserType = Expressions.stringPath(path_X, "UserType");
		StringPath path_X_UserName = Expressions.stringPath(path_X, "UserName");
		StringPath path_X_IPAddress = Expressions.stringPath(path_X, "IPAddress");
		StringPath path_X_CompanyName = Expressions.stringPath(path_X, "CompanyName");
		StringPath path_X_FullName = Expressions.stringPath(path_X, "FullName");
		DateTimePath<Timestamp> path_X_LoginedOn = Expressions.dateTimePath(Timestamp.class, path_X, "LoginedOn");

		query.select(
				Projections.constructor(
						UserLogin.class
						, path_X_LoginID
						, path_X_UserType
						, path_X_UserID
						, path_X_UserName
						, path_X_IPAddress
						, path_X_LoginedOn
						, path_X_CompanyName
						, path_X_FullName
				)
		)
				.from(
						SQLExpressions.unionAll(
								logLoginQuery(mssqlServer2012Templates, companyName, ip, userName, userType, sDate, eDate)
								, vendorAdminLogLoginQuery(mssqlServer2012Templates, companyName, ip, userName, userType, sDate, eDate)
						), path_X
				);

		ComparableExpressionBase orderPath = null;

		if("location".equalsIgnoreCase(sortField)) {
			orderPath = path_X_UserType;
		}

		if("ipaddress".equalsIgnoreCase(sortField)) {
			orderPath = path_X_IPAddress;
		}

		if("username".equalsIgnoreCase(sortField)) {
			orderPath = path_X_UserName;
		}

		if("companyname".equalsIgnoreCase(sortField)) {
			orderPath = path_X_CompanyName;
		}

		if("loginedon".equalsIgnoreCase(sortField)) {
			orderPath = path_X_LoginedOn;
		}

		if("fullname".equalsIgnoreCase(sortField)) {
			orderPath = path_X_FullName;
		}

		OrderSpecifier orderSpecifier = null;

		if(orderPath != null) {
			if("asc".equalsIgnoreCase(sortDir)) {
				orderSpecifier = orderPath.asc();
			} else {
				orderSpecifier = orderPath.desc();
			}
		}

		if(orderSpecifier != null) {
			query.orderBy(orderSpecifier,path_X_LoginID.asc());
		} else {
			query.orderBy(path_X_LoginID.asc());
		}

		query.offset(offset).limit(limmit);

		List<UserLogin> userLoginList = query.fetch();
		return GetUserLoginTrackingResponse.builder()
				.userLoginList(userLoginList)
				.columnCountList(Arrays.asList(columnCount))
				.build();
	}

	public JPASQLQuery logLoginCountQuery(SQLTemplates sqlTemplates,String companyName,String ipAddress,String userName,String userType,LocalDateTime sDate,LocalDateTime eDate) {
		JPASQLQuery logLoginCountQuery = new JPASQLQuery(entityManager,sqlTemplates);
		QLogLoginEntity LOG = QLogLoginEntity.logLoginEntity;
		QRetailerEntity R = QRetailerEntity.retailerEntity;
		StringExpression userTypeExpression = Expressions.asString(userType);

		logLoginCountQuery.select(LOG.loginID.count().as("cnt"))
				.from(LOG)
				.innerJoin(R).on(LOG.retailerID.eq(R.retailerID).and(R.companyName.contains(companyName)))
				.where(
						LOG.loggedOn.between(Timestamp.valueOf(sDate),Timestamp.valueOf(eDate))
						.and(LOG.ipAddress.contains(ipAddress))
						.and(LOG.userName.contains(userName))
						.and(
								ExpressionUtils.or(userTypeExpression.eq("FR"),userTypeExpression.eq(""))
						)
				);

		return logLoginCountQuery;
	}

	public JPASQLQuery vendorAdminLogLoginCountQuery(SQLTemplates sqlTemplates,String companyName,String ipAddress,String userName,String userType,LocalDateTime sDate,LocalDateTime eDate) {
		JPASQLQuery vendorAdminLogLoginCountQuery = new JPASQLQuery(entityManager,sqlTemplates);
		QVendorAdminLoginLogEntity LOG = QVendorAdminLoginLogEntity.vendorAdminLoginLogEntity;
		QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;
		StringExpression userTypeExpression = Expressions.asString(userType);

		vendorAdminLogLoginCountQuery.select(LOG.vendorAdminLoginID.count().as("cnt"))
				.from(LOG)
				.innerJoin(W).on(LOG.wholeSalerID.eq(W.wholeSalerID).and(W.companyName.contains(companyName)))
				.where(
						LOG.loginedOn.between(sDate,eDate)
								.and(LOG.ipAddress.contains(ipAddress))
								.and(LOG.userName.contains(userName))
								.and(
										ExpressionUtils.or(userTypeExpression.eq("VA"),userTypeExpression.eq(""))
								)
				);

		return vendorAdminLogLoginCountQuery;
	}

	public JPASQLQuery logLoginQuery(SQLTemplates sqlTemplates,String companyName,String ipAddress,String userName,String userType,LocalDateTime sDate,LocalDateTime eDate) {
		JPASQLQuery logLoginCountQuery = new JPASQLQuery(entityManager,sqlTemplates);
		QLogLoginEntity LOG = QLogLoginEntity.logLoginEntity;
		QRetailerEntity R = QRetailerEntity.retailerEntity;
		StringExpression userTypeExpression = Expressions.asString(userType);
		StringExpression fgExpression = Expressions.asString("FR");
		logLoginCountQuery.select(LOG.loginID,fgExpression.as("UserType"),LOG.retailerID.as("UserID"),LOG.userName,LOG.ipAddress,LOG.loggedOn.as("LoginedOn"),R.companyName,R.firstName.concat(" ").concat(R.lastName).as("FullName"))
				.from(LOG)
				.innerJoin(R).on(LOG.retailerID.eq(R.retailerID).and(R.companyName.contains(companyName)))
				.where(
						LOG.loggedOn.between(Timestamp.valueOf(sDate),Timestamp.valueOf(eDate))
								.and(LOG.ipAddress.contains(ipAddress))
								.and(LOG.userName.contains(userName))
								.and(
										ExpressionUtils.or(userTypeExpression.eq("FR"),userTypeExpression.eq(""))
								)
				);

		return logLoginCountQuery;
	}

	public JPASQLQuery vendorAdminLogLoginQuery(SQLTemplates sqlTemplates,String companyName,String ipAddress,String userName,String userType,LocalDateTime sDate,LocalDateTime eDate) {
		JPASQLQuery vendorAdminLogLoginQuery = new JPASQLQuery(entityManager,sqlTemplates);
		QVendorAdminLoginLogEntity LOG = QVendorAdminLoginLogEntity.vendorAdminLoginLogEntity;
		QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;
		StringExpression userTypeExpression = Expressions.asString(userType);
		StringExpression vaExpression = Expressions.asString("VA");

		vendorAdminLogLoginQuery.select(LOG.vendorAdminLoginID.as("LoginID"),vaExpression.as("UserType"),LOG.wholeSalerID.as("UserID"),LOG.userName,LOG.ipAddress,LOG.loginedOn.as("LoginedOn"),W.companyName,W.firstName.concat(" ").concat(W.lastName).as("FullName"))
				.from(LOG)
				.innerJoin(W).on(LOG.wholeSalerID.eq(W.wholeSalerID).and(W.companyName.contains(companyName)))
				.where(
						LOG.loginedOn.between(sDate,eDate)
								.and(LOG.ipAddress.contains(ipAddress))
								.and(LOG.userName.contains(userName))
								.and(
										ExpressionUtils.or(userTypeExpression.eq("VA"),userTypeExpression.eq(""))
								)
				);

		return vendorAdminLogLoginQuery;
	}

}
