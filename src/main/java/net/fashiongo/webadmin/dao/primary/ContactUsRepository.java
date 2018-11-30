package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.ContactUs;

/**
 * 
 * @author Reo
 *
 */
public interface ContactUsRepository extends CrudRepository<ContactUs, Integer> {
	ContactUs findOneByContactID(Integer contactID);
}
