package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QSystemVendorAdminWebServerEntity;
import net.fashiongo.webadmin.data.entity.primary.QWholeSalerEntity;
import net.fashiongo.webadmin.data.model.statistics.VendorAdminWebServerUrl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SystemVendorAdminWebServerEntityRepositoryCustomImpl implements SystemVendorAdminWebServerEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<VendorAdminWebServerUrl> findURLGroupByWebServerIDAndAdminWebServerID() {
        QSystemVendorAdminWebServerEntity SVAWS = QSystemVendorAdminWebServerEntity.systemVendorAdminWebServerEntity;
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;

        JPAQuery<VendorAdminWebServerUrl> query = new JPAQuery<>(entityManager);
        query.select(Projections.constructor(VendorAdminWebServerUrl.class,
                SVAWS.webServerID.as("adminwebserverid"),
                W.wholeSalerID.count().as("WholeSalerCount"),
                SVAWS.url.max().as("adminwebserverurl")))
                .from(SVAWS)
                .leftJoin(W).on(SVAWS.webServerID.eq(W.adminWebServerID).and(W.active.eq(true)).and(W.shopActive.eq(true)).and(W.orderActive.eq(true)))
                .groupBy(SVAWS.webServerID, W.adminWebServerID)
                .orderBy(W.wholeSalerID.count().desc(), SVAWS.webServerID.asc());

        return query.fetch();
    }
}
