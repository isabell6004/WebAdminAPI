package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoModelRepository extends CrudRepository<PhotoModel, Integer> {
    PhotoModel findOneByModelID(Integer modelID);

    List<PhotoModel> findAllByIsDeletedOrderByModelNameAsc(Boolean isDeleted);
}
