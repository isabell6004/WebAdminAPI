package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.MapWaUserVendor;

public interface MapWaUserVendorRepository extends CrudRepository<MapWaUserVendor, Integer> {
	Integer countByUserID(Integer userID);
}
