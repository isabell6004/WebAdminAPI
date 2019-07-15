package net.fashiongo.webadmin.data.repository.primary.procedure;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QCountEntity;
import net.fashiongo.webadmin.data.entity.primary.QMapWaUserVendorEntity;
import net.fashiongo.webadmin.data.entity.primary.QSimpleWholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.SimpleWholeSalerEntity;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendor;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendorAssigned;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
}
