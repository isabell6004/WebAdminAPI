package net.fashiongo.webadmin.data.repository.primary.vendor;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QCustomerSocialMediaEntity;
import net.fashiongo.webadmin.data.entity.primary.QListSocialMediaEntity;
import net.fashiongo.webadmin.data.model.vendor.ListSocialMedia;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ListSocialMediaEntityRepositoryCustomImpl implements ListSocialMediaEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;


    @Override
    public List<ListSocialMedia> findSocialMediaByWholeSalerID(Integer wholeSalerID) {
        JPAQuery<ListSocialMedia> query = new JPAQuery<>(entityManager);

        QListSocialMediaEntity LS = QListSocialMediaEntity.listSocialMediaEntity;
        QCustomerSocialMediaEntity CS = QCustomerSocialMediaEntity.customerSocialMediaEntity;

        query.select(Projections.constructor(ListSocialMedia.class,
                CS.mapID,
                CS.referenceID,
                LS.socialMediaID,
                LS.socialMedia,
                LS.url,
                CS.socialMediaUsername,
                LS.icon,
                LS.listOrder))
                .from(LS)
                .leftJoin(CS).on(LS.socialMediaID.eq(CS.socialMediaID).and(CS.userTypeID.eq(2).and(CS.referenceID.eq(wholeSalerID))));

        return query.fetch();
    }

    @Override
    @Transactional(transactionManager = "primaryTransactionManager")
    public long deleteByIds(List<Integer> ids) {
        QListSocialMediaEntity LS = QListSocialMediaEntity.listSocialMediaEntity;
        JPADeleteClause jpaDeleteClause = new JPADeleteClause(entityManager,LS);

        return jpaDeleteClause
                .where(LS.socialMediaID.in(ids))
                .execute();
    }
}
