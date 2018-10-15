package net.fashiongo.webadmin.dao.primary;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityLoginControl;

public interface SecurityLoginControlRepository extends CrudRepository<SecurityLoginControl, Integer> {
	@Transactional
	void deleteByUserIDIn(Integer userID);
}
