package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.pojo.payment.response.GetPaymentStatusSearchOptionResponse;
import net.fashiongo.webadmin.model.primary.PaymentStatus;
import net.fashiongo.webadmin.model.primary.VendorCompany;

/**
 * 
 * @author DAHYE
 *
 */
@Service
public class WAPaymentService extends ApiService {
	/**
	 * 
	 * 
	 * @since 2018. 11. 01.
	 * @author Dahye
	 * @param 
	 * @return GetPaymentStatusSearchOptionResponse
	 */
	@SuppressWarnings("unchecked")
	public GetPaymentStatusSearchOptionResponse getPaymentStatusSearchOption() {
		GetPaymentStatusSearchOptionResponse result = new GetPaymentStatusSearchOptionResponse();
		String spName = "up_wa_pay_GetPaymentStatusSearchOption";
		List<Object> params = new ArrayList<Object>();
		List<Object> _results = jdbcHelper.executeSP(spName, params, VendorCompany.class, PaymentStatus.class);
		result.setVendorList((List<VendorCompany>) _results.get(0));
		result.setPaymentStatusList((List<PaymentStatus>) _results.get(1));
		return result;
	}
}
