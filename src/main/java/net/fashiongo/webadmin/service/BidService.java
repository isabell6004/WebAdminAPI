package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.dao.primary.*;
import net.fashiongo.webadmin.model.pojo.bid.*;
import net.fashiongo.webadmin.model.pojo.bid.parameter.GetBidSettingLastRecordsParameter;
import net.fashiongo.webadmin.model.pojo.bid.parameter.GetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.bid.parameter.SetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingLastRecordsResponse;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingLastWeekResponse;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.primary.*;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 
 * @author JungHwan
 */
@Service
public class BidService extends ApiService {
	
	private static Logger logger = LoggerFactory.getLogger(BidService.class);

	@Autowired
	private AdBidSettingRepository adBidSettingRepository;
	@Autowired
	private AdBidRepository adBidRepository;
	@Autowired
	private AdBidLogRepository adBidLogRepository;
	@Autowired
	private AdVendorRepository adVendorRepository;
	@Autowired
	private AdPurchaseRepository adPurchaseRepository;
	@Autowired
	private EntityActionLogRepository entityActionLogRepository;
	@Autowired
	private RedissonClient redisson;
	@Autowired
	@Qualifier("redisListingAdBidSpotTemplate")
	private RedisTemplate<String, Object> redisListingAdBidSpotTemplate;

	@Value("${bid-redis.lock.timeout-seconds.acquire}")
	private int REDIS_LOCK_ACQUIRE_TIMEOUT_SECONDS;

	@Value("${bid-redis.lock.timeout-seconds.expire}")
	private int REDIS_LOCK_EXPIRE_TIMEOUT_SECONDS;

	// Bidding
	public static final String BIDDING_TOP_MAP_HASH = "bid_top_spot";

	/**
	 * Get BidSetting LastRecords
	 * 
	 * @since 2018. 10. 03.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GetBidSettingLastRecordsResponse getBidSettingLastRecords(GetBidSettingLastRecordsParameter parameters) {
		GetBidSettingLastRecordsResponse result = new GetBidSettingLastRecordsResponse();
		String spName = "up_wa_Bid_Admin_SearchSettingRecords";
		List<Object> params = new ArrayList<Object>();

		params.add(parameters.getSpotId());
		params.add(parameters.getFromDate());
		params.add(parameters.getToDate());
		params.add(parameters.getWeekDay());

		List<Object> _result = jdbcHelper.executeSP(spName, params, BidSettingLastRecords.class);
		result.setBidSettingLastRecords((List<BidSettingLastRecords>) _result.get(0));

		return result;
	}

	/**
	 * Set BidSetting
	 * 
	 * @since 2018. 10. 04.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	public ResultCode setBidSetting(SetBidSettingParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);

		String spName = "up_GenerateAdBid";
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getDataFlag());
		params.add(parameters.getSpotId());
		params.add(parameters.getFromData());
		params.add(parameters.getStartedOn());
		params.add(parameters.getEndedOn());
		params.add(parameters.getStartingPrice());
		params.add(parameters.getPriceUnit());
		params.add(parameters.getBuyItNowPrice());

		List<Object> outputParams = new ArrayList<Object>();
		outputParams.add(0);

		jdbcHelper.executeSP(spName, params, outputParams);

		result.setSuccess(true);
		result.setResultCode((Integer) outputParams.get(0));

		return result;
	}

	/**
	 * Get BidSetting
	 * 
	 * @since 2018. 10. 08.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GetBidSettingResponse getBidSetting(GetBidSettingParameter parameters) {
		GetBidSettingResponse result = new GetBidSettingResponse();

		String spName = "up_Bid_Admin_SettingList";
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getPageId());
		params.add(parameters.getSpotId());
		params.add(parameters.getFromDate());
		params.add(parameters.getToDate());
		params.add(parameters.getWeekDay());

		List<Object> _result = jdbcHelper.executeSP(spName, params, BidSetting.class);
		result.setBidSetting((List<BidSetting>) _result.get(0));

		return result;
	}

	/**
	 * Get BidSetting Last Week
	 * 
	 * @since 2018. 10. 08.
	 * @author Junghwan Lee
	 * @param top
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GetBidSettingLastWeekResponse getBidSettingLastWeek(Integer top) {
		GetBidSettingLastWeekResponse result = new GetBidSettingLastWeekResponse();
		
		String spName = "up_wa_Bid_Admin_AdVendorPeriodWeekly";
		List<Object> params = new ArrayList<Object>();
		params.add(top);
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, BidSettingLastWeek.class, BidSettingLastWeek.class);
		result.setBidSettingLastWeek((List<BidSettingLastWeek>) _result.get(0));
		result.setBidSettingLastWeek1((List<BidSettingLastWeek>) _result.get(1));
		
		return result;
	}
	
	@Transactional("primaryTransactionManager")
	public ResultCode acceptBids() {		
		LocalDateTime finalizedOn = LocalDateTime.now();
        String sessionId = UUID.randomUUID().toString();
        
		try {
			List<AdBidSetting> adBidSettingList = adBidSettingRepository.getFinalizeAdBidSettingTargetList();			
			adBidSettingList.forEach(adBidSetting -> {
				logger.info("adBidSetting : " + adBidSetting);
				
				//  update Ad_Bid_Setting (finalizedOn, finalizedBy)
				adBidSetting.setFinalizedOn(finalizedOn);
				adBidSetting.setFinalizedBy("AUTO");
				adBidSettingRepository.save(adBidSetting);
				
				List<AdBid> adBidList = adBidRepository.findByBidSettingIdAndStatusId(adBidSetting.getBidSettingId(), 1);
				logger.info("adBidList.size : " + adBidList.size());
				
				adBidList.forEach(adBid -> {
					//  update Ad_Bid (finalizedBidAmount, finalizedOn, finalizedBy)
					adBid.setFinalizedBidAmount(calculateBidAmount(adBid.getBidAmount(), adBid.getMaxBidAmount(), adBidSetting.getBidPriceUnit()));
					adBid.setBidAmount(calculateBidAmount(adBid.getBidAmount(), adBid.getMaxBidAmount(), adBidSetting.getBidPriceUnit()));
					adBid.setFinalizedOn(finalizedOn);
					adBid.setFinalizedBy("AUTO");
					adBidRepository.save(adBid);
					
					//  insert Ad_Bid_Log
					addAdBidLog(adBid, finalizedOn, "FINAL");
					
					//  update Ad_Vendor & Ad_Purchase 
					AdVendor adVendor = adVendorRepository.findTopBySpotIDAndFromDateAndWholeSalerIDIsNull(
							adBidSetting.getSpotId(), Date.from(adBidSetting.getFromDate().atZone(ZoneId.systemDefault()).toInstant()));
					addToAdVendorAndAdPurchase(adVendor, adBid, sessionId, finalizedOn, "AUTO");
				});

				redisListingAdBidSpotTemplate.opsForHash().delete(BIDDING_TOP_MAP_HASH, String.valueOf(adBidSetting.getBidSettingId()));
			});
		} catch (Exception e) {
			logger.error("acceptBids error :", e.getMessage());
			throw e;
		}
		
		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}
	
	@Transactional("primaryTransactionManager")
	public ResultCode editBid(String spotId, String adDateStr, String bidIds, String adminId) throws Exception {
		String[] bidIdArray = bidIds.split(",");
		if (bidIdArray.length == 0) {
			return new ResultCode(false, -1, "bidIds not accepted.");
		}
		List<Integer> bidIdList = Arrays.asList(bidIdArray).stream().map(Integer::parseInt).collect(Collectors.toList());
		
		LocalDateTime finalizedOn = LocalDateTime.now();
        String sessionId = UUID.randomUUID().toString();        
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date adDate = formatter.parse(adDateStr);

		try {
			// get BidSetting with spotId, adDate
			List<AdBidSetting> adBidSettingList = adBidSettingRepository.findBySpotIdAndFromDate(Integer.parseInt(spotId), 
					LocalDateTime.ofInstant(formatter.parse(adDateStr).toInstant(), ZoneId.systemDefault()));
			if (CollectionUtils.isEmpty(adBidSettingList) || adBidSettingList.size() != 1) {
				return new ResultCode(false, -1, "adBidSettingList is null or multiple.");
			}		
			AdBidSetting adBidSetting = adBidSettingList.get(0);
			logger.info("adBidSetting : " + adBidSetting);
			
			//  update Ad_Bid_Setting (finalizedOn, finalizedBy)
			adBidSetting.setFinalizedOn(finalizedOn);
			adBidSetting.setFinalizedBy(adminId);
			adBidSettingRepository.save(adBidSetting);
			
			// get AdBid with status=1 (for changing from winner to outbid when bidid not exist in request bidid list)
			Iterable<AdBid> originAdBidWinnerList = adBidRepository.findByBidSettingIdAndStatusId(adBidSetting.getBidSettingId(), 1);
			originAdBidWinnerList.forEach(adBid -> {
				if (!bidIdList.contains(adBid.getBidId())) {
					// update Ad_Bid (statusId(2), finalizedBidAmount, finalizedOn, finalizedBy)
					adBid.setStatusId(2);
					adBid.setFinalizedBidAmount(null);
					adBid.setFinalizedOn(finalizedOn);
					adBid.setFinalizedBy(adminId);
					adBidRepository.save(adBid);
					
					//  insert Ad_Bid_Log
					addAdBidLog(adBid, finalizedOn, adminId);
					
					//  update Ad_Vendor & Ad_Purchase
					AdVendor adVendor = adVendorRepository.findTopBySpotIDAndFromDateAndWholeSalerID(Integer.parseInt(spotId), adDate, adBid.getWholeSalerId());
					addToAdVendorAndAdPurchase(adVendor, null, null, finalizedOn, adminId);
				}
			});			
						
			// get AdBid in bidids
			Iterable<AdBid> adBidWinnerList = adBidRepository.findAllById(bidIdList);					
			adBidWinnerList.forEach(adBid -> {
				boolean needUpdate = false;
				
				// update Ad_Bid (statusId(1), finalizedBidAmount, finalizedOn, finalizedBy)
				if (adBid.getStatusId() == 2) {
					logger.info("Update Bidid : " + adBid.getBidId());
					needUpdate = true;
					adBid.setStatusId(1);
					adBid.setFinalizedBidAmount(calculateBidAmount(adBid.getBidAmount(), adBid.getMaxBidAmount(), adBidSetting.getBidPriceUnit()));
					adBid.setBidAmount(calculateBidAmount(adBid.getBidAmount(), adBid.getMaxBidAmount(), adBidSetting.getBidPriceUnit()));
				}
				adBid.setFinalizedOn(finalizedOn);
				adBid.setFinalizedBy(adminId);
				adBidRepository.save(adBid);
					
				//  insert Ad_Bid_Log
				addAdBidLog(adBid, finalizedOn, adminId);

				//  update Ad_Vendor & Ad_Purchase
				if (needUpdate) {
					AdVendor adVendor = adVendorRepository.findTopBySpotIDAndFromDateAndWholeSalerIDIsNull(Integer.parseInt(spotId), adDate);
					addToAdVendorAndAdPurchase(adVendor, adBid, sessionId, finalizedOn, adminId);
				}
			});
		} catch (Exception e) {
			logger.error("editBid error :", e.getMessage());
			throw e;
		}
		
		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}
	
	private BigDecimal calculateBidAmount(BigDecimal bidAmount, BigDecimal maxBidAmount, BigDecimal bidPriceUnit) {
		return BigDecimal.valueOf(
				Math.round(
						(bidAmount.longValue() 
							+ (maxBidAmount == null || maxBidAmount.longValue() == 0L ? bidAmount.longValue() : maxBidAmount.longValue())
						) / 2 / bidPriceUnit.longValue()
				) * bidPriceUnit.longValue());
	}
	
	private void addAdBidLog(AdBid adBid, LocalDateTime finalizedOn, String finalizedBy) {
		AdBidLog adBidLog = new AdBidLog();
		adBidLog.setBidId(adBid.getBidId());
		adBidLog.setBidSettingId(adBid.getBidSettingId());
		adBidLog.setWholeSalerId(adBid.getWholeSalerId());
		adBidLog.setBidAmount(adBid.getFinalizedBidAmount() == null ? adBid.getBidAmount() : adBid.getFinalizedBidAmount());
		adBidLog.setBiddedOn(adBid.getBiddedOn());
		adBidLog.setStatusId(adBid.getStatusId());
		adBidLog.setMaxBidAmount(adBid.getMaxBidAmount());
		adBidLog.setBiddedBy(adBid.getBiddedBy());
		adBidLog.setCreatedOn(finalizedOn);
		adBidLog.setCreatedBy(finalizedBy);
		adBidLog.setOriginBidId(0);
		adBidLogRepository.save(adBidLog);
	}
	
	private void addToAdVendorAndAdPurchase(AdVendor adVendor, AdBid adBid, String sessionId, LocalDateTime finalizedOn, String finalizedBy) {
		adVendor.setWholeSalerID(adBid == null ? null : adBid.getWholeSalerId());
		adVendor.setActualPrice(adBid == null ? null : adBid.getFinalizedBidAmount());
		adVendor.setHowToInput(adBid == null ? null : 2);
		adVendor.setHowtoSell(adBid == null ? null : 2);
		adVendor.setActive(adBid == null ? false : true);
		adVendor.setModifiedOn(Date.from(finalizedOn.atZone(ZoneId.systemDefault()).toInstant()));
		adVendor.setModifiedBy(finalizedBy);
		adVendorRepository.save(adVendor);
		logger.info("adVendor.getAdID() : " + adVendor.getAdID());

		AdPurchase adPurchase = adPurchaseRepository.findTopByAdId(adVendor.getAdID());
		if (adPurchase == null) {
			adPurchase = new AdPurchase();
			adPurchase.setAdId(adVendor.getAdID());
		}
		adPurchase.setPurchaseSessionId(sessionId == null ? "" : sessionId);
		adPurchase.setWholeSalerId(adBid == null ? 0 : adBid.getWholeSalerId());
		adPurchase.setPurchaseAmount(adBid == null ? BigDecimal.valueOf(0L) : adBid.getFinalizedBidAmount());
		adPurchase.setPurchaseTypeId(2);
		adPurchase.setPoNumber(adBid == null ? "" : String.format("FGAB-%010d", adVendor.getAdID()));
		adPurchase.setCreatedOn(finalizedOn);
		adPurchase.setCreatedBy(finalizedBy);				
		adPurchase.setModifiedOn(finalizedOn);
		adPurchase.setModifiedBy(finalizedBy);				
		adPurchaseRepository.save(adPurchase);
	}

	public ResultCode cancelBid(Integer bidId, String adminId) throws InterruptedException {
		LocalDateTime finalizedOn = LocalDateTime.now();

		Optional<AdBid> optionalAdBid = adBidRepository.findById(bidId);
		if (optionalAdBid.isPresent()) {
			AdBid adBidToCancel = optionalAdBid.get();
			int bidSettingId = adBidToCancel.getBidSettingId();
			String cacheHashKey = String.valueOf(bidSettingId);

			// acquire lock
			RLock lock = redisson.getLock(cacheHashKey);
			boolean acquired = lock.tryLock(REDIS_LOCK_ACQUIRE_TIMEOUT_SECONDS, REDIS_LOCK_EXPIRE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
			if (!acquired) {
				logger.warn("cancelBid({}) Could not acquire lock for bidSettingId {}", bidId, bidSettingId);
				return new ResultCode(false, -1, "Fail to acquire lock");
			}

			try {
				// get cache instance
				ListingAdBidSpot bidSpot = getFromCache(cacheHashKey, () -> {
					// check if db data exists when cache is empty
					return getWinningBidsFromDB(adBidToCancel.getBidSettingId());
				});
				List<ListingAdBid> bidList = bidSpot.getBidList();

				List<AdBid> otherAdBidByWholesalerList = adBidRepository.findByBidSettingIdAndWholeSalerId(bidSettingId, adBidToCancel.getWholeSalerId()).stream()
						.filter(adBid -> adBid.getBidId() < bidId)
						.collect(toList());

				// update Ad_Bid
				updateAdStatus(adBidToCancel, "USER", 0, 7, finalizedOn, adminId);
				// insert Entity_ActionLog (EntityTypeId(5), EntityId(bidId), ActionId(2001), ActedOn(now), ActedBy, Remark(bidding cancelled from webadmin))
				saveCancelToEntityActionLog(bidId, adminId, finalizedOn);
				for (AdBid adBid : otherAdBidByWholesalerList) {
					updateAdStatus(adBid, "USER", adBidToCancel.getBidId(), 7);
					saveCancelToEntityActionLog(adBid.getBidId(), adminId, finalizedOn);
				}

				boolean removed = bidList.removeIf(listingAdBid -> listingAdBid.getBidId() == bidId ||
						listingAdBid.getWid() == adBidToCancel.getWholeSalerId() && listingAdBid.getBidId() < bidId);

				// if cache is empty or not in winning bids, update db and return true
				if (!removed) {
					return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
				}

				List<Integer> winnerWholesalerIds = bidList.stream().map(ListingAdBid::getWid).collect(toList());
				AdBid newWinningBid = adBidRepository.findFirstByBidSettingIdAndStatusIdAndWholeSalerIdNotInOrderByBidAmountDescBiddedOnAscBidIdAsc(bidSettingId, 2, winnerWholesalerIds);

				// if candidate not exists, update db & cache and return true
				if (newWinningBid == null) {
					// put cache instance after set bidId
					setToCache(cacheHashKey, bidSpot);
					return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
				}

				// add to bidList and remove outbids
				bidList.add(ListingAdBid.of(newWinningBid));

				// put cache instance after set bidId
				setToCache(cacheHashKey, bidSpot);

				// update chosen candidate's status to winning status
				updateAdStatus(newWinningBid, "AUTO", bidId, 1);
				logger.info("Modified to status {} of bidId {}", 1, newWinningBid.getBidId());

				return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
			} catch (Exception e) {
				logger.error("cancelBid() error!", e);
				return new ResultCode(false, -1, e.getMessage());
			} finally {
				// unlock cache instance
				lock.unlock();
			}
		} else {
			return new ResultCode(false, -1, "Bid not found.");
		}
	}

	public Object getListingAdBidSpotFromCache(Integer bidSettingId) {
		if (bidSettingId == null || bidSettingId == 0) {
			return redisListingAdBidSpotTemplate.boundHashOps(BIDDING_TOP_MAP_HASH).entries();
		} else {
			return getFromCache(String.valueOf(bidSettingId));
		}
	}

	private void saveCancelToEntityActionLog(Integer bidId, String adminId, LocalDateTime finalizedOn) {
		EntityActionLog actionLog = new EntityActionLog();
		actionLog.setEntityTypeID(5);
		actionLog.setEntityID(bidId);
		actionLog.setActionID(2001);
		actionLog.setActedOn(finalizedOn);
		actionLog.setActedBy(adminId);
		actionLog.setRemark("bidding cancelled from webadmin");
		entityActionLogRepository.save(actionLog);
	}

	private ListingAdBidSpot getWinningBidsFromDB(int bidSettingId) {
		List<AdBid> adBidList = adBidRepository.findByBidSettingIdAndStatusIdOrderByBidAmountDescBiddedOnAsc(bidSettingId, 1);
		ListingAdBidSpot newBidSpot = new ListingAdBidSpot();
		if (CollectionUtils.isEmpty(adBidList)) {
			newBidSpot.setBidList(new ArrayList<>());
		} else {
			newBidSpot.setBidList(adBidList.stream()
					.map(ListingAdBid::of)
					.collect(Collectors.toList()));
		}

		logger.info("Load bidList from DB : {}", newBidSpot);
		return newBidSpot;
	}

	private ListingAdBidSpot getFromCache(String cacheHashKey) {
		return getFromCache(cacheHashKey, ListingAdBidSpot::new);
	}

	private ListingAdBidSpot getFromCache(String cacheHashKey, Supplier<ListingAdBidSpot> other) {
		ListingAdBidSpot listingAdBidSpot = (ListingAdBidSpot) redisListingAdBidSpotTemplate.opsForHash().get(BIDDING_TOP_MAP_HASH, cacheHashKey);
		return Optional.ofNullable(listingAdBidSpot)
				.filter(bidSpot -> bidSpot.getBidList() != null && bidSpot.getBidList().size() != 0)
				.orElseGet(other).copy();
	}

	private void setToCache(String cacheHashKey, ListingAdBidSpot bidSpot) {
		redisListingAdBidSpotTemplate.opsForHash().put(BIDDING_TOP_MAP_HASH, cacheHashKey, bidSpot);
	}

	private void updateAdStatus(AdBid adBid, String adLogCreateBy, int originalBidId, int statusId, LocalDateTime finalizedOn, String adminId) {
		adBid.setStatusId(statusId);
		adBid.setFinalizedOn(finalizedOn);
		adBid.setFinalizedBy(adminId);
		saveAdBidAndLog(adBid, adLogCreateBy, originalBidId);
	}

	private void updateAdStatus(AdBid adBid, String adLogCreateBy, int originalBidId, int statusId) {
		adBid.setStatusId(statusId);
		saveAdBidAndLog(adBid, adLogCreateBy, originalBidId);
	}

	private void saveAdBidAndLog(AdBid adBid, String adLogCreateBy, int originalBidId) {
		adBidRepository.save(adBid);
		adBidLogRepository.save(getAdBidLog(adBid, adLogCreateBy, originalBidId));
	}

	private AdBidLog getAdBidLog(AdBid adBid, String createBy, int originBidId) {
		AdBidLog adBidLog = new AdBidLog();
		adBidLog.setBidId(adBid.getBidId());
		adBidLog.setBidSettingId(adBid.getBidSettingId());
		adBidLog.setWholeSalerId(adBid.getWholeSalerId());
		adBidLog.setBidAmount(adBid.getBidAmount());
		adBidLog.setBiddedOn(adBid.getBiddedOn());
		adBidLog.setStatusId(adBid.getStatusId());
		adBidLog.setMaxBidAmount(adBid.getMaxBidAmount());
		adBidLog.setCreatedOn(LocalDateTime.now());
		adBidLog.setCreatedBy(createBy);
		adBidLog.setBiddedBy(adBid.getBiddedBy());
		adBidLog.setOriginBidId(originBidId);

		return adBidLog;
	}
}
