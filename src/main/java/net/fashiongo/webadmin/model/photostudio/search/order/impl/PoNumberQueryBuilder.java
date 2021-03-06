package net.fashiongo.webadmin.model.photostudio.search.order.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderEntity;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderQueryBuilder;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderSortInfoHolder;

public class PoNumberQueryBuilder extends PhotoOrderQueryBuilder<String> {
    public PoNumberQueryBuilder(PhotoOrderSortInfoHolder sortInfoHolder) {
        super(sortInfoHolder);
    }

    @Override
    public BooleanBuilder makePrevQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder.poNumber.eq(photoOrderEntity.getPoNumber())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.poNumber.gt(photoOrderEntity.getPoNumber()));
        } else {
            booleanBuilder
                    .and(photoOrder.poNumber.eq(photoOrderEntity.getPoNumber())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.poNumber.lt(photoOrderEntity.getPoNumber()));
        }

        return booleanBuilder;
    }

    @Override
    public BooleanBuilder makeNextQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder.poNumber.eq(photoOrderEntity.getPoNumber())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.poNumber.lt(photoOrderEntity.getPoNumber()));
        } else {
            booleanBuilder
                    .and(photoOrder.poNumber.eq(photoOrderEntity.getPoNumber())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.poNumber.gt(photoOrderEntity.getPoNumber()));
        }

        return booleanBuilder;
    }

    @Override
    public OrderSpecifier<String> makeOrderBy(boolean reverse) {
        if ((getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.ASC) && !reverse)
                || (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC) && reverse)) {
            return photoOrder.poNumber.asc();
        } else {
            return photoOrder.poNumber.desc();
        }
    }
}
