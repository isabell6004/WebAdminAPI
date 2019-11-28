package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class GetModifiedByBuyerReadParameter {

	@JsonProperty("rid")
	private String rid;

	public Integer getRid() {
		return Optional.ofNullable(rid).filter(s -> StringUtils.hasLength(s)).map(s -> Integer.valueOf(s)).orElse(0);
	}
}
