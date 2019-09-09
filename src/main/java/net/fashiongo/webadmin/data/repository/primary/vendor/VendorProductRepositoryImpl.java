package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLServer2012Templates;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.entity.primary.vendor.ProductColorRow;
import net.fashiongo.webadmin.data.entity.primary.vendor.VendorProductRow;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VendorProductRepositoryImpl implements VendorProductRepository {

	@PersistenceContext(unitName = "primaryEntityManager")
	private EntityManager entityManager;

	@Override
	public List<VendorProductRow> getVendorProducts(int wholesalerId, Integer vendorCategoryId, String productName) {
		QProductsEntity qProducts = QProductsEntity.productsEntity;
		QProductImageEntity qProductImage = QProductImageEntity.productImageEntity;
		QSimpleWholeSalerEntity qWholesaler = QSimpleWholeSalerEntity.simpleWholeSalerEntity;
		QSystemImageServersEntity qImageServers = QSystemImageServersEntity.systemImageServersEntity;

		JPASQLQuery<VendorProductRow> query = new JPASQLQuery<>(entityManager, new SQLServer2012Templates());

		return query
				.select(
						Projections
								.constructor(
										VendorProductRow.class,
										qProducts.productID,
										qProducts.productName,
										qProducts.unitPrice,
										qWholesaler.dirName,
										qImageServers.urlPath,
										qProductImage.productImageID,
										qProductImage.imageName
								)
				)
				.from(qProducts)
				.innerJoin(qWholesaler).on(qWholesaler.wholeSalerId.eq(qProducts.wholeSalerID))
				.leftJoin(qProductImage).on(qProductImage.productID.eq(qProducts.productID).and(qProductImage.listOrder.eq(1)))
				.leftJoin(qImageServers).on(qImageServers.imageServerID.eq(qWholesaler.imageServerID))
				.where(
						qProducts.active.isTrue()
								.and(qWholesaler.wholeSalerId.eq(wholesalerId))
								.and(vendorCategoryId != null && vendorCategoryId > 0 ? qProducts.vendorCategoryID.eq(vendorCategoryId) : null)
								.and(StringUtils.isNotEmpty(productName) ? qProducts.productName.startsWith(productName) : null)
				)
				.orderBy(qProducts.productID.asc())
				.fetch();
	}

	@Override
	public List<ProductColorRow> getColors(int productId) {
		QProductsEntity qProducts = QProductsEntity.productsEntity;
		QInventoryEntity qInventory = QInventoryEntity.inventoryEntity;
		QXColorEntity qColor = QXColorEntity.xColorEntity;

		Expression[] expressions = new Expression[]{qInventory.colorID, qColor.color, qColor.active};
		return new JPAQuery<>(entityManager)
				.select(Projections.constructor(ProductColorRow.class, expressions))
				.from(qProducts)
				.join(qProducts.inventory, qInventory)
				.join(qInventory.xColors, qColor)
				.where(qProducts.productID.eq(productId))
				.groupBy(qInventory.colorID, qColor.color, qColor.active)
				.orderBy(qInventory.colorID.asc())
				.fetch();
	}

}
