package net.fashiongo.webadmin.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.fashiongo.webadmin.common.PagedResult;
import net.fashiongo.webadmin.common.QueryParam;
import net.fashiongo.webadmin.common.SingleValueResult;
import net.fashiongo.webadmin.common.Utility;
import net.fashiongo.webadmin.dao.photostudio.LogPhotoActionRepository;
import net.fashiongo.webadmin.dao.photostudio.MapPhotoCalendarModelRepository;
import net.fashiongo.webadmin.dao.photostudio.MapPhotoCategoryPriceRepository;
import net.fashiongo.webadmin.dao.photostudio.MapPhotoImageRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoCalendarRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoCancellationFeeRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoCategoryRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoCreditRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoCreditUsageRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoDiscountRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoImageRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoModelRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoPriceRepository;
import net.fashiongo.webadmin.dao.photostudio.PhotoUnitRepository;
import net.fashiongo.webadmin.model.photostudio.CalendarPhotoModel;
import net.fashiongo.webadmin.model.photostudio.CommonReportsVo;
import net.fashiongo.webadmin.model.photostudio.DailySummaryVo;
import net.fashiongo.webadmin.model.photostudio.DetailPhotoOrder;
import net.fashiongo.webadmin.model.photostudio.LogPhotoAction;
import net.fashiongo.webadmin.model.photostudio.MapPhotoCalendarModel;
import net.fashiongo.webadmin.model.photostudio.MapPhotoCategoryPrice;
import net.fashiongo.webadmin.model.photostudio.MapPhotoImage;
import net.fashiongo.webadmin.model.photostudio.PhotoActionUser;
import net.fashiongo.webadmin.model.photostudio.PhotoCalendar;
import net.fashiongo.webadmin.model.photostudio.PhotoCancellationFee;
import net.fashiongo.webadmin.model.photostudio.PhotoCategory;
import net.fashiongo.webadmin.model.photostudio.PhotoCredit;
import net.fashiongo.webadmin.model.photostudio.PhotoDiscount;
import net.fashiongo.webadmin.model.photostudio.PhotoImage;
import net.fashiongo.webadmin.model.photostudio.PhotoModel;
import net.fashiongo.webadmin.model.photostudio.PhotoOrder;
import net.fashiongo.webadmin.model.photostudio.PhotoOrderDetail;
import net.fashiongo.webadmin.model.photostudio.PhotoPrice;
import net.fashiongo.webadmin.model.photostudio.PhotoUnit;
import net.fashiongo.webadmin.model.photostudio.SimplePhotoOrder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhotoStudioService extends ApiService {

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
	private LogPhotoActionRepository logPhotoActionRepository;
	
	@Autowired
	private PhotoCreditUsageRepository photoCreditUsageRepository;
	
	@Autowired
	private PhotoCreditRepository photoCreditRepository;
	
	public static final int IMAGE_MAPPING_TYPE_MODEL = 3;

	public List<PhotoCategory> getCategories() {
		return photoCategoryRepository.findAll();
	}
	
	public List<PhotoDiscount> getDiscounts() {
		Sort sort = new Sort(Direction.DESC, "DiscountID");
		return photoDiscountRepository.findAll(sort);
	}

	public String saveDiscount(PhotoDiscount photoDiscount) throws IllegalArgumentException, IllegalAccessException {
		
		if(photoDiscount.getActive() == null) {
			return "Status is required.";
		}
		
		LocalDateTime date = LocalDateTime.now();
		String username = Utility.getUsername();
		if(photoDiscount.getDiscountID() == null) {
			
			if(photoDiscount.getDiscountName() == null) {
				return "Title is required.";
			}
			
			if(photoDiscount.getDiscountCode() == null) {
				return "Discount Code is required.";
			}
			
			if(photoDiscount.getIsFirstTimeOrder() == null) {
				return "First Time Order Only is required.";
			}
			
			if(photoDiscount.getIsRequiredPriceQty() == null 
					|| (photoDiscount.getIsRequiredPriceQty() != null && Boolean.TRUE.equals(photoDiscount.getIsRequiredPriceQty()) 
					&& photoDiscount.getRequiredPrice() == null && photoDiscount.getRequiredQty() == null)) {
				return "Required Price or Required Qty is required.";
			}
			
			if(photoDiscount.getDiscountAmount() == null && photoDiscount.getDiscountRate() == null) {
				return "Discount Amount is required.";
			}
			
			if(photoDiscountRepository.existsByDiscountCode(photoDiscount.getDiscountCode())) {
				return "This Discount code [" + photoDiscount.getDiscountCode() + "] is already used.";
			}
			
			photoDiscount.setCreatedBy(username);
			photoDiscount.setCreatedOnDate(date);
			photoDiscountRepository.save(photoDiscount);
		}else {
			photoDiscount.setModifiedBY(username);
			photoDiscount.setModifiedOnDate(date);
			jdbcTemplatePhotoStudio.update(photoDiscount.toUpdateQuery(""));
		}
		return null;
	}

	public PhotoDiscount getDiscount(Integer discountID) {
		return photoDiscountRepository.findById(discountID).orElse(null);
	}
	
	public boolean deleteDiscount(Integer discountID) {

		boolean bSuccess = false;
		PhotoDiscount photoDiscount = new PhotoDiscount();
		photoDiscount.setDiscountID(discountID);
		String sql = photoDiscount.toDeleteQuery();
		jdbcTemplatePhotoStudio.update(sql);
		bSuccess = true;

		return bSuccess;
	}
	
	public Map<String, Object> getPhotoPrices() {
		Map<String, Object> result = new HashMap<String, Object> ();
		List<Object> params = new ArrayList<Object>();

		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetPrices", params, PhotoPrice.class, PhotoPrice.class);

		List<PhotoPrice> currentPhotoPrices = (List<PhotoPrice>) r.get(0);
		List<PhotoPrice> newPhotoPrices = (List<PhotoPrice>) r.get(1);
		
		if(newPhotoPrices == null || newPhotoPrices.size() == 0) {
			for(PhotoPrice currentPhotoPrice : currentPhotoPrices) {
				PhotoPrice newPhotoPrice = new PhotoPrice();
				newPhotoPrice.setPriceTypeID(currentPhotoPrice.getPriceTypeID());
				newPhotoPrice.setPriceTypeName(currentPhotoPrice.getPriceTypeName());
				newPhotoPrice.setPhotoshootType(currentPhotoPrice.getPhotoshootType());
				newPhotoPrice.setPhotoShootTypeName(currentPhotoPrice.getPhotoShootTypeName());
				newPhotoPrices.add(newPhotoPrice);
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
			if(currentPhotoPrice.getPriceID() == null) {
				return "The current price does not exist , can't update ！ ";
			}
			String sql = currentPhotoPrice.toUpdateQuery("");
			jdbcTemplatePhotoStudio.update(sql);
		}
		
		if(newPrices.get(0).getPriceID() == null) {
			for(PhotoPrice newPhotoPrice : newPrices) {
				newPhotoPrice.setCreatedOnDate(now);
				newPhotoPrice.setCreatedBy(username);
			}
			photoPriceRepository.saveAll(newPrices);
			
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
			mapPhotoCategoryPriceRepository.saveAll(mapPhotoCategoryPrices);
		}else {
			for(PhotoPrice newPhotoPrice : newPrices) {
				newPhotoPrice.setModifiedOnDate(now);
				newPhotoPrice.setModifiedBY(username);
				if(newPhotoPrice.getPriceID() == null) {
					return "The new price does not exist , can't update ！ ";
				}
				String sql = newPhotoPrice.toUpdateQuery("");
				jdbcTemplatePhotoStudio.update(sql);
			}
		}

		return Msg;
	}
	
	public Map<String, Object> getCancellationfees() {
		Map<String, Object> result = new HashMap<String, Object> ();
		List<Object> params = new ArrayList<Object>();

		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetCancellationFees", params, PhotoCancellationFee.class, PhotoCancellationFee.class);

		List<PhotoCancellationFee> currentCancellationFees = (List<PhotoCancellationFee>) r.get(0);
		List<PhotoCancellationFee> newCancellationFees = (List<PhotoCancellationFee>) r.get(1);
		
		if(newCancellationFees == null || newCancellationFees.size() == 0) {
			for(PhotoCancellationFee currentCancellationFee : currentCancellationFees) {
				PhotoCancellationFee newCancellationFee = new PhotoCancellationFee();
				newCancellationFee.setCancelTypeNo(currentCancellationFee.getCancelTypeNo());
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
			if(currentPhotoCancellationFee.getCancelTypeID() == null) {
				return "The current cancellationFee does not exist , can't update ！ ";
			}
			String sql = currentPhotoCancellationFee.toUpdateQuery("");
			jdbcTemplatePhotoStudio.update(sql);
		}

		if (newCancellationFees.get(0).getCancelTypeID() == null) {
			for (PhotoCancellationFee newPhotoCancellationFee : newCancellationFees) {
				newPhotoCancellationFee.setCreatedOnDate(now);
				newPhotoCancellationFee.setCreatedBy(username);
			}
			photoCancellationFeeRepository.saveAll(newCancellationFees);

		} else {
			for (PhotoCancellationFee newPhotoCancellationFee : newCancellationFees) {
				newPhotoCancellationFee.setModifiedOnDate(now);
				newPhotoCancellationFee.setModifiedBY(username);
				if(newPhotoCancellationFee.getCancelTypeID() == null) {
					return "The new cancellationFee does not exist , can't update ！ ";
				}
				String sql = newPhotoCancellationFee.toUpdateQuery("");
				jdbcTemplatePhotoStudio.update(sql);
			}
		}

		return Msg;
	}
	
	public Map<String, Object> getPhotoUnits() {
		Map<String, Object> result = new HashMap<String, Object> ();
		List<Object> params = new ArrayList<Object>();

		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetUnits", params, PhotoUnit.class, PhotoUnit.class);

		List<PhotoUnit> currentPhotoUnits = (List<PhotoUnit>) r.get(0);
		List<PhotoUnit> newPhotoUnits = (List<PhotoUnit>) r.get(1);
		
		if(newPhotoUnits == null || newPhotoUnits.size() == 0) {
			for(PhotoUnit currentPhotoUnit : currentPhotoUnits) {
				PhotoUnit newPhotoUnit = new PhotoUnit();
				newPhotoUnit.setPriceTypeID(currentPhotoUnit.getPriceTypeID());
				newPhotoUnit.setPriceTypeName(currentPhotoUnit.getPriceTypeName());
				newPhotoUnit.setPhotoshootType(currentPhotoUnit.getPhotoshootType());
				newPhotoUnit.setPhotoShootTypeName(currentPhotoUnit.getPhotoShootTypeName());
				newPhotoUnits.add(newPhotoUnit);
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
		String username = Utility.getUsername();

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
			if(currentPhotoUnit.getUnitID() == null) {
				return "The current unit does not exist , can't update ！ ";
			}
			String sql = currentPhotoUnit.toUpdateQuery("");
			jdbcTemplatePhotoStudio.update(sql);
		}

		if (newPhotoUnits.get(0).getUnitID() == null) {
			for (PhotoUnit newPhotoUnit : newPhotoUnits) {
				newPhotoUnit.setCreatedOnDate(now);
				newPhotoUnit.setCreatedBy(username);
			}
			photoUnitRepository.saveAll(newPhotoUnits);

		} else {
			for (PhotoUnit newPhotoUnit : newPhotoUnits) {
				newPhotoUnit.setModifiedOnDate(now);
				newPhotoUnit.setModifiedBY(username);
				if(newPhotoUnit.getUnitID() == null) {
					return "The new unit does not exist , can't update ！ ";
				}
				String sql = newPhotoUnit.toUpdateQuery("");
				jdbcTemplatePhotoStudio.update(sql);
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
				List<MapPhotoImage> mapPhotoImages = new ArrayList<MapPhotoImage>();
				for (PhotoImage photoImage : photoImages) {
					photoImage.setCreatedBy(username);
					photoImage.setCreatedOnDate(date);
					photoImageRepository.save(photoImage);
					MapPhotoImage mapPhotoImage = new MapPhotoImage();
					mapPhotoImage.setMappingType(IMAGE_MAPPING_TYPE_MODEL);
					mapPhotoImage.setImageID(photoImage.getImageID());
					mapPhotoImage.setReferenceID(photoModel.getModelID());
					mapPhotoImage.setListOrder(photoImage.getListOrder());
					mapPhotoImages.add(mapPhotoImage);
				}
				mapPhotoImageRepository.saveAll(mapPhotoImages);
			}
		} else {
			photoModel.setModifiedBY(username);
			photoModel.setModifiedOnDate(date);
			String sql = photoModel.toUpdateQuery("");
			jdbcTemplatePhotoStudio.update(sql);
			
			List<MapPhotoImage> oldMapPhotoImages = mapPhotoImageRepository.findByMappingTypeAndReferenceID(IMAGE_MAPPING_TYPE_MODEL, photoModel.getModelID());
			if(photoImages.size() > 0) {
				List<MapPhotoImage> mapPhotoImages = new ArrayList<MapPhotoImage>();
				for (PhotoImage photoImage : photoImages) {
					//add new image
					if(photoImage.getImageID() == null) {
						photoImage.setCreatedBy(username);
						photoImage.setCreatedOnDate(date);
						photoImageRepository.save(photoImage);
						MapPhotoImage mapPhotoImage = new MapPhotoImage();
						mapPhotoImage.setMappingType(IMAGE_MAPPING_TYPE_MODEL);
						mapPhotoImage.setImageID(photoImage.getImageID());
						mapPhotoImage.setReferenceID(photoModel.getModelID());
						mapPhotoImage.setListOrder(photoImage.getListOrder());
						mapPhotoImages.add(mapPhotoImage);
					}else {
						for(MapPhotoImage oldMapPhotoImage : oldMapPhotoImages) {
							//do nothing
							if(photoImage.getImageID().intValue() == oldMapPhotoImage.getImageID().intValue()) {
								oldMapPhotoImages.remove(oldMapPhotoImage);
								break;
							}
						}
					}
				}
				
				if(mapPhotoImages.size() > 0) {
					mapPhotoImageRepository.saveAll(mapPhotoImages);
				}
			}
			//delete
			if(oldMapPhotoImages.size() > 0) {
				for(MapPhotoImage deleteMapPhotoImage : oldMapPhotoImages) {
					jdbcTemplatePhotoStudio.update(deleteMapPhotoImage.toDeleteQuery());
					PhotoImage photoImage = new PhotoImage();
					photoImage.setImageID(deleteMapPhotoImage.getImageID());
					jdbcTemplatePhotoStudio.update(photoImage.toDeleteQuery());
				}
			}
		}

		return photoModel.getModelID();
	}
	
	@SuppressWarnings("unchecked")
	public PagedResult<PhotoModel> getModels(QueryParam queryParam) {
		PagedResult<PhotoModel> result = new PagedResult<PhotoModel>();
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(queryParam.getPn());
		params.add(queryParam.getPs());
		params.add(queryParam.getOrderBy());
		
		params.add(queryParam.getModelName());
		params.add(queryParam.getNoteLeft());
		params.add(queryParam.getActive());
		
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
		
		List<Object> _results = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetModelList", params, SingleValueResult.class, PhotoModel.class);
		List<SingleValueResult> rs1 = (List<SingleValueResult>)_results.get(0);
		List<PhotoModel> rs2 = (List<PhotoModel>)_results.get(1);

		result.setTotal(rs1.get(0));
		result.setRecords(rs2);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public PhotoModel getModel(Integer modelID) {

		List<Object> params = new ArrayList<Object>();
		params.add(modelID);
		
		List<Object> _results = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetModelInfo", params, PhotoModel.class, PhotoImage.class);
		
		List<PhotoModel> photoModels = (List<PhotoModel>)_results.get(0);
		List<PhotoImage> photoImages = (List<PhotoImage>)_results.get(1);
		
		PhotoModel photoModel = photoModels.get(0);
		photoModel.setPhotoImages(photoImages);
		
		return photoModel;
	}
	
	@SuppressWarnings("unchecked")
	public String deleteModel(Integer modelID) {

		List<Object> params = new ArrayList<Object>();
		params.add(modelID);
		params.add(Utility.getUsername());
		
		List<Object> outputparams = new ArrayList<Object>();
		outputparams.add("");
		List<Object> result = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_DeleteModel", params, outputparams);
		
		List<Object> outputs= (List<Object>) result.get(0);
		
		return outputs.get(0) == null ? null : String.valueOf(outputs.get(0));
	}
	
	public List<PhotoCalendar> getPhotoCalendar(Map<String, String> parmMap) {
		List<Object> params = new ArrayList<Object>();
		params.add(parmMap.get("year"));
		params.add(parmMap.get("month"));
		params.add(parmMap.get("modelID"));

		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetPhotoCalendar", params, PhotoCalendar.class);

		List<PhotoCalendar> photoCalendars = (List<PhotoCalendar>) r.get(0);

		return photoCalendars;
	}
	
	public Map<String, Object> getPhotoCalendarModelsOrders(Map<String, String> parmMap) {
		Map<String, Object> result = new HashMap<String, Object> ();
		List<Object> params = new ArrayList<Object>();
		params.add(parmMap.get("calendarID"));
		params.add(parmMap.get("modelID"));

		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetCalendarModelsAndOrders", params, CalendarPhotoModel.class, SimplePhotoOrder.class, PhotoModel.class);

		List<PhotoModel> models = (List<PhotoModel>) r.get(0);
		List<SimplePhotoOrder> orders = (List<SimplePhotoOrder>) r.get(1);
		List<PhotoModel> modelsOption = (List<PhotoModel>) r.get(2);
		
		
		result.put("models", models);
		result.put("orders", orders);
		result.put("modelsOption", modelsOption);

		return result;
	}
	
	@Transactional
	public String saveCalendar(PhotoCalendar photoCalendar) throws IllegalArgumentException, IllegalAccessException {
		
		String Msg = null;
		BigDecimal maxUnitPerDaySum = BigDecimal.ZERO; 
		if(photoCalendar.getCalendarID() == null) {
			return "CalendarID does not exist!";
		}else {
			
			List<MapPhotoCalendarModel> mapPhotoCalendarModels = photoCalendar.getMapPhotoCalendarModels();
			List<MapPhotoCalendarModel> oldmapPhotoCalendarModels = mapPhotoCalendarModelRepository.findByCalendarID(photoCalendar.getCalendarID());
			
			for(MapPhotoCalendarModel mapPhotoCalendarModel : mapPhotoCalendarModels) {
				mapPhotoCalendarModel.setCalendarID(photoCalendar.getCalendarID());
				maxUnitPerDaySum = maxUnitPerDaySum.add(mapPhotoCalendarModel.getAvailableUnit());
				if(mapPhotoCalendarModel.getModelScheduleID() == null) {
					//add
					mapPhotoCalendarModelRepository.save(mapPhotoCalendarModel);
				}else {
					for(MapPhotoCalendarModel oldMapPhotoCalendarModel : oldmapPhotoCalendarModels) {
						//update
						if(oldMapPhotoCalendarModel.getModelScheduleID().intValue() == mapPhotoCalendarModel.getModelScheduleID().intValue()) {
							jdbcTemplatePhotoStudio.update(mapPhotoCalendarModel.toUpdateQuery(""));
							oldmapPhotoCalendarModels.remove(oldMapPhotoCalendarModel);
							break;
						}
					}
				}
			}
			
			//update MaxUnitPerDay 
			photoCalendar.setMaxUnitPerDay(maxUnitPerDaySum);
			jdbcTemplatePhotoStudio.update(photoCalendar.toUpdateQuery(""));
			
			//delete
			if(oldmapPhotoCalendarModels.size() > 0) {
				for(MapPhotoCalendarModel oldMapPhotoCalendarModel : oldmapPhotoCalendarModels) {
					jdbcTemplatePhotoStudio.update(oldMapPhotoCalendarModel.toDeleteQuery());
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
		if(photoCalendar.getCalendarID() == null) {
			return "CalendarID does not exist!";
		}
		
		String sql = photoCalendar.toUpdateQuery("");
		jdbcTemplatePhotoStudio.update(sql);
		
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
		params.add(queryParam.getOnTime());
		params.add(queryParam.getNotCancelled());
		params.add(queryParam.getCancelledByFG());
		params.add(queryParam.getCancelledByVendor());

		List<Object> _results = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetOrderList", params, SingleValueResult.class, SimplePhotoOrder.class);
		
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

		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetOrderDetail", params, DetailPhotoOrder.class, LogPhotoAction.class, PhotoOrderDetail.class, PhotoActionUser.class);

		List<DetailPhotoOrder> photoOrders = (List<DetailPhotoOrder>) r.get(0);
		List<LogPhotoAction> logPhotoActions = (List<LogPhotoAction>) r.get(1);
		List<PhotoOrderDetail> photoOrderDetails = (List<PhotoOrderDetail>) r.get(2);
		List<PhotoActionUser> photoActionUsers = (List<PhotoActionUser>) r.get(3);
		
		result.put("photoOrder", photoOrders.get(0));
		result.put("actionLogs", logPhotoActions);
		result.put("items", photoOrderDetails);
		result.put("photoStudioUsers", photoActionUsers);

		return result;
	}
	
	public List<PhotoModel> getAvailableModels(Integer orderID, String theDate) {
		Map<String, Object> result = new HashMap<String, Object> ();
		List<Object> params = new ArrayList<Object>();
		params.add(orderID);
		params.add(theDate);
		
		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetAvailableModels", params, PhotoModel.class);

		List<PhotoModel> photoModels = (List<PhotoModel>) r.get(0);
		Map<String, List<PhotoImage>> imagesMap = new HashMap<String, List<PhotoImage>>(); //key: calendarID + ModelID
		Map<Integer, List<String>> nextAvailableMap = new HashMap<Integer, List<String>>(); //key: ModelID
		
		List<PhotoModel> models = new ArrayList<PhotoModel>();
		
		for (PhotoModel photoModel : photoModels) {
			String imageKey = String.valueOf(photoModel.getCalendarID()) + String.valueOf(photoModel.getModelID());
			List<PhotoImage> imageList = imagesMap.get(imageKey);
			if(imageList != null) {
				PhotoImage photoImage = new PhotoImage();
				photoImage.setImageUrl(photoModel.getImageUrl());
				photoImage.setListOrder(photoModel.getListOrder());
				imageList.add(photoImage);
				imagesMap.put(imageKey, imageList);
			}else {
				imageList = new ArrayList<PhotoImage>();
				PhotoImage photoImage = new PhotoImage();
				photoImage.setImageUrl(photoModel.getImageUrl());
				photoImage.setListOrder(photoModel.getListOrder());
				imageList.add(photoImage);
				imagesMap.put(imageKey, imageList);
				
				if(photoModel.getIsToday().intValue() == 0 ) {
					//Today's models
					models.add(photoModel);
				}else if(photoModel.getIsToday().intValue() == 1) {
					//Today's models next Available
					List<String> nextAvailableList = nextAvailableMap.get(photoModel.getModelID());
					if(nextAvailableList == null) {
						nextAvailableList = new ArrayList<String>();
					}
					nextAvailableList.add(photoModel.getTheDate());
					nextAvailableMap.put(photoModel.getModelID(), nextAvailableList);
					
				}
			}
		}
		
		for(PhotoModel photoModel : models) {
			photoModel.setPhotoImages(imagesMap.get(String.valueOf(photoModel.getCalendarID()) + String.valueOf(photoModel.getModelID())));
			photoModel.setNextAvailableDates(nextAvailableMap.get(photoModel.getModelID()));
		}

		return models;
	}
	
	public String updatePhotoOrder(PhotoOrder photoOrder) {
		List<Object> params = new ArrayList<Object>();
		if(photoOrder.getOrderID() == null) {
			return "OrderID does not exist!";
		}
		
		params.add(photoOrder.getOrderID());
		params.add(photoOrder.getPhotoshootDateTime());
		params.add(photoOrder.getModelID());
		params.add(photoOrder.getAdditionalDiscountAmount());
		params.add(photoOrder.getInHouseNote());
		params.add(Utility.getUsername());

		List<Object> outputparams = new ArrayList<Object>();
		outputparams.add(0);
		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_UpdateOrder", params);
		
		List<Object> outputs= (List<Object>) r.get(0);
		if(outputs != null && outputs.size()>0) {
			return outputs.get(0) == null ? null : String.valueOf(outputs.get(0));
		}

		return null;
	}
	
	public String cancelPhotoOrder(PhotoOrder photoOrder) {
		List<Object> params = new ArrayList<Object>();
		if(photoOrder.getOrderID() == null) {
			return "OrderID does not exist!";
		}
		
		params.add(photoOrder.getOrderID());
		params.add(photoOrder.getCancelNote());
		params.add(Utility.getUsername());
		params.add(photoOrder.getCancellationFeeRate());

		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_CancelOrder", params);

		return null;
	}
	
	public DailySummaryVo getDailySummary(String photoshootDate) {
		List<Object> params = new ArrayList<Object>();
		params.add(photoshootDate);

		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetDailySummary", params, DailySummaryVo.class);
		List<DailySummaryVo> dailySummaryVos = (List<DailySummaryVo>) r.get(0);

		return dailySummaryVos.get(0);
	}
	
	public List<LogPhotoAction> getActionLog(Integer orderId, Integer actionType) {
		List<Object> params = new ArrayList<Object>();
		params.add(null);//ActionID
		params.add(actionType);//ActionType
		params.add(orderId);//OrderID
		params.add(null);//CreatedOn
		params.add(null);//CreatedBy

		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetActionLog", params, LogPhotoAction.class);

		List<LogPhotoAction> logPhotoActions = (List<LogPhotoAction>) r.get(0);

		return logPhotoActions;
	}
	
	public Integer saveActionLog(LogPhotoAction logPhotoAction) {
		
		List<Object> params = new ArrayList<Object>();
		params.add(logPhotoAction.getActionType());
		params.add(logPhotoAction.getOrderID());
		params.add(logPhotoAction.getItemQty());
		params.add(logPhotoAction.getDroppedBy());
		params.add(Utility.getUsername());
		
		List<Object> outputparams = new ArrayList<Object>();
		outputparams.add(0);
		List<Object> result = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_SaveActionLog", params, outputparams);
		
		List<Object> outputs= (List<Object>) result.get(0);
		Integer orderStatusID = outputs.get(0) == null || Integer.parseInt(String.valueOf(outputs.get(0))) == 0 ? null : Integer.parseInt(String.valueOf(outputs.get(0)));
		
		return orderStatusID;
	}

	
	public Map<String, Object> getReports(Map<String, Object> parmMap) {
		
		int categoryID = Integer.parseInt(String.valueOf(parmMap.get("categoryID")));;
		
		Map<String, Object> result = new HashMap<String, Object> ();
		List<Object> params = new ArrayList<Object>();
		
		params.add(parmMap.get("year"));
		params.add(parmMap.get("month"));
		params.add(categoryID);

		if(categoryID == 5) {
			List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetReports", params, CommonReportsVo.class, CommonReportsVo.class);
			
			List<CommonReportsVo> dailyData  = (List<CommonReportsVo>) r.get(0);
			List<CommonReportsVo> monthSummary = (List<CommonReportsVo>) r.get(1);
			
			result.put("dailyData", dailyData);
			result.put("monthSummary", monthSummary);

		}else {
			List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetReports", params, CommonReportsVo.class, CommonReportsVo.class, CommonReportsVo.class);
			List<CommonReportsVo> dailyVendorData  = (List<CommonReportsVo>) r.get(0);
			List<CommonReportsVo> dailyData = (List<CommonReportsVo>) r.get(1);
			List<CommonReportsVo> monthSummary = (List<CommonReportsVo>) r.get(2);
			
			result.put("dailyVendorData", dailyVendorData);
			result.put("dailyData", dailyData);
			result.put("monthSummary", monthSummary);
		}
		
		return result;
	}
	
	public Integer saveCredit(PhotoCredit photoCredit) {
		LocalDateTime date = LocalDateTime.now();
		String username = Utility.getUsername();

		photoCredit.setCreatedBy(username);
		photoCredit.setCreatedOnDate(date);
		photoCreditRepository.save(photoCredit);
		
		return photoCredit.getPhotoCreditID();
	}
	
	@SuppressWarnings("unchecked")
	public BigDecimal getCreditBalance(Integer wholeSalerID) {
		List<Object> params = new ArrayList<Object>();
		params.add(wholeSalerID);
		
		List<Object> _results = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetCreditBalance", params, SingleValueResult.class, PhotoCredit.class);
		
		List<SingleValueResult> rs1 = (List<SingleValueResult>)_results.get(0);
		List<PhotoCredit> rs2 = (List<PhotoCredit>)_results.get(1);

		BigDecimal photoCreditBalance = BigDecimal.ZERO;
		if(rs2.size() > 0 && rs2.get(0) != null) {
			photoCreditBalance = rs2.get(0).getPhotoCreditBalance();
		}
		
		return photoCreditBalance;
	}
	
	@SuppressWarnings("unchecked")
	public PagedResult<PhotoCredit> getCredits(QueryParam queryParam) {
		PagedResult<PhotoCredit> result = new PagedResult<PhotoCredit>();

		List<Object> params = new ArrayList<Object>();
		params.add(queryParam.getPn());
		params.add(queryParam.getPs());
		
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
		
		List<Object> _results = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetCreditList", params, SingleValueResult.class, PhotoCredit.class);
		
		List<SingleValueResult> rs1 = (List<SingleValueResult>)_results.get(0);
		List<PhotoCredit> rs2 = (List<PhotoCredit>)_results.get(1);

		result.setTotal(rs1.get(0));
		result.setRecords(rs2);
		
		return result;
	}
	
	public List<PhotoCredit> getCreditHistory(Integer wholeSalerID) {
		List<Object> params = new ArrayList<Object>();
		params.add(wholeSalerID);

		List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetCreditDetail", params, PhotoCredit.class);
		List<PhotoCredit> photoCredits  = (List<PhotoCredit>) r.get(0);
		
		return photoCredits;
	}
	
	public boolean deleteCredit(Integer photoCreditID) {
		boolean bSuccess = false;
		PhotoCredit photoCredit = new PhotoCredit();
		photoCredit.setPhotoCreditID(photoCreditID);
		String sql = photoCredit.toDeleteQuery();
		jdbcTemplatePhotoStudio.update(sql);
		bSuccess = true;

		return bSuccess;
	}
}
