package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "ConsolidationOrders")
@DynamicUpdate
public class ConsolidationOrdersEntity {

	@Id
	@Column(name = "OrderID")
	private Integer orderID;

	@Column(name = "ConsolidationID")
	private int consolidationID;

	@Column(name = "DroppedBy")
	private String droppedBy;

	@Column(name = "ReceivedBy")
	private String receivedBy;

	@Column(name = "ReceivedOn")
	private Timestamp receivedOn;

	@Column(name = "NotifiedBy")
	private String notifiedBy;

	@Column(name = "NotifiedOn")
	private Timestamp notifiedOn;

	@Column(name = "DropReferenceID")
	private Integer dropReferenceID;
}
