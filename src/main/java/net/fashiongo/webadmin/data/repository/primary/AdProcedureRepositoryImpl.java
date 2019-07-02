package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.ResultTransformer;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLServer2012Templates;
import net.fashiongo.webadmin.data.FashionGODayOfWeek;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.ad.Bidding;
import net.fashiongo.webadmin.data.model.ad.CollectionCategoryWithCounts;
import net.fashiongo.webadmin.data.model.ad.Curated;
import net.fashiongo.webadmin.data.model.ad.ResultGetCategoryAdCalendar2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AdProcedureRepositoryImpl implements AdProcedureRepository {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public ResultGetCategoryAdCalendar2 up_wa_GetCategoryAdCalendar2(LocalDate categoryDate1) {

		LocalDate categoryDate = categoryDate1 == null ? LocalDate.now() : categoryDate1;
		LocalDateTime categoryDateTime = categoryDate.atTime(0, 0, 0, 0);
		LocalDateTime fd = categoryDateTime
				.withDayOfMonth(1)
				.withHour(0)
				.withMinute(0)
				.withSecond(0)
				.withNano(0);

		LocalDateTime td = fd.plusMonths(1)
				.minusSeconds(1);

		// Different java DayOfWeek and FashionGO DayOfWeek
		FashionGODayOfWeek fashionGOfdDayOfWeek = FashionGODayOfWeek.of(fd.getDayOfWeek());
		FashionGODayOfWeek fashionGOtdDayOfWeek = FashionGODayOfWeek.of(td.getDayOfWeek());
		LocalDateTime startFromDateOfFirstWeekDay = fd.minusDays(fashionGOfdDayOfWeek.getValue() - 1);
		LocalDateTime endFromDateOFListWeekDay = td.plusDays(7 - fashionGOtdDayOfWeek.getValue());
		int monthValue = categoryDateTime.getMonthValue();
		List<CollectionCategoryWithCounts> collectionCategoryWithCountsList = findAllCollectorCategoryWithCount(true, 0);
		List<Bidding> biddingLists = findAllBidding(monthValue, startFromDateOfFirstWeekDay, endFromDateOFListWeekDay);
		List<Curated> curatedLists = findAllCurated(monthValue, startFromDateOfFirstWeekDay, endFromDateOFListWeekDay);

		return ResultGetCategoryAdCalendar2.builder()
				.collectionCategoryWithCounts(collectionCategoryWithCountsList)
				.curatedList(curatedLists)
				.biddingList(biddingLists)
				.build();
	}

	private List<Bidding> findAllBidding(int categoryDateMonthValue, LocalDateTime startFromDate, LocalDateTime endFromDate) {
		QAdVendorEntity AV = new QAdVendorEntity("AV");
		QProductsEntity P = new QProductsEntity("P");
		QMapAdVendorItemEntity MAVI = new QMapAdVendorItemEntity("MAVI");
		QAdVendorEntity SUB_AV = new QAdVendorEntity("AV");
		QProductsEntity SUB_P = new QProductsEntity("SUB_P");
		QMapAdVendorItemEntity SUB_MAVI = new QMapAdVendorItemEntity("SUB_MAVI");
		QSimpleWholeSalerEntity W = new QSimpleWholeSalerEntity("W");
		SQLServer2012Templates sqlServer2012Templates = new SQLServer2012Templates();
		Timestamp timestampFd = Timestamp.valueOf(startFromDate);
		Timestamp timestampEd = Timestamp.valueOf(endFromDate);
		JPASQLQuery<Bidding> jpasqlQuery = new JPASQLQuery<Bidding>(entityManager,new SQLServer2012Templates());

		NumberExpression<Integer> dateDay = AV.fromDate.dayOfMonth().as("DateDay");
		StringExpression monthType = Expressions.cases()
				.when(AV.fromDate.month().lt(categoryDateMonthValue)).then("p")
				.when(AV.fromDate.month().gt(categoryDateMonthValue)).then("n")
				.otherwise("t");

		NumberExpression<Integer> active = Expressions.cases()
				.when(AV.active.eq(true)).then(1)
				.otherwise(0);

		QCollectionCategoryEntity SUB_CC = new QCollectionCategoryEntity("SUB_CC");
		Path<Object> cc = ExpressionUtils.path(Object.class, "CC");
		Path<Object> ccSpotId = ExpressionUtils.path(Integer.class,cc, "SpotID");
		Path<Object> temp = ExpressionUtils.path(Object.class, "TEMP");
		Path<Object> tempAdID = ExpressionUtils.path(Integer.class,temp, "ADID");
		Path<Object> tempMID = ExpressionUtils.path(Integer.class,temp, "MID");
		return jpasqlQuery.select(
					Projections.constructor(
							Bidding.class
							, AV.adID
							, AV.pageID
							, AV.spotID
							, AV.wholeSalerID
							, AV.vendorCategoryID
							, AV.fromDate
							, dateDay
							, monthType
							, P.productID
							, active
					))
				.from(AV)
				.innerJoin(
						JPAExpressions.select(SUB_CC.spotID)
						.from(SUB_CC)
						.groupBy(SUB_CC.spotID) ,ExpressionUtils.path(Object.class, "CC")).on(AV.spotID.eq(ccSpotId))
				.leftJoin(
							JPAExpressions.select(SUB_AV.adID.as("ADID"),SUB_CC.spotID,SUB_MAVI.mapID.min().as("MID"))
							.from(SUB_AV)
							.innerJoin(SUB_CC).on(SUB_AV.spotID.eq(SUB_CC.spotID))
							.innerJoin(SUB_MAVI).on(SUB_AV.adID.eq(SUB_MAVI.adID))
							.innerJoin(SUB_P).on(SUB_MAVI.productID.eq(SUB_P.productID))
							.where(
									SUB_AV.active.eq(true).and(SUB_AV.fromDate.goe(timestampFd).and(SUB_AV.fromDate.loe(timestampEd)))
									.and(SUB_P.active.eq(true))
							).groupBy(SUB_AV.adID,SUB_CC.spotID)
							,temp)
						.on(AV.adID.eq(tempAdID))
				.leftJoin(MAVI)
					.on(MAVI.mapID.eq(tempMID))
				.leftJoin(P)
					.on(MAVI.productID.eq(P.productID).and(P.active.eq(true)))
				.leftJoin(W)
					.on(P.wholeSalerID.eq(W.wholeSalerId))
				.where(
						AV.active.eq(true).and(AV.fromDate.goe(timestampFd).and(AV.fromDate.loe(timestampEd)))
				)
				.orderBy(AV.spotID.desc(),dateDay.desc(),AV.adID.desc()) //ORDER BY a.SpotID DESC, a.DateDay DESC, a.AdID DESC;
				.fetch()
				.stream()
				.distinct()
				.collect(Collectors.toList());
	}

	private List<CollectionCategoryWithCounts> findAllCollectorCategoryWithCount(boolean active, int spotId) {
		QCollectionCategoryEntity CC = QCollectionCategoryEntity.collectionCategoryEntity;
		JPAQuery jpaQuery = new JPAQuery<>(entityManager);

		NumberPath<Integer> bidCount = Expressions.numberPath(Integer.class,"0");
		NumberPath<Integer> curatedCount = Expressions.numberPath(Integer.class,"0");
		NumberPath<Integer> bestCount = Expressions.numberPath(Integer.class,"0");
		NumberPath<Integer> notCount = Expressions.numberPath(Integer.class,"0");

		jpaQuery.select(
				Projections.constructor(
						CollectionCategoryWithCounts.class
						, CC.collectionCategoryID
						, CC.collectionCategoryName
						, CC.parentCollectionCategoryID
						, CC.spotID
						, CC.lvl
						, bidCount
						, curatedCount
						, bestCount
						, notCount
				)
		)
				.from(CC)
				.where(CC.active.eq(active).and(CC.spotID.gt(spotId)))
				.orderBy(CC.spotID.asc(), CC.lvl.asc(), CC.listOrder.asc(), CC.collectionCategoryID.asc());

		return jpaQuery.fetch();
	}

	private List<Curated> findAllCurated(int categoryDateMonthValue, LocalDateTime startFromDate, LocalDateTime endFromDate) {
//		select CollectionCategoryItemID
//				, SpotID
//				, FromDate
//				, CollectionCategoryID
//		, cc.ProductID
//				, CollectionCategoryType
//				, DATEPART(day, FromDate) as DateDay
//		, (CASE WHEN DATEPART(mm, FromDate)<@tm THEN 'p' WHEN DATEPART(mm, FromDate)>@tm THEN 'n' ELSE 't' END) AS MonthType
//		, (CASE WHEN p.Active = 1 AND w.Active =1 AND w.ShopActive =1 AND w.OrderActive =1 THEN 1 ELSE 0 END) AS Active
//		from CollectionCategoryItem as cc
//		left outer join dbo.Products p on cc.ProductID=p.ProductID
//		left outer join dbo.tblWholeSaler w on cc.WholeSalerID = w.WholeSalerID
//		where FromDate >= @FromDate and FromDate <= @ToDate
//		ORDER BY cc.SpotID DESC, DateDay DESC, cc.CollectionCategoryItemID DESC
		QCollectionCategoryItemEntity CCI = QCollectionCategoryItemEntity.collectionCategoryItemEntity;
		QProductsEntity P = QProductsEntity.productsEntity;
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;

		JPAQuery<CollectionCategoryItemEntity> jpaQuery = new JPAQuery<>(entityManager);

		StringExpression monthType = Expressions.cases()
				.when(CCI.fromDate.month().lt(categoryDateMonthValue)).then("p")
				.when(CCI.fromDate.month().gt(categoryDateMonthValue)).then("n")
				.otherwise("t");

		NumberExpression<Integer> active = Expressions.cases()
				.when(P.active.eq(true)
						.and(W.active.eq(true))
						.and(W.shopActive.eq(true))
						.and(W.orderActive.eq(true))
				).then(1)
				.otherwise(0);

		NumberExpression<Integer> dateDay = CCI.fromDate.dayOfMonth();
		return jpaQuery.select(
				Projections.constructor(
						Curated.class
						, CCI.collectionCategoryItemID
						, CCI.spotID
						, CCI.fromDate
						, CCI.collectionCategoryID
						, CCI.productID
						, CCI.collectionCategoryType
						, dateDay
						, monthType
						, active
				)
		)
				.from(CCI)
				.leftJoin(CCI.products,P)
				.leftJoin(CCI.wholeSaler,W)
				.where(
						CCI.fromDate.goe(startFromDate).and(
								CCI.fromDate.loe(endFromDate)
						)
				).orderBy(CCI.spotID.desc(),dateDay.desc(),CCI.collectionCategoryItemID.desc())
				.fetch();
	}
}
