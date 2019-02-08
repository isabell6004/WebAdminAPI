package net.fashiongo.webadmin.dao.primary;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.FashiongoForm;

/**
 * 
 * @author Reo
 *
 */
public interface FashiongoFormRepository extends CrudRepository<FashiongoForm, Integer> {
	FashiongoForm findOneByFashionGoFormID(Integer fashiongoFormID);
}
