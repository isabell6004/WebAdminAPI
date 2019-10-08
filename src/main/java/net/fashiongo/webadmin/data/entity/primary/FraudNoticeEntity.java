package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Entity
@Table(name = "FraudNotice")
public class FraudNoticeEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "FraudNoticeID")
	private Integer fraudNoticeID;

	@Column(name = "WholeSalerID")
	private int wholeSalerID;

	@Column(name = "RetailerID")
	private int retailerID;

	@Column(name = "FraudDetail")
	private String fraudDetail;

	@Column(name = "CommentByWholeSaler")
	private String commentByWholeSaler;

	@Column(name = "CommentByFG")
	private String commentByFG;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "CreatedOn")
	private Timestamp createdOn;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private Timestamp modifiedOn;

	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Column(name = "OrderID")
	private Integer orderID;

	@Column(name = "OriginalStatus")
	private Integer originalStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WholeSalerID", referencedColumnName = "WholeSalerID", insertable = false, updatable = false)
	private SimpleWholeSalerEntity wholeSaler;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RetailerID", referencedColumnName = "RetailerID", insertable = false, updatable = false)
	private RetailerEntity retailer;

	public LocalDateTime getCreatedOn() {
		return Optional.ofNullable(createdOn).map(Timestamp::toLocalDateTime).orElse(null);
	}
}
