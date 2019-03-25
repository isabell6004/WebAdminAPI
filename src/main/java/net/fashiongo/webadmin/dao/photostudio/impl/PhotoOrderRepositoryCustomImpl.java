package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.dao.photostudio.PhotoOrderStatisticCustom;
import net.fashiongo.webadmin.model.photostudio.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

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

    @Override
    public PhotoOrder getPhotoOrderInfoWithAdditionalInfo(String poNumber) {

        QPhotoOrder photoOrder = QPhotoOrder.photoOrder;
        QPhotoCategory photoCategory = QPhotoCategory.photoCategory;
        QPhotoPackage photoPackage = QPhotoPackage.photoPackage;
        QCodePhotoBackgroundColor codePhotoBackgroundColor = QCodePhotoBackgroundColor.codePhotoBackgroundColor;
        QPhotoBooking photoBooking = QPhotoBooking.photoBooking;
        QPhotoDiscount photoDiscount = QPhotoDiscount.photoDiscount;
        QMapPhotoCalendarModel mapPhotoCalendarModel = QMapPhotoCalendarModel.mapPhotoCalendarModel;
        QPhotoModel photoModel = QPhotoModel.photoModel;

        JPAQuery<PhotoOrder> jpaQuery = new JPAQuery<>(photostudioEntityManager);
        PhotoOrder order = jpaQuery.from(photoOrder)
                .innerJoin(photoOrder.photoCategory, photoCategory).fetchJoin()
                .innerJoin(photoOrder.photoBooking, photoBooking).fetchJoin()
                .leftJoin(photoOrder.photoPackage, photoPackage).fetchJoin()
                .leftJoin(photoOrder.codePhotoBackgroundColor, codePhotoBackgroundColor).fetchJoin()
                .leftJoin(photoBooking.mapPhotoCalendarModel, mapPhotoCalendarModel).fetchJoin()
                .leftJoin(mapPhotoCalendarModel.photoModel, photoModel).fetchJoin()
                .leftJoin(photoOrder.photoDiscount, photoDiscount).fetchJoin()
                .where(photoOrder.poNumber.eq(poNumber))
                .fetchOne();

        return Optional.ofNullable(order).orElse(new PhotoOrder());
    }
}
