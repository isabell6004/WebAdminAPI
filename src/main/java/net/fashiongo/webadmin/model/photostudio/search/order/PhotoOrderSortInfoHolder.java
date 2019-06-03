package net.fashiongo.webadmin.model.photostudio.search.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PhotoOrderSortInfoHolder {
    private PhotoOrderSortProperty sortProperty;

    private PhotoOrderSortDirection sortDirection;

    public enum PhotoOrderSortProperty {
        PhotoshootDate,
        CheckOutDate,
        PoNumber,
        WholeSalerCompanyName,
        CategoryID,
        PackageID,
        totalUnit,
        DropOffDate,
        TotalAmount,
        OrderStatusID
    }

    public enum PhotoOrderSortDirection {
        ASC,
        DESC
    }
}
