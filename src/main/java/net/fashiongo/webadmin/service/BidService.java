package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.pojo.BidSettingLastRecords;
import net.fashiongo.webadmin.model.pojo.parameter.GetBidSettingLastRecordsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetBidSettingLastRecordsResponse;

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
	public GetBidSettingLastRecordsResponse GetBidSettingLastRecords(GetBidSettingLastRecordsParameter parameters) {
		GetBidSettingLastRecordsResponse result= new GetBidSettingLastRecordsResponse();
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
}
