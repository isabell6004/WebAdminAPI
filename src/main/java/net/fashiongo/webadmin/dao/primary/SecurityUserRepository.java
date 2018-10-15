package net.fashiongo.webadmin.dao.primary;

<<<<<<< HEAD
import javax.transaction.Transactional;
=======
import java.util.List;
>>>>>>> 658996c405700cd75c35b1377529378bda2781c1

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityUser;

<<<<<<< HEAD
public interface SecurityUserRepository extends CrudRepository<SecurityUser, Integer> {
	SecurityUser findByUserID(Integer userID);
	@Transactional
	void deleteByUserIDIn(Integer userID);
=======
/**
 * @author Nayeon Kim
 */
public interface SecurityUserRepository extends CrudRepository<SecurityUser, Integer> {
	List<SecurityUser> findAllByOrderByActiveDescUserName();
	SecurityUser findFirstByUserName(String userName);
>>>>>>> 658996c405700cd75c35b1377529378bda2781c1
}
