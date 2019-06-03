package net.fashiongo.webadmin.model.photostudio.search.order;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderEntity;
import net.fashiongo.webadmin.model.photostudio.QPhotoOrderEntity;

public abstract class PhotoOrderQueryBuilder<T extends Comparable> {
    protected static QPhotoOrderEntity photoOrder = QPhotoOrderEntity.photoOrderEntity;
    private PhotoOrderSortInfoHolder sortInfoHolder;

    public PhotoOrderQueryBuilder(PhotoOrderSortInfoHolder sortInfoHolder) {
        this.sortInfoHolder = sortInfoHolder;
    }

    public abstract BooleanBuilder makePrevQuery(PhotoOrderEntity photoOrderEntity);

    public abstract BooleanBuilder makeNextQuery(PhotoOrderEntity photoOrderEntity);

    public OrderSpecifier<T> makeOrderBy() {
        return makeOrderBy(false);
    }

    public abstract OrderSpecifier<T> makeOrderBy(boolean reverse);

    protected PhotoOrderSortInfoHolder getSortInfoHolder() {
        return this.sortInfoHolder;
    }
}
