package net.fashiongo.webadmin.data.repository.primary.vendor;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class VendorImageRequestSelectParameter {
	private Integer pageNumber;
	private Integer pageSize;

	private Integer wholesalerId;
	private String wholesalerName;

	private Integer vendorImageTypeId;

	private VendorImageRequestApprovalType approvalType;

	private Boolean active;

	private LocalDateTime searchFrom;
	private LocalDateTime searchTo;

	private VendorImageRequestOrderingType orderingType;

	private Boolean showDeleted;

	public Integer getOffset() {
		if (this.pageNumber == null || this.pageSize == null) {
			return 0;
		}
		return (this.pageNumber - 1) * this.pageSize;
	}
}
