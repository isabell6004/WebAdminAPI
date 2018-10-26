package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.primary.AspnetMembership;

public interface AspnetMembershipRepository extends CrudRepository<AspnetMembership, Integer> {
	void deleteByUserIdIn(List<String> aspnetUserIdList);
}
