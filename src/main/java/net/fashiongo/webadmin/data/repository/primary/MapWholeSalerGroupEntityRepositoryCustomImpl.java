package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.MapWholeSalerGroupEntity;
import net.fashiongo.webadmin.data.entity.primary.QMapWholeSalerGroupEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MapWholeSalerGroupEntityRepositoryCustomImpl implements MapWholeSalerGroupEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<MapWholeSalerGroupEntity> findAllByIds(ArrayList<Integer> wids) {
        QMapWholeSalerGroupEntity WG = QMapWholeSalerGroupEntity.mapWholeSalerGroupEntity;

        JPAQuery<MapWholeSalerGroupEntity> query = new JPAQuery<>(entityManager);

        query.select(WG)
                .from(WG)
                .where(WG.mapID.in(wids));

        return query.fetch();
    }
}
