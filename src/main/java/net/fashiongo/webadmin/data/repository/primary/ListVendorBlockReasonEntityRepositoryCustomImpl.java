package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.ListVendorBlockReasonEntity;
import net.fashiongo.webadmin.data.entity.primary.QListVendorBlockReasonEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ListVendorBlockReasonEntityRepositoryCustomImpl implements ListVendorBlockReasonEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<ListVendorBlockReasonEntity> findVendorBlockReason() {
        QListVendorBlockReasonEntity X = QListVendorBlockReasonEntity.listVendorBlockReasonEntity;
        JPAQuery<ListVendorBlockReasonEntity> query = new JPAQuery<>(entityManager);

        query.select(X).from(X);

        return query.fetch();
    }
}
