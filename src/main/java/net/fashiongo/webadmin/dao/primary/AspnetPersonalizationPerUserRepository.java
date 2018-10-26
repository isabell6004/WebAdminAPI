package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.primary.AspnetPersonalizationPerUser;

public interface AspnetPersonalizationPerUserRepository extends CrudRepository<AspnetPersonalizationPerUser, Integer> {
	void deleteByUserIdIn(List<String> aspnetUserIdList);
}
