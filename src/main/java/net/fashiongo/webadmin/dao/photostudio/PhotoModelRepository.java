package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoModel;

import org.springframework.data.repository.CrudRepository;

public interface PhotoModelRepository extends CrudRepository<PhotoModel, Integer>{
	PhotoModel findOneByModelID(Integer modelID);
}
