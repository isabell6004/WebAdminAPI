package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.primary.RetailerCompany;

/**
 * 
 * @author Reo
 *
 */
public interface RetailerCompanyRepository extends CrudRepository<RetailerCompany, Integer> {
	List<RetailerCompany> findByActiveAndCompanyNameStartingWithOrderByCompanyName(String active, String companyName);
}
