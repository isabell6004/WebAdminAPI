package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AspnetMembership;

/**
 * 
 * @author Reo
 *
 */
public interface AspnetMembershipRepository extends CrudRepository<AspnetMembership, String> {
	AspnetMembership findOneByUserId(String userGUID);
}
