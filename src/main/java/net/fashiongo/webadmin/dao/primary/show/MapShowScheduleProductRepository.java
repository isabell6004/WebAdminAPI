/**
 * 
 */
package net.fashiongo.webadmin.dao.primary.show;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.show.MapShowScheduleProduct;

/**
 * @author Sanghyup
 *
 */
public interface MapShowScheduleProductRepository extends CrudRepository<MapShowScheduleProduct, Integer> {
	// find all
	List<MapShowScheduleProduct> findAll();
	
	// findOneByMapID
	MapShowScheduleProduct findOneByMapID(Integer mapID);
	
	// findByShowScheduleID
	List<MapShowScheduleProduct> findByShowScheduleID(int showScheduleID);

	// findByProductID
	List<MapShowScheduleProduct> findByProductID(int productID);

	// findByShowScheduleIDAndProductID
	List<MapShowScheduleProduct> findByShowScheduleIDAndProductID(int showScheduleID, int productID);

	// deleteByShowScheduleID
	void deleteByShowScheduleID(int showScheduleID);
	
	// deleteByProductID
	void deleteByProductID(int productID);
	
	// deleteByShowScheduleIDAndProductID
	void deleteByShowScheduleIDAndProductID(int showScheduleID, int productID);
}
