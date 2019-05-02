package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorContent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-04-16
 */
public interface VendorContentRepository extends CrudRepository<VendorContent, Integer> {
	List<VendorContent> findByWholeSalerIdAndStatusIdAndIsActiveAndIsDeleted(Integer wholeSalerId, int statusId, boolean isActive, boolean isDelete);
}
