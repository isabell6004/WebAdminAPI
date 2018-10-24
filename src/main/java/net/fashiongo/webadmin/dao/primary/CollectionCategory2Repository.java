/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.CollectionCategory2;

/**
 * @author Sanghyup
 *
 */
public interface CollectionCategory2Repository extends CrudRepository<CollectionCategory2, Integer> {
	// findOneByCollectionCategoryID
	CollectionCategory2 findOneByCollectionCategoryID(Integer collectionCategoryID);

	// findByParentCollectionCategoryIDAndLvlAndCollectionCategoryIDNotOrderByListOrderAsc
	List<CollectionCategory2> findByParentCollectionCategoryIDAndLvlAndCollectionCategoryIDNotOrderByListOrderAsc(
			Integer parentCollectionCategoryID, Integer lvl, Integer collectionCategoryID);

	// findByParentCollectionCategoryIDAndLvlOrderByListOrderAsc
	List<CollectionCategory2> findByParentCollectionCategoryIDAndLvlOrderByListOrderAsc(
			Integer parentCollectionCategoryID, Integer lvl);
}
