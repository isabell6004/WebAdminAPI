package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityAccessCode;
import net.fashiongo.webadmin.model.primary.SecurityUser;

/**
 * 
 * @author JungHwan
 */
public interface SecurityAccessCodeRepository extends CrudRepository<SecurityAccessCode, Integer> {
	SecurityAccessCode findOneByCodeID(Integer codeID);
	SecurityAccessCode findFirstByAccessCode(String accessCode);
}
