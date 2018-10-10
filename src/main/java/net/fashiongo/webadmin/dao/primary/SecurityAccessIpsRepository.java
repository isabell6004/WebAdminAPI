package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessIpParameter;
import net.fashiongo.webadmin.model.primary.SecurityAccessIp;;

/**
 * 
 * @author Dahye Jeong
 */

public interface SecurityAccessIpsRepository extends CrudRepository<SecurityAccessIp, Integer> {
	SecurityAccessIp findFirstByipid(Integer ipid);
	SecurityAccessIp save(SetSecurityAccessIpParameter accessIp);
	void deleteByipid(Integer ipid);
	
}