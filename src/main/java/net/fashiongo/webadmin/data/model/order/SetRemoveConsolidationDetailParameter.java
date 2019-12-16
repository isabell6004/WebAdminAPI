package net.fashiongo.webadmin.data.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SetRemoveConsolidationDetailParameter {

	@JsonProperty("data")
	private List<Integer> datas;
}
