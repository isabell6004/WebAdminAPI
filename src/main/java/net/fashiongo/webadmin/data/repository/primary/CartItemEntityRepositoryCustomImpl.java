package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.buyer.ShoppingBag;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CartItemEntityRepositoryCustomImpl implements CartItemEntityRepositoryCustom {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	/**
	 * SQL in up_wa_GetShoppingBag
	 *
	 * SELECT WholeSalerID
	 * FROM CartItem AS ct
	 * WHERE ct.RetailerID=@RetailerID and ct.StatusID = 0  and ct.Available = 1  and ct.SavedOn is null
	 * GROUP BY WholeSalerID
	 *
	 * @param retailerId
	 * @return
	 */
	@Override
	public List<Integer> findAllShoppingBagGroupByWholesalerId(int retailerId) {
		JPAQuery<Integer> jpaQuery = new JPAQuery<>(entityManager);
		QCartItemEntity CART = QCartItemEntity.cartItemEntity;

		jpaQuery.select(CART.wholeSalerID)
				.from(CART)
				.where(
						CART.retailerID.eq(retailerId).and(CART.statusID.eq(0)).and(CART.available.eq(true)).and(CART.savedOn.isNull())
				)
				.groupBy(CART.wholeSalerID);

		return jpaQuery.fetch();
	}

	/**
	 * SQL in up_wa_GetShoppingBag
	 *
	 * SELECT ct.CartID, ct.WholeSalerID, ct.RetailerID, ct.ProductID, ct.ProductName, ct.ColorName, ct.NoOfPack, ct.TotalQty, ct.UnitPrice, ct.SubTotal
	 * 	, ct.BQ1, ct.BQ2, ct.BQ3, ct.BQ4, ct.BQ5, ct.BQ6, ct.BQ7, ct.BQ8, ct.BQ9, ct.BQ10, ct.BQ11
	 * 	, w.CompanyName, w.DirName, w.ExtraCharge, w.ExtraChargeAmountTo, w.PictureLogo
	 * 	, p.PrePackYN, p.EvenColorYN, prdi.ImageName AS PictureGeneral, i.UrlPath AS ImageUrlRoot, p.AvailableOn
	 * 	, xs.S1, xs.S2, xs.S3, xs.S4, xs.S5, xs.S6, xs.S7, xs.S8, xs.S9, xs.S10, xs.S11
	 * 	, xp.PackQtyTotal, xp.PackQty1, xp.PackQty2, xp.PackQty3, xp.PackQty4, xp.PackQty5, xp.PackQty6, xp.PackQty7, xp.PackQty8, xp.PackQty9, xp.PackQty10, xp.PackQty11
	 * 	FROM CartItem AS ct
	 * 	INNER JOIN tblWholeSaler w ON ct.WholeSalerID=w.WholeSalerID
	 * 	LEFT OUTER JOIN Products p ON ct.ProductID=p.ProductID
	 * 	LEFT OUTER JOIN Product_Image prdi on p.ProductID = prdi.ProductID and prdi.ListOrder = 1
	 * 	LEFT OUTER JOIN [XSizeChart] xs ON ct.SizeID=xs.SizeID
	 * 	LEFT OUTER JOIN [XPack] xp ON ct.PackID=xp.PackID
	 * 	LEFT OUTER JOIN [system_ImageServers] i on w.ImageServerID = i.ImageServerID
	 * 	WHERE ct.RetailerID=@RetailerID and ct.StatusID = 0  and ct.Available = 1  and ct.SavedOn is null
	 * 	order by ct.WholeSalerID, ct.ProductName, ct.ColorName
	 *
	 * @param retailerId
	 * @return
	 */
	@Override
	public List<ShoppingBag> findAllShoppingBag(int retailerId) {
		JPASQLQuery<ShoppingBag> jpaQuery = new JPASQLQuery<>(entityManager,new MSSQLServer2012Templates());
		QCartItemEntity CT = QCartItemEntity.cartItemEntity;
		QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;
		QProductsEntity P = QProductsEntity.productsEntity;
		QProductImageEntity PRDI = QProductImageEntity.productImageEntity;
		QSystemImageServersEntity I = QSystemImageServersEntity.systemImageServersEntity;
		QXSizeChartEntity XS = QXSizeChartEntity.xSizeChartEntity;
		QXPackEntity XP =  QXPackEntity.xPackEntity;

		jpaQuery.select(
					Projections.constructor(
							ShoppingBag.class
							,P.availableOn
							,CT.bQ1
							,CT.bQ2
							,CT.bQ3
							,CT.bQ4
							,CT.bQ5
							,CT.bQ6
							,CT.bQ7
							,CT.bQ8
							,CT.bQ9
							,CT.bQ10
							,CT.bQ11
							,CT.cartID
							,CT.colorName
							,W.companyName
							,W.dirName
							,P.evenColorYN
							,W.extraCharge
							,W.extraChargeAmountTo
							,I.urlPath
							,CT.noOfPack
							,XP.packQty1
							,XP.packQty2
							,XP.packQty3
							,XP.packQty4
							,XP.packQty5
							,XP.packQty6
							,XP.packQty7
							,XP.packQty8
							,XP.packQty9
							,XP.packQty10
							,XP.packQty11
							,XP.packQtyTotal
							,PRDI.imageName
							,W.pictureLogo
							,P.prePackYN
							,CT.productID
							,CT.productName
							,CT.retailerID
							,XS.s1
							,XS.s2
							,XS.s3
							,XS.s4
							,XS.s5
							,XS.s6
							,XS.s7
							,XS.s8
							,XS.s9
							,XS.s10
							,XS.s11
							,CT.subTotal
							,CT.totalQty
							,CT.unitPrice
							,CT.wholeSalerID
					)
				)
				.from(CT)
				.innerJoin(W).on(CT.wholeSalerID.eq(W.wholeSalerId))
				.leftJoin(P).on(CT.productID.eq(P.productID))
				.leftJoin(PRDI).on(P.productID.eq(PRDI.productID).and(PRDI.listOrder.eq(1)))
				.leftJoin(XS).on(CT.sizeID.eq(XS.sizeID))
				.leftJoin(XP).on(CT.packID.eq(XP.packID))
				.leftJoin(I).on(I.imageServerID.eq(W.imageServerID))
				.where(
						CT.retailerID.eq(retailerId).and(CT.statusID.eq(0)).and(CT.available.eq(true).and(CT.savedOn.isNull()))
				)
				.orderBy(CT.wholeSalerID.asc(),CT.productName.asc(),CT.colorName.asc());

		return jpaQuery.fetch();
	}
}
