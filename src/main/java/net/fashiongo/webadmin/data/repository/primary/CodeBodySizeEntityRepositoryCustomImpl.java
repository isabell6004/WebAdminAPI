package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.CodeBodySizeEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodeBodySizeEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.BodySizeInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CodeBodySizeEntityRepositoryCustomImpl implements CodeBodySizeEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<BodySizeInfo> findAllWhereActiveTrue() {
        JPAQuery<BodySizeInfo> query = new JPAQuery<>(entityManager);
        QCodeBodySizeEntity bodySizeEntity = QCodeBodySizeEntity.codeBodySizeEntity;

        query.select(Projections.constructor(BodySizeInfo.class,
                bodySizeEntity.bodySizeID,
                bodySizeEntity.bodySizeName,
                bodySizeEntity.titleImage,
                bodySizeEntity.categoryID,
                bodySizeEntity.active))
                .from(bodySizeEntity)
                .where(bodySizeEntity.active.eq(true));

        return query.fetch();
    }
}
