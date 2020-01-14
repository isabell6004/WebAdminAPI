package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class NewsEntityRepositoryCustomImpl implements NewsEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager newsEntityManger;

    @Override
    @Transactional
    public NewsEntity findOneByNewsID(Integer newsID) {
        QNewsEntity newsEntity = QNewsEntity.newsEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;

        return new JPAQuery<>(newsEntityManger)
                .select(newsEntity)
                .from(newsEntity)
                .leftJoin(newsEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity).fetchJoin()
                .where(newsEntity.newsId.eq(newsID))
                .fetchOne();
    }

    public NewsEntity getActiveNews(Integer consolidationId, Integer orderId) {
        QConsolidationOrdersEntity qco = QConsolidationOrdersEntity.consolidationOrdersEntity;
        ConsolidationOrdersEntity co = new JPAQuery<ConsolidationOrdersEntity>(newsEntityManger)
                .from(qco)
                .where(qco.consolidationID.eq(consolidationId)
                        .and(qco.orderID.eq(orderId))
                        .and(qco.dropReferenceID.isNotNull())
                )
                .fetchOne();
        if (co == null || co.getDropReferenceID() == null) return null;

        QNewsEntity qNews = QNewsEntity.newsEntity;
        return new JPAQuery<NewsEntity>(newsEntityManger)
                .from(qNews)
                .where(qNews.newsId.eq(co.getDropReferenceID())
                        .and(qNews.active.isTrue())
                        .and(qNews.showBanner.isTrue())
                )
                .fetchOne();
    }
}
