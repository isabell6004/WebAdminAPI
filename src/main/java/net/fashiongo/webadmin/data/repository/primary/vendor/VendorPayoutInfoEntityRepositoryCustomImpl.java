package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QVendorPayoutInfoEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorPayoutInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VendorPayoutInfoEntityRepositoryCustomImpl implements VendorPayoutInfoEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<VendorPayoutInfo> findAllByWholeSalerID(Integer wholeSalerID) {
        JPAQuery<VendorPayoutInfo> query = new JPAQuery<>(entityManager);
        QVendorPayoutInfoEntity VP = QVendorPayoutInfoEntity.vendorPayoutInfoEntity;

        query.select(Projections.constructor(VendorPayoutInfo.class,
                VP.payoutSchedule,
                VP.maxPayoutPerDay,
                VP.weekday,
                VP.dayOfMonth,
                VP.verifiedOn))
                .from(VP)
                .where(VP.wholeSalerID.eq(wholeSalerID));

        return query.fetch();
    }
}
