package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QCodeCreditCardTypeEntity;
import net.fashiongo.webadmin.data.model.payment.CodeCreditCardType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CodeCreditCardTypeEntityRepositoryCustomImpl implements CodeCreditCardTypeEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    @Transactional(value = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
    public List<CodeCreditCardType> findAllCodeCreditCardType() {
        QCodeCreditCardTypeEntity CCC = QCodeCreditCardTypeEntity.codeCreditCardTypeEntity;

        JPAQuery<CodeCreditCardType> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(
                CodeCreditCardType.class,
                CCC.creditCardTypeID,
                CCC.creditCardType))
            .from(CCC);

        return query.fetch();
    }
}
