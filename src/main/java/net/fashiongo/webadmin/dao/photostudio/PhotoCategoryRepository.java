package net.fashiongo.webadmin.dao.photostudio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.fashiongo.webadmin.model.photostudio.PhotoCategory;

public interface PhotoCategoryRepository extends CrudRepository<PhotoCategory, Integer>{
	
	List<PhotoCategory> findAll();

}
