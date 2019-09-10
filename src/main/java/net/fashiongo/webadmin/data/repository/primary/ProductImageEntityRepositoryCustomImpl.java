package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QProductImageEntity;
import net.fashiongo.webadmin.data.entity.primary.QProductsEntity;
import net.fashiongo.webadmin.data.entity.primary.QSimpleWholeSalerEntity;
import net.fashiongo.webadmin.data.entity.primary.QSystemImageServersEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsImage;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductImageEntityRepositoryCustomImpl implements ProductImageEntityRepositoryCustom{

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Autowired
    private QueryDSLSQLFunctions queryDSLSQLFunctions;

    @Override
    public List<ProductsImage> getProductsImage(Integer productID) {
        QProductImageEntity PRI = QProductImageEntity.productImageEntity;
        QProductsEntity P = QProductsEntity.productsEntity;
        QSimpleWholeSalerEntity W = QSimpleWholeSalerEntity.simpleWholeSalerEntity;
        QSystemImageServersEntity SYSI = QSystemImageServersEntity.systemImageServersEntity;

        JPAQuery<ProductsImage> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(ProductsImage.class,
                W.dirName,
                SYSI.urlPath,
                PRI.productImageID,
                PRI.productID,
                PRI.colorID,
                PRI.imageName,
                PRI.listOrder,
                PRI.active))
                .from(PRI)
                .leftJoin(PRI.products, P)
                .leftJoin(P.wholeSaler, W)
                .leftJoin(W.systemImageServersEntity, SYSI)
                .where(PRI.productID.eq(productID));

        return query.fetch();
    }
}
