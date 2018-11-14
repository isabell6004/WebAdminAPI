package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;
import net.fashiongo.webadmin.model.primary.VendorNewsDetail;
/**
 * 
 * @author DAHYE
 *
 */
public interface VendorNewsDetailRepository extends CrudRepository<VendorNewsDetail, Integer> {
	VendorNewsDetail findOneByNewsID(Integer NewsID);
}
