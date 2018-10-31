/**
 * 
 */
package net.fashiongo.webadmin.dao.primary.show;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.show.MapShowScheduleWholeSaler;

/**
 * @author Sanghyup
 *
 */
public interface MapShowScheduleWholeSalerRepository extends CrudRepository<MapShowScheduleWholeSaler, Integer> {
	// find all
	List<MapShowScheduleWholeSaler> findAll();

	// findOneByMapID
	MapShowScheduleWholeSaler findOneByMapID(Integer mapID);

	// findByShowScheduleID
	List<MapShowScheduleWholeSaler> findByShowScheduleID(int showScheduleID);

	// findByWholeSalerID
	List<MapShowScheduleWholeSaler> findByWholeSalerID(int wholeSalerID);

	// findByShowScheduleIDAndWholeSalerID
	List<MapShowScheduleWholeSaler> findByShowScheduleIDAndWholeSalerID(int showScheduleID, int wholeSalerID);

	// deleteByShowScheduleID
	void deleteByShowScheduleID(int showScheduleID);

	// deleteByWholeSalerID
	void deleteByWholeSalerID(int wholeSalerID);

	// deleteByShowScheduleIDAndWholeSalerID
	void deleteByShowScheduleIDAndWholeSalerID(int showScheduleID, int wholeSalerID);
}
