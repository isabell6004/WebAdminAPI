/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.CollectionCategory;

/**
 * @author Sanghyup
 *
 */
public interface CollectionCategoryRepository extends CrudRepository<CollectionCategory, Integer> {
	// findOneByCollectionCategoryID
	CollectionCategory findOneByCollectionCategoryID(Integer collectionCategoryID);

	// deleteByCollectionCategoryID
	void deleteByCollectionCategoryID(Integer collectionCategoryID);

	// deleteByCollectionCategoryIDOrParentCollectionCategoryID
	void deleteByCollectionCategoryIDOrParentCollectionCategoryID(Integer collectionCategoryID,
			Integer collectionCategoryID2);

}
