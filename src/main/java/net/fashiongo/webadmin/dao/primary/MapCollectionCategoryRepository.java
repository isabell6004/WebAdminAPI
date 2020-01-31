/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.model.primary.MapCollectionCategory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author Sanghyup
 *
 */
public interface MapCollectionCategoryRepository extends CrudRepository<MapCollectionCategory, Integer> {
	// findOneByMapID
	MapCollectionCategory findOneByMapID(Integer mapID);

	// findByCollectionCategoryID
	List<MapCollectionCategory> findByCollectionCategoryID(Integer collectionCategoryID);

	// findByCategoryID
	List<MapCollectionCategory> findByCategoryID(Integer categoryID);

	// deleteByMapID
	void deleteByMapID(Integer mapID);

	// deleteInBulkByCollectionCategoryID
	@Modifying
	@Query("delete from MapCollectionCategory mcc where collectionCategoryID = ?1")
	void deleteInBulkByCollectionCategoryID(Integer collectionCategoryID);

	// deleteByCollectionCategoryID
	void deleteByCollectionCategoryID(Integer collectionCategoryID);

	// deleteByCategoryID
	void deleteByCategoryID(Integer categoryID);

	List<MapCollectionCategory> findByCollectionCategoryIDIn(Collection<Integer> collectionCategoryIds);
}
