package net.fashiongo.webadmin.dao.primary;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.VendorContent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-04-16
 */
public interface VendorContentRepository extends CrudRepository<VendorContent, Integer> {
}
