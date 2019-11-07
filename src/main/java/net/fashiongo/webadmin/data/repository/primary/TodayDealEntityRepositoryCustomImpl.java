package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QProductsEntity;
import net.fashiongo.webadmin.data.entity.primary.QTodayDealEntity;
import net.fashiongo.webadmin.data.entity.primary.TodayDealEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TodayDealEntityRepositoryCustomImpl implements TodayDealEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<TodayDealEntity> findAllByWholeSalerID(Integer wholeSalerID) {
        QTodayDealEntity TD = QTodayDealEntity.todayDealEntity;
        QProductsEntity P = QProductsEntity.productsEntity;

        JPAQuery<TodayDealEntity> query = new JPAQuery<>(entityManager);

        query.select(TD)
                .from(TD)
                .where(TD.toDate.gt(Timestamp.valueOf(LocalDateTime.now()))
                        .and(TD.productId
                                .in(JPAExpressions.select(P.productID)
                                        .from(P)
                                        .where(P.wholeSalerID.eq(wholeSalerID)))));

        return query.fetch();
    }
}
