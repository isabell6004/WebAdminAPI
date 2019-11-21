package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QVendorAdminAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorAdminAccountEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VendorAdminAccountEntityRepositoryCustomImpl implements VendorAdminAccountEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<VendorAdminAccountEntity> findAllByWholeSalerID(Integer wholeSalerID) {
        QVendorAdminAccountEntity VAA = QVendorAdminAccountEntity.vendorAdminAccountEntity;

        JPAQuery<VendorAdminAccountEntity> query = new JPAQuery<>(entityManager);

        query.select(VAA).from(VAA).where(VAA.wholeSalerID.eq(wholeSalerID));

        return query.fetch();
    }

    @Override
    public VendorAdminAccountEntity findOneByVendorAdminAccountID(Integer vendorAdminAccountID) {
        QVendorAdminAccountEntity VAA = QVendorAdminAccountEntity.vendorAdminAccountEntity;

        JPAQuery<VendorAdminAccountEntity> query = new JPAQuery<>(entityManager);

        query.select(VAA).from(VAA).where(VAA.vendorAdminAccountID.eq(vendorAdminAccountID));

        return query.fetchFirst();
    }
}
