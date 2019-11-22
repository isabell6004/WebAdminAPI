package net.fashiongo.webadmin.data.model.franchise;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AutoCompleteParameter {

	@JsonProperty("searchText")
	private String searchText;
}
