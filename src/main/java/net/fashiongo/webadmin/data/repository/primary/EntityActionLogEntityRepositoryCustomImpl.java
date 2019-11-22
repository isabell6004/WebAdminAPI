package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QEntityActionLogEntity;
import net.fashiongo.webadmin.data.entity.primary.QListEntityActionEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EntityActionLogEntityRepositoryCustomImpl implements EntityActionLogEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<VendorHistory> findByEntityIDAndEntityTypeID(Integer wid) {
        QEntityActionLogEntity LOG = QEntityActionLogEntity.entityActionLogEntity;
        QListEntityActionEntity ACTION = QListEntityActionEntity.listEntityActionEntity;
        JPAQuery<VendorHistory> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(VendorHistory.class,
                ACTION.entityActionName,
                LOG.actedBy,
                LOG.actedOn,
                LOG.remark))
                .from(LOG)
                .innerJoin(LOG.listEntityAction, ACTION)
                .where(LOG.entityID.eq(wid).and(LOG.entityTypeID.eq(1).and(LOG.actionID.eq(3001).or(LOG.actionID.eq(3002))))).orderBy(LOG.actedOn.desc());

        return query.fetch();
    }
}
