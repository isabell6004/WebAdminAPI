package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.AdPageEntity;
import net.fashiongo.webadmin.data.jpa.entity.primary.QAdPageEntity;
import net.fashiongo.webadmin.data.jpa.entity.primary.QAdPageSpotEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
}
