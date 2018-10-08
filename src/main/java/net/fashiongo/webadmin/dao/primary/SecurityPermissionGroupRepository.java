package net.fashiongo.webadmin.dao.primary;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.SecurityPermissionGroup;

public interface SecurityPermissionGroupRepository extends CrudRepository<SecurityPermissionGroup, Integer>{
	@Transactional
	void deleteByGroupID(Integer groupID);
}