package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import net.fashiongo.webadmin.data.entity.primary.QEntityActionLogEntity;
import net.fashiongo.webadmin.data.entity.primary.QListEntityActionEntity;
import net.fashiongo.webadmin.data.entity.primary.QRetailerEntity;
import net.fashiongo.webadmin.data.model.buyer.ModifiedByBuyer;
import net.fashiongo.webadmin.data.model.vendor.VendorHistory;
import net.fashiongo.webadmin.data.repository.QueryDSLSQLFunctions;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EntityActionLogEntityRepositoryCustomImpl implements EntityActionLogEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Autowired
    private QueryDSLSQLFunctions queryDSLSQLFunctions;

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

    @Override
    public List<ModifiedByBuyer> up_wa_GetModifiedByBuyer(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        MSSQLServer2012Templates mssqlServer2012Templates = new MSSQLServer2012Templates();
        JPASQLQuery<ModifiedByBuyer> jpasqlQuery = new JPASQLQuery<ModifiedByBuyer>(entityManager,mssqlServer2012Templates);
        QRetailerEntity R = QRetailerEntity.retailerEntity;

//        select	a.EntityID RetailerID,
//        a.ActedOn LastModifiedOn,
//        c.CompanyName RetailerName,
//        concat(c.FirstName, ' ', c.LastName) FullName
//        from	(
//                select  EntityID, max(ActedOn) ActedOn
//                from dbo.Entity_ActionLog
//                where ActionID=4003
//                group by EntityID
//        ) a
//        left outer join
//                (
//                        select  EntityID, max(ActedOn) ActedOn
//                        from dbo.Entity_ActionLog
//                        where ActionID=4005
//                        group by EntityID
//                ) b on a.EntityID = b.EntityID
//        inner join dbo.tblRetailer c on a.EntityID = c.RetailerID and c.CurrentStatus = 3
//        and (c.SellerPermitFileName is not null or c.InvoiceFileName1 is not null or c.InvoiceFileName2 is not null)
//        where a.ActedOn >= isnull(b.ActedOn, a.ActedOn)
//        order by LastModifiedOn desc

        JPASQLQuery aQuery = new JPASQLQuery(entityManager,mssqlServer2012Templates);
        QEntityActionLogEntity A_LOG = new QEntityActionLogEntity("A_LOG");
        SimplePath<Object> PATH_A = Expressions.path(Object.class, "A");
        NumberPath<Integer> PATH_A_EntityID = Expressions.numberPath(Integer.class, PATH_A, "EntityID");
        DateTimePath<Timestamp> PATH_A_ActedOn = Expressions.dateTimePath(Timestamp.class, PATH_A, "ActedOn");

        aQuery.select(A_LOG.entityID, A_LOG.actedOn.max().as("ActedOn"))
                .from(A_LOG)
                .where(A_LOG.actionID.eq(4003))
                .groupBy(A_LOG.entityID);

        JPASQLQuery bQuery = new JPASQLQuery(entityManager,mssqlServer2012Templates);
        QEntityActionLogEntity B_LOG = new QEntityActionLogEntity("A_LOG");
        SimplePath<Object> PATH_B = Expressions.path(Object.class, "B");
        NumberPath<Integer> PATH_B_EntityID = Expressions.numberPath(Integer.class, PATH_B, "EntityID");
        DateTimePath<LocalDateTime> PATH_B_ActedOn = Expressions.dateTimePath(LocalDateTime.class, PATH_B, "ActedOn");
        bQuery.select(B_LOG.entityID, B_LOG.actedOn.max().as("ActedOn"))
                .from(B_LOG)
                .where(B_LOG.actionID.eq(4005))
                .groupBy(B_LOG.entityID);


        jpasqlQuery
                .select(
                        Projections.constructor(
                                ModifiedByBuyer.class
                                ,PATH_A_EntityID
                                ,PATH_A_ActedOn
                                ,R.companyName
                                ,R.firstName.concat(" ").concat(R.lastName)
                        )
                )
                .from(aQuery,PATH_A)
                .leftJoin(bQuery,PATH_B).on(PATH_A_EntityID.eq(PATH_B_EntityID))
                .innerJoin(R).on(
                        PATH_A_EntityID.eq(R.retailerID).and(R.currentStatus.eq(3)).and(
                                        R.sellerPermitFileName.isNotNull().or(R.invoiceFileName1.isNotNull()).or(R.invoiceFileName2.isNotNull())
                        )
                ).where(
                    PATH_A_ActedOn.goe(
                            queryDSLSQLFunctions.isnull(Timestamp.class,PATH_B_ActedOn,PATH_A_ActedOn)
                    )
        ).orderBy(PATH_A_ActedOn.desc());


        return jpasqlQuery.fetch();
    }
}
