package net.fashiongo.webadmin.data.repository.primary.buyer;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.sql.JPASQLQuery;
import net.fashiongo.webadmin.data.entity.primary.QChannelLogEntity;
import net.fashiongo.webadmin.data.entity.primary.QOrdersEntity;
import net.fashiongo.webadmin.data.entity.primary.QRetailerEntity;
import net.fashiongo.webadmin.data.entity.primary.QRetailerLoginCountEntity;
import net.fashiongo.webadmin.data.model.buyer.AdminRetailerCSV;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AdvancedSearchRetailerRepository {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Autowired
	private QueryDSLSQLFunctions queryDSLSQLFunctions;

	@Transactional(transactionManager = "primaryTransactionManager")
	public List<AdminRetailerCSV> advancedSearchRetailerCSV(String userID,
	                                                        Boolean isUserIDPartialMatch,
	                                                        Integer retailerID,
	                                                        Integer currentStatus,
	                                                        String companyName,
	                                                        Boolean isCompanyNamePartialMatch,
	                                                        Boolean isCompanyNameStartsWith,
	                                                        String firstName,
	                                                        Boolean isFirstNamePartialMatch,
	                                                        String lastName,
	                                                        Boolean isLastNamePartialMatch,
	                                                        Boolean isActive,
	                                                        Boolean isOnline,
	                                                        String documentUpload,
	                                                        String location,
	                                                        String state,
	                                                        String country,
	                                                        Boolean isS,
	                                                        Boolean isIn1,
	                                                        Boolean isIn2,
	                                                        Boolean isO,
	                                                        String registerFromDateTime,
	                                                        String registerToDateTime,
	                                                        Integer loginCountFrom,
	                                                        Integer loginCountTo,
	                                                        String loginFromDateTime,
	                                                        String loginToDateTime,
	                                                        Integer orderCountFrom,
	                                                        Integer orderCountTo,
	                                                        BigDecimal orderAmountFrom,
	                                                        BigDecimal orderAmountTo,
	                                                        Integer orderVendorCountFrom,
	                                                        Integer orderVendorCountTo,
	                                                        String checkoutFromDateTime,
	                                                        String checkoutToDateTime,
	                                                        Integer wholeSalerID,
	                                                        String orderBy,
	                                                        Integer showID
	) {

		LocalDateTime registerFrom = Optional.ofNullable(registerFromDateTime).filter(dateTime -> StringUtils.hasLength(dateTime)).map(dateTime -> LocalDateTime.parse(dateTime)).orElse(null);
		LocalDateTime registerTo = Optional.ofNullable(registerToDateTime).filter(dateTime -> StringUtils.hasLength(dateTime)).map(dateTime -> LocalDateTime.parse(dateTime)).orElse(null);
		LocalDateTime loginFrom = Optional.ofNullable(loginFromDateTime).filter(dateTime -> StringUtils.hasLength(dateTime)).map(dateTime -> LocalDateTime.parse(dateTime)).orElse(null);
		LocalDateTime loginTo = Optional.ofNullable(loginToDateTime).filter(dateTime -> StringUtils.hasLength(dateTime)).map(dateTime -> LocalDateTime.parse(dateTime)).orElse(null);
		LocalDateTime checkoutFrom = Optional.ofNullable(checkoutFromDateTime).filter(dateTime -> StringUtils.hasLength(dateTime)).map(dateTime -> LocalDateTime.parse(dateTime)).orElse(null);
		LocalDateTime checkoutTo = Optional.ofNullable(checkoutToDateTime).filter(dateTime -> StringUtils.hasLength(dateTime)).map(dateTime -> LocalDateTime.parse(dateTime)).orElse(null);

		MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
		JPASQLQuery<AdminRetailerCSV> jpasqlQuery = new JPASQLQuery<AdminRetailerCSV>(entityManager,mssqlServer2012Templates);
		QRetailerEntity R = QRetailerEntity.retailerEntity;

		SimplePath<Object> pathCL = Expressions.path(Object.class, "CL");
		NumberPath<Integer> pathCL_happenedAt = Expressions.numberPath(Integer.class, pathCL, "HappenedAt");
		NumberPath<Integer> pathCL_retailerID = Expressions.numberPath(Integer.class, pathCL, "RetailerID");
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
								AdminRetailerCSV.class,
								R.retailerID,
								R.companyName,
								R.firstName,
								R.lastName,
								R.email,
								R.phone,
								R.billStreetNo,
								R.billCity,
								R.billSTATE,
								R.billZipcode,
								R.billCountry,
								R.streetNo,
								R.city,
								R.state,
								R.zipcode,
								R.country,
								R.active,
								R.currentStatus,
								R.startingDate.as("[Created On]"),
								Expressions.cases()
										.when(pathCL_happenedAt.eq(1)).then("WEB")
										.when(pathCL_happenedAt.eq(2)).then("MOBILE")
										.when(pathCL_happenedAt.eq(3)).then("VENDOR ADMIN")
										.when(pathCL_happenedAt.eq(4)).then("WEB ADMIN")
										.when(pathCL_happenedAt.eq(5)).then("MAGIC")
										.otherwise("WEB")
										.as("[Registration Device]")
						)
				)
				.from(R);

		JPASQLQuery channelLog = channelLog(showID, mssqlServer2012Templates);

		if(showID != null) {
			jpasqlQuery.innerJoin(channelLog,pathCL).on(R.retailerID.eq(pathCL_retailerID));
		} else {
			jpasqlQuery.leftJoin(channelLog,pathCL).on(R.retailerID.eq(pathCL_retailerID));
		}

		if (loginCountFrom != null || loginCountTo != null || loginFrom != null || loginTo != null) {
			SimplePath<Object> pathL = Expressions.path(Object.class, "L");
			NumberPath<Integer> pathL_retailerID = Expressions.numberPath(Integer.class, pathL, "RetailerID");
			NumberPath<Long> pathL_loginCount = Expressions.numberPath(Long.class, pathL, "Logincount");

			JPASQLQuery queryLoginPerRetailer = new JPASQLQuery(entityManager,mssqlServer2012Templates);
			QRetailerLoginCountEntity LC = QRetailerLoginCountEntity.retailerLoginCountEntity;
			BooleanExpression booleanExpression = Expressions.ONE.eq(Expressions.ONE);

			if(retailerID != null) {
				booleanExpression = booleanExpression.and(LC.id.retailerID.eq(retailerID));
			}

			if(loginFrom != null) {
				String loginFromValue = loginFrom.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
				booleanExpression = booleanExpression.and(LC.id.happendedOn.goe(loginFromValue));
			}

			if(loginTo != null) {
				String loginToValue = loginTo.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
				booleanExpression = booleanExpression.and(LC.id.happendedOn.loe(loginToValue));
			}

			queryLoginPerRetailer.select(LC.id.retailerID.as("RetailerID"),LC.loginCount.sum().as("Logincount"))
					.from(LC)
					.where(booleanExpression)
					.groupBy(LC.id.retailerID);

			jpasqlQuery.innerJoin(queryLoginPerRetailer,pathL).on(R.retailerID.eq(pathL_retailerID));

			if(loginCountFrom != null) {
				filter = filter.and(pathL_loginCount.goe(loginCountFrom));
			}

			if(loginCountTo != null) {
				filter = filter.and(pathL_loginCount.loe(loginCountTo));
			}
		}

		if (orderCountFrom != null || orderCountTo != null || orderAmountFrom != null ||orderAmountTo != null || orderVendorCountFrom != null || orderVendorCountTo != null || checkoutFrom != null || checkoutTo != null ) {
			SimplePath<Object> pathO = Expressions.path(Object.class, "O");
			NumberPath<Integer> pathO_retailerID = Expressions.numberPath(Integer.class, pathO, "RetailerID");
			NumberPath<Long> pathO_orderCount = Expressions.numberPath(Long.class, pathO, "OrderCount");
			NumberPath<Long> pathO_orderAmount = Expressions.numberPath(Long.class, pathO, "OrderAmount");
			NumberPath<Long> pathO_orderVendorCount = Expressions.numberPath(Long.class, pathO, "OrderVendorCount");

			QOrdersEntity O = new QOrdersEntity("O");
			JPASQLQuery queryOrder = new JPASQLQuery(entityManager,mssqlServer2012Templates);

			BooleanExpression booleanExpression = O.orderStatusID.notIn(0);

			if(retailerID != null) {
				booleanExpression = booleanExpression.and(O.retailerID.eq(retailerID));
			}

			if(checkoutFrom != null) {
				String checkoutFromValue = checkoutFrom.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
				booleanExpression = booleanExpression.and(Expressions.comparableTemplate(String.class, "CONVERT(nvarchar(8),{0},112", O.checkOutDate).goe(checkoutFromValue));
			}

			if(checkoutTo != null) {
				String checkoutToValue = checkoutTo.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
				booleanExpression = booleanExpression.and(Expressions.comparableTemplate(String.class, "CONVERT(nvarchar(8),{0},112", O.checkOutDate).loe(checkoutToValue));
			}

			queryOrder.select(O.retailerID.as("RetailerID"),O.orderID.count().as("OrderCount"),O.totalAmountWSC.sum().as("OrderAmount"),O.wholeSalerID.countDistinct().as("OrderVendorCount"))
					.from(O)
					.where(booleanExpression)
					.groupBy(O.retailerID);
			jpasqlQuery.innerJoin(queryOrder,pathO).on(R.retailerID.eq(pathO_retailerID));

			if(orderCountFrom != null) {
				filter = filter.and(pathO_orderCount.goe(orderCountFrom));
			}

			if(orderCountTo != null) {
				filter = filter.and(pathO_orderCount.loe(orderCountTo));
			}

			if(orderAmountFrom != null) {
				filter = filter.and(pathO_orderAmount.goe(orderAmountFrom));
			}

			if(orderAmountTo != null) {
				filter = filter.and(pathO_orderAmount.loe(orderAmountTo));
			}

			if(orderVendorCountFrom != null) {
				filter = filter.and(pathO_orderVendorCount.goe(orderVendorCountFrom));
			}

			if(orderVendorCountTo != null) {
				filter = filter.and(pathO_orderVendorCount.loe(orderVendorCountTo));
			}
		}

		if(userID != null) {
			if(Objects.equals(true,isUserIDPartialMatch)) {
				filter = filter.and(R.userID.contains(userID).or(R.email.contains(userID)));
			} else {
				filter = filter.and(R.userID.eq(userID).or(R.email.eq(userID)));
			}
		}

		if(retailerID != null) {
			if(Objects.equals(true,isCompanyNamePartialMatch)) {
				filter = filter.and(R.retailerID.like("%" + String.valueOf(retailerID) + "%"));
			} else {
				filter = filter.and(R.retailerID.eq(retailerID));
			}
		}

		if(currentStatus != null) {
			filter = filter.and(R.currentStatus.eq(currentStatus));
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

		if(isActive != null) {
			if(Objects.equals(true,isActive)) {
				filter = filter.and(R.active.eq("Y"));
			} else {
				filter = filter.and(R.active.eq("N"));
			}
		}

		if(isOnline != null) {
			if(Objects.equals(true,isOnline)) {
				filter = filter.and(R.webSite.ne(""));
			} else {
				filter = filter.and(R.webSite.isNull().or(R.webSite.eq("")));
			}
		}


		Boolean isAndOperation = null;
		if("or".equalsIgnoreCase(documentUpload)) {
			isAndOperation = false;
		}

		if("and".equalsIgnoreCase(documentUpload)) {
			isAndOperation = true;
		}

		BooleanExpression fileFilter = Expressions.ONE.eq(Expressions.ONE);

		if(isAndOperation != null) {
			if(isS) {
				fileFilter = fileFilter.and(R.sellerPermitFileName.ne(""));
			}

			if(isIn1) {
				if(isAndOperation) {
					fileFilter = fileFilter.and(R.invoiceFileName1.ne(""));
				} else {
					fileFilter = fileFilter.or(R.invoiceFileName1.ne(""));
				}
			}

			if(isIn2) {
				if(isAndOperation) {
					fileFilter = fileFilter.and(R.invoiceFileName2.ne(""));
				} else {
					fileFilter = fileFilter.or(R.invoiceFileName2.ne(""));
				}
			}

			if(isO) {
				if(isAndOperation) {
					fileFilter = fileFilter.and(R.additionalDocumentFileName.ne(""));
				} else {
					fileFilter = fileFilter.or(R.additionalDocumentFileName.ne(""));
				}
			}

			filter = filter.and(fileFilter);
		}

		if(location != null) {
			if(location.equals("229") || location.equals("38")) {
				filter = filter.and(R.billCountryID.eq(Integer.valueOf(location)));
			} else {
				filter = filter.and(
						queryDSLSQLFunctions.isnull(Integer.class,R.billCountryID,0).notIn(229,38)
				);

				if(Objects.equals("ALL",country) == false) {
					filter = filter.and(queryDSLSQLFunctions.isnull(Integer.class,R.billCountryID,0).eq(Integer.valueOf(country)));
				}
			}

			if(state != null) {
				filter = filter.and(R.state.eq(state));
			}
		}

		if(registerFrom != null) {
			filter = filter.and(R.startingDate.goe(Timestamp.valueOf(registerFrom)));
		}

		if(registerTo != null) {
			filter = filter.and(R.startingDate.loe(Timestamp.valueOf(registerTo)));
		}

		return jpasqlQuery.where(filter)
				.orderBy(orderSpecifier)
				.fetch();
	}

	private JPASQLQuery channelLog(Integer showID, MSSQLServer2012Templates mssqlServer2012Templates) {
		QChannelLogEntity A = QChannelLogEntity.channelLogEntity;
		QOrdersEntity B = QOrdersEntity.ordersEntity;
		SimplePath<Object> C = Expressions.path(Object.class, "C");
		NumberPath<Integer> retailerID = Expressions.numberPath(Integer.class, C, "RetailerID");
		NumberPath<Integer> channelLogID = Expressions.numberPath(Integer.class, C, "ChannelLogID");
		QChannelLogEntity D = QChannelLogEntity.channelLogEntity;
		JPASQLQuery jpasqlQueryC = new JPASQLQuery(entityManager,mssqlServer2012Templates);
		jpasqlQueryC.select(B.retailerID.as("RetailerID"),A.channelLogID.min().as("ChannelLogID"))
				.from(A)
				.innerJoin(B).on(A.referenceID.eq(B.orderID))
				.where(A.actionTypeID.eq(1))
				.groupBy(B.retailerID);

		JPASQLQuery jpasqlQuery = new JPASQLQuery(entityManager,mssqlServer2012Templates);
		jpasqlQuery.select(retailerID,D.happenedAt)
				.from(jpasqlQueryC,C)
				.innerJoin(D).on(channelLogID.eq(D.channelLogID));

		if(showID != null) {
			jpasqlQuery.where(D.happenedAt.eq(showID));
		}

		return jpasqlQuery;
	}
}
