package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QTrendReportEntity;
import net.fashiongo.webadmin.data.entity.primary.QTrendReportMapEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.TrendReportDefault;
import net.fashiongo.webadmin.data.model.sitemgmt.TrendReportTotal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
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
}
