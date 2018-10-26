package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.primary.AspnetUserInRoles;

public interface AspnetUsersInRolesRepository extends CrudRepository<AspnetUserInRoles, Integer> {
	void deleteByUserIdIn(List<String> aspnetUserIdList);
}
