package net.fashiongo.webadmin.data.model.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class UserLogin {

	@JsonProperty("LoginID")
	private Integer loginID;

	@JsonProperty("UserType")
	private String userType;

	@JsonProperty("UserID")
	private Integer userID;

	@JsonProperty("UserName")
	private String userName;

	@JsonProperty("IPAddress")
	private String ipAddress;

	@JsonProperty("LoginedOn")
	private LocalDateTime loginedOn;

	@JsonProperty("CompanyName")
	private String companyName;

	@JsonProperty("FullName")
	private String fullName;

	public UserLogin(Integer loginID, String userType, Integer userID, String userName, String ipAddress, Timestamp loginedOn, String companyName, String fullName) {
		this.loginID = loginID;
		this.userType = userType;
		this.userID = userID;
		this.userName = userName;
		this.ipAddress = ipAddress;
		this.loginedOn = Optional.ofNullable(loginedOn).map(Timestamp::toLocalDateTime).orElse(null);
		this.companyName = companyName;
		this.fullName = fullName;
	}
}
