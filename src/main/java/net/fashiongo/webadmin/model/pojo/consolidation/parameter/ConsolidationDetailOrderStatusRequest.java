package net.fashiongo.webadmin.model.pojo.consolidation.parameter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsolidationDetailOrderStatusRequest {
    private int consolidationId;
    private int orderId;
    private int orderStatusId;
}
