package net.fashiongo.webadmin.data.repository.primary.show;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ListShowSelectParameter {
	private Integer pageNum;
	private Integer pageSize;
	private Boolean active;
	private String location;
	private String orderBy;
	private String showName;

	public Integer getOffset() {
		if (this.pageNum == null || this.pageSize == null) {
			return 0;
		}
		return ((this.getPageNum() - 1) * this.getPageSize());
	}
}
