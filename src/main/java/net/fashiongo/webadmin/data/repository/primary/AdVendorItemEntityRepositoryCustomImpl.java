package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.AdVendorItemEntity;
import net.fashiongo.webadmin.data.entity.primary.QAdVendorItemEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AdVendorItemEntityRepositoryCustomImpl implements AdVendorItemEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public long getCountByWholeSalerID(Integer wholeSalerID) {
        QAdVendorItemEntity AVI = QAdVendorItemEntity.adVendorItemEntity;
        JPAQuery<AdVendorItemEntity> query = new JPAQuery<>(entityManager);

        query.select(AVI).from(AVI).where(AVI.wholeSalerID.eq(wholeSalerID).and(AVI.categoryID.eq(-10)));

        return query.fetchCount();
    }
}
