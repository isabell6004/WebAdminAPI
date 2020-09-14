package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QVendorBlockedEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorBlockedEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorBlock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VendorBlockedEntityRepositoryCustomImpl implements VendorBlockedEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager vendorBlockedEntityManager;

    @Override
    public List<VendorBlock> findByWholeSalerID(Integer wid) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        JPAQuery<VendorBlock> query = new JPAQuery<>(vendorBlockedEntityManager);

        query.select(Projections.constructor(VendorBlock.class,
                vendorBlockedEntity.blockId,
                vendorBlockedEntity.blockReasonId,
                vendorBlockedEntity.blockedOn))
                .from(vendorBlockedEntity)
                .where(vendorBlockedEntity.wholeSalerId.eq(wid));

        return query.fetch();
    }

    @Override
    public VendorBlockedEntity findOneByWholeSalerID(Integer wid) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        JPAQuery<VendorBlockedEntity> query = new JPAQuery<>(vendorBlockedEntityManager);

        query.select(vendorBlockedEntity)
                .from(vendorBlockedEntity)
                .where(vendorBlockedEntity.wholeSalerId.eq(wid));

        return query.fetchFirst();
    }

}
