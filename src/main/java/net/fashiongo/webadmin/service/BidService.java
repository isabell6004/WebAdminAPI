package net.fashiongo.webadmin.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.dao.primary.AdBidLogRepository;
import net.fashiongo.webadmin.dao.primary.AdBidRepository;
import net.fashiongo.webadmin.dao.primary.AdBidSettingRepository;
import net.fashiongo.webadmin.dao.primary.AdPurchaseRepository;
import net.fashiongo.webadmin.dao.primary.AdVendorRepository;
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
		List<AdBidSetting> adBidSettingList = adBidSettingRepository.getFinalizeAdBidSettingTargetList();

		LocalDateTime finalizedOn = LocalDateTime.now();
        String sessionId = UUID.randomUUID().toString();
        
		try {
			adBidSettingList.forEach(adBidSetting -> {
				System.out.println("adBidSetting : " + adBidSetting);
				
				//  update Ad_Bid_Setting (finalizedOn, finalizedBy)
				adBidSetting.setFinalizedOn(finalizedOn);
				adBidSetting.setFinalizedBy("AUTO");
				adBidSettingRepository.save(adBidSetting);
				
				List<AdBid> adBidList = adBidRepository.findByBidSettingIdAndStatusId(adBidSetting.getBidSettingId(), 1);
				System.out.println("adBidList.size : " + adBidList.size());
				
				adBidList.forEach(adBid -> {

					//  update Ad_Bid (finalizedBidAmount, finalizedOn, finalizedBy)
					adBid.setBidAmount(
							BigDecimal.valueOf(
									Math.round((
											adBid.getCurrentBidAmount().longValue()	
											+ (adBid.getMaxBidAmount() == null || adBid.getMaxBidAmount().longValue() == 0L 
												? adBid.getCurrentBidAmount().longValue() : adBid.getMaxBidAmount().longValue())) 
											/ 2 / adBidSetting.getBidPriceUnit().longValue())
									* adBidSetting.getBidPriceUnit().longValue()));
					adBid.setFinalizedOn(finalizedOn);
					adBid.setFinalizedBy("AUTO");
					adBidRepository.save(adBid);
					
					//  insert Ad_Bid_Log
					AdBidLog adBidLog = makeAdBidLog(adBid);
					adBidLog.setCreatedOn(finalizedOn);
					adBidLog.setCreatedBy("FINAL");
					adBidLog.setOriginBidId(BigDecimal.valueOf(0));
					adBidLogRepository.save(adBidLog);
					
					//  update Ad_Vendor (wholeSalerId, actualPrice, howToInput(2), howtoSell(2), active(1), modifiedOn, modifiedBy)
					AdVendor adVendor = adVendorRepository.findTopBySpotIDAndFromDateAndWholeSalerIDIsNull(
							adBidSetting.getSpotId(), Date.from(adBidSetting.getFromDate().atZone(ZoneId.systemDefault()).toInstant()));
					adVendor.setWholeSalerID(adBid.getWholeSalerId());
					adVendor.setActualPrice(adBid.getBidAmount());
					adVendor.setHowToInput(2);
					adVendor.setHowtoSell(2);
					adVendor.setActive(true);
					adVendor.setModifiedOn(Date.from(finalizedOn.atZone(ZoneId.systemDefault()).toInstant()));
					adVendor.setModifiedBy("AUTO");
					adVendorRepository.save(adVendor);
					System.out.println("adVendor.getAdID() : " + adVendor.getAdID());

					//  update Ad_Purchase (purchaseSessionId, wholeSalerId, purchaseAmount, purchaseTypeId(2), poNumber(FGAB-adId(10character)), createdOn, createdBy, modifiedOn, modifiedBy)
					AdPurchase adPurchase = adPurchaseRepository.findTopByAdId(adVendor.getAdID());
					if (adPurchase == null) {
						adPurchase = new AdPurchase();
						adPurchase.setAdId(adVendor.getAdID());
					}
					adPurchase.setPurchaseSessionId(sessionId);
					adPurchase.setWholeSalerId(adBid.getWholeSalerId());
					adPurchase.setPurchaseAmount(adBid.getBidAmount());
					adPurchase.setPurchaseTypeId(2);
					adPurchase.setPoNumber(String.format("FGAB-%010d", adVendor.getAdID()));
					adPurchase.setCreatedOn(finalizedOn);
					adPurchase.setCreatedBy("AUTO");				
					adPurchase.setModifiedOn(finalizedOn);
					adPurchase.setModifiedBy("AUTO");				
					adPurchaseRepository.save(adPurchase);
				});
			});
		} catch (Exception e) {
			logger.error("acceptBids error :", e.getMessage());
			throw e;
		}
		
		return new ResultCode(true, 1, MSG_SAVE_SUCCESS);
	}
	
	private AdBidLog makeAdBidLog(AdBid adBid) {
		AdBidLog adBidLog = new AdBidLog();
		adBidLog.setBidId(adBid.getBidId());
		adBidLog.setBidSettingId(adBid.getBidSettingId());
		adBidLog.setWholeSalerId(adBid.getWholeSalerId());
		adBidLog.setBidAmount(adBid.getBidAmount());
		adBidLog.setBiddedOn(adBid.getBiddedOn());
		adBidLog.setStatusId(adBid.getStatusId());
		adBidLog.setMaxBidAmount(adBid.getMaxBidAmount());
		adBidLog.setBiddedBy(adBid.getBiddedBy());
		return adBidLog;
	}
	
}
