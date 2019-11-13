package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QMapWholeSalerSisterEntity;
import net.fashiongo.webadmin.data.entity.primary.QWholeSalerEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorSister;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MapWholeSalerSisterEntityRepositoryCustomImpl implements MapWholeSalerSisterEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;


    @Override
    public List<VendorSister> findVendorSister(Integer wid) {
        QMapWholeSalerSisterEntity M = QMapWholeSalerSisterEntity.mapWholeSalerSisterEntity;
        QWholeSalerEntity W = QWholeSalerEntity.wholeSalerEntity;
        JPAQuery<VendorSister> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(VendorSister.class,
                M.mapID,
                M.wholeSalerID,
                M.sisterWholeSalerID,
                W.companyName))
                .from(M)
                .innerJoin(M.wholeSaler, W)
                .where(M.wholeSalerID.eq(wid).and(W.active.eq(true)));

        return query.fetch();
    }
}
