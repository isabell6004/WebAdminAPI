package net.fashiongo.webadmin.data.repository.primary.show;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ScheduleSelectParameter {
	private Integer pageNumber;
	private Integer pageSize;

	private Integer showID;
	private String showName;
	private String location;
	private Boolean active;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;

	private String orderBy;

	public Integer getOffset() {
		if (this.pageNumber == null || this.pageSize == null) {
			return 0;
		}
		return ((this.pageNumber - 1) * this.pageSize);
	}
}
