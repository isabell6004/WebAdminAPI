package net.fashiongo.webadmin.model.photostudio.search.order;

import net.fashiongo.webadmin.model.photostudio.search.order.impl.*;

public class PhotoOrderQueryBuilderFactory {
    public static PhotoOrderQueryBuilder getBuilder(PhotoOrderSortInfoHolder sortInfoHolder) {
        switch (sortInfoHolder.getSortProperty()) {
            case PhotoshootDate:
                return new PhotoshootDateQueryBuilder(sortInfoHolder);
            case CheckOutDate:
                return new CheckOutDateQueryBuilder(sortInfoHolder);
            case PoNumber:
                return new PoNumberQueryBuilder(sortInfoHolder);
            case WholeSalerCompanyName:
                return new WholeSalerCompanyNameQueryBuilder(sortInfoHolder);
            case CategoryID:
                return new CategoryIdQueryBuilder(sortInfoHolder);
            case PackageID:
                return new PackageIdQueryBuilder(sortInfoHolder);
            case totalUnit:
                return new TotalUnitQueryBuilder(sortInfoHolder);
            case DropOffDate:
                return new DropOffDateQueryBuilder(sortInfoHolder);
            case TotalAmount:
                return new TotalAmountQueryBuilder(sortInfoHolder);
            case OrderStatusID:
                return new OrderStatusIdQueryBuilder(sortInfoHolder);
            default:
                return new PhotoshootDateQueryBuilder(sortInfoHolder);
        }
    }
}
