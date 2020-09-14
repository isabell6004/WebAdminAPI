package net.fashiongo.webadmin.data.repository.primary.procedure;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.Union;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.sitemgmt.DMRequestDetail;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DMSendListMigrationProcedure {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Transactional(value = "primaryTransactionManager")
	public List<DMRequestDetail> up_wa_DMSendList_Migration(List<Integer> catalogIdList) {
/*

	SELECT	*
	FROM (
		-- V
		SELECT
			VCP.ProductID
			, VENDOR_CATALOG.CatalogID
			, I.UrlPath ImageUrlRoot
			, TWS.DirName
			, TWS.PictureLogo
			, PRDI.ImageName PictureGeneral
			, P.ProductName
			, TWS.CompanyName
			, ROW_NUMBER() OVER (PARTITION BY VENDOR_CATALOG.CatalogID ORDER BY VCP.SortNo, VCP.CatalogProductID) AS row
		FROM (
				SELECT
					SUB_VCP.CatalogProductID
					, SUB_VCP.CatalogID
					, SUB_VCP.ProductID
					, SUB_VCP.SortNo
				FROM VendorCatalog_Product SUB_VCP
				WHERE
					SUB_VCP.CatalogID IN (94539,94528)

				UNION ALL

				SELECT null as CatalogProductID,SUB_V.CatalogID,SUB_V.ProductID, SUB_V.SortNo
				FROM (
					SELECT
						SUB_P.ProductID
						, row_number() over (PARTITION BY SUB_VENDOR_CATALOG.VendorID order by SUB_P.CreatedOn desc ) AS SortNo
						, row_number() over (PARTITION BY SUB_VENDOR_CATALOG.VendorID order by SUB_P.ProductID desc ) AS row
						, SUB_VENDOR_CATALOG.CatalogID
					FROM Products SUB_P
						INNER JOIN Category SUB_C  on SUB_P.CategoryID = SUB_C.CategoryID
						INNER JOIN tblWholeSaler SUB_W  on SUB_P.WholeSalerID = SUB_W.WholeSalerID
						INNER JOIN system_ImageServers SUB_I on SUB_W.ImageServerID = SUB_I.ImageServerID
						INNER JOIN VendorCategory SUB_VC on SUB_P.VendorCategoryID = SUB_VC.VendorCategoryID
						INNER JOIN VendorCatalog as SUB_VENDOR_CATALOG on SUB_VENDOR_CATALOG.VendorID = SUB_P.WholeSalerID AND SUB_VENDOR_CATALOG.CatalogID IN (94539,94528)
					WHERE
						SUB_P.Active = 1 and SUB_W.Active = 1 and SUB_W.ShopActive = 1 and SUB_W.OrderActive = 1
					) AS SUB_V
				WHERE
					SUB_V.row <= 4
			) AS VCP
			INNER JOIN Products P ON VCP.ProductID = P.ProductID
			INNER join Category C  on P.CategoryID = c.CategoryID
			INNER JOIN tblWholeSaler AS TWS ON P.WholeSalerID = TWS.WholeSalerID
			INNER join VendorCategory VC on P.VendorCategoryID = VC.VendorCategoryID
			INNER JOIN VendorCatalog as VENDOR_CATALOG on VENDOR_CATALOG.VendorID = TWS.WholeSalerID AND VENDOR_CATALOG.CatalogID IN (94539,94528)
			INNER JOIN System_ImageServers AS I ON TWS.ImageServerID = I.ImageServerID
			LEFT JOIN Product_Image AS PRDI ON VCP.ProductID = PRDI.ProductID and PRDI.ListOrder = 1
		WHERE
			P.Active = 1 and TWS.Active = 1 and TWS.ShopActive = 1 and TWS.OrderActive = 1
	) AS V
	WHERE V.row <= 4;
*/

		JPASQLQuery<DMRequestDetail> jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		SimplePath<Object> V = Expressions.path(Object.class, "V");
		NumberPath<Integer> productID = Expressions.numberPath(Integer.class, V, "ProductID");
		NumberPath<Integer> catalogID = Expressions.numberPath(Integer.class, V, "CatalogID");
		StringPath urlPath = Expressions.stringPath(V, "UrlPath");
		StringPath dirName = Expressions.stringPath(V,"DirName");
		StringPath pictureLogo = Expressions.stringPath(V,"PictureLogo");
		StringPath imageName = Expressions.stringPath(V,"ImageName");
		StringPath productName = Expressions.stringPath(V,"ProductName");
		StringPath companyName = Expressions.stringPath(V,"CompanyName");

		NumberPath<Long> V_Row = Expressions.numberPath(Long.class, V, "row");

		jpasqlQuery.select(
				Projections.constructor(
						DMRequestDetail.class
						,catalogID
						,urlPath
						,dirName
						,pictureLogo
						,productID
						,imageName
						,productName
						,companyName
				)
		).from(
						V(catalogIdList)
						,V)
				.where(
						V_Row.loe(4)
				);

		return jpasqlQuery.fetch();
	}

	private JPASQLQuery V(List<Integer> catalogIdList) {
		JPASQLQuery jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		SimplePath<Object> VCP = Expressions.path(Object.class, "VCP");
		NumberPath<Integer> vcpProductID = Expressions.numberPath(Integer.class, VCP, "ProductID");
		NumberPath<Integer> vcpSortNo = Expressions.numberPath(Integer.class, VCP, "SortNo");
		NumberPath<Integer> vcpCatalogProductID = Expressions.numberPath(Integer.class, VCP, "CatalogProductID");

		QProductsEntity P = new QProductsEntity("P");
		QCategoryEntity C = new QCategoryEntity("C");
		QVendorEntity TWS = new QVendorEntity("TWS");
		QVendorBannerEntity VB = new QVendorBannerEntity("VB");
		QVendorSettingEntity VS = new QVendorSettingEntity("VS");
		QVendorCategoryEntity VC = new QVendorCategoryEntity("VC");
		QVendorCatalogEntity VENDOR_CATALOG = new QVendorCatalogEntity("VENDOR_CATALOG");
		QProductImageEntity PRDI = new QProductImageEntity("PRDI");
		QSystemImageServersEntity I = new QSystemImageServersEntity("I");

		jpasqlQuery.select(
				P.productID
				, VENDOR_CATALOG.catalogID
				, I.urlPath
				, TWS.dirname.as("DirName")
				, VB.fileName.as("PictureLogo")
				, PRDI.imageName
				, P.productName
				, TWS.name.as("CompanyName")
				, SQLExpressions.rowNumber().over().partitionBy(VENDOR_CATALOG.catalogID).orderBy(vcpSortNo.asc(),vcpCatalogProductID.asc()).as("row")
		)
				.from(VCP(catalogIdList),VCP)
				.innerJoin(P).on(vcpProductID.eq(P.productID))
				.innerJoin(C).on(C.categoryId.eq(P.categoryID))
				.innerJoin(TWS).on(P.wholeSalerID.eq(TWS.vendor_id.intValue()))
				.innerJoin(VB).on(TWS.vendor_id.eq(VB.vendorId).and(VB.bannerTypeId.intValue().eq(1)))
				.innerJoin(VS).on(TWS.vendor_id.eq(VS.vendorId))
				.innerJoin(VC).on(P.vendorCategoryID.eq(VC.vendorCategoryID))
				.innerJoin(VENDOR_CATALOG).on(VENDOR_CATALOG.vendorID.eq(TWS.vendor_id.intValue()).and(VENDOR_CATALOG.catalogID.in(catalogIdList)))
				.leftJoin(PRDI).on(PRDI.productID.eq(P.productID).and(PRDI.listOrder.eq(1)))
				.innerJoin(I).on(I.imageServerID.eq(7))
				.where(
						P.active.eq(true).and(VS.statusCode.eq(3))
				);


		return jpasqlQuery;
	}

	private Union VCP(List<Integer> catalogIdList) {
		return SQLExpressions.unionAll(SUB_VCP1(catalogIdList), SUB_VCP2(catalogIdList));
	}

	private JPASQLQuery SUB_VCP1(List<Integer> catalogIdList) {
		JPASQLQuery jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		QVendorCatalogProductEntity SUB_VCP = new QVendorCatalogProductEntity("SUB_VCP");

		jpasqlQuery.select(
				SUB_VCP.catalogProductID
				, SUB_VCP.catalogID
				, SUB_VCP.productID
				, SUB_VCP.sortNo
		).from(SUB_VCP)
				.where(SUB_VCP.catalogID.in(catalogIdList));

		return jpasqlQuery;
	}

	private JPASQLQuery SUB_VCP2(List<Integer> catalogIdList) {
		JPASQLQuery jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		SimplePath<Object> SUB_V = Expressions.path(Object.class, "SUB_V");
		NumberPath<Long> V_Row = Expressions.numberPath(Long.class, SUB_V, "row");
		NumberPath<Integer> V_CatalogID = Expressions.numberPath(Integer.class, SUB_V, "CatalogID");
		NumberPath<Integer> V_ProductID = Expressions.numberPath(Integer.class, SUB_V, "ProductID");
		NumberPath<Integer> V_SortNo = Expressions.numberPath(Integer.class, SUB_V, "SortNo");

		jpasqlQuery.select(
				Expressions.nullExpression()
				,V_CatalogID
				,V_ProductID
				,V_SortNo
		).from(SUB_V(catalogIdList),SUB_V)
				.where(V_Row.loe(4));

		return jpasqlQuery;
	}

	private JPASQLQuery SUB_V(List<Integer> catalogIdList) {
		JPASQLQuery jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
		QProductsEntity SUB_P = new QProductsEntity("SUB_P");
		QCategoryEntity SUB_C = new QCategoryEntity("SUB_C");
		QVendorEntity SUB_TWS = new QVendorEntity("SUB_TWS");
		QVendorSettingEntity VS = new QVendorSettingEntity("VS");
		QVendorCategoryEntity SUB_VC = new QVendorCategoryEntity("SUB_VC");
		QVendorCatalogEntity SUB_VENDOR_CATALOG = new QVendorCatalogEntity("SUB_VENDOR_CATALOG");
		QSystemImageServersEntity SUB_I = new QSystemImageServersEntity("SUB_I");

		jpasqlQuery.select(
				SUB_P.productID
				, SQLExpressions.rowNumber().over().partitionBy(SUB_VENDOR_CATALOG.vendorID).orderBy(SUB_P.createdOn.desc()).as("SortNo")
				, SQLExpressions.rowNumber().over().partitionBy(SUB_VENDOR_CATALOG.vendorID).orderBy(SUB_P.productID.desc()).as("row")
				, SUB_VENDOR_CATALOG.catalogID
		).from(SUB_P)
				.innerJoin(SUB_C).on(SUB_P.categoryID.eq(SUB_C.categoryId))
				.innerJoin(SUB_TWS).on(SUB_P.wholeSalerID.eq(SUB_TWS.vendor_id.intValue()))
				.innerJoin(VS).on(SUB_TWS.vendor_id.eq(VS.vendorId))
				.innerJoin(SUB_I).on(SUB_I.imageServerID.eq(7))
				.innerJoin(SUB_VC).on(SUB_P.vendorCategoryID.eq(SUB_VC.vendorCategoryID))
				.innerJoin(SUB_VENDOR_CATALOG).on(SUB_VENDOR_CATALOG.vendorID.eq(SUB_P.wholeSalerID).and(SUB_VENDOR_CATALOG.catalogID.in(catalogIdList)))
				.where(
						SUB_P.active.eq(true).and(VS.statusCode.eq(3))
				);

		return jpasqlQuery;
	}
}
