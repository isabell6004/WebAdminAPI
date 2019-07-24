package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "coupon_notification_target")
public class CouponNotificationTargetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_notification_target_id")
	private Integer couponNotificationTargetId;

	@Column(name = "coupon_notification_id")
	private Integer coupon_notification_id;

	@Column(name = "notification_method")
	private Integer notificationMethod;

	@Column(name = "notification_target")
	private String notificationTarget;

	@Column(name = "is_sent")
	private boolean isSent;

	@Column(name = "unsubscribe_date")
	private Timestamp unsubscribeDate;

	@Column(name = "sent_fail_reason")
	private String sentFailReason;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_on")
	private Timestamp createdOn;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_on")
	private Timestamp modifiedOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_notification_id", referencedColumnName = "coupon_notification_id",updatable = false, insertable = false)
	private CouponNotificationEntity couponNotificationEntity;

}
