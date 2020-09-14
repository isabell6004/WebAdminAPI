package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.ad.CategoryAdItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MapAdVendorItemEntityRepositoryCustomImpl implements MapAdVendorItemEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<CategoryAdItem> findCategoryAdItemOrderByListOrder(Integer adID) {
        QMapAdVendorItemEntity mapAdVendorItem = QMapAdVendorItemEntity.mapAdVendorItemEntity;
        QProductsEntity products = QProductsEntity.productsEntity;
        QVendorEntity wholeSaler = QVendorEntity.vendorEntity;
        QVendorSettingEntity vendorSetting = QVendorSettingEntity.vendorSettingEntity;
        QProductImageEntity pri = QProductImageEntity.productImageEntity;
        QSystemImageServersEntity sis = QSystemImageServersEntity.systemImageServersEntity;
        QCategoryEntity category = QCategoryEntity.categoryEntity;
        QVendorCategoryEntity vc = QVendorCategoryEntity.vendorCategoryEntity;
        QProductVideoEntity prv = QProductVideoEntity.productVideoEntity;

        List<CategoryAdItem> queryResults = new JPAQuery<>(entityManager)
                .select(Projections.constructor(CategoryAdItem.class,
                        mapAdVendorItem.mapID,
                        mapAdVendorItem.productID,
                        mapAdVendorItem.listOrder,
                        products.wholeSalerID,
                        wholeSaler.name,
                        pri.imageName,
                        wholeSaler.dirname,
                        products.productName,
                        sis.urlPath))
                .from(mapAdVendorItem)
                .leftJoin(mapAdVendorItem.products, products)
                .innerJoin(products.category, category)
                .innerJoin(products.wholeSaler, wholeSaler)
                .leftJoin(wholeSaler.vendorSetting, vendorSetting)
                .innerJoin(sis).on(sis.imageServerID.eq(7))
                .leftJoin(products.productImageEntity, pri).on(pri.listOrder.eq(1))
                .innerJoin(products.vendorCategoryEntity, vc)
                .leftJoin(products.productVideoEntity, prv).on(prv.active.eq(true))
                .where(products.active.eq(true)
                        .and(vendorSetting.statusCode.eq(3))
                        .and(mapAdVendorItem.adID.eq(adID)))
                .orderBy(mapAdVendorItem.listOrder.asc())
                .fetch();

        return queryResults;
    }
}
