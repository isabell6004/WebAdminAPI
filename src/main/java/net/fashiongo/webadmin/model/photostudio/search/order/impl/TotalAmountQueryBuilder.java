package net.fashiongo.webadmin.model.photostudio.search.order.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderEntity;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderQueryBuilder;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderSortInfoHolder;

import java.math.BigDecimal;

public class TotalAmountQueryBuilder extends PhotoOrderQueryBuilder<BigDecimal> {
    public TotalAmountQueryBuilder(PhotoOrderSortInfoHolder sortInfoHolder) {
        super(sortInfoHolder);
    }

    @Override
    public BooleanBuilder makePrevQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder.totalAmount.eq(photoOrderEntity.getTotalAmount())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.totalAmount.gt(photoOrderEntity.getTotalAmount()));
        } else {
            booleanBuilder
                    .and(photoOrder.totalAmount.eq(photoOrderEntity.getTotalAmount())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.totalAmount.lt(photoOrderEntity.getTotalAmount()));
        }

        return booleanBuilder;
    }

    @Override
    public BooleanBuilder makeNextQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder.totalAmount.eq(photoOrderEntity.getTotalAmount())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.totalAmount.lt(photoOrderEntity.getTotalAmount()));
        } else {
            booleanBuilder
                    .and(photoOrder.totalAmount.eq(photoOrderEntity.getTotalAmount())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.totalAmount.gt(photoOrderEntity.getTotalAmount()));
        }

        return booleanBuilder;
    }

    @Override
    public OrderSpecifier<BigDecimal> makeOrderBy(boolean reverse) {
        if ((getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.ASC) && !reverse)
                || (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC) && reverse)) {
            return photoOrder.totalAmount.asc();
        } else {
            return photoOrder.totalAmount.desc();
        }
    }
}
