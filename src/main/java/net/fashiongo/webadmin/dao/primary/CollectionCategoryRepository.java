/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.CollectionCategory;


/**
 * @author Sanghyup
 *
 */
public interface CollectionCategoryRepository extends CrudRepository<CollectionCategory, Integer>{
	CollectionCategory findOneByCollectionCategoryID(Integer collectionCategoryID);
}
