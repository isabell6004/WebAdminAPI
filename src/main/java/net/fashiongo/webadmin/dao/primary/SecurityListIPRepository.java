package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityListIP;

/**
 * 
 * @author Incheol Jung
 */
public interface SecurityListIPRepository extends CrudRepository<SecurityListIP, Integer>{
	boolean existsByIpAddress(String ipAddress);
}