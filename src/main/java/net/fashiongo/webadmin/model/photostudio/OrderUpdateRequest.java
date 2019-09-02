package net.fashiongo.webadmin.model.photostudio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class OrderUpdateRequest {

	private static final String DEFAULT_TIME = " 18:00:00";

	@JsonProperty("orderID")
	private Integer orderId;

	@JsonProperty("modelID")
	private Integer modelId;

	@JsonProperty("discountID")
	private Integer discountId;

	private String photoshootDate;

	public void setPhotoshootDate(String photoshootDate) {
		this.photoshootDate = StringUtils.isNotEmpty(photoshootDate) ? photoshootDate + DEFAULT_TIME : null;
	}

	private String inHouseNote;

	private BigDecimal additionalChargeAmount;

	private BigDecimal additionalDiscountAmount;

	private List<DetailOrderQuantity> items;
}
