package net.fashiongo.webadmin.model.primary;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="FraudNotice")
public class FraudNotice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FraudNoticeID")
	private int fraudNoticeId;

	@Column(name = "WholeSalerID")
	private int wholesalerId;

	@Column(name = "RetailerID")
	private int retailerId;

	@Column(name = "FraudDetail")
	private String fraudDetail;

	@Column(name = "CommentByWholeSaler")
	private String commentByWholesaler;

	@Column(name = "CommentByFG")
	private String commentByFG;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "OrderID")
	private Integer orderId;

	@Column(name = "OriginalStatus")
	private Integer originalStatus;
}
