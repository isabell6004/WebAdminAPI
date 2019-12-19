package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QTrendReportMapEntity;
import net.fashiongo.webadmin.data.entity.primary.TrendReportMapEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsSelectCheck;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TrendReportMapEntityRepositoryCustomImpl implements TrendReportMapEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public ProductsSelectCheck getProductsSelectCheck(Integer trendReport, Integer productID) {
        QTrendReportMapEntity TRM = QTrendReportMapEntity.trendReportMapEntity;

        JPAQuery<Long> query = new JPAQuery<>(entityManager);

        query.select(TRM.productID.count())
                .from(TRM)
                .where(TRM.trendReportID.eq(trendReport).and(TRM.productID.eq(productID)));

        return new ProductsSelectCheck(query.fetchOne().intValue());
    }

    @Override
    public List<TrendReportMapEntity> findAllByTrendReportID(Integer trendReportID) {
        QTrendReportMapEntity TRM = QTrendReportMapEntity.trendReportMapEntity;

        JPAQuery<TrendReportMapEntity> query = new JPAQuery<>(entityManager);

        query.select(TRM)
                .from(TRM)
                .where(TRM.trendReportID.eq(trendReportID));

        return query.fetch();
    }
}
