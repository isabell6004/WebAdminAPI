package net.fashiongo.webadmin.data.repository.primary.vendor;

import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface VendorImageRequestEntityRepository extends CrudRepository<VendorImageRequestEntity, Integer>, VendorImageRequestEntityRepositoryCustom {

    List<VendorImageRequestEntity> findByWholesalerIdInAndVendorImageTypeIdInAndIsApproved(Collection<Integer> wholesalerIds, Collection<Integer> vendorImageTypes, Boolean isApproved);
}
