package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Table(name = "Log_Login")
public class LogLoginEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LoginID")
	private Integer loginID;

	@Column(name = "RetailerID")
	private int retailerID;

	@Column(name = "UserName")
	private String userName;

	@Column(name = "IPAddress")
	private String ipAddress;

	@Column(name = "LoggedOn")
	private Timestamp loggedOn;
}
