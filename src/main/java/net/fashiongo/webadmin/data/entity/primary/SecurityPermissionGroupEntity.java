package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "[security.Permission_Group]")
public class SecurityPermissionGroupEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PermissionID")
	@Setter(AccessLevel.NONE)
	private Integer permissionID;

	@Column(name = "GroupID")
	private int groupID;

	@Column(name = "ResourceID")
	private int resourceID;

	@Column(name = "Allow")
	private boolean allow;

	@Column(name = "AllowEdit")
	private boolean allowEdit;

	@Column(name = "AllowDelete")
	private boolean allowDelete;

	@Column(name = "AllowAdd")
	private boolean allowAdd;
}
