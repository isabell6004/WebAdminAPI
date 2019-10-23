package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SetCartItemParameter {

	@JsonProperty(value = "obj")
	private List<SetCardItem> cardItems;
}
