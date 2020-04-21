package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import net.fashiongo.webadmin.dao.primary.custom.VendorRepositoryCustom;
import net.fashiongo.webadmin.model.primary.Vendor;

/**
 * @author roy
 */
@Repository
public interface VendorRepository extends VendorRepositoryCustom, JpaRepository<Vendor, Integer>, JpaSpecificationExecutor<Vendor>, QuerydslPredicateExecutor<Vendor> {
	List<Vendor> findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();
	Vendor findCompanyNameByWholeSalerId(Integer wholeSalerId);
}
