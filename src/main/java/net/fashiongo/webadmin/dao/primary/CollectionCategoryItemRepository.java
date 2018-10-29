package net.fashiongo.webadmin.dao.primary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdPageSpot;

/**
 * @author Jiwon Kim
 */
public interface CollectionCategoryItemRepository extends CrudRepository<AdPageSpot, Integer> {
	
}