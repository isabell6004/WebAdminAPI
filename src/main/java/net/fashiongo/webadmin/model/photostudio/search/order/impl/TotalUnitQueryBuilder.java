package net.fashiongo.webadmin.model.photostudio.search.order.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderEntity;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderQueryBuilder;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderSortInfoHolder;

import java.math.BigDecimal;

public class TotalUnitQueryBuilder extends PhotoOrderQueryBuilder<BigDecimal> {
    public TotalUnitQueryBuilder(PhotoOrderSortInfoHolder sortInfoHolder) {
        super(sortInfoHolder);
    }

    @Override
    public BooleanBuilder makePrevQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder.totalUnit.eq(photoOrderEntity.getTotalUnit())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.totalUnit.gt(photoOrderEntity.getTotalUnit()));
        } else {
            booleanBuilder
                    .and(photoOrder.totalUnit.eq(photoOrderEntity.getTotalUnit())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.totalUnit.lt(photoOrderEntity.getTotalUnit()));
        }

        return booleanBuilder;
    }

    @Override
    public BooleanBuilder makeNextQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder.totalUnit.eq(photoOrderEntity.getTotalUnit())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.totalUnit.lt(photoOrderEntity.getTotalUnit()));
        } else {
            booleanBuilder
                    .and(photoOrder.totalUnit.eq(photoOrderEntity.getTotalUnit())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.totalUnit.gt(photoOrderEntity.getTotalUnit()));
        }

        return booleanBuilder;
    }

    @Override
    public OrderSpecifier<BigDecimal> makeOrderBy(boolean reverse) {
        if ((getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.ASC) && !reverse)
                || (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC) && reverse)) {
            return photoOrder.totalUnit.asc();
        } else {
            return photoOrder.totalUnit.desc();
        }
    }
}
