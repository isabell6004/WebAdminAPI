/**
 * 
 */
package net.fashiongo.webadmin.dao.primary.show;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.show.MapShowSchedulePromotionPlanVendor;

/**
 * @author Sanghyup
 *
 */
public interface MapShowSchedulePromotionPlanVendorRepository extends CrudRepository<MapShowSchedulePromotionPlanVendor, Integer> {
	// find all
	List<MapShowSchedulePromotionPlanVendor> findAll();
	
	// findOneByMapID
	MapShowSchedulePromotionPlanVendor findOneByMapID(Integer mapID);
	
	// findByPlanID
	List<MapShowSchedulePromotionPlanVendor> findByPlanID(int planID);
	
	// findByWholeSalerID
	List<MapShowSchedulePromotionPlanVendor> findByWholeSalerID(int wholeSalerID);
	
	// findByPlanIDAndWholeSalerID
	List<MapShowSchedulePromotionPlanVendor> findByPlanIDAndWholeSalerID(int planID, int wholeSalerID);
	
	// deleteByPlanID
	void deleteByPlanID(int planID);
	
	// deleteByWholeSalerID
	void deleteByWholeSalerID(int wholeSalerID);
	
	// deleteByPlanIDAndWholeSalerID
	void deleteByPlanIDAndWholeSalerID(int planID, int wholeSalerID);
	
}
