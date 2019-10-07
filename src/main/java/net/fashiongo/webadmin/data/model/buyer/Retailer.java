package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Retailer {

	private String lastLoginDate;

	private boolean isLockOut;

	private String lastLockoutDate;

	@JsonProperty("retailer")
	private RetailerDetail retailerDetail;
}
