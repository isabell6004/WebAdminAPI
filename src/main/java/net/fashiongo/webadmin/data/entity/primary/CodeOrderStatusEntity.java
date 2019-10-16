package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "Code_OrderStatus")
public class CodeOrderStatusEntity {

	@Id
	@Column(name = "OrderStatusID")
	private Integer orderStatusID;

	@Column(name = "OrderStatusName")
	private String orderStatusName;

	@Column(name = "Name2Vendor")
	private String name2Vendor;

	@Column(name = "Name2Retailer")
	private String name2Retailer;
}
