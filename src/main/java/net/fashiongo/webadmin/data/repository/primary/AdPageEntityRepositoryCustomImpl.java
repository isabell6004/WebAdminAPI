package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.AdPageEntity;
import net.fashiongo.webadmin.data.entity.primary.AdPageSpotEntity;
import net.fashiongo.webadmin.data.entity.primary.QAdPageEntity;
import net.fashiongo.webadmin.data.entity.primary.QAdPageSpotEntity;
import net.fashiongo.webadmin.data.model.ad.BidAdPage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AdPageEntityRepositoryCustomImpl implements AdPageEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager adPageManager;

    @Override
    @Transactional
    public List<AdPageEntity> findAllOrderByPageIDAsc() {
        QAdPageEntity adPageEntity = QAdPageEntity.adPageEntity;

        List<AdPageEntity> results = new JPAQuery<>(adPageManager)
                .select(adPageEntity)
                .from(adPageEntity)
                .orderBy(adPageEntity.pageId.asc())
                .fetch();

        return results;
    }

    @Override
    @Transactional
    public List<AdPageEntity> findAllDistinctOrderByPageIDAsc() {
        QAdPageEntity adPageEntity = QAdPageEntity.adPageEntity;
        QAdPageSpotEntity adPageSpotEntity = QAdPageSpotEntity.adPageSpotEntity;

        List<AdPageEntity> results = new JPAQuery<>(adPageManager)
                .select(adPageEntity)
                .distinct()
                .from(adPageEntity)
                .join(adPageEntity.adPageSpotEntity, adPageSpotEntity)
                .on(adPageSpotEntity.isBannerAd.eq(true))
                .orderBy(adPageEntity.pageId.asc())
                .fetch();

        return results;
    }

    @Override
    @Transactional
    public List<BidAdPage> up_wa_GetBidAdPages() {
        QAdPageEntity adPageEntity = QAdPageEntity.adPageEntity;
        QAdPageSpotEntity adPageSpotEntity = QAdPageSpotEntity.adPageSpotEntity;

        List<BidAdPage> results = new JPAQuery<>(adPageManager)
                .select(Projections.constructor(BidAdPage.class,
                        adPageEntity.pageId,
                        adPageEntity.pageName))
                .from(adPageEntity)
                .where(JPAExpressions.select(adPageSpotEntity).from(adPageSpotEntity).where(
                        adPageSpotEntity.pageId.eq(adPageEntity.pageId)
                                .and(adPageSpotEntity.active.eq(true))
                                .and(adPageSpotEntity.bidEffectiveOn.loe(LocalDateTime.now())))
                        .exists()
                ).fetch();

        return results.stream().distinct().collect(Collectors.toList());
    }
}
