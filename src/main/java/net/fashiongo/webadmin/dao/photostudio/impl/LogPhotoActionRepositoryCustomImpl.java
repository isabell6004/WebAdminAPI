package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.dao.photostudio.LogPhotoActionCustom;
import net.fashiongo.webadmin.model.photostudio.LogPhotoAction;
import net.fashiongo.webadmin.model.photostudio.QLogPhotoAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by jinwoo on 2019. 3. 4..
 */
@Slf4j
public class LogPhotoActionRepositoryCustomImpl implements LogPhotoActionCustom {

    @PersistenceContext(unitName = "photostudioEntityManager")
    private EntityManager entityManager;

    @Override
    public List<LogPhotoAction> getLogPhotoActions(Integer orderID) {

        QLogPhotoAction logPhotoAction = QLogPhotoAction.logPhotoAction;

        JPAQuery<LogPhotoAction> jpaQuery = new JPAQuery<>(entityManager);
        List<LogPhotoAction> logPhotoActions = jpaQuery.from(logPhotoAction)
                .where(logPhotoAction.orderID.eq(orderID))
                .orderBy(logPhotoAction.actionType.desc(), logPhotoAction.createdOnDate.desc())
                .fetch();

        return Optional.ofNullable(logPhotoActions).orElse(new ArrayList<>());
    }
}
