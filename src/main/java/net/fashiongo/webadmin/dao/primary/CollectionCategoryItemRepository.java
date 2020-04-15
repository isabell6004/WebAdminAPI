package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.model.primary.CollectionCategoryItem;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jiwon Kim
 */
public interface CollectionCategoryItemRepository extends CrudRepository<CollectionCategoryItem, Integer> {

    List<CollectionCategoryItem> findByFromDateGreaterThanEqualAndFromDateLessThan(LocalDateTime beginFromDate, LocalDateTime endFromDate);

    List<CollectionCategoryItem> findByFromDateOrderByCollectionCategoryItemID(LocalDateTime fromDate);
}