package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorCategory;

/**
 * 
 * @author Incheol Jung
 */
public interface VendorCategoryRepository extends CrudRepository<VendorCategory, Integer> {
	List<VendorCategory> findByWholeSalerIDAndActiveTrue(Integer wholeSalerID);
}