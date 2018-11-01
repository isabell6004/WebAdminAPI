/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.MapAdVendorItem;

/**
 * @author Jiwon Kim
 *
 */
public interface MapAdVendorItemRepository extends CrudRepository<MapAdVendorItem, Integer> {
//	// find all
//	List<MapShowScheduleProduct> findAll();
//	
//	// findOneByMapID
//	MapShowScheduleProduct findOneByMapID(Integer mapID);
//	
	//findByAdid
	List<MapAdVendorItem> findByAdID(int adID);
//
//	// findByProductID
//	List<MapShowScheduleProduct> findByProductID(int productID);
//
//	// findByShowScheduleIDAndProductID
//	List<MapShowScheduleProduct> findByShowScheduleIDAndProductID(int showScheduleID, int productID);
//
//	// deleteByShowScheduleID
//	void deleteByShowScheduleID(int showScheduleID);
//	
//	// deleteByProductID
//	void deleteByProductID(int productID);
//	
//	// deleteByShowScheduleIDAndProductID
//	void deleteByShowScheduleIDAndProductID(int showScheduleID, int productID);
}
