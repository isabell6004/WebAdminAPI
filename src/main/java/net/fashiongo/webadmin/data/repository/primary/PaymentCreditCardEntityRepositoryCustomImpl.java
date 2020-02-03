package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.QCodeCreditCardTypeEntity;
import net.fashiongo.webadmin.data.entity.primary.QListCardStatusEntity;
import net.fashiongo.webadmin.data.entity.primary.QPaymentCreditCardEntity;
import net.fashiongo.webadmin.data.model.payment.PaymentCreditCardInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PaymentCreditCardEntityRepositoryCustomImpl implements PaymentCreditCardEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public List<PaymentCreditCardInfo> getPaymentCreditCardInfo(Integer creditCardID) {
        QPaymentCreditCardEntity A = new QPaymentCreditCardEntity("a");
        QCodeCreditCardTypeEntity B = new QCodeCreditCardTypeEntity("b");
        QListCardStatusEntity C = new QListCardStatusEntity("c");

        JPAQuery<PaymentCreditCardInfo> query = new JPAQuery<>(entityManager);

        query.select(Projections.constructor(PaymentCreditCardInfo.class,
                A.creditCardID, A.retailerID, A.referenceID, A.cardTypeID, A.last4Digit, A.cvv, A.expirationMonth, A.expirationYear, A.nameOnCard, A.fingerprint, A.email,
                A.street, A.city, A.state, A.zipcode, A.country, A.countryID, A.isDefaultCard, A.cardStatusID, A.isDeleted, A.createdOn, A.createdBy, A.modifiedOn, A.modifiedBy,
                A.referenceID2, A.source, A.sourceDate, A.cardFunding, B.creditCardType.as("CreditCardTypeName"), C.cardStatusName))
            .from(A)
            .innerJoin(B).on(A.cardTypeID.eq(B.creditCardTypeID))
            .innerJoin(C).on(A.cardStatusID.eq(C.cardStatusID))
            .where(A.creditCardID.eq(creditCardID));

        return query.fetch();
    }
}
