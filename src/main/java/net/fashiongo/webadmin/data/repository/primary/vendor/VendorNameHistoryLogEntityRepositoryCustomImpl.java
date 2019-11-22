package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QVendorNameHistoryLogEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorNameHistoryLog;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VendorNameHistoryLogEntityRepositoryCustomImpl implements VendorNameHistoryLogEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<VendorNameHistoryLog> findAllByIDOrderByCreatedOn(Integer wholeSalerID) {
        JPAQuery<VendorNameHistoryLog> query = new JPAQuery<>(entityManager);
        QVendorNameHistoryLogEntity VNHL = QVendorNameHistoryLogEntity.vendorNameHistoryLogEntity;

        query.select(Projections.constructor(VendorNameHistoryLog.class,
                VNHL.oldCompanyName,
                VNHL.newCompanyName,
                VNHL.createdOn))
                .from(VNHL)
                .where(VNHL.wholeSalerID.eq(wholeSalerID))
                .orderBy(VNHL.createdOn.desc());

        return query.fetch();
    }
}
