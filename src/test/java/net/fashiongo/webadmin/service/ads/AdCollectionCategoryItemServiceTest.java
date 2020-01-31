package net.fashiongo.webadmin.service.ads;


import net.fashiongo.webadmin.dao.primary.CollectionCategoryItemRepository;
import net.fashiongo.webadmin.dao.primary.CollectionCategoryRepository;
import net.fashiongo.webadmin.dao.primary.MapCollectionCategoryRepository;
import net.fashiongo.webadmin.model.ads.response.CollectionCategoryItemByDateResponse;
import net.fashiongo.webadmin.model.ads.response.CollectionCategoryItemCountsByDateResponse;
import net.fashiongo.webadmin.model.primary.CollectionCategory;
import net.fashiongo.webadmin.model.primary.CollectionCategoryItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

public class AdCollectionCategoryItemServiceTest {

    private final LocalDateTime beginDisplayOn = LocalDate.now().atStartOfDay();
    private final LocalDateTime endDisplayOn = beginDisplayOn.plusDays(6);
    private AdCollectionCategoryItemService collectionCategoryItemService;
    private CollectionCategoryRepository collectionCategoryRepository = mock(CollectionCategoryRepository.class);
    private CollectionCategoryItemRepository collectionCategoryItemRepository = mock(CollectionCategoryItemRepository.class);
    private MapCollectionCategoryRepository mapCollectionCategoryRepository = mock(MapCollectionCategoryRepository.class);
    private List<CollectionCategoryItem> collectionCategoryItems;
    private List<CollectionCategory> collectionCategories;

    @Before
    public void setup() {
        this.collectionCategoryItemService = new AdCollectionCategoryItemServiceImpl(
                collectionCategoryRepository, collectionCategoryItemRepository, mapCollectionCategoryRepository
        );

        this.collectionCategoryItems = generateCollectionCategoryItemList();
        this.collectionCategories = generateCollectionCategoryList();
    }

    @Test
    public void get_counts_by_dates_success_case() {

        // given
        when(collectionCategoryItemRepository.findByFromDateGreaterThanEqualAndFromDateLessThan(beginDisplayOn, endDisplayOn.plusDays(1)))
                .thenReturn(collectionCategoryItems);

        when(collectionCategoryRepository.findByCollectionCategoryIDIn(any()))
                .thenReturn(collectionCategories);

        // when
        CollectionCategoryItemCountsByDateResponse response = collectionCategoryItemService.getCountsByDates(beginDisplayOn, endDisplayOn);

        // then
        Assert.assertNotNull(response);
        Assert.assertEquals(7, response.getCollectionCategoryCounts().size());
        Assert.assertTrue(response.getCollectionCategoryCounts().stream().allMatch(res -> res.getCounts() == 10));
        Assert.assertEquals(collectionCategories.size(), response.getCollectionCategories().size());
    }

    @Test
    public void get_items_by_date_success_case() {

        // given
        when(collectionCategoryItemRepository.findByFromDateOrderByCollectionCategoryItemID(beginDisplayOn))
                .thenReturn(
                        collectionCategoryItems.stream()
                                .filter(item -> item.getFromDate().equals(beginDisplayOn))
                                .collect(Collectors.toList())
                );

        when(collectionCategoryRepository.findByCollectionCategoryIDIn(any()))
                .thenReturn(collectionCategories);

        // when
        CollectionCategoryItemByDateResponse response = collectionCategoryItemService.getItemsByDate(beginDisplayOn);

        // then
        Assert.assertNotNull(response);
        Assert.assertEquals(10, response.getCollectionCategoryItems().size());
    }

    private List<CollectionCategoryItem> generateCollectionCategoryItemList() {

        List<CollectionCategoryItem> categoryItems = new ArrayList<>();

        int collectionCategoryId = 1;
        for (LocalDateTime date = beginDisplayOn; date.isBefore(endDisplayOn.plusDays(1)); date = date.plusDays(1)) {

            for (int i = 1; i <= 10; i++) {
                CollectionCategoryItem collectionCategoryItem = new CollectionCategoryItem();

                collectionCategoryItem.setCollectionCategoryID(collectionCategoryId);
                collectionCategoryItem.setFromDate(date);
                collectionCategoryItem.setProductID(i);

                categoryItems.add(collectionCategoryItem);
            }
            collectionCategoryId++;
        }

        return categoryItems;
    }

    private List<CollectionCategory> generateCollectionCategoryList() {

        return collectionCategoryItems.stream()
                .map(CollectionCategoryItem::getCollectionCategoryID)
                .collect(Collectors.toSet())
                .stream()
                .map(id -> {
                    CollectionCategory collectionCategory = new CollectionCategory();
                    collectionCategory.setCollectionCategoryID(id);
                    collectionCategory.setCollectionCategoryName("CategoryName#" + id);
                    collectionCategory.setParentCollectionCategoryID(0);
                    collectionCategory.setSpotID(id * 10);
                    return collectionCategory;
                })
                .collect(Collectors.toList());
    }
}
