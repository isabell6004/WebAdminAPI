package net.fashiongo.webadmin.service.ads;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.dao.primary.CollectionCategoryItemRepository;
import net.fashiongo.webadmin.dao.primary.CollectionCategoryRepository;
import net.fashiongo.webadmin.dao.primary.MapCollectionCategoryRepository;
import net.fashiongo.webadmin.model.ads.response.*;
import net.fashiongo.webadmin.model.primary.CollectionCategoryItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdCollectionCategoryItemServiceImpl implements AdCollectionCategoryItemService {

    private CollectionCategoryRepository collectionCategoryRepository;
    private CollectionCategoryItemRepository collectionCategoryItemRepository;
    private MapCollectionCategoryRepository mapCollectionCategoryRepository;

    public AdCollectionCategoryItemServiceImpl(CollectionCategoryRepository collectionCategoryRepository,
                                               CollectionCategoryItemRepository collectionCategoryItemRepository,
                                               MapCollectionCategoryRepository mapCollectionCategoryRepository) {

        this.collectionCategoryRepository = collectionCategoryRepository;
        this.collectionCategoryItemRepository = collectionCategoryItemRepository;
        this.mapCollectionCategoryRepository = mapCollectionCategoryRepository;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public CollectionCategoryItemCountsByDateResponse getCountsByDates(LocalDateTime startDisplayOn, LocalDateTime endDisplayOn) {

        /*
         * average item counts per day : 260
         * list size : 260 x 30 = 7800
         */
        List<CollectionCategoryItem> collectionCategoryItems = collectionCategoryItemRepository.findByFromDateGreaterThanEqualAndFromDateLessThan(
                startDisplayOn, endDisplayOn.plusDays(1)
        );

        Map<LocalDateTime, Map<Integer, Long>> countMap = collectionCategoryItems.stream()
                .collect(
                        Collectors.groupingBy(
                                CollectionCategoryItem::getFromDate,
                                Collectors.groupingBy(
                                        CollectionCategoryItem::getCollectionCategoryID,
                                        Collectors.counting()
                                )
                        )
                );

        List<AdCollectionCategoryCountResponse> collectionCategoryCounts = new ArrayList<>();

        countMap.forEach((displayDate, collectionCategoryCount) ->
                collectionCategoryCount.forEach((collectionCategoryId, count) ->
                        collectionCategoryCounts.add(
                                AdCollectionCategoryCountResponse.of(displayDate, collectionCategoryId, count)
                        ))
        );

        return CollectionCategoryItemCountsByDateResponse.of(
                collectionCategoryCounts,
                getCollectionCategoryResponses()
        );
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public CollectionCategoryItemByDateResponse getItemsByDate(LocalDateTime displayOn) {

        List<CollectionCategoryItem> collectionCategoryItems = collectionCategoryItemRepository.findByFromDateOrderByCollectionCategoryItemID(displayOn);

        List<AdCollectionCategoryResponse> collectionCategoryResponses = getCollectionCategoryResponses();

        List<Integer> collectionCategoryIds = collectionCategoryResponses.stream()
                .map(AdCollectionCategoryResponse::getCollectionCategoryId)
                .collect(Collectors.toList());

        List<AdCollectionCategoryItemResponse> collectionCategoryItemResponses = collectionCategoryItems.stream()
                .map(AdCollectionCategoryItemResponse::from)
                .collect(Collectors.toList());

        List<AdCollectionCategoryMapResponse> collectionCategoryMaps = mapCollectionCategoryRepository
                .findByCollectionCategoryIDIn(collectionCategoryIds)
                .stream()
                .map(AdCollectionCategoryMapResponse::from)
                .collect(Collectors.toList());

        return CollectionCategoryItemByDateResponse.of(
                collectionCategoryItemResponses,
                getCollectionCategoryResponses(),
                collectionCategoryMaps
        );
    }

    private List<AdCollectionCategoryResponse> getCollectionCategoryResponses() {

        return collectionCategoryRepository.findAllBy()
                .stream()
                .map(AdCollectionCategoryResponse::from)
                .collect(Collectors.toList());
    }
}
