package net.fashiongo.webadmin.dao.photostudio;

import java.util.List;

import net.fashiongo.webadmin.model.photostudio.MapPhotoImage;

import org.springframework.data.repository.CrudRepository;

public interface MapPhotoImageRepository extends CrudRepository<MapPhotoImage, Integer>{
	List<MapPhotoImage> findByMappingTypeAndReferenceID(Integer mappingType,Integer referenceID);
}
