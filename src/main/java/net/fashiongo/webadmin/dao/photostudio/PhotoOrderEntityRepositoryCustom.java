package net.fashiongo.webadmin.dao.photostudio;

import com.querydsl.core.QueryResults;
import net.fashiongo.webadmin.model.photostudio.OrderQueryParam;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderEntity;

import java.util.List;
import java.util.Optional;

public interface PhotoOrderEntityRepositoryCustom {
    List<PhotoOrderEntity> getValidOrderWithModelByCalendarIdAndModelId(Integer calendarId, Integer modelId);

    QueryResults<Integer> getPhotoOrderIdList(OrderQueryParam queryParam);

    List<PhotoOrderEntity> getPhotoOrderListFromIdList(List<Integer> orderIdList, String orderBy);

    Optional<String> getPrevOrderNumber(PhotoOrderEntity poNumber, OrderQueryParam queryParam);

    Optional<String> getNextOrderNumber(PhotoOrderEntity poNumber, OrderQueryParam queryParam);
}
