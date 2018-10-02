package net.fashiongo.webadmin.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.dao.primary.SecurityAccessCodeRepository;
import net.fashiongo.webadmin.model.pojo.SecurityAccessCodes;
import net.fashiongo.webadmin.model.pojo.parameter.GetSecurityAccessCodesParameters;
import net.fashiongo.webadmin.model.pojo.parameter.SetSecurityAccessCodeParameters;
import net.fashiongo.webadmin.model.pojo.response.GetSecurityAccessCodesResponse;
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
	public SetResultResponse SetDeleteSecurityAccessCodes(List<Integer> idList) throws Exception {
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
			throw new Exception();
		}

		return result;
	}
}
