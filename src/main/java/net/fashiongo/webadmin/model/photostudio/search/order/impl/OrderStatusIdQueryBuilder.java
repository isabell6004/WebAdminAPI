package net.fashiongo.webadmin.model.photostudio.search.order.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderEntity;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderQueryBuilder;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderSortInfoHolder;

public class OrderStatusIdQueryBuilder extends PhotoOrderQueryBuilder<Integer> {

    public OrderStatusIdQueryBuilder(PhotoOrderSortInfoHolder sortInfoHolder) {
        super(sortInfoHolder);
    }

    @Override
    public BooleanBuilder makePrevQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder.orderStatusID.eq(photoOrderEntity.getOrderStatusID())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.orderStatusID.gt(photoOrderEntity.getOrderStatusID()));
        } else {
            booleanBuilder
                    .and(photoOrder.orderStatusID.eq(photoOrderEntity.getOrderStatusID())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.orderStatusID.lt(photoOrderEntity.getOrderStatusID()));
        }

        return booleanBuilder;
    }

    @Override
    public BooleanBuilder makeNextQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder.orderStatusID.eq(photoOrderEntity.getOrderStatusID())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.orderStatusID.lt(photoOrderEntity.getOrderStatusID()));
        } else {
            booleanBuilder
                    .and(photoOrder.orderStatusID.eq(photoOrderEntity.getOrderStatusID())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.orderStatusID.gt(photoOrderEntity.getOrderStatusID()));
        }

        return booleanBuilder;
    }

    @Override
    public OrderSpecifier<Integer> makeOrderBy(boolean reverse) {
        if ((getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.ASC) && !reverse)
                || (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC) && reverse)) {
            return photoOrder.orderStatusID.asc();
        } else {
            return photoOrder.orderStatusID.desc();
        }
    }
}
