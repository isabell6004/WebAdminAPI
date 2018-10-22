package net.fashiongo.webadmin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.dao.primary.SecurityAccessCodeRepository;
import net.fashiongo.webadmin.dao.primary.SecurityAccessIpsRepository;
import net.fashiongo.webadmin.dao.primary.SecurityMenuRepository;
import net.fashiongo.webadmin.dao.primary.SecurityResourceRepository;
import net.fashiongo.webadmin.model.pojo.Resource;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.SecurityAccessCodes;
import net.fashiongo.webadmin.model.pojo.SecurityLogs;
import net.fashiongo.webadmin.model.pojo.SecurityLogsColumn;
import net.fashiongo.webadmin.model.pojo.SecurityMenus2;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityAccessCodesParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityLogsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityMenus2Parameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityResourcesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetActiveSecurityMenusParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDeleteSecurityMenusParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessCodeParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessIpParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityMenuParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityResourceParameter;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityAccessCodesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityAccessIpsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityLogsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityMenus2Response;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityParentMenusResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityResourcesResponse;
import net.fashiongo.webadmin.model.primary.SecurityAccessCode;
import net.fashiongo.webadmin.model.primary.SecurityAccessIp;
import net.fashiongo.webadmin.model.primary.SecurityMenu;
import net.fashiongo.webadmin.model.primary.SecurityResource;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * 
 * @author JungHwan
 */
@Service
public class AdminService extends ApiService {
	
	@Autowired
	private SecurityMenuRepository securityMenuRepository;
	
	@Autowired
	private SecurityAccessCodeRepository securityAccessCodeRepository;
	
	@Autowired
	private SecurityAccessIpsRepository securityAccessIpsRepository;
	
	@Autowired
	private SecurityResourceRepository securityResourceRepository;

	/**
	 * Get Security Access Code
	 * 
	 * @since 2018. 10. 01.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GetSecurityAccessCodesResponse GetSecurityAccessCodes(GetSecurityAccessCodesParameters parameters) {
		GetSecurityAccessCodesResponse result = new GetSecurityAccessCodesResponse();
		String spName = "up_Security_GetAccessCode";
		List<Object> params = new ArrayList<Object>();

		params.add(parameters.getAccessCode());
		params.add(parameters.getsDate());
		params.add(parameters.geteDate());

		List<Object> _result = jdbcHelper.executeSP(spName, params, SecurityAccessCodes.class);

		result.setSecurityAccessCodes((List<SecurityAccessCodes>) _result.get(0));

		return result;
	}

	/**
	 * Set Security Access Code
	 * 
	 * @since 2018. 10. 02.
	 * @author Junghwan Lee
	 * @param parameters
	 * @return
	 * @throws Exception 
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode SetSecurityAccessCode(SetSecurityAccessCodeParameters parameters) {
		ResultCode result = new ResultCode(true, 0, "Saved successfully!");
		
		SecurityAccessCode securityAccessCode = new SecurityAccessCode();

		if (parameters.getCodeID() != 0) {
			securityAccessCode = securityAccessCodeRepository.findOneByCodeID(parameters.getCodeID());
		}

		if (securityAccessCode != null) {
			securityAccessCode.setAccessCode(parameters.getAccessCode());
			
			SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
			Date dtExpiredOn = null;
			try {
				dtExpiredOn = dt.parse(parameters.getExpiredOn());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			securityAccessCode.setExpiredOn(dtExpiredOn);
			
			securityAccessCodeRepository.save(securityAccessCode);
		}
		
		return result;
	}
	
	/**
	 * Delete Security Access Code
	 * 
	 * @since 2018. 10. 02.
	 * @author Junghwan Lee
	 * @param idList
	 * @return
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode SetDeleteSecurityAccessCodes(List<Integer> idList) {
		ResultCode result = new ResultCode(true, 0, "Deleted successfully!");

		for (Integer id : idList) {
			securityAccessCodeRepository.deleteById(id);
		}

		return result;
	}
	/**
	 * 
	 * Get Security Log
	 * 
	 * @since 2018. 10. 02.
	 * @author Nayeon Kim
	 * @param GetSecurityLogsParameter
	 * @return GetSecurityLogsResponse
	 */
	@SuppressWarnings("unchecked")
	public GetSecurityLogsResponse getSecuritylogs(GetSecurityLogsParameter parameters) {
		GetSecurityLogsResponse result = new GetSecurityLogsResponse();
		String spName = "up_Security_GetLoginLog";
		List<Object> params = new ArrayList<Object>();

		params.add(parameters.getPagenum());
		params.add(parameters.getPagesize());
		params.add(parameters.getUsrid());
		params.add(parameters.getIp());
		params.add(parameters.getStartDate());
		params.add(parameters.getEndDate());
			
		List<Object> _result = jdbcHelper.executeSP(spName, params, SecurityLogs.class, SecurityLogsColumn.class);
		List<SecurityLogs> securityLogs = (List<SecurityLogs>) _result.get(0);
		List<SecurityLogsColumn> securityLogsColumn = (List<SecurityLogsColumn>) _result.get(1);

		result.setSecurityLogs(securityLogs);
		result.setSecurityLogsColumn(securityLogsColumn);
		
		return result;
	}
	
	/**
	 * 
	 * Get security resources
	 * @since 2018. 10. 2.
	 * @author Dahye Jeong
	 * @param GetSecurityResourcesParameter
	 * @return GetSecurityResourcesResponse
	 */
	@SuppressWarnings("unchecked")
	public GetSecurityResourcesResponse GetSecurityResources (GetSecurityResourcesParameter parameters) {
		GetSecurityResourcesResponse result = new GetSecurityResourcesResponse();
		String spName = "up_wa_Security_GetResource";
        List<Object> params = new ArrayList<Object>();

        params.add(parameters.getApplication());
        params.add(parameters.getResourceName());
        params.add(parameters.getResourceParent());
        params.add(parameters.getResourceType());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, Resource.class);
        result.setResource((List<Resource>)_result.get(0));
		return result;
	}

	/**
	 * 
	 * Get Security Access Ips
	 * @since 2018. 10. 10.
	 * @author Dahye Jeong
	 * @param null
	 * @return GetSecurityAccessIpsResponse
	 */
	@SuppressWarnings("unchecked")
	public GetSecurityAccessIpsResponse GetSecurityAccessIps() {
		GetSecurityAccessIpsResponse result = new GetSecurityAccessIpsResponse();
		String spName = "up_wa_Security_GetListIP";
		
		List<Object> params = new ArrayList<Object>();
		List<Object> _result = jdbcHelper.executeSP(spName, params, SecurityAccessIp.class);
		result.setIps((List<SecurityAccessIp>) _result.get(0));
		return result;
	}
	
	/**
	 * 
	 * Set Security Access Ip
	 * @since 2018. 10. 10.
	 * @author Dahye Jeong
	 * @param SetSecurityAccessIpParameter
	 * @return ResultCode
	 */
	@SuppressWarnings("unchecked")
	public ResultCode SetSecurityAccessIp(SetSecurityAccessIpParameter parameters) throws Exception {		
		SecurityAccessIp securityAccessIps = new SecurityAccessIp();
		if (parameters.getIpid() != 0) {
			securityAccessIps = securityAccessIpsRepository.findFirstByipid(parameters.getIpid());
		}
		if (securityAccessIps != null) {
			securityAccessIps.setIpAddress(parameters.getIp());
			securityAccessIps.setDescription(parameters.getDescription());
			securityAccessIpsRepository.save(securityAccessIps);
		}
		return new ResultCode(true, 0, MSG_SAVE_SUCCESS);
	}
	
	/**
	 * 
	 * Set Delete Security Access Ips
	 * @since 2018. 10. 10.
	 * @author Dahye Jeong
	 * @param id list
	 * @return ResultCode
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode SetDeleteSecurityAccessIps(List<Integer> idList) {
		securityAccessIpsRepository.deleteByipidIn(idList);
		return new ResultCode(true, idList.size(), MSG_DELETE_SUCCESS);
	}
	
	
	/**
	 * 
	 * Set Resource
	 * @since 2018. 10. 12.
	 * @author Dahye Jeong
	 * @param resourceID, active
	 * @return ResultCode
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode SetResource(Integer resourceID, boolean active) {
		SecurityResource sr = securityResourceRepository.findOneByResourceID(resourceID);
		if(sr != null) {
			sr.setActive(active);
			securityResourceRepository.save(sr);
		}
		return new ResultCode(true, 1, MSG_UPDATE_SUCCESS);
	}
	
	/**
	 * 
	 * Set Security Resource
	 * @since 2018. 10. 12.
	 * @author Dahye Jeong
	 * @param SetSecurityResourceParameter
	 * @return ResultCode
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode SetSecurityResource(SetSecurityResourceParameter parameters) {
		SecurityResource sr = new SecurityResource();
		if(parameters.getResourceID() != 0) {
			sr = securityResourceRepository.findOneByResourceID(parameters.getResourceID());
		}
		
		if(sr != null) {
			sr.setName(parameters.getResourceName());
			sr.setApplicationID(parameters.getApplicationid());
			sr.setResourceType(parameters.getResourceType());
			sr.setUrl(parameters.getResourceUrl());
			sr.setActive(parameters.getActive());
			securityResourceRepository.save(sr);
		}
		return new ResultCode(true, 0, MSG_SAVE_SUCCESS);
	}
	
	/**
	 * 
	 * Set Delete Security Resources
	 * @since 2018. 10. 12.
	 * @author Dahye Jeong
	 * @param idList
	 * @return ResultCode
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode SetDeleteSecurityResources(List<Integer> idList) {
		securityResourceRepository.deleteByResourceIDIn(idList);
		return new ResultCode(true, 1, MSG_DELETE_SUCCESS);
	}
	
	/**
	 * 
	 * Get Security Parent Menus
	 * @since 2018. 10. 16.
	 * @author Jiwon Kim
	 * @return GetSecurityParentMenus
	 */
	@SuppressWarnings("unchecked")
	public GetSecurityParentMenusResponse GetSecurityParentMenus() {
		GetSecurityParentMenusResponse results = new GetSecurityParentMenusResponse();
		List<SecurityMenu> result = (List<SecurityMenu>) securityMenuRepository.findAllByParentIDAndApplicationIDOrderByActiveDescNameAsc(null,1);
		results.setSecurityMenu(result);
		return results;
	}
	
	/**
	 * 
	 * Get Security Menus2
	 * @since 2018. 10. 16.
	 * @author Jiwon Kim
	 * @return GetSecurityMenus2
	 */
	@SuppressWarnings("unchecked")
	public GetSecurityMenus2Response GetSecurityMenus2(GetSecurityMenus2Parameter parameters) {

		GetSecurityMenus2Response result = new GetSecurityMenus2Response();
		String spName = "up_wa_GetSecurityMenus2";
		
		List<Object> params = new ArrayList<Object>();
		

        params.add(parameters.getMenuname());
        params.add(parameters.getParentmenuid());
        params.add(parameters.getApplicationid());
        params.add(parameters.getActive());
		
		List<Object> _result = jdbcHelper.executeSP(spName, params, SecurityMenus2.class);
		result.setSecurityMenu((List<SecurityMenus2>) _result.get(0));
		return result;
	}
	
	/**
	 * 
	 * Set Security Resource
	 * @since 2018. 10. 12.
	 * @author Dahye Jeong
	 * @param SetSecurityResourceParameter
	 * @return ResultCode
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode SetSecurityMenu(SetSecurityMenuParameter parameters) {
		ResultCode result = new ResultCode(true, 0, MSG_SAVE_SUCCESS);
		SecurityMenu ssm = new SecurityMenu();
		if (parameters.getMenuid()==0)
		{
			SecurityMenu sm = securityMenuRepository.findOneByResourceID(parameters.getResourceid());
			if (sm != null)
			{
				result.setSuccess(false);
				result.setResultCode(0);
				result.setResultMsg("resource duplicated!");
				return result;
			}
		}
		else
		{
			SecurityMenu sm = securityMenuRepository.findOneByResourceIDAndMenuIDNot(parameters.getResourceid(),parameters.getMenuid());
			if (sm != null)
			{
				result.setSuccess(false);
				result.setResultCode(0);
				result.setResultMsg("resource duplicated!");
				return result;
			}
			ssm = securityMenuRepository.findOneByMenuID(parameters.getMenuid());
		}
		
		if(ssm != null) {
			ssm.setName(parameters.getMenuname());
			ssm.setApplicationID(parameters.getApplicationid());
			ssm.setParentID(parameters.getParentid());
			ssm.setResourceID(parameters.getResourceid());
			ssm.setRoutePath(parameters.getRoutepath());
			ssm.setMenuIcon(parameters.getMenuicon());
			ssm.setListOrder(parameters.getListorder());
			ssm.setVisible(parameters.getVisible());
			ssm.setActive(parameters.getActive());
			securityMenuRepository.save(ssm);
		}
		return result;
	}
	
	/**
	 * 
	 * Set Delete Security Menus
	 * 
	 * @since 2018. 10. 16.
	 * @author Jiwon Kim
	 * @param SetDeleteSecurityMenusParameter
	 * @return SetDeleteSecurityMenus
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode SetDeleteSecurityMenus(SetDeleteSecurityMenusParameter parameters) {
		ResultCode result = new ResultCode(true, 0, MSG_DELETE_SUCCESS);
		List<Integer> deleteids = parameters.getIdList();
		for (Integer id : deleteids) {
			securityMenuRepository.deleteById(id);
		}
		return result;
	}
	/**
	 * 
	 * Set Active Security Menus
	 * 
	 * @since 2018. 10. 16.
	 * @author Jiwon Kim
	 * @param SetActiveSecurityMenusParameter
	 * @return SetActiveSecurityMenus
	 */
	@Transactional("primaryTransactionManager")
	public ResultCode SetActiveSecurityMenus(SetActiveSecurityMenusParameter parameters) {
		ResultCode result = new ResultCode(true, 0, MSG_UPDATE_SUCCESS);
		SecurityMenu ssm = securityMenuRepository.findOneByMenuID(parameters.getMenuID());
		ssm.setActive(parameters.getActive());
		securityMenuRepository.save(ssm);
		return result;
	}
}
