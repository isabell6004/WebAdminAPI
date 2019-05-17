package net.fashiongo.webadmin.model.photostudio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvailableModelsResponse {

	private String imageUrl;

	private int isToday = 0;

	@JsonProperty("modelID")
	private Integer modelId;

	private String modelName;

	private List<String> nextAvailableDates;
}
