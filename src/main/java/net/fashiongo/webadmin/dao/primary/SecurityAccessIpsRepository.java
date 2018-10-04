package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.pojo.SecurityAccessIps;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessIpParameter;
/**
 * @author Dahye Jeong
 */
public interface SecurityAccessIpsRepository extends CrudRepository<SecurityAccessIps, Integer> {
	SecurityAccessIps findFirstByIPID(Integer ipid);
	
	SecurityAccessIps save(SetSecurityAccessIpParameter accessIp);
	void deleteByipid(Integer ipid);
}
