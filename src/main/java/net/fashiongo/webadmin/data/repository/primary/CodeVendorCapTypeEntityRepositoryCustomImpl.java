package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.CodeVendorCapTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.QCodeVendorCapTypeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CodeVendorCapTypeEntityRepositoryCustomImpl implements CodeVendorCapTypeEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<CodeVendorCapTypeEntity> findVendorCapDefault() {
        QCodeVendorCapTypeEntity X = QCodeVendorCapTypeEntity.codeVendorCapTypeEntity;
        JPAQuery<CodeVendorCapTypeEntity> query = new JPAQuery<>(entityManager);

        query.select(X).from(X);

        return query.fetch();
    }
}
