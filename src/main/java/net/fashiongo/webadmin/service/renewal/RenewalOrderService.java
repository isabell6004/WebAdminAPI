package net.fashiongo.webadmin.service.renewal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fashiongo.webadmin.data.model.order.GetPrintPoUrlParameter2;
import net.fashiongo.webadmin.data.repository.primary.MergeOrdersEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.OrdersEntityRepository;
import net.fashiongo.webadmin.model.pojo.order.parameter.GetPrintPoUrlParameter;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RenewalOrderService {

	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;

	@Autowired
	private MergeOrdersEntityRepository mergeOrdersEntityRepository;

	@Autowired
	private OrdersEntityRepository ordersEntityRepository;

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
		String url = "/" + parameters.getResultType() + "/po/" + parameters.getOrderSessionGUID() + "/" + parameters.getOids() + "?t=" + parameters.getT() + "&forPdf=" + parameters.getForPdf() + "&withImage=" + parameters.getWithImage() + "&withVendorStyleNo=" + parameters.getWithVendorStyleNo();
		JSONObject jsonObj = new JSONObject();
		JsonResponse<?> result = httpClient.get(url);

		return result;
	}

	public JsonResponse getWebRequest2(GetPrintPoUrlParameter2 parameters) throws JsonProcessingException {
		int orderId = parameters.getOrderid();
		String orderSessionGUID = ordersEntityRepository.findById(parameters.getOrderid()).map(ordersEntity -> ordersEntity.getOrderSessionGUID()).orElse("");

		String url = "/" + parameters.getTresulttype() + "/po/" + orderSessionGUID + "/" + orderId + "?t=" + parameters.getT() + "&forPdf=" + parameters.getTforpdf() + "&withImage=" + parameters.getTwithimage() + "&withVendorStyleNo=" + parameters.getTwithvendorstyleno();
		JsonResponse<?> result = httpClient.get(url);

		return result;
	}

	public JsonResponse getPrintMergePOUrl(GetPrintPoUrlParameter2 parameters) {
		int orderId = parameters.getOrderid();

		String orderSessionGUID = mergeOrdersEntityRepository.getMergeOrderWholesalerGuid(orderId).orElse(null);


		String url = "/" + parameters.getTresulttype() + "/merged-po/" + orderSessionGUID + "/" + orderId + "?t=" + parameters.getT() + "&forPdf=" + parameters.getTforpdf() + "&withImage=" + parameters.getTwithimage() + "&withVendorStyleNo=" + parameters.getTwithvendorstyleno();
		JsonResponse<?> result = httpClient.get(url);

		return result;
	}
}
