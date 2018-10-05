/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

//	@Procedure("up_wa_SetCollectionCategoryInactive")
//	@Procedure(procedureName = "up_wa_SetCollectionCategoryInactive")
	@Procedure(name = "CollectionCategory.upWaSetCollectionCategoryInactive")
	void upWaSetCollectionCategoryInactive(@Param("CollectionCategoryID") Integer collectionCategoryID);
}
