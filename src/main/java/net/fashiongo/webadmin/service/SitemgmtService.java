package net.fashiongo.webadmin.service;

import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

//import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.dao.fgem.EmConfigurationRepository;
import net.fashiongo.webadmin.model.fgem.EmConfiguration;
import net.fashiongo.webadmin.model.pojo.ResultCode;
//import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetPaidCampaignParameter;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.response.GetPaidCampaignResponse;
import net.fashiongo.webadmin.model.primary.CollectionCategory;

/**
 * 
 * @author Sanghyup Kim
 */
@Service
public class SitemgmtService extends ApiService {

	@Autowired
	private EmConfigurationRepository emConfigurationRepository;

	/**
	 * 
	 * Get Category List
	 * 
	 * @since 2018. 9. 28.
	 * @author Sanghyup Kim
	 * @param GetCategoryListParameters
	 * @return GetCategoryListResponse
	 */
	@SuppressWarnings("unchecked")
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, transactionManager = "primaryTransactionManager")
//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, transactionManager = "primaryTransactionManager")
	public GetCategoryListResponse getCategoryList(GetCategoryListParameters parameters) {

		List<Object> params = new ArrayList<Object>();

		params.add(parameters.getCategoryId());
		params.add(parameters.getExpandAll());
		final String spName = "up_wa_GetCategoryList";

		final List<Object> _result = jdbcHelper.executeSP(spName, params, CollectionCategory.class);
		final List<CollectionCategory> collectionCategoryList = (List<CollectionCategory>) _result.get(0);

		GetCategoryListResponse resultSet = new GetCategoryListResponse();
		resultSet.setCategoryLst(collectionCategoryList);

		return resultSet;
	}

	/**
	 * 
	 * Get Paid Campaign
	 * 
	 * @since 2018. 10. 08.
	 * @author Nayeon Kim
	 * @return GetPaidCampaignResponse
	 */
	public GetPaidCampaignResponse getPaidCampaign() {
		GetPaidCampaignResponse getPaidCampaignResponse = new GetPaidCampaignResponse();
		List<EmConfiguration> configurationsList = emConfigurationRepository.findAll();
		getPaidCampaignResponse.setConfigurationsList(configurationsList);

		return getPaidCampaignResponse;
	}
	
	/**
	 * 
	 * Set Paid Campaign
	 * 
	 * @since 2018. 10. 11.
	 * @author Nayeon Kim
	 * @param SetPaidCampaignParameter
	 * @return 
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setPaidCampaign(SetPaidCampaignParameter parameters) {
		ResultCode result = new ResultCode(true, 1, MSG_SAVE_SUCCESS);
		
		EmConfiguration emConfiguration;
		List<EmConfiguration> emConfigurationList = parameters.getObjList();
		
		for (EmConfiguration emConfiguration2 : emConfigurationList) {
			emConfiguration = new EmConfiguration();
			emConfiguration.setConfigID(emConfiguration2.getConfigID());
			emConfiguration.setConfigType(emConfiguration2.getConfigType());
			emConfiguration.setConfigValue(emConfiguration2.getConfigValue());
			
			emConfigurationRepository.save(emConfiguration);
		}
		
		//emConfigurationRepository.findOneByConfigID(configID);

		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void GetPolicyManagement () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void SetAddDelPolicyManagement () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void GetPolicyDetail () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void GetPolicyManagementDetail () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void GetCommunicationReasonAll () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void DeleteCommunicationReason () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void SetCommunicationReasonActive () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void SetCommunicationReason () {
		
	}
	
	
}
