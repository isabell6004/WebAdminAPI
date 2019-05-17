package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoUnit;

import java.time.LocalDateTime;
import java.util.List;

public interface PhotoUnitRepositoryCustom {
	List<PhotoUnit> findAllCurrentEffectiveUnit(Integer categoryId, Integer PackageId, boolean isFullModelShot);
	List<PhotoUnit> findAllCurrentEffectiveUnit(LocalDateTime now);
	List<PhotoUnit> findAllToBeEffectiveUnit(LocalDateTime now);
}
