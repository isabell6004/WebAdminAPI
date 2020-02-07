package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.data.entity.primary.MapUserGroupEntity;


public interface MapUserGroupRepository extends CrudRepository<MapUserGroupEntity, Integer>{
	MapUserGroupEntity findByUserIdAndGroupId(Integer userId, Integer groupId);
}
