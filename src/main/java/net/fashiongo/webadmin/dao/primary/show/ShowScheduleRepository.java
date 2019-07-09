/**
 * 
 */
package net.fashiongo.webadmin.dao.primary.show;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.show.ShowSchedule;

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
	
	List<ShowSchedule> findByActive(boolean active);
	
	// deleteByShowID
	void deleteByShowID(int showID);

	List<ShowSchedule> findByShowIDInAndActive(List<Integer> showIds, boolean active);
}
