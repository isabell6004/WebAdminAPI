package net.fashiongo.webadmin.dao.primary.custom;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.model.primary.NewsRecipient;
import net.fashiongo.webadmin.model.primary.QNewsEntity;
import net.fashiongo.webadmin.model.primary.QReadOnlyWholeSalerNameEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class NewsEntityRepositoryCustomImpl implements NewsEntityRepositoryCustom {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager newsEntityManger;

    @Override
    @Transactional
    public NewsRecipient findOneByNewsID(Integer newsID) {
        QNewsEntity newsEntity = QNewsEntity.newsEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;

        Tuple queryResult = new JPAQuery<>(newsEntityManger)
                .select(newsEntity, readOnlyWholeSalerNameEntity.companyName)
                .from(newsEntity)
                .leftJoin(newsEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity)
                .where(newsEntity.newsId.eq(newsID))
                .fetchOne();

        NewsRecipient result = new NewsRecipient();
        result.setNews(queryResult.get(newsEntity));
        if (queryResult.get(readOnlyWholeSalerNameEntity.companyName) == null) {
            result.setRecipient("All");
        } else {
            result.setRecipient(queryResult.get(readOnlyWholeSalerNameEntity.companyName));
        }

        return result;
    }
}
