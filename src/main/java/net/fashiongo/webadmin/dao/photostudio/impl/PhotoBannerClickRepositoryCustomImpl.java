package net.fashiongo.webadmin.dao.photostudio.impl;

import net.fashiongo.webadmin.dao.photostudio.PhotoBannerClickStatisticCustom;
import net.fashiongo.webadmin.model.photostudio.PhotoBannerClick;
import net.fashiongo.webadmin.model.photostudio.PhotoBannerClickStatistic;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jinwoo on 2019. 2. 6..
 */
@Repository
public class PhotoBannerClickRepositoryCustomImpl implements PhotoBannerClickStatisticCustom {

    @PersistenceContext(unitName = "photostudioEntityManager")
    private EntityManager photostudioEntityManager;


    @Override
    public List<PhotoBannerClickStatistic> getClickStatistic(Date start, Date end) {

        CriteriaBuilder criteriaBuilder = photostudioEntityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<PhotoBannerClick> root = criteriaQuery.from(PhotoBannerClick.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("clickedOn"), start));
        predicates.add(criteriaBuilder.lessThan(root.<Date>get("clickedOn"), end));

        Expression<String> wholeSalerId = root.get("wholeSalerId");
        Expression<String> wholeSalerCompanyName = root.get("wholeSalerCompanyName");
        Expression<String> bannerType = root.get("bannerType");
        Expression<Long> count = criteriaBuilder.count(root.get("id"));

        criteriaQuery.select(criteriaBuilder.tuple(wholeSalerId.alias("wholeSalerId"),
                wholeSalerCompanyName.alias("wholeSalerCompanyName"), bannerType.alias("bannerType"), count.alias("count")));

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.groupBy(wholeSalerId, wholeSalerCompanyName, bannerType);

        List<Tuple> results = photostudioEntityManager.createQuery(criteriaQuery).getResultList();

        return PhotoBannerClickStatistic.build(results);

    }

}
