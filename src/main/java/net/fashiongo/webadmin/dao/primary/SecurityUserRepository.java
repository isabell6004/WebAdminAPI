package net.fashiongo.webadmin.dao.primary;


import javax.transaction.Transactional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityUser;

public interface SecurityUserRepository extends CrudRepository<SecurityUser, Integer> {
	SecurityUser findByUserID(Integer userID);
	List<SecurityUser> findByUserIDIn(List<Integer> delUserIDList);
	@Transactional
	void deleteByUserIDIn(Integer userID);
	void deleteByUserIDIn(List<Integer> userIDList);
	List<SecurityUser> findAllByOrderByActiveDescUserName();
	SecurityUser findFirstByUserName(String userName);
}
