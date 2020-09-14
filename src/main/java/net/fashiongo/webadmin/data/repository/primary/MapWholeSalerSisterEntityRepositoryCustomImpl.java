package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QMapWholeSalerSisterEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorSettingEntity;
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
        QVendorEntity V = QVendorEntity.vendorEntity;
        QVendorSettingEntity VS = QVendorSettingEntity.vendorSettingEntity;
        JPAQuery<VendorSister> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(VendorSister.class,
                M.mapID,
                M.wholeSalerID,
                M.sisterWholeSalerID,
                V.name))
                .from(M)
                .innerJoin(M.wholeSaler, V)
                .innerJoin(V.vendorSetting, VS)
                .where(M.wholeSalerID.eq(wid).and(VS.statusCode.in(1,2,3)));

        return query.fetch();
    }

    @Override
    public List<Integer> findMapIDByWholeSalerIDAndSisterWholeSalerID(Integer wid, Integer sisterID) {
        QMapWholeSalerSisterEntity M = QMapWholeSalerSisterEntity.mapWholeSalerSisterEntity;
        JPAQuery<Integer> query = new JPAQuery<>(entityManager);

        query.select(M.mapID).from(M).where(M.wholeSalerID.eq(wid).and(M.sisterWholeSalerID.eq(sisterID)));

        return query.fetch();
    }
}
