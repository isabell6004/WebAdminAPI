package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "aspnet_Users")
@Setter
public class AspnetUsersEntity {

	@Id
	@Setter(AccessLevel.NONE)
	@Column(name = "UserId")
	private String userId;

	@Column(name = "ApplicationId")
	private String applicationId;

	@Column(name = "UserName")
	private String userName;

	@Column(name = "LoweredUserName")
	private String loweredUserName;

	@Column(name = "MobileAlias")
	private String mobileAlias;

	@Column(name = "IsAnonymous")
	private boolean isAnonymous;

	@Column(name = "LastActivityDate")
	private Timestamp lastActivityDate;
}
