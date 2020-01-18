package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.dao.primary.custom.GnbMenuCollectionRepositoryCustom;
import net.fashiongo.webadmin.dao.primary.custom.GnbVendorGroupRepositoryCustom;
import net.fashiongo.webadmin.data.entity.primary.GnbMenuCollectionEntity;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupMapEntity;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupMapId;

import org.springframework.data.jpa.repository.JpaRepository;


public interface GnbMenuCollectionRepository extends JpaRepository<GnbMenuCollectionEntity, Integer>, GnbMenuCollectionRepositoryCustom {

}
