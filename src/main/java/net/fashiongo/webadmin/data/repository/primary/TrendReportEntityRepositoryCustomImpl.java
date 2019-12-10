package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.sitemgmt.TrendReport;
import net.fashiongo.webadmin.data.model.sitemgmt.TrendReportDefault;
import net.fashiongo.webadmin.data.model.sitemgmt.TrendReportTotal;
import net.fashiongo.webadmin.utility.MSSQLServer2012Templates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class TrendReportEntityRepositoryCustomImpl implements TrendReportEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public TrendReportTotal getRecCnt() {
        QTrendReportEntity T = QTrendReportEntity.trendReportEntity;

        JPAQuery<Long> query = new JPAQuery<>(entityManager);

        LocalDateTime now = LocalDateTime.now();

        query.select(T.trendReportID.count())
                .from(T)
                .where(T.dateFrom.lt(now).and(T.dateTo.gt(now)).and(T.active.eq(true)));

        return new TrendReportTotal(query.fetchOne());
    }

    @Override
    public List<TrendReportDefault> getTrendReportDefault(String orderBy, String orderByGubn) {
        String newOrderBy = orderBy.substring(0,1).toLowerCase() + orderBy.substring(1);

        QTrendReportEntity T = QTrendReportEntity.trendReportEntity;
        QTrendReportMapEntity M = QTrendReportMapEntity.trendReportMapEntity;

        JPAQuery<TrendReportDefault> query = new JPAQuery<>(entityManager);

        LocalDateTime now = LocalDateTime.now();

        query.select(Projections.constructor(TrendReportDefault.class,
                T.trendReportID,
                T.title,
                T.image,
                T.dateFrom,
                T.dateTo,
                T.listOrder,
                T.active,
                T.createdBy,
                T.trendReportID.count()))
                .from(T)
                .leftJoin(T.trendReportMap, M)
                .where(T.dateFrom.lt(now).and(T.dateTo.gt(now)).and(T.active.eq(true)))
                .groupBy(T.trendReportID, T.title, T.image, T.dateFrom, T.dateTo, T.listOrder, T.active, T.createdBy);

        StringPath _orderby = Expressions.stringPath(T, newOrderBy);
        if(orderBy == null) {
            query.orderBy(T.trendReportID.desc());
        } else {
            if(orderByGubn.equalsIgnoreCase("desc"))
                query.orderBy(_orderby.desc());
            else if(orderByGubn.equalsIgnoreCase("asc"))
                query.orderBy(_orderby.asc());
        }

        return query.fetch();
    }

    @Override
    public Page<TrendReport> up_wa_GetAdminTrendReport(int pageNo, int pageSize, String searchText, LocalDateTime fromDate, LocalDateTime toDate, String orderBy, String orderByGubn, Boolean active) {
        long offset = (pageNo - 1) * pageSize;
        long limit = pageSize;

        JPASQLQuery<TrendReport> jpasqlQuery = new JPASQLQuery(entityManager,new MSSQLServer2012Templates());
        QTrendReportEntity T = QTrendReportEntity.trendReportEntity;
        QTrendReportMapEntity TRM = QTrendReportMapEntity.trendReportMapEntity;
        QTrendReportMapCandidateEntity TRMC = QTrendReportMapCandidateEntity.trendReportMapCandidateEntity;
        DateTimePath<Timestamp> t_DateFrom = Expressions.dateTimePath(Timestamp.class, T, T.dateFrom.getMetadata().getName());
        DateTimePath<Timestamp> t_DateTo = Expressions.dateTimePath(Timestamp.class, T, T.dateTo.getMetadata().getName());
        BooleanExpression expression = Expressions.ONE.eq(1);

        if(searchText != null) {
            expression = expression.and(T.title.contains(searchText).or(T.createdBy.contains(searchText)));
        }

        if(fromDate != null) {
            String format = fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ComparableTemplate<String> fromDateTemplate = Expressions.comparableTemplate(String.class, "CONVERT(VARCHAR(10),{0},121)", T.dateFrom);
            expression = expression.and(fromDateTemplate.goe(format));
        }

        if(toDate != null) {
            String format = toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            ComparableTemplate<String> toDateTemplate = Expressions.comparableTemplate(String.class, "CONVERT(VARCHAR(10),{0},121)", T.dateTo);
            expression = expression.and(toDateTemplate.loe(format));
        }

        if(active != null) {
            expression = expression.and(T.active.eq(active));
        }

        OrderSpecifier orderSpecifier = null;
        if(orderBy == null) {
            orderSpecifier = T.trendReportID.desc();
        } else {
            StringPath path = Expressions.stringPath(orderBy);

            if(orderByGubn.toLowerCase().equals("desc")) {
                orderSpecifier = path.desc();
            } else {
                orderSpecifier = path.asc();
            }
        }

        jpasqlQuery.select(
                Projections.constructor(TrendReport.class
                        ,T.active
                        ,T.createdBy
                        ,t_DateFrom
                        ,t_DateTo
                        ,T.image
                        ,Expressions.cases()
                                .when(
                                        T.curatedType.eq(4)
                                ).then(
                                        SQLExpressions.select(TRMC.candidateID.count())
                                                .from(TRMC)
                                                .where(TRMC.trendReportID.eq(T.trendReportID))
                                ).otherwise(
                                        SQLExpressions.select(TRM.trendReportID.count())
                                                .from(TRM)
                                                .where(TRM.trendReportID.eq(T.trendReportID))
                                ).as("ItemCount")
                        ,T.listOrder
                        ,T.title
                        ,T.trendReportID
                        ,SQLExpressions.rowNumber().over().orderBy(orderSpecifier).as("row"))
        )
                .from(T)
                .where(
                    expression
                )
                .orderBy(orderSpecifier)
                .offset(offset)
                .limit(limit);

        QueryResults<TrendReport> trendReportQueryResults = jpasqlQuery.fetchResults();
        long total = trendReportQueryResults.getTotal();
        List<TrendReport> results = trendReportQueryResults.getResults();

        PageRequest pageRequest = PageRequest.of(pageNo-1, pageSize);
        return PageableExecutionUtils.getPage(results,pageRequest,()-> total);
    }

    @Override
    @Transactional(transactionManager = "primaryTransactionManager")
    public void up_wa_AddDelTrendReportItem(String setType, int mapId, int trendreportId, int productId, String modifiedBy) {
        JPAQuery<Integer> jpaQuery = new JPAQuery(entityManager);
        QTrendReportEntity T = QTrendReportEntity.trendReportEntity;

        jpaQuery.select(T.curatedType)
                .from(T)
                .where(
                        T.trendReportID.eq(trendreportId)
                );


        Integer curatedType = jpaQuery.fetchOne();
        LocalDateTime NOW = LocalDateTime.now();
        if(curatedType != null && curatedType == 4) {
            if(setType.equalsIgnoreCase("del")) {
                QTrendReportMapCandidateEntity TRMC = QTrendReportMapCandidateEntity.trendReportMapCandidateEntity;
                JPAQuery<Integer> productIdQuery = new JPAQuery<>(entityManager);

                Integer selectedProductId = productIdQuery.select(TRMC.productID)
                        .from(TRMC)
                        .where(TRMC.candidateID.eq(mapId))
                        .fetchOne();

                JPADeleteClause jpaDeleteClause = new JPADeleteClause(entityManager,TRMC);
                jpaDeleteClause.where(TRMC.candidateID.eq(mapId)).execute();

                EntityActionLogEntity entityActionLogEntity = new EntityActionLogEntity();
                entityActionLogEntity.setEntityTypeID(6);
                entityActionLogEntity.setActionID(6001);
                entityActionLogEntity.setEntityID(mapId);
                entityActionLogEntity.setRemark(selectedProductId.toString());
                entityActionLogEntity.setActedOn(NOW);
                entityActionLogEntity.setActedBy(modifiedBy);
                entityManager.persist(entityActionLogEntity);
            }

            if(setType.equalsIgnoreCase("add")) {
                QTrendReportMapCandidateEntity TRMC = QTrendReportMapCandidateEntity.trendReportMapCandidateEntity;
                JPADeleteClause jpaDeleteClause = new JPADeleteClause(entityManager,TRMC);

                jpaDeleteClause.where(TRMC.trendReportID.eq(trendreportId).and(TRMC.productID.eq(productId))).execute();
                TrendReportMapCandidateEntity trendReportMapCandidateEntity = new TrendReportMapCandidateEntity();
                trendReportMapCandidateEntity.setTrendReportID(trendreportId);
                trendReportMapCandidateEntity.setProductID(productId);
                trendReportMapCandidateEntity.setListOrder(0);
                entityManager.persist(trendReportMapCandidateEntity);
            }
        } else {
            if(setType.equalsIgnoreCase("del")) {
                QTrendReportMapEntity TRM = QTrendReportMapEntity.trendReportMapEntity;
                JPAQuery<Integer> productIdQuery = new JPAQuery<>(entityManager);

                Integer selectedProductId = productIdQuery.select(TRM.productID)
                        .from(TRM)
                        .where(TRM.mapID.eq(mapId))
                        .fetchOne();

                JPADeleteClause jpaDeleteClause = new JPADeleteClause(entityManager,TRM);
                jpaDeleteClause.where(TRM.mapID.eq(mapId)).execute();

                EntityActionLogEntity entityActionLogEntity = new EntityActionLogEntity();
                entityActionLogEntity.setEntityTypeID(6);
                entityActionLogEntity.setActionID(6001);
                entityActionLogEntity.setEntityID(mapId);
                entityActionLogEntity.setRemark(selectedProductId.toString());
                entityActionLogEntity.setActedOn(NOW);
                entityActionLogEntity.setActedBy(modifiedBy);
                entityManager.persist(entityActionLogEntity);
            }

            if(setType.equalsIgnoreCase("add")) {
                QTrendReportMapEntity TRM = QTrendReportMapEntity.trendReportMapEntity;
                JPADeleteClause jpaDeleteClause = new JPADeleteClause(entityManager,TRM);

                jpaDeleteClause.where(TRM.trendReportID.eq(trendreportId).and(TRM.productID.eq(productId))).execute();
                TrendReportMapEntity trendReportMapEntity = new TrendReportMapEntity();
                trendReportMapEntity.setTrendReportID(trendreportId);
                trendReportMapEntity.setProductID(productId);
                trendReportMapEntity.setSortNo(0);
                trendReportMapEntity.setCreatedOn(NOW);
                trendReportMapEntity.setCreatedBy(modifiedBy);
                entityManager.persist(trendReportMapEntity);
            }
        }

        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(entityManager,T);

        jpaUpdateClause.set(T.modifiedOn,NOW)
                .where(T.trendReportID.eq(trendreportId))
                .execute();
    }
}
