package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AspnetUsers;

public interface AspnetUsersRepository extends CrudRepository<AspnetUsers, Integer> {
	AspnetUsers findByLoweredUserName(String aspnetUserName);
	void deleteByUserIdIn(List<String> aspnetUserIdList);
}
