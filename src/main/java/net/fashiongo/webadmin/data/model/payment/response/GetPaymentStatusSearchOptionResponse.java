package net.fashiongo.webadmin.data.model.payment.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.payment.PaymentStatus;
import net.fashiongo.webadmin.data.model.vendor.Vendor;

import java.util.List;

@Getter
@Builder
public class GetPaymentStatusSearchOptionResponse {

	@JsonProperty("Table")
	private List<Vendor> vendorList;

	@JsonProperty("Table1")
	private List<PaymentStatus> paymentStatusList;
}
