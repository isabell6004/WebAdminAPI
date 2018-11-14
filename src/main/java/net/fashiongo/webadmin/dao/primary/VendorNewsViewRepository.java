package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;
import net.fashiongo.webadmin.model.primary.VendorNewsView;

/**
 * 
 * @author DAHYE
 *
 */
public interface VendorNewsViewRepository extends CrudRepository<VendorNewsView, Integer> {
	VendorNewsView findOneByNewsID(Integer NewsID);

}


