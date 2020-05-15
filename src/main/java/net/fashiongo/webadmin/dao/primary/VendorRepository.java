package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.dao.primary.custom.VendorRepositoryCustom;
import net.fashiongo.webadmin.model.primary.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author roy
 */
@Repository
public interface VendorRepository extends VendorRepositoryCustom, JpaRepository<Vendor, Integer>, JpaSpecificationExecutor<Vendor>, QuerydslPredicateExecutor<Vendor> {
    List<Vendor> findAllByActiveTrueAndShopActiveTrueOrderByCompanyName();

    Optional<Vendor> findByWholeSalerIdAndActive(int wid, boolean active);
}
