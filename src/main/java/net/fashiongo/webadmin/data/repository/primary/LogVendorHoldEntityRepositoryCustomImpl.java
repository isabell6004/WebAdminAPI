package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.LogVendorHoldEntity;
import net.fashiongo.webadmin.data.entity.primary.QLogVendorHoldEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class LogVendorHoldEntityRepositoryCustomImpl implements LogVendorHoldEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<LogVendorHoldEntity> findByWholeSalerIDAndActiveAndHoldTo(Integer wid) {
        QLogVendorHoldEntity X = QLogVendorHoldEntity.logVendorHoldEntity;
        JPAQuery<LogVendorHoldEntity> query = new JPAQuery<>(entityManager);

        query.select(X).from(X).where(X.wholeSalerID.eq(wid).and(X.active.eq(true).and(X.holdTo.lt(Timestamp.valueOf(LocalDateTime.now())))));

        return query.fetch();
    }
}
