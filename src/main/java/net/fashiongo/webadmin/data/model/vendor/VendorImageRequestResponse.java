package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.entity.primary.VendorImageRequestEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class VendorImageRequestResponse {
	@JsonProperty("ImageRequestID")
	private Integer imageRequestId;

	@JsonProperty("WholeSalerID")
	private Integer wholesalerId;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("VendorImageTypeID")
	private Integer vendorImageTypeId;

	@JsonProperty("RequestedOn")
	private LocalDateTime requestedOn;

	@JsonProperty("RequestedBy")
	private String requestedBy;

	@JsonProperty("IsApproved")
	private Boolean isApproved;

	@JsonProperty("RejectReason")
	private String rejectReason;

	@JsonProperty("DecidedOn")
	private LocalDateTime decidedOn;

	@JsonProperty("DecidedBy")
	private String decidedBy;

	@JsonProperty("Active")
	private Boolean active;

	@JsonProperty("OriginalFileName")
	private String originalFileName;

	@JsonProperty("DeletedOn")
	private LocalDateTime deletedOn;

	@JsonProperty("DeletedBy")
	private String deletedBy;

	public static VendorImageRequestResponse convert(VendorImageRequestEntity entity) {
		String companyName = null;
		if (entity.getWholesaler() != null) {
			companyName = entity.getWholesaler().getCompanyName();
		}
		return VendorImageRequestResponse.builder()
				.imageRequestId(entity.getImageRequestId())
				.wholesalerId(entity.getWholesalerId())
				.companyName(companyName)
				.vendorImageTypeId(entity.getVendorImageTypeId())
				.requestedOn(entity.getRequestedOn())
				.requestedBy(entity.getRequestedBy())
				.isApproved(entity.getIsApproved())
				.rejectReason(entity.getRejectReason())
				.decidedOn(entity.getDecidedOn())
				.decidedBy(entity.getDecidedBy())
				.active(entity.isActive())
				.originalFileName(entity.getOriginalFileName())
				.deletedOn(entity.getDeletedOn())
				.deletedBy(entity.getDeletedBy())
				.build();
	}
}
