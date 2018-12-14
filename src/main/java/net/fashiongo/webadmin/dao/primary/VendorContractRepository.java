package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorContract;

/**
 * 
 * @author Reo
 *
 */
public interface VendorContractRepository extends CrudRepository<VendorContract, Integer> {
	List<VendorContract> findByWholeSalerIDOrderByVendorContractIDDesc(Integer wholeSalerID);
}
