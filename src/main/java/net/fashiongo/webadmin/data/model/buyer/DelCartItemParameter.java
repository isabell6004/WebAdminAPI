package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class DelCartItemParameter {

	@JsonProperty(value = "obj")
	private List<Integer> cartIds;
}
