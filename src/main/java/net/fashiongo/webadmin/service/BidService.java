package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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

/**
 * 
 * @author JungHwan
 */
@Service
public class BidService extends ApiService {

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
}
