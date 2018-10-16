package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.fashiongo.webadmin.common.PagedResult;
import net.fashiongo.webadmin.common.PhotoStudioJdbcHelper;
import net.fashiongo.webadmin.common.QueryParam;
import net.fashiongo.webadmin.common.SingleValueResult;
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
import net.fashiongo.webadmin.dao.photostudio.PhotoUnitRepository;
import net.fashiongo.webadmin.model.photostudio.CommonReportsVo;
import net.fashiongo.webadmin.model.photostudio.DailySummaryVo;
import net.fashiongo.webadmin.model.photostudio.DetailPhotoOrder;
import net.fashiongo.webadmin.model.photostudio.LogPhotoAction;
import net.fashiongo.webadmin.model.photostudio.MapPhotoCalendarModel;
import net.fashiongo.webadmin.model.photostudio.MapPhotoCategoryPrice;
import net.fashiongo.webadmin.model.photostudio.MapPhotoImage;
import net.fashiongo.webadmin.model.photostudio.PhotoCalendar;
import net.fashiongo.webadmin.model.photostudio.PhotoCancellationFee;
import net.fashiongo.webadmin.model.photostudio.PhotoCategory;
import net.fashiongo.webadmin.model.photostudio.PhotoDiscount;
import net.fashiongo.webadmin.model.photostudio.PhotoImage;
import net.fashiongo.webadmin.model.photostudio.PhotoModel;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderDetail;
import net.fashiongo.webadmin.model.photostudio.PhotoPrice;
import net.fashiongo.webadmin.model.photostudio.PhotoUnit;
import net.fashiongo.webadmin.model.photostudio.SimplePhotoOrder;
import net.fashiongo.webadmin.utility.Utility;

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
	private PhotoUnitRepository photoUnitRepository;
	
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
		String username = Utility.getWebAdminUserName();

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
	
	public Map<String, Object> getPhotoPrices() {
		Map<String, Object> result = new HashMap<String, Object> ();
		List<Object> params = new ArrayList<Object>();

		List<Object> r = jdbcHelper.executeSP("up_wa_Photo_GetPrices", params, PhotoPrice.class, PhotoPrice.class);

		List<PhotoPrice> currentPhotoPrices = (List<PhotoPrice>) r.get(0);
		List<PhotoPrice> newPhotoPrices = (List<PhotoPrice>) r.get(1);
		
		if(newPhotoPrices == null || newPhotoPrices.size() == 0) {
			for(PhotoPrice currentPhotoPrice : currentPhotoPrices) {
				PhotoPrice newPhotoPrice = new PhotoPrice();
				newPhotoPrice.setPriceTypeID(currentPhotoPrice.getPriceTypeID());
				newPhotoPrice.setPriceTypeName(currentPhotoPrice.getPriceTypeName());
				newPhotoPrice.setPhotoshootType(currentPhotoPrice.getPhotoshootType());
				newPhotoPrice.setPhotoShootTypeName(currentPhotoPrice.getPhotoShootTypeName());
			}
		}
		
		result.put("currentPrices", currentPhotoPrices);
		result.put("newPrices", newPhotoPrices);

		return result;
	}
	
	@Transactional
	public String savePrices(Map<String, List<PhotoPrice>> parmMap) throws IllegalArgumentException, IllegalAccessException {
		
		String Msg = null;
		
		List<PhotoPrice> currentPrices = parmMap.get("currentPrices");
		List<PhotoPrice> newPrices = parmMap.get("newPrices");
		
		LocalDateTime currentFromEffectiveDate = currentPrices.get(0).get_fromEffectiveDate();
		LocalDateTime newFromEffectiveDate = newPrices.get(0).get_fromEffectiveDate();
		
		LocalDateTime now = LocalDateTime.now();
		String username = Utility.getWebAdminUserName();
		
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
					if(StringUtils.equalsIgnoreCase(photoCategory.getTypeOfPhotoshoot(), newPhotoPrice.getPhotoShootTypeName())) {
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
	
	public Map<String, Object> getCancellationfees() {
		Map<String, Object> result = new HashMap<String, Object> ();
		List<Object> params = new ArrayList<Object>();

		List<Object> r = jdbcHelper.executeSP("up_wa_Photo_GetCancellationFees", params, PhotoCancellationFee.class, PhotoCancellationFee.class);

		List<PhotoCancellationFee> currentCancellationFees = (List<PhotoCancellationFee>) r.get(0);
		List<PhotoCancellationFee> newCancellationFees = (List<PhotoCancellationFee>) r.get(1);
		
		if(newCancellationFees == null || newCancellationFees.size() == 0) {
			for(PhotoCancellationFee currentCancellationFee : currentCancellationFees) {
				PhotoCancellationFee newCancellationFee = new PhotoCancellationFee();
				newCancellationFee.setCancelTypeName(currentCancellationFee.getCancelTypeName());
				newCancellationFees.add(newCancellationFee);
			}
		}
		
		result.put("currentCancellationFees", currentCancellationFees);
		result.put("newCancellationFees", newCancellationFees);

		return result;
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
		String username = Utility.getWebAdminUserName();

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
	
	public Map<String, Object> getPhotoUnits() {
		Map<String, Object> result = new HashMap<String, Object> ();
		List<Object> params = new ArrayList<Object>();

		List<Object> r = jdbcHelper.executeSP("up_wa_Photo_GetUnits", params, PhotoUnit.class, PhotoUnit.class);

		List<PhotoUnit> currentPhotoUnits = (List<PhotoUnit>) r.get(0);
		List<PhotoUnit> newPhotoUnits = (List<PhotoUnit>) r.get(1);
		
		if(newPhotoUnits == null || newPhotoUnits.size() == 0) {
			for(PhotoUnit currentPhotoUnit : currentPhotoUnits) {
				PhotoUnit newPhotoUnit = new PhotoUnit();
				newPhotoUnit.setPriceTypeID(currentPhotoUnit.getPriceTypeID());
				newPhotoUnit.setPriceTypeName(currentPhotoUnit.getPriceTypeName());
				newPhotoUnit.setPhotoshootType(currentPhotoUnit.getPhotoshootType());
				newPhotoUnit.setPhotoShootTypeName(currentPhotoUnit.getPhotoShootTypeName());
			}
		}
		
		result.put("currentPhotoUnits", currentPhotoUnits);
		result.put("newPhotoUnits", newPhotoUnits);

		return result;
	}
	
	@Transactional
	public String savePhotoUnits(Map<String, List<PhotoUnit>> parmMap)
			throws IllegalArgumentException, IllegalAccessException {

		String Msg = null;

		List<PhotoUnit> currentPhotoUnits = parmMap.get("currentPhotoUnits");
		List<PhotoUnit> newPhotoUnits = parmMap.get("newPhotoUnits");

		LocalDateTime currentFromEffectiveDate = currentPhotoUnits.get(0).get_fromEffectiveDate();
		LocalDateTime newFromEffectiveDate = newPhotoUnits.get(0).get_fromEffectiveDate();

		LocalDateTime now = LocalDateTime.now();
		String username = Utility.getWebAdminUserName();

		if (!newFromEffectiveDate.isAfter(now)) {
			return "The new effective date must after today!";
		}

		LocalDateTime currentToEffectiveDate = newFromEffectiveDate.minusSeconds(1);
		if (currentToEffectiveDate.isBefore(currentFromEffectiveDate)) {
			return "The new effective date can not be equal or less than current effective date!";
		}

		for (PhotoUnit currentPhotoUnit : currentPhotoUnits) {
			currentPhotoUnit.set_toEffectiveDate(currentToEffectiveDate);
			currentPhotoUnit.setModifiedBY(username);
			currentPhotoUnit.setModifiedOnDate(now);
			String sql = currentPhotoUnit.toUpdateQuery("");
			jdbcTemplate.update(sql);
		}

		if (newPhotoUnits.get(0).getUnitID() == null) {
			for (PhotoUnit newPhotoUnit : newPhotoUnits) {
				newPhotoUnit.setCreatedOnDate(now);
				newPhotoUnit.setCreatedBy(username);
			}
			photoUnitRepository.save(newPhotoUnits);

		} else {
			for (PhotoUnit newPhotoUnit : newPhotoUnits) {
				newPhotoUnit.setModifiedOnDate(now);
				newPhotoUnit.setModifiedBY(username);
				String sql = newPhotoUnit.toUpdateQuery("");
				jdbcTemplate.update(sql);
			}
		}

		return Msg;
	}

	@Transactional
	public Integer saveModel(PhotoModel photoModel) throws IllegalArgumentException, IllegalAccessException {
		LocalDateTime date = LocalDateTime.now();
		String username = Utility.getWebAdminUserName();

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
			
			if(photoImages.size() > 0) {
				List<MapPhotoImage> mapPhotoImages = new ArrayList<MapPhotoImage>();
				List<PhotoImage> newPhotoImages = new ArrayList<PhotoImage>();
				for (PhotoImage photoImage : photoImages) {
					if(photoImage.getImageID() == null) {
						MapPhotoImage mapPhotoImage = new MapPhotoImage();
						mapPhotoImage.setMappingType(IMAGE_MAPPING_TYPE_MODEL);
						mapPhotoImage.setImageID(photoImage.getImageID());
						mapPhotoImage.setReferenceID(photoModel.getModelID());
						mapPhotoImage.setListOrder(photoImage.getListOrder());
						mapPhotoImages.add(mapPhotoImage);
						newPhotoImages.add(photoImage);
					}else {
						sql = photoImage.toUpdateQuery("");
						jdbcTemplate.update(sql);
					}
				}
				photoImageRepository.save(newPhotoImages);
				mapPhotoImageRepository.save(mapPhotoImages);
			}
		}

		return photoModel.getModelID();
	}
	
	@SuppressWarnings("unchecked")
	public List<PhotoModel> getModels(QueryParam queryParam) {

		List<Object> params = new ArrayList<Object>();
		params.add(queryParam.getModelName());
		params.add(queryParam.getNoteLeft());
		
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
		
		params.add(queryParam.getModelType());
		params.add(queryParam.getSize());
		params.add(queryParam.getHairColor());
		params.add(queryParam.getEyesColor());
		params.add(queryParam.getHeightType());
		params.add(queryParam.getHeightFt());
		params.add(queryParam.getHeightIn());
		params.add(queryParam.getWeightFrom());
		params.add(queryParam.getWeightTo());
		params.add(queryParam.getWaistFrom());
		params.add(queryParam.getWaistTo());
		params.add(queryParam.getHipFrom());
		params.add(queryParam.getHipTo());
		params.add(queryParam.getBustFrom());
		params.add(queryParam.getBustTo());
		params.add(queryParam.getShoeSizeFrom());
		params.add(queryParam.getShoeSizeTo());
		
		List<Object> _results = jdbcHelper.executeSP("up_wa_Photo_GetModelList", params, PhotoModel.class);
		List<PhotoModel> result = (List<PhotoModel>)_results.get(0);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public PhotoModel getModel(Integer modelID) {

		List<Object> params = new ArrayList<Object>();
		params.add(modelID);
		
		List<Object> _results = jdbcHelper.executeSP("up_wa_Photo_GetModelInfo", params, PhotoModel.class, PhotoImage.class);
		
		List<PhotoModel> photoModels = (List<PhotoModel>)_results.get(0);
		List<PhotoImage> photoImages = (List<PhotoImage>)_results.get(1);
		
		PhotoModel photoModel = photoModels.get(0);
		photoModel.setPhotoImages(photoImages);
		
		return photoModel;
	}
	
	@Transactional
	public String saveCalendar(List<PhotoCalendar> photoCalendars) throws IllegalArgumentException, IllegalAccessException {
		
		String Msg = null;
		
		LocalDateTime now = LocalDateTime.now();
		String username = Utility.getWebAdminUserName();
		
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
		String username = Utility.getWebAdminUserName();
		
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
		
		params.add(queryParam.getIsDelayed());
		params.add(queryParam.getCancelledByFG());
		params.add(queryParam.getCancelledByVendor());

		List<Object> _results = jdbcHelper.executeSP("up_wa_Photo_GetOrderList", params, SingleValueResult.class, SimplePhotoOrder.class);
		
		List<SingleValueResult> rs1 = (List<SingleValueResult>)_results.get(0);
		List<SimplePhotoOrder> rs2 = (List<SimplePhotoOrder>)_results.get(1);

		result.setTotal(rs1.get(0));
		result.setRecords(rs2);
		
		return result;
	}
	
	public Map<String, Object> getPhotoOrder(String poNumber) {
		Map<String, Object> result = new HashMap<String, Object> ();
		List<Object> params = new ArrayList<Object>();
		params.add(poNumber);

		List<Object> r = jdbcHelper.executeSP("up_wa_Photo_GetOrderDetail", params, DetailPhotoOrder.class, PhotoOrderDetail.class);

		List<DetailPhotoOrder> photoOrders = (List<DetailPhotoOrder>) r.get(0);
		List<PhotoOrderDetail> photoOrderDetails = (List<PhotoOrderDetail>) r.get(1);
		
		result.put("photoOrder", photoOrders.get(0));
		result.put("photoOrderDetails", photoOrderDetails);
		
		//get photoStudio Users Being confirmed
		result.put("photoStudioUsers", Arrays.asList("Brooke","Maria","Brandon","Rosa"));

		return result;
	}
	
	public DailySummaryVo getDailySummary(String photoshootDate) {
		List<Object> params = new ArrayList<Object>();
		params.add(photoshootDate);

		List<Object> r = jdbcHelper.executeSP("up_wa_Photo_GetDailySummary", params, DailySummaryVo.class);
		List<DailySummaryVo> dailySummaryVos = (List<DailySummaryVo>) r.get(0);

		return dailySummaryVos.get(0);
	}
	
	public List<PhotoCalendar> getPhotoCalendar(Map<String, String> parmMap) {
		List<Object> params = new ArrayList<Object>();
		params.add(parmMap.get("year"));
		params.add(parmMap.get("month"));

		List<Object> r = jdbcHelper.executeSP("up_wa_Photo_GetPhotoCalendar", params, PhotoCalendar.class);

		List<PhotoCalendar> photoCalendars = (List<PhotoCalendar>) r.get(0);

		return photoCalendars;
	}
	
	public List<LogPhotoAction> getActionLog(Integer orderId, Integer actionType) {
		List<Object> params = new ArrayList<Object>();
		params.add(null);//ActionID
		params.add(actionType);//ActionType
		params.add(orderId);//OrderID
		params.add(null);//CreatedOn
		params.add(null);//CreatedBy

		List<Object> r = jdbcHelper.executeSP("up_wa_Photo_GetActionLog", params, LogPhotoAction.class);

		List<LogPhotoAction> logPhotoActions = (List<LogPhotoAction>) r.get(0);

		return logPhotoActions;
	}
	
	public Map<String, Object> getReports(Map<String, Object> parmMap) {
		
		int categoryID = Integer.parseInt(String.valueOf(parmMap.get("categoryID")));;
		
		Map<String, Object> result = new HashMap<String, Object> ();
		List<Object> params = new ArrayList<Object>();
		
		params.add(parmMap.get("year"));
		params.add(parmMap.get("month"));
		params.add(categoryID);

		if(categoryID == 5) {
			List<Object> r = jdbcHelper.executeSP("up_wa_Photo_GetReports", params, CommonReportsVo.class, CommonReportsVo.class);
			
			List<CommonReportsVo> dailyData  = (List<CommonReportsVo>) r.get(0);
			List<CommonReportsVo> monthSummary = (List<CommonReportsVo>) r.get(1);
			
			result.put("dailyData", dailyData);
			result.put("monthSummary", monthSummary);

		}else {
			List<Object> r = jdbcHelper.executeSP("up_wa_Photo_GetReports", params, CommonReportsVo.class, CommonReportsVo.class, CommonReportsVo.class);
			List<CommonReportsVo> dailyVendorData  = (List<CommonReportsVo>) r.get(0);
			List<CommonReportsVo> dailyData = (List<CommonReportsVo>) r.get(1);
			List<CommonReportsVo> monthSummary = (List<CommonReportsVo>) r.get(2);
			
			result.put("dailyVendorData", dailyVendorData);
			result.put("dailyData", dailyData);
			result.put("monthSummary", monthSummary);
		}
		
		return result;
	}
}
