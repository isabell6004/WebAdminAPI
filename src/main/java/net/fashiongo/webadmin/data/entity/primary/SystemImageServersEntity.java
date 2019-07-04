package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "System_ImageServers")
public class SystemImageServersEntity {

	@Id
	@Column(name = "ImageServerID")
	private Integer imageServerID;

	@Column(name = "ImageServerName")
	private String imageServerName;

	@Column(name = "UrlPath")
	private String urlPath;

	@Column(name = "PhysicalPath")
	private String physicalPath;

	@Column(name = "Comment")
	private String comment;

	@Column(name = "AddMoreVendorYN")
	private boolean addMoreVendorYN;

	@Column(name = "VendorAdminUrl")
	private String vendorAdminUrl;

	@Column(name = "Active")
	private boolean active;

	@Column(name = "InsertUser")
	private String insertUser;

	@Column(name = "InsertDate")
	private Timestamp insertDate;

	@Column(name = "UpdateUser")
	private String updateUser;

	@Column(name = "UpdateDate")
	private Timestamp updateDate;
}
