package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoImage;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoImageRepository extends CrudRepository<PhotoImage, Integer>{
	List<PhotoImage> findAllByImageIDIn(List<Integer> imageIds);
}
