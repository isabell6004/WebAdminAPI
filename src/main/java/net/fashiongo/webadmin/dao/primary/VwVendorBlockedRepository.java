package net.fashiongo.webadmin.dao.primary;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VwVendorBlocked;

/**
 * 
 * @author Reo
 *
 */
public interface VwVendorBlockedRepository extends CrudRepository<VwVendorBlocked, Integer> {
	List<VwVendorBlocked> findByBlockID(Integer searchKeyword);
	List<VwVendorBlocked> findByCompanyName(String searchKeyword);
	List<VwVendorBlocked> findByCompanyNameContainingIgnoreCase(String searchKeyword);
	List<VwVendorBlocked> findByBlockedOnBetween(LocalDateTime fromDate, LocalDateTime toDate);
	List<VwVendorBlocked> findByBlockReasonTitle(String searchKeyword);
}