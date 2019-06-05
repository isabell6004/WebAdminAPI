package net.fashiongo.webadmin.model.photostudio.search.order.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderEntity;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderQueryBuilder;
import net.fashiongo.webadmin.model.photostudio.search.order.PhotoOrderSortInfoHolder;

import java.time.LocalDateTime;

public class CheckOutDateQueryBuilder extends PhotoOrderQueryBuilder<LocalDateTime> {

    public CheckOutDateQueryBuilder(PhotoOrderSortInfoHolder sortInfoHolder) {
        super(sortInfoHolder);
    }

    /*
    https://github.com/microsoft/mssql-jdbc/issues/680

    There is an issue in mssql-jdbc that TimeStamp is cast to DateTime2 NOT DateTime.
    Therefore, comparing (Java8)LocalDateTime with (MSSQL)DateTime is not accurate at millisecond level.
     */

    @Override
    public BooleanBuilder makePrevQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        JPQLQuery<LocalDateTime> checkOutDate = JPAExpressions.select(photoOrder._checkOutDate)
                .from(photoOrder)
                .where(photoOrder.orderID.eq(photoOrderEntity.getOrderID()));

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder._checkOutDate.eq(checkOutDate)
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._checkOutDate.gt(checkOutDate));
        } else {
            booleanBuilder
                    .and(photoOrder._checkOutDate.eq(checkOutDate)
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._checkOutDate.lt(checkOutDate));
        }

        return booleanBuilder;
    }

    @Override
    public BooleanBuilder makeNextQuery(PhotoOrderEntity photoOrderEntity) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        JPQLQuery<LocalDateTime> checkOutDate = JPAExpressions.select(photoOrder._checkOutDate)
                .from(photoOrder)
                .where(photoOrder.orderID.eq(photoOrderEntity.getOrderID()));

        if (getSortInfoHolder().getSortDirection().equals(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC)) {
            booleanBuilder
                    .and(photoOrder._checkOutDate.eq(checkOutDate)
                            .and(photoOrder.orderID.gt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._checkOutDate.lt(checkOutDate));
        } else {
            booleanBuilder
                    .and(photoOrder._checkOutDate.eq(checkOutDate)
                            .and(photoOrder.orderID.lt(photoOrderEntity.getOrderID())))
                    .or(photoOrder._checkOutDate.gt(checkOutDate));
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
