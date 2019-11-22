package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.CustomerSocialMediaEntity;
import net.fashiongo.webadmin.data.entity.primary.QCustomerSocialMediaEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerSocialMediaEntityRepositoryCustomImpl implements CustomerSocialMediaEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public CustomerSocialMediaEntity findOneByMapID(Integer mapID) {
        QCustomerSocialMediaEntity CSM = QCustomerSocialMediaEntity.customerSocialMediaEntity;

        JPAQuery<CustomerSocialMediaEntity> query = new JPAQuery<>(entityManager);

        query.select(CSM).from(CSM).where(CSM.mapID.eq(mapID));

        return query.fetchFirst();
    }
}
