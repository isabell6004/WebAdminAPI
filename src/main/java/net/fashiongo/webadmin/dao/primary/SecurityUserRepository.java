package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityUser;

/**
 * @author Nayeon Kim
 */
public interface SecurityUserRepository extends CrudRepository<SecurityUser, Integer> {
	List<SecurityUser> findAllByOrderByActiveDescUserName();
	SecurityUser findFirstByUserName(String userName);
}
