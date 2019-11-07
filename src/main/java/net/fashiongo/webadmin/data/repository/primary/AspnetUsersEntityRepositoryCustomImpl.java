package net.fashiongo.webadmin.data.repository.primary;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.data.entity.primary.AspnetUsersEntity;
import net.fashiongo.webadmin.data.entity.primary.QAspnetUsersEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AspnetUsersEntityRepositoryCustomImpl implements AspnetUsersEntityRepositoryCustom {
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager entityManager;

    @Override
    public AspnetUsersEntity findOneByUserNameAndWholeSalerGUID(String userID, String wholeSalerGUID) {
        QAspnetUsersEntity USER = QAspnetUsersEntity.aspnetUsersEntity;

        JPAQuery<AspnetUsersEntity> query = new JPAQuery<>(entityManager);

        query.select(USER).from(USER).where(USER.loweredUserName.eq(userID.toLowerCase()).and(USER.userId.ne(wholeSalerGUID)));

        return query.fetchFirst();
    }

    @Override
    public AspnetUsersEntity findOneByWholeSalerGUID(String wholeSalerGUID) {
        QAspnetUsersEntity USER = QAspnetUsersEntity.aspnetUsersEntity;

        JPAQuery<AspnetUsersEntity> query = new JPAQuery<>(entityManager);

        query.select(USER).from(USER).where(USER.userId.eq(wholeSalerGUID));

        return query.fetchFirst();
    }
}
