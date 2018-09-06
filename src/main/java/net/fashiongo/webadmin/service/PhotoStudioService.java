package net.fashiongo.webadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.photostudio.PhotoCategoryRepository;
import net.fashiongo.webadmin.model.photostudio.PhotoCategory;

@Service
public class PhotoStudioService {

	@Autowired
	private PhotoCategoryRepository photoCategoryRepository;
	
	public List<PhotoCategory> getCategories() {
		return photoCategoryRepository.findAll();
	}
}
