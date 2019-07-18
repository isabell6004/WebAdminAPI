package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QCategoryEntity;
import net.fashiongo.webadmin.data.entity.primary.QTrendDailyKeywordEntity;
import net.fashiongo.webadmin.data.entity.primary.TrendDailyKeywordEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TrendDailyKeywordEntityRepositoryCustomImpl implements TrendDailyKeywordEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<TrendDailyKeywordEntity> findAllBetweenFromTo(LocalDateTime fromDate, LocalDateTime toDate) {
        QTrendDailyKeywordEntity trendDailyKeyword = QTrendDailyKeywordEntity.trendDailyKeywordEntity;
        QCategoryEntity category = QCategoryEntity.categoryEntity;

        JPAQuery<TrendDailyKeywordEntity> query = new JPAQuery<>(entityManager);

        query.select(trendDailyKeyword)
                .from(trendDailyKeyword)
                .leftJoin(trendDailyKeyword.category, category).fetchJoin()
                .where(trendDailyKeyword.exposeDate.goe(fromDate).and(trendDailyKeyword.exposeDate.loe(toDate)));

        return query.fetch();
    }

    @Override
    public TrendDailyKeywordEntity findOneById(Long id) {
        QTrendDailyKeywordEntity trendDailyKeyword = QTrendDailyKeywordEntity.trendDailyKeywordEntity;
        QCategoryEntity category = QCategoryEntity.categoryEntity;

        JPAQuery<TrendDailyKeywordEntity> query = new JPAQuery<>(entityManager);

        query.select(trendDailyKeyword)
                .from(trendDailyKeyword)
                .leftJoin(trendDailyKeyword.category, category).fetchJoin();

        if(id == null) {
            query.where(trendDailyKeyword.trendDailyKeywordID.isNull());
        } else {
            query.where(trendDailyKeyword.trendDailyKeywordID.eq(id));
        }

        return query.fetchOne();
    }
}
