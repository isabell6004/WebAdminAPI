package net.fashiongo.webadmin.data.repository.primary;

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
import net.fashiongo.webadmin.data.model.ad.*;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
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
		Timestamp timestampFd = Timestamp.valueOf(startFromDate);
		Timestamp timestampEd = Timestamp.valueOf(endFromDate);
		JPASQLQuery<Bidding> jpasqlQuery = new JPASQLQuery<Bidding>(entityManager,new MSSQLServer2012Templates());

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

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public ResultGetCategoryAdDetail up_wa_GetCategoryAdDetail(LocalDate categoryDate, int spotId) {
		LocalDateTime categoryDateTime = categoryDate == null ?  LocalDateTime.now() : categoryDate.atTime(0, 0, 0, 0);
		LocalDateTime startFromDate = categoryDateTime;
		LocalDateTime endFromDate = startFromDate.plusDays(1);

		List<Bidding2> bidding2List = this.findAllBidding2JoinCollectionCategory(startFromDate, endFromDate, spotId);
		List<CuratedBest> curatedBestList = this.findAllCuratedBestOrderBySpotIDDesc(startFromDate, spotId);
		return ResultGetCategoryAdDetail.builder()
				.bidding2List(bidding2List)
				.curatedBestList(curatedBestList)
				.build();
	}

	private List<Bidding2> findAllBidding2JoinCollectionCategory(LocalDateTime startFromDate, LocalDateTime endFromDate, int spotID) {
//		SELECT a.*, p.ProductName, w.CompanyName, i.UrlPath as ImageUrlRoot, w.DirName, prdi.ImageName as PictureGeneral
//	, CASE WHEN p.Active = 1 AND w.Active =1 AND w.ShopActive =1 AND w.OrderActive =1 THEN 1 ELSE 0 END AS Active
//		FROM (
//				select av.AdID, av.SpotID, av.WholeSalerID, av.VendorCategoryID as CollectionCategoryID, 0 as CollectionCategoryType, av.ActualPrice
//				,(select TOP 1 mai.ProductID from Map_AdVendor_Item as mai INNER JOIN Products as p on mai.ProductID=p.ProductID where mai.AdID=av.AdID AND p.Active=1 order by mai.MapID) as ProductID
//		,(select COUNT(mai.ProductID) from Map_AdVendor_Item as mai where mai.AdID=av.AdID) as ItemCount
//		from Ad_Vendor as av
//		where av.Active=1 and FromDate>=@FromDate and ToDate<@ToDate and av.SpotID=@SpotID
//	) as a
//		left outer join dbo.Products p on a.ProductID=p.ProductID
//		left outer join dbo.tblWholeSaler w on a.WholeSalerID = w.WholeSalerID
//		left outer join dbo.System_ImageServers i on w.ImageServerID = i.ImageServerID
//		left outer join dbo.Product_Image  prdi on p.ProductID = prdi.ProductID and prdi.ListOrder = 1
//		ORDER BY a.ActualPrice, a.AdID DESC

//		SELECT AV.AdID,AV.SpotID,AV.WholeSalerID,AV.VendorCategoryID AS CollectionCategoryID,0 AS CollectionCategoryType,AV.ActualPrice
//				, P.ProductID
//				, ISNULL(TEMP_COUNT.P_COUNT,0) AS ItemCount
//                ,P.ProductName,W.CompanyName,I.UrlPath AS ImageUrlRoot,W.DirName,PI.ImageName AS PictureGeneral
//		FROM Ad_Vendor AV LEFT JOIN (
//				SELECT SUB_AV.AdID AS AdID,MIN(SUB_MAVI.MapID) AS MAP_ID
//				FROM Ad_Vendor SUB_AV INNER JOIN Map_AdVendor_Item SUB_MAVI ON SUB_AV.AdID = SUB_MAVI.AdID
//				INNER JOIN Products SUB_P ON SUB_MAVI.ProductID = SUB_P.ProductID AND SUB_P.Active = 1
//				WHERE
//				SUB_AV.Active = 1
//				AND SUB_AV.FromDate>=@FromDate
//						AND SUB_AV.ToDate<@ToDate
//						AND SUB_AV.SpotID=@SpotID
//						GROUP BY SUB_AV.AdID
//		) TEMP_PRODUCT ON AV.AdID = TEMP_PRODUCT.AdID
//		LEFT JOIN (
//				SELECT SUB_AV.AdID AS AdID,COUNT(SUB_MAVI.MapID) AS P_COUNT
//		FROM Ad_Vendor SUB_AV INNER JOIN Map_AdVendor_Item SUB_MAVI ON SUB_AV.AdID = SUB_MAVI.AdID
//		WHERE
//		SUB_AV.Active = 1
//		AND SUB_AV.FromDate>=@FromDate
//				AND SUB_AV.ToDate<@ToDate
//				AND SUB_AV.SpotID=@SpotID
//				GROUP BY SUB_AV.AdID
//                            ) TEMP_COUNT ON AV.AdID = TEMP_COUNT.AdID
//		LEFT JOIN Map_AdVendor_Item MAVI ON AV.AdID = MAVI.AdID AND MAVI.MapID = TEMP_PRODUCT.MAP_ID
//		LEFT JOIN Products P ON P.ProductID = MAVI.ProductID
//		LEFT JOIN tblWholeSaler W ON AV.WholeSalerID = W.WholeSalerID
//		LEFT JOIN System_ImageServers I ON W.ImageServerID = I.ImageServerID
//		LEFT JOIN Product_Image PI ON P.ProductID = PI.ProductID AND PI.ListOrder = 1
//		WHERE
//		AV.Active = 1
//		AND AV.FromDate>=@FromDate
//				AND AV.ToDate<@ToDate
//				AND AV.SpotID=@SpotID
//				ORDER BY AV.ActualPrice, AV.AdID DESC
		Timestamp timestampFd = Timestamp.valueOf(startFromDate);
		Timestamp timestampEd = Timestamp.valueOf(endFromDate);

		JPASQLQuery<Bidding2> jpasqlQuery = new JPASQLQuery<Bidding2>(entityManager,new MSSQLServer2012Templates());
		QAdVendorEntity AV = new QAdVendorEntity("AV");
		QMapAdVendorItemEntity MAVI = new QMapAdVendorItemEntity("MAVI");
		QProductsEntity P = new QProductsEntity("P");
		QSystemImageServersEntity I = new QSystemImageServersEntity("I");
		QProductImageEntity PI = new QProductImageEntity("PI");

		QAdVendorEntity SUB_AV = new QAdVendorEntity("SUB_AV");
		QMapAdVendorItemEntity SUB_MAVI = new QMapAdVendorItemEntity("SUB_MAVI");
		QProductsEntity SUB_P = new QProductsEntity("SUB_P");
		QSimpleWholeSalerEntity W = new QSimpleWholeSalerEntity("W");

		Path<Object> temp_product = ExpressionUtils.path(Object.class, "TEMP_PRODUCT");
		Path<Object> temp_count = ExpressionUtils.path(Object.class, "TEMP_COUNT");

		Path<Object> temp_product_adid = ExpressionUtils.path(Integer.class,temp_product, "ADID");
		Path<Object> temp_product_mapid = ExpressionUtils.path(Integer.class,temp_product, "MAP_ID");
		Path<Object> temp_count_adid = ExpressionUtils.path(Integer.class,temp_count, "ADID");
		NumberPath<Integer> temp_count_map_count = Expressions.numberPath(Integer.class, temp_count, "MAP_COUNT");
		NumberExpression<Integer> collectionCategoryType = Expressions.asNumber(0).as("CollectionCategoryType");

		NumberExpression<Integer> active = Expressions.cases()
				.when(P.active.eq(true)
						.and(W.active.eq(true))
						.and(W.shopActive.eq(true))
						.and(W.orderActive.eq(true))
				).then(1)
				.otherwise(0);

		jpasqlQuery
				.select(
						Projections.constructor(
								Bidding2.class
								, AV.adID
								, AV.spotID
								, AV.wholeSalerID
								, AV.vendorCategoryID
								, collectionCategoryType
								, AV.actualPrice
								, P.productID
								, Expressions.cases().when(temp_count_map_count.isNull()).then(0).otherwise(temp_count_map_count).as("ItemCount")
								, P.productName
								, W.companyName
								, I.urlPath
								, W.dirName
								, PI.imageName
								, active
						)
				)
				.from(AV)
				.leftJoin(
						JPAExpressions.select(SUB_AV.adID,SUB_MAVI.mapID.min().as("MAP_ID"))
								.from(SUB_AV)
								.innerJoin(SUB_MAVI).on(SUB_AV.adID.eq(SUB_MAVI.adID))
								.innerJoin(SUB_P).on(SUB_MAVI.productID.eq(SUB_P.productID).and(SUB_P.active.eq(true)))
								.where(SUB_AV.active.eq(true).and(SUB_AV.fromDate.goe(timestampFd)).and(SUB_AV.fromDate.lt(timestampEd)).and(SUB_AV.spotID.eq(spotID)))
								.groupBy(SUB_AV.adID)
						,temp_product).on(AV.adID.eq(temp_product_adid))
				.leftJoin(
						JPAExpressions.select(SUB_AV.adID,SUB_MAVI.mapID.count().as("MAP_COUNT"))
								.from(SUB_AV)
								.innerJoin(SUB_MAVI).on(SUB_AV.adID.eq(SUB_MAVI.adID))
								.where(SUB_AV.active.eq(true).and(SUB_AV.fromDate.goe(timestampFd)).and(SUB_AV.fromDate.lt(timestampEd)).and(SUB_AV.spotID.eq(spotID)))
								.groupBy(SUB_AV.adID)
						,temp_count).on(AV.adID.eq(temp_count_adid))
				.leftJoin(MAVI).on(AV.adID.eq(MAVI.adID).and(MAVI.mapID.eq(temp_product_mapid)))
				.leftJoin(P).on(MAVI.productID.eq(P.productID))
				.leftJoin(W).on(AV.wholeSalerID.eq(W.wholeSalerId))
				.leftJoin(I).on(I.imageServerID.eq(W.imageServerID))
				.leftJoin(PI).on(P.productID.eq(PI.productID).and(PI.listOrder.eq(1)))
				.where(AV.active.eq(true).and(AV.fromDate.goe(timestampFd)).and(AV.fromDate.lt(timestampEd)).and(AV.spotID.eq(spotID)))
				.orderBy(AV.actualPrice.asc(),AV.adID.desc());

		return jpasqlQuery.fetch()
				.stream()
				.distinct()
				.collect(Collectors.toList());
	}

	private List<CuratedBest> findAllCuratedBestOrderBySpotIDDesc(LocalDateTime dateTime, int spotId) {
		//		select CollectionCategoryItemID, SpotID, w.WholeSalerID, w.CompanyName, CollectionCategoryID, CollectionCategoryType
//				, p.ProductID, p.ProductName, i.UrlPath as ImageUrlRoot, w.DirName, prdi.ImageName as PictureGeneral
//	, CASE WHEN p.Active = 1 AND w.Active =1 AND w.ShopActive =1 AND w.OrderActive =1 THEN 1 ELSE 0 END AS Active
//		from CollectionCategoryItem as cc
//		left outer join dbo.Products p on cc.ProductID=p.ProductID
//		left outer join dbo.tblWholeSaler w on cc.WholeSalerID = w.WholeSalerID
//		left outer join dbo.System_ImageServers i on w.ImageServerID = i.ImageServerID
//		left outer join dbo.Product_Image  prdi on p.ProductID = prdi.ProductID and prdi.ListOrder = 1
//		where FromDate=@FromDate and SpotID=@SpotID
//				ORDER BY Active, cc.CollectionCategoryItemID DESC
		JPASQLQuery<CuratedBest> jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		QCollectionCategoryItemEntity CC = new QCollectionCategoryItemEntity("CC");
		QProductsEntity P = new QProductsEntity("P");
		QProductImageEntity PI = new QProductImageEntity("PI");
		QSimpleWholeSalerEntity W = new QSimpleWholeSalerEntity("W");
		QSystemImageServersEntity I = new QSystemImageServersEntity("I");

		NumberExpression<Integer> active = Expressions.cases()
				.when(P.active.eq(true)
						.and(W.active.eq(true))
						.and(W.shopActive.eq(true))
						.and(W.orderActive.eq(true))
				).then(1)
				.otherwise(0);

		jpasqlQuery.select(
					Projections.constructor(
							CuratedBest.class
							, CC.collectionCategoryItemID
							, CC.spotID
							, W.wholeSalerId
							, W.companyName
							, CC.collectionCategoryID
							, CC.collectionCategoryType
							, P.productID
							, P.productName
							, I.urlPath
							, W.dirName
							, PI.imageName
							, active
					)
				).from(CC)
				.leftJoin(P).on(CC.productID.eq(P.productID))
				.leftJoin(W).on(CC.wholeSalerID.eq(W.wholeSalerId))
				.leftJoin(I).on(I.imageServerID.eq(W.imageServerID))
				.leftJoin(PI).on(P.productID.eq(PI.productID).and(PI.listOrder.eq(1)))
				.where(CC.fromDate.eq(dateTime).and(CC.spotID.eq(spotId)))
				.orderBy(active.asc(),CC.collectionCategoryItemID.desc());

		return jpasqlQuery.fetch()
				.stream()
				.distinct()
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public ResultGetCategoryAdList up_wa_GetCategoryAdList(LocalDate categoryDate) {
		LocalDateTime categoryDateTime = categoryDate == null ?  LocalDateTime.now() : categoryDate.atTime(0, 0, 0, 0);
		LocalDateTime startFromDate = categoryDateTime;
		LocalDateTime endFromDate = startFromDate.plusDays(1);

		List<CollectionCategoryWithCounts> collectionCategoryWithCountsList = this.findAllCollectorCategoryWithCountJoinAdPageSpot(true, 0);
		List<Bidding2> bidding2List = this.findAllBidding2JoinCollectionCategory(startFromDate, endFromDate);
		List<CuratedBest> curatedBestList = this.findAllCuratedBestOrderBySpotIDDesc(startFromDate);

		return ResultGetCategoryAdList.builder()
				.collectionCategoryWithCountsList(collectionCategoryWithCountsList)
				.bidding2List(bidding2List)
				.curatedBestList(curatedBestList)
				.build();
	}


	private List<CollectionCategoryWithCounts> findAllCollectorCategoryWithCountJoinAdPageSpot(boolean active, int spotId) {
		QCollectionCategoryEntity CC = QCollectionCategoryEntity.collectionCategoryEntity;
		QCollectionCategoryEntity SUB_CC = new QCollectionCategoryEntity("SUB_CC");
		QAdPageSpotEntity APS = QAdPageSpotEntity.adPageSpotEntity;

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
						, APS.categoryId
						, JPAExpressions.select(SUB_CC.collectionCategoryID.count())
								.from(SUB_CC)
								.where(SUB_CC.spotID.eq(CC.spotID)
										.and(SUB_CC.parentCollectionCategoryID.eq(CC.collectionCategoryID)))
						, bidCount
						, curatedCount
						, bestCount
						, notCount
				)
		)
				.from(CC)
				.leftJoin(CC.pageSpotEntity, APS)
				.where(CC.active.eq(active).and(CC.spotID.gt(spotId)))
				.orderBy(CC.spotID.asc(), CC.lvl.asc(), CC.listOrder.asc(), CC.collectionCategoryID.asc());

		return jpaQuery.fetch();
	}


	private List<Bidding2> findAllBidding2JoinCollectionCategory(LocalDateTime startFromDate, LocalDateTime endFromDate) {
		Timestamp timestampFd = Timestamp.valueOf(startFromDate);
		Timestamp timestampEd = Timestamp.valueOf(endFromDate);

		JPASQLQuery<Bidding2> jpasqlQuery = new JPASQLQuery<Bidding2>(entityManager,new MSSQLServer2012Templates());
		QAdVendorEntity AV = new QAdVendorEntity("AV");
		QMapAdVendorItemEntity MAVI = new QMapAdVendorItemEntity("MAVI");
		QProductsEntity P = new QProductsEntity("P");
		QSystemImageServersEntity I = new QSystemImageServersEntity("I");
		QProductImageEntity PI = new QProductImageEntity("PI");

		QAdVendorEntity SUB_AV = new QAdVendorEntity("SUB_AV");
		QMapAdVendorItemEntity SUB_MAVI = new QMapAdVendorItemEntity("SUB_MAVI");
		QProductsEntity SUB_P = new QProductsEntity("SUB_P");
		QSimpleWholeSalerEntity W = new QSimpleWholeSalerEntity("W");
		QCollectionCategoryEntity CC = new QCollectionCategoryEntity("CC");

		Path<Object> temp_product = ExpressionUtils.path(Object.class, "TEMP_PRODUCT");
		Path<Object> temp_count = ExpressionUtils.path(Object.class, "TEMP_COUNT");
		Path<Object> temp_spot = ExpressionUtils.path(Object.class, "TEMP_SPOT");

		Path<Object> temp_product_adid = ExpressionUtils.path(Integer.class,temp_product, "ADID");
		Path<Object> temp_product_mapid = ExpressionUtils.path(Integer.class,temp_product, "MAP_ID");
		Path<Object> temp_count_adid = ExpressionUtils.path(Integer.class,temp_count, "ADID");
		Path<Object> temp_spotid = ExpressionUtils.path(Integer.class,temp_spot, "SPOTID");

		NumberPath<Integer> temp_count_map_count = Expressions.numberPath(Integer.class, temp_count, "MAP_COUNT");
		NumberExpression<Integer> collectionCategoryType = Expressions.asNumber(0).as("CollectionCategoryType");

		NumberExpression<Integer> active = Expressions.cases()
				.when(P.active.eq(true)
						.and(W.active.eq(true))
						.and(W.shopActive.eq(true))
						.and(W.orderActive.eq(true))
				).then(1)
				.otherwise(0);

		jpasqlQuery
				.select(
						Projections.constructor(
								Bidding2.class
								, AV.adID
								, AV.spotID
								, AV.wholeSalerID
								, AV.vendorCategoryID
								, collectionCategoryType
								, AV.actualPrice
								, P.productID
								, Expressions.cases().when(temp_count_map_count.isNull()).then(0).otherwise(temp_count_map_count).as("ItemCount")
								, P.productName
								, W.companyName
								, I.urlPath
								, W.dirName
								, PI.imageName
								, active
						)
				)
				.from(AV)
				.leftJoin(
						JPAExpressions.select(SUB_AV.adID,SUB_MAVI.mapID.min().as("MAP_ID"))
								.from(SUB_AV)
								.innerJoin(SUB_MAVI).on(SUB_AV.adID.eq(SUB_MAVI.adID))
								.innerJoin(SUB_P).on(SUB_MAVI.productID.eq(SUB_P.productID).and(SUB_P.active.eq(true)))
								.where(SUB_AV.active.eq(true).and(SUB_AV.fromDate.goe(timestampFd)).and(SUB_AV.fromDate.lt(timestampEd)))
								.groupBy(SUB_AV.adID)
						,temp_product).on(AV.adID.eq(temp_product_adid))
				.leftJoin(
						JPAExpressions.select(SUB_AV.adID,SUB_MAVI.mapID.count().as("MAP_COUNT"))
								.from(SUB_AV)
								.innerJoin(SUB_MAVI).on(SUB_AV.adID.eq(SUB_MAVI.adID))
								.where(SUB_AV.active.eq(true).and(SUB_AV.fromDate.goe(timestampFd)).and(SUB_AV.fromDate.lt(timestampEd)))
								.groupBy(SUB_AV.adID)
						,temp_count).on(AV.adID.eq(temp_count_adid))
				.innerJoin(
						JPAExpressions.select(CC.spotID)
								.from(CC)
								.groupBy(CC.spotID)
						,temp_spot).on(AV.spotID.eq(temp_spotid))
				.leftJoin(MAVI).on(AV.adID.eq(MAVI.adID).and(MAVI.mapID.eq(temp_product_mapid)))
				.leftJoin(P).on(MAVI.productID.eq(P.productID))
				.leftJoin(W).on(AV.wholeSalerID.eq(W.wholeSalerId))
				.leftJoin(I).on(I.imageServerID.eq(W.imageServerID))
				.leftJoin(PI).on(P.productID.eq(PI.productID).and(PI.listOrder.eq(1)))
				.where(AV.active.eq(true).and(AV.fromDate.goe(timestampFd)).and(AV.fromDate.lt(timestampEd)))
				.orderBy(AV.actualPrice.asc(),AV.adID.desc());

		return jpasqlQuery.fetch()
				.stream()
				.distinct()
				.collect(Collectors.toList());
	}

	private List<CuratedBest> findAllCuratedBestOrderBySpotIDDesc(LocalDateTime dateTime) {
		JPASQLQuery<CuratedBest> jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		QCollectionCategoryItemEntity CC = new QCollectionCategoryItemEntity("CC");
		QProductsEntity P = new QProductsEntity("P");
		QProductImageEntity PI = new QProductImageEntity("PI");
		QSimpleWholeSalerEntity W = new QSimpleWholeSalerEntity("W");
		QSystemImageServersEntity I = new QSystemImageServersEntity("I");

		NumberExpression<Integer> active = Expressions.cases()
				.when(P.active.eq(true)
						.and(W.active.eq(true))
						.and(W.shopActive.eq(true))
						.and(W.orderActive.eq(true))
				).then(1)
				.otherwise(0);

		jpasqlQuery.select(
				Projections.constructor(
						CuratedBest.class
						, CC.collectionCategoryItemID
						, CC.spotID
						, W.wholeSalerId
						, W.companyName
						, CC.collectionCategoryID
						, CC.collectionCategoryType
						, P.productID
						, P.productName
						, I.urlPath
						, W.dirName
						, PI.imageName
						, active
				)
		).from(CC)
				.leftJoin(P).on(CC.productID.eq(P.productID))
				.leftJoin(W).on(CC.wholeSalerID.eq(W.wholeSalerId))
				.leftJoin(I).on(I.imageServerID.eq(W.imageServerID))
				.leftJoin(PI).on(P.productID.eq(PI.productID).and(PI.listOrder.eq(1)))
				.where(CC.fromDate.eq(dateTime))
				.orderBy(CC.spotID.desc(), active.asc(),CC.collectionCategoryItemID.desc());

		return jpasqlQuery.fetch()
				.stream()
				.distinct()
				.collect(Collectors.toList());
	}
}
