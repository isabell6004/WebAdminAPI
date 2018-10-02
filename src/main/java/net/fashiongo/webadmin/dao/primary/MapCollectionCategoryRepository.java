/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.MapCollectionCategory;

/**
 * @author Sanghyup
 *
 */
public interface MapCollectionCategoryRepository extends CrudRepository<MapCollectionCategory, Integer> {
	// findOneByMapID
	MapCollectionCategory findOneByMapID(Integer mapID);

	// findByCollectionCategoryID
	List<MapCollectionCategory> findByCollectionCategoryID(Integer collectionCategoryID);

	// deleteByMapID
	void deleteByMapID(Integer mapID);

	// deleteInBulkByCollectionCategoryID
	@Modifying
	@Query("delete from MapCollectionCategory mcc where collectionCategoryID = ?1")
	void deleteInBulkByCollectionCategoryID(Integer collectionCategoryID);

}
