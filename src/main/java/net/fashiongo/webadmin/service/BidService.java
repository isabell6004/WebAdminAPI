package net.fashiongo.webadmin.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import net.fashiongo.webadmin.dao.primary.AdBidLogRepository;
import net.fashiongo.webadmin.dao.primary.AdBidRepository;
import net.fashiongo.webadmin.dao.primary.AdBidSettingRepository;
import net.fashiongo.webadmin.dao.primary.AdPurchaseRepository;
import net.fashiongo.webadmin.dao.primary.AdVendorRepository;
import net.fashiongo.webadmin.dao.primary.EntityActionLogRepository;
import net.fashiongo.webadmin.model.pojo.bid.BidSetting;
import net.fashiongo.webadmin.model.pojo.bid.BidSettingLastRecords;
import net.fashiongo.webadmin.model.pojo.bid.BidSettingLastWeek;
import net.fashiongo.webadmin.model.pojo.bid.parameter.GetBidSettingLastRecordsParameter;
import net.fashiongo.webadmin.model.pojo.bid.parameter.GetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.bid.parameter.SetBidSettingParameter;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingLastRecordsResponse;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingLastWeekResponse;
import net.fashiongo.webadmin.model.pojo.bid.response.GetBidSettingResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.primary.AdBid;
import net.fashiongo.webadmin.model.primary.AdBidLog;
import net.fashiongo.webadmin.model.primary.AdBidSetting;
import net.fashiongo.webadmin.model.primary.AdPurchase;
import net.fashiongo.webadmin.model.primary.AdVendor;
import net.fashiongo.webadmin.model.primary.EntityActionLog;

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
					adBid.setFinalizedBidAmount(calculateBidAmount(adBid.getCurrentBidAmount(), adBid.getMaxBidAmount(), adBidSetting.getBidPriceUnit()));
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
				// update Ad_Bid (statusId(1), finalizedBidAmount, finalizedOn, finalizedBy)
				adBid.setStatusId(1);
				adBid.setFinalizedBidAmount(calculateBidAmount(adBid.getCurrentBidAmount(), adBid.getMaxBidAmount(), adBidSetting.getBidPriceUnit()));
				adBid.setFinalizedOn(finalizedOn);
				adBid.setFinalizedBy(adminId);
				adBidRepository.save(adBid);
					
				//  insert Ad_Bid_Log
				addAdBidLog(adBid, finalizedOn, adminId);

				//  update Ad_Vendor & Ad_Purchase
				AdVendor adVendor = adVendorRepository.findTopBySpotIDAndFromDateAndWholeSalerIDIsNull(Integer.parseInt(spotId), adDate);
				addToAdVendorAndAdPurchase(adVendor, adBid, sessionId, finalizedOn, adminId);
			});
		} catch (Exception e) {
			logger.error("editBid error :", e.getMessage());
			throw e;
		}
		
		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}
	
	private BigDecimal calculateBidAmount(BigDecimal currentBidAmount, BigDecimal maxBidAmount, BigDecimal bidPriceUnit) {
		return BigDecimal.valueOf(
				Math.round(
						(currentBidAmount.longValue() 
							+ (maxBidAmount == null || maxBidAmount.longValue() == 0L ? currentBidAmount.longValue() : maxBidAmount.longValue())
						) / 2 / bidPriceUnit.longValue()
				) * bidPriceUnit.longValue());
	}
	
	private void addAdBidLog(AdBid adBid, LocalDateTime finalizedOn, String finalizedBy) {
		AdBidLog adBidLog = new AdBidLog();
		adBidLog.setBidId(adBid.getBidId());
		adBidLog.setBidSettingId(adBid.getBidSettingId());
		adBidLog.setWholeSalerId(adBid.getWholeSalerId());
		adBidLog.setBidAmount(adBid.getFinalizedBidAmount() == null ? adBid.getCurrentBidAmount() : adBid.getFinalizedBidAmount());
		adBidLog.setBiddedOn(adBid.getBiddedOn());
		adBidLog.setStatusId(adBid.getStatusId());
		adBidLog.setMaxBidAmount(adBid.getMaxBidAmount());
		adBidLog.setBiddedBy(adBid.getBiddedBy());
		adBidLog.setCreatedOn(finalizedOn);
		adBidLog.setCreatedBy(finalizedBy);
		adBidLog.setOriginBidId(BigDecimal.valueOf(0));
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
		adPurchase.setPurchaseSessionId(sessionId == null ? null : sessionId);
		adPurchase.setWholeSalerId(adBid == null ? null : adBid.getWholeSalerId());
		adPurchase.setPurchaseAmount(adBid == null ? null : adBid.getFinalizedBidAmount());
		adPurchase.setPurchaseTypeId(2);
		adPurchase.setPoNumber(adBid == null ? null : String.format("FGAB-%010d", adVendor.getAdID()));
		adPurchase.setCreatedOn(finalizedOn);
		adPurchase.setCreatedBy(finalizedBy);				
		adPurchase.setModifiedOn(finalizedOn);
		adPurchase.setModifiedBy(finalizedBy);				
		adPurchaseRepository.save(adPurchase);
	}
	
	@Transactional("primaryTransactionManager")
	public ResultCode cancelBid(Integer bidId, String adminId) {
		LocalDateTime finalizedOn = LocalDateTime.now();
		
		// update Ad_Bid
		Optional<AdBid> optionalAdBid = adBidRepository.findById(bidId);
		if (optionalAdBid.isPresent()) {
			AdBid adBid = optionalAdBid.get();
			adBid.setStatusId(7);
			adBid.setFinalizedOn(finalizedOn);
			adBid.setFinalizedBy(adminId);
			adBidRepository.save(adBid);
		} else {
			return new ResultCode(false, -1, "Bid not found.");
		}
		
		// insert Entity_ActionLog (EntityTypeId(5), EntityId(bidId), ActionId(2001), ActedOn(now), ActedBy, Remark(bidding cancelled from webadmin))
		EntityActionLog actionLog = new EntityActionLog();
		actionLog.setEntityTypeID(5);
		actionLog.setEntityID(bidId);
		actionLog.setActionID(2001);
		actionLog.setActedOn(finalizedOn);
		actionLog.setActedBy(adminId);
		actionLog.setRemark("bidding cancelled from webadmin");
		entityActionLogRepository.save(actionLog);
		
		// remove canceled bid from redis
		
		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}
	
}
