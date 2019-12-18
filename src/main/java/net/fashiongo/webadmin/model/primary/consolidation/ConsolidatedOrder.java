package net.fashiongo.webadmin.model.primary.consolidation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tblOrders")
@Where(clause = "IsConsolidated = 1")
public class ConsolidatedOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderID")
	private Integer orderId;
	
	@Column(name = "PONumber")
	private String poNumber;
	
	@Column(name = "WholeCompanyName")
	private String vendorName;
	
	@Column(name = "ConsolidationID")
	private Integer consolidationId;
	
	@OneToOne(targetEntity = ConsolidationOrders.class, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "OrderID", insertable = false, updatable = false)
	private ConsolidationOrders consolidationOrder;
}
