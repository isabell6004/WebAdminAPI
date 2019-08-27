package net.fashiongo.webadmin.data.repository.primary.procedure;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.admin.SecurityMenus2;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendor;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendorAssigned;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

		if(StringUtils.isEmpty(companyType) == false) {
			predicate = predicate.and(W.companyType.in(companyType));
		}

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
				.leftJoin(W.mapWaUserVendor,M).on(W.wholeSalerId.eq(M.vendorID).and(M.userID.eq(userId)))
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

		JPAQuery jpaQuery = new JPAQuery(entityManager);

		jpaQuery.select(M)
				.from(M)
				.where(M.userID.eq(userId));

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

		JPAQuery<CategoryList> query = new JPAQuery<>(entityManager);

		query.select(Projections.constructor(CategoryList.class,
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
				JPAExpressions.select(SUB_C.categoryId.count().as("NodeCnt")).from(SUB_C).where(SUB_C.parentCategoryId.eq(C.categoryId)),
				MCC.collectionCategoryID))
				.from(C)
				.leftJoin(C.mapCollectionCategory, MCC)
				.where(C.categoryId.eq(categoryID).or(categoryIDZero))
				.orderBy(C.listOrder.asc(), C.categoryName.asc());

		return query.fetch().stream().distinct().collect(Collectors.toList());
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
}
