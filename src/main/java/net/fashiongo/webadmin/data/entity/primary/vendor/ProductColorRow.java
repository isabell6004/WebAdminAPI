package net.fashiongo.webadmin.data.entity.primary.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductColorRow {
	@JsonProperty("ColorID")
	private int colorID;

	@JsonProperty("Color")
	private String color;

	@JsonProperty("Active")
	private boolean active;
}
