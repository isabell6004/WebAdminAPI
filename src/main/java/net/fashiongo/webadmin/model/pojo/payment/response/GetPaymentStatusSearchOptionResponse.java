package net.fashiongo.webadmin.model.pojo.payment.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import net.fashiongo.webadmin.model.primary.PaymentStatus;
import net.fashiongo.webadmin.model.primary.VendorCompany;

/**
 * 
 * @author DAHYE
 *
 */
@Data
public class GetPaymentStatusSearchOptionResponse {
	@JsonProperty("Table")
	private List<VendorCompany> vendorList;
	
	@JsonProperty("Table1")
	private List<PaymentStatus> paymentStatusList;
}
