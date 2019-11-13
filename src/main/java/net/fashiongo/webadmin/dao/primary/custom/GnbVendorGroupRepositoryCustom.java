package net.fashiongo.webadmin.dao.primary.custom;

import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupEntity;

import java.util.List;

public interface GnbVendorGroupRepositoryCustom {
	List<Integer> findIdListByWholeSalerIdAndTitle(Integer wholeSalerId, String title);
	List<GnbVendorGroupEntity> findAllByIdListWithGnbVendorGroupMap(List<Integer> idList);
}
