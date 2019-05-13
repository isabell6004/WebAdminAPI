package net.fashiongo.webadmin.dao.primary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import net.fashiongo.webadmin.dao.primary.custom.VendorContentRepositoryCustom;
import net.fashiongo.webadmin.model.primary.VendorContent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-04-16
 */
@Repository
public interface VendorContentRepository extends VendorContentRepositoryCustom, JpaRepository<VendorContent, Integer>, JpaSpecificationExecutor<VendorContent>, QuerydslPredicateExecutor<VendorContent> {
	List<VendorContent> findByWholeSalerIdAndStatusIdAndTargetTypeIdAndIsActiveAndIsDeleted(Integer wholeSalerId, Integer statusId, Integer targetTypeId, Boolean isActive, Boolean isDelete);
}
