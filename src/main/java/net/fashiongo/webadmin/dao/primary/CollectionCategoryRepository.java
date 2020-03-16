/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.model.primary.CollectionCategory;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author Sanghyup
 *
 */
public interface CollectionCategoryRepository extends CrudRepository<CollectionCategory, Integer> {
	// findOneByCollectionCategoryID
	CollectionCategory findOneByCollectionCategoryID(Integer collectionCategoryID);

	// findOneByParentCollectionCategoryID
	List<CollectionCategory> findByParentCollectionCategoryID(Integer parentCollectionCategoryID);

	// findBySpotID
	List<CollectionCategory> findBySpotID(Integer spotID);

	// findByActive
	List<CollectionCategory> findByActive(boolean active);

	// deleteByCollectionCategoryID
	void deleteByCollectionCategoryID(Integer collectionCategoryID);

	// deleteByCollectionCategoryIDOrParentCollectionCategoryID
	void deleteByCollectionCategoryIDOrParentCollectionCategoryID(Integer collectionCategoryID,
			Integer collectionCategoryID2);

//	@Procedure("up_wa_SetCollectionCategoryInactive")
//	@Procedure(procedureName = "up_wa_SetCollectionCategoryInactive")
	@Procedure(name = "CollectionCategory.upWaSetCollectionCategoryInactive")
	void upWaSetCollectionCategoryInactive(@Param("CollectionCategoryID") Integer collectionCategoryID);

	// deleteByParentCollectionCategoryID
	void deleteByParentCollectionCategoryID(Integer parentCollectionCategoryID);

	// deleteBySpotID
	void deleteBySpotID(Integer spotID);

	// deleteByActive
	void deleteByActive(boolean active);

	List<CollectionCategory> findByCollectionCategoryIDIn(Collection<Integer> collectionCategoryIds);

	List<CollectionCategory> findAllBy();
}
