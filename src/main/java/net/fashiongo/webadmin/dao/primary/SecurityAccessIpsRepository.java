package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.pojo.admin.parameter.SetSecurityAccessIpParameter;
import net.fashiongo.webadmin.model.primary.SecurityAccessIp;;

/**
 * 
 * @author Dahye Jeong
 */

public interface SecurityAccessIpsRepository extends CrudRepository<SecurityAccessIp, Integer> {
	SecurityAccessIp findFirstByipid(Integer ipid);
	SecurityAccessIp save(SetSecurityAccessIpParameter accessIp);
	void deleteByipidIn(List<Integer> idList);
	
}
