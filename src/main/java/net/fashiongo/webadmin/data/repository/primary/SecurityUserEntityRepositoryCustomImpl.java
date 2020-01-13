package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.sql.SQLExpressions;
import net.fashiongo.webadmin.data.entity.primary.QMapWaUserVendorEntity;
import net.fashiongo.webadmin.data.entity.primary.QSecurityUserEntity;
import net.fashiongo.webadmin.data.entity.primary.SecurityUserEntity;
import net.fashiongo.webadmin.data.model.vendor.AssignedUser;
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

    @Override
    public List<AssignedUser> findAssignedUserList() {
        QSecurityUserEntity U = QSecurityUserEntity.securityUserEntity;
        QMapWaUserVendorEntity M = QMapWaUserVendorEntity.mapWaUserVendorEntity;

        JPAQuery<AssignedUser> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(AssignedUser.class,
                U.userID,
                U.userName))
                .from(U)
                .innerJoin(M).on(U.userID.eq(M.userID))
        .groupBy(U.userID, U.userName);

        return query.fetch();
    }
}
