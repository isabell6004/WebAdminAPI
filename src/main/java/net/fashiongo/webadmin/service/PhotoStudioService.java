package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.fashiongo.webadmin.common.PagedResult;
import net.fashiongo.webadmin.common.PhotoStudioJdbcHelper;
import net.fashiongo.webadmin.common.QueryParam;
import net.fashiongo.webadmin.common.SingleValueResult;
import net.fashiongo.webadmin.common.Utility;
import net.fashiongo.webadmin.dao.photostudio.MapPhotoCalendarModelRepository;
import net.fashiongo.webadmin.dao.photostudio.MapPhotoCategoryPriceRepository;
import net.fashiongo.webadmin.dao.photostudio.MapPhotoImageRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoCalendarRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoCancellationFeeRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoCategoryRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoDiscountRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoImageRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoModelRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoPriceRepository;
import net.fashiongo.webadmin.model.photostudio.MapPhotoCalendarModel;
import net.fashiongo.webadmin.model.photostudio.MapPhotoCategoryPrice;
import net.fashiongo.webadmin.model.photostudio.MapPhotoImage;
import net.fashiongo.webadmin.model.photostudio.PhotoCalendar;
import net.fashiongo.webadmin.model.photostudio.PhotoCancellationFee;
import net.fashiongo.webadmin.model.photostudio.PhotoCategory;
import net.fashiongo.webadmin.model.photostudio.PhotoDiscount;
import net.fashiongo.webadmin.model.photostudio.PhotoImage;
import net.fashiongo.webadmin.model.photostudio.PhotoModel;
import net.fashiongo.webadmin.model.photostudio.PhotoPrice;
import net.fashiongo.webadmin.model.photostudio.SimplePhotoOrder;

import org.apache.commons.lang.StringUtils;
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
	private PhotoPriceRepository photoPriceRepository;
	
	@Autowired
	private MapPhotoCategoryPriceRepository mapPhotoCategoryPriceRepository;
	
	@Autowired
	private PhotoCancellationFeeRepository photoCancellationFeeRepository;
	
	@Autowired
	private PhotoCalendarRepository photoCalendarRepository;
	
	@Autowired
	private MapPhotoCalendarModelRepository mapPhotoCalendarModelRepository;
	
	@Autowired
	protected PhotoStudioJdbcHelper jdbcHelper;
	
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
	public String savePrices(Map<String, List<PhotoPrice>> parmMap) throws IllegalArgumentException, IllegalAccessException {
		
		String Msg = null;
		
		List<PhotoPrice> currentPrices = parmMap.get("currentPrices");
		List<PhotoPrice> newPrices = parmMap.get("newPrices");
		
		LocalDateTime currentFromEffectiveDate = currentPrices.get(0).get_fromEffectiveDate();
		LocalDateTime newFromEffectiveDate = newPrices.get(0).get_fromEffectiveDate();
		
		LocalDateTime now = LocalDateTime.now();
		String username = Utility.getUsername();
		
		if(!newFromEffectiveDate.isAfter(now)) {
			return "The new effective date must after today!";
		}
		
		LocalDateTime currentToEffectiveDate = newFromEffectiveDate.minusSeconds(1);
		if(currentToEffectiveDate.isBefore(currentFromEffectiveDate)) {
			return "The new effective date can not be equal or less than current effective date!";
		}
		
		for(PhotoPrice currentPhotoPrice : currentPrices) {
			currentPhotoPrice.set_toEffectiveDate(currentToEffectiveDate);
			currentPhotoPrice.setModifiedBY(username);
			currentPhotoPrice.setModifiedOnDate(now);
			String sql = currentPhotoPrice.toUpdateQuery("");
			jdbcTemplate.update(sql);
		}
		
		if(newPrices.get(0).getPriceID() == null) {
			for(PhotoPrice newPhotoPrice : newPrices) {
				newPhotoPrice.setCreatedOnDate(now);
				newPhotoPrice.setCreatedBy(username);
			}
			photoPriceRepository.save(newPrices);
			
			List<PhotoCategory> photoCategorys = photoCategoryRepository.findAll();
			List<MapPhotoCategoryPrice> mapPhotoCategoryPrices = new ArrayList<MapPhotoCategoryPrice>();
			for(PhotoCategory photoCategory : photoCategorys) {
				for(PhotoPrice newPhotoPrice : newPrices) {
					if(StringUtils.equalsIgnoreCase(photoCategory.getTypeOfPhotoshoot(), getPhotoshootTypeName(newPhotoPrice.getPhotoshootType()))) {
						MapPhotoCategoryPrice mapPhotoCategoryPrice = new MapPhotoCategoryPrice();
						mapPhotoCategoryPrice.setCategoryID(photoCategory.getCategoryId());
						mapPhotoCategoryPrice.setPriceID(newPhotoPrice.getPriceID());
						mapPhotoCategoryPrices.add(mapPhotoCategoryPrice);
					}
				}
			}
			mapPhotoCategoryPriceRepository.save(mapPhotoCategoryPrices);
		}else {
			for(PhotoPrice newPhotoPrice : newPrices) {
				newPhotoPrice.setModifiedOnDate(now);
				newPhotoPrice.setModifiedBY(username);
				String sql = newPhotoPrice.toUpdateQuery("");
				jdbcTemplate.update(sql);
			}
		}

		return Msg;
	}
	
	private String getPhotoshootTypeName(int photoshootType) {
		
		if(photoshootType == 1) {
			return "Full Model Shot";
		}else if(photoshootType == 2) {
			return "Flat Product Shot";
		}else {
			return null;
		}
	}
	
	@Transactional
	public String saveCancellationFees(Map<String, List<PhotoCancellationFee>> parmMap)
			throws IllegalArgumentException, IllegalAccessException {

		String Msg = null;

		List<PhotoCancellationFee> currentCancellationFees = parmMap.get("currentCancellationFees");
		List<PhotoCancellationFee> newCancellationFees = parmMap.get("newCancellationFees");

		LocalDateTime currentFromEffectiveDate = currentCancellationFees.get(0).get_fromEffectiveDate();
		LocalDateTime newFromEffectiveDate = newCancellationFees.get(0).get_fromEffectiveDate();

		LocalDateTime now = LocalDateTime.now();
		String username = Utility.getUsername();

		if (!newFromEffectiveDate.isAfter(now)) {
			return "The new effective date must after today!";
		}

		LocalDateTime currentToEffectiveDate = newFromEffectiveDate.minusSeconds(1);
		if (currentToEffectiveDate.isBefore(currentFromEffectiveDate)) {
			return "The new effective date can not be equal or less than current effective date!";
		}

		for (PhotoCancellationFee currentPhotoCancellationFee : currentCancellationFees) {
			currentPhotoCancellationFee.set_toEffectiveDate(currentToEffectiveDate);
			currentPhotoCancellationFee.setModifiedBY(username);
			currentPhotoCancellationFee.setModifiedOnDate(now);
			String sql = currentPhotoCancellationFee.toUpdateQuery("");
			jdbcTemplate.update(sql);
		}

		if (newCancellationFees.get(0).getCancelTypeID() == null) {
			for (PhotoCancellationFee newPhotoCancellationFee : newCancellationFees) {
				newPhotoCancellationFee.setCreatedOnDate(now);
				newPhotoCancellationFee.setCreatedBy(username);
			}
			photoCancellationFeeRepository.save(newCancellationFees);

		} else {
			for (PhotoCancellationFee newPhotoCancellationFee : newCancellationFees) {
				newPhotoCancellationFee.setModifiedOnDate(now);
				newPhotoCancellationFee.setModifiedBY(username);
				String sql = newPhotoCancellationFee.toUpdateQuery("");
				jdbcTemplate.update(sql);
			}
		}

		return Msg;
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
	
	@Transactional
	public String saveCalendar(List<PhotoCalendar> photoCalendars) throws IllegalArgumentException, IllegalAccessException {
		
		String Msg = null;
		
		LocalDateTime now = LocalDateTime.now();
		String username = Utility.getUsername();
		
		List<MapPhotoCalendarModel> mapPhotoCalendarModels;
		
		for(PhotoCalendar photoCalendar : photoCalendars) {
			if(photoCalendar.getCalendarID() == null) {
				return "CalendarID does not exist!";
			}else {
				photoCalendar.setModifiedOnDate(now);
				photoCalendar.setModifiedBY(username);
				String sql = photoCalendar.toUpdateQuery("");
				jdbcTemplate.update(sql);
				mapPhotoCalendarModels = photoCalendar.getMapPhotoCalendarModels();
				for(MapPhotoCalendarModel mapPhotoCalendarModel : mapPhotoCalendarModels) {
					if(mapPhotoCalendarModel.getModelScheduleID() == null) {
						mapPhotoCalendarModelRepository.save(mapPhotoCalendarModels);
					}else {
						sql = mapPhotoCalendarModel.toUpdateQuery("");
						jdbcTemplate.update(sql);
					}
				}
			}
		}
		
		return Msg;
	}
	
	public String calendarAvailable(PhotoCalendar photoCalendar) throws IllegalArgumentException, IllegalAccessException {
		
		String Msg = null;
		
		LocalDateTime now = LocalDateTime.now();
		String username = Utility.getUsername();
		
		photoCalendar.setModifiedOnDate(now);
		photoCalendar.setModifiedBY(username);
		String sql = photoCalendar.toUpdateQuery("");
		jdbcTemplate.update(sql);
		
		return Msg;
	}
	
	@SuppressWarnings("unchecked")
	public PagedResult<SimplePhotoOrder> getPhotoOrders(QueryParam queryParam) {
		PagedResult<SimplePhotoOrder> result = new PagedResult<SimplePhotoOrder>();

		List<Object> params = new ArrayList<Object>();
		params.add(queryParam.getPn());
		params.add(queryParam.getPs());
		params.add(queryParam.getOrderBy());
		
		String searchType = queryParam.getQt();	
		String searchKeyword = queryParam.getQ();
		
		if(!StringUtils.isEmpty(searchType) && !StringUtils.isEmpty(searchKeyword) ) {
			params.add(searchType);
			params.add(searchKeyword);
		}
		else {
			params.add(null);
			params.add(null);
		}
		
		String dType = queryParam.getDtype();
		Date df = queryParam.getDf();
		Date dt = queryParam.getDt();
		if(!StringUtils.isEmpty(dType) && (df != null || dt != null)) {
			params.add(dType);
			params.add(df);
			params.add(dt);
		}else {
			params.add(null);
			params.add(null);
			params.add(null);
		}
		
		params.add(queryParam.getCatids());
		params.add(queryParam.getOstsids());

		List<Object> _results = jdbcHelper.executeSP("up_wa_Photo_GetOrderList", params, SimplePhotoOrder.class, SingleValueResult.class);
		
		List<SimplePhotoOrder> rs1 = (List<SimplePhotoOrder>)_results.get(0);
		List<SingleValueResult> rs2 = (List<SingleValueResult>)_results.get(1);

		result.setTotal(rs2.get(0));
		result.setRecords(rs1);
		
		return result;
	}
	
}
