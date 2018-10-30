package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityResource;

/**
 * 
 * @author DAHYE
 *
 */
public interface SecurityResourceRepository extends CrudRepository<SecurityResource, Integer> {
	SecurityResource findOneByResourceID(Integer resourceID);
	void deleteByResourceIDIn(List<Integer> resourceID);
}
