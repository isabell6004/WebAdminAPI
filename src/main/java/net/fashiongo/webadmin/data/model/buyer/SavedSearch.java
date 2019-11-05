package net.fashiongo.webadmin.data.model.buyer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SavedSearch {

	@JsonProperty("Active")
	private Boolean active;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	@JsonProperty("DisplayStr")
	private String displayStr;

	@JsonProperty("FilterStr")
	private String filterStr;

	@JsonProperty("ModifiedBy")
	private String modifiedBy;

	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;

	@JsonProperty("Remark")
	private String remark;

	@JsonProperty("SavedId")
	private Integer savedId;

	@JsonProperty("SavedName")
	private String savedName;

	@JsonProperty("SavedType")
	private String savedType;
}
