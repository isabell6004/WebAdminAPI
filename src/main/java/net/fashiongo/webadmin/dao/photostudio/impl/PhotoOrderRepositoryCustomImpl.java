package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.dao.photostudio.PhotoOrderRepositoryCustom;
import net.fashiongo.webadmin.model.photostudio.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by jinwoo on 2019. 2. 12..
 */
@Repository
public class PhotoOrderRepositoryCustomImpl implements PhotoOrderRepositoryCustom {

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

        Expression<Long> styleQuantity = criteriaBuilder.sum(orderDetailJoin.get("styleQty"));
        Expression<Long> colorSetQuantity = criteriaBuilder.sum(orderDetailJoin.get("colorSetQty"));
        Expression<Long> colorQuantity = criteriaBuilder.sum(orderDetailJoin.get("colorQty"));
        Expression<Long> movieQuantity = criteriaBuilder.sum(orderDetailJoin.get("movieQty"));

        Expression<Long> baseColorSetQuantity = criteriaBuilder.sum(orderDetailJoin.get("baseColorSetQty"));
        Expression<Long> modelSwatchQuantity = criteriaBuilder.sum(orderDetailJoin.get("modelSwatchQty"));
        Expression<Long> movieClipQuantity = criteriaBuilder.sum(orderDetailJoin.get("movieClipQty"));
        Expression<Long> colorSwatchQuantity = criteriaBuilder.sum(orderDetailJoin.get("colorSwatchQty"));

        criteriaQuery.select(criteriaBuilder.tuple(categoryID.alias("categoryId"),
                styleQuantity.alias("styleQuantity"), colorSetQuantity.alias("colorSetQuantity"),
                colorQuantity.alias("colorQuantity"), movieQuantity.alias("movieQuantity"),
                baseColorSetQuantity.alias("baseColorSetQuantity"), modelSwatchQuantity.alias("modelSwatchQuantity"),
                movieClipQuantity.alias("movieClipQuantity"), colorSwatchQuantity.alias("colorSwatchQuantity") ));

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

    @Override
    public List<PhotoOrder> getValidOrderWithDetail(LocalDateTime start, LocalDateTime end) {

        QPhotoOrder photoOrder = QPhotoOrder.photoOrder;
        QPhotoOrderDetail photoOrderDetail = QPhotoOrderDetail.photoOrderDetail;

        JPAQuery<PhotoOrder> jpaQuery = new JPAQuery<>(photostudioEntityManager);
        List<PhotoOrder> orders = jpaQuery.from(photoOrder)
                .innerJoin(photoOrder.orderDetails, photoOrderDetail).fetchJoin()
                .where(photoOrder.isCancelledBy.isNull().and(photoOrder._checkOutDate.goe(start)).and(photoOrder._checkOutDate.lt(end)))
                .fetch()
                .stream()
                .distinct()
                .collect(Collectors.toList());

        return orders;
    }

    @Override
    public List<PhotoOrder> getCancelOrderWithDetail(LocalDateTime start, LocalDateTime end) {

        QPhotoOrder photoOrder = QPhotoOrder.photoOrder;
        QPhotoOrderDetail photoOrderDetail = QPhotoOrderDetail.photoOrderDetail;

        JPAQuery<PhotoOrder> jpaQuery = new JPAQuery<>(photostudioEntityManager);
        List<PhotoOrder> orders = jpaQuery.from(photoOrder)
                .innerJoin(photoOrder.orderDetails, photoOrderDetail).fetchJoin()
                .where(photoOrder.isCancelledBy.isNotNull().and(photoOrder._checkOutDate.goe(start)).and(photoOrder._checkOutDate.lt(end)))
                .fetch()
                .stream()
                .distinct()
                .collect(Collectors.toList());

        return orders;
    }

    @Override
    public List<PhotoOrder> getOrderWithDetail(LocalDateTime start, LocalDateTime end) {

        QPhotoOrder photoOrder = QPhotoOrder.photoOrder;
        QPhotoOrderDetail photoOrderDetail = QPhotoOrderDetail.photoOrderDetail;

        JPAQuery<PhotoOrder> jpaQuery = new JPAQuery<>(photostudioEntityManager);
        List<PhotoOrder> orders = jpaQuery.from(photoOrder)
                .innerJoin(photoOrder.orderDetails, photoOrderDetail).fetchJoin()
                .where(photoOrder._checkOutDate.goe(start).and(photoOrder._checkOutDate.lt(end)))
                .fetch()
                .stream()
                .distinct()
                .collect(Collectors.toList());

        return orders;
    }

    @Override
    public Map<Integer, List<PhotoOrder>> getOrderOfWholeSaler(List<Integer> wholeSalerIds) {

        QPhotoOrder photoOrder = QPhotoOrder.photoOrder;

        JPAQuery<PhotoOrder> jpaQuery = new JPAQuery<>(photostudioEntityManager);
        List<PhotoOrder> orders = jpaQuery.from(photoOrder)
                .where(photoOrder.wholeSalerID.in(wholeSalerIds)).fetch();

        if(!CollectionUtils.isEmpty(orders)) {
            return orders.stream().collect(Collectors.groupingBy(PhotoOrder::getWholeSalerID));
        }

        return new HashMap<>();
    }

    @Override
    public Map<Integer, PhotoOrder> getOrderOfCart(List<Integer> cartIds) {

        QPhotoOrder photoOrder = QPhotoOrder.photoOrder;

        JPAQuery<PhotoOrder> jpaQuery = new JPAQuery<>(photostudioEntityManager);
        List<PhotoOrder> orders = jpaQuery.from(photoOrder)
                .where(photoOrder.cartID.in(cartIds)).fetch();

        if(!CollectionUtils.isEmpty(orders)) {
            return orders.stream().collect(Collectors.toMap(PhotoOrder::getCartID, Function.identity()));
        }

        return new HashMap<>();
    }

    @Override
    public List<PhotoOrder> getOrderWithDetailByPhotoshootDate(LocalDateTime start, LocalDateTime end) {

        QPhotoOrder photoOrder = QPhotoOrder.photoOrder;
        QPhotoOrderDetail photoOrderDetail = QPhotoOrderDetail.photoOrderDetail;

        JPAQuery<PhotoOrder> jpaQuery = new JPAQuery<>(photostudioEntityManager);
        List<PhotoOrder> orders = jpaQuery.from(photoOrder)
                .innerJoin(photoOrder.orderDetails, photoOrderDetail).fetchJoin()
                .where(photoOrder._photoshootDate.goe(start).and(photoOrder._photoshootDate.lt(end)))
                .fetch()
                .stream()
                .distinct()
                .collect(Collectors.toList());

        return orders;
    }

    @Override
    public List<PhotoOrder> getOrdersByPhotoshootDate(LocalDateTime start, LocalDateTime end) {
        QPhotoOrder photoOrder = QPhotoOrder.photoOrder;

        JPAQuery<PhotoOrder> jpaQuery = new JPAQuery<>(photostudioEntityManager);
        List<PhotoOrder> orders = jpaQuery.from(photoOrder)
                .where(photoOrder._photoshootDate.goe(start).and(photoOrder._photoshootDate.lt(end)))
                .fetch()
                .stream()
                .distinct()
                .collect(Collectors.toList());
        return orders;
    }

    @Override
    public PhotoOrder getPhotoOrderInfoWithBookAndModelAndCategory(int orderId) {
        QPhotoOrder photoOrder = QPhotoOrder.photoOrder;
        QPhotoBooking photoBooking = QPhotoBooking.photoBooking;
        QPhotoCategory photoCategory = QPhotoCategory.photoCategory;
        QMapPhotoCalendarModel photoCalendarModel = QMapPhotoCalendarModel.mapPhotoCalendarModel;
        QPhotoModel photoModel = QPhotoModel.photoModel;

        JPAQuery<PhotoOrder> query = new JPAQuery<>(photostudioEntityManager)
                .select(photoOrder)
                .from(photoOrder)
                .join(photoOrder.photoBooking, photoBooking).fetchJoin()
                .join(photoOrder.photoCategory, photoCategory).fetchJoin()
                .leftJoin(photoBooking.mapPhotoCalendarModel, photoCalendarModel).fetchJoin()
                .leftJoin(photoCalendarModel.photoModel, photoModel).fetchJoin()
                .leftJoin(photoCalendarModel.photoBooking, photoBooking).fetchJoin()
                .where(photoOrder.orderID.eq(orderId));

        return query.fetchOne();
    }

    @Override
    public List<PhotoOrder> getValidOrderWithDetailByPhotoshootDate(LocalDateTime start, LocalDateTime end) {
        QPhotoOrder photoOrder = QPhotoOrder.photoOrder;
        QPhotoOrderDetail photoOrderDetail = QPhotoOrderDetail.photoOrderDetail;

        JPAQuery<PhotoOrder> jpaQuery = new JPAQuery<>(photostudioEntityManager);
        List<PhotoOrder> orders = jpaQuery.from(photoOrder)
                .innerJoin(photoOrder.orderDetails, photoOrderDetail).fetchJoin()
                .where(photoOrder.isCancelledBy.isNull().and(photoOrder._photoshootDate.goe(start)).and(photoOrder._photoshootDate.lt(end)))
                .fetch()
                .stream()
                .distinct()
                .collect(Collectors.toList());

        return orders;
    }
}
