package net.fashiongo.webadmin.dao.photostudio.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.jpa.impl.JPAQuery;
import net.fashiongo.webadmin.dao.photostudio.PhotoOrderEntityRepositoryCustom;
import net.fashiongo.webadmin.model.photostudio.*;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderQueryBuilder;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderQueryBuilderFactory;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderSortInfoHolder;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderSortInfoParser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PhotoOrderEntityRepositoryCustomImpl implements PhotoOrderEntityRepositoryCustom {
    @PersistenceContext(unitName = "photostudioEntityManager")
    private EntityManager photostudioEntityManager;

    @Override
    public List<PhotoOrderEntity> getValidOrderWithModelByCalendarIdAndModelId(Integer calendarId, Integer modelId) {

        QPhotoOrderEntity photoOrder = QPhotoOrderEntity.photoOrderEntity;
        QPhotoOrderDetail photoOrderDetail = QPhotoOrderDetail.photoOrderDetail;
        QPhotoBooking photoBooking = QPhotoBooking.photoBooking;
        QMapPhotoCalendarModel mapPhotoCalendarModel = QMapPhotoCalendarModel.mapPhotoCalendarModel;
        QPhotoPackage photoPackage = QPhotoPackage.photoPackage;
        QPhotoModel photoModel = QPhotoModel.photoModel;

        return new JPAQuery<>(photostudioEntityManager)
                .select(photoOrder).distinct()
                .from(photoOrder)
                .innerJoin(photoOrder.orderDetails, photoOrderDetail).fetchJoin()
                .innerJoin(photoOrder.photoBooking, photoBooking).fetchJoin()
                .leftJoin(photoOrder.photoPackage, photoPackage).fetchJoin()
                .innerJoin(photoBooking.mapPhotoCalendarModel, mapPhotoCalendarModel).fetchJoin()
                .leftJoin(mapPhotoCalendarModel.photoModel, photoModel).fetchJoin()
                .where(mapPhotoCalendarModel.calendarID.eq(calendarId)
                        .and(Optional.ofNullable(modelId).map(photoModel.modelID::eq).orElse(null))
                        .and(photoOrder.isCancelledBy.isNull()))
                .orderBy(photoOrder.categoryID.asc())
                .fetch();
    }

    @Override
    public QueryResults<PhotoOrderEntity> getPhotoOrderList(OrderQueryParam queryParam) {
        QPhotoOrderEntity photoOrder = QPhotoOrderEntity.photoOrderEntity;
        QPhotoCategory photoCategory = QPhotoCategory.photoCategory;
        QPhotoPackage photoPackage = QPhotoPackage.photoPackage;

        long pageNumber = Optional.ofNullable(queryParam.getPn())
                .orElse(1);
        long pageSize = Optional.ofNullable(queryParam.getPs())
                .orElse(20);

        long limit = pageSize;
        long offset = (pageNumber - 1) * pageSize;

        PhotoOrderSortInfoHolder sortInfoHolder = PhotoOrderSortInfoParser.parseOrderBy(queryParam.getOrderBy());
        PhotoOrderQueryBuilder<?> queryBuilder = PhotoOrderQueryBuilderFactory.getBuilder(sortInfoHolder);

        JPAQuery<PhotoOrderEntity> query = new JPAQuery<>(photostudioEntityManager)
                .select(photoOrder)
                .from(photoOrder)
                .innerJoin(photoOrder.photoCategory, photoCategory).fetchJoin()
                .innerJoin(photoOrder.photoPackage, photoPackage).fetchJoin()
                .where(makeQuery(queryParam))
                .orderBy(queryBuilder.makeOrderBy(), defaultSortOrder(sortInfoHolder, false))
                .offset(offset)
                .limit(limit);


        return query.fetchResults();
    }

    @Override
    public Optional<String> getPrevOrderNumber(PhotoOrderEntity photoOrderEntity, OrderQueryParam queryParam) {
        QPhotoOrderEntity photoOrder = QPhotoOrderEntity.photoOrderEntity;

        PhotoOrderSortInfoHolder sortInfoHolder = PhotoOrderSortInfoParser.parseOrderBy(queryParam.getOrderBy());
        PhotoOrderQueryBuilder<?> queryBuilder = PhotoOrderQueryBuilderFactory.getBuilder(sortInfoHolder);

        JPAQuery<String> query = new JPAQuery<>(photostudioEntityManager)
                .select(photoOrder.poNumber)
                .from(photoOrder)
                .where(makeQuery(queryParam)
                        .and(queryBuilder.makePrevQuery(photoOrderEntity)))
                .orderBy(queryBuilder.makeOrderBy(true), defaultSortOrder(sortInfoHolder, true));

        return Optional.ofNullable(query.fetchFirst());
    }

    @Override
    public Optional<String> getNextOrderNumber(PhotoOrderEntity photoOrderEntity, OrderQueryParam queryParam) {
        QPhotoOrderEntity photoOrder = QPhotoOrderEntity.photoOrderEntity;

        PhotoOrderSortInfoHolder sortInfoHolder = PhotoOrderSortInfoParser.parseOrderBy(queryParam.getOrderBy());
        PhotoOrderQueryBuilder<?> queryBuilder = PhotoOrderQueryBuilderFactory.getBuilder(sortInfoHolder);

        JPAQuery<String> query = new JPAQuery<>(photostudioEntityManager)
                .select(photoOrder.poNumber)
                .from(photoOrder)
                .where(makeQuery(queryParam)
                        .and(queryBuilder.makeNextQuery(photoOrderEntity)))
                .orderBy(queryBuilder.makeOrderBy(), defaultSortOrder(sortInfoHolder, false));

        return Optional.ofNullable(query.fetchFirst());
    }

    private BooleanBuilder makeQuery(OrderQueryParam param) {
        QPhotoOrderEntity photoOrder = QPhotoOrderEntity.photoOrderEntity;

        BooleanBuilder queryBuilder = new BooleanBuilder();

        Integer queryType = param.getQt();
        if (queryType != null) {
            String queryString = param.getQ();
            if (queryType == 0) {
                queryBuilder.and(photoOrder.poNumber.contains(queryString)
                        .or(photoOrder.wholeSalerCompanyName.contains(queryString)));
            } else if (queryType == 1) {
                queryBuilder.and(photoOrder.poNumber.contains(queryString));
            } else if (queryType == 2) {
                queryBuilder.and(photoOrder.wholeSalerCompanyName.contains(queryString));
            }
        }

        String dateType = param.getDtype();
        if (dateType != null) {
            LocalDateTime dateFrom = param.getDf();
            LocalDateTime dateTo = Optional.ofNullable(param.getDt())
                    .map(dt -> dt.minusDays(1))
                    .orElse(null);

            if (dateType.equals("PhotoshootDate")) {
                if (dateFrom != null) {
                    queryBuilder.and(photoOrder._photoshootDate.goe(dateFrom));
                }

                if (dateTo != null) {
                    queryBuilder.and(photoOrder._photoshootDate.loe(dateTo));
                }
            } else if (dateType.equals("OrderDate")) {
                if (dateFrom != null) {
                    queryBuilder.and(photoOrder._checkOutDate.goe(dateFrom));
                }

                if (dateTo != null) {
                    queryBuilder.and(photoOrder._checkOutDate.loe(dateTo));
                }
            } else if (dateType.equals("DropOffDate")) {
                if (dateFrom != null) {
                    queryBuilder.and(photoOrder._dropOffDate.goe(dateFrom));
                }

                if (dateTo != null) {
                    queryBuilder.and(photoOrder._dropOffDate.loe(dateTo));
                }
            }
        }

        List<Integer> categoryIds = param.getCatids();
        List<Integer> packageIds = param.getPackids();
        if (categoryIds != null || packageIds != null) {
            BooleanBuilder tempBuilder = new BooleanBuilder();

            if (categoryIds != null) {
                tempBuilder.or(photoOrder.categoryID.in(categoryIds));
            }

            if (packageIds != null) {
                tempBuilder.or(photoOrder.packageID.in(packageIds));
            }

            queryBuilder.and(tempBuilder);
        }

        List<Integer> orderStatusIds = param.getOstsids();
        if (orderStatusIds != null) {
            queryBuilder.and(photoOrder.orderStatusID.in(orderStatusIds));
        }

        LocalDateTime now = LocalDateTime.now();
        Boolean onTime = param.getOnTime();
        if (onTime != null && onTime) {
            DateTimeExpression<LocalDateTime> datetimeExpression = new CaseBuilder()
                    .when(photoOrder.orderStatusID.eq(1)).then(photoOrder._dropOffDate)
                    .when(photoOrder.orderStatusID.eq(2)).then(photoOrder._prepDate)
                    .when(photoOrder.orderStatusID.eq(3)).then(photoOrder._photoshootDate)
                    .when(photoOrder.orderStatusID.eq(4)).then(photoOrder._retouchDate)
                    .when(photoOrder.orderStatusID.eq(5)).then(photoOrder._uploadDate)
                    .when(photoOrder.orderStatusID.eq(6)).then(now)
                    .otherwise(now);

            queryBuilder.and(datetimeExpression.goe(now));
        }

        Boolean isDelayed = param.getIsDelayed();
        if (isDelayed != null && isDelayed) {
            DateTimeExpression<LocalDateTime> datetimeExpression = new CaseBuilder()
                    .when(photoOrder.orderStatusID.eq(1)).then(photoOrder._dropOffDate)
                    .when(photoOrder.orderStatusID.eq(2)).then(photoOrder._prepDate)
                    .when(photoOrder.orderStatusID.eq(3)).then(photoOrder._photoshootDate)
                    .when(photoOrder.orderStatusID.eq(4)).then(photoOrder._retouchDate)
                    .when(photoOrder.orderStatusID.eq(5)).then(photoOrder._uploadDate)
                    .otherwise(now.minusSeconds(1));

            queryBuilder.and(datetimeExpression.lt(now));
        }

        Boolean notCancelled = param.getNotCancelled();
        Boolean cancelledByFG = param.getCancelledByFG();
        Boolean cancelledByVendor = param.getCancelledByVendor();
        if (notCancelled != null || cancelledByFG != null || cancelledByVendor != null) {
            BooleanBuilder isCancelledBy = new BooleanBuilder();

            if (notCancelled != null) {
                isCancelledBy.or(photoOrder.isCancelledBy.isNull()
                        .or(photoOrder.isCancelledBy.eq(0)));
            }

            if (cancelledByVendor != null) {
                isCancelledBy.or(photoOrder.isCancelledBy.eq(1));
            }

            if (cancelledByFG != null) {
                isCancelledBy.or(photoOrder.isCancelledBy.eq(2));
            }

            queryBuilder.and(isCancelledBy);
        }

        return queryBuilder;
    }

    private OrderSpecifier<Integer> defaultSortOrder(PhotoOrderSortInfoHolder sortInfoHolder, boolean reverse) {
        QPhotoOrderEntity photoOrder = QPhotoOrderEntity.photoOrderEntity;

        if ((sortInfoHolder.getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.ASC) && !reverse)
                || (sortInfoHolder.getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC) && reverse)) {
            return photoOrder.orderID.desc();
        } else {
            return photoOrder.orderID.asc();
        }
    }
}
