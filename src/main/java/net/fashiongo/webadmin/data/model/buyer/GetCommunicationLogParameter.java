package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetCommunicationLogParameter {

	@JsonProperty(value = "retailerid")
	private Integer retailerId;

}
