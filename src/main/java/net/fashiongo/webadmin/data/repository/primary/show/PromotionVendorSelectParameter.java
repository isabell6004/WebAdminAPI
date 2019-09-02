package net.fashiongo.webadmin.data.repository.primary.show;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PromotionVendorSelectParameter {
	private Integer scheduleId;
	private Integer planId;
	private Integer pageNumber;
	private Integer pageSize;

	public Integer getOffset() {
		if (this.pageNumber == null || this.pageSize == null) {
			return 0;
		}
		return ((this.pageNumber - 1) * this.pageSize);
	}

	public boolean isPaginated() {
		return this.pageNumber != null && this.pageSize != null;
	}
}
