package net.fashiongo.webadmin.data.model.buyer;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WholeSalerId {

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
}
