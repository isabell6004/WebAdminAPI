package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.Policy;

public interface PolicyRepository extends CrudRepository<Policy, Integer> {
	
	//Page<Policy> findAll(PageRequest request);
}
