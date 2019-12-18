package net.fashiongo.webadmin.model.pojo.consolidation.parameter;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConslidationDropOffSaveRequest {
	private Integer orderId;
	private Integer consolidationId;
	private String poNumber;
	private String vendorName;
	private LocalDateTime dropOffTime;
	private String dropOffBy;
	private String receivedBy;
	private Integer itemQty;
	private Integer boxQty;
	private String memo;
}
