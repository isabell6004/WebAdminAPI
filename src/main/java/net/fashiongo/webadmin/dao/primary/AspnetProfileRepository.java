package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.primary.AspnetProfile;

public interface AspnetProfileRepository extends CrudRepository<AspnetProfile, Integer> {
	void deleteByUserIdIn(List<String> aspnetUserIdList);
}
