package net.fashiongo.webadmin.model.pojo.buyer.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SetAttachedFileParameter {
	@JsonProperty("retailerId")
	private Integer retailerId;

	@JsonProperty("fileType")
	private String fileType;

	@JsonProperty("fileName")
	private String fileName;
}
