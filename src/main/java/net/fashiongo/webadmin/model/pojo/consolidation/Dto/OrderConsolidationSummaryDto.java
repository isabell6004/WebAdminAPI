package net.fashiongo.webadmin.model.pojo.consolidation.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class OrderConsolidationSummaryDto {
    private Long count;
    private BigDecimal totalAmount;
    private Integer totalQty;
}
