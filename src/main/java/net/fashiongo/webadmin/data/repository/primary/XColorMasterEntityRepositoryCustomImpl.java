package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QXColorMasterEntity;
import net.fashiongo.webadmin.data.entity.primary.XColorMasterEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.ColorListInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class XColorMasterEntityRepositoryCustomImpl implements XColorMasterEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<ColorListInfo> findAllColors() {
        JPAQuery<ColorListInfo> query = new JPAQuery<>(entityManager);
        QXColorMasterEntity colorMasterEntity = QXColorMasterEntity.xColorMasterEntity;

        query.select(Projections.constructor(ColorListInfo.class,
                colorMasterEntity.colorListID,
                colorMasterEntity.masterColorName,
                colorMasterEntity.colorName,
                colorMasterEntity.active))
                .from(colorMasterEntity);

        return query.fetch();
    }
}
