package net.fashiongo.webadmin.dao.primary;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.AdVendor;

/**
 * @author Nayeon Kim
 */
public interface AdVendorRepository extends CrudRepository<AdVendor, Integer> {

	AdVendor findByAdID(Integer adID);

	AdVendor findTopBySpotID(Integer spotID);
	AdVendor findTopBySpotIDAndFromDateAndWholeSalerIDIsNull(Integer spotID, Date fromDate);

}
