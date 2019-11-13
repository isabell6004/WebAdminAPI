package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QVendorCapEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorCapEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VendorCapEntityRepositoryCustomImpl implements VendorCapEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<VendorCapEntity> findByWholeSalerID(Integer wid) {
        QVendorCapEntity X = QVendorCapEntity.vendorCapEntity;
        JPAQuery<VendorCapEntity> query = new JPAQuery<>(entityManager);

        query.select(X).from(X).where(X.wholeSalerID.eq(wid));

        return query.fetch();
    }
}
