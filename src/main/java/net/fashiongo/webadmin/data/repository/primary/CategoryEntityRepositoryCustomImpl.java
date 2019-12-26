package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QCategoryEntity;
import net.fashiongo.webadmin.data.model.common.SubCategories;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryEntityRepositoryCustomImpl implements CategoryEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<SubCategories> findAllByLvlAndParentCateID(Integer lvl, Integer parentCateId) {
        QCategoryEntity C = QCategoryEntity.categoryEntity;
        JPAQuery<SubCategories> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(SubCategories.class,
                C.categoryId,
                C.categoryName))
                .from(C)
                .where(C.active.eq(true)
                    .and(C.lvl.eq(lvl))
                    .and(C.parentCategoryId.eq(parentCateId)))
                .orderBy(C.listOrder.asc());

        return query.fetch();
    }
}
