package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QSecurityUserEntity;
import net.fashiongo.webadmin.data.entity.primary.SecurityUserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SecurityUserEntityRepositoryCustomImpl implements SecurityUserEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<SecurityUserEntity> findAllActive() {
        QSecurityUserEntity SU = QSecurityUserEntity.securityUserEntity;
        JPAQuery<SecurityUserEntity> query = new JPAQuery<>(entityManager);

        query.select(SU).from(SU).where(SU.active.eq(true));

        return query.fetch();
    }
}
