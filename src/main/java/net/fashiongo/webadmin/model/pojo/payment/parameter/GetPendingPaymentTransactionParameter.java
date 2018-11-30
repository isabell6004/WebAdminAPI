package net.fashiongo.webadmin.model.pojo.payment.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author DAHYE
 *
 */
public class GetPendingPaymentTransactionParameter {
	@ApiModelProperty(required = false, example="204439")
	@JsonProperty("creditcardid")
	private Integer creditcardid;

	public Integer getCreditcardid() {
		return creditcardid == null ? 0: creditcardid;
	}

	public void setCreditcardid(Integer creditcardid) {
		this.creditcardid = creditcardid;
	}
	
	
}
