package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QSystemImageServersEntity;
import net.fashiongo.webadmin.data.entity.primary.QWholeSalerEntity;
import net.fashiongo.webadmin.data.model.statistics.ImageServerUrl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SystemImageServersEntityRepositoryCustomImpl implements SystemImageServersEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<ImageServerUrl> findImageServerURlGroupByImageServerID() {
        QSystemImageServersEntity SIS = QSystemImageServersEntity.systemImageServersEntity;
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;

        JPAQuery<ImageServerUrl> query = new JPAQuery<>(entityManager);
        query.select(Projections.constructor(ImageServerUrl.class,
                SIS.imageServerID.as("imageserverid"),
                W.wholeSalerID.count().as("WholeSalerCount"),
                SIS.imageServerName.max().as("ImageServerName"),
                SIS.urlPath.max().as("UrlPath")))
                .from(SIS)
                .leftJoin(W).on(SIS.imageServerID.eq(W.imageServerID).and(W.active.eq(true)).and(W.shopActive.eq(true)).and(W.orderActive.eq(true)))
                .where(SIS.active.eq(true))
                .groupBy(SIS.imageServerID,W.imageServerID)
                .orderBy(W.wholeSalerID.count().desc(),SIS.imageServerID.asc());

        return query.fetch();
    }
}
