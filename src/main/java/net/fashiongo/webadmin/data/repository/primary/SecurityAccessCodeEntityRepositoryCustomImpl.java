package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QSecurityAccessCodeEntity;
import net.fashiongo.webadmin.data.entity.primary.SecurityAccessCodeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SecurityAccessCodeEntityRepositoryCustomImpl implements SecurityAccessCodeEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<SecurityAccessCodeEntity> findAllAccessCode(String accessCode, LocalDateTime startDate, LocalDateTime endDate) {
        QSecurityAccessCodeEntity SAC = QSecurityAccessCodeEntity.securityAccessCodeEntity;

        BooleanExpression accessCodeEmpty = Expressions.asBoolean(accessCode.equals("")).isTrue();

        List<SecurityAccessCodeEntity> queryResults = new JPAQuery<>(entityManager)
                .select(SAC)
                .from(SAC)
                .where((SAC.expiredOn.goe(startDate).and(SAC.expiredOn.loe(endDate)))
                        .and((accessCodeEmpty).or(accessCodeEmpty.not().and(SAC.accessCode.contains(accessCode)))))
                .fetch();

        return queryResults;
    }
}
