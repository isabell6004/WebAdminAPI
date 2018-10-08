package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.pojo.Message;
import net.fashiongo.webadmin.model.pojo.TotalRate;
import net.fashiongo.webadmin.model.pojo.response.GetDashboardResponse;

/**
 * 
 * @author Incheol Jung
 */
@Service
public class StaticService extends ApiService {
	
	/**
	 * 
	 * Description Example
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @return
	 */
	public GetDashboardResponse GetDashboard() {
		String spName = "up_wa_GetAdminMessage";
        List<Object> params = new ArrayList<Object>();
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, TotalRate.class, Message.class);
        
		return null;
	}
}
