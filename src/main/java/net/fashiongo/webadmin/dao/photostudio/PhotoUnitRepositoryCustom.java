package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoUnit;

import java.util.List;

public interface PhotoUnitRepositoryCustom {
	List<PhotoUnit> findAllEffectiveUnit(Integer categoryId, Integer PackageId, boolean isFullModelShot);
}
