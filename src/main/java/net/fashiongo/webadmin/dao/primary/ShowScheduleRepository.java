/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.ShowSchedule;

/**
 * @author Sanghyup
 *
 */
public interface ShowScheduleRepository extends CrudRepository<ShowSchedule, Integer> {
	// find all
	List<ShowSchedule> findAll();

	// findOneByShowScheduleID
	ShowSchedule findOneByShowScheduleID(Integer ShowScheduleID);

	// findByShowID
	List<ShowSchedule> findByShowID(int showID);
	
	// deleteByShowID
	void deleteByShowID(int showID);
}
