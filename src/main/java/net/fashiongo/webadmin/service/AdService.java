package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.pojo.AdSettingSubList;
import net.fashiongo.webadmin.model.pojo.AdSettingList;
import net.fashiongo.webadmin.model.pojo.response.GetADSettingResponse;

@Service
public class AdService extends ApiService {
	
	/**
	 * 
	 * Get AD Setting
	 * 
	 * @since 2018. 10. 2.
	 * @author Nayeon Kim
	 * @return GetADSettingResponse
	 */
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, transactionManager = "primaryTransactionManager")

    public GetADSettingResponse getAdsetting() {
    	GetADSettingResponse resultSet = new GetADSettingResponse();
    	String spName = "up_wa_GetAdSetting";
    	List<Object> params = new ArrayList<Object>();

    	List<Object> _result = jdbcHelper.executeSP(spName, params, AdSettingSubList.class, AdSettingList.class);
		List<AdSettingSubList> adsettingsublist= (List<AdSettingSubList>) _result.get(0);
		List<AdSettingList> adsettinglist= (List<AdSettingList>) _result.get(1);
		
		resultSet.setAdsettingsublist(adsettingsublist);
		resultSet.setAdsettinglist(adsettinglist);
   
		return resultSet;
    }
}
