package net.fashiongo.webadmin.dao.photostudio.impl;

import net.fashiongo.webadmin.dao.photostudio.PhotoOrderStatisticCustom;
import net.fashiongo.webadmin.model.photostudio.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jinwoo on 2019. 2. 12..
 */
@Repository
public class PhotoOrderRepositoryCustomImpl implements PhotoOrderStatisticCustom {

    @PersistenceContext(unitName = "photostudioEntityManager")
    private EntityManager photostudioEntityManager;

    @Override
    public Map<Integer, OrderStatistic> getValidOrderStatistic(Date start, Date end) {

        CriteriaBuilder criteriaBuilder = photostudioEntityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<PhotoOrder> root = criteriaQuery.from(PhotoOrder.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<LocalDateTime>get("_checkOutDate"),
                start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        predicates.add(criteriaBuilder.lessThan(root.<LocalDateTime>get("_checkOutDate"),
                end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        predicates.add(criteriaBuilder.isNull(root.<Integer>get("cancelTypeID")));

        Expression<Integer> categoryID = root.get("categoryID");
        Expression<Long> totalOrderNumber = criteriaBuilder.count(root.get("orderID"));
        Expression<BigDecimal> totalAmounts = criteriaBuilder.sum(root.get("totalAmount"));

        criteriaQuery.select(criteriaBuilder.tuple(categoryID.alias("categoryId"),
                totalOrderNumber.alias("totalOrderNumber"), totalAmounts.alias("totalAmounts")));

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.groupBy(categoryID);

        List<Tuple> results = photostudioEntityManager.createQuery(criteriaQuery).getResultList();

        return OrderStatistic.build(results);
    }

    @Override
    public Map<Integer, OrderStatistic> getCancelOrderStatistic(Date start, Date end) {

        CriteriaBuilder criteriaBuilder = photostudioEntityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<PhotoOrder> root = criteriaQuery.from(PhotoOrder.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<LocalDateTime>get("_checkOutDate"),
                start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        predicates.add(criteriaBuilder.lessThan(root.<LocalDateTime>get("_checkOutDate"),
                end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        predicates.add(criteriaBuilder.isNotNull(root.<Integer>get("cancelTypeID")));

        Expression<Integer> categoryID = root.get("categoryID");
        Expression<Long> totalOrderNumber = criteriaBuilder.count(root.get("orderID"));
        Expression<BigDecimal> totalAmounts = criteriaBuilder.sum(root.get("totalAmount"));

        criteriaQuery.select(criteriaBuilder.tuple(categoryID.alias("categoryId"),
                totalOrderNumber.alias("totalOrderNumber"), totalAmounts.alias("totalAmounts")));

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.groupBy(categoryID);

        List<Tuple> results = photostudioEntityManager.createQuery(criteriaQuery).getResultList();

        return OrderStatistic.build(results);
    }

    @Override
    public Map<Integer, OrderDetailStatistic> getValidOrderDetailStatistic(Date start, Date end) {

        CriteriaBuilder criteriaBuilder = photostudioEntityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<PhotoOrder> root = criteriaQuery.from(PhotoOrder.class);
        Join<PhotoOrder, PhotoOrderDetail> orderDetailJoin = root.join("orderDetails", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.<LocalDateTime>get("_checkOutDate"),
                start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        predicates.add(criteriaBuilder.lessThan(root.<LocalDateTime>get("_checkOutDate"),
                end.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        predicates.add(criteriaBuilder.isNull(root.<Integer>get("cancelTypeID")));

        Expression<Integer> categoryID = root.get("categoryID");
        Expression<Long> styleQuentity = criteriaBuilder.sum(orderDetailJoin.get("styleQty"));
        Expression<Long> colorSetQuentity = criteriaBuilder.sum(orderDetailJoin.get("colorSetQty"));
        Expression<Long> colorQuentity = criteriaBuilder.sum(orderDetailJoin.get("colorQty"));
        Expression<Long> movieQuentity = criteriaBuilder.sum(orderDetailJoin.get("movieQty"));

        criteriaQuery.select(criteriaBuilder.tuple(categoryID.alias("categoryId"),
                styleQuentity.alias("styleQuentity"), colorSetQuentity.alias("colorSetQuentity"),
                colorQuentity.alias("colorQuentity"), movieQuentity.alias("movieQuentity")));

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.groupBy(categoryID);

        List<Tuple> results = photostudioEntityManager.createQuery(criteriaQuery).getResultList();

        return OrderDetailStatistic.build(results);


    }
}
