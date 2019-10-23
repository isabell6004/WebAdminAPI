package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "Channel_Log")
public class ChannelLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ChannelLogID")
	private Integer channelLogID;

	@Column(name = "IPAddress")
	private String ipAddress;

	@Column(name = "ReferenceID")
	private Integer referenceID;

	@Column(name = "OrderSessionGUID")
	private String orderSessionGUID;

	@Column(name = "HappenedAt")
	private Integer happenedAt;

	@Column(name = "ActionTypeID")
	private Integer actionTypeID;

	@Column(name = "TotalQty")
	private Integer totalQty;

	@Column(name = "TotalAmount")
	private BigDecimal totalAmount;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;
}
