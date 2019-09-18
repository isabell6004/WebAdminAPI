package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.FashionGoFormEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class VendorFormResponse {

	@JsonProperty("FashionGoFormID")
	private Integer fashionGoFormId;

	@JsonProperty("FormName")
	private String formName;

	@JsonProperty("Memo")
	private String memo;

	@JsonProperty("Attachment")
	private String attachment;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;

	@JsonProperty("ModifiedBy")
	private String modifiedBy;

	@JsonProperty("Cehckbox")
	private int checkbox;

	public static VendorFormResponse convert(FashionGoFormEntity entity) {
		return VendorFormResponse.builder()
				.fashionGoFormId(entity.getFashionGoFormId())
				.formName(entity.getFormName())
				.memo(entity.getMemo())
				.attachment(entity.getAttachment())
				.createdOn(entity.getCreatedOn())
				.createdBy(entity.getCreatedBy())
				.modifiedOn(entity.getModifiedOn())
				.modifiedBy(entity.getModifiedBy())
				.checkbox(0)
				.build();
	}
}
