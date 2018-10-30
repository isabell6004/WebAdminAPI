package net.fashiongo.webadmin.dao.primary;


import javax.transaction.Transactional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityUser;

public interface SecurityUserRepository extends CrudRepository<SecurityUser, Integer> {
	SecurityUser findByUserID(Integer userID);
	@Transactional
	void deleteByUserIDIn(Integer userID);
	List<SecurityUser> findAllByOrderByActiveDescUserName();
	SecurityUser findFirstByUserName(String userName);
}
