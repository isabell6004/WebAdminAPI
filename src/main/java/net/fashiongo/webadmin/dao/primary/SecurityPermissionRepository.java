package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.primary.SecurityPermission;

public interface SecurityPermissionRepository extends CrudRepository<SecurityPermission, Integer>{
	@Transactional
	void deleteByUserIDAndApplicationID(Integer userID, Integer applicationID);
}
