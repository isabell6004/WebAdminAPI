package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QTrendReportContentsEntity;
import net.fashiongo.webadmin.data.entity.primary.TrendReportContentsEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TrendReportContentsEntityRepositoryCustomImpl implements TrendReportContentsEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public TrendReportContentsEntity findOneByTrendReportID(Integer trendReportId) {
        QTrendReportContentsEntity TRC = QTrendReportContentsEntity.trendReportContentsEntity;

        JPAQuery<TrendReportContentsEntity> query = new JPAQuery<>(entityManager);

        query.select(TRC)
                .from(TRC)
                .where(TRC.trendReportID.eq(trendReportId));

        return query.fetchFirst();
    }
}
