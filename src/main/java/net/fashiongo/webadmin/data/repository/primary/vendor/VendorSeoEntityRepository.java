package net.fashiongo.webadmin.data.repository.primary.vendor;

import org.springframework.data.jpa.repository.JpaRepository;

import net.fashiongo.webadmin.data.entity.primary.VendorSeoEntity;

public interface VendorSeoEntityRepository extends JpaRepository<VendorSeoEntity, Integer>, VendorSeoEntityRepositoryCustom {

}
