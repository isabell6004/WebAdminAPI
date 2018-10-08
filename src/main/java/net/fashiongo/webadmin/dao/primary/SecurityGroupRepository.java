package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityGroup;

public interface SecurityGroupRepository extends CrudRepository<SecurityGroup, Integer>{
	boolean existsByGroupName(String groupName);
	boolean existsByGroupIDAndGroupName(Integer groupID, String groupName);
	SecurityGroup findOneByGroupID(Integer groupID);
	void deleteByGroupIDIn(List<Integer> groupIDs);
}