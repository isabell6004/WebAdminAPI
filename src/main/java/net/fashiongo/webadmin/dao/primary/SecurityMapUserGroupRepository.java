package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityMapUserGroup;
import net.fashiongo.webadmin.model.primary.SecurityPermissionGroup;

public interface SecurityMapUserGroupRepository extends CrudRepository<SecurityMapUserGroup, Integer>{
	List<SecurityMapUserGroup> findByGroupIDIn(List<Integer> groupID);
}