package net.fashiongo.webadmin.model.photostudio.search.order.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderEntity;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderQueryBuilder;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderSortInfoHolder;

import java.time.LocalDateTime;

public class CheckOutDateQueryBuilder extends PhotoOrderQueryBuilder<LocalDateTime> {

    public CheckOutDateQueryBuilder(PhotoOrderSortInfoHolder sortInfoHolder) {
        super(sortInfoHolder);
    }

    @Override
    public BooleanBuilder makePrevQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder._checkOutDate.eq(photoOrderEntity.get_checkOutDate())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._checkOutDate.gt(photoOrderEntity.get_checkOutDate()));
        } else {
            booleanBuilder
                    .and(photoOrder._checkOutDate.eq(photoOrderEntity.get_checkOutDate())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._checkOutDate.lt(photoOrderEntity.get_checkOutDate()));
        }

        return booleanBuilder;
    }

    @Override
    public BooleanBuilder makeNextQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder._checkOutDate.eq(photoOrderEntity.get_checkOutDate())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._checkOutDate.lt(photoOrderEntity.get_checkOutDate()));
        } else {
            booleanBuilder
                    .and(photoOrder._checkOutDate.eq(photoOrderEntity.get_checkOutDate())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._checkOutDate.gt(photoOrderEntity.get_checkOutDate()));
        }

        return booleanBuilder;
    }

    @Override
    public OrderSpecifier<LocalDateTime> makeOrderBy(boolean reverse) {
        if ((getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.ASC) && !reverse)
                || (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC) && reverse)) {
            return photoOrder._checkOutDate.asc();
        } else {
            return photoOrder._checkOutDate.desc();
        }
    }
}
