package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "tblOrders")
public class SummaryOrdersEntity {

	@Id
	@Column(name = "OrderID")
	private Integer orderID;

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;

	@Column(name = "RetailerID")
	private Integer retailerID;

	@Column(name = "TotalQty")
	private Integer totalQty;

	@Column(name = "OrderStatusID")
	private int orderStatusID;

	@Column(name = "TotalAmount")
	private BigDecimal totalAmount;

	@Column(name = "TotalAmountWSC")
	private BigDecimal totalAmountWSC;

	@Column(name = "TotalAmountWOSC")
	private BigDecimal totalAmountWOSC;

	@Column(name = "IsCancelledByBuyer")
	private Boolean isCancelledByBuyer;
}
