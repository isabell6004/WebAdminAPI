package net.fashiongo.webadmin.dao.primary.custom;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.model.primary.*;
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

}