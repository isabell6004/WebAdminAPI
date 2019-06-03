package net.fashiongo.webadmin.model.photostudio.search.order.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderEntity;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderQueryBuilder;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderSortInfoHolder;

import java.time.LocalDateTime;

public class DropOffDateQueryBuilder extends PhotoOrderQueryBuilder<LocalDateTime> {

    public DropOffDateQueryBuilder(PhotoOrderSortInfoHolder sortInfoHolder) {
        super(sortInfoHolder);
    }

    @Override
    public BooleanBuilder makePrevQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder._dropOffDate.eq(photoOrderEntity.get_dropOffDate())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._dropOffDate.gt(photoOrderEntity.get_dropOffDate()));
        } else {
            booleanBuilder
                    .and(photoOrder._dropOffDate.eq(photoOrderEntity.get_dropOffDate())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._dropOffDate.lt(photoOrderEntity.get_dropOffDate()));
        }

        return booleanBuilder;
    }

    @Override
    public BooleanBuilder makeNextQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder._dropOffDate.eq(photoOrderEntity.get_dropOffDate())
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._dropOffDate.lt(photoOrderEntity.get_dropOffDate()));
        } else {
            booleanBuilder
                    .and(photoOrder._dropOffDate.eq(photoOrderEntity.get_dropOffDate())
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._dropOffDate.gt(photoOrderEntity.get_dropOffDate()));
        }

        return booleanBuilder;
    }

    @Override
    public OrderSpecifier<LocalDateTime> makeOrderBy(boolean reverse) {
        if ((getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.ASC) && !reverse)
                || (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC) && reverse)) {
            return photoOrder._dropOffDate.asc();
        } else {
            return photoOrder._dropOffDate.desc();
        }
    }
}
