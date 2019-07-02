package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.AdPageSpotEntity;
import net.fashiongo.webadmin.data.entity.primary.QAdPageEntity;
import net.fashiongo.webadmin.data.entity.primary.QAdPageSpotEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AdPageSpotEntityRepositoryCustomImpl implements AdPageSpotEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager adPageSpotManager;

    @Override
    @Transactional
    public List<AdPageSpotEntity> findAllOrderByPageIDAscAndSpotIDAsc() {
        QAdPageSpotEntity adPageSpotEntity = QAdPageSpotEntity.adPageSpotEntity;
        QAdPageEntity adPageEntity = QAdPageEntity.adPageEntity;

        List<AdPageSpotEntity> queryResults = new JPAQuery<>(adPageSpotManager)
                .select(adPageSpotEntity)
                .from(adPageSpotEntity)
                .join(adPageSpotEntity.adPageEntity, adPageEntity).fetchJoin()
                .orderBy(adPageSpotEntity.pageId.asc(), adPageSpotEntity.spotId.asc())
                .fetch();

        return queryResults;
    }
}
