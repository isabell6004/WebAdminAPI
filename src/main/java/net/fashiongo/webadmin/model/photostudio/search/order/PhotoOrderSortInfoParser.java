package net.fashiongo.webadmin.model.photostudio.search.order;

public class PhotoOrderSortInfoParser {
    public static PhotoOrderSortInfoHolder parseOrderBy(String orderBy) {
        if (orderBy == null || orderBy.equals("")) {
            return PhotoOrderSortInfoHolder.builder()
                    .sortProperty(PhotoOrderSortInfoHolder.PhotoOrderSortProperty.PhotoshootDate)
                    .sortDirection(PhotoOrderSortInfoHolder.PhotoOrderSortDirection.ASC)
                    .build();
        }

        String[] splitedOrderBy = orderBy.split(" ");
        PhotoOrderSortInfoHolder.PhotoOrderSortDirection sortDirection;
        if (splitedOrderBy.length == 1) {
            sortDirection = PhotoOrderSortInfoHolder.PhotoOrderSortDirection.ASC;
        } else if (splitedOrderBy.length == 2 && splitedOrderBy[1].equals("desc")) {
            sortDirection = PhotoOrderSortInfoHolder.PhotoOrderSortDirection.DESC;
        } else {
            sortDirection = PhotoOrderSortInfoHolder.PhotoOrderSortDirection.ASC;
        }

        PhotoOrderSortInfoHolder.PhotoOrderSortProperty sortProperty = PhotoOrderSortInfoHolder.PhotoOrderSortProperty.PhotoshootDate;
        switch (splitedOrderBy[0]) {
            case "PhotoshootDate":
                sortProperty = PhotoOrderSortInfoHolder.PhotoOrderSortProperty.PhotoshootDate;
                break;
            case "CheckOutDate":
                sortProperty = PhotoOrderSortInfoHolder.PhotoOrderSortProperty.CheckOutDate;
                break;
            case "PoNumber":
                sortProperty = PhotoOrderSortInfoHolder.PhotoOrderSortProperty.PoNumber;
                break;
            case "WholeSalerCompanyName":
                sortProperty = PhotoOrderSortInfoHolder.PhotoOrderSortProperty.WholeSalerCompanyName;
                break;
            case "CategoryID":
                sortProperty = PhotoOrderSortInfoHolder.PhotoOrderSortProperty.CategoryID;
                break;
            case "PackageID":
                sortProperty = PhotoOrderSortInfoHolder.PhotoOrderSortProperty.PackageID;
                break;
            case "totalUnit":
                sortProperty = PhotoOrderSortInfoHolder.PhotoOrderSortProperty.totalUnit;
                break;
            case "DropOffDate":
                sortProperty = PhotoOrderSortInfoHolder.PhotoOrderSortProperty.DropOffDate;
                break;
            case "TotalAmount":
                sortProperty = PhotoOrderSortInfoHolder.PhotoOrderSortProperty.TotalAmount;
                break;
            case "OrderStatusID":
                sortProperty = PhotoOrderSortInfoHolder.PhotoOrderSortProperty.OrderStatusID;
                break;
        }

        return PhotoOrderSortInfoHolder.builder()
                .sortProperty(sortProperty)
                .sortDirection(sortDirection)
                .build();
    }
}
