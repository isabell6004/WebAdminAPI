package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityGroup;
import net.fashiongo.webadmin.model.primary.SecurityMapUserGroup;
import net.fashiongo.webadmin.model.primary.SecurityPermissionGroup;

public interface SecurityMapUserGroupRepository extends CrudRepository<SecurityMapUserGroup, Integer>{
	List<SecurityMapUserGroup> findByGroupIDIn(List<Integer> groupID);
	SecurityMapUserGroup mapUserGroup(Integer usrId);
	SecurityMapUserGroup findByUserIDAndGroupID(Integer userID, Integer groupID);
	@Transactional
	void deleteByUserIDIn(Integer userID);
	void deleteByUserIDIn(List<Integer> userIDList);
}