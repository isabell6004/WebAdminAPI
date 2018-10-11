package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityLoginControl;

public interface SecurityLoginControlRepository extends CrudRepository<SecurityLoginControl, Integer>{
	List<SecurityLoginControl> findByUserIDAndWeekDay(Integer userID, Integer weekDay);
}