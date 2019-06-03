package net.fashiongo.webadmin.model.photostudio.search.order.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderEntity;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderQueryBuilder;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderSortInfoHolder;

public class WholeSalerCompanyNameQueryBuilder extends PhotoOrderQueryBuilder<String> {
    public WholeSalerCompanyNameQueryBuilder(PhotoOrderSortInfoHolder sortInfoHolder) {
        super(sortInfoHolder);
    }

    @Override
    public BooleanBuilder makePrevQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder.wholeSalerCompanyName.eq(photoOrderEntity.getWholeSalerCompanyName())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.wholeSalerCompanyName.gt(photoOrderEntity.getWholeSalerCompanyName()));
        } else {
            booleanBuilder
                    .and(photoOrder.wholeSalerCompanyName.eq(photoOrderEntity.getWholeSalerCompanyName())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.wholeSalerCompanyName.lt(photoOrderEntity.getWholeSalerCompanyName()));
        }

        return booleanBuilder;
    }

    @Override
    public BooleanBuilder makeNextQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder.wholeSalerCompanyName.eq(photoOrderEntity.getWholeSalerCompanyName())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.wholeSalerCompanyName.lt(photoOrderEntity.getWholeSalerCompanyName()));
        } else {
            booleanBuilder
                    .and(photoOrder.wholeSalerCompanyName.eq(photoOrderEntity.getWholeSalerCompanyName())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder.wholeSalerCompanyName.gt(photoOrderEntity.getWholeSalerCompanyName()));
        }

        return booleanBuilder;
    }

    @Override
    public OrderSpecifier<String> makeOrderBy(boolean reverse) {
        if ((getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.ASC) && !reverse)
                || (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC) && reverse)) {
            return photoOrder.wholeSalerCompanyName.asc();
        } else {
            return photoOrder.wholeSalerCompanyName.desc();
        }
    }
}
