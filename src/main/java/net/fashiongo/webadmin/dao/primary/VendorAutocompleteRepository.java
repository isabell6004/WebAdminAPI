package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.fashiongo.webadmin.model.primary.VendorAutocomplete;

/**
 * @author Andy
 *
 */
public interface VendorAutocompleteRepository extends JpaRepository<VendorAutocomplete, Integer> {
	
	List<VendorAutocomplete> findByCompanyNameStartingWithOrEmailStartingWithAllIgnoreCase(String companyName, String email);

}
