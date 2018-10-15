/**
 * 
 */
package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.ShowSchedulePromotionPlan;

/**
 * @author Sanghyup
 *
 */
public interface ShowSchedulePromotionPlanRepository extends CrudRepository<ShowSchedulePromotionPlan, Integer> {
	// find all
	List<ShowSchedulePromotionPlan> findAll();
	
	// findByShowScheduleID
	ShowSchedulePromotionPlan findOneByPlanID(Integer planID);
	
	// findByShowScheduleID
	List<ShowSchedulePromotionPlan> findByShowScheduleID(int showScheduleID);
	
	// deleteByShowScheduleID
	void deleteByShowScheduleID(int showScheduleID);
}