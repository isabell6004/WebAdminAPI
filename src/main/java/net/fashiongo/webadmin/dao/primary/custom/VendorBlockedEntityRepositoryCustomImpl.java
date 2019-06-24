package net.fashiongo.webadmin.dao.primary.custom;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.model.primary.QListVendorBlockReasonEntity;
import net.fashiongo.webadmin.model.primary.QReadOnlyWholeSalerNameEntity;
import net.fashiongo.webadmin.model.primary.QVendorBlockedEntity;
import net.fashiongo.webadmin.model.primary.VendorBlockReason;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VendorBlockedEntityRepositoryCustomImpl implements VendorBlockedEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager vendorBlockedEntityManager;

    @Override
    @Transactional
    public List<VendorBlockReason> findAllList() {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;
        QListVendorBlockReasonEntity listVendorBlockReasonEntity = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;

        List<Tuple> queryResults = new JPAQuery<>(vendorBlockedEntityManager)
                .select(vendorBlockedEntity, readOnlyWholeSalerNameEntity.companyName, listVendorBlockReasonEntity.blockReasonTitle, listVendorBlockReasonEntity.blockReasonDetail)
                .from(vendorBlockedEntity)
                .innerJoin(vendorBlockedEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity)
                .innerJoin(vendorBlockedEntity.listVendorBlockReasonEntity, listVendorBlockReasonEntity)
                .fetch();

        return makeList(queryResults);
    }

    @Override
    @Transactional
    public List<VendorBlockReason> findByBlockID(Integer searchKeyword) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;
        QListVendorBlockReasonEntity listVendorBlockReasonEntity = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;

        List<Tuple> queryResults = new JPAQuery<>(vendorBlockedEntityManager)
                .select(vendorBlockedEntity, readOnlyWholeSalerNameEntity.companyName, listVendorBlockReasonEntity.blockReasonTitle, listVendorBlockReasonEntity.blockReasonDetail)
                .from(vendorBlockedEntity)
                .innerJoin(vendorBlockedEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity)
                .innerJoin(vendorBlockedEntity.listVendorBlockReasonEntity, listVendorBlockReasonEntity)
                .where(vendorBlockedEntity.blockId.eq(searchKeyword))
                .fetch();

        return makeList(queryResults);
    }

    @Override
    @Transactional
    public List<VendorBlockReason> findByCompanyNameContainingIgnoreCase(String searchKeyword) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;
        QListVendorBlockReasonEntity listVendorBlockReasonEntity = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;

        List<Tuple> queryResults = new JPAQuery<>(vendorBlockedEntityManager)
                .select(vendorBlockedEntity, readOnlyWholeSalerNameEntity.companyName, listVendorBlockReasonEntity.blockReasonTitle, listVendorBlockReasonEntity.blockReasonDetail)
                .from(vendorBlockedEntity)
                .innerJoin(vendorBlockedEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity)
                .innerJoin(vendorBlockedEntity.listVendorBlockReasonEntity, listVendorBlockReasonEntity)
                .where(vendorBlockedEntity.readOnlyWholeSalerNameEntity.companyName.equalsIgnoreCase(searchKeyword))
                .fetch();

        return makeList(queryResults);
    }

    @Override
    @Transactional
    public List<VendorBlockReason> findByBlockedOnBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;
        QListVendorBlockReasonEntity listVendorBlockReasonEntity = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;

        List<Tuple> queryResults = new JPAQuery<>(vendorBlockedEntityManager)
                .select(vendorBlockedEntity, readOnlyWholeSalerNameEntity.companyName, listVendorBlockReasonEntity.blockReasonTitle, listVendorBlockReasonEntity.blockReasonDetail)
                .from(vendorBlockedEntity)
                .innerJoin(vendorBlockedEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity)
                .innerJoin(vendorBlockedEntity.listVendorBlockReasonEntity, listVendorBlockReasonEntity)
                .where(vendorBlockedEntity.blockedOn.gt(fromDate).and(vendorBlockedEntity.blockedOn.lt(toDate)))
                .fetch();

        return makeList(queryResults);
    }

    @Override
    @Transactional
    public List<VendorBlockReason> findByBlockReasonTitle(String searchKeyword) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;
        QListVendorBlockReasonEntity listVendorBlockReasonEntity = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;

        List<Tuple> queryResults = new JPAQuery<>(vendorBlockedEntityManager)
                .select(vendorBlockedEntity, readOnlyWholeSalerNameEntity.companyName, listVendorBlockReasonEntity.blockReasonTitle, listVendorBlockReasonEntity.blockReasonDetail)
                .from(vendorBlockedEntity)
                .innerJoin(vendorBlockedEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity)
                .innerJoin(vendorBlockedEntity.listVendorBlockReasonEntity, listVendorBlockReasonEntity)
                .where(vendorBlockedEntity.listVendorBlockReasonEntity.blockReasonTitle.eq(searchKeyword))
                .fetch();

        return makeList(queryResults);
    }

    private List<VendorBlockReason> makeList(List<Tuple> queryResults) {
        QVendorBlockedEntity vendorBlockedEntity = QVendorBlockedEntity.vendorBlockedEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;
        QListVendorBlockReasonEntity listVendorBlockReasonEntity = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;

        List<VendorBlockReason> results = new ArrayList<>();
        for (Tuple queryResult : queryResults) {
            VendorBlockReason result = new VendorBlockReason();
            result.setVendorBlockedEntity(queryResult.get(vendorBlockedEntity));
            result.setCompanyName(queryResult.get(readOnlyWholeSalerNameEntity.companyName));
            result.setBlockedReasonTitle(queryResult.get(listVendorBlockReasonEntity.blockReasonTitle));
            result.setBlockedReasonDetail(queryResult.get(listVendorBlockReasonEntity.blockReasonDetail));

            results.add(result);
        }

        return results;
    }

}
