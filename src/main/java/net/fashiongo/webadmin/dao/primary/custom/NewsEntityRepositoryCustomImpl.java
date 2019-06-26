package net.fashiongo.webadmin.dao.primary.custom;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.model.primary.NewsEntity;
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
    public NewsEntity findOneByNewsID(Integer newsID) {
        QNewsEntity newsEntity = QNewsEntity.newsEntity;
        QReadOnlyWholeSalerNameEntity readOnlyWholeSalerNameEntity = QReadOnlyWholeSalerNameEntity.readOnlyWholeSalerNameEntity;

        NewsEntity result = new JPAQuery<>(newsEntityManger)
                .select(newsEntity)
                .from(newsEntity)
                .leftJoin(newsEntity.readOnlyWholeSalerNameEntity, readOnlyWholeSalerNameEntity).fetchJoin()
                .where(newsEntity.newsId.eq(newsID))
                .fetchOne();

        return result;
    }
}
