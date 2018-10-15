package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorCompany;

/**
 * @author roy
 */
public interface VendorListRepository extends CrudRepository<VendorCompany, Integer> {
	List<VendorCompany> findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();
}
