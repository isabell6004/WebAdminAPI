package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

import net.fashiongo.webadmin.model.primary.SecurityGroup;

public interface SecurityGroupRepository extends CrudRepository<SecurityGroup, Integer>{
	List<SecurityGroup> findAllByOrderByGroupNameAsc();
	boolean existsByGroupName(String groupName);
	boolean existsByGroupIDNotAndGroupName(Integer groupID, String groupName);
	SecurityGroup findOneByGroupID(Integer groupID);
	@Transactional
	void deleteByGroupIDIn(List<Integer> groupIDs);
}