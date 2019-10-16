package net.fashiongo.webadmin.model.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GnbVendorGroupSaveRequest {
	private String title;

	@JsonProperty("isAlphabeticalOrder")
	private boolean isAlphabeticalOrder;

	private List<Integer> wholeSalerIdList = new ArrayList<>();
}
