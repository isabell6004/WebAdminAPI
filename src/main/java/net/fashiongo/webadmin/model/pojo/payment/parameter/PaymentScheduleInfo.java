package net.fashiongo.webadmin.model.pojo.payment.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentScheduleInfo {
	public Integer wholesalerId;
	@JsonProperty(value = "isLocked")
	public Boolean isLocked;
	@JsonProperty(value = "payoutScheduleId")
	public Integer payoutScheduleId;
	public Integer weekday;
	public Integer dayOfMonth;
}
