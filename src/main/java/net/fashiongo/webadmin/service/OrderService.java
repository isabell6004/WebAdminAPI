package net.fashiongo.webadmin.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.model.pojo.order.parameter.GetPrintPoUrlParameter;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;

/**
 * 
 * @author Reo
 *
 */
@Service
public class OrderService {
	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 23.
	 * @author Reo
	 * @param url
	 * @return
	 * @throws JsonProcessingException 
	 */
	public JsonResponse getWebRequest(GetPrintPoUrlParameter parameters) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String url = "" + parameters.getResultType() + "/po/" + parameters.getOrderSessionGUID() + "/" + parameters.getOids() + "?t=" + parameters.getT() + "&forPdf=" + parameters.getForPdf() + "&withImage=" + parameters.getWithImage() + "&withVendorStyleNo=" + parameters.getWithVendorStyleNo();
		JSONObject jsonObj = new JSONObject();
		JsonResponse<?> result = httpClient.get(url);
		
		return result;
	}
}
