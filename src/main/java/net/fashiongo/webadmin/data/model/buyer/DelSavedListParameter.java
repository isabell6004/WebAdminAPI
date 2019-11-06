package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DelSavedListParameter {

	@JsonProperty("savedid")
	private Integer savedid;
}
