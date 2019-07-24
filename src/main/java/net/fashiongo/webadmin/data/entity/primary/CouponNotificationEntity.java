package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Entity
@Table(name = "coupon_notification")
public class CouponNotificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_notification_id")
	private Integer couponNotificationId;

	@Column(name = "coupon_id")
	private Integer couponId;

	@Column(name = "notification_method")
	private Integer notificationMethod;

	@Column(name = "is_sent")
	private boolean isSent;

	@Column(name = "sent_date")
	private Timestamp sentDate;

	@Column(name = "notification_subject")
	private String notificationSubject;

	@Column(name = "notification_preview_text")
	private String notificationPreviewText;

	@Column(name = "notification_target_file")
	private String notificationTargetFile;

	@Column(name = "notification_image_file_name")
	private String notificationImageFileName;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_on")
	private Timestamp createdOn;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_on")
	private Timestamp modifiedOn;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "coupon_notification_id", referencedColumnName = "coupon_notification_id",updatable = false, insertable = false)
	private List<CouponNotificationTargetEntity> couponNotificationTargetEntityList;

}
