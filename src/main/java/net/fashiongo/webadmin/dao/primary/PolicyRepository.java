package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.Policy;
/**
 * 
 * @author DAHYE
 *
 */
public interface PolicyRepository extends CrudRepository<Policy, Integer> {
	Policy findOneByPolicyID(Integer policyID);
	Page<Policy> findAll(Pageable request);
}
