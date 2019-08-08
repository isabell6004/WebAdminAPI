package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QSecurityListIPEntity;
import net.fashiongo.webadmin.data.entity.primary.SecurityListIPEntity;
import net.fashiongo.webadmin.data.model.admin.SecurityListIP;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SecurityListIPEntityRepositoryCustomImpl implements SecurityListIPEntityRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SecurityListIP> findAllOrderByIPID() {
        QSecurityListIPEntity listIPEntity = QSecurityListIPEntity.securityListIPEntity;

        JPAQuery query = new JPAQuery<>(entityManager)
                .select(Projections.constructor(
                        SecurityListIP.class,
                        listIPEntity.ipID,
                        listIPEntity.ipAddress,
                        listIPEntity.description
                ))
                .from(listIPEntity)
                .orderBy(listIPEntity.ipID.desc());

        return query.fetch();
    }
}
