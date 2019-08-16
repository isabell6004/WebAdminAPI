package net.fashiongo.webadmin.service;

import com.querydsl.core.QueryResults;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.dao.photostudio.*;
import net.fashiongo.webadmin.exception.NotEnoughPhotostudioAvailableUnit;
import net.fashiongo.webadmin.exception.NotFoundPhotostudioDiscount;
import net.fashiongo.webadmin.exception.NotFoundPhotostudioPhotoModel;
import net.fashiongo.webadmin.model.photostudio.*;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.SingleValueResult;
import net.fashiongo.webadmin.model.pojo.payment.parameter.QueryParam;
import net.fashiongo.webadmin.model.primary.SecurityUser;
import net.fashiongo.webadmin.model.primary.Vendor;
import net.fashiongo.webadmin.service.photostudio.ReportTypeChecker;
import net.fashiongo.webadmin.utility.DateUtils;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    @Autowired
    private PhotoBookingRepository photoBookingRepository;

    @Autowired
    private PhotoCartRepository photoCartRepository;

    @Autowired
    private PhotoOrderRepository photoOrderRepository;

    @Autowired
    private PhotoOrderEntityRepository photoOrderEntityRepository;

    @Autowired
    private PhotoBannerClickRepository photoBannerClickRepository;

    @Autowired
    private PhotoDropperRepository photoDropperRepository;

    @Autowired
    @Qualifier("photostudioTransactionManager")
    private PlatformTransactionManager transactionManager;

    public static final int IMAGE_MAPPING_TYPE_MODEL = 3;

    public List<PhotoCategory> getCategories() {
        return photoCategoryRepository.findAll();
    }

    public List<PhotoDiscount> getDiscounts() {
        Sort sort = new Sort(Direction.DESC, "DiscountID");
        return photoDiscountRepository.findAll(sort);
    }

    public String saveDiscount(PhotoDiscount photoDiscount) throws IllegalArgumentException, IllegalAccessException {

        if (photoDiscount.getActive() == null) {
            return "Status is required.";
        }

        LocalDateTime date = LocalDateTime.now();
        String username = Utility.getUsername();
        if (photoDiscount.getDiscountID() == null) {

            if (photoDiscount.getDiscountName() == null) {
                return "Title is required.";
            }

            if (photoDiscount.getDiscountCode() == null) {
                return "Discount Code is required.";
            }

            if (photoDiscount.getIsFirstTimeOrder() == null) {
                return "First Time Order Only is required.";
            }

            if (photoDiscount.getIsRequiredPriceQty() == null
                    || (photoDiscount.getIsRequiredPriceQty() != null && Boolean.TRUE.equals(photoDiscount.getIsRequiredPriceQty())
                    && photoDiscount.getRequiredPrice() == null && photoDiscount.getRequiredQty() == null)) {
                return "Required Price or Required Qty is required.";
            }

            if (photoDiscount.getDiscountAmount() == null && photoDiscount.getDiscountRate() == null) {
                return "Discount Amount is required.";
            }

            if (photoDiscountRepository.existsByDiscountCode(photoDiscount.getDiscountCode())) {
                return "This Discount code [" + photoDiscount.getDiscountCode() + "] is already used.";
            }

            photoDiscount.setCreatedBy(username);
            photoDiscount.setCreatedOnDate(date);
            photoDiscountRepository.save(photoDiscount);
        } else {
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

    public PhotoPriceListResponse getPhotoPrices() {

    	LocalDateTime now = LocalDateTime.now();

		List<PhotoPrice> currentPhotoPrices = photoPriceRepository.findAllCurrentEffectivePrice(now);
		List<PhotoPrice> newPhotoPrices = photoPriceRepository.findAllToBeEffectivePrice(now);

		if (newPhotoPrices.size() == 0) {
			for (PhotoPrice currentPhotoPrice : currentPhotoPrices) {
				PhotoPrice newPhotoPrice = new PhotoPrice();
				newPhotoPrice.setPriceTypeID(currentPhotoPrice.getPriceTypeID());
				newPhotoPrice.setPriceTypeName(currentPhotoPrice.getPriceTypeName());
				newPhotoPrice.setPhotoshootType(currentPhotoPrice.getPhotoshootType());
				newPhotoPrice.setPhotoShootTypeName(currentPhotoPrice.getPhotoShootTypeName());
				newPhotoPrice.setPhotoPackage(currentPhotoPrice.getPhotoPackage());
				newPhotoPrices.add(newPhotoPrice);
			}
		}

		PhotoPriceListResponse listResponse = new PhotoPriceListResponse();
		listResponse.setCurrentPrices(currentPhotoPrices.stream()
				.map(PhotoPriceResponse::of)
				.collect(Collectors.toList()));
		listResponse.setNewPrices(newPhotoPrices.stream()
				.map(PhotoPriceResponse::of)
				.collect(Collectors.toList()));

        return listResponse;
    }

    @Transactional
    public String savePrices(Map<String, List<PhotoPrice>> parmMap) throws IllegalArgumentException, IllegalAccessException {
        log.info(Boolean.toString(TransactionSynchronizationManager.isActualTransactionActive()));

        String Msg = null;
        LocalDateTime now = LocalDateTime.now();
        String username = Utility.getUsername();

        List<PhotoPrice> currentPrices = photoPriceRepository.findAllCurrentEffectivePrice(now);
        List<PhotoPrice> newPrices = parmMap.get("newPrices");

        LocalDateTime currentFromEffectiveDate = currentPrices.get(0).get_fromEffectiveDate();
        LocalDateTime newFromEffectiveDate = newPrices.get(0).get_fromEffectiveDate();

        if (!newFromEffectiveDate.isAfter(now)) {
            return "The new effective date must after today!";
        }

        LocalDateTime currentToEffectiveDate = newFromEffectiveDate.minusSeconds(1);
        if (currentToEffectiveDate.isBefore(currentFromEffectiveDate)) {
            return "The new effective date can not be equal or less than current effective date!";
        }

        for (PhotoPrice currentPhotoPrice : currentPrices) {
            currentPhotoPrice.set_toEffectiveDate(currentToEffectiveDate);
            currentPhotoPrice.setModifiedBY(username);
            currentPhotoPrice.setModifiedOnDate(now);
        }

        photoPriceRepository.saveAll(currentPrices);

        if (newPrices.get(0).getPriceID() == null) {
            // save new prices
            for (PhotoPrice newPhotoPrice : newPrices) {
                newPhotoPrice.setCreatedOnDate(now);
                newPhotoPrice.setCreatedBy(username);
            }
            photoPriceRepository.saveAll(newPrices);

            // save new mapPhotoImages
//            List<MapPhotoImage> mapPhotoImages = mapPhotoImageRepository.findPriceImagesByPriceIds(currentPrices.stream()
//                    .map(PhotoPrice::getPriceID)
//                    .collect(Collectors.toList()));
//
//            for (MapPhotoImage mapPhotoImage : mapPhotoImages) {
//                mapPhotoImage.setImageID(null);
//
//                Integer newReferenceId = currentPrices.stream()
//                        .filter(price -> price.getPriceID().equals(mapPhotoImage.getReferenceID()))
//                        .findFirst()
//                        .map(currentPrice -> newPrices.stream()
//                                    .filter(newPrice -> newPrice.getPackageId().equals(currentPrice.getPackageId()) && newPrice.getPriceTypeID().equals(currentPrice.getPriceTypeID()))
//                                    .findFirst()
//                                    .orElse(null))
//                        .map(PhotoPrice::getPriceID)
//                        .orElse(null);
//
//                if(newReferenceId == null) {
//                    return "Unknown Error";
//                }
//
//                mapPhotoImage.setReferenceID(newReferenceId);
//            }
//
//            mapPhotoImageRepository.saveAll(mapPhotoImages);
        } else {
            for (PhotoPrice newPhotoPrice : newPrices) {
                newPhotoPrice.setModifiedOnDate(now);
                newPhotoPrice.setModifiedBY(username);
                if (newPhotoPrice.getPriceID() == null) {
                    return "The new price does not exist , can't update ！ ";
                }
                String sql = newPhotoPrice.toUpdateQuery("");
                jdbcTemplatePhotoStudio.update(sql);
            }
        }

        return Msg;
    }

    public Map<String, Object> getCancellationfees() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Object> params = new ArrayList<Object>();

        List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetCancellationFees", params, PhotoCancellationFee.class, PhotoCancellationFee.class);

        List<PhotoCancellationFee> currentCancellationFees = (List<PhotoCancellationFee>) r.get(0);
        List<PhotoCancellationFee> newCancellationFees = (List<PhotoCancellationFee>) r.get(1);

        if (newCancellationFees == null || newCancellationFees.size() == 0) {
            for (PhotoCancellationFee currentCancellationFee : currentCancellationFees) {
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
            if (currentPhotoCancellationFee.getCancelTypeID() == null) {
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
                if (newPhotoCancellationFee.getCancelTypeID() == null) {
                    return "The new cancellationFee does not exist , can't update ！ ";
                }
                String sql = newPhotoCancellationFee.toUpdateQuery("");
                jdbcTemplatePhotoStudio.update(sql);
            }
        }

        return Msg;
    }

    public PhotoUnitListResponse getPhotoUnits() {
        LocalDateTime now = LocalDateTime.now();

        List<PhotoUnit> currentPhotoUnits = photoUnitRepository.findAllCurrentEffectiveUnit(now);
        List<PhotoUnit> newPhotoUnits = photoUnitRepository.findAllToBeEffectiveUnit(now);

        if (newPhotoUnits.size() == 0) {
            for (PhotoUnit currentPhotoUnit : currentPhotoUnits) {
                PhotoUnit newPhotoUnit = new PhotoUnit();
                newPhotoUnit.setPriceTypeID(currentPhotoUnit.getPriceTypeID());
                newPhotoUnit.setPriceTypeName(currentPhotoUnit.getPriceTypeName());
                newPhotoUnit.setPhotoshootType(currentPhotoUnit.getPhotoshootType());
                newPhotoUnit.setPhotoShootTypeName(currentPhotoUnit.getPhotoShootTypeName());
                newPhotoUnit.setPhotoPackage(currentPhotoUnit.getPhotoPackage());
                newPhotoUnits.add(newPhotoUnit);
            }
        }

        PhotoUnitListResponse listResponse = new PhotoUnitListResponse();
        listResponse.setCurrentPhotoUnits(currentPhotoUnits.stream()
                .map(PhotoUnitResponse::of)
                .collect(Collectors.toList()));
        listResponse.setNewPhotoUnits(newPhotoUnits.stream()
                .map(PhotoUnitResponse::of)
                .collect(Collectors.toList()));

        return listResponse;
    }

    @Transactional
    public String savePhotoUnits(Map<String, List<PhotoUnit>> parmMap)
            throws IllegalArgumentException, IllegalAccessException {

        LocalDateTime now = LocalDateTime.now();
        String username = Utility.getUsername();

        List<PhotoUnit> currentPhotoUnits = photoUnitRepository.findAllCurrentEffectiveUnit(now);
        List<PhotoUnit> newPhotoUnits = parmMap.get("newPhotoUnits");

        LocalDateTime currentFromEffectiveDate = currentPhotoUnits.get(0).get_fromEffectiveDate();
        LocalDateTime newFromEffectiveDate = newPhotoUnits.get(0).get_fromEffectiveDate();

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
            if (currentPhotoUnit.getUnitID() == null) {
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
                if (newPhotoUnit.getUnitID() == null) {
                    return "The new unit does not exist , can't update ！ ";
                }
                String sql = newPhotoUnit.toUpdateQuery("");
                jdbcTemplatePhotoStudio.update(sql);
            }
        }

        return null;
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
            if (photoImages.size() > 0) {
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
            if (photoImages.size() > 0) {
                List<MapPhotoImage> mapPhotoImages = new ArrayList<MapPhotoImage>();
                for (PhotoImage photoImage : photoImages) {
                    //add new image
                    if (photoImage.getImageID() == null) {
                        photoImage.setCreatedBy(username);
                        photoImage.setCreatedOnDate(date);
                        photoImageRepository.save(photoImage);
                        MapPhotoImage mapPhotoImage = new MapPhotoImage();
                        mapPhotoImage.setMappingType(IMAGE_MAPPING_TYPE_MODEL);
                        mapPhotoImage.setImageID(photoImage.getImageID());
                        mapPhotoImage.setReferenceID(photoModel.getModelID());
                        mapPhotoImage.setListOrder(photoImage.getListOrder());
                        mapPhotoImages.add(mapPhotoImage);
                    } else {
                        for (MapPhotoImage oldMapPhotoImage : oldMapPhotoImages) {
                            //do update
                            if (photoImage.getImageID().intValue() == oldMapPhotoImage.getImageID().intValue()) {
                                oldMapPhotoImage.setListOrder(photoImage.getListOrder());
                                jdbcTemplatePhotoStudio.update(oldMapPhotoImage.toUpdateQuery(""));
                                oldMapPhotoImages.remove(oldMapPhotoImage);
                                break;
                            }
                        }
                    }
                }

                if (mapPhotoImages.size() > 0) {
                    mapPhotoImageRepository.saveAll(mapPhotoImages);
                }
            }
            //delete
            if (oldMapPhotoImages.size() > 0) {
                for (MapPhotoImage deleteMapPhotoImage : oldMapPhotoImages) {
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
        if (!StringUtils.isEmpty(dType) && (df != null || dt != null)) {
            params.add(dType);
            params.add(df);
            params.add(dt);
        } else {
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
        List<SingleValueResult> rs1 = (List<SingleValueResult>) _results.get(0);
        List<PhotoModel> rs2 = (List<PhotoModel>) _results.get(1);

        result.setTotal(rs1.get(0));
        result.setRecords(rs2);

        return result;
    }

    public PhotoModel getModel(Integer modelID) {
        PhotoModel model = updateModelPopularity(modelID);

        List<PhotoImage> photoImageList = mapPhotoImageRepository.findModelImageByModelId(modelID).stream()
                .map(mapPhotoImage -> {
                    PhotoImage photoImage = mapPhotoImage.getPhotoImage();
                    photoImage.setListOrder(mapPhotoImage.getListOrder());
                    return photoImage;
                })
                .collect(Collectors.toList());

        model.setPhotoImages(photoImageList);

        return model;
    }

    private PhotoModel updateModelPopularity(int modelId) {
        PhotoModel model = photoModelRepository.findOneByModelID(modelId);
        List<MapPhotoCalendarModel> maps = mapPhotoCalendarModelRepository.findByModelIdWithBooking(modelId);

        long totalOrderCount = maps.stream()
                .map(MapPhotoCalendarModel::getPhotoBooking)
                .mapToLong(List::size)
                .sum();
        long orderDayCount = maps.size();

        BigDecimal popularity = orderDayCount == 0 ? BigDecimal.ZERO
                : BigDecimal.valueOf(totalOrderCount).divide(BigDecimal.valueOf(orderDayCount), 2, RoundingMode.HALF_UP);
        model.setPopularity(popularity);

        return photoModelRepository.save(model);
    }

    @SuppressWarnings("unchecked")
    public String deleteModel(Integer modelID) {

        List<Object> params = new ArrayList<Object>();
        params.add(modelID);
        params.add(Utility.getUsername());

        List<Object> outputparams = new ArrayList<Object>();
        outputparams.add("");
        List<Object> result = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_DeleteModel", params, outputparams);

        List<Object> outputs = (List<Object>) result.get(0);

        return outputs.get(0) == null ? null : String.valueOf(outputs.get(0));
    }

    public List<PhotoCalendarResponse> getPhotoCalendar(Map<String, String> parmMap) {
        LocalDateTime now = LocalDateTime.now();

        int year = Optional.ofNullable(parmMap.get("year"))
                .map(Integer::parseInt)
                .orElse(now.getYear());
        int month = Optional.ofNullable(parmMap.get("month"))
                .map(Integer::parseInt)
                .orElse(now.getMonthValue());
        Integer modelId = Optional.ofNullable(parmMap.get("modelID"))
                .map(Integer::parseInt)
                .orElse(null);

        PhotoModel photoModel = (modelId == null) ? null : Optional.ofNullable(photoModelRepository.findOneByModelID(modelId))
                .orElseThrow(NotFoundPhotostudioPhotoModel::new);

        LocalDateTime inputDate = LocalDateTime.of(year, month, 1, 0, 0);

        List<PhotoCalendarEntity> photoCalendarEntityList = photoCalendarRepository.getPhotoCalendarWithJoinDate(inputDate, inputDate.with(TemporalAdjusters.lastDayOfMonth()));
        List<PhotoCalendarResponse> photoCalendarResponseList = photoCalendarEntityList.stream()
                .map(photoCalendarEntity -> {
                    PhotoCalendarResponse photoCalendarResponse = new PhotoCalendarResponse();

                    photoCalendarResponse.setAvailable(photoCalendarEntity.getAvailable());
                    photoCalendarResponse.setAvailableUnits(photoCalendarEntity.getMapPhotoCalendarModel().stream()
                            .filter(mapPhotoCalendarModel -> mapPhotoCalendarModel.getIsDelete() == null || mapPhotoCalendarModel.getIsDelete().equals(false))
                            .map(mapPhotoCalendarModel -> mapPhotoCalendarModel.getAvailableUnit().subtract(mapPhotoCalendarModel.getPhotoBooking().stream()
                                    .filter(photoBooking -> photoBooking.getPhotoOrder().getIsCancelledBy() == null || photoBooking.getPhotoOrder().getIsCancelledBy().equals(0))
                                    .map(PhotoBooking::getBookedUnit)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add)))
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            .doubleValue());
                    photoCalendarResponse.setCalendarID(photoCalendarEntity.getCalendarID());
                    photoCalendarResponse.setDateName(photoCalendarEntity.getDateName());
                    photoCalendarResponse.setIsDelayed(photoCalendarEntity.getMapPhotoCalendarModel().stream()
                            .filter(mapPhotoCalendarModel -> mapPhotoCalendarModel.getIsDelete() == null || mapPhotoCalendarModel.getIsDelete().equals(false))
                            .anyMatch(mapPhotoCalendarModel -> mapPhotoCalendarModel.getPhotoBooking().stream()
                                    .filter(photoBooking -> photoBooking.getPhotoOrder().getIsCancelledBy() == null || photoBooking.getPhotoOrder().getIsCancelledBy().equals(0))
                                    .map(PhotoBooking::getPhotoOrder)
                                    .anyMatch(photoOrder -> (photoOrder.getOrderStatusID() == 1 && photoOrder.get_dropOffDate().isBefore(now))
                                            || (photoOrder.getOrderStatusID() == 2 && photoOrder.get_prepDate().isBefore(now))
                                            || (photoOrder.getOrderStatusID() == 3 && photoOrder.get_photoshootDate().isBefore(now))
                                            || (photoOrder.getOrderStatusID() == 4 && photoOrder.get_retouchDate().isBefore(now))
                                            || (photoOrder.getOrderStatusID() == 5 && photoOrder.get_uploadDate().isBefore(now)))));
                    photoCalendarResponse.setBookedCnt(photoCalendarEntity.getMapPhotoCalendarModel().stream()
                            .filter(mapPhotoCalendarModel -> mapPhotoCalendarModel.getIsDelete() == null || mapPhotoCalendarModel.getIsDelete().equals(false))
                            .filter(mapPhotoCalendarModel -> modelId == null || (mapPhotoCalendarModel.getModelID() != null && mapPhotoCalendarModel.getModelID().equals(modelId)))
                            .map(mapPhotoCalendarModel -> mapPhotoCalendarModel.getPhotoBooking().stream()
                                    .filter(photoBooking -> photoBooking.getPhotoOrder().getIsCancelledBy() == null || photoBooking.getPhotoOrder().getIsCancelledBy().equals(0))
                                    .count())
                            .reduce(Long::sum)
                            .map(Long::intValue)
                            .orElse(-1));
                    photoCalendarResponse.setIsHoliday(photoCalendarEntity.getIsHoliday());
                    photoCalendarResponse.setIsModelShot(photoCalendarEntity.getIsModelShot());
                    photoCalendarResponse.setMaxUnitPerDay(photoCalendarEntity.getMapPhotoCalendarModel().stream()
                            .filter(mapPhotoCalendarModel -> mapPhotoCalendarModel.getIsDelete() == null || mapPhotoCalendarModel.getIsDelete().equals(false))
                            .map(MapPhotoCalendarModel::getAvailableUnit)
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            .doubleValue());
                    photoCalendarResponse.setTheDate(photoCalendarEntity.getTheDate().toLocalDate().toString());

                    return photoCalendarResponse;
                })
                .collect(Collectors.toList());

        return photoCalendarResponseList;
    }

    public ModelsOrdersResponse getPhotoCalendarModelsOrders(Integer calendarId, Integer modelId) {
        ModelsOrdersResponse.ModelsOrdersResponseBuilder responseBuilder = ModelsOrdersResponse.builder();

        if(modelId == null) {
            // models
            List<MapPhotoCalendarModel> maps = mapPhotoCalendarModelRepository.findWithModelAndBookingByCalendarId(calendarId);
            List<ModelUnit> modelUnits = maps.stream()
                    .map(ModelUnit::of)
                    .collect(Collectors.toList());

            // modelsOption
            List<PhotoModel> models = photoModelRepository.findAllByIsDeletedOrderByModelNameAsc(false);

            List<Integer> modelIds = models.stream()
                    .map(PhotoModel::getModelID)
                    .collect(Collectors.toList());
            List<MapPhotoImage> firstImageMaps = getModelsFirstImageMaps(modelIds);
            List<PhotoImage> firstImages = getModelsFirstImages(firstImageMaps);

            List<ModelOption> modelsOption = models.stream()
                    .map(model -> ModelOption.builder()
                            .imageUrl(getFirstImageUrl(model.getModelID(), firstImageMaps, firstImages))
                            .isBooked(modelUnits.stream()
                                    .filter(modelUnit -> modelUnit.getModelId() != null)
                                    .filter(modelUnit -> modelUnit.getModelId().equals(model.getModelID()))
                                    .anyMatch(modelUnit -> modelUnit.getBookedUnit() != null && modelUnit.getBookedUnit().doubleValue() > 0))
                            .modelId(model.getModelID())
                            .modelName(model.getModelName())
                            .build())
                    .collect(Collectors.toList());

            responseBuilder
                    .models(modelUnits)
                    .modelsOption(modelsOption);
        }

        // orders
        List<PhotoOrderEntity> photoOrders = photoOrderEntityRepository.getValidOrderWithModelByCalendarIdAndModelId(calendarId, modelId);
        List<SimplePhotoOrder> orders = SimplePhotoOrder.makeOrders(photoOrders);

        return responseBuilder
                .orders(orders)
                .isEditable(isEditable(calendarId, 2))
                .build();
    }

    @Transactional
    public String saveCalendar(PhotoCalendar photoCalendar) throws IllegalArgumentException, IllegalAccessException {

        String Msg = null;
        BigDecimal maxUnitPerDaySum = BigDecimal.ZERO;
        if (photoCalendar.getCalendarID() == null) {
            return "CalendarID does not exist!";
        } else {

            List<MapPhotoCalendarModel> mapPhotoCalendarModels = photoCalendar.getMapPhotoCalendarModels();
            List<MapPhotoCalendarModel> oldmapPhotoCalendarModels = mapPhotoCalendarModelRepository.findByCalendarID(photoCalendar.getCalendarID());

            List<MapPhotoCalendarModel> addMapPhotoCalendarModels = new ArrayList<MapPhotoCalendarModel>();
            List<MapPhotoCalendarModel> updateMapPhotoCalendarModels = new ArrayList<MapPhotoCalendarModel>();

            for (MapPhotoCalendarModel mapPhotoCalendarModel : mapPhotoCalendarModels) {
                mapPhotoCalendarModel.setCalendarID(photoCalendar.getCalendarID());
                maxUnitPerDaySum = maxUnitPerDaySum.add(mapPhotoCalendarModel.getAvailableUnit());
                if (mapPhotoCalendarModel.getModelScheduleID() == null) {
                    //add
                    addMapPhotoCalendarModels.add(mapPhotoCalendarModel);
                    //mapPhotoCalendarModelRepository.save(mapPhotoCalendarModel);
                } else {
                    for (MapPhotoCalendarModel oldMapPhotoCalendarModel : oldmapPhotoCalendarModels) {
                        //update
                        if (oldMapPhotoCalendarModel.getModelScheduleID().intValue() == mapPhotoCalendarModel.getModelScheduleID().intValue()) {
                            //jdbcTemplatePhotoStudio.update(mapPhotoCalendarModel.toUpdateQuery(""));
                            updateMapPhotoCalendarModels.add(mapPhotoCalendarModel);
                            oldmapPhotoCalendarModels.remove(oldMapPhotoCalendarModel);
                            break;
                        }
                    }
                }
            }

            if (photoCalendar.getIsFromModelDetail() == null || photoCalendar.getIsFromModelDetail().equals(Boolean.FALSE)) {
                //booked info check for delete
                for (MapPhotoCalendarModel oldMapPhotoCalendarModel : oldmapPhotoCalendarModels) {
                    List<PhotoBooking> photoBookings = photoBookingRepository.findByModelScheduleID(oldMapPhotoCalendarModel.getModelScheduleID());
                    for (PhotoBooking photoBooking : photoBookings) {
                        if (photoBooking.getBookedUnit().compareTo(BigDecimal.ZERO) > 0) {
                            PhotoModel photoModel = photoModelRepository.findOneByModelID(oldMapPhotoCalendarModel.getModelID());
                            return "The schedule of model (" + photoModel.getModelName() + ") is can't be deleted or changed for booked.";
                        }
                    }
                }

                //do delete
                for (MapPhotoCalendarModel oldMapPhotoCalendarModel : oldmapPhotoCalendarModels) {
                    oldMapPhotoCalendarModel.setAvailableUnit(BigDecimal.ZERO);
                    oldMapPhotoCalendarModel.setIsDelete(true);
                    jdbcTemplatePhotoStudio.update(oldMapPhotoCalendarModel.toUpdateQuery(""));
                }
            }

            //do add
            for (MapPhotoCalendarModel addMapPhotoCalendarModel : addMapPhotoCalendarModels) {
                mapPhotoCalendarModelRepository.save(addMapPhotoCalendarModel);
            }

            //do update
            for (MapPhotoCalendarModel updateMapPhotoCalendarModel : updateMapPhotoCalendarModels) {
                jdbcTemplatePhotoStudio.update(updateMapPhotoCalendarModel.toUpdateQuery(""));
            }

            //update MaxUnitPerDay
            photoCalendar.setMaxUnitPerDay(maxUnitPerDaySum);
            jdbcTemplatePhotoStudio.update(photoCalendar.toUpdateQuery(""));

        }

        return Msg;
    }

    public String calendarAvailable(PhotoCalendar photoCalendar) throws IllegalArgumentException, IllegalAccessException {

        String Msg = null;

        LocalDateTime now = LocalDateTime.now();
        String username = Utility.getUsername();

        photoCalendar.setModifiedOnDate(now);
        photoCalendar.setModifiedBY(username);
        if (photoCalendar.getCalendarID() == null) {
            return "CalendarID does not exist!";
        }

        String sql = photoCalendar.toUpdateQuery("");
        jdbcTemplatePhotoStudio.update(sql);

        return Msg;
    }

    @SuppressWarnings("unchecked")
    public PagedResult<PhotoOrderResponse> getPhotoOrders(OrderQueryParam queryParam) {
        QueryResults<PhotoOrderEntity> orderListResult = photoOrderEntityRepository.getPhotoOrderList(queryParam);

        SingleValueResult total = new SingleValueResult();
        total.setTotalCount(new Long(orderListResult.getTotal()).intValue());

        PagedResult result = new PagedResult();
        result.setTotal(total);
        result.setRecords(orderListResult.getResults().stream()
                .map(PhotoOrderResponse::of)
                .collect(Collectors.toList()));

        return result;
    }

    @SuppressWarnings("unchecked")
    public PhotoOrderSiblingResponse getPhotoOrderSiblings(String poNumber, OrderQueryParam queryParam) {
        PhotoOrderEntity photoOrderEntity = photoOrderEntityRepository.findByPoNumber(poNumber);

        Optional<String> prevPoOrder = photoOrderEntityRepository.getPrevOrderNumber(photoOrderEntity, queryParam);
        Optional<String> nextPoOrder = photoOrderEntityRepository.getNextOrderNumber(photoOrderEntity, queryParam);

        return PhotoOrderSiblingResponse.builder()
                .prevPoNumber(prevPoOrder.orElse(null))
                .nextPoNumber(nextPoOrder.orElse(null))
                .build();
    }

    @Autowired
    private PhotoOrderDetailRepository photoOrderDetailRepository;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private SecurityGroupService securityGroupService;

    private final static Integer PHOTOSTUDIO_GROUP_NUMBER = 29;

    public Map<String, Object> getPhotoOrder(String poNumber) {

        PhotoOrder photoOrder = photoOrderRepository.getPhotoOrderInfoWithAdditionalInfo(poNumber);
        Vendor vendor = vendorService.getVendorInfo(photoOrder.getWholeSalerID());
        List<LogPhotoAction> logPhotoActions = logPhotoActionRepository.getLogPhotoActions(photoOrder.getOrderID());
        List<PhotoOrderDetail> photoOrderDetails = photoOrderDetailRepository.findByOrderID(photoOrder.getOrderID());
        List<SecurityUser> securityUsers = securityGroupService.getSecurityUsersByGroupId(PHOTOSTUDIO_GROUP_NUMBER);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("photoOrder", DetailPhotoOrder.build(photoOrder, vendor));
        result.put("actionLogs", LogPhotoActionDto.build(logPhotoActions));
        result.put("items", DetailOrderQuantity.build(photoOrderDetails));
        result.put("photoStudioUsers", PhotoActionUser.build(securityUsers));
        result.put("isEditable", isEditable(photoOrder.getPhotoBooking().getMapPhotoCalendarModel().getCalendarID(), 2));
        return result;
    }

    private boolean isEditable(int calendarId, int businessDay) {
        PhotoCalendarEntity calendarEntity = photoCalendarRepository.findById(calendarId)
                .orElse(null);

        PhotoCalendarEntity limitCalendarEntity = photoCalendarRepository.findBeforeBusinessDayFromTheDate(businessDay, LocalDateTime.now().minusDays(1));

        return calendarEntity != null
                && (calendarEntity.getTheDate().isAfter(limitCalendarEntity.getTheDate())
                || calendarEntity.getTheDate().isEqual(limitCalendarEntity.getTheDate()));
    }

    public List<AvailableModelsResponse> getAvailableModels(Integer orderID, String theDate) {
        List<Object> params = new ArrayList<Object>();
        params.add(orderID);
        params.add(theDate);

        LocalDate selectedDate = LocalDate.parse(theDate);

        // get order info
        PhotoOrder photoOrder = photoOrderRepository.getPhotoOrderInfoWithBookAndModelAndCategory(orderID);
        MapPhotoCalendarModel orderModel = photoOrder.getPhotoBooking().getMapPhotoCalendarModel();
        boolean isFullModelShot = orderModel.getModelID() != null;

        // get selected day's models
        List<MapPhotoCalendarModel> selectedDaysAvailableModels = mapPhotoCalendarModelRepository.findAvailableMapByTheDate(selectedDate)
                .stream()
                .filter(selectedDaysModel -> (orderModel.getModelID() == null) == (selectedDaysModel.getModelID() == null))
                .filter(selectedDaysModel -> selectedDaysModel.getModelID() == null // TODO: HARDCODED
                        || (selectedDaysModel.getPhotoModel().getType().equals("Regular") && photoOrder.getPhotoCategory().getCategoryName().equals("Women Regular"))
                        || (selectedDaysModel.getPhotoModel().getType().equals("Plus") && photoOrder.getPhotoCategory().getCategoryName().equals("Women Plus Size")))
                .filter(selectedDaysModel -> {
                    BigDecimal selectedDaysAvailableUnit = selectedDaysModel.getAvailableUnit().subtract(selectedDaysModel.getPhotoBooking().stream()
                            .filter(selectedDaysBooking -> selectedDaysBooking.getStatusID() == 0)
                            .map(PhotoBooking::getBookedUnit)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));

                    return photoOrder.getTotalUnit().doubleValue() <= selectedDaysAvailableUnit.doubleValue();
                })
                .collect(Collectors.toList());

        List<Integer> selectedDaysAvailableModelIds = selectedDaysAvailableModels.stream()
                .map(MapPhotoCalendarModel::getModelID)
                .collect(Collectors.toList());

        List<MapPhotoImage> selectedModelsFirstImageMaps = getModelsFirstImageMaps(selectedDaysAvailableModelIds);
        List<PhotoImage> selectedModelsFirstImages = getModelsFirstImages(selectedModelsFirstImageMaps);

        // selected day's models' other available days(after now)
        List<MapPhotoCalendarModel> selectedModelsOtherDays = mapPhotoCalendarModelRepository.findAvailableMapAfterNowByModelIdsAndNotTheDate(selectedDaysAvailableModelIds, selectedDate, isFullModelShot)
                .stream()
                .filter(selectedModelsOtherDay -> {
                    BigDecimal otherDaysAvailableUnit = selectedModelsOtherDay.getAvailableUnit().subtract(selectedModelsOtherDay.getPhotoBooking().stream()
                            .filter(otherDaysBooking -> otherDaysBooking.getStatusID() == 0)
                            .map(PhotoBooking::getBookedUnit)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));

                    return photoOrder.getTotalUnit().doubleValue() <= otherDaysAvailableUnit.doubleValue();
                })
                .collect(Collectors.toList());

        // make response
        List<AvailableModelsResponse> availableModelsResponses;
        if (isFullModelShot) {
            // for full model shot
            availableModelsResponses = selectedDaysAvailableModels.stream()
                    .map(models -> {
                        AvailableModelsResponse response = new AvailableModelsResponse();

                        response.setModelId(models.getModelID());
                        response.setModelName(Optional.ofNullable(models.getPhotoModel())
                                .map(PhotoModel::getModelName)
                                .orElse(null));
                        response.setNextAvailableDates(selectedModelsOtherDays.stream()
                                .filter(otherDays -> otherDays.getModelID().equals(models.getModelID()))
                                .map(otherDays -> otherDays.getPhotoCalendarEntity().getTheDate().toLocalDate().toString())
                                .collect(Collectors.toList()));
                        response.setImageUrl(getFirstImageUrl(models.getModelID(), selectedModelsFirstImageMaps, selectedModelsFirstImages));

                        return response;
                    })
                    .collect(Collectors.toList());
        } else {
            // for flat product shot
            availableModelsResponses = new ArrayList<>(1);

            AvailableModelsResponse response = new AvailableModelsResponse();

            response.setNextAvailableDates(selectedModelsOtherDays.stream()
                    .map(otherDays -> otherDays.getPhotoCalendarEntity().getTheDate().toLocalDate().toString())
                    .collect(Collectors.toList()));

            if (selectedDaysAvailableModels.size() == 0) {
                // is today available
                response.setIsToday(1);
            }

            availableModelsResponses.add(response);
        }

        return availableModelsResponses;
    }

    public PhotoDiscount checkDiscountCode(String discountCode) {
        return photoDiscountRepository.findUsableDiscountByDiscountCode(discountCode)
                .orElse(null);
    }

    public String updatePhotoOrder(OrderUpdateRequest orderUpdateRequest) {
        if (orderUpdateRequest.getOrderId() == null) {
			return "OrderID does not exist!";
		}

        PhotoOrder photoOrder = Optional.ofNullable(photoOrderRepository.getPhotoOrderInfoWithBookAndModelAndCategory(orderUpdateRequest.getOrderId()))
                .orElse(null);

        if(photoOrder == null) {
            return "OrderID does not exist!";
        }

        LocalDateTime now = LocalDateTime.now();

        String username = Utility.getUsername();

        LocalDate nowDate = now.toLocalDate();
        LocalDate photoShootDate = photoOrder.get_photoshootDate().toLocalDate();
        boolean isInScheduleChangeDueDate = nowDate.compareTo(photoShootDate) <= 0;
        boolean isInStyleChangeDueDate = nowDate.getYear() < photoShootDate.getYear()
                || (nowDate.getYear() == photoShootDate.getYear() && nowDate.getMonthValue() < photoShootDate.getMonthValue())
                || (nowDate.getYear() == photoShootDate.getYear()
					&& nowDate.getMonthValue() == photoShootDate.getMonthValue()
                    && nowDate.compareTo(photoShootDate.plusDays(2)) <= 0);
        boolean isInAdditionalDiscountChangeDueDate = nowDate.getYear() < photoShootDate.getYear()
                || nowDate.getYear() == photoShootDate.getYear() && nowDate.getMonthValue() <= photoShootDate.getMonthValue();

		if(orderUpdateRequest.getPhotoshootDate() != null) {
            if(!isInScheduleChangeDueDate) {
                return "The photo shoot date can be changed only before the original photo shoot date!";
            }

			List<Object> params = new ArrayList<Object>();
			params.add(orderUpdateRequest.getOrderId());
			params.add(orderUpdateRequest.getPhotoshootDate());
            params.add(orderUpdateRequest.getModelId());
            params.add(orderUpdateRequest.getAdditionalDiscountAmount());
            params.add(orderUpdateRequest.getInHouseNote());
			params.add(username);

			List<Object> outputparams = new ArrayList<Object>();
			outputparams.add("");
			List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_UpdateOrder", params, outputparams);

			List<Object> outputs = (List<Object>) r.get(0);
			if (outputs != null && outputs.size() > 0) {
			    Integer beforeModelID = photoOrder.getPhotoBooking().getMapPhotoCalendarModel().getModelID();
			    if (beforeModelID != null) {
                    updateModelPopularity(beforeModelID);
                    updateModelPopularity(orderUpdateRequest.getModelId());
                }

				return outputs.get(0) == null ? null : String.valueOf(outputs.get(0));
			}
		} else {
            if(!isInStyleChangeDueDate && !isInAdditionalDiscountChangeDueDate) {
                modifyInHouseNote(photoOrder, now, username, orderUpdateRequest.getInHouseNote());
                return null;
            } else if (!isInStyleChangeDueDate && isInAdditionalDiscountChangeDueDate) {
                try {
                    modifyNonBookingAndStyleOption(photoOrder, orderUpdateRequest.getDiscountId(), orderUpdateRequest.getAdditionalDiscountAmount(), orderUpdateRequest.getInHouseNote());
                } catch (NotFoundPhotostudioDiscount e) {
                    return "DiscountID does not exist!";
                }
                return null;
            }

            if (CollectionUtils.isEmpty(orderUpdateRequest.getItems())) {
                return "At least 1 order has to be on the list";
            } else if (validateInputQty(orderUpdateRequest.getItems())) {
		        return "The item qty cannot be lower than 0";
            }

            // Load Unit
            boolean isFullModelShot = photoOrder.getPhotoCategory().getTypeOfPhotoshoot().equals("Full Model Shot"); // TODO HARDCODED
            Map<Integer, PhotoUnit> photoUnitMap = photoUnitRepository.findAllCurrentEffectiveUnit(photoOrder.getCategoryID(), photoOrder.getPackageID(), isFullModelShot)
                    .stream()
                    .collect(Collectors.toMap(PhotoUnit::getPriceTypeID, photoUnit -> photoUnit));

            List<PhotoOrderDetail> originalItems = photoOrderDetailRepository.findByOrderID(photoOrder.getOrderID());
            List<PhotoOrderDetail> unchangedItems = getUnchangedDetailList(originalItems, orderUpdateRequest.getItems());
            List<PhotoOrderDetail> changedItems = getChangedDetailList(originalItems, orderUpdateRequest.getItems(), now);
            List<PhotoOrderDetail> addedItems = getAddedDetailList(photoOrder, orderUpdateRequest.getItems(), now);
            List<PhotoOrderDetail> removedItems = getRemovedDetailList(originalItems, orderUpdateRequest.getItems());

            // Calculate total unit
            try {
                List<PhotoOrderDetail> storedItems = new ArrayList<>(changedItems);
                storedItems.addAll(unchangedItems);
                storedItems.addAll(addedItems);
                BigDecimal totalUnit = calculateTotalUnit(photoOrder, storedItems, photoUnitMap);

                photoOrder.setTotalUnit(totalUnit);
                photoOrder.setTotalQty(calculateTotalQty(storedItems));
                photoOrder.setSubtotalAmount(calculateSubtotalPrice(storedItems));
            } catch (NotEnoughPhotostudioAvailableUnit e) {
                return "There is no available unit";
            }

            // Additional Promo Code
            try {
                applyDiscount(photoOrder, orderUpdateRequest.getDiscountId());
            } catch (NotFoundPhotostudioDiscount e) {
                return "DiscountID does not exist!";
            }

            if (orderUpdateRequest.getAdditionalDiscountAmount() != null) {
                applyAdditionalDiscountAmount(photoOrder, orderUpdateRequest.getAdditionalDiscountAmount());
            }
            photoOrder.setInHouseNote(orderUpdateRequest.getInHouseNote());
            photoOrder.setModifiedOnDate(now);
            photoOrder.setModifiedBY(username);

            photoOrder.getPhotoBooking().setBookedUnit(photoOrder.getTotalUnit());
            photoOrder.getPhotoBooking().setModifiedOnDate(now);
            photoOrder.getPhotoBooking().setModifiedBY(username);

            TransactionTemplate template = new TransactionTemplate(transactionManager);
            template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            template.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    photoOrderRepository.save(photoOrder);
                    photoBookingRepository.save(photoOrder.getPhotoBooking());
                    photoOrderDetailRepository.saveAll(changedItems);
                    photoOrderDetailRepository.saveAll(addedItems);
                    photoOrderDetailRepository.deleteAll(removedItems);
                }
            });
		}

        return null;
    }

    private void applyDiscount(PhotoOrder order, Integer newDiscountId) {
        if ((order.getDiscountID() == null && newDiscountId == null)
        || (order.getDiscountID() != null && order.getDiscountID().equals(newDiscountId))) {
            return;
        }

        if (newDiscountId == null) {
            order.setPhotoDiscount(null);
            order.setDiscountAmount(null);
            order.setDiscountCode(null);
        } else {
            PhotoDiscount discount = photoDiscountRepository.findById(newDiscountId)
                    .orElseThrow(NotFoundPhotostudioDiscount::new);

            BigDecimal discountAmount;

            if (discount.getDiscountAmount() != null) {
                discountAmount = discount.getDiscountAmount();
            } else {
                discountAmount = order.getSubtotalAmount().multiply(discount.getDiscountRate());
            }

            BigDecimal subtotal = order.getSubtotalAmount().subtract(order.getPhotoCreditUsedAmount());

            if (discountAmount.longValue() > subtotal.longValue()) {
                discountAmount = BigDecimal.valueOf(subtotal.longValue());
                order.setAdditionalDiscountAmount(BigDecimal.ZERO);
            } else if (discountAmount.longValue() + order.getAdditionalDiscountAmount().longValue() > subtotal.longValue()) {
                order.setAdditionalDiscountAmount(BigDecimal.valueOf(subtotal.subtract(discountAmount).longValue()));
            }

            order.setDiscountAmount(discountAmount);
            order.setPhotoDiscount(discount);
            order.setDiscountCode(discount.getDiscountCode());
        }
    }

    private void applyAdditionalDiscountAmount(PhotoOrder order, BigDecimal additionalDiscountAmount) {
        long availableAdditionalDiscountAmount = order.getSubtotalAmount()
                .subtract(order.getPhotoCreditUsedAmount() != null ? order.getPhotoCreditUsedAmount() : BigDecimal.ZERO)
                .subtract(order.getDiscountAmount() != null ? order.getDiscountAmount() : BigDecimal.ZERO)
                .longValue();

        if (availableAdditionalDiscountAmount > additionalDiscountAmount.longValue()) {
            order.setAdditionalDiscountAmount(additionalDiscountAmount);
        } else {
            order.setAdditionalDiscountAmount(BigDecimal.valueOf(availableAdditionalDiscountAmount));
        }
    }

    private void modifyInHouseNote(PhotoOrder photoOrder, LocalDateTime now, String username, String inHouseNote) {
        photoOrder.setInHouseNote(inHouseNote);
        photoOrder.setModifiedOnDate(now);
        photoOrder.setModifiedBY(username);
        TransactionTemplate template = new TransactionTemplate(transactionManager);
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                photoOrderRepository.save(photoOrder);
            }
        });
    }

    private void modifyNonBookingAndStyleOption(PhotoOrder photoOrder, Integer discountId, BigDecimal discountAmount, String inHouseNode) {
        // Additional Promo Code
        applyDiscount(photoOrder, discountId);

        photoOrder.setAdditionalDiscountAmount(discountAmount);
        photoOrder.setInHouseNote(inHouseNode);
        photoOrder.setModifiedOnDate(LocalDateTime.now());
        photoOrder.setModifiedBY(Utility.getUsername());

        TransactionTemplate template = new TransactionTemplate(transactionManager);
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                photoOrderRepository.save(photoOrder);
            }
        });
    }

    public String cancelPhotoOrder(PhotoOrder photoOrder) {
        List<Object> params = new ArrayList<Object>();
        if (photoOrder.getOrderID() == null) {
            return "OrderID does not exist!";
        }

        params.add(photoOrder.getOrderID());
        params.add(photoOrder.getCancelNote());
        params.add(Utility.getUsername());
        params.add(photoOrder.getCancellationFeeRate());

        List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_CancelOrder", params);

        PhotoOrder order = Optional.ofNullable(photoOrderRepository.getPhotoOrderInfoWithBookAndModelAndCategory(photoOrder.getOrderID()))
                .orElse(null);

        if (order.getPhotoBooking().getMapPhotoCalendarModel().getModelID() != null) {
            updateModelPopularity(order.getPhotoBooking().getMapPhotoCalendarModel().getModelID());
        }

        return null;
    }

    public DailySummaryResponse getDailySummary(String photoshootDate) {

        LocalDateTime start = DateUtils.getLocalDateTimeFromyyyyDashMMDashdd(photoshootDate);
        LocalDateTime end = DateUtils.getDatePlusOneDay(start);

        List<PhotoOrder> photoOrders = photoOrderRepository.getValidOrderWithDetailByPhotoshootDate(start, end);

        DailySummaryResponse dailySummaryResponse = DailySummaryResponse.make(start, photoOrders);

//        List<Object> params = new ArrayList<Object>();
//        params.add(photoshootDate);
//
//        List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetDailySummary", params, DailySummaryResponse.class);
//        List<DailySummaryResponse> dailySummaryResponses = (List<DailySummaryResponse>) r.get(0);
//        return dailySummaryResponses.get(0);

        return dailySummaryResponse;
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

        List<Object> outputs = (List<Object>) result.get(0);
        Integer orderStatusID = outputs.get(0) == null || Integer.parseInt(String.valueOf(outputs.get(0))) == 0 ? null : Integer.parseInt(String.valueOf(outputs.get(0)));

        return orderStatusID;
    }


    public Map<String, Object> getReports(int year, int month, ReportType reportType) {

        LocalDateTime start = DateUtils.getFirstDayOfMonthAsLocalDateTime(year, month);
        LocalDateTime end = DateUtils.getFirstDayOfNextMonth(year, month);

        List<PhotoOrder> orders = null;
        Map<String, Object> result = new HashMap<String, Object>();
        if (reportType == ReportType.DailySalesReport) {
            orders = photoOrderRepository.getOrderWithDetail(start, end);
            if(CollectionUtils.isEmpty(orders)) return new HashedMap<>();

            List<PhotoOrder> validOrders = orders.stream().filter(x -> x.getIsCancelledBy() == null).collect(Collectors.toList());
            List<ReportDailySummaryResponse> dailyDatas = makeDailyReportDataByCheckoutDate(validOrders);
            if(CollectionUtils.isNotEmpty(dailyDatas)) dailyDatas.sort(Comparator.comparing(o -> o.getDate()));
            result.put("dailyData", dailyDatas);

            ReportMonthlySummaryResponse response = makeMonthlyReportData(start, orders);
            result.put("monthSummary", response);
        } else {
            orders = photoOrderRepository.getOrderWithDetailByPhotoshootDate(start, end);
            if(CollectionUtils.isEmpty(orders)) return new HashedMap<>();

            List<PhotoOrder> validOrders = null;
            List<PhotoOrder> ordersByType = null;
            if(reportType == ReportType.AllPhotoshoot) {
                validOrders = orders.stream().filter(x -> x.getIsCancelledBy() == null).collect(Collectors.toList());

                ordersByType = orders;
            } else {
                validOrders = orders.stream().filter(
                        x ->
                                x.getIsCancelledBy() == null && ReportTypeChecker.checkReportType(reportType, x.getCategoryID(), x.getPackageID())
                ).collect(Collectors.toList());

                ordersByType = orders.stream().filter(x ->
                        ReportTypeChecker.checkReportType(reportType, x.getCategoryID(), x.getPackageID())
                ).collect(Collectors.toList());
            }

            List<ReportDailySummaryResponse> dailyDatas = makeDailyReportDataByPhotoShootDate(validOrders);
            if(CollectionUtils.isNotEmpty(dailyDatas)) dailyDatas.sort(Comparator.comparing(o -> o.getDate()));
            result.put("dailyData", dailyDatas);

            List<ReportDailyVendorSummaryResponse> dailyVendorDatas = makeDailyVendorReportData(validOrders);
            dailyVendorDatas.sort(Comparator.comparing(o -> o.getDate()));
            result.put("dailyVendorData", dailyVendorDatas);

            ReportMonthlySummaryResponse response = makeMonthlyReportData(start, ordersByType);
            result.put("monthSummary", response);
        }

        return result;
    }

    private List<ReportDailyVendorSummaryResponse> makeDailyVendorReportData(List<PhotoOrder> validOrders) {
        Map<String, List<PhotoOrder>> photoOrderMap = validOrders.stream().collect(Collectors.groupingBy(PhotoOrder::getPhotoshootDate));
        List<ReportDailyVendorSummaryResponse> dailyVendorDatas = new ArrayList<>();
        for (String photoshootDate : photoOrderMap.keySet()) {
            List<PhotoOrder> orders = photoOrderMap.get(photoshootDate);
            Map<String, List<PhotoOrder>> photoOrderMapByWholeSaler = orders.stream().collect(Collectors.groupingBy(PhotoOrder::getWholeSalerCompanyName));

            for (String wholeSalerCompanyName : photoOrderMapByWholeSaler.keySet()) {
                ReportDailyVendorSummaryResponse response = ReportDailyVendorSummaryResponse.makeSummary(photoshootDate, wholeSalerCompanyName, photoOrderMapByWholeSaler.get(wholeSalerCompanyName));
                dailyVendorDatas.add(response);
            }
        }
        return dailyVendorDatas;
    }

    private ReportMonthlySummaryResponse makeMonthlyReportData(LocalDateTime startDate, List<PhotoOrder> orders) {
        ReportMonthlySummaryResponse response = ReportMonthlySummaryResponse.makeSummary(startDate, orders);
        List<Integer> wholeSalerIds = orders.parallelStream().map(PhotoOrder::getWholeSalerID).collect(Collectors.toList());
        Map<Integer, List<PhotoOrder>> oldOrders = photoOrderRepository.getOrderOfWholeSaler(wholeSalerIds);
        response.makeOldOrdersSummary(oldOrders);
        return response;
    }

    private List<ReportDailySummaryResponse> makeDailyReportDataByPhotoShootDate(List<PhotoOrder> validOrders) {
        if(CollectionUtils.isEmpty(validOrders)) {
            return new ArrayList<>();
        }

        Map<String, List<PhotoOrder>> photoOrderMap = validOrders.stream().collect(Collectors.groupingBy(PhotoOrder::getPhotoshootDate));
        return makeDailyReportData(photoOrderMap);
    }

    private List<ReportDailySummaryResponse> makeDailyReportData(Map<String, List<PhotoOrder>> photoOrderMap) {
        List<ReportDailySummaryResponse> dailyDatas = new ArrayList<>();
        for (String date : photoOrderMap.keySet()) {
            List<PhotoOrder> orders = photoOrderMap.get(date);
            ReportDailySummaryResponse response = ReportDailySummaryResponse.makeSummary(date, orders);

            List<Integer> wholeSalerIds = orders.parallelStream().map(PhotoOrder::getWholeSalerID).collect(Collectors.toList());
            Map<Integer, List<PhotoOrder>> oldOrders = photoOrderRepository.getOrderOfWholeSaler(wholeSalerIds);
            response.makeOldOrdersSummary(oldOrders);

            dailyDatas.add(response);
        }
        return dailyDatas;
    }

    private List<ReportDailySummaryResponse> makeDailyReportDataByCheckoutDate(List<PhotoOrder> validOrders) {
        if(CollectionUtils.isEmpty(validOrders)) {
            return new ArrayList<>();
        }

        Map<String, List<PhotoOrder>> photoOrderMap = validOrders.stream().collect(Collectors.groupingBy(PhotoOrder::getCheckOutDate));
        return makeDailyReportData(photoOrderMap);
    }

    public List<ReportCsvMonthly> getReportsMonthlyCsv(String yyyymmddString, ReportType reportType) {

        LocalDateTime start = DateUtils.getLocalDateTimeFromyyyyMMdd(yyyymmddString);
        LocalDateTime end = DateUtils.getLastDayOfMonthAsLocalDateTime(start);

        List<PhotoOrder> orders = photoOrderRepository.getValidOrderWithDetailByPhotoshootDate(start, end);

        List<PhotoOrder> ordersByType = null;
        if(reportType == ReportType.AllPhotoshoot) {
            ordersByType = orders;
        } else {
            ordersByType = orders.stream().filter(x ->
                    ReportTypeChecker.checkReportType(reportType, x.getCategoryID(), x.getPackageID())
            ).collect(Collectors.toList());
        }
        List<ReportCsvMonthly> reportCsvMonthlyDatas = ReportCsvMonthly.makeSummary(ordersByType);
        return reportCsvMonthlyDatas;
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

        List<Object> _results = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetCreditBalance", params, PhotoCredit.class);

        List<PhotoCredit> rs2 = (List<PhotoCredit>) _results.get(0);

        BigDecimal photoCreditBalance = BigDecimal.ZERO;
        if (rs2.size() > 0 && rs2.get(0) != null) {
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

        if (!StringUtils.isEmpty(searchType) && !StringUtils.isEmpty(searchKeyword)) {
            params.add(searchType);
            params.add(searchKeyword);
        } else {
            params.add(null);
            params.add(null);
        }

        List<Object> _results = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetCreditList", params, SingleValueResult.class, PhotoCredit.class);

        List<SingleValueResult> rs1 = (List<SingleValueResult>) _results.get(0);
        List<PhotoCredit> rs2 = (List<PhotoCredit>) _results.get(1);

        result.setTotal(rs1.get(0));
        result.setRecords(rs2);

        return result;
    }

    public List<PhotoCredit> getCreditHistory(Integer wholeSalerID) {
        List<Object> params = new ArrayList<Object>();
        params.add(wholeSalerID);

        List<Object> r = jdbcHelperPhotoStudio.executeSP("up_wa_Photo_GetCreditDetail", params, PhotoCredit.class);
        List<PhotoCredit> photoCredits = (List<PhotoCredit>) r.get(0);

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

    public List<PhotoCart> getPhotoCarts(LocalDateTime start, LocalDateTime end) {
        List<PhotoCart> photoCarts = photoCartRepository.findAllByCreatedOnBetween(start, end);
        return photoCarts;
    }

    public List<PhotoBannerClickStatistic> getPhotoBannerClicks(LocalDateTime start, LocalDateTime end) {
        List<PhotoBannerClickStatistic> photoBannerClickStatistics = photoBannerClickRepository.getClickStatistic(start, end);
        return photoBannerClickStatistics;
    }

    private final String DAILY_REPORT_REQUEST_DATE_PATTERN = "yyyyMMdd";

    public DailyReport getDailyReportToExcel(String date) {

        LocalDateTime start = null;
        LocalDateTime end = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DAILY_REPORT_REQUEST_DATE_PATTERN);
        try {
            start = DateUtils.convertToLocalDateTime(sdf.parse(date));
            end = DateUtils.getDatePlusOneDay(start);
        } catch (ParseException e) {
        }

        try {
            List<PhotoCart> photoCarts = getPhotoCarts(start, end);
            List<Integer> cartIds = photoCarts.parallelStream().map(x -> x.getId()).collect(Collectors.toList());
            Map<Integer, PhotoOrder> photoOrderOfCartId = photoOrderRepository.getOrderOfCart(cartIds);

            List<PageViewDailyReport> pageViewDailyReports = PageViewDailyReport.build(photoCarts, photoOrderOfCartId);

            List<PhotoCategory> photoCategories = photoCategoryRepository.findAll();
            Map<Integer, PhotoCategory> photoCategoryMap = photoCategories.stream().collect(
                    Collectors.toMap(x -> x.getCategoryId(), x -> x));
            List<OrderDetailDailyReport> orderDetailDailyReports = OrderDetailDailyReport.build(
                    photoCategoryMap,
                    photoOrderRepository.getValidOrderStatistic(start, end),
                    photoOrderRepository.getCancelOrderStatistic(start, end),
                    photoOrderRepository.getValidOrderDetailStatistic(start, end)
            );

            List<ClickStatDailyReport> clickStatDailyReports = ClickStatDailyReport.build(getPhotoBannerClicks(start, end));

            List<PhotoOrder> orders = photoOrderRepository.getOrderWithDetail(start, end);
            ReportMonthlySummaryResponse monthlySummaryResponse = makeMonthlyReportData(start, orders);

            DailyReport dailyReport = new DailyReport();
            dailyReport.setPageViewDailyReports(pageViewDailyReports);
            dailyReport.setOrderDetailDailyReports(orderDetailDailyReports);
            dailyReport.setClickStatDailyReports(clickStatDailyReports);
            dailyReport.setReportMonthlySummaryResponse(monthlySummaryResponse);
            return dailyReport;
        }catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public List<PhotoDropperResponse> getDroppers(int wholeSalerId) {
        return photoDropperRepository.findAllByWholeSalerId(wholeSalerId).stream()
                .map(PhotoDropperResponse::of)
                .collect(Collectors.toList());
    }

    public List<PhotoDropperResponse> saveDropper(PhotoDropperSaveRequest request) {
        PhotoDropper photoDropper = PhotoDropper.builder()
                .wholeSalerId(request.getWholeSalerId())
                .dropperName(request.getDropperName())
                .dropperEmail(request.getDropperEmail())
                .build();

        photoDropperRepository.save(photoDropper);
        return photoDropperRepository.findAllByWholeSalerId(request.getWholeSalerId()).stream()
                .map(PhotoDropperResponse::of)
                .collect(Collectors.toList());
    }

    private List<PhotoOrderDetail> getAddedDetailList(PhotoOrder photoOrder, List<DetailOrderQuantity> newItems, LocalDateTime now) {
        return newItems.stream().filter(i -> i.getOrderDetailID() == null || i.getOrderDetailID() == 0)
                .map(i -> {
                    PhotoOrderDetail detail = new PhotoOrderDetail();
                    detail.setOrderID(photoOrder.getOrderID());
                    detail.setStyleName(i.getStyleName());

                    detail.setBaseColorSetUnitPrice(i.getBaseColorSetUnitPrice());
                    detail.setBaseColorSetQty(i.getBaseColorSetQty());

                    detail.setColorSwatchUnitPrice(i.getColorSwatchUnitPrice());
                    detail.setColorSwatchQty(i.getColorSwatchQty());

                    detail.setModelSwatchUnitPrice(i.getModelSwatchUnitPrice());
                    detail.setModelSwatchQty(i.getModelSwatchQty());

                    detail.setMovieClipUnitPrice(i.getMovieClipUnitPrice());
                    detail.setMovieClipQty(i.getMovieClipQty());

                    detail.setCreatedOnDate(now);
                    detail.setCreatedBy(Utility.getUsername());
                    return detail;
                })
                .collect(Collectors.toList());
    }

    private List<PhotoOrderDetail> getRemovedDetailList(List<PhotoOrderDetail> originalItems, List<DetailOrderQuantity> newItems) {
        List<Integer> idList = newItems.stream().filter(i -> i.getOrderDetailID() != null && i.getOrderDetailID() > 0)
                .map(DetailOrderQuantity::getOrderDetailID)
                .collect(Collectors.toList());
        return originalItems.stream().filter(d -> !idList.contains(d.getOrderDetailID()))
                .collect(Collectors.toList());
    }

    private List<PhotoOrderDetail> getChangedDetailList(List<PhotoOrderDetail> originalItems, List<DetailOrderQuantity> newItems, LocalDateTime now) {
        Map<Integer, DetailOrderQuantity> itemMap = newItems.stream().filter(i -> i.getOrderDetailID() != null && i.getOrderDetailID() > 0)
                .collect(Collectors.toMap(DetailOrderQuantity::getOrderDetailID, i -> i, (i1, i2) -> i1));

        return originalItems.stream()
                .filter(d -> itemMap.get(d.getOrderDetailID()) != null)
                .filter(d -> isUpdatedDetail(d, itemMap.get(d.getOrderDetailID())))
                .peek(d -> {
                    DetailOrderQuantity item = itemMap.get(d.getOrderDetailID());
                    d.setStyleName(item.getStyleName());
                    d.setStyleQty(item.getStyleQty());
                    d.setColorQty(item.getColorQty());
                    d.setColorSetQty(item.getColorSetQty());
                    d.setMovieQty(item.getMovieQty());
                    d.setBaseColorSetQty(item.getBaseColorSetQty());
                    d.setModelSwatchQty(item.getModelSwatchQty());
                    d.setColorSwatchQty(item.getColorSwatchQty());
                    d.setMovieClipQty(item.getMovieClipQty());
                    d.setModifiedOnDate(now);
                    d.setModifiedBY(Utility.getUsername());
                })
                .collect(Collectors.toList());
    }

    private List<PhotoOrderDetail> getUnchangedDetailList(List<PhotoOrderDetail> originalItems, List<DetailOrderQuantity> newItems) {
        Map<Integer, DetailOrderQuantity> itemMap = newItems.stream().filter(i -> i.getOrderDetailID() != null && i.getOrderDetailID() > 0)
                .collect(Collectors.toMap(DetailOrderQuantity::getOrderDetailID, i -> i, (i1, i2) -> i1));

        return originalItems.stream()
                .filter(d -> itemMap.get(d.getOrderDetailID()) != null)
                .filter(d -> !isUpdatedDetail(d, itemMap.get(d.getOrderDetailID())))
                .collect(Collectors.toList());
    }

    private boolean isUpdatedDetail(PhotoOrderDetail originalItem, DetailOrderQuantity newItem) {
        if (StringUtils.equals(originalItem.getStyleName(), newItem.getStyleName())
                && equalsNullable(originalItem.getStyleQty(), newItem.getStyleQty())
                && equalsNullable(originalItem.getColorQty(), newItem.getColorQty())
                && equalsNullable(originalItem.getColorSetQty(), newItem.getColorSetQty())
                && equalsNullable(originalItem.getMovieQty(), newItem.getMovieQty())
                && equalsNullable(originalItem.getBaseColorSetQty(), newItem.getBaseColorSetQty())
                && equalsNullable(originalItem.getModelSwatchQty(), newItem.getModelSwatchQty())
                && equalsNullable(originalItem.getColorSwatchQty(), newItem.getColorSwatchQty())
                && equalsNullable(originalItem.getMovieClipQty(), newItem.getMovieClipQty())) {
            return false;
        }
        return true;
    }

    private BigDecimal calculateTotalUnit(PhotoOrder photoOrder, List<PhotoOrderDetail> items, Map<Integer, PhotoUnit> photoUnitMap) {
        BigDecimal newTotalUnit = calculateTotalUnit(items, photoUnitMap);

        MapPhotoCalendarModel mapPhotoCalendarModel = photoOrder.getPhotoBooking().getMapPhotoCalendarModel();

        BigDecimal totalAvailableUnit = mapPhotoCalendarModel.getAvailableUnit();
        BigDecimal bookedUnit = mapPhotoCalendarModel.getPhotoBooking().stream()
                .filter(booking -> booking.getStatusID() == 0)
                .map(PhotoBooking::getBookedUnit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal availableUnit = totalAvailableUnit.subtract(bookedUnit);
        BigDecimal changedUnit = newTotalUnit.subtract(photoOrder.getTotalUnit());

        if (availableUnit.subtract(changedUnit).doubleValue() < 0) {
            throw new NotEnoughPhotostudioAvailableUnit();
        }

        return newTotalUnit;
    }

    private boolean equalsNullable(Integer int1, Integer int2) {
        if (int1 == null && int2 == null) {
            return true;
        } else if (int1 == null || int2 == null) {
            return false;
        } else {
            return int1.equals(int2);
        }
    }

    private BigDecimal calculateTotalUnit(List<PhotoOrderDetail> photoOrderDetails, Map<Integer, PhotoUnit> photoUnitMap) {
        BigDecimal totalUnit = BigDecimal.ZERO;

        for (PhotoOrderDetail photoOrderDetail : photoOrderDetails) {
			totalUnit = totalUnit.add(Optional.ofNullable(photoUnitMap.get(1)).map(PhotoUnit::getUnit).orElse(BigDecimal.ZERO)
					.multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getStyleQty()).orElse(0))));

			totalUnit = totalUnit.add(Optional.ofNullable(photoUnitMap.get(2)).map(PhotoUnit::getUnit).orElse(BigDecimal.ZERO)
					.multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getColorSetQty()).orElse(0))));

			totalUnit = totalUnit.add(Optional.ofNullable(photoUnitMap.get(3)).map(PhotoUnit::getUnit).orElse(BigDecimal.ZERO)
					.multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getColorQty()).orElse(0))));

			totalUnit = totalUnit.add(Optional.ofNullable(photoUnitMap.get(4)).map(PhotoUnit::getUnit).orElse(BigDecimal.ZERO)
					.multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getMovieQty()).orElse(0))));

			totalUnit = totalUnit.add(Optional.ofNullable(photoUnitMap.get(5)).map(PhotoUnit::getUnit).orElse(BigDecimal.ZERO)
					.multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getBaseColorSetQty()).orElse(0))));

			totalUnit = totalUnit.add(Optional.ofNullable(photoUnitMap.get(6)).map(PhotoUnit::getUnit).orElse(BigDecimal.ZERO)
					.multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getModelSwatchQty()).orElse(0))));

			totalUnit = totalUnit.add(Optional.ofNullable(photoUnitMap.get(7)).map(PhotoUnit::getUnit).orElse(BigDecimal.ZERO)
					.multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getMovieClipQty()).orElse(0))));

			totalUnit = totalUnit.add(Optional.ofNullable(photoUnitMap.get(8)).map(PhotoUnit::getUnit).orElse(BigDecimal.ZERO)
					.multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getColorSwatchQty()).orElse(0))));
		}

        return totalUnit;
    }

    private BigDecimal calculateSubtotalPrice(List<PhotoOrderDetail> photoOrderDetails) {
        BigDecimal totalUnit = BigDecimal.ZERO;

        for (PhotoOrderDetail photoOrderDetail : photoOrderDetails) {
            totalUnit = totalUnit.add(Optional.ofNullable(photoOrderDetail.getStyleUnitPrice()).orElse(BigDecimal.ZERO)
                    .multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getStyleQty()).orElse(0))));

            totalUnit = totalUnit.add(Optional.ofNullable(photoOrderDetail.getColorSetUnitPrice()).orElse(BigDecimal.ZERO)
                    .multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getColorSetQty()).orElse(0))));

            totalUnit = totalUnit.add(Optional.ofNullable(photoOrderDetail.getColorUnitPrice()).orElse(BigDecimal.ZERO)
                    .multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getColorQty()).orElse(0))));

            totalUnit = totalUnit.add(Optional.ofNullable(photoOrderDetail.getMovieUnitPrice()).orElse(BigDecimal.ZERO)
                    .multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getMovieQty()).orElse(0))));

            totalUnit = totalUnit.add(Optional.ofNullable(photoOrderDetail.getBaseColorSetUnitPrice()).orElse(BigDecimal.ZERO)
                    .multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getBaseColorSetQty()).orElse(0))));

            totalUnit = totalUnit.add(Optional.ofNullable(photoOrderDetail.getModelSwatchUnitPrice()).orElse(BigDecimal.ZERO)
                    .multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getModelSwatchQty()).orElse(0))));

            totalUnit = totalUnit.add(Optional.ofNullable(photoOrderDetail.getMovieClipUnitPrice()).orElse(BigDecimal.ZERO)
                    .multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getMovieClipQty()).orElse(0))));

            totalUnit = totalUnit.add(Optional.ofNullable(photoOrderDetail.getColorSwatchUnitPrice()).orElse(BigDecimal.ZERO)
                    .multiply(new BigDecimal(Optional.ofNullable(photoOrderDetail.getColorSwatchQty()).orElse(0))));
        }

        return totalUnit;
    }

    private int calculateTotalQty(List<PhotoOrderDetail> photoOrderDetails) {
    	int totalQty = 0;

    	for (PhotoOrderDetail photoOrderDetail : photoOrderDetails) {
    		totalQty += Optional.ofNullable(photoOrderDetail.getStyleQty()).orElse(0);
    		totalQty += Optional.ofNullable(photoOrderDetail.getColorQty()).orElse(0);
    		totalQty += Optional.ofNullable(photoOrderDetail.getColorSetQty()).orElse(0);
    		totalQty += Optional.ofNullable(photoOrderDetail.getMovieQty()).orElse(0);
    		totalQty += Optional.ofNullable(photoOrderDetail.getBaseColorSetQty()).orElse(0);
    		totalQty += Optional.ofNullable(photoOrderDetail.getModelSwatchQty()).orElse(0);
    		totalQty += Optional.ofNullable(photoOrderDetail.getColorSwatchQty()).orElse(0);
    		totalQty += Optional.ofNullable(photoOrderDetail.getMovieClipQty()).orElse(0);
		}

    	return totalQty;
	}

    private boolean validateInputQty(List<DetailOrderQuantity> items) {
        return items.stream()
                .anyMatch(item -> Optional.ofNullable(item.getStyleQty()).orElse(0) < 0
                            || Optional.ofNullable(item.getColorQty()).orElse(0) < 0
                            || Optional.ofNullable(item.getColorSetQty()).orElse(0) < 0
                            || Optional.ofNullable(item.getMovieQty()).orElse(0) < 0
                            || Optional.ofNullable(item.getBaseColorSetQty()).orElse(0) < 0
                            || Optional.ofNullable(item.getColorSwatchQty()).orElse(0) < 0
                            || Optional.ofNullable(item.getModelSwatchQty()).orElse(0) < 0
                            || Optional.ofNullable(item.getMovieClipQty()).orElse(0) < 0);
    }

    private List<MapPhotoImage> getModelsFirstImageMaps(List<Integer> modelIds) {
        // get selected day's models' first image maps
        return mapPhotoImageRepository.findModelImagesByModelIds(modelIds)
                .stream()
                // make Map<Integer, List<MapPhotoImage>>. key=(modelId), value=(MapPhotoImages of each model)
                .collect(Collectors.toMap(MapPhotoImage::getReferenceID, mapPhotoImage -> {
                    List<MapPhotoImage> imageIds = new ArrayList<>();
                    imageIds.add(mapPhotoImage);
                    return imageIds;
                }, (mapPhotoImages, mapPhotoImages2) -> {
                    mapPhotoImages.addAll(mapPhotoImages2);
                    return mapPhotoImages;
                }))
                .values()
                .stream()
                .peek(mapPhotoImages -> mapPhotoImages.sort(Comparator.comparing(MapPhotoImage::getListOrder)))
                // find first Image. There is no mapPhotoImage that it's size is 0.
                .map(mapPhotoImages -> mapPhotoImages.get(0))
                .collect(Collectors.toList());
    }

    private List<PhotoImage> getModelsFirstImages(List<MapPhotoImage> firstImageMaps) {
        // get selected day's models' first images
        return photoImageRepository.findAllByImageIDIn(firstImageMaps.stream()
                .map(MapPhotoImage::getImageID)
                .collect(Collectors.toList()));
    }

    private String getFirstImageUrl(Integer modelId, List<MapPhotoImage> firstImageMaps, List<PhotoImage> firstImages) {
        return firstImageMaps.stream()
                .filter(mapPhotoImage -> mapPhotoImage.getReferenceID().equals(modelId))
                .findFirst()
                .map(mapPhotoImage -> firstImages.stream()
                        .filter(photoImage -> photoImage.getImageID().equals(mapPhotoImage.getImageID()))
                        .findFirst()
                        .map(PhotoImage::getImageUrl)
                        .orElse(null))
                .orElse(null);
    }
}
