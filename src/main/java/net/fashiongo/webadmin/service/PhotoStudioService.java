package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import net.fashiongo.webadmin.common.Utility;
import net.fashiongo.webadmin.dao.photostudio.MapPhotoImageRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoCategoryRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoDiscountRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoImageRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoModelRepository;
import net.fashiongo.webadmin.model.photostudio.MapPhotoImage;
import net.fashiongo.webadmin.model.photostudio.PhotoCategory;
import net.fashiongo.webadmin.model.photostudio.PhotoDiscount;
import net.fashiongo.webadmin.model.photostudio.PhotoImage;
import net.fashiongo.webadmin.model.photostudio.PhotoModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhotoStudioService {

	@Autowired
	private PhotoCategoryRepository photoCategoryRepository;

	@Autowired
	private PhotoDiscountRepository photoDiscountRepository;
	
	@Autowired
	private PhotoModelRepository photoModelRepository;
	
	@Autowired
	private PhotoImageRepository photoImageRepository;
	
	@Autowired
	private MapPhotoImageRepository mapPhotoImageRepository;

	@Autowired
	@Qualifier("photostudioJdbcTemplate")
	protected JdbcTemplate jdbcTemplate;
	
	public static final int IMAGE_MAPPING_TYPE_MODEL = 3;

	public List<PhotoCategory> getCategories() {
		return photoCategoryRepository.findAll();
	}

	public List<PhotoDiscount> getDiscounts() {
		return photoDiscountRepository.findAll();
	}

	public Integer saveDiscount(PhotoDiscount photoDiscount) throws IllegalArgumentException, IllegalAccessException {
		LocalDateTime date = LocalDateTime.now();
		String username = Utility.getUsername();
		//photoDiscount.setFromDateDB(LocalDateTime.parse(photoDiscount.getFromDate(),DateTimeFormatter.ISO_LOCAL_DATE));
		//photoDiscount.setToDateDB(LocalDateTime.parse(photoDiscount.getToDate(),DateTimeFormatter.ISO_LOCAL_DATE));

		if (photoDiscount.getDiscountID() == null) {
			photoDiscount.setCreatedBy(username);
			photoDiscount.setCreatedOnDate(date);
			photoDiscountRepository.save(photoDiscount);
		} else {
			photoDiscount.setModifiedBY(username);
			photoDiscount.setModifiedOnDate(date);
			String sql = photoDiscount.toUpdateQuery("");
			jdbcTemplate.update(sql);
		}

		return photoDiscount.getDiscountID();
	}

	public PhotoDiscount getDiscount(Integer discountID) {
		return photoDiscountRepository.findOne(discountID);
	}

	public boolean deleteDiscount(Integer discountID) {

		boolean bSuccess = false;
		PhotoDiscount photoDiscount = new PhotoDiscount();
		photoDiscount.setDiscountID(discountID);
		String sql = photoDiscount.toDeleteQuery();
		jdbcTemplate.update(sql);
		bSuccess = true;

		return bSuccess;
	}

	@Transactional
	public Integer saveModel(PhotoModel photoModel) throws IllegalArgumentException, IllegalAccessException {
		LocalDateTime date = LocalDateTime.now();
		String username = Utility.getUsername();

		List<PhotoImage> photoImages = photoModel.getPhotoImages();
		if (photoModel.getModelID() == null) {
			photoModel.setCreatedBy(username);
			photoModel.setCreatedOnDate(date);
			photoModelRepository.save(photoModel);
			if(photoImages.size() > 0) {
				photoImageRepository.save(photoImages);
				List<MapPhotoImage> mapPhotoImages = new ArrayList<MapPhotoImage>();
				for (PhotoImage photoImage : photoImages) {
					MapPhotoImage mapPhotoImage = new MapPhotoImage();
					mapPhotoImage.setMappingType(IMAGE_MAPPING_TYPE_MODEL);
					mapPhotoImage.setImageID(photoImage.getImageID());
					mapPhotoImage.setReferenceID(photoModel.getModelID());
					mapPhotoImage.setListOrder(photoImage.getListOrder());
					mapPhotoImages.add(mapPhotoImage);
				}
				mapPhotoImageRepository.save(mapPhotoImages);
			}
		} else {
			photoModel.setModifiedBY(username);
			photoModel.setModifiedOnDate(date);
			String sql = photoModel.toUpdateQuery("");
			jdbcTemplate.update(sql);
			for (PhotoImage photoImage : photoImages) {
				sql = photoImage.toUpdateQuery("");
				jdbcTemplate.update(sql);
			}
		}

		return photoModel.getModelID();
	}
}
