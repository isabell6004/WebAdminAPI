package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QListVendorBlockReasonEntity;
import net.fashiongo.webadmin.data.entity.primary.QReadOnlyWholeSalerNameEntity;
import net.fashiongo.webadmin.data.entity.primary.QVendorBlockedEntity;
import net.fashiongo.webadmin.data.entity.primary.VendorBlockedEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VendorBlockedEntityRepositoryCustomImpl implements VendorBlockedEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager vendorBlockedEntityManager;

    @Override
    @Transactional
    public List<VendorBlockedEntity> findAllList() {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;
        QListVendorBlockReasonEntity listVendorBlockReasonEntity = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;

        List<VendorBlockedEntity> queryResults = new JPAQuery<>(vendorBlockedEntityManager)
                .select(vendorBlockedEntity)
                .from(vendorBlockedEntity)
                .innerJoin(vendorBlockedEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity).fetchJoin()
                .innerJoin(vendorBlockedEntity.listVendorBlockReasonEntity, listVendorBlockReasonEntity).fetchJoin()
                .fetch();

        return queryResults;
    }

    @Override
    @Transactional
    public List<VendorBlockedEntity> findByBlockID(Integer searchKeyword) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;
        QListVendorBlockReasonEntity listVendorBlockReasonEntity = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;

        List<VendorBlockedEntity> queryResults = new JPAQuery<>(vendorBlockedEntityManager)
                .select(vendorBlockedEntity)
                .from(vendorBlockedEntity)
                .innerJoin(vendorBlockedEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity).fetchJoin()
                .innerJoin(vendorBlockedEntity.listVendorBlockReasonEntity, listVendorBlockReasonEntity).fetchJoin()
                .where(vendorBlockedEntity.blockId.eq(searchKeyword))
                .fetch();

        return queryResults;
    }

    @Override
    @Transactional
    public List<VendorBlockedEntity> findByCompanyNameContainingIgnoreCase(String searchKeyword) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;
        QListVendorBlockReasonEntity listVendorBlockReasonEntity = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;

        List<VendorBlockedEntity> queryResults = new JPAQuery<>(vendorBlockedEntityManager)
                .select(vendorBlockedEntity)
                .from(vendorBlockedEntity)
                .innerJoin(vendorBlockedEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity).fetchJoin()
                .innerJoin(vendorBlockedEntity.listVendorBlockReasonEntity, listVendorBlockReasonEntity).fetchJoin()
                .where(vendorBlockedEntity.readOnlyWholeSalerNameEntity.companyName.equalsIgnoreCase(searchKeyword))
                .fetch();

        return queryResults;
    }

    @Override
    @Transactional
    public List<VendorBlockedEntity> findByBlockedOnBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;
        QListVendorBlockReasonEntity listVendorBlockReasonEntity = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;

        List<VendorBlockedEntity> queryResults = new JPAQuery<>(vendorBlockedEntityManager)
                .select(vendorBlockedEntity)
                .from(vendorBlockedEntity)
                .innerJoin(vendorBlockedEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity).fetchJoin()
                .innerJoin(vendorBlockedEntity.listVendorBlockReasonEntity, listVendorBlockReasonEntity).fetchJoin()
                .where(vendorBlockedEntity.blockedOn.gt(fromDate).and(vendorBlockedEntity.blockedOn.lt(toDate)))
                .fetch();

        return queryResults;
    }

    @Override
    @Transactional
    public List<VendorBlockedEntity> findByBlockReasonTitle(String searchKeyword) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;
        QListVendorBlockReasonEntity listVendorBlockReasonEntity = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;

        List<VendorBlockedEntity> queryResults = new JPAQuery<>(vendorBlockedEntityManager)
                .select(vendorBlockedEntity)
                .from(vendorBlockedEntity)
                .innerJoin(vendorBlockedEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity).fetchJoin()
                .innerJoin(vendorBlockedEntity.listVendorBlockReasonEntity, listVendorBlockReasonEntity).fetchJoin()
                .where(vendorBlockedEntity.listVendorBlockReasonEntity.blockReasonTitle.eq(searchKeyword))
                .fetch();

        return queryResults;
    }

    @Override
    public List<VendorBlockedEntity> findByWholeSalerID(Integer wid) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        JPAQuery<VendorBlockedEntity> query = new JPAQuery<>(vendorBlockedEntityManager);

        query.select(vendorBlockedEntity)
                .from(vendorBlockedEntity)
                .where(vendorBlockedEntity.wholeSalerId.eq(wid));

        return query.fetch();
    }

}
