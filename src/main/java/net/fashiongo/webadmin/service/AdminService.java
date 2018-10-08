package net.fashiongo.webadmin.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.dao.primary.SecurityAccessCodeRepository;
import net.fashiongo.webadmin.model.pojo.Resource;
import net.fashiongo.webadmin.model.pojo.SecurityAccessCodes;
import net.fashiongo.webadmin.model.pojo.SecurityLogs;
import net.fashiongo.webadmin.model.pojo.SecurityLogsColumn;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityAccessCodesParameters;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityLogsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityResourcesParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessCodeParameters;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityAccessCodesResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityLogsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityResourcesResponse;
import net.fashiongo.webadmin.model.pojo.response.SetResultResponse;
import net.fashiongo.webadmin.model.primary.SecurityAccessCode;

/**
 * 
 * @author JungHwan
 */
@Service
public class AdminService extends ApiService {
	
	@Autowired
	private SecurityAccessCodeRepository securityAccessCodeRepository;

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
	 */
	@Transactional("primaryTransactionManager")
	public SetResultResponse SetSecurityAccessCode(SetSecurityAccessCodeParameters parameters) throws Exception {
		SetResultResponse result = new SetResultResponse();
		
		result.setSuccess(true);
		result.setResultCode(0);
		result.setResultMsg("Saved successfully!");
		
		try {
			SecurityAccessCode securityAccessCode = new SecurityAccessCode();

			if (parameters.getCodeID() != 0) {
				securityAccessCode = securityAccessCodeRepository.findOneByCodeID(parameters.getCodeID());
			}

			if (securityAccessCode != null) {
				securityAccessCode.setAccessCode(parameters.getAccessCode());
				
				SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
				Date dtExpiredOn = dt.parse(parameters.getExpiredOn());
				securityAccessCode.setExpiredOn(dtExpiredOn);
				
				securityAccessCodeRepository.save(securityAccessCode);
			}
		} catch (Exception ex) {
			result.setSuccess(false);
			result.setResultCode(-1);
			result.setResultMsg(ex.getMessage());
			throw new Exception();
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
	public SetResultResponse SetDeleteSecurityAccessCodes(List<Integer> idList) {
		SetResultResponse result = new SetResultResponse();
		result.setSuccess(true);
		result.setResultCode(0);
		result.setResultMsg("Deleted successfully!");

		try {
			for (Integer id : idList) {
				securityAccessCodeRepository.deleteById(id);
			}
		} catch (Exception ex) {
			result.setSuccess(false);
			result.setResultCode(-1);
			result.setResultMsg(ex.getMessage());
			//throw new Exception();
		}

		return result;
	}
	/**
	 * 
	 * Get Security Log
	 * 
	 * @since 2018. 10. 02.
	 * @author nayeon
	 * @param GetSecurityLogsParameter
	 * @return GetSecurityLogsResponse
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public GetSecurityLogsResponse getSecuritylogs(GetSecurityLogsParameter parameters) throws Exception {
		GetSecurityLogsResponse result = new GetSecurityLogsResponse();
		String spName = "up_Security_GetLoginLog";
		List<Object> params = new ArrayList<Object>();

		params.add(parameters.getPageNum());
		params.add(parameters.getPageSize());
		params.add(parameters.getUsrId());
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
	
	public GetSecurityResourcesResponse GetSecurityResources (GetSecurityResourcesParameter parameters) {
		GetSecurityResourcesResponse result = new GetSecurityResourcesResponse();
		String spName = "up_wa_Security_GetResource";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getResourceName());
        params.add(parameters.getResourceParent());
        params.add(parameters.getResourceType());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, Resource.class);
        result.setResource((List<Resource>)_result.get(0));
		return result;
	}

	
	
}
