package net.fashiongo.webadmin.dao.primary;

import javax.transaction.Transactional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityLoginControl;

public interface SecurityLoginControlRepository extends CrudRepository<SecurityLoginControl, Integer>{
	List<SecurityLoginControl> findByUserIDAndWeekday(Integer userID, Integer weekDay);
	SecurityLoginControl findOneByUserIDAndWeekday(Integer userID, Integer weekDay);
	@Transactional
	void deleteByUserIDIn(Integer userID);
}

