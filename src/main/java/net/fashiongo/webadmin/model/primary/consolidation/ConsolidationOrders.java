package net.fashiongo.webadmin.model.primary.consolidation;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ConsolidationOrders")
public class ConsolidationOrders {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderID")
	private Integer orderId;
	
	@Column(name = "ConsolidationID")
	private Integer consolidationId;
	
	@Column(name = "DroppedBy")
	private String droppedBy;
	
	@Column(name = "ReceivedBy")
	private String receivedBy;
	
	@Column(name = "ReceivedOn")
	private LocalDateTime receivedOn;
	
	@Column(name = "NotifiedBy")
	private String notifiedBy;
	
	@Column(name = "NotifiedOn")
	private LocalDateTime notifiedOn;
	
	@Column(name = "DropReferenceID")
	private Integer dropReferenceId;
	
	@Column(name = "ItemQty")
	private Integer itemQty;
	
	@Column(name = "BoxQty")
	private Integer boxQty;
	
	@Column(name = "Memo")
	private String memo;
}
