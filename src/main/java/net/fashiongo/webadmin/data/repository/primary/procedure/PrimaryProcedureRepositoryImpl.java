package net.fashiongo.webadmin.data.repository.primary.procedure;

import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.admin.SecurityMenus2;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendor;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendorAssigned;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.model.primary.SecurityMenu;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
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
}
