package net.fashiongo.webadmin.data.repository.primary.procedure;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.JoinFlag;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.admin.SecurityMenus2;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendor;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendorAssigned;
import net.fashiongo.webadmin.data.model.buyer.OrderHistoryStatistics;
import net.fashiongo.webadmin.data.model.sitemgmt.*;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Repository
public class PrimaryProcedureRepositoryImpl implements PrimaryProcedureRepository {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Autowired
	private QueryDSLSQLFunctions queryDSLSQLFunctions;

	@Override
	@Transactional(value = "primaryTransactionManager")
	public ResultGetUserMappingVendor up_wa_GetUserMappingVendor(Integer userId, String alphabet, String companyType, String categories, String vendorType, String vendorKeyword) {

		List<UserMappingVendor> userMappingVendorList = findUserMappingVendor(userId, alphabet, companyType, categories, vendorType, vendorKeyword);
		long assignedCount = countMapWaUserVendor(userId);
		return ResultGetUserMappingVendor.builder()
				.userMappingVendorList(userMappingVendorList)
				.userMappingVendorAssignedList(Arrays.asList(
						UserMappingVendorAssigned.builder()
								.assigned((int)assignedCount)
						.build()
				))
				.build();
	}

	private List<UserMappingVendor> findUserMappingVendor(Integer userId, String alphabet, String companyType, String categories, String vendorType, String vendorKeyword) {

		//		IF @Alphabet != ''
//		BEGIN
//		SET @Filter=@Filter+' AND w.CompanyName LIKE ('''+@Alphabet+'%'')'
//		END
//
//		IF @CompanyType != ''
//		BEGIN
//		SET @Filter=@Filter+' AND w.CompanyTypeID in ('+@CompanyType+')'
//		END
//
//		IF @VendorKeyword != ''
//		BEGIN
//		SET @Filter=@Filter+' AND w.'+@VendorType+' LIKE ('''+@VendorKeyword+'%'')'
//		END
//
//		IF @Categorys !=''
//		BEGIN
//		SET @Filter=@Filter+' AND w.WholeSalerID in (select EntityID from [count] where CountTypeID = 2 and ReferenceID IN ('+ CONVERT(NVARCHAR,@Categorys)+'))'
//		END
//
//		SET @Sql = '
//		SELECT w.WholeSalerID, w.CompanyName, ISNULL(m.MapID,0) MapID
//		FROM [dbo].[tblWholeSaler] AS w
//		LEFT OUTER JOIN Map_Wa_User_Vendor AS m ON w.WholeSalerID=m.VendorID AND m.UserID='+CONVERT(NVARCHAR,@UserID)+
//		' WHERE w.Active = 1 AND w.ShopActive = 1' + @Filter + ' ORDER BY m.UserID DESC, w.CompanyName'

		JPAQuery<UserMappingVendor> jpaQuery = new JPAQuery(entityManager);
		QSimpleWholeSalerEntity W = new QSimpleWholeSalerEntity("W");
		QMapWaUserVendorEntity M = new QMapWaUserVendorEntity("M");
		QCountEntity C = new QCountEntity("C");

		BooleanExpression predicate = W.active.eq(true).and(W.shopActive.eq(true));

		if(StringUtils.isEmpty(alphabet) == false) {
			predicate = predicate.and(W.companyName.startsWith(alphabet));
		}

//		if(StringUtils.isEmpty(companyType) == false) {
//			predicate = predicate.and(W.companyType.in(companyType));
//		}

		if(StringUtils.isEmpty(vendorKeyword) == false) {
			StringPath vendorTypePath = Expressions.stringPath(W, vendorType);
			predicate = predicate.and(vendorTypePath.startsWith(vendorKeyword));
		}

		if(StringUtils.isEmpty(categories) == false) {
			List<Integer> categoryList = Arrays.asList(categories.split(","))
					.stream()
					.map(Integer::valueOf)
					.collect(Collectors.toList());

			predicate = predicate.and(W.wholeSalerId.in(
					JPAExpressions.select(C.entityID)
					.from(C)
					.where(C.countTypeID.eq(2).and(C.referenceID.in(categoryList)))

			));
		}

		jpaQuery.select(
				Projections.constructor(
						UserMappingVendor.class
						, W.wholeSalerId
						, W.companyName
						, queryDSLSQLFunctions.isnull(Integer.class,M.mapID,0)
				)
		).from(W)
				.leftJoin(W.mapWaUserVendorEntities,M).on(M.userID.eq(userId)) // simplewhol0_.WholeSalerID=mapwauserv1_.VendorID and ( mapwauserv1_.UserID=?)
				.where(predicate)
				.orderBy(M.userID.desc(),W.companyName.asc());

		return jpaQuery.fetch()
				.stream()
				.distinct()
				.collect(Collectors.toList());
	}

	private long countMapWaUserVendor(Integer userId) {
//		SET @AssignVendor = '
//		SELECT COUNT(MapID) AS Assigned FROM Map_Wa_User_Vendor AS m
//		WHERE m.UserID='+CONVERT(NVARCHAR,@UserID)
		QMapWaUserVendorEntity M = new QMapWaUserVendorEntity("M");
		QSimpleWholeSalerEntity W = new QSimpleWholeSalerEntity("W");	
			
		JPAQuery jpaQuery = new JPAQuery(entityManager);

		jpaQuery.select(M)		
				.from(M)	
					.innerJoin(W).on(W.wholeSalerId.eq(M.vendorID).and(M.userID.eq(userId)))
				    .where(W.active.eq(true).and(W.shopActive.eq(true)));

		return jpaQuery.fetchCount();
	}


	@Override
	@Transactional(value = "primaryTransactionManager")
	public List<SecurityMenus2> up_wa_GetSecurityMenus2(String menuName, Integer parentMenuId, Integer applicationId, Integer active) {
//		select	m.MenuID,
////				m.Name as MenuName,
////		(case when m.ParentID is null then 0 else m.ParentID end) as ParentID,
////		m2.Name  as ParentMenuName ,
////				r.ResourceID,
////				r.Name as ResourceName,
////
////		m.RoutePath,
////				m.MenuIcon,
////
////				m.ApplicationID,
////				m.ListOrder,
////				m.Visible,
////				m.Active
////
////		from [security.Menu] m
////		left OUTER join [security.Menu] m2 on m2.MenuID = m.ParentID
////		left OUTER join [security.Resource] r on r.ResourceID = m.ResourceID
////		where
////		m.Name like '%' + @menuName + '%'
////		and (m.ParentID = @parentMenuId or @parentMenuId = 0)
////		and (m.ApplicationID = @applicationId or @applicationId = 0)
////		and ((@active=2) or (m.active=@active))
////		order by m.MenuID asc

		JPAQuery<SecurityMenus2> jpaQuery = new JPAQuery<>(entityManager);

		QSecurityMenuEntity M = new QSecurityMenuEntity("M");
		QSecurityMenuEntity M2 = new QSecurityMenuEntity("M2");
		QSecurityResourceEntity R = new QSecurityResourceEntity("R");
		Expression<Integer> ZERO = Expressions.constant(0);
		NumberExpression<Integer> PARENT_MENU_ID = Expressions.asNumber(parentMenuId);
		NumberExpression<Integer> APPLICATION_ID = Expressions.asNumber(applicationId);
		NumberExpression<Integer> ACTIVE = Expressions.asNumber(active);

		jpaQuery.select(
				Projections.constructor(
						SecurityMenus2.class
						, M.menuID
						, M.name
						, queryDSLSQLFunctions.isnull(Integer.class,M.parentID,0)
						, M2.name
						, R.resourceID
						, R.name
						, M.routePath
						, M.menuIcon
						, M.applicationID
						, M.listOrder
						, M.visible
						, M.active
				)
		).from(M)
				.leftJoin(M.securityParentMenuEntity,M2)
				.leftJoin(M.securityResourceEntity,R)
				.where(
						M.name.contains(menuName)
						.and(
								M.parentID.eq(parentMenuId).or(PARENT_MENU_ID.eq(ZERO))
						).and(M.applicationID.eq(applicationId).or(APPLICATION_ID.eq(ZERO))
						).and(ACTIVE.eq(active).or(M.active.eq(active > 0 ? true : false)))
				).orderBy(M.menuID.asc());

		return jpaQuery.fetch()
				.stream()
				.distinct()
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(value = "primaryTransactionManager")
	public ResultGetCollectionCategory up_wa_GetCollectionCategory(Integer categoryID, Integer expandAll) {
		if(expandAll == null) expandAll = 1;

		List<SitemgmtCollectionCategory> collectionCategories = this.getCollectionCategory(categoryID, expandAll);

		List<SitemgmtMapCollectionCategory> mapCollectionCategories = new ArrayList<>();
		List<SitemgmtAdPageSpot> adPageSpots = new ArrayList<>();
		List<SitemgmtCategory> categories = new ArrayList<>();

		if(categoryID != null && categoryID > 0) {
			mapCollectionCategories = this.getMapCollectionCategory(categoryID);
			adPageSpots = this.getAdPageSpot();
			categories = this.getCategory();
		}

		return ResultGetCollectionCategory.builder()
				.collectionCategories(collectionCategories)
				.mapCollectionCategories(mapCollectionCategories)
				.adPageSpots(adPageSpots)
				.categories(categories)
				.build();
	}

	private List<SitemgmtCollectionCategory> getCollectionCategory(Integer categoryID, Integer expandAll) {
		QCollectionCategoryEntity C = new QCollectionCategoryEntity("C");
		QCollectionCategoryEntity SUB_C = new QCollectionCategoryEntity("SUB_C");

		NumberExpression<Integer> expended = Expressions.asNumber(expandAll == 0 ? 0 : 1);
		NumberExpression<Long> ZERO = Expressions.asNumber(Long.valueOf(0));

		JPAQuery<SitemgmtCollectionCategory> query = new JPAQuery<>(entityManager);

		if(categoryID == 0) {
			query.select(Projections.constructor(SitemgmtCollectionCategory.class,
					C.collectionCategoryID,
					queryDSLSQLFunctions.isnull(Integer.class, C.parentCollectionCategoryID, 0),
					C.collectionCategoryName,
					C.lvl,
					C.listOrder,
					C.active,
					expended,
					JPAExpressions.select(SUB_C.collectionCategoryID.count().as("NodeCnt")).from(SUB_C).where(SUB_C.parentCollectionCategoryID.eq(C.collectionCategoryID)),
					C.spotID,
					C.serviceInUse,
					C.vendorType,
					C.vendorTierGroup,
					C.orderBy,
					C.modifiedBy,
					C.modifiedOn))
					.from(C);
		} else {
			query.select(Projections.constructor(SitemgmtCollectionCategory.class,
					C.collectionCategoryID,
					queryDSLSQLFunctions.isnull(Integer.class, C.parentCollectionCategoryID, 0),
					C.collectionCategoryName,
					C.lvl,
					C.listOrder,
					C.active,
					expended,
					ZERO,
					C.spotID,
					C.serviceInUse,
					C.vendorType,
					C.vendorTierGroup,
					C.orderBy,
					C.modifiedBy,
					C.modifiedOn))
					.from(C);
		}

		if(categoryID != 0) {
			query.where(C.collectionCategoryID.eq(categoryID));
		}
		query.orderBy(C.listOrder.asc(),C.collectionCategoryName.asc());

		return query.fetch().stream().distinct().collect(Collectors.toList());
	}

	private List<SitemgmtMapCollectionCategory> getMapCollectionCategory(Integer categoryID) {
		QMapCollectionCategoryEntity A = new QMapCollectionCategoryEntity("A");
		QCategoryEntity B = new QCategoryEntity("B");

		JPAQuery<SitemgmtMapCollectionCategory> query = new JPAQuery<>(entityManager);
		query.select(Projections.constructor(SitemgmtMapCollectionCategory.class,
				A.mapID,
				A.collectionCategoryID,
				A.categoryID,
				B.categoryName))
				.from(A)
				.innerJoin(B).on(A.categoryID.eq(B.categoryId))
				.where(A.collectionCategoryID.eq(categoryID));

		return query.fetch().stream().distinct().collect(Collectors.toList());
	}

	private List<SitemgmtAdPageSpot> getAdPageSpot() {
		QAdPageSpotEntity adPageSpot = QAdPageSpotEntity.adPageSpotEntity;

		JPAQuery<SitemgmtAdPageSpot> query = new JPAQuery<>(entityManager);
		query.select(Projections.constructor(SitemgmtAdPageSpot.class,
				adPageSpot.spotId,
				adPageSpot.spotName))
				.from(adPageSpot)
				.where(adPageSpot.pageId.eq(1).and(adPageSpot.active.eq(true).and(adPageSpot.categoryId.isNotNull())));

		return query.fetch().stream().distinct().collect(Collectors.toList());
	}

	private List<SitemgmtCategory> getCategory() {
		QCategoryEntity category = QCategoryEntity.categoryEntity;

		JPAQuery<SitemgmtCategory> query = new JPAQuery<>(entityManager);
		query.select(Projections.constructor(SitemgmtCategory.class,
                category.categoryId,
                category.categoryName))
				.from(category)
				.where(category.active.eq(true).and(category.lvl.eq(1)))
				.orderBy(category.listOrder.asc());

		return query.fetch().stream().distinct().collect(Collectors.toList());
	}

	@Override
	@Transactional(value = "primaryTransactionManager")
	public List<CategoryList> up_wa_GetCategoryList(Integer categoryID, Integer expandAll) {
		QCategoryEntity C = new QCategoryEntity("C");
		QCategoryEntity SUB_C = new QCategoryEntity("SUB_C");
		QMapCollectionCategoryEntity MCC = new QMapCollectionCategoryEntity("MCC");

		NumberExpression<Integer> expended = Expressions.asNumber(expandAll == null ? 1 : expandAll == 0 ? 0 : 1);

		Expression<Integer> constantZERO = Expressions.constant(0);

		categoryID = categoryID == null ? 0 : categoryID;
		BooleanExpression categoryIDZero = Expressions.asNumber(categoryID).eq(constantZERO);

		JPASQLQuery<CategoryList> jpasqlQuery = new JPASQLQuery<CategoryList>(entityManager, new MSSQLServer2012Templates());

		jpasqlQuery.select(Projections.constructor(CategoryList.class,
				C.categoryId,
				C.parentParentCategoryId,
				C.parentCategoryId,
				C.categoryName,
				C.categoryDescription,
				C.titleImage,
				C.isLandingPage,
				C.isFeatured,
				C.lvl,
				C.listOrder,
				C.active,
				expended,
				SQLExpressions.select(SUB_C.categoryId.count().as("NodeCnt")).from(SUB_C).where(SUB_C.parentCategoryId.eq(C.categoryId)),
				SQLExpressions.select(MCC.collectionCategoryID.as("CollectionCategoryID")).from(MCC).where(MCC.categoryID.eq(C.categoryId)).offset(0).limit(1)))
				.from(C)
				.where(C.categoryId.eq(categoryID).or(categoryIDZero))
				.orderBy(C.listOrder.asc(), C.categoryName.asc());

		return jpasqlQuery.fetch().stream().distinct().collect(Collectors.toList());
	}

	@Override
	@Transactional(value = "primaryTransactionManager")
	public Page<TodayDealDetail> up_wa_GetAdminTodayDeal(Integer pageNumber, Integer pageSize, Integer wholeSalerID, String checkedCompanyNo, Integer categoryId, BigDecimal priceFrom, BigDecimal priceTo, Date dateFrom, Date dateTo, Boolean isActive, String orderBy) {
//if @OrderBy is null
//set @OrderBy = 'FromDate desc'
//
//set @columnlist = 'TodayDealID, Title, ProductID, FromDate, ToDate, TodayDealPrice, Active, AppliedOn, ApprovedOn,  UnitPrice, ProductName, CompanyName, Case When Active = 1 Then ''Approved'' Else ''Pending'' End As Status, CompanyTypeID, CompanyTypeCode
//		,CategoryID,OrgCategoryID,CategoryName, WholeSalerID '
//
//set @filter = '1=1'
//
//if @WholeSalerID != 0
//set @filter = @filter + ' and WholeSalerID = ' + CONVERT(nvarchar(50), @WholeSalerID)
//
//if @CheckedCompanyNo is not null
//set @filter = @filter + ' and CompanyTypeID In (' + @CheckedCompanyNo + ') '
//
//if @CategoryID != 100
//set @filter = @filter + ' and OrgCategoryID = ' + CONVERT(nvarchar(50), @CategoryID)
//
//if @PriceFrom is not null
//set @filter = @filter + ' and TodayDealPrice >= ' + CONVERT(nvarchar(50), @PriceFrom)
//
//if @PriceTo is not null
//set @filter = @filter + ' and TodayDealPrice <= ' + CONVERT(nvarchar(50), @PriceTo)
//
//if @DateFrom is not null
//set @filter = @filter + ' and Convert(nvarchar(10), FromDate, 120) >= ''' + CONVERT(nvarchar(10), @DateFrom, 120) + ''''
//
//if @DateTo is not null
//set @filter = @filter + ' and Convert(nvarchar(10), ToDate, 120) <= ''' + CONVERT(nvarchar(10), @DateTo, 120) + ''''
//
//		--set @filter = @filter + ' and Active = 1 '
//if @Active is not null
//set @filter = @filter + ' and Active = ''' + CONVERT(nvarchar(50), @Active) + ''''
//
//exec up_GetPage @PageNum, @PageSize, 'vw_wa_TodayDealWebAdmin', @columnlist, @filter, @OrderBy

//CREATE view [dbo].[vw_wa_TodayDealWebAdmin]
//AS
//		select
//t.TodayDealID, t.Title, t.Description, t.FromDate, t.ToDate, t.TodayDealPrice,
//		t.AppliedOn, t.ApprovedOn, t.Active, t.ModifiedBy, t.ModifiedOn,
//		p.ProductID, p.ProductName, i.UrlPath ImageUrlRoot, p.DirName, prdi.ImageName PictureGeneral,
//		p.CompanyName, p.WholeSalerID, p.Price UnitPrice, p.CompanyTypeID,p.CompanyTypeName, ,p.CategoryID,OrgCategoryID,p.CategoryName
//from
//TodayDeal t
//left outer join vw_wa_ProductDetail p on t.ProductID = p.ProductID
//Left Outer Join Product_Image prdi on t.ProductID = prdi.ProductID and prdi.ListOrder = 1
//inner join System_ImageServers i on p.ImageServerID = i.ImageServerID
		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;

		JPASQLQuery<TodayDealDetail> jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		QTodayDealEntity T = QTodayDealEntity.todayDealEntity;
		QProductImageEntity PRDI = QProductImageEntity.productImageEntity;
		QSystemImageServersEntity I = QSystemImageServersEntity.systemImageServersEntity;
		JPQLQuery vw_wa_ProductDetail = vw_wa_ProductDetail();
		Path<Object> P = ExpressionUtils.path(Object.class, "P");
		NumberPath<Integer> pathProductID = Expressions.numberPath(Integer.class,P, "ProductID");
		StringPath pathProductName = Expressions.stringPath(P, "ProductName");
		StringPath pathCompanyName = Expressions.stringPath(P, "CompanyName");
		StringPath pathCategoryName = Expressions.stringPath(P, "CategoryName");
		NumberPath<BigDecimal> pathUnitPrice = Expressions.numberPath(BigDecimal.class,P, "Price");
		NumberPath<Integer> pathImageServerID = Expressions.numberPath(Integer.class,P, "ImageServerID");
		NumberPath<Integer> pathCompanyTypeID = Expressions.numberPath(Integer.class,P, "CompanyTypeID");
		NumberPath<Integer> pathWholeSalerID = Expressions.numberPath(Integer.class, P, "WholeSalerID");
		NumberPath<Integer> pathCategoryID = Expressions.numberPath(Integer.class, P, "CategoryID");
		NumberPath<Integer> pathOrgCategoryID = Expressions.numberPath(Integer.class, P, "OrgCategoryID");
		StringExpression statusExpression = Expressions.cases()
				.when(T.active.eq(true)).then("Approved")
				.otherwise("Pending").as("Status");

		StringExpression comPanyTypeCode = Expressions.cases()
				.when(pathCompanyTypeID.eq(1)).then("M")
				.when(pathCompanyTypeID.eq(2)).then("W")
				.otherwise("D").as("ComPanyTypeCode");

		String orderByColumn = orderBy == null ? "FromDate desc" : orderBy;
		String[] orderyColumns = orderByColumn.split(" ");
		String orderyColumn= orderyColumns[0];
		StringPath sort = Expressions.stringPath(orderyColumn);
		boolean isAsc = orderyColumns.length == 2 && orderyColumns[1].equalsIgnoreCase("asc") ? true : false;
		OrderSpecifier<String> orderSpecifier = sort.desc();
		if(isAsc) {
			orderSpecifier = sort.asc();
		}
		Expression<Integer> constant = Expressions.constant(1);
		BooleanExpression expression = Expressions.asNumber(1).eq(constant);

		if(checkedCompanyNo != null) {
			List<Integer> checkedCompanyIds = Arrays.asList(checkedCompanyNo.split(",")).stream()
					.map(Integer::valueOf)
					.collect(Collectors.toList());
			expression = expression.and(pathCompanyTypeID.in(checkedCompanyIds));
		}

		if(wholeSalerID != 0) {
			expression = expression.and(pathWholeSalerID.eq(wholeSalerID));
		}

		if(categoryId != 100) {
			expression = expression.and(pathOrgCategoryID.eq(categoryId));
		}

		if(priceFrom != null) {
			expression = expression.and(T.todayDealPrice.goe(priceFrom));
		}

		if(priceTo != null) {
			expression = expression.and(T.todayDealPrice.loe(priceTo));
		}

		if(dateFrom != null) {
			expression = expression.and(T.fromDate.goe(new Timestamp(dateFrom.getTime())));
		}

		if(dateTo != null) {
			expression = expression.and(T.toDate.loe(new Timestamp(dateTo.getTime())));
		}

		if(isActive != null) {
			expression = expression.and(T.active.eq(isActive));
		}

		jpasqlQuery.select(
					Projections.constructor(
							TodayDealDetail.class
							, T.todayDealId
							, T.title
							, pathProductID
							, T.fromDate
							, T.toDate
							, T.todayDealPrice
							, T.active
							, T.appliedOn
							, T.approvedOn
							, pathUnitPrice.as("UnitPrice")
							, pathProductName
							, pathCompanyName
							, statusExpression
							, pathCompanyTypeID
							, comPanyTypeCode
							, pathCategoryID
							, pathOrgCategoryID
							, pathCategoryName
							, pathWholeSalerID
					)
				)
				.from(T)
				.leftJoin(vw_wa_ProductDetail,P).on(T.productId.eq(pathProductID))
				.leftJoin(PRDI).on(PRDI.productID.eq(T.productId).and(PRDI.listOrder.eq(1)))
				.leftJoin(I).on(I.imageServerID.eq(pathImageServerID))
				.where(expression)
				.orderBy(orderSpecifier,T.todayDealId.asc())
				.offset(offset)
				.limit(limit);

		QueryResults<TodayDealDetail> tupleQueryResults = jpasqlQuery.fetchResults();
		List<TodayDealDetail> results = tupleQueryResults.getResults();
		long resultsTotal = tupleQueryResults.getTotal();

		PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize);
		return PageableExecutionUtils.getPage(results,pageRequest,()-> resultsTotal);
	}

	private JPQLQuery vw_wa_ProductDetail() {
//CREATE VIEW [dbo].[vw_wa_ProductDetail]
//AS
//select	p.ProductID, p.WholeSalerID, p.ProductName, p.ProductName2, p.ProductDescription
//		, p.CategoryID,p.ParentParentCategoryID As OrgCategoryID,c.CategoryName, p.VendorCategoryID
//		, prdi.ImageName As PictureGeneral
//--, p.PictureGeneral, p.PictureEx01, p.PictureEx02, p.PictureEx03, p.PictureEx04
//		--, p.PictureEx05, p.PictureEx06, p.PictureEx07, p.PictureEx08, p.PictureEx09
//		, dbo.ufnMinValue(dbo.ufnMinValue(p.UnitPrice, p.UnitPrice1), p.UnitPrice2) Price
//		, dbo.ufnMaxValue(dbo.ufnMaxValue(p.UnitPrice, p.UnitPrice1), p.UnitPrice2) PriceOld
//		, p.CallforPrice
//		, isnull(p.FabricDescription, 'Unspecified') FabricDescription
//		, isnull(p.MadeIn, 'Unspecified') MadeIn
//		, isnull(p.StockAvailability, 'Unspecified') StockAvailability
//		, p.LabelTypeID
//		, p.EvenColorYN, p.PrePackYN
//		, isnull(p.Active,0) as HotItems
//		, p.MinTQYNStyle, p.MinTQStyle
//		, p.DiscountYN
//		, w.CompanyName, w.DirName, i.UrlPath ImageUrlRoot
//		, p.Active,w.Active WholeSalerActive, w.ShopActive WholeSalerShopActive
//		, p.ActivatedOn, p.PackQtyTotal
//		, w.OrderNotice, cw.CompanyTypeID, cw.CompanyTypeName, w.ImageServerID
//from		dbo.Products p
//inner join dbo.tblWholeSaler w on p.WholeSalerID = w.WholeSalerID
//inner join dbo.System_ImageServers i on w.ImageServerID = i.ImageServerID
//inner join dbo.Code_WholeSalerCompanyType cw on w.CompanyTypeID = cw.CompanyTypeID
//left outer join dbo.category c on p.ParentParentCategoryID = c.CategoryID And c.Active = 1
//left outer join dbo.Product_Image  prdi on p.ProductID = prdi.ProductID and prdi.ListOrder = 1
//		-- left outer join dbo.Product_SearchableItem as ps on p.ProductID = ps.ProductID
//go
		QProductsEntity P = QProductsEntity.productsEntity;
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;
		QSystemImageServersEntity I = QSystemImageServersEntity.systemImageServersEntity;
		QCodeWholeSalerCompanyTypeEntity CW = QCodeWholeSalerCompanyTypeEntity.codeWholeSalerCompanyTypeEntity;
		QCategoryEntity C = QCategoryEntity.categoryEntity;
		QProductImageEntity PRDI = QProductImageEntity.productImageEntity;

		return JPAExpressions.select(
				P.productID
				, P.wholeSalerID
				, P.productName
				, P.productName2
				, P.productDescription
				, P.categoryID
				, P.parentParentCategoryID.as("OrgCategoryID")
				, C.categoryName
				, P.vendorCategoryID
				, PRDI.imageName.as("PictureGeneral")
				, queryDSLSQLFunctions.ufnMinValue(BigDecimal.class, queryDSLSQLFunctions.ufnMinValue(BigDecimal.class, P.unitPrice, P.unitPrice1), P.unitPrice2).as("Price")
				, queryDSLSQLFunctions.ufnMaxValue(BigDecimal.class, queryDSLSQLFunctions.ufnMaxValue(BigDecimal.class, P.unitPrice, P.unitPrice1), P.unitPrice2).as("PriceOld")
				, P.callforPrice
				, queryDSLSQLFunctions.isnull(String.class, P.fabricDescription, "Unspecified").as("FabricDescription")
				, queryDSLSQLFunctions.isnull(String.class, P.madeIn, "Unspecified").as("MadeIn")
				, queryDSLSQLFunctions.isnull(String.class, P.stockAvailability, "Unspecified").as("StockAvailability")
				, P.labelTypeID
				, P.evenColorYN
				, P.prePackYN
				, queryDSLSQLFunctions.isnull(Boolean.class, P.active, false).as("HotItems")
				, P.minTQYNStyle
				, P.minTQStyle
				, P.discountYN
				, W.companyName
				, W.dirName
				, I.urlPath.as("ImageUrlRoot")
				, P.active
				, W.active.as("WholeSalerActive")
				, W.shopActive.as("WholeSalerShopActive")
				, P.activatedOn
				, P.packQtyTotal
				, W.orderNotice
				, CW.companyTypeID
				, CW.companyTypeName
				, W.imageServerID
		)
				.from(P)
				.innerJoin(W).on(P.wholeSalerID.eq(W.wholeSalerId))
				.innerJoin(I).on(W.imageServerID.eq(I.imageServerID))
				.innerJoin(CW).on(CW.companyTypeID.eq(W.companyTypeID))
				.leftJoin(C).on(P.parentParentCategoryID.eq(C.categoryId).and(C.active.eq(true)))
				.leftJoin(PRDI).on(P.productID.eq(PRDI.productID).and(PRDI.listOrder.eq(1)));
	}

	@Override
	@Transactional(value = "primaryTransactionManager")
	public ResultGetVendorList up_GetVendorList() {
		JPAQuery<CategoryCount> categoryCountQuery = this.getCategoryCountQuery();
		JPAQuery<VendorSummary> vendorSummaryQuery = this.getVendorSummaryQuery();

		List<CategoryCount> categoryCounts = categoryCountQuery.fetch().stream().distinct().collect(Collectors.toList());
		List<VendorSummary> vendorSummaries = vendorSummaryQuery.fetch().stream().distinct().collect(Collectors.toList());

		return ResultGetVendorList.builder()
				.categoryCountlist(categoryCounts)
				.vendorSummarylist(vendorSummaries)
				.build();
	}

	private JPAQuery<CategoryCount> getCategoryCountQuery() {
		QProductsEntity P = new QProductsEntity("P");
		QCategoryEntity C = new QCategoryEntity("C");
		QCategoryEntity C2 = new QCategoryEntity("C2");
		QCategoryEntity C1 = new QCategoryEntity("C1");

		JPAQuery<CategoryCount> query = new JPAQuery<>(entityManager);

		query.select(Projections.constructor(CategoryCount.class,
				C1.categoryId,
				P.wholeSalerID.countDistinct()))
				.from(P)
				.innerJoin(C).on(P.categoryID.eq(C.categoryId))
				.innerJoin(C2).on(C.parentCategoryId.eq(C2.categoryId).or(C.categoryId.eq(C2.categoryId)))
				.innerJoin(C1).on(C2.parentCategoryId.eq(C1.categoryId).or(C2.categoryId.eq(C1.categoryId)).or(C.categoryId.eq(C1.categoryId)))
				.where(P.active.eq(true).and(C1.lvl.eq(1)))
				.groupBy(C1.categoryId);

		return query;
	}

	private JPAQuery<VendorSummary> getVendorSummaryQuery() {
		QProductsEntity P = new QProductsEntity("P");
		QCategoryEntity C = new QCategoryEntity("C");
		QSimpleWholeSalerEntity W = new QSimpleWholeSalerEntity("W");
		QSystemImageServersEntity I = new QSystemImageServersEntity("I");
		QProductImageEntity PRI = new QProductImageEntity("PRI");
		QVendorCategoryEntity VC = new QVendorCategoryEntity("VC");
		QProductVideoEntity PRV = new QProductVideoEntity("PRV");

		NumberExpression accessStatus = Expressions.asNumber(2);

		JPAQuery<VendorSummary> query = new JPAQuery<>(entityManager);

		query.select(Projections.constructor(VendorSummary.class,
				P.wholeSalerID,
				W.companyName,
				W.dirName,
				P.productID.countDistinct(),
				accessStatus))
				.from(P)
						.innerJoin(C).on(P.categoryID.eq(C.categoryId))
						.innerJoin(W).on(P.wholeSalerID.eq(W.wholeSalerId))
						.innerJoin(I).on(W.imageServerID.eq(I.imageServerID))
						.leftJoin(PRI).on(P.productID.eq(PRI.productID).and(PRI.listOrder.eq(1)))
						.innerJoin(VC).on(P.vendorCategoryID.eq(VC.vendorCategoryID))
						.leftJoin(PRV).on(P.productID.eq(PRV.productID).and(PRV.active.eq(true)))
				.where(P.active.eq(true).and(W.active.eq(true)).and(W.shopActive.eq(true)).and(W.orderActive.eq(true)))
				.groupBy(P.wholeSalerID, W.companyName, W.dirName)
				.orderBy(W.companyName.asc());

		return query;
	}

	@Override
	@Transactional(value = "primaryTransactionManager")
	public ResultGetAdminTodayDealCalendarList up_wa_GetAdminTodayDealCalendarList(Date sDate, Integer wholeSalerID) {
		List<ActiveTodayDealDetail> activeTodayDealDetails = (List<ActiveTodayDealDetail>) this.getTodayDealDetail(sDate, wholeSalerID, true);
		List<InactiveTodayDealDetail> inactiveTodayDealDetails = (List<InactiveTodayDealDetail>) this.getTodayDealDetail(sDate, wholeSalerID, false);

		return ResultGetAdminTodayDealCalendarList.builder()
				.activeTodayDealDetails(activeTodayDealDetails)
				.inactiveTodayDealDetails(inactiveTodayDealDetails)
				.build();
	}

	private JPQLQuery vwProductDetail() {
		QProductsEntity P = QProductsEntity.productsEntity;
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;
		QSystemImageServersEntity I = QSystemImageServersEntity.systemImageServersEntity;
		QProductImageEntity PRI = QProductImageEntity.productImageEntity;

		return JPAExpressions.select(
				P.productID
				, P.wholeSalerID
				, P.productName
				, P.productName2
				, P.productDescription
				, P.categoryID
				, P.vendorCategoryID
				, JPAExpressions.select(PRI.imageName.as("PictureGeneral")).from(PRI).where(P.productID.eq(PRI.productID).and(PRI.listOrder.eq(1)))
				, Expressions.cases().when(P.unitPrice1.eq(BigDecimal.valueOf(0))).then(P.unitPrice).otherwise(queryDSLSQLFunctions.ufnMinValue(BigDecimal.class, P.unitPrice, P.unitPrice1)).as("Price")
				, queryDSLSQLFunctions.ufnMaxValue(BigDecimal.class, P.unitPrice, P.unitPrice1).as("PriceOld")
				, P.callforPrice
				, queryDSLSQLFunctions.isnull(String.class, P.fabricDescription, "Unspecified").as("FabricDescription")
				, queryDSLSQLFunctions.isnull(String.class, P.madeIn, "Unspecified").as("MadeIn")
				, queryDSLSQLFunctions.isnull(String.class, P.stockAvailability, "Unspecified").as("StockAvailability")
				, P.labelTypeID
				, P.evenColorYN
				, P.prePackYN
				, P.hotItems
				, P.minTQYNStyle
				, P.minTQStyle
				, P.discountYN
				, W.companyName
				, W.dirName
				, I.urlPath.as("ImageUrlRoot")
				, P.active
				, W.active.as("WholeSalerActive")
				, W.shopActive.as("WholeSalerShopActive")
				, W.orderActive.as("WholeSalerOrderActive")
				, P.activatedOn
				, P.packQtyTotal
				, W.orderNotice
				, W.imageServerID
		)
				.from(P)
				.innerJoin(W).on(P.wholeSalerID.eq(W.wholeSalerId))
				.innerJoin(I).on(W.imageServerID.eq(I.imageServerID));
	}
	
	private List<?> getTodayDealDetail(Date sDate, Integer wholeSalerID, Boolean active) {
		QTodayDealEntity T = QTodayDealEntity.todayDealEntity;
		QProductsEntity PR = QProductsEntity.productsEntity;
		QProductImageEntity PRDI = QProductImageEntity.productImageEntity;
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;

		JPASQLQuery<?> jpasqlQuery = new JPASQLQuery(entityManager, new MSSQLServer2012Templates());
		JPQLQuery vwProductDetail = this.vwProductDetail();
		Path<Object> P = ExpressionUtils.path(Object.class, "P");
		NumberPath<Integer> pathProductID = Expressions.numberPath(Integer.class,P, "ProductID");
		StringPath pathProductName = Expressions.stringPath(P, "ProductName");
		StringPath pathImageUrlRoot = Expressions.stringPath(P, "ImageUrlRoot");
		StringPath pathDirName = Expressions.stringPath(P, "DirName");
		StringPath pathCompanyName = Expressions.stringPath(P, "CompanyName");
		NumberPath<BigDecimal> pathUnitPrice = Expressions.numberPath(BigDecimal.class,P, "Price");
		NumberPath<Integer> pathWholeSalerID = Expressions.numberPath(Integer.class, P, "WholeSalerID");
		BooleanPath pathOrderActive = Expressions.booleanPath(P, "WholeSalerOrderActive");

		Expression<Integer> constantZERO = Expressions.constant(0);

		wholeSalerID = wholeSalerID == null ? 0 : wholeSalerID;
		BooleanExpression wholeSalerIDZero = Expressions.asNumber(wholeSalerID).eq(constantZERO);
		BooleanExpression temp = Expressions.asBoolean(pathWholeSalerID.eq(wholeSalerID));

		Timestamp sDateTimestamp = new Timestamp(sDate.getTime());
		
		BooleanBuilder w = new BooleanBuilder();
		if (active) {
			w.and(wholeSalerIDZero
				.and(T.fromDate.eq(sDateTimestamp)
					.and(T.active.isTrue())
					.and(pathOrderActive.isTrue())
					)
			.or(wholeSalerIDZero.not()
				.and(
					pathWholeSalerID.eq(wholeSalerID)
					.and(T.fromDate.eq(sDateTimestamp))
					.and(T.active.isTrue())	
					.and(pathOrderActive.isTrue())
				)
			));
		} else {
			w.and(wholeSalerIDZero
				.and(T.fromDate.eq(sDateTimestamp)
					.and(T.active.isFalse().or(pathOrderActive.isFalse()))
					)
			.or(wholeSalerIDZero.not()
				.and(
					pathWholeSalerID.eq(wholeSalerID)
					.and(T.fromDate.eq(sDateTimestamp))
					.and(T.active.isFalse().or(pathOrderActive.isFalse()))	
				)
			));
		}

		jpasqlQuery.select(Projections.constructor(TodayDealDetailDto.class,
				T.todayDealId,
				T.title,
				T.description,
				T.fromDate,
				T.toDate,
				T.todayDealPrice,
				T.modifiedBy,
				T.modifiedOn,
				pathProductID,
				pathProductName,
				pathImageUrlRoot,
				pathDirName,
				PRDI.imageName.as("PictureGeneral"),
				pathCompanyName,
				pathWholeSalerID,
				pathUnitPrice.as("UnitPrice"),
				T.revokedOn,
				T.revokedBy,
				T.notes,
				pathOrderActive,
				T.active,			
				T.approvedOn,	
				T.appliedOn,
				T.createdByVendor,
				T.fromDate.year().as("sYear"),
				T.fromDate.month().as("sMonth")
				))
				.from(T)
				.leftJoin(vwProductDetail, P).on(T.productId.eq(pathProductID))
				.leftJoin(PRDI).on(pathProductID.eq(PRDI.productID).and(PRDI.listOrder.eq(1)))			
				.where(w)
				.orderBy(T.todayDealId.asc(), T.fromDate.desc());
		
		return jpasqlQuery.fetch();
	}

	@Override
	public ResultGetCategoryVendorList up_wa_GetCategoryVendorList(Integer categoryID, String vendorName) {
		return ResultGetCategoryVendorList.builder()
				.categoryCountlist(this.getCategory1WholeSalerCount())
				.categoryVendorList(this.getCategoryVendor(categoryID))
				.categoryVendorInfoList(this.getCategoryVendorInfo(vendorName))
				.build();
	}

	private List<CategoryVendor> getCategoryVendor(Integer categoryID) {
		JPASQLQuery<CategoryVendor> jpasqlQuery = new JPASQLQuery(entityManager, new MSSQLServer2012Templates());

		JPQLQuery vwWholeSalerItemCount = this.vwWholeSalerItemCount();
		JPQLQuery vwVendorCategory = this.vwVendorCategory();

		Path<Object> A = ExpressionUtils.path(Object.class, "A");
		Path<Object> B = ExpressionUtils.path(Object.class, "B");

		NumberPath<Integer> pathWholeSalerID = Expressions.numberPath(Integer.class, A, "WholeSalerID");
		StringPath pathCompanyName = Expressions.stringPath(A, "CompanyName");

		NumberPath<Integer> pathWholeSalerID_B = Expressions.numberPath(Integer.class, B, "WholeSalerID");

		NumberPath<Integer> pathCategoryID = Expressions.numberPath(Integer.class, B, "CategoryID");
		NumberPath<Integer> pathParentCategoryID = Expressions.numberPath(Integer.class, B, "ParentCategoryID");
		NumberPath<Integer> pathParentParentCategoryID = Expressions.numberPath(Integer.class, B, "ParentParentCategoryID");

		Expression<Integer> constant = Expressions.constant(1);
		BooleanExpression expression = Expressions.asNumber(1).eq(constant);


		jpasqlQuery.select(Projections.constructor(CategoryVendor.class,
				pathWholeSalerID,
				pathCompanyName))
				.from(vwWholeSalerItemCount, A)
				.innerJoin(vwVendorCategory, B).on(pathWholeSalerID.eq(pathWholeSalerID_B));

		if(categoryID != null) {
			jpasqlQuery.where(pathCategoryID.in(categoryID).or(pathParentCategoryID.in(categoryID)).or(pathParentParentCategoryID.in(categoryID)));
		}

		jpasqlQuery.groupBy(pathWholeSalerID, pathCompanyName)
				.orderBy(pathCompanyName.asc());

		return jpasqlQuery.fetch();
	}

	private JPQLQuery vwVendorCategory() {
		QVendorCategoryEntity VC = QVendorCategoryEntity.vendorCategoryEntity;
		QCategoryEntity C = QCategoryEntity.categoryEntity;

		return JPAExpressions.select(
				VC.vendorCategoryID,
				VC.wholeSalerID,
				VC.categoryName,
				VC.categoryDescription,
				VC.listOrder,
				VC.active,
				VC.defaultBodySizeID,
				VC.defaultFabricDescription,
				VC.defaultFabricID,
				VC.defaultInventoryStatusID,
				VC.defaultLabelID,
				VC.defaultLengthID,
				VC.defaultMadeIn,
				VC.defaultPackID,
				VC.defaultPatternID,
				VC.defaultSizeID,
				VC.defaultStyleID,
				VC.parentVendorCategoryID,
				queryDSLSQLFunctions.isnull(Integer.class, VC.categoryID, 0).as("CategoryID"),
				queryDSLSQLFunctions.isnull(Integer.class, C.parentCategoryId, 0).as("ParentCategoryID"),
				queryDSLSQLFunctions.isnull(Integer.class, C.parentParentCategoryId, 0).as("ParentParentCategoryID"),
				C.lvl,
				VC.createdOn)
				.from(VC)
				.leftJoin(C).on(VC.categoryID.eq(C.categoryId));
	}

	private List<CategoryCount> getCategory1WholeSalerCount() {
		QProductsEntity P = QProductsEntity.productsEntity;
		QCategoryEntity C = new QCategoryEntity("C");
		QCategoryEntity C1 = new QCategoryEntity("C1");
		QCategoryEntity C2 = new QCategoryEntity("C2");

		JPAQuery<CategoryCount> query = new JPAQuery<>(entityManager);

		query.select(Projections.constructor(CategoryCount.class,
				C1.categoryId,
				P.wholeSalerID.countDistinct()))
				.from(P)
				.innerJoin(C).on(P.categoryID.eq(C.categoryId))
				.innerJoin(C2).on(C.parentCategoryId.eq(C2.categoryId).or(C.categoryId.eq(C2.categoryId)))
				.innerJoin(C1).on(C2.parentCategoryId.eq(C1.categoryId).or(C2.categoryId.eq(C1.categoryId)).or(C.categoryId.eq(C1.categoryId)))
				.where(P.active.eq(true).and(C1.lvl.eq(1)))
				.groupBy(C1.categoryId);

		return query.fetch();

	}

	private JPQLQuery vwWholeSalerItemCount() {
		QProductsEntity P = QProductsEntity.productsEntity;
		QCategoryEntity C = QCategoryEntity.categoryEntity;
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;
		QSystemImageServersEntity I = QSystemImageServersEntity.systemImageServersEntity;
		QProductImageEntity PRI = QProductImageEntity.productImageEntity;
		QVendorCategoryEntity VC = QVendorCategoryEntity.vendorCategoryEntity;
		QProductVideoEntity PRV = QProductVideoEntity.productVideoEntity;


		return JPAExpressions.select(
				P.wholeSalerID,
				W.companyName,
				W.dirName,
				P.productID.count())
				.from(P)
				.innerJoin(C).on(P.categoryID.eq(C.categoryId))
				.innerJoin(W).on(P.wholeSalerID.eq(W.wholeSalerId))
				.innerJoin(I).on(W.imageServerID.eq(I.imageServerID))
				.leftJoin(PRI).on(P.productID.eq(PRI.productID).and(PRI.listOrder.eq(1)))
				.innerJoin(VC).on(P.vendorCategoryID.eq(VC.vendorCategoryID))
				.leftJoin(PRV).on(P.productID.eq(PRV.productID).and(PRV.active.eq(true)))
				.where(P.active.eq(true).and(W.active.eq(true).and(W.shopActive.eq(true).and(W.orderActive.eq(true)))))
				.groupBy(P.wholeSalerID, W.companyName, W.dirName);
	}



	private List<CategoryVendorInfo> getCategoryVendorInfo(String vendorName) {
		QSimpleWholeSalerEntity tw = QSimpleWholeSalerEntity.simpleWholeSalerEntity;
		QFeaturedWholeSalerInfoEntity sw = QFeaturedWholeSalerInfoEntity.featuredWholeSalerInfoEntity;

		NumberExpression<Integer> selectChk = Expressions.asNumber(0).as("SelectChk");
		NumberExpression<Integer> viewChk = Expressions.asNumber(0).as("ViewChk");

		BooleanExpression expression = tw.active.eq(true).and(tw.shopActive.eq(true));

		if(vendorName != null) {
			expression = expression.and(tw.companyName.like('%'+vendorName+'%'));
		}

		JPAQuery<FeaturedWholeSalerInfoEntity> query = new JPAQuery<>(entityManager);
		return query.select(Projections.constructor(CategoryVendorInfo.class,
				tw.companyName,
				tw.wholeSalerId,
				tw.companyTypeID,
				tw.vendorType,
				selectChk,
				viewChk,
				sw.buyerRate,
				sw.vendorRate,
				sw.vendorTierGroup,
				queryDSLSQLFunctions.isnull(Boolean.class, sw.isCM, 0),
				queryDSLSQLFunctions.isnull(Boolean.class, sw.isPG, 0),
				queryDSLSQLFunctions.isnull(Boolean.class, sw.isCS, 0)))
			.from(tw)
			.leftJoin(sw).on(tw.wholeSalerId.eq(sw.wholeSalerID))
			.where(expression)
			.orderBy(tw.companyName.asc()).fetch();
	}

	@Override
	@Transactional(value = "primaryTransactionManager")
	public GetAdminTodayDealCalendarResult up_wa_GetAdminTodayDealCalendar(Date dateFrom, Date dateTo) {
//		Select
//		TodayDealID,datepart(dd,FromDate) As Dom, CompanyName,Convert(varchar(10),FromDate,121) As FromDate,Active,WholeSalerID,CreatedByVendor
//		From vwTodayDealWebAdmin
//		Where CompanyName is not null
//		and  FromDate between Convert(varchar(10),@FromDate,121) and Convert(varchar(10),@ToDate,121)
//		and Active = 1
//		Order By TodayDealID,FromDate Desc
//
//		Select WholeSalerID,CompanyName
//		From vwTodayDealWebAdmin
//		Where CompanyName is not null
//		and  FromDate between Convert(varchar(10),@FromDate,121) and Convert(varchar(10),@ToDate,121)
//		and Active = 1
//		group by WholeSalerID,CompanyName
//		Order By CompanyName asc


//CREATE View dbo.vwTodayDealWebAdmin
//AS
//	select
//		t.TodayDealID, t.Title, t.Description, t.FromDate, t.ToDate, t.TodayDealPrice,
//		t.AppliedOn, t.ApprovedOn, t.Active, t.ModifiedBy, t.ModifiedOn, t.CreatedByVendor,
//		p.ProductID, p.ProductName, p.ImageUrlRoot, p.DirName, p.PictureGeneral,
//		p.CompanyName, p.WholeSalerID, p.Price UnitPrice
//	from
//		TodayDeal t
//		left outer join vwProductDetail p on t.ProductID = p.ProductID


//CREATE View [dbo].[vwProductDetail]
//AS
//select p.ProductID, p.WholeSalerID, p.ProductName, p.ProductName2, p.ProductDescription,
//		p.CategoryID, p.VendorCategoryID,
//		(select imagename from product_image pri where p.productid = pri.productid and pri.listOrder = 1 ) PictureGeneral,
//case when p.UnitPrice1 = 0 then p.UnitPrice else dbo.ufnMinValue(p.UnitPrice, p.UnitPrice1) end Price,
//dbo.ufnMaxValue(p.UnitPrice, p.UnitPrice1) PriceOld,
//		p.CallforPrice,
//		isnull(p.FabricDescription, 'Unspecified') FabricDescription,
//		isnull(p.MadeIn, 'Unspecified') MadeIn,
//		isnull(p.StockAvailability, 'Unspecified') StockAvailability,
//		p.LabelTypeID,
//		p.EvenColorYN, p.PrePackYN, p.HotItems,
//		p.MinTQYNStyle, p.MinTQStyle,
//		p.DiscountYN,
//		w.CompanyName, w.DirName, i.UrlPath ImageUrlRoot, p.Active,
//		w.Active WholeSalerActive, w.ShopActive WholeSalerShopActive,
//		p.ActivatedOn, p.PackQtyTotal
//		,w.OrderNotice
//		,p.ItemName
//from
//Products p
//inner join tblWholeSaler w on p.WholeSalerID = w.WholeSalerID
//inner join System_ImageServers i on w.ImageServerID = i.ImageServerID



//		SELECT
//		T.TodayDealID,datepart(dd,T.FromDate) As Dom, W.CompanyName,Convert(varchar(10),T.FromDate,121) As FromDate,T.Active,W.WholeSalerID,T.CreatedByVendor
//		FROM TodayDeal T
//		LEFT JOIN Products P ON T.ProductID = P.ProductID
//		INNER JOIN tblWholeSaler W ON P.WholeSalerID = W.WholeSalerID
//		INNER JOIN System_ImageServers I ON W.ImageServerID = I.ImageServerID
//		WHERE
//		W.CompanyName IS NOT NULL
//		AND T.FromDate BETWEEN '2019-08-01 00:00:00' AND '2019-08-31 23:59:59'
//		AND T.Active = 1
//		ORDER BY T.TodayDealID ASC, T.FromDate DESC;
//
//
//		SELECT W.WholeSalerID , W.CompanyName
//		FROM TodayDeal T
//		LEFT JOIN Products P ON T.ProductID = P.ProductID
//		INNER JOIN tblWholeSaler W ON P.WholeSalerID = W.WholeSalerID
//		INNER JOIN System_ImageServers I ON W.ImageServerID = I.ImageServerID
//		WHERE
//		W.CompanyName IS NOT NULL
//		AND T.FromDate BETWEEN '2019-08-01 00:00:00' AND '2019-08-31 23:59:59'
//		AND T.Active = 1
//		GROUP BY W.WholeSalerID , W.CompanyName
//		ORDER BY W.CompanyName asc;

		JPAQuery<TodayDealCalendarDetail> jpaQuery1 = new JPAQuery(entityManager);
		JPAQuery<VendorSummaryDetail> jpaQuery2 = new JPAQuery(entityManager);

		QTodayDealEntity T = QTodayDealEntity.todayDealEntity;
		QProductsEntity P = QProductsEntity.productsEntity;
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;
		QSystemImageServersEntity I = QSystemImageServersEntity.systemImageServersEntity;

		jpaQuery1
				.select(
						Projections.constructor(
								TodayDealCalendarDetail.class
								, T.todayDealId
								, Expressions.asDateTime(T.fromDate).dayOfMonth()
								, W.companyName
								, T.fromDate
								, T.active
								, W.wholeSalerId
								, T.createdByVendor
								)
				)
				.from(T)
				.leftJoin(T.products,P)
				.innerJoin(P.wholeSaler,W)
				.innerJoin(W.systemImageServersEntity,I)
				.where(
						W.companyName.isNotNull()
								.and(
										T.fromDate.between(
											new Timestamp(dateFrom.getTime())
											,new Timestamp(dateTo.getTime())
										)
								).and(T.active.eq(true))
				)
				.orderBy(T.todayDealId.asc(),T.fromDate.desc());


		jpaQuery2
				.select(
						Projections.constructor(
								VendorSummaryDetail.class
								, W.wholeSalerId,W.companyName
						)
				)
				.from(T)
				.leftJoin(T.products,P)
				.innerJoin(P.wholeSaler,W)
				.innerJoin(W.systemImageServersEntity,I)
				.where(
						W.companyName.isNotNull()
								.and(
										T.fromDate.between(
												new Timestamp(dateFrom.getTime())
												,new Timestamp(dateTo.getTime())
										)
								).and(T.active.eq(true))
				)
				.groupBy(W.wholeSalerId,W.companyName)
				.orderBy(W.companyName.asc());

		List<TodayDealCalendarDetail> todayDealCalendarDetails = jpaQuery1.fetch();
		List<VendorSummaryDetail> vendorSummaryDetails = jpaQuery2.fetch();

		return GetAdminTodayDealCalendarResult.builder()
				.calendarDetails(todayDealCalendarDetails)
				.vendors(vendorSummaryDetails)
				.build();
	}

	@Override
	@Transactional(value = "primaryTransactionManager")
	public List<DMRequest> up_wa_GetFGCatalog(Integer pageNumber, Integer pageSize, String status, Integer vendorStatus, Integer wholeSalerID, String companyTypeCD, Date dateFrom, Date dateTo, String orderBy) {
		long offset = (pageNumber - 1) * pageSize;
		long limit = pageSize;

		List<Integer> companyTypeIds = Collections.EMPTY_LIST;

		if(StringUtils.hasLength(companyTypeCD)) {
			companyTypeIds = Arrays.asList(companyTypeCD.split(",")).stream()
					.map(Integer::valueOf)
					.collect(Collectors.toList());
		}

		MSSQLServer2012Templates server2012Templates = new MSSQLServer2012Templates();

		JPASQLQuery subQuery1 = new JPASQLQuery(entityManager,server2012Templates);
		JPASQLQuery subQuery2 = new JPASQLQuery(entityManager,server2012Templates);
		JPASQLQuery<DMRequest> query = new JPASQLQuery(entityManager,server2012Templates);
		QVendorCatalogSendRequestEntity R = new QVendorCatalogSendRequestEntity("R");
		QVendorCatalogEntity C = new QVendorCatalogEntity("C");
		QSimpleWholeSalerEntity W = new QSimpleWholeSalerEntity("W");
		QSystemImageServersEntity I = new QSystemImageServersEntity("I");
		QVendorCatalogSendQueueEntity Q = new QVendorCatalogSendQueueEntity("Q");

		QVendorCatalogSendRequestEntity SUB_R = new QVendorCatalogSendRequestEntity("SUB_R");
		QVendorCatalogProductEntity SUB_A = new QVendorCatalogProductEntity("SUB_A");
		QProductsEntity SUB_P = new QProductsEntity("SUB_P");
		QSimpleWholeSalerEntity SUB_W = new QSimpleWholeSalerEntity("SUB_W");
		QProductImageEntity SUB_I = new QProductImageEntity("SUB_I");

		Path<Object> sub_r = ExpressionUtils.path(Object.class, "SUB_R_TEMP");
		Path<Integer> sub_rRequestedCatalogID = ExpressionUtils.path(Integer.class,sub_r, "RequestedCatalogID");

		Path<Object> Z = ExpressionUtils.path(Object.class, "Z");
		NumberPath<Integer> Z_CatalogID = Expressions.numberPath(Integer.class, Z, "CatalogID");
		StringPath C1 = Expressions.stringPath(Z, "[1]");
		StringPath C2 = Expressions.stringPath(Z, "[2]");
		StringPath C3 = Expressions.stringPath(Z, "[3]");
		StringPath C4 = Expressions.stringPath(Z, "[4]");

		Path<Object> CP = ExpressionUtils.path(Object.class, "CP");
		StringPath CP_C1 = Expressions.stringPath(CP, "C1");
		StringPath CP_C2 = Expressions.stringPath(CP, "C2");
		StringPath CP_C3 = Expressions.stringPath(CP, "C3");
		StringPath CP_C4 = Expressions.stringPath(CP, "C4");

		NumberPath<Integer> cp_catalogID = Expressions.numberPath(Integer.class, CP, "CatalogID");

		List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
		switch (orderBy) {
			case "CatalogSendRequestID desc":
				orderSpecifiers.add(R.catalogSendRequestID.desc());
				break;
			case "CatalogSendRequestID asc":
				orderSpecifiers.add(R.catalogSendRequestID.asc());
				break;
			case "CompanyName desc":
				orderSpecifiers.add(W.companyName.desc());
				break;
			case "CompanyName asc":
				orderSpecifiers.add(W.companyName.asc());
				break;
			case "CompanyTypeCD desc":
				orderSpecifiers.add(W.companyTypeID.desc());
				break;
			case "CompanyTypeCD asc":
				orderSpecifiers.add(W.companyTypeID.asc());
				break;
			case "CreatedOn desc":
				orderSpecifiers.add(R.createdOn.desc());
				break;
			case "CreatedOn asc":
				orderSpecifiers.add(R.createdOn.asc());
				break;
			case "SentOn desc":
				orderSpecifiers.add(Q.sentOn.desc());
				break;
			case "SentOn asc":
				orderSpecifiers.add(Q.sentOn.asc());
				break;
		}
		orderSpecifiers.add(R.requestedCatalogID.desc());

		OrderSpecifier[] orderSpec = orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);

		subQuery1
				.select(SUB_A.catalogID, SUB_I.imageName, SQLExpressions.rowNumber().over().partitionBy(SUB_A.catalogID).orderBy(SUB_A.catalogProductID).as("rowNo"))
				.from(
						JPAExpressions.select(SUB_R.requestedCatalogID)
								.from(SUB_R)
								.groupBy(SUB_R.requestedCatalogID), sub_r)
				.innerJoin(SUB_A).on(SUB_A.catalogID.eq(sub_rRequestedCatalogID))
				.innerJoin(SUB_P).on(SUB_A.productID.eq(SUB_P.productID))
				.innerJoin(SUB_W).on(SUB_P.wholeSalerID.eq(SUB_W.wholeSalerId))
				.innerJoin(SUB_I).on(SUB_P.productID.eq(SUB_I.productID).and(SUB_I.listOrder.eq(1)))
				.where(SUB_P.active.eq(true).and(SUB_W.active.eq(true)).and(SUB_W.shopActive.eq(true)).and(SUB_W.orderActive.eq(true)));

		subQuery2
				.select(Z_CatalogID,C1.as("C1"),C2.as("C2"),C3.as("C3"),C4.as("C4"))
				.from(subQuery1,Z).addJoinFlag(" Pivot ( "
				+ "Min(ImageName) "
				+ "For rowNo In ([1],[2],[3],[4])"
				+ ") Z", JoinFlag.Position.END);

		query.select(
				Projections.constructor(
						DMRequest.class
						,SQLExpressions.rowNumber().over().orderBy(orderSpec).as("[row]")
						,R.catalogSendRequestID
						,R.catalogSendQueueID
						,R.fgCatalogID
						,R.catalogSortNo
						,R.createdOn
						,R.modifiedOn
						,R.active
						,W.wholeSalerId
						, Expressions.cases().when(W.companyTypeID.eq(1)).then("M")
								.when(W.companyTypeID.eq(2)).then("W")
								.otherwise("D")
						,W.companyName
						,Q.sentOn
						,R.requestedCatalogID
						,I.urlPath
						,W.dirName
						,CP_C1
						,CP_C2
						,CP_C3
						,CP_C4
						,SQLExpressions.count().over().as("TotalCount")
						)
				)
				.from(R)
				.innerJoin(C).on(R.requestedCatalogID.eq(C.catalogID))
				.innerJoin(W).on(W.wholeSalerId.eq(C.vendorID))
				.innerJoin(I).on(I.imageServerID.eq(W.imageServerID))
				.leftJoin(Q).on(Q.catalogSendQueueID.eq(R.catalogSendQueueID))
				.leftJoin(subQuery2,CP).on(R.requestedCatalogID.eq(cp_catalogID));

		Expression<Integer> ONE = Expressions.constant(1);
		BooleanExpression predicate = Expressions.asNumber(1).eq(ONE);

		if(wholeSalerID != null && wholeSalerID > 0) {
			predicate = predicate.and(W.wholeSalerId.eq(wholeSalerID));
		}

		if(companyTypeIds.size() > 0) {
			predicate = predicate.and(W.companyTypeID.in(companyTypeIds));
		}

		if(dateFrom != null) {
			Timestamp fromTimestamp = new Timestamp(dateFrom.getTime());
			BooleanExpression toExpressions = Optional.ofNullable(dateTo).map(date -> R.createdOn.lt(new Timestamp(date.getTime()))).orElse(R.createdOn.isNull());

			predicate = predicate.and(
					R.createdOn.goe(fromTimestamp)
							.and(toExpressions)
			);
		}

		Timestamp now = new Timestamp(new Date().getTime());
		JPASQLQuery blockedVendorQuery = new JPASQLQuery(entityManager,server2012Templates);
		blockedVendorQuery.select(QVendorBlockedEntity.vendorBlockedEntity.wholeSalerId)
				.from(QVendorBlockedEntity.vendorBlockedEntity);
		JPASQLQuery logVendorHoldQuery = new JPASQLQuery(entityManager,server2012Templates);
		logVendorHoldQuery.select(QLogVendorHoldEntity.logVendorHoldEntity.wholeSalerID)
				.from(QLogVendorHoldEntity.logVendorHoldEntity)
				.where(
						QLogVendorHoldEntity.logVendorHoldEntity.active.eq(true)
								.and(QLogVendorHoldEntity.logVendorHoldEntity.holdFrom.loe(now))
								.and(QLogVendorHoldEntity.logVendorHoldEntity.holdTo.goe(now))
				);

		switch (vendorStatus) {
			case 1:
				predicate = predicate.and(W.orderActive.eq(true));
				break;
			case 2:
				predicate = predicate.and(
						W.shopActive.eq(true)
								.and(W.orderActive.eq(false))
								.and(W.wholeSalerId.notIn(blockedVendorQuery))
								.and(W.wholeSalerId.notIn(logVendorHoldQuery))
								.and(W.contractExpireDate.isNull().or(W.contractExpireDate.gt(now)))

				);
				break;
			case 3:
				predicate = predicate.and(W.active.eq(true).and(W.shopActive.eq(false)).and(W.orderActive.eq(false)));
				break;
			case 4:
				predicate = predicate.and(
						W.wholeSalerId.in(blockedVendorQuery).and(W.contractExpireDate.isNull().or(W.contractExpireDate.gt(now)))
				);
				break;
			case 5:
				predicate = predicate.and(
						W.wholeSalerId.in(logVendorHoldQuery)
				);
				break;
			case 6:
				predicate = predicate.and(
						W.contractExpireDate.isNotNull().and(W.contractExpireDate.lt(now))
				);
				break;
			case 7:
				predicate = predicate.and(W.active.eq(false));
				break;
			case 8:
				predicate = predicate.and(W.actualOpen.eq("Y"));
				break;
		}

		switch (status) {
			case "Requested":
				predicate = predicate.and(R.catalogSendQueueID.isNull());
				break;
			case "Waiting":
				predicate = predicate.and(R.catalogSendQueueID.isNotNull().and(Q.sentOn.isNull()));
				break;
			case "Sent":
				predicate = predicate.and(R.catalogSendQueueID.isNotNull().and(Q.sentOn.isNotNull()));
				break;
		}

		query.where(predicate)
				.orderBy(orderSpec)
				.offset(offset)
				.limit(limit);

		return query
				.fetch();
	}

	@Override
	@Transactional(value = "primaryTransactionManager")
	public OrderHistoryStatistics up_wa_RetailerInfo_OrderSummary(Integer retailerId) {
		QSummaryOrdersEntity ORDER = QSummaryOrdersEntity.summaryOrdersEntity;

		SimpleTemplate<BigDecimal> expressionTotalAmountWSC = queryDSLSQLFunctions.isnull(BigDecimal.class
				, Expressions.asNumber(queryDSLSQLFunctions.isnull(BigDecimal.class, ORDER.totalAmountWSC, 0)).sum()
				, 0);

//		declare
//		@OrderAmount       decimal(18,2)=0,
//		@OrderCount        int=0,
//		@OrderQty          int=0,
//		@VendorQty         int=0,
//		@CancelAmount      decimal(18,2)=0,
//		@CancelledByBuyer  decimal(18,2)=0,
//		@CancelledByVendor decimal(18,2)=0

		Supplier<Tuple> functionOrderAmountAndCount = () -> {
			//		select @OrderAmount = isnull(sum(isnull(totalamountwsc,0)),0) , @OrderCount = count(*)
			//		from tblOrders
			//		where OrderStatusID not in (0)
			//		and RetailerId = @RetailerID
			JPAQuery<Tuple> query = new JPAQuery(entityManager);
			query.select(
					expressionTotalAmountWSC
					, ORDER.orderID.count()
			).from(ORDER)
					.where(ORDER.retailerID.eq(retailerId).and(ORDER.orderStatusID.notIn(0)));

			return query.fetchOne();
		};
		Tuple tuple = functionOrderAmountAndCount.get();
		BigDecimal orderAmount = tuple.get(expressionTotalAmountWSC).setScale(2, RoundingMode.HALF_UP);
		Long orderCount = tuple.get(ORDER.orderID.count());

		SimpleTemplate<Integer> expressionTotalQty =
				queryDSLSQLFunctions.isnull(Integer.class
						,Expressions.asNumber(queryDSLSQLFunctions.isnull(Integer.class, ORDER.totalQty, 0)).sum()
						,0);

		Supplier<Integer> functionOrderQty = () -> {
			//		select @OrderQty = isnull(sum(isnull(TotalQty,0)) ,0)
			//		from tblOrders
			//		where OrderStatusID not in (0)
			//		and RetailerId = @RetailerID
			JPAQuery<Integer> query = new JPAQuery(entityManager);
			query.select(
					expressionTotalQty
			).from(ORDER)
					.where(ORDER.retailerID.eq(retailerId).and(ORDER.orderStatusID.notIn(0)));
			return query.fetchOne();
		};
		Integer orderQty = functionOrderQty.get();

		Supplier<Long> functionVendorQty = () -> {
			//		select @VendorQty = count(distinct WholeSalerID)
			//		from tblOrders
			//		where OrderStatusID not in (0)
			//		and RetailerId = @RetailerID
			NumberExpression<Long> longNumberExpressionVendorQty = ORDER.wholeSalerID.countDistinct();
			JPAQuery<Long> query = new JPAQuery(entityManager);
			query.select(
					longNumberExpressionVendorQty
			).from(ORDER)
					.where(ORDER.retailerID.eq(retailerId).and(ORDER.orderStatusID.notIn(0)));
			return query.fetchOne();
		};
		Long vendorQty = functionVendorQty.get();

		Supplier<BigDecimal> functionCancelAmount = () -> {
			//		select @CancelAmount = isnull(sum(isnull(totalamountwsc,0)) ,0)
			//		from tblOrders
			//		where OrderStatusID in (5,51,52,53)
			//		and RetailerId = @RetailerID
			JPAQuery<BigDecimal> query = new JPAQuery(entityManager);
			query.select(
					expressionTotalAmountWSC
			).from(ORDER)
					.where(ORDER.retailerID.eq(retailerId).and(ORDER.orderStatusID.in(5,51,52,53)));
			return query.fetchOne();
		};
		BigDecimal cancelAmount = functionCancelAmount.get().setScale(2, RoundingMode.HALF_UP);;

		Supplier<Long> functionCancelledByBuyer = () -> {
			//		select @CancelledByBuyer = count(OrderID)
			//		from tblOrders
			//		where OrderStatusID = 5 and IsCancelledByBuyer =1
			//		and RetailerId = @RetailerID
			JPAQuery<Long> query = new JPAQuery(entityManager);
			query.select(
					ORDER.orderID.count()
			).from(ORDER)
					.where(ORDER.retailerID.eq(retailerId).and(ORDER.orderStatusID.eq(5)).and(ORDER.isCancelledByBuyer.eq(true)));
			return query.fetchOne();
		};
		BigDecimal cancelledByBuyer = new BigDecimal(functionCancelledByBuyer.get()).setScale(2,RoundingMode.HALF_UP);

		Supplier<Long> functionCancelledByVendor = () -> {
			//		select @CancelledByVendor = count(OrderID)
			//		from tblOrders
			//		where OrderStatusID = 5 and IsCancelledByBuyer =0
			//		and RetailerId = @RetailerID
			JPAQuery<Long> query = new JPAQuery(entityManager);
			query.select(
					ORDER.orderID.count()
			).from(ORDER)
					.where(ORDER.retailerID.eq(retailerId).and(ORDER.orderStatusID.eq(5)).and(ORDER.isCancelledByBuyer.eq(false)));
			return query.fetchOne();
		};
		BigDecimal cancelledByVendor = new BigDecimal(functionCancelledByVendor.get()).setScale(2,RoundingMode.HALF_UP);

//		select case when @OrderCount = 0 then
//		0
//               else (@OrderAmount / @OrderCount)
//		end  Average_Order_Amount
//         ,@OrderAmount as Total_Order_Amount
//         ,@OrderQty as Total_Order_Qty
//         ,@VendorQty as Total_Vendor_Qty
//
//		 ,case when @OrderAmount = 0 then
//		0
//               else (@CancelAmount / @OrderAmount) * 100
//		end Order_Amount_VS_Cancel_Ratio
//		  ,case when @CancelledByBuyer+@CancelledByVendor = 0 then 0 else (@CancelledByBuyer/(@CancelledByBuyer+@CancelledByVendor))*100 end Cancelled_By_Buyer
//		  ,case when @CancelledByBuyer+@CancelledByVendor = 0 then 0 else (@CancelledByVendor/(@CancelledByBuyer+@CancelledByVendor))*100 end Cancelled_By_Vendor

		Supplier<OrderHistoryStatistics> orderHistoryStatisticsSupplier = () -> {
			BigDecimal cancelledByBuyerVendor = cancelledByBuyer.add(cancelledByVendor);

			BigDecimal finalCancelledByBuyer = cancelledByBuyerVendor.longValue() == 0 ? BigDecimal.ZERO :
					cancelledByBuyer.divide(cancelledByBuyerVendor,MathContext.DECIMAL64)
					.multiply(new BigDecimal(100));

			BigDecimal finalCancelledByVendor = cancelledByBuyerVendor.longValue() == 0 ? BigDecimal.ZERO :
					cancelledByVendor.divide(cancelledByBuyerVendor,MathContext.DECIMAL64)
					.multiply(new BigDecimal(100));

			BigDecimal averageOrderAmount = orderCount == 0 ? BigDecimal.ZERO :
					orderAmount.divide(new BigDecimal(orderCount), MathContext.DECIMAL64);

			BigDecimal orderAmountVSCancelRatio = orderAmount.longValue() == 0 ? BigDecimal.ZERO :
					cancelAmount.divide(orderAmount,MathContext.DECIMAL64)
					.multiply(new BigDecimal(100));

			return OrderHistoryStatistics.builder()
					.totalOrderAmount(orderAmount)
					.totalOrderQty(orderQty.intValue())
					.totalVendorQty(vendorQty.intValue())
					.orderAmountVSCancelRatio(orderAmountVSCancelRatio)
					.cancelledByBuyer(finalCancelledByBuyer)
					.cancelledByVendor(finalCancelledByVendor)
					.averageOrderAmount(averageOrderAmount)
					.build();
		};


		return orderHistoryStatisticsSupplier.get();
	}
}
