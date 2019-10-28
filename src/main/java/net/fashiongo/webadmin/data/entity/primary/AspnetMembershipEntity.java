package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Setter
@Getter
@Entity
@Table(name = "aspnet_Membership")
@DynamicUpdate
public class AspnetMembershipEntity {

	@Id
	@Column(name = "UserId")
	@Setter(AccessLevel.NONE)
	private String userId;

	@Column(name = "ApplicationId")
	private String applicationId;

	@Column(name = "Password")
	private String password;

	@Column(name = "PasswordFormat")
	private int passwordFormat;

	@Column(name = "PasswordSalt")
	private String passwordSalt;

	@Column(name = "MobilePIN")
	private String mobilePIN;

	@Column(name = "Email")
	private String email;

	@Column(name = "LoweredEmail")
	private String loweredEmail;

	@Column(name = "PasswordQuestion")
	private String passwordQuestion;

	@Column(name = "PasswordAnswer")
	private String passwordAnswer;

	@Column(name = "IsApproved")
	private boolean isApproved;

	@Column(name = "IsLockedOut")
	private boolean isLockedOut;

	@Column(name = "CreateDate")
	private Timestamp createDate;

	@Column(name = "LastLoginDate")
	private Timestamp lastLoginDate;

	@Column(name = "LastPasswordChangedDate")
	private Timestamp lastPasswordChangedDate;

	@Column(name = "LastLockoutDate")
	private Timestamp lastLockoutDate;

	@Column(name = "FailedPasswordAttemptCount")
	private int failedPasswordAttemptCount;

	@Column(name = "FailedPasswordAttemptWindowStart")
	private Timestamp failedPasswordAttemptWindowStart;

	@Column(name = "FailedPasswordAnswerAttemptCount")
	private int failedPasswordAnswerAttemptCount;

	@Column(name = "FailedPasswordAnswerAttemptWindowStart")
	private Timestamp failedPasswordAnswerAttemptWindowStart;

	@Column(name = "Comment")
	private String comment;

	@OneToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "UserId",referencedColumnName = "UserId", insertable = false,updatable = false)
	private AspnetUsersEntity aspnetUsers;


	public LocalDateTime getCreateDate() {
		return Optional.ofNullable(createDate).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getLastLoginDate() {
		return Optional.ofNullable(lastLoginDate).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getLastPasswordChangedDate() {
		return Optional.ofNullable(lastPasswordChangedDate).map(Timestamp::toLocalDateTime).orElse(null);
	}

	public LocalDateTime getLastLockoutDate() {
		return Optional.ofNullable(lastLockoutDate).map(Timestamp::toLocalDateTime).orElse(null);
	}
}
