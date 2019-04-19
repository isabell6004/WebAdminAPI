package net.fashiongo.webadmin.dao.primary;


import javax.transaction.Transactional;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityUser;

public interface SecurityUserRepository extends CrudRepository<SecurityUser, Integer> {
	SecurityUser findByUserID(Integer userID);
	List<SecurityUser> findByUserIDIn(List<Integer> delUserIDList);
	@Transactional
	void deleteByUserIDIn(Integer userID);
	List<SecurityUser> findAllByOrderByActiveDescUserName();
	SecurityUser findFirstByUserName(String userName);
	
	@Query(value = "select u.*\n" + 
			"from [security.User] u\n" + 
			"where exists (select 1 from Map_Wa_User_Vendor m where u.UserID=m.UserID)", nativeQuery = true)
	List<SecurityUser> findAllMappedByVendor();
}
