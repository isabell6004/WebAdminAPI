package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QSecurityLoginLogEntity;
import net.fashiongo.webadmin.data.entity.primary.QSecurityUserEntity;
import net.fashiongo.webadmin.data.model.admin.SecurityLoginLogs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SecurityLoginLogEntityRepositoryCustomImpl implements SecurityLoginLogEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public long findAllCount(LocalDateTime sDate, LocalDateTime eDate, Integer userID, String ip) {
        QSecurityLoginLogEntity LL = QSecurityLoginLogEntity.securityLoginLogEntity;
        QSecurityUserEntity U = QSecurityUserEntity.securityUserEntity;

        long queryResult = new JPAQuery<>(entityManager)
                .select(LL.logID.count())
                .from(LL)
                .innerJoin(LL.securityUserEntity, U)
                .where(
                        goeLoginOn(LL, sDate),
                        loeLoginOn(LL, eDate),
                        eqUserID(LL, userID),
                        likeIP(LL, ip)
                )
                .fetch().get(0);

        return queryResult;
    }

    private BooleanExpression goeLoginOn(QSecurityLoginLogEntity LL, LocalDateTime sDate) {
        if (sDate == null) {
            return null;
        }
        return LL.loginOn.goe(sDate);
    }

    private BooleanExpression loeLoginOn(QSecurityLoginLogEntity LL, LocalDateTime eDate) {
        if (eDate == null) {
            return null;
        }
        return LL.loginOn.loe(eDate);
    }

    private BooleanExpression eqUserID(QSecurityLoginLogEntity LL, Integer userID) {
        if (userID == null) {
            return null;
        }
        return LL.userID.eq(userID);
    }

    private BooleanExpression likeIP(QSecurityLoginLogEntity LL, String ip) {
        if (StringUtils.isEmpty(ip)) {
            return null;
        }
        return LL.ip.like(ip + "%");
    }


    @Override
    public List<SecurityLoginLogs> findAllLimitOffset(LocalDateTime sDate, LocalDateTime eDate, Integer userID, String ip, Integer pageNum, Integer pageSize, long recCnt) {
        QSecurityLoginLogEntity LL = QSecurityLoginLogEntity.securityLoginLogEntity;
        QSecurityUserEntity U = QSecurityUserEntity.securityUserEntity;

        int lBound = (pageNum - 1) * pageSize;
        int uBound = lBound + pageSize + 1;

        if (lBound >= recCnt) {
            uBound = (int) recCnt + 1;
            lBound = uBound - (pageSize + 1);
        }

        List<SecurityLoginLogs> queryResults = new JPAQuery<>(entityManager)
                .select(Projections.constructor(
                        SecurityLoginLogs.class,
                        LL.logID,
                        U.userName,
                        LL.ip,
                        LL.loginOn))
                .from(LL)
                .innerJoin(LL.securityUserEntity, U)
                .where(goeLoginOn(LL, sDate),
                        loeLoginOn(LL, eDate),
                        eqUserID(LL, userID),
                        likeIP(LL, ip))
                .orderBy(LL.loginOn.desc())
                .offset(lBound)
                .limit(uBound - lBound - 1)
                .fetch();

        return queryResults;
    }
}
