package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.ListVendorImageType;

/**
 * 
 * @author Reo
 *
 */
public interface ListVendorImageTypeRepository extends CrudRepository<ListVendorImageType, Integer> {
	List<ListVendorImageType> findAllByOrderByVendorImageTypeID();
}
